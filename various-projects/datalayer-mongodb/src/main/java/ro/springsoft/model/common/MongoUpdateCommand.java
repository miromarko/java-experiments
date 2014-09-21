package ro.springsoft.model.common;


import java.util.logging.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import ro.springsoft.persistence.IPersistenceCommand;
import static ro.springsoft.model.common.CommonFields.*;

/**
 * Created by Miro on 8/20/2014.
 */
public class MongoUpdateCommand implements IPersistenceCommand {
    private final Logger log = Logger.getLogger(MongoUpdateCommand.class.getName());
    private final DBObject dbObject;
    private final DBCollection dbCollection;
    private boolean failed = false;
    private String failKey = null;

    public MongoUpdateCommand(DBObject dbObject, DBCollection dbCollection) {
        this.dbCollection = dbCollection;
        this.dbObject = dbObject;
        failKey = dbCollection.getName();
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
            DBObject selCriteria = new BasicDBObject();
            selCriteria.put(BUSINESS_ID.fieldName(),
                            dbObject.get(BUSINESS_ID.fieldName()));
            selCriteria.put(CommonFields.DELETED.fieldName(),false);
            dbCollection.findAndModify(selCriteria, dbObject);
        } catch (MongoException e) {
            log.severe(e.toString());
            failed = true;
        }

    }
}
