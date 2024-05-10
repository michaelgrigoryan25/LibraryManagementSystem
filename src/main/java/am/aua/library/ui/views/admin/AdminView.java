package am.aua.library.ui.views.admin;

import am.aua.library.ui.components.*;
import am.aua.library.ui.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * This class represents the main admin view.
 */
public final class AdminView extends AbstractPage {
    private static AdminView instance;

    @Override
    public void setupRedirects() {
    }

    /**
     * Gets the singleton instance of AdminView.
     *
     * @return The singleton instance of AdminView.
     */
    public static AdminView getInstance() {
        return AdminView.instance;
    }

    /**
     * Constructs a new AdminView.
     */
    public AdminView() {
        super("Admin View");
    }

    @Override
    protected void setup() {
        AdminView.instance = this;
        this.setLayout(new GridLayout(6, 1));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                AdminView.this.setLocationRelativeTo(instance);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                AdminView.this.setLocationRelativeTo(instance);
            }
        });
    }

    @Override
    protected void addComponents() {
        JButton viewBooksTableButton = new Button("View Books");
        viewBooksTableButton.addActionListener(e -> {
            this.setVisible(false);
            new BookTableView();
        });
        this.add(viewBooksTableButton);

        JButton viewLeasersTableButton = new Button("View Leasers");
        viewLeasersTableButton.addActionListener(e -> {
            this.setVisible(false);
            new LeaserTableView();
        });
        this.add(viewLeasersTableButton);

        JButton addLeaserButton = new Button("New Leaser Registration");
        addLeaserButton.addActionListener(e -> {
            this.setVisible(false);
            new LeaserRegistrationView();
        });
        this.add(addLeaserButton);

        JButton addBooksButton = new Button("Add a New Book");
        addBooksButton.addActionListener(e -> {
            this.setVisible(false);
            new AddBookView();
        });
        this.add(addBooksButton);

        JButton currentLeases = new Button("See Currently Leased Books");
        currentLeases.addActionListener(e -> {
            this.setVisible(false);
            new CurrentLeases();
        });
        this.add(currentLeases);

        JButton returnBookButton = new Button("Return a Book");
        returnBookButton.addActionListener(e -> {
            this.setVisible(false);
            new ReturnBookView();
        });
        this.add(returnBookButton);
    }
}
