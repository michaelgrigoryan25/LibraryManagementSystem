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

        this.setLayout(new GridLayout(5, 1));
    }

    @Override
    protected void addComponents() {
        JButton viewBooksTableButton = new JButton("View Books");
        viewBooksTableButton.addActionListener(e -> new BookTableView());
        this.add(viewBooksTableButton);

        JButton addLeaserButton = new JButton("New Leaser Registration");
        addLeaserButton.addActionListener(e -> new LeaserRegistrationView(this));
        this.add(addLeaserButton);

        JButton viewLeasersTableButton = new JButton("View Leasers");
        viewLeasersTableButton.addActionListener(e -> new LeaserTableView());
        this.add(viewLeasersTableButton);

        JButton addBooksButton = new JButton("Add Books");
        addBooksButton.addActionListener(e -> new AddBooksView());
        this.add(addBooksButton);

        JButton findLeaserButton = new JButton("Find & Update Leaser");
        findLeaserButton.addActionListener(e -> new LeaserView());
        this.add(findLeaserButton);
    }
}
