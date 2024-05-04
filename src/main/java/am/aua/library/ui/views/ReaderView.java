package am.aua.library.ui.views;

import am.aua.library.ui.components.*;

import javax.swing.*;
import java.awt.*;

class ReaderViewNavigationPanel extends AbstractNavigationPanel {
    public ReaderViewNavigationPanel(AbstractPage page, NavigationChangeListener listener) {
        super(page, listener);
        configureDefaultNavigation(this.getPage(), false);
    }
}

class ReaderViewNavigationChangeListener implements AbstractNavigationPanel.NavigationChangeListener {
    private final ReaderView view;

    public ReaderViewNavigationChangeListener(ReaderView view) {
        this.view = view;
    }

    @Override
    public void onChange(AbstractNavigationPanel.Tab tab) {
        boolean flag = tab.equals(AbstractNavigationPanel.Tab.BOOKS);
        view.bookListPanel.setVisible(flag);
        view.userListPanel.setVisible(!flag);
    }
}

public class ReaderView extends AbstractPage {
    private final Long id;

    private ReaderViewNavigationPanel readerViewNavigationPanel;
    protected JPanel root;
    protected JPanel bookListPanel;
    protected JPanel userListPanel;

    public ReaderView(Long id) {
        super("User View");
        this.id = id;
    }

    @Override
    protected synchronized void setup() {
        this.setLayout(new BorderLayout());
        // Setting up the shared navigation panel for the Admin view
        this.readerViewNavigationPanel = new ReaderViewNavigationPanel(this, new ReaderViewNavigationChangeListener(this));
        // This is the root panel where all the other panels are going to be added
        this.root = new JPanel();
        // these are the children of contentPanel
        this.bookListPanel = new JPanel();
        this.userListPanel = new JPanel();
    }

    @Override
    protected synchronized void addComponents() {
        this.bookListPanel.add(new Text("Books"));
        this.root.add(this.bookListPanel);

        this.userListPanel.add(new Text("Users"));
        this.userListPanel.setVisible(false);
        this.root.add(this.userListPanel);

        this.add(this.readerViewNavigationPanel, BorderLayout.NORTH);
        this.add(this.root, BorderLayout.CENTER);
    }
}
