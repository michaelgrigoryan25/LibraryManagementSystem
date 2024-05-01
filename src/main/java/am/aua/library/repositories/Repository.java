package am.aua.library.repositories;

import am.aua.library.database.DatabaseException;

import java.util.List;

public interface Repository<T> {
    T get(Long id);

    T getUnsafe(Long id);

    List<T> findAll();

    void add(T element) throws DatabaseException;

    void update(T element) throws DatabaseException;

    void remove(T element) throws DatabaseException;

    boolean exists(Long id);
}
