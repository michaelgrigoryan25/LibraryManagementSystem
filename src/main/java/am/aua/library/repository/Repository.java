package am.aua.library.repository;

import java.util.List;

public interface Repository<T> {

    T save(T t);

    void delete(T t);

    void delete(long id);

    T update(T before, T after);

    T update(long id, T after);

    List<T> findAll();
}
