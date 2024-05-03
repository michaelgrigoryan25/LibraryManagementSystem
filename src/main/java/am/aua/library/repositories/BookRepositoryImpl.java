package am.aua.library.repositories;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.database.RecordNotFoundException;
import am.aua.library.models.Book;
import am.aua.library.models.Professor;
import am.aua.library.models.User;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private final Database database = Database.getInstance();

    @Override
    public ArrayList<Book> findByTitle(String title) {
        ArrayList<Book> filtered = new ArrayList<>();
        for (Book book : database.getBooks()) {
            if (book.getTitle().contains(title)) {
                filtered.add(book);
            }
        }

        return filtered;
    }

    @Override
    public boolean rentById(Long id, Long userId) throws DatabaseException {
        final StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        final ProfessorRepositoryImpl professorRepository = new ProfessorRepositoryImpl();

        Book book = getUnsafe(id);
        User user = studentRepository.get(userId);
        if (user == null) user = professorRepository.get(userId);

        if (book != null && user != null) {
            book.addRenter(user.getId());
            this.database.persist();
            return true;
        }

        // indicates that the book was not rented
        return false;
    }

    @Override
    public boolean giveBackById(Long id, Long userId) throws DatabaseException {
        final StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        final ProfessorRepositoryImpl professorRepository = new ProfessorRepositoryImpl();

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
            if (book.getIsbn().equals(id)) {
                return book;
            }
        }

        return null;
    }

    @Override
    public Book getUnsafe(Long id) {
        for (Book book : database.getBooksUnsafe()) {
            if (book.getIsbn().equals(id)) {
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
        if (exists(element.getIsbn())) {
            throw new DuplicateRecordException("book with id `" + element.getIsbn() + "` already exists");
        }

        this.database.getBooksUnsafe().add(element);
        this.database.persist();
    }

    @Override
    public void update(Book element) throws DatabaseException {
        int index = this.database.getBooksUnsafe().indexOf(element);
        if (index < 0) {
            throw new RecordNotFoundException("cannot update user `" + element.getIsbn() + "` since it doesn't exist");
        }

        Book prev = this.database.getBooksUnsafe().get(index);
        // This ensures that the IDs and the usernames are not modified
        element.setIsbn(prev.getIsbn());

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
