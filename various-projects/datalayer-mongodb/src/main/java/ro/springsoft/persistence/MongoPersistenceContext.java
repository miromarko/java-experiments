package ro.springsoft.persistence;

import com.mongodb.DB;

/**
 * Created by Miro on 8/20/2014.
 */
public class MongoPersistenceContext implements IPersistenceContext {
    private DB db;

    MongoPersistenceContext() {
    }

    public MongoPersistenceContext(DB db) {
        this.db = db;
    }

    public DB getDb() {
        return db;
    }

    void setDb(DB db) {
        this.db = db;
    }

    @Override
    public void execute(IPersistenceCommand... commands) {
        for (IPersistenceCommand c : commands) {
            c.run();
        }
    }
}
