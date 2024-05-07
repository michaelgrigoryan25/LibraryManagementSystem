package am.aua.library.ui.views;

import am.aua.library.Constants;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.models.Admin;
import am.aua.library.repositories.AdminRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;
import am.aua.library.ui.components.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class RegistrationView extends AbstractPage {
    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField adminRegistrationKeyField;

    private AdminRepositoryImpl adminRepository;

    public RegistrationView() {
        super("Admin Registration");
    }

    @Override
    public void setup() {
        this.setLayout(new GridLayout(3, 1));
        this.adminRepository = new AdminRepositoryImpl();
    }

    @Override
    public void addComponents() {
        this.add(createTextPanel());
        this.add(createInputPanel());
        this.add(createButtonsPanel());
    }

    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel text = new Text("Registration", Text.Size.LG);
        panel.add(text);
        return panel;
    }

    private JPanel createInputPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(6, 0);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(0, 50, 0, 50));

        JLabel fullNameLabel = new Text("Full Name:", Text.Size.SM);
        JLabel usernameLabel = new Text("Username:", Text.Size.SM);
        JLabel passwordLabel = new Text("Password:", Text.Size.SM);

        fullNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        panel.add(fullNameLabel);
        panel.add(fullNameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        JLabel adminRegistrationKeyLabel = new JLabel("Admin Registration Key: ");
        adminRegistrationKeyField = new JPasswordField();

        panel.add(adminRegistrationKeyLabel);
        panel.add(adminRegistrationKeyField);

        rootPanel.add(panel, BorderLayout.CENTER);
        return rootPanel;
    }

    private JPanel createButtonsPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(createBackButton());
        panel.add(createRegisterButton());

        rootPanel.add(panel);
        return rootPanel;
    }

    private JButton createBackButton() {
        JButton button = new JButton("Back to Main Menu");
        button.addActionListener(e -> {
            dispose();
            new MainView();
        });

        return button;
    }

    private JButton createRegisterButton() {
        JButton button = new JButton("Register");
        button.addActionListener(e -> {
            String fullName = this.fullNameField.getText();
            if (fullName.isBlank() || fullName.isEmpty()) {
                JOptionPane.showMessageDialog(RegistrationView.this, "Please enter a valid full name");
                return;
            }

            String username = this.usernameField.getText().toLowerCase();
            if (!Helpers.isValidUsername(username)) {
                JOptionPane.showMessageDialog(RegistrationView.this, "Username cannot contain spaces, be empty, or contain characters other than ASCII");
                return;
            }

            String password = new String(this.passwordField.getPassword());
            if (!Helpers.isValidPassword(password)) {
                JOptionPane.showMessageDialog(RegistrationView.this, "Password must contain at least 8 characters");
                return;
            }

            if (String.copyValueOf(adminRegistrationKeyField.getPassword()).equals(Constants.ADMIN_REGISTRATION_KEY)) {
                Admin admin = new Admin(fullName, username, password);
                try {
                    this.adminRepository.add(admin);
                    JOptionPane.showMessageDialog(RegistrationView.this, "Registered successfully!");
                    dispose();
                    new AdminView();
                } catch (DatabaseException ex) {
                    if (ex instanceof DuplicateRecordException) {
                        JOptionPane.showMessageDialog(RegistrationView.this, "Professor with username `" + username + "` already exists. Choose a different username.");
                        return;
                    }

                    System.err.print(getClass().getCanonicalName());
                    System.err.print(": " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(RegistrationView.this, "Professor registration key is invalid");
            }

        });

        return button;
    }
}
