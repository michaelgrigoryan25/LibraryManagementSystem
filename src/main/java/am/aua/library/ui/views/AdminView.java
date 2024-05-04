package am.aua.library.ui.views;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.models.Student;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AdminView extends AbstractPage {
//    private final Long id;

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
//        this.id = id;
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
        bookJList.setCellRenderer(new BookListCellRenderer(this));
        this.bookListPane = new JScrollPane(bookJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        groupedPanel.add(this.bookListPane, BorderLayout.CENTER);
    }

    protected void removeBooksList() {
        this.groupedPanel.remove(this.bookListPane);
    }

    protected void addStudentsList() {
        JList<Student> studentJList = new JList<>(this.studentListModel);
        studentJList.setCellRenderer(new StudentListCellRenderer(this));
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
        configureDefaultNavigation(this.getPage(), true);
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

class BookListCellRenderer implements ListCellRenderer<Book> {
    private final AdminView view;
    private static final ImageIcon penIcon = Helpers.getRescaledImageIcon("pen.png", 20, 20);
    private static final ImageIcon eyeIcon = Helpers.getRescaledImageIcon("eye.png", 20, 20);
    private static final ImageIcon removeIcon = Helpers.getRescaledImageIcon("remove.png", 20, 20);


    public BookListCellRenderer(AdminView view) {
        this.view = view;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list, Book value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel infoContainer = new JPanel();
        infoContainer.setLayout(new GridLayout(2, 1));
        String title = value.getTitle();
        if (title.length() > 50) title = title.substring(0, 49);
        infoContainer.add(new Text(title, Text.Size.MD));

        JPanel actionsContainer = new JPanel();
        actionsContainer.setLayout(new GridLayout(1, 2));

        JButton viewButton = new JButton(eyeIcon);
        JButton editIcon = new JButton(penIcon);
        JButton removeButton = new JButton(removeIcon);
        editIcon.addActionListener(e -> {
        });
        viewButton.addActionListener(e -> {
        });
        removeButton.addActionListener(e -> {
            System.out.println('a');
            try {
                view.bookRepository.remove(value);
            } catch (DatabaseException ex) {
                throw new RuntimeException(ex);
            }
            view.bookListModel.removeElement(value);
            view.groupedPanel.revalidate();
        });

        actionsContainer.add(viewButton);
        actionsContainer.add(editIcon);
        actionsContainer.add(removeButton);

        panel.add(infoContainer, BorderLayout.WEST);
        panel.add(actionsContainer, BorderLayout.EAST);
        panel.setMinimumSize(new Dimension(panel.getPreferredSize().width, panel.getPreferredSize().height + 100));
        return panel;
    }
}

class StudentListCellRenderer implements ListCellRenderer<Student> {
    private final AdminView view;

    public StudentListCellRenderer(AdminView view) {
        this.view = view;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Student> list, Student value, int index, boolean isSelected, boolean cellHasFocus) {
        return null;
    }
}
