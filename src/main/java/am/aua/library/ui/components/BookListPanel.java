package am.aua.library.ui.components;

import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepositoryImpl;

import java.util.List;

public class BookListPanel extends AbstractListPanel<Book> {
    private final BookRepositoryImpl bookRepository;

    public BookListPanel() {
        super();
        this.bookRepository = new BookRepositoryImpl();
    }

    @Override
    public void onSearch(String query) {
        List<Book> books = this.bookRepository.findByTitle(query);
        this.getListModel().clear();
        this.getListModel().addAll(books);
    }
}
