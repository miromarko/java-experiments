package ro.springsoft.exceptions;

import java.io.Serializable;


/**
 * Created by Miro on 8/29/2014.
 */
public class DuplicateIdException extends PersistentException implements Serializable {
    private static final long serialVersionUID = 8108325451559361377L;
    private final String message;

    public DuplicateIdException(String id) {
        message = "Entity with this name: " + id
                + " has already been registered in the system! ";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }

    @Override
    public String toString() {
      return message;
    }
}
