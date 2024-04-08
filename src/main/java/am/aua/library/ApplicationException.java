package am.aua.library;

/**
 * The ApplicationException class represents an exception specific to the application.
 * It extends the standard Java Exception class.
 */
public class ApplicationException extends Exception {
    /**
     * Constructs a new ApplicationException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ApplicationException(String message) {
        super(message);
    }
}
