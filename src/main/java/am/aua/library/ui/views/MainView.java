package am.aua.library.ui.views;

import am.aua.library.database.Database;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends AbstractPage {
    @Override
    protected void setup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
    }

    @Override
    protected void addComponents() {
        add(createTextPanel());
        add(createImagePanel());
        add(createButtonPanel());
    }

    private JPanel createImagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        ImageIcon imageIcon = new ImageIcon(Database.getAssetPath("./images/books.png").toString());
        Image image = imageIcon.getImage();
        Image resized = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resized);
        panel.add(new JLabel(imageIcon));
        return panel;
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
        rootPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

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
        new LoginView();
    }

    private void openRegisterPage() {
        new RegistrationView();
    }
}
