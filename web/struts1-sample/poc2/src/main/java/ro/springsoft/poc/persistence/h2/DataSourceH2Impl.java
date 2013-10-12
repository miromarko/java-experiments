package ro.springsoft.poc.persistence.h2;

import ro.springsoft.poc.persistence.DataSource;
import org.h2.jdbcx.JdbcConnectionPool;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Data provider implementation for H2DB
 *
 * @author Miroslav MARKO
 */
public final class DataSourceH2Impl extends DataSource {
    private static final Logger LOG = Logger.getLogger(DataSourceH2Impl.class.getName());
    private static final String JDBC_URL = "jdbc:h2:" + System.getProperty("user.home") + File.separator + "db" + File.separator + "pocdb" + ";AUTO_SERVER=true";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWD = "user1";

    private static String createTableSQL = "CREATE TABLE IF NOT EXISTS POCMESSAGE (account VARCHAR(255), balance FLOAT, timestamp TIMESTAMP)";

    private JdbcConnectionPool connectionPool;

    public DataSourceH2Impl() {
        connectionPool = JdbcConnectionPool.create(JDBC_URL
                , DB_USER, DB_PASSWD);
        try {
            this.initdb();
        } catch (Exception e) {
            LOG.severe(e.toString());
        }
    }

    @Override
    public Connection getConnection() throws Exception {

        return connectionPool.getConnection();
    }

    @Override
    public void disposeConnectionPool() {
        if (null != connectionPool) {
            connectionPool.dispose();
        }
    }

    /**
     * Initialize DB
     *
     * @throws Exception
     */
    private void initdb() throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate(createTableSQL);
        } finally {
            stmt.close();
            conn.close();
        }
    }

}
