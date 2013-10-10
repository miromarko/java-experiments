package ro.springsoft.poc.persistence;

import ro.springsoft.poc.model.AccountData;

import java.util.Random;
import static org.junit.Assert.*;

/**
 *
 * @author Miroslav MARKO
 */
public class PersistenceTest {

    public PersistenceTest() {
    }

    /**
     * Test of create method, of class AccountDataDAOH2Impl.
     */
    @org.junit.Test
    public void testPersist() throws Exception {
        AccountDataDAO accountDataDAO = DAOFactory.getInstance().getAccountDataDAO();
        Random r = new Random();
        AccountData accountData = new AccountData("2013-10-19", "EUR", r.nextInt(10000) % 100 + 10, "john@gmail.com");
        accountDataDAO.create(accountData);
        boolean testResult = accountDataDAO.findAll().contains(accountData);
        
        
        System.out.println("TestPersist result: " + testResult);
        DAOFactory.getInstance().disposeConnections();
        assertTrue(testResult);

    }
}