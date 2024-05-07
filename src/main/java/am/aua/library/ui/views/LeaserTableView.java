package am.aua.library.ui.views;

import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class LeaserTableView extends AbstractPage {
    private static final String[] COLUMN_NAMES = {
            "ID", "Full Name", "IID", "Institution", "Number of Current Leases", "Passphrase"
    };

    private static final boolean[] EDITABLE_COLUMNS = {
            false, true, false, false, false, true
    };

    private JTable bookTable;
    private JScrollPane scrollPane;
    private JTextField filterTextField;
    private DefaultTableModel bookTableModel;
    private BookRepositoryImpl bookRepository;
    private TableRowSorter<TableModel> rowSorter;

    @Override
    protected void setup() {

    }

    @Override
    protected void addComponents() {

    }
}
