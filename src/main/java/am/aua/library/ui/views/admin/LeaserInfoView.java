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

public class LeaserInfoView {

    private AbstractPage parent;

    private LeaserRepository leaserRepository;
    private InstitutionRepository institutionRepository;

    private JTextField idField;
    private JTextField fullNameField;
    private JComboBox<Object> institutionField;
    private JTextField passphraseField;

    private Leaser leaser;

    public LeaserInfoView(AbstractPage parent, Leaser leaser) {

        this.leaser = leaser;

        setup();
        addComponents(parent);
    }

    public void setup() {
        this.leaserRepository = new LeaserRepositoryImpl();
        this.institutionRepository = new InstitutionRepositoryImpl();
    }


    public void addComponents(AbstractPage parent) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        this.idField = new JTextField(String.valueOf(leaser.getId()));
        this.fullNameField = new JTextField(leaser.getFullName());
        this.institutionField = new JComboBox<>(institutionRepository.findAll().stream().map(Institution::getName).toArray());
        this.passphraseField = new JTextField(leaser.getPassword());

        this.idField.setEditable(false);

        panel.add(new JLabel("ID: "));
        panel.add(idField);
        panel.add(new JLabel("Full Name: "));
        panel.add(fullNameField);
        panel.add(new JLabel("Passphrase: "));
        panel.add(this.passphraseField);
        panel.add(new JLabel("Institution:"));
        panel.add(institutionField);

        JButton update = new JButton("Update");
        update.addActionListener(e -> {
            System.out.println("Updating " + leaser.getFullName());
            update();
        });

        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            System.out.println("Deleting " + leaser.getFullName());
            delete();
        });

        panel.add(update);
        panel.add(delete);

        JOptionPane.showMessageDialog(parent, panel);
    }

    private void delete() {
        try {
            leaserRepository.remove(leaser);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }

    private void update() {
        try {
            System.out.println(institutionField.getSelectedItem().toString());
            Leaser element = new Leaser(String.valueOf(this.fullNameField.getText()), this.passphraseField.getText(), institutionRepository.getByName(institutionField.getSelectedItem().toString()).getId());
            element.setId(this.leaser.getId());
            leaserRepository.update(element);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}
