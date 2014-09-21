package ro.springsoft.model.common;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * Created by Miro on 9/4/2014.
 */
public class GenericMongoAdapter<T extends IPersistent> implements MongoAdapter<T> {
    private Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    private Class<? extends T> tClass ;

    public GenericMongoAdapter(Class<? extends T> cls) {
        tClass = cls;
    }

    @Override
    public DBObject toDBObject(T entity) {
        if (entity == null) {
            return null;
        }
        DBObject dbo = (DBObject) JSON.parse(gson.toJson(entity));
        // mongodb will not execute update commands for objects with _id
        // _id cannot be changed
        dbo.removeField("_id");
        return dbo;
    }

    @Override
    public T fromDBObject(DBObject dbObject) {
        if (dbObject == null) {
            return null;
        }

        T obj = gson.fromJson(JSON.serialize(dbObject), tClass);
        return (T) obj;
    }
}
