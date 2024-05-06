package am.aua.library.ui.views;

import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class LeaserTableView extends AbstractPage {
    private static final String[] COLUMN_NAMES = {
            "ID", "Full Name", "Username", "Language", "Available Copies", "Pages"
    };
    private static final boolean[] EDITABLE_COLUMNS = {
            false, true, true, true, false, true,
    };

    private JTable bookTable;
    private DefaultTableModel bookTableModel;
    private TableRowSorter<TableModel> rowSorter;
    private JTextField filterTextField;

    private JScrollPane scrollPane;
    private BookRepositoryImpl bookRepository;

    @Override
    protected void setup() {

    }

    @Override
    protected void addComponents() {

    }
}
