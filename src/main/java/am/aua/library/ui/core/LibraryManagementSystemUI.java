package am.aua.library.ui.core;

import am.aua.library.ui.LoginUI;
import am.aua.library.ui.RegistrationUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LibraryManagementSystemUI extends Page {
    @Override
    protected void setupPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
    }

    @Override
    protected void setupComponents() {
        add(createTextPanel());
        add(createButtonPanel());
    }

    private JPanel createTextPanel() {
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());

        JLabel welcomeText = new JLabel("Library Management System");
        welcomeText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        container.add(welcomeText);
        return container;
    }

    private JPanel createButtonPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBorder(new EmptyBorder(100, 50, 100, 50));

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(2, 1);
        layout.setVgap(10);
        panel.setLayout(layout);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            dispose(); // Close the main menu
            openLoginPage(); // Open the login page
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            dispose(); // Close the main menu
            openRegisterPage(); // Open the registration page
        });
        registerButton.setSize(300, 300);

        panel.add(loginButton);
        panel.add(registerButton);
        rootPanel.add(panel);
        return rootPanel;
    }

    private void openLoginPage() {
        new LoginUI();
    }

    private void openRegisterPage() {
        new RegistrationUI();
    }
}
