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

public final class LoginView extends AbstractPage {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AdminRepositoryImpl adminRepository;

    public LoginView() {
        super("Login");
    }

    @Override
    public void setup() {
        this.setLayout(new GridLayout(3, 1));
        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
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
            new MainView();
        });

        return button;
    }

    private JButton createLoginButton() {
        JButton login = new JButton("Login");
        login.addActionListener(e -> authenticate());
        return login;
    }

    private void authenticate() {
        String username = usernameField.getText();
        String javaEnv = System.getenv("JAVA_ENV");

        // WARNING: This chunk is only used for development purposes
        if (javaEnv != null && javaEnv.equals("development")) {
            LoginView.this.dispose();
            if (username.equals("admin")) {
                new AdminView();
                return;
            }
            return;
        }

        if (!Helpers.isValidUsername(username)) {
            JOptionPane.showMessageDialog(LoginView.this, "Invalid username");
            return;
        }

        String password = new String(passwordField.getPassword());
        if (!Helpers.isValidPassword(password)) {
            JOptionPane.showMessageDialog(LoginView.this, "Incorrect password");
            return;
        }

        this.dispose();

        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            JOptionPane.showMessageDialog(LoginView.this, "User not found");
            return;
        }

        if (password.equals(admin.getPassword())) {
            new AdminView();
            return;
        }

        JOptionPane.showMessageDialog(LoginView.this, "Incorrect password");
    }
}
