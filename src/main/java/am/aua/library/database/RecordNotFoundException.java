package am.aua.library.database;

public class RecordNotFoundException extends DatabaseException {
    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Exception e) {
        super(e);
    }
}
