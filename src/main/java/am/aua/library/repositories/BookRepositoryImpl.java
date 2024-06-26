package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Book;
import am.aua.library.models.Leaser;
import am.aua.library.models.User;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the {@link BookRepository} interface.
 */
public class BookRepositoryImpl implements BookRepository {
    private final Database database = Database.getInstance();

    @Override
    public boolean rentById(Long id, Long userId) throws DatabaseException {
        final LeaserRepositoryImpl leaserRepository = new LeaserRepositoryImpl();

        Book book = getUnsafe(id);
        Leaser user = leaserRepository.getUnsafe(userId);
        if (book != null && user != null) {
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.MONTH, 1);
            Leaser.Lease lease = new Leaser.Lease(book.getId(), new Date(), endDate.getTime());

            user.addLease(lease);
            book.addRenter(user.getId());
            this.database.persist();
            return true;
        }

        // indicates that the book was not rented
        return false;
    }

    @Override
    public boolean giveBackById(Long id, Long userId) throws DatabaseException {
        final LeaserRepositoryImpl studentRepository = new LeaserRepositoryImpl();
        final AdminRepositoryImpl professorRepository = new AdminRepositoryImpl();

        Book book = getUnsafe(id);
        User user = studentRepository.get(userId);
        if (user == null) {
            user = professorRepository.get(userId);
        }

        if (book != null && user != null) {
            book.returnBook(user.getId());
            this.database.persist();
            return true;
        }

        return false;
    }

    @Override
    public Book get(Long id) {
        for (Book book : database.getBooks()) {
            if (book.getId().equals(id)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public Book getUnsafe(Long id) {
        for (Book book : database.getBooksUnsafe()) {
            if (book.getId().equals(id)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public List<Book> findAll() {
        return database.getBooks();
    }

    @Override
    public void add(Book element) throws DatabaseException {
        if (exists(element.getId())) {
            throw new DuplicateRecordException("book with id `" + element.getId() + "` already exists");
        }

        this.database.getBooksUnsafe().add(element);
        this.database.persist();
    }

    @Override
    public void update(Book element) throws DatabaseException {
        int index = this.database.getBooksUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user `" + element.getId() + "` since it doesn't exist");
        }

        Book prev = this.database.getBooksUnsafe().get(index);
        // This ensures that the IDs and the usernames are not modified
        element.setId(prev.getId());

        this.database.getBooksUnsafe().set(index, element);
        this.database.persist();
    }

    @Override
    public void remove(Book element) throws DatabaseException {
        this.database.getBooksUnsafe().remove(element);
        this.database.persist();
    }

    @Override
    public boolean exists(Long id) {
        return get(id) != null;
    }
}
