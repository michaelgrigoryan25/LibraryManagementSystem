package am.aua.library.repositories;

import am.aua.library.models.User;

public interface UserRepository<T extends User> extends Repository<T> {
    T findByUsername(String username);
}
