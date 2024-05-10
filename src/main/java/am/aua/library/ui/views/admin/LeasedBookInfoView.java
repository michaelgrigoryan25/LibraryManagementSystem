package am.aua.library.ui.views.admin;


import am.aua.library.database.DatabaseException;
import am.aua.library.models.Leaser;
import am.aua.library.repositories.BookRepository;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.repositories.LeaserRepository;
import am.aua.library.repositories.LeaserRepositoryImpl;
import am.aua.library.ui.components.AbstractPage;

import javax.swing.*;
import java.awt.*;

public class LeasedBookInfoView {

    private LeaserRepository leaserRepository;
    private BookRepository bookRepository;

    private Long bookId;
    private Long userId;

    public LeasedBookInfoView(AbstractPage parent, Long bookId, Long userId) {
        setup(bookId, userId);
        addComponents(parent);
    }

    public void setup(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;

        leaserRepository = new LeaserRepositoryImpl();
        bookRepository = new BookRepositoryImpl();
    }

    public void addComponents(AbstractPage parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());

        JButton deleteConfirm = new JButton("Yes, Delete");
        deleteConfirm.addActionListener(e -> {
            try {
                bookRepository.giveBackById(bookId, userId);
            } catch (DatabaseException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(new CurrentLeases(), "Leaser deleted successfully");
        });
        panel.add(new JLabel("Are you sure that you want to delete the lease?"));
        panel.add(deleteConfirm);

        JOptionPane.showMessageDialog(parent, panel);

        new CurrentLeases();
    }
}
