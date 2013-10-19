package ro.springsoft.bean;

import ro.springsoft.guice.Log;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;

/**
 *
 * @author John Yeary <jyeary@springsoft.com>
 */
@ManagedBean
@ViewScoped
public class IndexBean implements Serializable {

    private static final long serialVersionUID = 2921749833278042423L;

    @Log
    private Logger log;

    private String name;

    public IndexBean() {
        // This will not work since @Log is injected after @PostConstruct.
//    log.info("This will not work.");
    }

    @PostConstruct
    private void init() {
//        log.info("This will not work since the Guice @Log is not injected until after post contstruct.");
        name = "I love Guice";
    }

    public String getName() {
        log.info("getName() \u2192 " + name);
        return name;
    }

    public void setName(String name) {
        log.info("setName() \u2192 " + name);
        this.name = name;
    }
    
    
}
