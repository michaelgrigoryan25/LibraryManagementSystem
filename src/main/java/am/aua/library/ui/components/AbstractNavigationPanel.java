package am.aua.library.ui.components;

import am.aua.library.models.Book;
import am.aua.library.ui.Helpers;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractNavigationPanel extends JPanel {
    public Tab getCurrentTab() {
        return currentTab;
    }

    public interface NavigationChangeListener {
        void onChange(AbstractNavigationPanel.Tab tab);
    }

    public enum Tab {BOOKS, USERS, RENTED, ADD}

    private Tab currentTab;
    private final AbstractPage page;
    private final NavigationChangeListener navigationChangeListener;

    public AbstractNavigationPanel(AbstractPage page, NavigationChangeListener listener) {
        super();
        this.page = page;
        this.navigationChangeListener = listener;
    }

    public AbstractPage getPage() {
        return this.page;
    }

    protected void setCurrentTab(Tab tab) {
        this.currentTab = tab;
    }

    public void configureDefaultNavigation(JFrame target, boolean isAdmin) {
        this.setLayout(new GridLayout(1, 3));
        JButton booksRef = new JButton("Books");
        booksRef.addActionListener(e -> changeTab(Tab.BOOKS));
        this.add(booksRef);

        if (isAdmin) {
            this.setLayout(new GridLayout(1, 4));

            JButton usersRef = new JButton("Students");
            usersRef.addActionListener(e -> changeTab(Tab.USERS));
            this.add(usersRef);

            JButton addBookRef = new JButton("Add Book");
            addBookRef.addActionListener(e -> changeTab(Tab.ADD));
            this.add(addBookRef);
        } else {
            JButton savesRef = new JButton("Rented Books");
            savesRef.addActionListener(e -> changeTab(Tab.RENTED));
            this.add(savesRef);
        }

        JButton logoutRef = new JButton("Logout");
        logoutRef.addActionListener(e -> Helpers.handleLogout(target));
        this.add(logoutRef);
    }

    public void changeTab(Tab tab) {
        if (this.currentTab != tab) {
            this.setCurrentTab(tab);
            this.navigationChangeListener.onChange(tab);
        }
    }
}
