package am.aua.library.ui.views.admin;

import am.aua.library.ui.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public final class AdminView extends AbstractPage {
    private static AdminView instance;

    @Override
    public void setupRedirects() {
    }

    public static AdminView getInstance() {
        return AdminView.instance;
    }

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
        JButton viewBooksTableButton = new JButton("View Books");
        viewBooksTableButton.addActionListener(e -> {
            this.setVisible(false);
            new BookTableView();
        });
        this.add(viewBooksTableButton);

        JButton viewLeasersTableButton = new JButton("View Leasers");
        viewLeasersTableButton.addActionListener(e -> {
            this.setVisible(false);
            new LeaserTableView();
        });
        this.add(viewLeasersTableButton);

        JButton addLeaserButton = new JButton("New Leaser Registration");
        addLeaserButton.addActionListener(e -> {
            this.setVisible(false);
            new LeaserRegistrationView();
        });
        this.add(addLeaserButton);

        JButton addBooksButton = new JButton("Add a New Book");
        addBooksButton.addActionListener(e -> {
            this.setVisible(false);
            new AddBookView();
        });
        this.add(addBooksButton);

        JButton currentLeases = new JButton("See Currently Leased Books");
        currentLeases.addActionListener(e -> {
            this.setVisible(false);
            new CurrentLeases();
        });
        this.add(currentLeases);

        JButton returnBookButton = new JButton("Return a Book");
        returnBookButton.addActionListener(e -> {
            this.setVisible(false);
            new ReturnBookView();
        });
        this.add(returnBookButton);
    }
}
