package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Book;
import am.aua.library.models.Leaser;

import java.util.List;

/**
 * Implementation of the LeaserRepository interface for managing leaser data.
 */
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
        int index = getIndex(database.getLeasersUnsafe(), element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user with id `" + element.getId() + "` since it doesn't exist");
        }

        Leaser old = database.getLeasersUnsafe().get(index);
        element.setId(old.getId());
        // Preventing overridden leases
        element.setLeases(old.getLeases());

        this.database.getLeasersUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(Leaser element) throws DatabaseException {
        final BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        for (Book book : bookRepository.findAll()) {
            if (book.getRenters().contains(element.getId())) {
                book.getRentersUnsafe().remove(element.getId());
                book.incrementCopies();
            }
        }

        int index = getIndex(this.database.getLeasers(), element);
        this.database.getLeasersUnsafe().remove(index);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }

    private int getIndex(List<Leaser> leasers, Leaser leaser) {
        for (int i = 0; i < leasers.size(); i++) {
            if (leasers.get(i).equals(leaser)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Leaser.Lease> getLeases(Long id) {
        Leaser leaser = get(id);
        return leaser.getLeases();
    }
}
