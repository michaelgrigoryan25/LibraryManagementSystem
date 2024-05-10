package am.aua.library.ui.views.admin;

import am.aua.library.models.Book;
import am.aua.library.models.Leaser;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        renderButton.addActionListener(e -> renderTable());
        // Create the table with an empty model for now
        table = new JTable(new DefaultTableModel()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void renderTable() {
        // Sample data, you can replace this with your actual data retrieval logic
        String[] columnNames = {"ID", "Title", "Author", "Lease End Date", "Is Overdue?"};

        // Create a new table model with the data
        DefaultTableModel model = new DefaultTableModel(getUpdatedBooks(), columnNames);

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

    private Object[][] getUpdatedBooks() {
        try {
            ArrayList<java.util.List<Object>> elements = new ArrayList<>();
            List<Leaser.Lease> leases = this.leaserRepository.getLeases(Long.decode(this.inputField.getText()));
            if (leases != null) {
                for (Leaser.Lease lease : this.leaserRepository.getLeases(Long.decode(this.inputField.getText()))) {
                    Book book = bookRepository.get(lease.getId());
                    Calendar calendar = Calendar.getInstance();
                    elements.add(List.of(book.getId(), book.getTitle(), book.getAuthors(), lease.getLeaseEndDate(), calendar.before(lease.getLeaseEndDate())));
                }
            }

            Object[][] raw = new Object[elements.size()][];
            for (int i = 0; i < elements.size(); i++) raw[i] = elements.get(i).toArray();
            return raw;
        } catch (Exception ex) {
            if (ex instanceof NumberFormatException) {
                JOptionPane.showMessageDialog(this, "Provide a valid ID!");
            } else {
                // Print the stack trace if an exception occurs
                //noinspection CallToPrintStackTrace
                ex.printStackTrace();
            }

            return null;
        }
    }
}
