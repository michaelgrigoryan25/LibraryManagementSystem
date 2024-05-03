package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Professor;
import am.aua.library.models.User;

import java.util.List;

public interface UserRepository<T extends User> extends Repository<T> {
    T findByUsername(String username);

    T get(Long id);

    T getUnsafe(Long id);

    List<T> findAll();

    void add(T user) throws DatabaseException;

    void update(T user) throws DatabaseException;

    void remove(T user) throws DatabaseException;

    boolean exists(Long id);
}
