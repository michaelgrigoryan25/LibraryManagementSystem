package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;

/**
 * This exception is thrown when a method is not supported.
 */
public final class MethodNotSupportedException extends DatabaseException {

    /**
     * Constructs a new MethodNotSupportedException with the specified method name.
     *
     * @param methodName The name of the method that is not supported.
     */
    public MethodNotSupportedException(String methodName) {
        super("method `" + methodName + "` is not supported");
    }
}
