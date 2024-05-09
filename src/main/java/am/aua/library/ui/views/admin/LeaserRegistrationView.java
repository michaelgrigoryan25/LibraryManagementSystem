package am.aua.library.ui.views.admin;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Institution;
import am.aua.library.models.Leaser;
import am.aua.library.repositories.InstitutionRepository;
import am.aua.library.repositories.InstitutionRepositoryImpl;
import am.aua.library.repositories.LeaserRepository;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;

public final class LeaserRegistrationView {
    private JTextField fullNameField;
    private JPasswordField passwordField;

    private JPanel jPanel;

    private InstitutionRepository institutionRepository;
    private LeaserRepository leaserRepository;

    public LeaserRegistrationView() {
        this.setup();
    }

    public void setup() {
        institutionRepository = new InstitutionRepositoryImpl();
        leaserRepository = new LeaserRepositoryImpl();

        jPanel = new JPanel();

        fullNameField = new JTextField();
        passwordField = new JPasswordField();
        JComboBox<Object> institutionField = new JComboBox<>(institutionRepository.findAll().stream().map(Institution::getName).toArray());
        jPanel.setLayout(new GridLayout(8, 1));
        jPanel.add(new JLabel("Full Name"));
        jPanel.add(fullNameField);
        jPanel.add(new JLabel("Password"));
        jPanel.add(passwordField);
        jPanel.add(new JLabel("Institution"));
        jPanel.add(institutionField);
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register(fullNameField.getText(), String.valueOf(passwordField.getPassword()), String.valueOf(institutionField.getSelectedItem())));

        jPanel.add(registerButton);

        JOptionPane.showMessageDialog(AdminView.getInstance(), jPanel);
        AdminView.getInstance().setVisible(true);
    }

    public void register(String fullName, String password, String institutionName) {
        System.out.println(fullName);
        System.out.println(password);
        System.out.println(institutionName);
        try {
            leaserRepository.add(new Leaser(fullName, password, institutionRepository.getByName(institutionName).getId()));
        } catch (DatabaseException e) {
            JTextArea errorArea = new JTextArea("Could not register user with provided credentials.");
            jPanel.add(errorArea);
        }
    }
}
