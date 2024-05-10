package am.aua.library.ui.views;

import am.aua.library.Constants;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.models.Admin;
import am.aua.library.repositories.AdminRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;
import am.aua.library.ui.components.Text;
import am.aua.library.ui.views.admin.AdminView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * View for admin registration.
 */
public final class RegistrationView extends AbstractPage {
    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField adminRegistrationKeyField;

    private AdminRepositoryImpl adminRepository;

    /**
     * Constructs a new RegistrationView.
     */
    public RegistrationView() {
        super("Admin Registration");
    }

    /**
     * Sets up the registration view.
     */
    @Override
    public void setup() {
        this.setLayout(new GridLayout(3, 1));
        this.adminRepository = new AdminRepositoryImpl();
    }

    /**
     * Adds components to the registration view.
     */
    @Override
    public void addComponents() {
        this.add(createTextPanel());
        this.add(createInputPanel());
        this.add(createButtonsPanel());
    }

    /**
     * Creates a panel for displaying text.
     *
     * @return The created panel.
     */
    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel text = new Text("Registration", Text.Size.LG);
        panel.add(text);
        return panel;
    }

    /**
     * Creates a panel for user input.
     *
     * @return The created panel.
     */
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

    /**
     * Creates a panel for buttons.
     *
     * @return The created panel.
     */
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

    /**
     * Creates a button for navigating back to the main menu.
     *
     * @return The created button.
     */
    private JButton createBackButton() {
        JButton button = new JButton("Back to Main Menu");
        button.addActionListener(e -> {
            dispose();
            new MainView();
        });

        return button;
    }

    /**
     * Creates a button for registering an admin.
     *
     * @return The created button.
     */
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

                    //noinspection CallToPrintStackTrace
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(RegistrationView.this, "Professor registration key is invalid");
            }

        });

        return button;
    }

    /**
     * Does not set up any redirects.
     */
    @Override
    public void setupRedirects() {
    }
}
