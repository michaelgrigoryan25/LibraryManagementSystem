package am.aua.library.models.exceptions;

import am.aua.library.ApplicationException;

public class MethodNotSupportedException extends ApplicationException {
    /**
     * Constructs a new ApplicationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public MethodNotSupportedException(String message) {
        super(message);
    }
}
