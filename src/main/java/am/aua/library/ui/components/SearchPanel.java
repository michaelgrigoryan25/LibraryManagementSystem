package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchPanel extends JPanel {
    public interface SearchListener {
        void onSearch(String query);
    }

    public SearchPanel(SearchListener listener) {
        super();

        this.setLayout(new BorderLayout());
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, 30));
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> listener.onSearch(searchField.getText()));
        searchButton.setPreferredSize(new Dimension(100, searchField.getPreferredSize().height));
        searchField.setForeground(Color.GRAY);

        this.add(searchField, BorderLayout.CENTER);
        this.add(searchButton, BorderLayout.EAST);
    }
}
