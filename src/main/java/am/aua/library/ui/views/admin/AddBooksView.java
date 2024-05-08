package am.aua.library.ui.views.admin;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepository;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AddBooksView extends AbstractPage {

    private JTextField idField;
    private JTextField titleField;
    private JTextField subtitleField;
    private JTextField yearField;
    private JTextField copiesField;
    private JTextField pagesField;
    private JTextField languageField;
    private JTextField publisherField;
    private JTextField rentersField;
    private JTextField authorsField;
    private JTextField categoriesField;

    private JPanel panel;

    private BookRepository bookRepository;

    public AddBooksView() {
        super("Add Books");
    }

    @Override
    protected void setup() {
        this.setLayout(new GridLayout(12, 1));
        bookRepository = new BookRepositoryImpl();
    }

    @Override
    protected void addComponents() {
        //createPanel();
        addTextFields();
        addSubmitButton();
    }

    private void createPanel() {
        panel = new JPanel();
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
        addRentersField();
        addAuthorsField();
        addCategoriesField();
    }

    private void addIdField() {
        idField = new JTextField();
        this.add(new JLabel("id"));
        this.add(idField);
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

    private void addRentersField() {
        rentersField = new JTextField();
        this.add(new JLabel("renters"));
        this.add(rentersField);
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
                bookRepository.add(new Book(
                        Long.valueOf(idField.getText()),
                        titleField.getText(),
                        subtitleField.getText(),
                        Integer.parseInt(yearField.getText()),
                        Integer.parseInt(copiesField.getText()),
                        Integer.parseInt(pagesField.getText()),
                        languageField.getText(),
                        publisherField.getText(),
                        Arrays.stream(rentersField.getText().split(";")).map(Long::valueOf).toList(),
                        Arrays.stream(authorsField.getText().split(";")).toList(),
                        Arrays.stream(categoriesField.getText().split(";")).toList()
                ));
            } catch (DatabaseException exception) {
                System.out.println("Could not add a book"); // TODO: Integrate error messages into this view page
            }
        });
        this.add(submit);
    }
}
