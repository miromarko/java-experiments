package ro.springsoft.poc.utils;

import ro.springsoft.poc.persistence.DAOFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet container app. startup and destroy listener
 *
 * @author Miroslav MARKO
 */
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory.init();
    }

    /**
     * Close all DB connections on app. undeploy
     * @param sce 
     */
    public void contextDestroyed(ServletContextEvent sce) {
        //close all connections to database
        DAOFactory.getInstance().disposeConnections();
    }
}
