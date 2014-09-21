package ro.springsoft.persistence;

/**
 * Created by Miro on 8/20/2014.
 */
public interface IPersistenceContext {
    void execute(IPersistenceCommand... commands);
}
