package ro.springsoft.model.common;

import com.mongodb.DBObject;

public interface MongoAdapter<T extends IPersistent> {
    DBObject toDBObject(T client);

    T fromDBObject(DBObject dbObject);
}