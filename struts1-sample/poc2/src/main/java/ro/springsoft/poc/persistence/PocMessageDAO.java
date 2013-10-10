package ro.springsoft.poc.persistence;

import ro.springsoft.poc.model.PocMessage;

import java.util.List;

/**
 * This interface provides methods to access data from POCMESSAGE table
 *
 * @author Miroslav MARKO
 */
public interface PocMessageDAO {

    /**
     * Insert or Update a new record in Database.
     *
     * @param pocMessage The Object to be inserted.
     * @throws Exception if something is wrong.
     */
    boolean upsert(PocMessage pocMessage) throws Exception;

    /**
     * Return all messages from DB
     *
     * @throws Exception if something is wrong.
     */
    List<PocMessage> findAll() throws Exception;
}
