package am.aua.library.ui.views.admin;

import am.aua.library.models.Book;
import am.aua.library.models.Leaser;
import am.aua.library.repositories.BookRepository;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.LeaserRepository;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BookAssignView {
    private BookRepository bookRepository;
    private LeaserRepository leaserRepository;

    private Book book;

    private JTextField idField;
    private JTextField titleField;
    private JTextField subtitleField;
    private JTextField yearField;
    private JTextField languageField;
    private JTextField availableCopiesField;
    private JTextField pagesField;
    private JComboBox<Object> leasersField;

    private List<Leaser> leasers;

    public BookAssignView(AbstractPage parent, Book book) {
        setup(book);
        addComponents(parent);
    }

    public void setup(Book book) {
        this.leaserRepository = new LeaserRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
        this.book = book;
        this.leasers = leaserRepository.findAll();
    }

    public void addComponents(AbstractPage parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));
        panel.setPreferredSize(new Dimension(500, 500));

        idField = new JTextField(String.valueOf(book.getId()));
        idField.setEditable(false);
        panel.add(new JLabel("ID"));
        panel.add(idField);

        titleField = new JTextField(book.getTitle());
        titleField.setEditable(false);
        panel.add(new JLabel("Title"));
        panel.add(titleField);

        subtitleField = new JTextField(book.getSubtitle());
        subtitleField.setEditable(false);
        panel.add(new JLabel("Subtitle"));
        panel.add(subtitleField);

        yearField = new JTextField(String.valueOf(book.getYear()));
        yearField.setEditable(false);
        panel.add(new JLabel("Year"));
        panel.add(yearField);

        languageField = new JTextField(book.getLanguage());
        languageField.setEditable(false);
        panel.add(new JLabel("Language"));
        panel.add(languageField);

        availableCopiesField = new JTextField(String.valueOf(book.getCopies()));
        availableCopiesField.setEditable(false);
        panel.add(new JLabel("Available Copies"));
        panel.add(availableCopiesField);

        pagesField = new JTextField(String.valueOf(book.getPages()));
        pagesField.setEditable(false);
        panel.add(new JLabel("Pages"));
        panel.add(pagesField);

        leasersField = new JComboBox<>(leasers.stream().map(Leaser::getPassword).toArray());
        panel.add(new JLabel("Leaser"));
        panel.add(leasersField);

        JButton assignButton = new JButton("Assign");
        assignButton.addActionListener(e -> {
            Leaser leaser = leaserRepository.getByPassword(leasersField.getSelectedItem().toString());
            this.book.addRenter(leaser.getId());
        });

        panel.add(assignButton);
        JOptionPane.showMessageDialog(parent, panel);

        new BookTableView();
    }
}
