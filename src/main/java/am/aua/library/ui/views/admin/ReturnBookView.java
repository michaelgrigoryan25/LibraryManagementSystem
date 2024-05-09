package am.aua.library.ui.views.admin;

import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;

public final class ReturnBookView extends AbstractPage {
    public ReturnBookView() {
        super("Dynamic JComboBox");
    }

    @Override
    protected void setup() {
        setLayout(new BorderLayout());
    }

    @Override
    protected void addComponents() {
        JTextField userId = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton returnBookButton = new JButton("Return Book");
        panel.add(new JLabel("Leaser ID"));
        panel.add(userId);
        panel.add(new JComboBox<>());
        panel.add(returnBookButton);
        add(panel, BorderLayout.NORTH);
    }

    private String[] generateOptions(String input) {
        // Sample logic to generate options based on the input
        // Here, we simply add the input text with numbers from 1 to 5
        String[] options = new String[5];
        for (int i = 0; i < 5; i++) {
            options[i] = input + " " + (i + 1);
        }
        return options;
    }
}
