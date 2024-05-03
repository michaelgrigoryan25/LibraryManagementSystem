package am.aua.library.ui.components;

import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepositoryImpl;

import javax.swing.*;

public class BookListPanel extends JPanel {
    private JList<Book> bookJList;
    private DefaultListModel<Book> bookListModel;
    private final BookRepositoryImpl bookRepository;

    public BookListPanel() {
        super();
        this.bookRepository = new BookRepositoryImpl();
        this.bookListModel = new DefaultListModel<>();
        this.bookListModel.addAll(this.bookRepository.findAll());
        this.bookJList = new JList<>(bookListModel);
        this.add(bookJList);
    }
}
