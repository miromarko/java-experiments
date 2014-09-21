package ro.springsoft.dao;

import ro.springsoft.exceptions.PersistentException;
import ro.springsoft.model.common.IPersistent;

import java.util.Collection;


/**
 * Created by Miro on 8/29/2014.
 */
public interface IBaseDAO<T extends IPersistent> {
    /**
     * Insert entities without checking duplicated business id
     * @param entities
     * @throws PersistentException
     */
    void insert(T... entities) throws PersistentException;

    /**
     * Insert with check if business id already exist in database
     * @param entity
     * @throws PersistentException (DuplicateIdException if business id exist in db)
     */
    void safeInsert(T entity) throws PersistentException;

    void update(T entity) throws PersistentException;

    void delete(T entity) throws PersistentException;

    /**
     * Find all non deleted entities
     * @return
     * @throws PersistentException
     */
    Collection<T> findAll() throws PersistentException;

    /**
     * Find all entities, deleted entities inclusive
     * @return
     * @throws PersistentException
     */
    Collection<T> findAllDeletedInclusive() throws PersistentException;

    /**
     * find by business id
     * @param id
     * @return Persistent Entity
     * @throws PersistentException
     */
    T get(String id) throws PersistentException;

    long count();

    long countDeletedInclusive();
}
