package ro.springsoft.model.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import ro.springsoft.dao.IBaseDAO;
import ro.springsoft.exceptions.DuplicateIdException;
import ro.springsoft.exceptions.MissingEntityIdException;
import ro.springsoft.exceptions.MongoPersistenceException;
import ro.springsoft.exceptions.PersistentException;
import ro.springsoft.persistence.IPersistenceCommand;
import ro.springsoft.persistence.MongoPersistenceContext;

public abstract class BaseDAO<T extends IPersistent> implements IBaseDAO<T> {
    protected MongoPersistenceContext persistenceContext;
    private final DBCollection dbCollection;
    private final MongoAdapter<T> mongoAdapter;

    protected BaseDAO(DBCollection dbCollection, MongoAdapter<T> mongoAdapter) {
        this.dbCollection = dbCollection;
        this.mongoAdapter = mongoAdapter;
    }

    public DBCollection getDbCollection() {
        return dbCollection;
    }

    public MongoAdapter<T> getMongoAdapter() {
        return mongoAdapter;
    }

    public void insert(T... entities) throws PersistentException {
        persistenceContext.execute(getInsertCommand(entities));
    }
    public void safeInsert(T entity) throws PersistentException {
        if(entity.getBusinessId()!=null && exist(entity.getBusinessId()))
            throw new DuplicateIdException(entity.getBusinessId());
        insert(entity);
    }

    public void update(T entity) throws PersistentException {
        persistenceContext.execute(getUpdateCommand(entity));
    }

    public void delete(T entity) throws PersistentException {
        persistenceContext.execute(getDeleteCommand(entity));
    }

    @SafeVarargs
    protected final IPersistenceCommand getInsertCommand(T... entities) throws PersistentException {
        List<DBObject> dbObjects = new ArrayList<>();
        for (T e : entities)
            if (e.getBusinessId() != null) dbObjects.add(mongoAdapter.toDBObject(e));
            else throw new MissingEntityIdException();
//        if (entities.length != dbObjects.size())
//            throw new MissingEntityIdException();
        return new MongoInsertCommand(dbObjects, dbCollection);
    }

    protected IPersistenceCommand getUpdateCommand(T entity) throws PersistentException {
        if (entity.getBusinessId() != null) {
            DBObject dbObject = mongoAdapter.toDBObject(entity);
            return new MongoUpdateCommand(dbObject, dbCollection);
        } else
            throw new MissingEntityIdException();
    }

    protected IPersistenceCommand getDeleteCommand(T entity) throws PersistentException {
        if (entity.getBusinessId() != null) {
            DBObject criteriaObj = new BasicDBObject();
            criteriaObj.put(CommonFields.BUSINESS_ID.fieldName(), entity.getBusinessId());
            criteriaObj.put(CommonFields.DELETED.fieldName(), false);
            return new MongoDeleteCommand(criteriaObj, dbCollection);
        } else
            throw new MissingEntityIdException();
    }

    public Collection<T> findAllDeletedInclusive() throws MongoPersistenceException {
        try {
            DBCursor find = dbCollection.find();
            return dbCursorToList(find);
        } catch (MongoException e) {
            throw new MongoPersistenceException(e);
        }
    }

    public Collection<T> findAll() throws MongoPersistenceException {
        try {
            DBCursor find = dbCollection.find(new BasicDBObject(CommonFields.DELETED.fieldName(), false));
            return dbCursorToList(find);
        } catch (MongoException e) {
            throw new MongoPersistenceException(e);
        }
    }

    private List<T> dbCursorToList(DBCursor dbCursor) {
        List<T> entities = new ArrayList<>();

        while (dbCursor.hasNext()) {
            DBObject object = dbCursor.next();
            entities.add(mongoAdapter.fromDBObject(object));
        }
        return entities;
    }

    public T get(String id) throws MongoPersistenceException {
        try {
            DBObject dbDoc = dbCollection.findOne(new BasicDBObject(CommonFields.BUSINESS_ID.fieldName(), id).append(CommonFields.DELETED.fieldName(), false));
            return mongoAdapter.fromDBObject(dbDoc);
        } catch (MongoException e) {
            throw new MongoPersistenceException(e);
        }
    }

    protected boolean exist(String id) throws MongoPersistenceException{
        try {
            DBCursor find = dbCollection.find(new BasicDBObject(CommonFields.BUSINESS_ID.fieldName(), id).append(CommonFields.DELETED.fieldName(), false));
            return find.hasNext();
        } catch (MongoException e) {
            throw new MongoPersistenceException(e);
        }
    }

    public long count(){
        return dbCollection.count(new BasicDBObject(CommonFields.DELETED.fieldName(),false));
    }
    public long countDeletedInclusive(){
        return dbCollection.count();
    }
}
