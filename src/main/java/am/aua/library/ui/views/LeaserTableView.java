package am.aua.library.ui.views;

import am.aua.library.models.Leaser;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LeaserTableView extends AbstractPage {
    private static final String[] COLUMN_NAMES = {
            "ID", "Full Name", "IID", "Institution", "Number of Current Leases", "Passphrase"
    };

    private static final boolean[] EDITABLE_COLUMNS = {
            false, true, false, false, false, true
    };

    private JTable leaserTable;
    private JScrollPane scrollPane;
    private JTextField filterTextField;
    private DefaultTableModel leaserTableModel;
    private LeaserRepositoryImpl leaserRepository;
    private TableRowSorter<TableModel> rowSorter;

    public LeaserTableView() {
        super("Leaser's view");
    }

    @Override
    protected void setup() {
        this.setLayout(new BorderLayout());
        this.leaserRepository = new LeaserRepositoryImpl();
        this.leaserTableModel = new DefaultTableModel(this.getUpdatedLeasers(), COLUMN_NAMES);
        this.leaserTable = new JTable(this.leaserTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.leaserTable.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = LeaserTableView.this.leaserTable;
                String cellValue = table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                StringSelection stringSelection = new StringSelection(cellValue);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
            }
        });
        this.rowSorter = new TableRowSorter<>(this.leaserTable.getModel());
        this.leaserTable.setRowSorter(this.rowSorter);
        this.scrollPane = new JScrollPane(this.leaserTable);
    }

    @Override
    protected void addComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.filterTextField = new JTextField();
        this.filterTextField.setBorder(new EmptyBorder(0, 20, 0, 0));
        JButton filterRowsButton = new JButton("Search");
        filterRowsButton.addActionListener(e -> this.filterLeasers());
        this.filterTextField.addActionListener(e -> this.filterLeasers());

        panel.add(new JLabel("Specify a word to match:"), BorderLayout.WEST);
        panel.add(this.filterTextField, BorderLayout.CENTER);
        panel.add(filterRowsButton, BorderLayout.EAST);

        this.add(panel, BorderLayout.SOUTH);
        this.add(this.scrollPane, BorderLayout.CENTER);
    }

    private Object[][] getUpdatedLeasers() {
        ArrayList<java.util.List<Object>> elements = new ArrayList<>();
        for (Leaser leaser : this.leaserRepository.findAll()) {
            elements.add(List.of(leaser.getId(), leaser.getFullName(), Objects.requireNonNullElse(leaser.getPassword() /*may be deleted*/, "")));
        }

        Object[][] raw = new Object[elements.size()][];
        for (int i = 0; i < elements.size(); i++) raw[i] = elements.get(i).toArray();
        return raw;
    }

    private void filterLeasers() {

    }
}