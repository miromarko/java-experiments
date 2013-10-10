package ro.springsoft.poc.persistence.h2;

import ro.springsoft.poc.model.PocMessage;
import ro.springsoft.poc.persistence.DataSource;
import ro.springsoft.poc.persistence.PocMessageDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * PocMessageDAOH2 implementation for H2DB
 *
 * @author Miroslav MARKO
 */
public class PocMessageDAOH2Impl implements PocMessageDAO {

    private DataSource dataSource;
    private static String trx_query1 = "SELECT * FROM POCMESSAGE WHERE account = ?";
    private static String trx_query2 = "UPDATE POCMESSAGE SET balance = ?, timestamp = ? WHERE account = ? and timestamp < ?";
    private static String trx_query3 = "INSERT INTO POCMESSAGE VALUES(?,?,?) ";
    private static String findAllSQL = "SELECT * FROM POCMESSAGE";


    public PocMessageDAOH2Impl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Inert or update a new record in Database.
     *
     * @param pocMessage The Object to be inserted.
     * @exception Exception if something is wrong.
     */
    public boolean upsert(PocMessage pocMessage) throws Exception {
        Connection conn = dataSource.getConnection();
        boolean returnVal = false;

        try {
            //start a transaction
            conn.setAutoCommit(false);

            // IF EXISTS: SELECT * FROM POCMESSAGE WHERE account = ?
            PreparedStatement preparedStatement1 = conn.prepareStatement(trx_query1);

            preparedStatement1.setString(1, pocMessage.getAccount());
            boolean accountExist = preparedStatement1.executeQuery().next();
            preparedStatement1.close();
            if (accountExist) {
                //UPDATE POCMESSAGE SET balance = ?, timestamp = ? WHERE account = ? and timestamp < ?
                PreparedStatement preparedStatement2 = conn.prepareStatement(trx_query2);
                //UPDATE
                preparedStatement2.setFloat(1, pocMessage.getBallance());
                preparedStatement2.setTimestamp(2, new Timestamp(pocMessage.getTimestamp()));
                //WHERE
                preparedStatement2.setString(3, pocMessage.getAccount());
                preparedStatement2.setTimestamp(4, new Timestamp(pocMessage.getTimestamp()));
                if (preparedStatement2.executeUpdate() > 0) {
                    returnVal = true;
                }
                preparedStatement2.close();
            } else {
                //INSERT INTO POCMESSAGE VALUES(?,?,?) 
                PreparedStatement preparedStatement3 = conn.prepareStatement(trx_query3);
                // INSERT
                preparedStatement3.setString(1, pocMessage.getAccount());
                preparedStatement3.setFloat(2, pocMessage.getBallance());
                preparedStatement3.setTimestamp(3, new Timestamp(pocMessage.getTimestamp()));

                preparedStatement3.executeUpdate();
                returnVal = true;
                preparedStatement3.close();

            }
            conn.commit(); // comit transaction
        } finally {
            //return the connection to ConnectinPool
            //do not close the connection in other impl. if you 
            //not use a JDBConnectionPoll
            conn.close();
        }
        return returnVal;
    }

    /**
     * Return all messages from DB
     *
     * @exception Exception if something is wrong.
     */
    public List<PocMessage> findAll() throws Exception {
        List<PocMessage> returnList = new LinkedList<PocMessage>();
        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(findAllSQL);
        try {
            while (resultSet.next()) {
                PocMessage pocMessage = new PocMessage();
                pocMessage.setAccount(resultSet.getString("account"));
                pocMessage.setBallance(resultSet.getLong("balance"));
                pocMessage.setTimestamp(resultSet.getTimestamp("timestamp").getTime());
                returnList.add(pocMessage);
            }
        } finally {
            statement.close();
            resultSet.close();
            //return the connection to ConnectinPool
            //do not close the connection in other impl. if you 
            //not use a JDBConnectionPoll
            conn.close();
        }
        return returnList;
    }
}
