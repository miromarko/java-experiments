package ro.springsoft.poc.persistence;

import ro.springsoft.poc.persistence.h2.DataSourceH2Impl;
import ro.springsoft.poc.persistence.h2.PocMessageDAOH2Impl;

/**
 * Singleton for producing Data Access Objects needed by application
 *
 * @author Miroslav MARKO
 */
public class DAOFactory {

    private static DAOFactory _instance = null;
    private DataSource dataSource;
    private PocMessageDAO pocMessageDAO;

    private DAOFactory() {
        initH2DB();
    }

    private void initH2DB() {
        dataSource = new DataSourceH2Impl();
        pocMessageDAO = new PocMessageDAOH2Impl(dataSource);
    }

    public static DAOFactory getInstance() {
        if (null == _instance) {
            _instance = new DAOFactory();
        }
        return _instance;
    }

    /**
     * Method called from ContextListener
     * for db initialization - all initializations done in constructor
     */
    public static void init(){
        _instance = new DAOFactory();
    }

    public void disposeConnections() {
        dataSource.disposeConnectionPool();
    }

    public PocMessageDAO getPocMessageDAO() {
        return pocMessageDAO;
    }
}
