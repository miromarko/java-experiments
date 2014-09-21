package ro.springsoft.exceptions;

import java.io.Serializable;


/**
 * Created by Miro on 8/20/2014.
 */
public class MissingEntityIdException extends PersistentException implements Serializable{
    public MissingEntityIdException() {
    }

    @Override
    public String getMessage() {
        return "Missing business id for given entities !";
    }
}
