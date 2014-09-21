package ro.springsoft.model.common;

import java.util.List;
import java.util.logging.Logger;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import ro.springsoft.persistence.IPersistenceCommand;

/**
 * Created by Miro on 8/20/2014.
 */
public class MongoInsertCommand implements IPersistenceCommand {
    private final Logger log = Logger.getLogger(MongoInsertCommand.class.getName());
    private String failKey = null;
    private boolean failed = false;
    private final List<DBObject> dbObjects;
    private final DBCollection dbCollection;


    public MongoInsertCommand(List<DBObject> dbObjects, DBCollection dbCollection) {
        this.dbObjects = dbObjects;
        this.dbCollection = dbCollection;
        this.failKey = dbCollection.getName();
    }

    @Override
    public String getKey() {
        return failKey;
    }

    @Override
    public boolean failed() {
        return failed;
    }

    @Override
    public void run() {
        try {
            dbCollection.insert(dbObjects);
        } catch (MongoException e) {
            log.severe(e.getMessage());
            failed = true;
        }
    }
}
