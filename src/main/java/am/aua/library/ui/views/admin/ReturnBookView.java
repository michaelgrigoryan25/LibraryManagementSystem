package am.aua.library.ui.views.admin;

import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ReturnBookView extends AbstractPage {
    private JTextField inputField;
    private JButton renderButton;
    private JComboBox<String> comboBox;

    public ReturnBookView() {
        super("Dynamic JComboBox");
    }

    @Override
    protected void setup() {
        // Components will be added in the addComponents method
    }

    @Override
    protected void addComponents() {
        inputField = new JTextField(20);
        renderButton = new JButton("Render ComboBox");

        renderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renderComboBox();
            }
        });

        // Create the combo box with initial empty options
        comboBox = new JComboBox<>();

        // Set up the layout
        setLayout(new BorderLayout());

        // Panel for text input field and button
        JPanel topPanel = new JPanel();
        topPanel.add(inputField);
        topPanel.add(renderButton);
        add(topPanel, BorderLayout.NORTH);

        // Panel for the combo box
        JPanel comboBoxPanel = new JPanel(new BorderLayout());
        comboBoxPanel.add(comboBox, BorderLayout.CENTER);
        add(comboBoxPanel, BorderLayout.CENTER);
    }

    private void renderComboBox() {
        // Get the input text
        String inputText = inputField.getText();

        // Populate the combo box with options based on the input text
        String[] options = generateOptions(inputText);
        comboBox.setModel(new DefaultComboBoxModel<>(options));
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
