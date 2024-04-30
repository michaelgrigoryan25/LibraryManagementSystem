package am.aua.library.ui;

import am.aua.library.ui.core.LibraryManagementSystemUI;
import am.aua.library.ui.core.Page;
import am.aua.library.ui.core.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginUI extends Page {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        super("Login");
    }

    @Override
    public void setupPage() {
        this.setLayout(new GridLayout(3, 1));
    }

    @Override
    public void setupComponents() {
        this.add(createTextPanel());
        this.add(createInputPanel());
        this.add(createButtonsPanel());
    }

    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel text = new Text("Login", Text.Size.LG);
        panel.add(text);
        return panel;
    }

    private JPanel createInputPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(new EmptyBorder(0, 50, 0, 50));

        JLabel usernameLabel = new Text("Username:");
        JLabel passwordLabel = new Text("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        rootPanel.add(panel, BorderLayout.CENTER);
        return rootPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(createBackButton());
        panel.add(createLoginButton());

        rootPanel.add(panel);
        return rootPanel;
    }

    private JButton createBackButton() {
        JButton button = new JButton("Back to Main Menu");
        button.addActionListener(e -> {
            dispose();
            new LibraryManagementSystemUI();
        });

        return button;
    }

    private JButton createLoginButton() {
        JButton register = new JButton("Login");
        register.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Dummy check, replace with actual authentication logic
            if (username.equals("user") && password.equals("password")) {
                dispose(); // Close the login window
                // TODO: Redirect the user to the database page
            } else {
                JOptionPane.showMessageDialog(LoginUI.this, "Invalid username or password");
            }
        });
        return register;
    }
}
