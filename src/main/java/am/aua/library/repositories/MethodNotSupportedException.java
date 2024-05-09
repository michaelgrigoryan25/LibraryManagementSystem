package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;

public final class MethodNotSupportedException extends DatabaseException {
    public MethodNotSupportedException(String methodName) {
        super("method `" + methodName + "` is not supported");
    }
}
