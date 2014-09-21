package ro.springsoft.conf;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class ModuleConfig {
    private static final Logger LOG = Logger.getLogger(ModuleConfig.class
            .getName());
    private static String configFile = "module-config.xml";
    private static ModuleConfig _instance = null;
    private Properties props = null;

    private ModuleConfig() {
        InputStream configResourceAsStream;
        try {
            configResourceAsStream = new FileInputStream(new File(configFile));
        } catch (FileNotFoundException e) {
            configResourceAsStream = ModuleConfig.class.getClassLoader()
                    .getResourceAsStream(configFile);
        }

        props = new Properties();
        try {
            props.loadFromXML(configResourceAsStream);
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        }
    }

    private static ModuleConfig getInstance() {
        if (_instance == null) {
            _instance = new ModuleConfig();
        }
        return _instance;
    }

    public static String getProperty(String key) {
        return ModuleConfig.getInstance().get(key);
    }

    public static String getConfigFilePath() {
        return configFile;
    }

    public static void setConfigFilePath(String configFile) {
        ModuleConfig.configFile = configFile;
    }

    String get(String key) {
        return props.getProperty(key);
    }

}
