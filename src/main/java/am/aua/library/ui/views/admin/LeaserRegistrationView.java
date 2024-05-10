package am.aua.library.ui.views.admin;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Institution;
import am.aua.library.models.Leaser;
import am.aua.library.repositories.InstitutionRepository;
import am.aua.library.repositories.InstitutionRepositoryImpl;
import am.aua.library.repositories.LeaserRepository;
import am.aua.library.repositories.LeaserRepositoryImpl;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the view for registering a new leaser.
 */
public final class LeaserRegistrationView {

    /**
     * Constructs a new LeaserRegistrationView.
     */
    public LeaserRegistrationView() {
        this.setup();
    }

    /**
     * Sets up the components and functionality for the leaser registration view.
     */
    public void setup() {
        InstitutionRepository institutionRepository = new InstitutionRepositoryImpl();
        LeaserRepository leaserRepository = new LeaserRepositoryImpl();

        JPanel jPanel = new JPanel();

        JTextField fullNameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JComboBox<Object> institutionField = new JComboBox<>(institutionRepository.findAll().toArray());
        jPanel.setLayout(new GridLayout(8, 1));
        jPanel.add(new JLabel("Full Name"));
        jPanel.add(fullNameField);
        jPanel.add(new JLabel("Password"));
        jPanel.add(passwordField);
        jPanel.add(new JLabel("Institution"));
        jPanel.add(institutionField);

        JOptionPane.showMessageDialog(AdminView.getInstance(), jPanel);
        // Assuming that the user wants to trigger the registration process
        if (!fullNameField.getText().isEmpty() || !fullNameField.getText().isBlank()) {
            String fullName = fullNameField.getText();
            String password = String.copyValueOf(passwordField.getPassword());
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(AdminView.getInstance(), "Invalid password");
            } else {
                Institution institution = (Institution) institutionField.getSelectedItem();
                if (institution == null) {
                    JOptionPane.showMessageDialog(AdminView.getInstance(), "Select a valid institution");
                } else {
                    try {
                        leaserRepository.add(new Leaser(fullName, password, institution.getId()));
                    } catch (DatabaseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        AdminView.getInstance().setVisible(true);
    }
}
