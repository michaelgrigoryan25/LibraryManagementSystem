package am.aua.library.ui.views;

import am.aua.library.models.Leaser;
import am.aua.library.repositories.InstitutionRepository;
import am.aua.library.repositories.InstitutionRepositoryImpl;
import am.aua.library.repositories.LeaserRepository;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public final class LeaserView extends AbstractPage {

    private LeaserRepository leaserRepository;
    private InstitutionRepository institutionRepository;

    private List<Leaser> leasers;

    public LeaserView() {
        super("Find & Update Leaser View");
    }

    @Override
    protected void setup() {
        this.setLayout(new FlowLayout());
        leaserRepository = new LeaserRepositoryImpl();
        institutionRepository = new InstitutionRepositoryImpl();
        leasers = leaserRepository.findAll();
    }

    @Override
    protected void addComponents() {
        System.out.println(leasers.size());
        for(Leaser leaser : leasers) {
            JPanel panel = new JPanel(new GridLayout(1, 2));
            panel.add(new JTextField(leaser.getFullName()));
            panel.add(new JTextField(institutionRepository.get(leaser.getInstitutionId()).getName()));
            this.add(panel);
        }
    }
}
