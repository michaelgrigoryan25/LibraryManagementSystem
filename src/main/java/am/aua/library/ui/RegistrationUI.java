package am.aua.library.ui;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.models.Institution;
import am.aua.library.models.Student;
import am.aua.library.repositories.UserRepositoryImpl;
import am.aua.library.ui.core.LibraryManagementSystemUI;
import am.aua.library.ui.core.Page;
import am.aua.library.ui.core.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RegistrationUI extends Page {
    private final UserRepositoryImpl userRepository;

    private enum UserType {STUDENT, PROFESSOR}

    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<UserType> userTypeComboBox;
    private JComboBox institutionComboBox;

    public RegistrationUI() {
        super("Registration");
        this.userRepository = new UserRepositoryImpl();
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
        JLabel text = new Text("Registration", Text.Size.LG);
        panel.add(text);
        return panel;
    }

    private JPanel createInputPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(6, 1);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(0, 50, 0, 50));

        JLabel fullNameLabel = new Text("Full Name:");
        JLabel usernameLabel = new Text("Username:");
        JLabel passwordLabel = new Text("Password:");
        JLabel userTypeLabel = new Text("User Type: ");
        JLabel institutionLabel = new Text("Institution");

        fullNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        userTypeComboBox = createUserTypeComboBox();
        institutionComboBox = createInstitutionsComboBox();

        panel.add(fullNameLabel);
        panel.add(fullNameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(userTypeLabel);
        panel.add(userTypeComboBox);
        panel.add(institutionLabel);
        panel.add(institutionComboBox);
        panel.add(passwordLabel);
        panel.add(passwordField);

        rootPanel.add(panel, BorderLayout.CENTER);
        return rootPanel;
    }

    private JComboBox<UserType> createUserTypeComboBox() {
        return new JComboBox<>(UserType.values());
    }

    private JComboBox<Institution> createInstitutionsComboBox() {
        Institution[] institutions = this.database.getInstitutions().toArray(Institution[]::new);
        JComboBox<Institution> comboBox = new JComboBox<>(institutions);
        comboBox.setRenderer(new InstitutionCellRenderer());
        return comboBox;
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
            new LibraryManagementSystemUI();
        });

        return button;
    }

    private JButton createRegisterButton() {
        JButton button = new JButton("Register");
        button.addActionListener(e -> {
            String fullName = this.fullNameField.getText();
            if (fullName.isBlank() || fullName.isEmpty()) {
                JOptionPane.showMessageDialog(RegistrationUI.this, "Please enter a valid full name");
                return;
            }

            String username = this.usernameField.getText().toLowerCase();
            if (username.isEmpty() || username.isBlank()) {
                JOptionPane.showMessageDialog(RegistrationUI.this, "Username cannot be empty");
                return;
            }

            String password = new String(this.passwordField.getPassword());
            if (password.isBlank() || password.length() < 8) {
                JOptionPane.showMessageDialog(RegistrationUI.this, "Password must contain at least 8 characters");
                return;
            }

            UserType type = (UserType) this.userTypeComboBox.getSelectedItem();
            if (type == null) {
                JOptionPane.showMessageDialog(RegistrationUI.this, "Please select a user type for registration");
                return;
            }

            Institution institution = (Institution) this.institutionComboBox.getSelectedItem();
            if (institution == null) {
                JOptionPane.showMessageDialog(RegistrationUI.this, "Please select a verified institution");
                return;
            }

            if (type == UserType.STUDENT) {
                String[] nameChunks = fullName.split(" ");
                Student student = new Student(nameChunks[0], nameChunks[1], username, password, institution.getId());
                try {
                    this.userRepository.add(student);
                    // TODO: Figure out why the user isn't saving in the database
                } catch (DatabaseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        return button;
    }
}

class InstitutionCellRenderer implements ListCellRenderer<Institution> {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    private final static Dimension preferredSize = new Dimension(0, 20);

    @Override
    public Component getListCellRendererComponent(JList list, Institution value, int index, boolean isSelected, boolean cellHasFocus) {
        String name = value.getName();
        if (name.length() > 45) {
            name = name.substring(0, 46) + "...";
        }

        JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, name, index, isSelected, cellHasFocus);
        renderer.setPreferredSize(preferredSize);
        return renderer;
    }
}
