package am.aua.library.ui.views.admin;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepository;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public final class AddBooksView extends AbstractPage {
    private Long newBookId;

    private JTextField titleField;
    private JTextField subtitleField;
    private JTextField yearField;
    private JTextField copiesField;
    private JTextField pagesField;
    private JTextField languageField;
    private JTextField publisherField;
    private JTextField authorsField;
    private JTextField categoriesField;

    private BookRepository bookRepository;

    public AddBooksView() {
        super("Add Books");
    }

    @Override
    protected void setup() {
        this.setLayout(new GridLayout(12, 0));
        bookRepository = new BookRepositoryImpl();
        this.newBookId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    @Override
    protected void addComponents() {
        addTextFields();
        addSubmitButton();
    }

    private void addTextFields() {
        addIdField();
        addTitleField();
        addSubtitleField();
        addYearField();
        addCopiesField();
        addPagesField();
        addLanguageField();
        addPublisherField();
        addAuthorsField();
        addCategoriesField();
    }

    private void addIdField() {
        this.add(new JLabel("id"));
        this.add(new JLabel(this.newBookId.toString()));
    }

    private void addTitleField() {
        titleField = new JTextField();
        this.add(new JLabel("title"));
        this.add(titleField);
    }

    private void addSubtitleField() {
        subtitleField = new JTextField();
        this.add(new JLabel("subtitle"));
        this.add(subtitleField);
    }

    private void addYearField() {
        yearField = new JTextField();
        this.add(new JLabel("year"));
        this.add(yearField);
    }

    private void addCopiesField() {
        copiesField = new JTextField();
        this.add(new JLabel("copies"));
        this.add(copiesField);
    }

    private void addPagesField() {
        pagesField = new JTextField();
        this.add(new JLabel("pages"));
        this.add(pagesField);
    }

    private void addLanguageField() {
        languageField = new JTextField();
        this.add(new JLabel("language"));
        this.add(languageField);
    }

    private void addPublisherField() {
        publisherField = new JTextField();
        this.add(new JLabel("publisher"));
        this.add(publisherField);
    }

    private void addAuthorsField() {
        authorsField = new JTextField();
        this.add(new JLabel("authors"));
        this.add(authorsField);
    }

    private void addCategoriesField() {
        categoriesField = new JTextField();
        this.add(new JLabel("categories"));
        this.add(categoriesField);
    }

    private void addSubmitButton() {
        JButton submit = new JButton("Add Book");
        submit.addActionListener(e -> {
            try {
                String title = titleField.getText();
                if (title == null || title.isEmpty() || title.isBlank()) {
                    JOptionPane.showMessageDialog(this, "Title cannot be empty!");
                    return;
                }

                String year = yearField.getText();
                if (year == null || year.isBlank() || year.isEmpty() || !Helpers.isNumeric(year)) {
                    JOptionPane.showMessageDialog(this, "The year must be a valid number!");
                    return;
                }

                String copies = copiesField.getText();
                if (copies == null || copies.isEmpty() ||
                        copies.isBlank() || !Helpers.isNumeric(copies) || !(Integer.parseInt(copies) > 0)) {
                    JOptionPane.showMessageDialog(this, "The number of copies must be a valid number");
                    return;
                }

                bookRepository.add(new Book(
                        this.newBookId,
                        titleField.getText(),
                        subtitleField.getText(),
                        Integer.parseInt(year),
                        Integer.parseInt(copies),
                        Integer.parseInt(pagesField.getText()),
                        languageField.getText(),
                        publisherField.getText(),
                        new ArrayList<>(),
                        Arrays.stream(authorsField.getText().split(";")).toList(),
                        Arrays.stream(categoriesField.getText().split(";")).toList()
                ));

                JOptionPane.showMessageDialog(this, "Book added successfully!");
                this.dispose();
            } catch (DatabaseException exception) {
                JOptionPane.showMessageDialog(this, exception);
            }
        });

        this.add(submit);
    }
}
