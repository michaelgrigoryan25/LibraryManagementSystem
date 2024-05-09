package am.aua.library.ui.views.admin;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepository;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public final class AddBookView extends AbstractPage {
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
    private JPanel inputsPanel;

    private BookRepository bookRepository;

    public AddBookView() {
        super("Add Books");
    }

    @Override
    protected void setup() {
        this.setLayout(new BorderLayout());
        bookRepository = new BookRepositoryImpl();
        this.newBookId = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        this.inputsPanel = new JPanel(new GridLayout(10, 0));
        this.inputsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        this.add(inputsPanel, BorderLayout.NORTH);
    }

    private void addIdField() {
        inputsPanel.add(new JLabel("id"));
        inputsPanel.add(new JLabel(this.newBookId.toString()));
    }

    private void addTitleField() {
        titleField = new JTextField();
        inputsPanel.add(new JLabel("title"));
        inputsPanel.add(titleField);
    }

    private void addSubtitleField() {
        subtitleField = new JTextField();
        inputsPanel.add(new JLabel("subtitle"));
        inputsPanel.add(subtitleField);
    }

    private void addYearField() {
        yearField = new JTextField();
        inputsPanel.add(new JLabel("year"));
        inputsPanel.add(yearField);
    }

    private void addCopiesField() {
        copiesField = new JTextField();
        inputsPanel.add(new JLabel("copies"));
        inputsPanel.add(copiesField);
    }

    private void addPagesField() {
        pagesField = new JTextField();
        inputsPanel.add(new JLabel("pages"));
        inputsPanel.add(pagesField);
    }

    private void addLanguageField() {
        languageField = new JTextField();
        inputsPanel.add(new JLabel("language"));
        inputsPanel.add(languageField);
    }

    private void addPublisherField() {
        publisherField = new JTextField();
        inputsPanel.add(new JLabel("publisher"));
        inputsPanel.add(publisherField);
    }

    private void addAuthorsField() {
        authorsField = new JTextField();
        inputsPanel.add(new JLabel("authors"));
        inputsPanel.add(authorsField);
    }

    private void addCategoriesField() {
        categoriesField = new JTextField();
        inputsPanel.add(new JLabel("categories"));
        inputsPanel.add(categoriesField);
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
                        Arrays.asList(authorsField.getText().split(";")),
                        Arrays.asList(categoriesField.getText().split(";"))
                ));

                JOptionPane.showMessageDialog(this, "Book added successfully!");
                this.dispose();
            } catch (DatabaseException exception) {
                JOptionPane.showMessageDialog(this, exception);
            }
        });

        this.add(submit, BorderLayout.SOUTH);
    }
}
