package ro.springsoft.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.mongodb.MongoException;

/**
 * Created by Miro on 8/20/2014.
 */
public class MongoPersistenceException extends PersistentException {
    private final MongoException ex;

    public MongoPersistenceException(MongoException ex) {
        this.ex = ex;
    }

    @Override
    public String getMessage() {
        return ex.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return ex.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return ex.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return ex.initCause(cause);
    }

    @Override
    public String toString() {
        return ex.toString();
    }

    @Override
    public void printStackTrace() {
        ex.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        ex.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        ex.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return ex.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return ex.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        ex.setStackTrace(stackTrace);
    }
}
