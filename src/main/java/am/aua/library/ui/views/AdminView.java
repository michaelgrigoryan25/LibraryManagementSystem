package am.aua.library.ui.views;

import am.aua.library.ui.components.*;

import javax.swing.*;
import java.awt.*;

public final class AdminView extends AbstractPage {
    public AdminView() {
        super("Admin View");
    }

    @Override
    protected void setup() {
        this.setLayout(new GridLayout(3, 1));
    }

    @Override
    protected void addComponents() {
        JButton viewBooksTableButton = new JButton("View Books");
        viewBooksTableButton.addActionListener(e -> new BookTableView());
        this.add(viewBooksTableButton);

        JButton addLeaserButton = new JButton("New Leaser Registration");
        addLeaserButton.addActionListener(e -> new LeaserRegistrationView());
        this.add(addLeaserButton);

        JButton viewLeasersTableButton = new JButton("View Leasers");
        viewLeasersTableButton.addActionListener(e -> new LeaserTableView());
        this.add(viewBooksTableButton);
    }
}
