package am.aua.library.ui.components;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    public interface SearchListener {
        void onSearch(String query);
    }

    public SearchPanel(SearchListener listener) {
        super();

        this.setLayout(new FlowLayout());
        JTextField searchField = new JTextField();
        this.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> listener.onSearch(searchField.getText()));
        this.add(searchButton);
    }
}
