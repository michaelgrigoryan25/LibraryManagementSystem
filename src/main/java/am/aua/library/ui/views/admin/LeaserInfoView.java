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
 * This class represents the view for displaying and managing information about a leaser.
 */
public final class LeaserInfoView {
    private LeaserRepository leaserRepository;
    private InstitutionRepository institutionRepository;

    private JTextField fullNameField;
    private JComboBox<Object> institutionField;
    private JTextField passphraseField;
    private final Leaser leaser;

    /**
     * Constructs a new LeaserInfoView.
     *
     * @param leaser The leaser whose information is displayed.
     */
    public LeaserInfoView(Leaser leaser) {
        this.leaser = leaser;

        setup();
        addComponents();
    }

    /**
     * Sets up the LeaserInfoView.
     */
    public void setup() {
        this.leaserRepository = new LeaserRepositoryImpl();
        this.institutionRepository = new InstitutionRepositoryImpl();
    }

    /**
     * Adds components to the LeaserInfoView.
     */
    public void addComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JTextField idField = new JTextField(String.valueOf(leaser.getId()));
        this.fullNameField = new JTextField(leaser.getFullName());
        this.institutionField = new JComboBox<>(institutionRepository.findAll().stream().map(Institution::getName).toArray());
        this.passphraseField = new JTextField(leaser.getPassword());

        idField.setEditable(false);

        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(new JLabel("Full Name: "));
        panel.add(fullNameField);
        panel.add(new JLabel("Passphrase: "));
        panel.add(this.passphraseField);
        panel.add(new JLabel("Institution:"));
        panel.add(institutionField);


        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    delete();
                }
            }.start();

            JOptionPane.showMessageDialog(LeaserTableView.getInstance(), "Leaser deleted successfully");
            fullNameField.setEnabled(false);
            passphraseField.setEnabled(false);
            institutionField.setEnabled(false);
        });

        panel.add(new JLabel());
        panel.add(delete);
        JOptionPane.showMessageDialog(LeaserTableView.getInstance(), panel);
        if (leaserRepository.get(leaser.getId()) != null) {
            update();
        }

        new LeaserTableView();
    }

    /**
     * Deletes the leaser from the database.
     */
    private void delete() {
        try {
            leaserRepository.remove(leaser);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the information of the leaser.
     */
    private void update() {
        try {
            if (institutionField.getSelectedItem() != null) {
                Leaser element = new Leaser(String.valueOf(this.fullNameField.getText()), this.passphraseField.getText(), institutionRepository.getByName(institutionField.getSelectedItem().toString()).getId());
                element.setId(this.leaser.getId());
                leaserRepository.update(element);
            }
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
