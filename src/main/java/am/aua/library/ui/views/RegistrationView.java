package am.aua.library.ui.views;

import am.aua.library.database.Database;
import am.aua.library.database.DatabaseException;
import am.aua.library.database.DuplicateRecordException;
import am.aua.library.models.Institution;
import am.aua.library.models.Professor;
import am.aua.library.models.Student;
import am.aua.library.repositories.ProfessorRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;
import am.aua.library.ui.components.Text;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class RegistrationView extends AbstractPage {
    private enum UserType {STUDENT, PROFESSOR}

    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<UserType> userTypeComboBox;
    private JComboBox<Institution> institutionComboBox;
    private JLabel professorRegistrationKeyLabel;
    private JPasswordField professorRegistrationKeyField;

    private StudentRepositoryImpl userRepository;
    private ProfessorRepositoryImpl professorRepository;

    public RegistrationView() {
        super("Registration");
    }

    @Override
    public void setup() {
        this.setLayout(new GridLayout(3, 1));
        this.userRepository = new StudentRepositoryImpl();
        this.professorRepository = new ProfessorRepositoryImpl();
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
        GridLayout layout = new GridLayout(6, 1);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(0, 50, 0, 50));

        JLabel fullNameLabel = new Text("Full Name:", Text.Size.SM);
        JLabel usernameLabel = new Text("Username:", Text.Size.SM);
        JLabel passwordLabel = new Text("Password:", Text.Size.SM);
        JLabel userTypeLabel = new Text("User Type: ", Text.Size.SM);
        JLabel institutionLabel = new Text("Institution: ", Text.Size.SM);

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

        professorRegistrationKeyLabel = new Text("Professor Registration Key: ");
        professorRegistrationKeyLabel.setVisible(false);
        professorRegistrationKeyField = new JPasswordField();
        professorRegistrationKeyField.setVisible(false);

        panel.add(professorRegistrationKeyLabel);
        panel.add(professorRegistrationKeyField);

        rootPanel.add(panel, BorderLayout.CENTER);
        return rootPanel;
    }

    private JComboBox<UserType> createUserTypeComboBox() {
        JComboBox<UserType> userTypeComboBox = new JComboBox<>(UserType.values());
        userTypeComboBox.addItemListener(e -> {
            professorRegistrationKeyLabel.setVisible(e.getItem().equals(UserType.PROFESSOR));
            professorRegistrationKeyField.setVisible(e.getItem().equals(UserType.PROFESSOR));
        });
        return userTypeComboBox;
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

            UserType type = (UserType) this.userTypeComboBox.getSelectedItem();
            if (type == null) {
                JOptionPane.showMessageDialog(RegistrationView.this, "Please select a user type for registration");
                return;
            }

            Institution institution = (Institution) this.institutionComboBox.getSelectedItem();
            if (institution == null) {
                JOptionPane.showMessageDialog(RegistrationView.this, "Please select a verified institution");
                return;
            }


            if (type == UserType.STUDENT) {
                Student student = new Student(fullName, username, password, institution.getId());
                try {
                    this.userRepository.add(student);
                    JOptionPane.showMessageDialog(RegistrationView.this, "Registered successfully!");
                    dispose();
                    new ReaderView(student.getId());
                } catch (DatabaseException ex) {
                    if (ex instanceof DuplicateRecordException) {
                        JOptionPane.showMessageDialog(RegistrationView.this, "Student with username `" + username + "` already exists. Choose a different username.");
                        return;
                    }

                    System.err.print(getClass().getCanonicalName());
                    System.err.print(": " + ex.getMessage());
                }
            } else {
                if (Arrays.equals(professorRegistrationKeyField.getPassword(), Database.PROFESSOR_REGISTRATION_KEY.toCharArray())) {
                    Professor professor = new Professor(fullName, username, password, institution.getId());
                    try {
                        this.professorRepository.add(professor);
                        JOptionPane.showMessageDialog(RegistrationView.this, "Registered successfully!");
                        dispose();
                        new AdminView(professor.getId());
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
            }
        });

        return button;
    }

    private static class InstitutionCellRenderer implements ListCellRenderer<Institution> {
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
}
