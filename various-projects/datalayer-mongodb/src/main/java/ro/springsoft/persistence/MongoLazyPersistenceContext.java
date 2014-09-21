package ro.springsoft.persistence;


import ro.springsoft.conf.ModuleConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 * Created by Miro on 8/20/2014.
 */
public class MongoLazyPersistenceContext extends MongoPersistenceContext {
    private final MongoPersistenceContext simpleContext;
    private ExecutorService executorService;

    public MongoLazyPersistenceContext(MongoPersistenceContext simpleContext) {
        this.simpleContext = simpleContext;
        int nrThreads = 10;
        try {
            nrThreads = Integer.parseInt(ModuleConfig.getProperty("mongoLazyContextThNr"));
        } catch (NumberFormatException e) {
            Logger.getLogger(this.getClass().getName()).info(e.getMessage());
        }
        ExecutorService executorService = Executors.newFixedThreadPool(nrThreads);

    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void execute(IPersistenceCommand... commands) {
        //skip commands with failed keys
        for (IPersistenceCommand cmd : commands) {
            executorService.execute(cmd);
        }
        //check for errors


    }

}
