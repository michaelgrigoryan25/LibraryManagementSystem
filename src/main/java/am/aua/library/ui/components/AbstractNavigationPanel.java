package am.aua.library.ui.components;

import am.aua.library.ui.Helpers;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractNavigationPanel extends JPanel {
    public interface NavigationChangeListener {
        void onChange(AbstractNavigationPanel.Tab tab);
    }

    public enum Tab {BOOKS, USERS, SAVES}

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

    public void configureDefaultNavigation(JFrame target, boolean isAdmin) {
        this.setLayout(new GridLayout(1, 3));
        JButton booksRef = new JButton("Books");
        booksRef.addActionListener(e -> changeTab(Tab.BOOKS));
        this.add(booksRef);

        if (isAdmin) {
            JButton usersRef = new JButton("Students");
            usersRef.addActionListener(e -> changeTab(Tab.USERS));
            this.add(usersRef);
        } else {
            JButton savesRef = new JButton("Saved Books");
            savesRef.addActionListener(e -> changeTab(Tab.SAVES));
            this.add(savesRef);
        }

        JButton logoutRef = new JButton("Logout");
        logoutRef.addActionListener(e -> Helpers.handleLogout(target));
        this.add(logoutRef);
    }

    public void changeTab(Tab tab) {
        this.navigationChangeListener.onChange(tab);
    }
}
