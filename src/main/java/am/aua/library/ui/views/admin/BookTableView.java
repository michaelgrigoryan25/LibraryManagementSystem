package am.aua.library.ui.views.admin;

import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepositoryImpl;
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
import java.util.Objects;

/**
 * This class represents the view for displaying a table of books.
 */
public final class BookTableView extends AbstractPage {
    private static final String[] COLUMN_NAMES = {
            "ID", "Title", "Subtitle", "Year", "Language", "Available Copies", "Pages"
    };

    private JTable bookTable;
    private DefaultTableModel bookTableModel;
    private TableRowSorter<TableModel> rowSorter;
    private JTextField filterTextField;
    private JScrollPane scrollPane;
    private BookRepositoryImpl bookRepository;
    private List<Book> books;

    /**
     * Constructs a new BookTableView.
     */
    public BookTableView() {
        super("Books View");
    }

    @Override
    protected void setup() {
        this.setLayout(new BorderLayout());
        this.bookRepository = new BookRepositoryImpl();
        this.bookTableModel = new DefaultTableModel(this.getUpdatedBooks(), COLUMN_NAMES);
        this.books = bookRepository.findAll();
        this.bookTable = new JTable(this.bookTableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        this.bookTable.getActionMap().put("copy", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = BookTableView.this.bookTable;
                String cellValue = table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString();
                StringSelection stringSelection = new StringSelection(cellValue);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
            }
        });

        AbstractPage currentBook = this;
        this.bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = bookTable.rowAtPoint(evt.getPoint());
                int col = bookTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    Book selectedBook = books.get(row);
                    new BookAssignView(currentBook, selectedBook);
                    // refreshing the page by closing and reopening the panel
                    BookTableView.this.dispose();
                }
            }
        });

        this.rowSorter = new TableRowSorter<>(this.bookTable.getModel());
        this.bookTable.setRowSorter(this.rowSorter);
        this.scrollPane = new JScrollPane(this.bookTable);
    }

    @Override
    protected void addComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.filterTextField = new JTextField();
        this.filterTextField.setBorder(new EmptyBorder(0, 20, 0, 0));
        JButton filterRowsButton = new JButton("Search");
        filterRowsButton.addActionListener(e -> this.filterBooks());
        this.filterTextField.addActionListener(e -> this.filterBooks());

        panel.add(new JLabel("Specify a word to match:"), BorderLayout.WEST);
        panel.add(this.filterTextField, BorderLayout.CENTER);
        panel.add(filterRowsButton, BorderLayout.EAST);

        this.add(panel, BorderLayout.SOUTH);
        this.add(this.scrollPane, BorderLayout.CENTER);
    }

    /**
     * Filters the books based on the input in the filter text field.
     */
    private void filterBooks() {
        this.bookTableModel.setDataVector(this.getUpdatedBooks(), BookTableView.COLUMN_NAMES);

        String query = filterTextField.getText();
        if (!query.isBlank() && !query.isEmpty()) {
            this.rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            this.rowSorter.setRowFilter(null);
        }
    }

    /**
     * Retrieves updated book data.
     *
     * @return A 2D array of book data.
     */
    private Object[][] getUpdatedBooks() {
        ArrayList<List<Object>> elements = new ArrayList<>();
        for (Book book : this.bookRepository.findAll()) {
            elements.add(List.of(book.getId(), book.getTitle(), Objects.requireNonNullElse(book.getSubtitle(), ""), book.getYear(), book.getLanguage(), book.getCopies(), book.getPages()));
        }

        Object[][] raw = new Object[elements.size()][];
        for (int i = 0; i < elements.size(); i++) raw[i] = elements.get(i).toArray();
        return raw;
    }
}
