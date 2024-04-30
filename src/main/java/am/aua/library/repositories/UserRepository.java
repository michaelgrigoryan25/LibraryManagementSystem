package am.aua.library.repositories;

import am.aua.library.models.User;

public interface UserRepository extends Repository<User> {
    User findByUsername(String username);

    User findByUsernameUnsafe(String username);
}
