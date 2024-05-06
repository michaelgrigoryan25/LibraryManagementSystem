package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    private final Database database = Database.getInstance();

    @Override
    public Admin findByUsername(String username) {
        for (Admin admin : this.database.getAdmins()) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }

        return null;
    }

    @Override
    public List<Admin> findByNameContaining(String contains) {
        ArrayList<Admin> admins = new ArrayList<>();
        for (Admin user : this.database.getAdmins()) {
            if (user.getFullName().contains(contains)) {
                admins.add(user);
            }
        }

        return admins;
    }

    @Override
    public Admin get(Long id) {
        for (Admin admin : this.database.getAdmins()) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }

        return null;
    }

    @Override
    public Admin getUnsafe(Long id) {
        for (Admin admin : this.database.getAdminsUnsafe()) {
            if (admin.getId().equals(id)) {
                return admin;
            }
        }

        return null;
    }

    @Override
    public List<Admin> findAll() {
        return this.database.getAdmins();
    }

    @Override
    public void add(Admin element) throws DatabaseException {
        if (findByUsername(element.getUsername()) != null) {
            throw new DuplicateRecordException("cannot create user `" + element.getUsername() + "` since it already exists");
        }

        this.database.getAdminsUnsafe().add(element);
        this.database.persist();
    }

    @Override
    public void update(Admin element) throws DatabaseException {
        int index = this.database.getAdminsUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user `" + element.getId() + "` since it doesn't exist");
        }

        Admin prev = this.database.getAdminsUnsafe().get(index);
        // This ensures that the IDs and the usernames are not modified
        element.setId(prev.getId());

        this.database.getAdminsUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(Admin element) throws DatabaseException {
        this.database.getAdminsUnsafe().remove(element);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }
}
