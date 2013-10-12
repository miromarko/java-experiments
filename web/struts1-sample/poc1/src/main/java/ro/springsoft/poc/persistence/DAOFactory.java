package ro.springsoft.poc.persistence;

import ro.springsoft.poc.persistence.h2.AccountDataDAOH2Impl;
import ro.springsoft.poc.persistence.h2.DataSourceH2Impl;

/**
 * Singleton for producing Data Access Objects needed by application
 * 
 * @author Miroslav MARKO
 */
public class DAOFactory {

    private static DAOFactory _instance = null;
    private DataSource dataSource;
    private AccountDataDAO accountDataDAO;

    private DAOFactory() {
        //implement another init method in case of db change
        initH2DB();
    }

    //init persistence objects related to underlying storage
    //in current implementation H2DB
    private void initH2DB() {
        dataSource = new DataSourceH2Impl();
        accountDataDAO = new AccountDataDAOH2Impl(dataSource);
    }

    public AccountDataDAO getAccountDataDAO() {
        return accountDataDAO;
    }

    public static DAOFactory getInstance() {
        if (null == _instance) {
            _instance = new DAOFactory();
        }
        return _instance;
    }
    
    public void disposeConnections(){
        dataSource.disposeConnectionPool();
    }

    /**
     * Method called from ContextListener
     * for db initialization - all initializations done in constructor
     */
    public static void init(){
        _instance = new DAOFactory();
    }
}
