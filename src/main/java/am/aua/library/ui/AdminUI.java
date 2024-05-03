package am.aua.library.ui;

import am.aua.library.ui.core.Page;

import javax.swing.*;
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
        add(jList);
    }

    @Override
    protected void setupComponents() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(List.of("book 1"));
        this.jList = new JList<>(listModel);
    }
}
