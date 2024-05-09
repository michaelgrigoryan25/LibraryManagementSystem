package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Leaser;

import java.util.ArrayList;
import java.util.List;

public class LeaserRepositoryImpl implements LeaserRepository {
    private final Database database = Database.getInstance();

    @Override
    public Leaser get(Long id) {
        for (Leaser user : this.database.getLeasers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public Leaser getUnsafe(Long id) {
        for (Leaser user : this.database.getLeasersUnsafe()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public Leaser findByUsername(String username) throws MethodNotSupportedException {
        throw new MethodNotSupportedException("findByUsername is not supported for type `Leaser`");
    }

    @Override
    public List<Leaser> findByNameContaining(String contains) {
        ArrayList<Leaser> leasers = new ArrayList<>();
        for (Leaser user : this.database.getLeasers()) {
            if (user.getFullName().contains(contains)) {
                leasers.add(user);
            }
        }

        return leasers;
    }

    @Override
    public List<Leaser> findAll() {
        return database.getLeasers();
    }

    @Override
    public void add(Leaser element) throws DatabaseException {
        this.database.getLeasersUnsafe().add(element);
        this.database.persist();
    }

    @Override
    public void update(Leaser element) throws DatabaseException {
        int index = this.database.getLeasersUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user with id `" + element.getId() + "` since it doesn't exist");
        }

        Leaser prev = this.database.getLeasersUnsafe().get(index);
        element.setId(prev.getId());

        this.database.getLeasersUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(Leaser element) throws DatabaseException {
        this.database.getLeasersUnsafe().remove(element);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }
}
