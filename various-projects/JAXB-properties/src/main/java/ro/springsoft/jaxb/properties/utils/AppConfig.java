package ro.springsoft.jaxb.properties.utils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

/**
 * @author Miroslav MARKO <mmarko@springsoft.com>
 */
@XmlRootElement(name = "app-config")
@XmlAccessorType(XmlAccessType.NONE)
public class AppConfig extends HashMap<String, String> {

    private static final long serialVersionUID = 5457583791774559003L;
    private static final String configFile = "app-config.xml";
    private static final Logger LOG = Logger.getLogger(AppConfig.class.getName());
    private static volatile AppConfig instance = null;
    private static String config = null;
    @XmlElement(name = "configuration")
    private List<Type> types;

    @XmlElement(name = "property")
    public MapEntry[] getMap() {
        List<MapEntry> list = new ArrayList<>();
        for (Entry<String, String> entry : this.entrySet()) {
            MapEntry mapEntry = new MapEntry();
            mapEntry.name = entry.getKey();
            mapEntry.value = entry.getValue();
            list.add(mapEntry);
        }
        return list.toArray(new MapEntry[list.size()]);
    }

    public void setMap(MapEntry... entries) {
        for (MapEntry entry : entries) {
            this.put(entry.name, entry.value);
        }
    }

    public static AppConfig getInstance(String conf) {
        if (AppConfig.config != null) {
            instance = null;
            config = conf;
        }
        return getInstance();
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            try {
                AppConfig appConfig;
                InputStream configResourceAsStream = AppConfig.class.getClassLoader().getResourceAsStream(configFile);
                JAXBContext jc = JAXBContext.newInstance(AppConfig.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                appConfig = (AppConfig) unmarshaller
                        .unmarshal(configResourceAsStream);
                if (config == null) {
                    config = appConfig.get("config");
                }
                if (config != null) {
                    for (Type t : appConfig.types) {
                        if (t.type.equals(config)) {
                            for (MapEntry entry : t.properties) {
                                appConfig.put(entry.name, entry.value);
                            }
                        }
                    }
                    appConfig.types = null; // free memory unused objects
                }
                instance = appConfig;

            } catch (JAXBException e) {
                LOG.severe(e.getMessage());
            }
        }
        return instance;
    }

    public static String getProperty(String key){
        return getInstance().get(key);
    }

    public static class Type implements Serializable {

        private static final long serialVersionUID = 1L;
        @XmlAttribute
        public String type;
        @XmlElement(name = "property")
        public List<MapEntry> properties;
    }

    public static class MapEntry implements Serializable {

        private static final long serialVersionUID = 1L;
        @XmlAttribute
        public String name;
        @XmlValue
        public String value;
    }
}