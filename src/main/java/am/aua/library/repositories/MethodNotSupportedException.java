package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;

public class MethodNotSupportedException extends DatabaseException {
    /**
     * Constructs a new ApplicationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MethodNotSupportedException(String methodName) {
        super("method `" + methodName + "` is not supported");
    }
}
