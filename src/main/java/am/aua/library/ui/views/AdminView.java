package am.aua.library.ui.views;

import am.aua.library.ui.components.*;

import javax.swing.*;
import java.awt.*;

class NavigationPanel extends AbstractNavigationPanel {
    public NavigationPanel(NavigationChangeListener listener) {
        super(listener);

        this.setLayout(new GridLayout(1, 2));
        JButton booksRef = new JButton("Books");
        booksRef.addActionListener(e -> changeTab(Tab.BOOKS));
        this.add(booksRef);

        JButton usersRef = new JButton("Students");
        usersRef.addActionListener(e -> changeTab(Tab.USERS));
        this.add(usersRef);
    }
}

public class AdminView extends AbstractPage {
    private final Long id;

    private NavigationPanel navigationPanel;
    private JPanel contentPanel;
    private JPanel bookListPanel;
    private JPanel userListPanel;

    public AdminView(Long id) {
        super("Admin View");
        this.id = id;
    }

    @Override
    protected synchronized void setup() {
        this.setLayout(new BorderLayout());
        this.navigationPanel = new NavigationPanel(tab -> {
            boolean flag = tab.equals(AbstractNavigationPanel.Tab.BOOKS);
            AdminView.this.bookListPanel.setVisible(flag);
            AdminView.this.userListPanel.setVisible(!flag);
        });

        this.contentPanel = new JPanel();
        // these are the children of contentPanel
        this.bookListPanel = new JPanel();
        this.bookListPanel.add(new Text("Books"));
        this.userListPanel = new JPanel();
        this.userListPanel.add(new Text("Users"));
    }

    @Override
    protected synchronized void addComponents() {
        this.contentPanel.add(this.bookListPanel);
        this.userListPanel.setVisible(false);
        this.contentPanel.add(this.userListPanel);
        this.contentPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        this.add(this.navigationPanel, BorderLayout.NORTH);
        this.add(this.contentPanel, BorderLayout.CENTER);
    }
}
