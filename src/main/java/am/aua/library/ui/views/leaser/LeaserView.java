package am.aua.library.ui.views.leaser;

import am.aua.library.models.Leaser;
import am.aua.library.repositories.InstitutionRepository;
import am.aua.library.repositories.InstitutionRepositoryImpl;
import am.aua.library.repositories.LeaserRepository;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;
import am.aua.library.ui.views.admin.LeaserDeleteView;
import am.aua.library.ui.views.admin.LeaserUpdateView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public final class LeaserView extends AbstractPage {

    private static final String[] COLUMN_NAMES = {"Full Name", "University", "Delete", "Update"};

    private LeaserRepository leaserRepository;
    private InstitutionRepository institutionRepository;
    private List<Leaser> leasers;

    private DefaultTableModel defaultTableModel;
    private JTable table;

    public LeaserView() {
        super("Find & Update Leaser View");
    }

    @Override
    protected void setup() {
        this.setLayout(new FlowLayout());
        leaserRepository = new LeaserRepositoryImpl();
        institutionRepository = new InstitutionRepositoryImpl();
        leasers = leaserRepository.findAll();

        this.defaultTableModel = new DefaultTableModel(this.getUpdatedLeasers(), COLUMN_NAMES);
        this.table = new JTable(this.defaultTableModel);
    }

    @Override
    protected void addComponents() {
        this.add(this.table);
    }

    private Object[][] getUpdatedLeasers() {
        Object[][] data = new Object[leasers.size()][4];
        for (int i = 0; i < data.length; i++) {
            Leaser leaser = leasers.get(i);
            JButton update = new JButton("Update");
            JButton delete = new JButton("Delete");
            update.addActionListener(e -> {
                new LeaserUpdateView(leaser, leaserRepository);
            });
            delete.addActionListener(e -> {
                new LeaserDeleteView(leaser, leaserRepository);
            });
            data[i] = new Object[]{leaser.getFullName(), institutionRepository.get(leaser.getInstitutionId()).getName(), update, delete};
        }
        return data;
    }
}
