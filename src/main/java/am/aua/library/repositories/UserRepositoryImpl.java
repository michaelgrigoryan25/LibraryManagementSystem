package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final Database database = Database.getInstance();

    @Override
    public User get(Long id) {
        for (User user : this.database.getUsers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User getUnsafe(Long id) {
        for (User user : this.database.getUsersUnsafe()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : this.database.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User findByUsernameUnsafe(String username) {
        for (User user : this.database.getUsersUnsafe()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<User> findAll() {
        return database.getUsers();
    }

    @Override
    public void add(User element) throws DatabaseException {
        if (this.exists(element.getId())) {
            throw new DuplicateRecordException("user with id `" + element.getId() + "` already exists");
        }

        this.database.getUsers().add(element);
        this.database.persist();
    }

    @Override
    public void update(User element) throws DatabaseException {
        int index = this.database.getUsersUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user with id `" + element.getId() + "` since it doesn't exist");
        }

        this.database.getUsersUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(User element) throws DatabaseException {
        this.database.getUsersUnsafe().remove(element);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }
}
