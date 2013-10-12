package ro.springsoft.poc.persistence.h2;

import ro.springsoft.poc.model.AccountData;
import ro.springsoft.poc.persistence.AccountDataDAO;
import ro.springsoft.poc.persistence.DataSource;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * AccountDataDAO implementation for H2DB
 *
 * @author Miroslav MARKO
 */
public class AccountDataDAOH2Impl implements AccountDataDAO {

    private DataSource dataSource;

    private static String insertSQL = "INSERT INTO ACCOUNTDATA VALUES(?,?,?,?)";
    private static String findAllSQL = "SELECT * FROM ACCOUNTDATA";

    public AccountDataDAOH2Impl(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    /**
     * Create a new record in Database.
     *
     * @param accountData The Object to be inserted.
     * @exception Exception if something is wrong.
     */
    public void create(AccountData accountData) throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(insertSQL);
        try {
            preparedStatement.setDate(1, accountData.getDateAsSqlDate());
            preparedStatement.setString(2, accountData.getCurrency());
            preparedStatement.setFloat(3, accountData.getAmountAsFloat());
            preparedStatement.setString(4, accountData.getEmail());
            preparedStatement.executeUpdate();
        } finally {
            preparedStatement.close();
            //return the connection to ConnectinPool
            //do not close the connection in other impl. if you 
            //not use a JDBConnectionPoll
            conn.close();
        }
    }

    /**
     * Retrieve all objects from DB
     *
     * @return List of all AccountData from DB
     * @throws SQLException if something is wrong.
     */
    public List<AccountData> findAll() throws Exception {
        List<AccountData> returnList = new LinkedList<AccountData>();
        Connection conn = dataSource.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(findAllSQL);
        try {
            while (resultSet.next()) {
                AccountData accountData = new AccountData();
                accountData.setDate(resultSet.getDate("date"));
                accountData.setCurrency(resultSet.getString("currency"));
                accountData.setAmount(resultSet.getFloat("amount") + "");
                accountData.setEmail(resultSet.getString("email"));
                returnList.add(accountData);
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
