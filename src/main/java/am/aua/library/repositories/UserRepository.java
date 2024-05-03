package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Professor;
import am.aua.library.models.User;

import java.util.List;

public interface UserRepository<T extends User> extends Repository<T> {
    T findByUsername(String username);
}
