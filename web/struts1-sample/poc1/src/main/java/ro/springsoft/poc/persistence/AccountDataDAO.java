package ro.springsoft.poc.persistence;

import ro.springsoft.poc.model.AccountData;

import java.util.List;

/**
 *
 * @author Miroslav MARKO
 * This interface provides methods to access data 
 * from ACCOUNTDATA table
 */
public interface AccountDataDAO {
    /**
     * Create a new record in Database.
     * 
     * @param accountData The Object to be inserted.
     * @exception Exception if something is wrong.
     */
    void create(AccountData accountData) throws Exception;
    
    /**
     * Retrieve all objects from DB
     * 
     * @return List of all AccountData from DB
     * @throws Exception if something is wrong.
     */
    List<AccountData> findAll() throws Exception;
    
}
