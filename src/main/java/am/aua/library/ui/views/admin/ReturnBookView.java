package am.aua.library.ui.views.admin;

import am.aua.library.models.Leaser;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;

public final class ReturnBookView extends AbstractPage {
    private JPanel panel;
    private LeaserRepositoryImpl leaserRepository;

    public ReturnBookView() {
        super("Return Books");
    }

    @Override
    protected void setup() {
        this.leaserRepository = new LeaserRepositoryImpl();

        this.setLayout(new BorderLayout());
        this.panel = new JPanel(new GridLayout(4, 0));
    }

    @Override
    protected void addComponents() {
        JComboBox<Leaser> leaserJComboBox = new JComboBox<>(leaserRepository.findAll().toArray(new Leaser[0]));
        this.panel.add(leaserJComboBox);
        this.add(panel);
    }
}
