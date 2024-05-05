package am.aua.library.ui.views;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.models.Student;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminView extends AbstractPage {
    private SearchPanel searchPanel;
    private AdminViewNavigationPanel adminViewNavigationPanel;

    protected BookRepositoryImpl bookRepository;
    protected StudentRepositoryImpl studentRepository;

    protected DefaultListModel<Book> bookListModel;
    protected DefaultListModel<Student> studentListModel;

    protected JPanel groupedPanel;
    protected JScrollPane bookListPane;
    protected JScrollPane studentListPane;

    public AdminView(Long id) {
        super("Admin View");
    }

    @Override
    protected synchronized void setup() {
        this.setLayout(new BorderLayout());
        this.bookListModel = new DefaultListModel<>();
        this.studentListModel = new DefaultListModel<>();
        this.bookRepository = new BookRepositoryImpl();
        this.studentRepository = new StudentRepositoryImpl();
        this.adminViewNavigationPanel = new AdminViewNavigationPanel(this, new AdminViewNavigationChangeListener(this));
        // Setting up the shared navigation panel for the Admin view
        this.searchPanel = new SearchPanel(query -> {
            this.bookListModel.clear();
            this.studentListModel.clear();
            if (adminViewNavigationPanel.getCurrentTab() == AbstractNavigationPanel.Tab.BOOKS) {
                if (query.isEmpty() || query.isBlank()) {
                    this.bookListModel.addAll(this.bookRepository.findAll());
                    return;
                }

                this.bookListModel.addAll(this.bookRepository.findByTitle(query));
            } else if (adminViewNavigationPanel.getCurrentTab() == AbstractNavigationPanel.Tab.USERS) {
                if (query.isEmpty() || query.isBlank()) {
                    this.studentListModel.addAll(this.studentRepository.findAll());
                    return;
                }

                this.studentListModel.addAll(this.studentRepository.findByNameContaining(query));
            }
        });
    }

    @Override
    protected synchronized void addComponents() {
        this.add(this.adminViewNavigationPanel, BorderLayout.NORTH);

        this.groupedPanel = new JPanel();
        this.groupedPanel.setLayout(new BorderLayout());
        this.groupedPanel.add(this.searchPanel, BorderLayout.NORTH);

        this.addBooksList();
        this.groupedPanel.revalidate();
        this.add(this.groupedPanel, BorderLayout.CENTER);
    }

    protected void addBooksList() {
        this.bookListModel.addAll(bookRepository.findAll());
        JList<Book> bookJList = new JList<>(this.bookListModel);
        bookJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Book book = bookJList.getSelectedValue();
                    new BookView(book, AdminView.this);
                }
            }
        });

        this.bookListPane = new JScrollPane(bookJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        groupedPanel.add(this.bookListPane, BorderLayout.CENTER);
    }

    protected void removeBooksList() {
        this.groupedPanel.remove(this.bookListPane);
    }

    protected void addStudentsList() {
        JList<Student> studentJList = new JList<>(this.studentListModel);
        this.studentListPane = new JScrollPane(studentJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.groupedPanel.add(this.studentListPane, BorderLayout.CENTER);
    }

    protected void removeStudentsList() {
        this.groupedPanel.remove(this.studentListPane);
    }
}

class AdminViewNavigationPanel extends AbstractNavigationPanel {
    public AdminViewNavigationPanel(AbstractPage page, NavigationChangeListener listener) {
        super(page, listener);
        this.setCurrentTab(Tab.BOOKS);
        this.configureDefaultNavigation(this.getPage(), true);
    }
}

class AdminViewNavigationChangeListener implements AbstractNavigationPanel.NavigationChangeListener {
    private final AdminView view;

    public AdminViewNavigationChangeListener(AdminView view) {
        this.view = view;
    }

    @Override
    public void onChange(AbstractNavigationPanel.Tab tab) {
        boolean flag = tab.equals(AbstractNavigationPanel.Tab.BOOKS);
        if (!flag) {
            view.addStudentsList();
            view.removeBooksList();
        } else {
            view.addBooksList();
            view.removeStudentsList();
        }

        // triggering a re-render of the nested panel
        view.groupedPanel.revalidate();
    }
}
