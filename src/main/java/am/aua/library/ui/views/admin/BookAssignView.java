package am.aua.library.ui.views.admin;

import am.aua.library.database.DatabaseException;
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
    private Book book;
    private List<Leaser> leasers;
    private BookRepository bookRepository;
    private LeaserRepository leaserRepository;

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
        JPanel root = new JPanel(new GridLayout(2, 0));
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1));
        panel.setPreferredSize(new Dimension(700, 500));

        JTextField idField = new JTextField(String.valueOf(book.getId()));
        idField.setEditable(false);
        panel.add(new JLabel("ID"));
        panel.add(idField);

        JTextField titleField = new JTextField(book.getTitle());
        titleField.setEditable(false);
        panel.add(new JLabel("Title"));
        panel.add(titleField);

        JTextField subtitleField = new JTextField(book.getSubtitle());
        subtitleField.setEditable(false);
        panel.add(new JLabel("Subtitle"));
        panel.add(subtitleField);

        JTextField yearField = new JTextField(String.valueOf(book.getYear()));
        yearField.setEditable(false);
        panel.add(new JLabel("Year"));
        panel.add(yearField);

        JTextField languageField = new JTextField(book.getLanguage());
        languageField.setEditable(false);
        panel.add(new JLabel("Language"));
        panel.add(languageField);

        JTextField availableCopiesField = new JTextField(String.valueOf(book.getCopies()));
        availableCopiesField.setEditable(false);
        panel.add(new JLabel("Available Copies"));
        panel.add(availableCopiesField);

        JTextField pagesField = new JTextField(String.valueOf(book.getPages()));
        pagesField.setEditable(false);
        panel.add(new JLabel("Pages"));
        panel.add(pagesField);

        DefaultComboBoxModel<Leaser> model = new DefaultComboBoxModel<>(leasers.toArray(Leaser[]::new));
        JComboBox<Leaser> leasersField = new JComboBox<>(model);
        panel.add(new JLabel("Leaser"));
        panel.add(leasersField);

        root.add(panel);
        JOptionPane.showMessageDialog(parent, panel);
        if (leasersField.getSelectedItem() != null) {
            Leaser leaser = leaserRepository.get(((Leaser) leasersField.getSelectedItem()).getId());
            try {
                this.bookRepository.rentById(book.getId(), leaser.getId());
            } catch (DatabaseException ex) {
                throw new RuntimeException(ex);
            }
        }
        new BookTableView();
    }
}
