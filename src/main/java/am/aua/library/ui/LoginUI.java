package am.aua.library.ui;

import am.aua.library.models.User;
import am.aua.library.repositories.ProfessorRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.core.Helpers;
import am.aua.library.ui.core.Page;
import am.aua.library.ui.core.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginUI extends Page {
    private final StudentRepositoryImpl studentRepository;
    private final ProfessorRepositoryImpl professorRepository;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        super("Login");
        this.studentRepository = new StudentRepositoryImpl();
        this.professorRepository = new ProfessorRepositoryImpl();
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
            new MainUI();
        });

        return button;
    }

    private JButton createLoginButton() {
        JButton register = new JButton("Login");
        register.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (Helpers.isValidPassword(password) && Helpers.isValidUsername(username)) {
                this.dispose(); // Close the login window

                boolean isAdmin = false;
                User user = studentRepository.findByUsername(username);
                if (user == null) {
                    user = professorRepository.findByUsername(username);
                    isAdmin = true;
                }

                if (user != null && password.equals(user.getPassword())) {
                    if (isAdmin) {
                        new AdminUI(user.getId());
                        return;
                    }

                    new ReaderUI(user.getId());
                    return;
                }

                JOptionPane.showMessageDialog(LoginUI.this, "Invalid username or password");
            }
        });

        return register;
    }
}
