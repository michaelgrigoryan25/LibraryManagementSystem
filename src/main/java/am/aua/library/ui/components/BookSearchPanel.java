package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public class BookSearchPanel extends JPanel {
    private final JTextField searchField;
    private final JButton searchButton;

    public BookSearchPanel() {
        super();
        this.setLayout(new GridLayout(1, 2));
        this.searchField = new JTextField();
        this.searchField.setMinimumSize(this.getMaximumSize());
        this.searchField.setToolTipText("Hello, World");
        this.add(this.searchField);

        this.searchButton = new JButton("Search");
        this.add(searchButton);
    }
}
