package ro.springsoft.persistence;

/**
 * Created by Miro on 8/20/2014.
 */
public interface IPersistenceCommand extends Runnable {
    String getKey(); //chain key

    boolean failed();
}
