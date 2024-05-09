package am.aua.library.database;

public final class RecordNotFoundException extends DatabaseException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
