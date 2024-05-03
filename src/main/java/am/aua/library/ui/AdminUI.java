package am.aua.library.ui;

import am.aua.library.ui.components.BookListPanel;
import am.aua.library.ui.components.BookSearchPanel;
import am.aua.library.ui.core.Page;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminUI extends Page {
    private final Long id;
    private JList<String> jList;

    public AdminUI(Long id) {
        super("Admin View");
        this.id = id;
    }

    @Override
    protected void setupPage() {
        setLayout(new GridLayout(2, 1));
        add(new BookSearchPanel());
        add(new BookListPanel());
    }

    @Override
    protected void setupComponents() {
    }
}
