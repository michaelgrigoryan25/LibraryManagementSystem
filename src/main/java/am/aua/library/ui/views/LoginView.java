package am.aua.library.ui.views;

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
 * View for user login.
 */
public final class LoginView extends AbstractPage {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AdminRepositoryImpl adminRepository;

    /**
     * Constructs a new LoginView.
     */
    public LoginView() {
        super("Login");
    }

    /**
     * Does not set up any redirects.
     */
    @Override
    public void setupRedirects() {
    }

    /**
     * Sets up the login view.
     */
    @Override
    public void setup() {
        this.setLayout(new GridLayout(3, 1));
        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        this.adminRepository = new AdminRepositoryImpl();
    }

    /**
     * Adds components to the login view.
     */
    @Override
    public void addComponents() {
        this.add(createTextPanel());
        this.add(createInputPanel());
        this.add(createButtonsPanel());
    }

    /**
     * Creates a panel with text.
     *
     * @return The created panel.
     */
    private JPanel createTextPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel text = new Text("Login", Text.Size.LG);
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
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(new EmptyBorder(0, 50, 0, 50));

        JLabel usernameLabel = new Text("Username:");
        JLabel passwordLabel = new Text("Password:");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        rootPanel.add(panel, BorderLayout.CENTER);
        return rootPanel;
    }

    /**
     * Creates a panel with buttons.
     *
     * @return The created panel.
     */
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

    /**
     * Creates a button to navigate back to the main menu.
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
     * Creates a button for user login.
     *
     * @return The created button.
     */
    private JButton createLoginButton() {
        JButton login = new JButton("Login");
        login.addActionListener(e -> authenticate());
        return login;
    }

    /**
     * Authenticates user credentials.
     */
    private void authenticate() {
        String username = usernameField.getText();
        if (!Helpers.isValidUsername(username)) {
            JOptionPane.showMessageDialog(LoginView.this, "Invalid username");
            return;
        }

        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            JOptionPane.showMessageDialog(LoginView.this, "User not found");
            return;
        }

        String password = new String(passwordField.getPassword());
        if (!Helpers.isValidPassword(password)) {
            JOptionPane.showMessageDialog(LoginView.this, "Invalid password");
            return;
        }

        if (password.equals(admin.getPassword())) {
            this.dispose();
            new AdminView();
            return;
        }

        JOptionPane.showMessageDialog(LoginView.this, "Incorrect password");
    }
}
