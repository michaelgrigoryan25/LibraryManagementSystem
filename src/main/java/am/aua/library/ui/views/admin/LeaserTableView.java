package am.aua.library.ui.views.admin;

import am.aua.library.models.Leaser;
import am.aua.library.repositories.InstitutionRepository;
import am.aua.library.repositories.InstitutionRepositoryImpl;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * View for displaying and managing leasers.
 */
public final class LeaserTableView extends AbstractPage {

    /**
     * Column names for the leaser table.
     */
    private static final String[] COLUMN_NAMES = {"ID", "Full Name", "Passphrase", "Institution"};

    /**
     * Singleton instance of LeaserTableView.
     */
    private static LeaserTableView instance;

    /**
     * Gets the singleton instance of LeaserTableView.
     *
     * @return The singleton instance of LeaserTableView.
     */
    static LeaserTableView getInstance() {
        return instance;
    }

    /**
     * Table displaying leasers.
     */
    private JTable leaserTable;

    /**
     * Scroll pane for the leaser table.
     */
    private JScrollPane scrollPane;

    /**
     * Text field for filtering leasers.
     */
    private JTextField filterTextField;

    /**
     * Model for the leaser table.
     */
    private DefaultTableModel leaserTableModel;

    /**
     * Sorter for the leaser table.
     */
    private TableRowSorter<TableModel> rowSorter;

    /**
     * Repository for leasers.
     */
    private LeaserRepositoryImpl leaserRepository;

    /**
     * Repository for institutions.
     */
    private InstitutionRepository institutionRepository;

    /**
     * List of leasers.
     */
    private List<Leaser> leasers;

    /**
     * Constructs a new LeaserTableView.
     */
    public LeaserTableView() {
        super("Leaser's view");
        setup();
        addComponents();
    }

    /**
     * Sets up the leaser table view.
     */
    @Override
    protected void setup() {
        LeaserTableView.instance = this;
        this.setLayout(new BorderLayout());
        this.leaserRepository = new LeaserRepositoryImpl();
        this.institutionRepository = new InstitutionRepositoryImpl();
        this.leasers = leaserRepository.findAll();

        this.leaserTableModel = new DefaultTableModel(getUpdatedLeasers(), COLUMN_NAMES);
        this.leaserTable = new JTable(this.leaserTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.leaserTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = leaserTable.rowAtPoint(evt.getPoint());
                int col = leaserTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    Leaser selectedLeaser = leasers.get(row);
                    new LeaserInfoView(selectedLeaser);
                    LeaserTableView.this.dispose();
                }
            }
        });

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

    /**
     * Adds components to the leaser table view.
     */
    @Override
    protected void addComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.filterTextField = new JTextField();
        this.filterTextField.setBorder(new EmptyBorder(0, 20, 0, 0));
        JButton filterRowsButton = new JButton("Search");
        filterRowsButton.addActionListener(e -> filterLeasers());
        this.filterTextField.addActionListener(e -> filterLeasers());

        panel.add(new JLabel("Specify a word to match:"), BorderLayout.WEST);
        panel.add(this.filterTextField, BorderLayout.CENTER);
        panel.add(filterRowsButton, BorderLayout.EAST);

        this.add(panel, BorderLayout.SOUTH);
        this.add(this.scrollPane, BorderLayout.CENTER);
    }

    /**
     * Retrieves updated leasers data.
     *
     * @return The updated leasers data.
     */
    private Object[][] getUpdatedLeasers() {
        ArrayList<List<Object>> elements = new ArrayList<>();
        for (Leaser leaser : this.leaserRepository.findAll()) {
            elements.add(List.of(leaser.getId(), leaser.getFullName(), leaser.getPassword(), institutionRepository.get(leaser.getInstitutionId()).getName()));
        }

        Object[][] raw = new Object[elements.size()][];

        for (int i = 0; i < elements.size(); i++) raw[i] = elements.get(i).toArray();
        return raw;
    }

    /**
     * Filters leasers based on the input query.
     */
    private void filterLeasers() {
        this.leaserTableModel.setDataVector(getUpdatedLeasers(), COLUMN_NAMES);

        String query = filterTextField.getText();
        if (!query.isBlank() && !query.isEmpty()) {
            this.rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            this.rowSorter.setRowFilter(null);
        }
    }
}