/**
 * @(#)DuplicateNameException.java
 * @version: 1.0
 *
 * Copyright (c) 2005-2007 Green Mountain Analytics 
 * This software is the confidential and proprietary information 
 * of Green Mountain Analytics. You shall not disclose such Confidential   
 * Information and shall use it only in accordance with the terms 
 * of the license agreement you entered into with Green Mountain Analytics.
 */

package ro.springsoft.exceptions;

import java.io.Serializable;


/**
 * @author Alin Alexandru
 * @version 1.0
 * @date May 22, 2006
 */
public class DuplicateNameException extends PersistentException implements Serializable {

    /**
     * Serial UID
     */
    private static final long serialVersionUID = 8108325451559361377L;
    private final String message;

    public DuplicateNameException(String duplicateName) {
        message = "Entity with this name: " + duplicateName
                + " has already been registered in the system! ";
    }

    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return message;
    }
}
