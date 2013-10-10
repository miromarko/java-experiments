package ro.springsoft.poc.persistence;

import java.sql.Connection;

/**
 * Abstract data provider 
 * will be implemented according to each DB
 * @author Miroslav MARKO
 */
public abstract class DataSource {
    public static String jdbc_url;
    public static String user;
    public static String password;


    /**
     * Close the connection pool
     * associated with this data provider
     */
    public abstract void disposeConnectionPool();

    /**
     * Method to retrieve a db connection
     * usually from a connection pool
     * @return java.sql.Connection
     * @throws Exception 
     */
    public abstract Connection getConnection() throws Exception;
}
