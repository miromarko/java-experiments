package ro.springsoft.model.common;


import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import ro.springsoft.persistence.IPersistenceCommand;
import static ro.springsoft.model.common.CommonFields.*;

/**
 * Created by Miro on 8/21/2014.
 */
public class MongoDeleteCommand implements IPersistenceCommand {
    private final DBObject updateObj;
    private final Logger log = Logger.getLogger(MongoDeleteCommand.class.getName());
    private final DBObject criteriaObj;
    private final DBCollection dbCollection;
    private boolean failed = false;
    private String failKey = null;

    public MongoDeleteCommand(DBObject criteriaObj, DBCollection dbCollection) {
        this.dbCollection = dbCollection;
        this.criteriaObj = criteriaObj;
        updateObj = new BasicDBObject(DELETED.fieldName(), true);
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
            dbCollection.findAndModify( criteriaObj,
                    new BasicDBObject("$set",updateObj));
        } catch (MongoException e) {
            log.severe(e.getMessage());
            failed = true;
        }
    }
}
