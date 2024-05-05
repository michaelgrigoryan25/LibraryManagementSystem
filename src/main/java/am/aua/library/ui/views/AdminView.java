package am.aua.library.ui.views;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.models.Student;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.StudentRepositoryImpl;
import am.aua.library.ui.components.*;
import org.javatuples.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class AdminView extends AbstractPage {
    private SearchPanel searchPanel;
    protected AdminViewNavigationPanel adminViewNavigationPanel;

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
                    new AdminBookView(book, AdminView.this);
                }
            }
        });

        this.bookListPane = new JScrollPane(bookJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
        groupedPanel.add(this.bookListPane, BorderLayout.CENTER);
    }

    protected void removeBooksList() {
        this.groupedPanel.remove(this.bookListPane);
    }

    protected void addStudentsList() {
        JList<Student> studentJList = new JList<>(this.studentListModel);
        this.studentListPane = new JScrollPane(studentJList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_NEVER);
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
        if (tab == AbstractNavigationPanel.Tab.ADD)
            view.adminViewNavigationPanel.changeTab(AbstractNavigationPanel.Tab.BOOKS);

        boolean flag = tab == AbstractNavigationPanel.Tab.BOOKS;
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

class AdminAddBookView extends AbstractPage {
    private AdminView parent;

    public AdminAddBookView(AdminView parent) {
        super("Add Book",
                new Pair<>("parent", parent));
    }

    @Override
    protected void setup() {
        this.parent = (AdminView) this.getInternalState().get("parent");
    }

    @Override
    protected void addComponents() {

    }
}

class AdminBookView extends AbstractPage {
    private Book book;
    private JLabel infoLabel;
    private AdminView parent;

    public AdminBookView(Book book, AdminView parent) {
        super("Book View",
                new Pair<>("parent", parent),
                new Pair<>("book", book),
                new Pair<>("loading", true));
    }

    @Override
    protected void setup() {
        this.book = (Book) this.getInternalState().get("book");
        this.parent = (AdminView) this.getInternalState().get("parent");
        this.infoLabel = new Text("Loading...", Text.Size.LG);
        this.infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void addComponents() {
        this.add(this.infoLabel, BorderLayout.CENTER);

        new Thread(() -> {
            try {
                Image image = ImageIO.read(this.book.getCover());
                SwingUtilities.invokeLater(() -> {
                    this.remove(this.infoLabel);

                    JPanel root = new JPanel();
                    root.setLayout(new GridLayout(3, 0));
                    root.setMinimumSize(this.parent.getSize());
                    root.setPreferredSize(this.parent.getSize());

                    JPanel buttons = new JPanel();
                    JButton viewButton = getViewButton();
                    buttons.add(viewButton);
                    JButton editButton = new JButton("Edit");
                    buttons.add(editButton);
                    JButton removeButton = getRemoveButton();
                    buttons.add(removeButton);

                    JPanel textContent = new JPanel();
                    textContent.setSize(new Dimension(textContent.getPreferredSize().width, 600));
                    textContent.setLayout(new GridBagLayout());
                    textContent.setBorder(new EmptyBorder(10, 10, 10, 10));

                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.weightx = 1;
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 0;

                    gbc.gridy = 0;
                    textContent.add(new Text(wrapText(book.getTitle()), Text.Size.MD), gbc);

                    gbc.gridy = 1;
                    String subtitle = book.getSubtitle();
                    if (subtitle == null) {
                        subtitle = "No subtitle available";
                    }
                    textContent.add(new Text(wrapText(subtitle), Text.Size.MD), gbc);

                    gbc.gridy = 2;
                    gbc.gridwidth = 3;
                    gbc.weighty = 1;
                    JLabel description = new JLabel(wrapText(book.getDescription()));
                    description.setBorder(new EmptyBorder(10, 10, 10, 10));

                    textContent.add(description, gbc);

                    root.add(textContent);
                    JLabel cover = new JLabel(new ImageIcon(image));
                    root.add(cover);
                    root.add(buttons);

                    JScrollPane pane = new JScrollPane(root, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
                    pane.setPreferredSize(parent.getPreferredSize());
                    pane.setMinimumSize(parent.getPreferredSize());
                    this.add(pane);
                    this.revalidate();
                    this.repaint();
                });
            } catch (IOException e) {
                System.out.println(e.getMessage());
                this.infoLabel.setText("A network error occurred. Cannot load book cover");
                this.revalidate();
            }
        }).start();
    }

    private String wrapText(String text) {
        return String.format("<html><p>%s</p></html>", text);
    }

    private JButton getViewButton() {
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(this.book.getLink().toURI());
            } catch (Exception ignored) {
                this.removeAll();
                this.infoLabel.setText("An unknown error occurred. Cannot open the link");
                this.add(infoLabel);
                this.revalidate();
            }
        });
        return viewButton;
    }

    private JButton getRemoveButton() {
        JButton removeButton = new JButton("Delete Book from Database");
        removeButton.addActionListener(e -> {
            try {
                this.parent.bookRepository.remove(this.book);
                this.parent.bookListModel.removeElement(this.book);
                this.parent.groupedPanel.revalidate();
                this.dispose();
            } catch (DatabaseException ex) {
                throw new RuntimeException(ex);
            }
        });
        return removeButton;
    }
}
