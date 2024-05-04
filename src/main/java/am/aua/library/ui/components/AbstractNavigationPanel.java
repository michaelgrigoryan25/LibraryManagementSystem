package am.aua.library.ui.components;

import javax.swing.*;

public abstract class AbstractNavigationPanel extends JPanel {
    public interface NavigationChangeListener {
        void onChange(AbstractNavigationPanel.Tab tab);
    }

    public enum Tab {BOOKS, USERS}

    private Tab currentTab;
    private final NavigationChangeListener navigationChangeListener;

    public AbstractNavigationPanel(NavigationChangeListener listener) {
        super();
        this.navigationChangeListener = listener;
    }

    public Tab getCurrentTab() {
        return currentTab;
    }

    public void changeTab(Tab tab) {
        this.currentTab = tab;
        this.navigationChangeListener.onChange(tab);
    }
}
