package am.aua.library.repositories;

import am.aua.library.models.exceptions.MethodNotSupportedException;
import am.aua.library.models.User;

import java.util.List;

public interface UserRepository<T extends User> extends Repository<T> {
    T findByUsername(String username) throws MethodNotSupportedException;

    List<T> findByNameContaining(String contains);
}
