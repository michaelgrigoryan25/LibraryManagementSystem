package am.aua.library.ui.views.admin;

import am.aua.library.models.Book;
import am.aua.library.models.Leaser;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class represents the view for displaying currently leased books for a specific leaser.
 */
public final class CurrentLeases extends AbstractPage {
    private JTextField inputField;
    private JButton renderButton;
    private JTable table;
    private LeaserRepositoryImpl leaserRepository;
    private BookRepositoryImpl bookRepository;
    private final static String[] COlUMN_NAMES = {"ID", "Title", "Author", "Lease End Date", "Is Overdue?"};


    /**
     * Constructs a new CurrentLeases view.
     */
    public CurrentLeases() {
        super("Currently Leased Books");
    }

    @Override
    protected void setup() {
        inputField = new JTextField(20);
        renderButton = new JButton("Find Leased Books");
        leaserRepository = new LeaserRepositoryImpl();
        bookRepository = new BookRepositoryImpl();
        renderButton.addActionListener(e -> {
            try {
                if (this.inputField == null || this.inputField.getText().isBlank() || this.inputField.getText().isEmpty()) {
                    renderTable();
                } else {
                    renderTable(Long.decode(this.inputField.getText()));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Provide a valid ID!");
                //noop
            }
        });

        // Create the table with an empty model for now
        table = new JTable(new DefaultTableModel()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.table.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = CurrentLeases.this.table;
                String cellValue = table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                StringSelection stringSelection = new StringSelection(cellValue);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
            }
        });

        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    Long selectedLeaserId = (Long) CurrentLeases.this.table.getModel().getValueAt(row, 0);
                    Leaser leaser = CurrentLeases.this.leaserRepository.get(selectedLeaserId);
                    new LeaserInfoView(leaser);
                    CurrentLeases.this.dispose();
                }
            }
        });
    }

    private void renderTable() {
        // Create a new table model with the data
        DefaultTableModel model = new DefaultTableModel(getUpdatedBooks(), COlUMN_NAMES);
        // Set the new table model to the table
        table.setModel(model);
    }


    private void renderTable(Long id) {
        // Create a new table model with the data
        DefaultTableModel model = new DefaultTableModel(getUpdatedBooks(id), COlUMN_NAMES);
        // Set the new table model to the table
        table.setModel(model);
    }

    @Override
    protected void addComponents() {
        renderTable();
        // Add components to the frame
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1, 1));
        panel.add(new JLabel("Enter Leaser ID: "));
        panel.add(inputField);
        panel.add(renderButton);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER); // Add a scroll pane for the table
    }

    private Object[][] getUpdatedBooks(Long id) {
        try {
            ArrayList<java.util.List<Object>> elements = new ArrayList<>();
            List<Leaser.Lease> leases = this.leaserRepository.getLeases(id);
            if (leases != null) {
                for (Leaser.Lease lease : this.leaserRepository.getLeases(id)) {
                    Book book = bookRepository.get(lease.getId());
                    Calendar calendar = Calendar.getInstance();
                    elements.add(List.of(book.getId(), book.getTitle(), book.getAuthors(), lease.getLeaseEndDate(), calendar.before(lease.getLeaseEndDate())));
                }
            }

            Object[][] raw = new Object[elements.size()][];
            for (int i = 0; i < elements.size(); i++) raw[i] = elements.get(i).toArray();
            return raw;
        } catch (Exception ex) {
            //noinspection CallToPrintStackTrace
            ex.printStackTrace();
            return null;
        }
    }

    private Object[][] getUpdatedBooks() {
        try {
            ArrayList<java.util.List<Object>> elements = new ArrayList<>();
            List<Leaser> leasers = this.leaserRepository.findAll();
            if (leasers != null) {
                for (Leaser leaser : leasers) {
                    if (leaser.getLeases() != null) {
                        for (Leaser.Lease lease : leaser.getLeases()) {
                            Book book = bookRepository.get(lease.getId());
                            Calendar calendar = Calendar.getInstance();
                            elements.add(List.of(book.getId(), book.getTitle(), book.getAuthors(), lease.getLeaseEndDate(), calendar.before(lease.getLeaseEndDate())));
                        }
                    }
                }
            }

            Object[][] raw = new Object[elements.size()][];
            for (int i = 0; i < elements.size(); i++) raw[i] = elements.get(i).toArray();
            return raw;
        } catch (Exception ex) {
            // Print the stack trace if an exception occurs
            //noinspection CallToPrintStackTrace
            ex.printStackTrace();
            return null;
        }
    }
}
