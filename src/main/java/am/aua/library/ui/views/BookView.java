package am.aua.library.ui.views;

import am.aua.library.database.DatabaseException;
import am.aua.library.models.Book;
import am.aua.library.repositories.BookRepositoryImpl;
import am.aua.library.ui.Helpers;
import am.aua.library.ui.components.AbstractPage;
import am.aua.library.ui.components.Text;
import org.javatuples.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BookView extends AbstractPage {
    private final static ImageIcon viewIcon = Helpers.getRescaledImageIcon("eye.png", 20, 20);
    private final static ImageIcon editIcon = Helpers.getRescaledImageIcon("pen.png", 20, 20);
    private final static ImageIcon removeIcon = Helpers.getRescaledImageIcon("remove.png", 20, 20);
    private JLabel loader;
    private Book book;
    private AdminView parent;

    private JLabel imageHolder;
    private BookRepositoryImpl bookRepository;

    public BookView(Book book, AdminView parent) {
        super("Book View",
                new Pair<>("parent", parent),
                new Pair<>("book", book),
                new Pair<>("loading", true));
    }

    @Override
    protected void setup() {
        this.setLayout(new BorderLayout());
        this.book = (Book) this.getInternalState().get("book");
        this.parent = (AdminView) this.getInternalState().get("parent");
        this.loader = new Text("Loading...", Text.Size.LG);
        this.loader.setHorizontalAlignment(SwingConstants.CENTER);
        this.bookRepository = new BookRepositoryImpl();
    }

    @Override
    protected void addComponents() {
        this.add(this.loader, BorderLayout.CENTER);

        new Thread(() -> {
            try {
                Image image = ImageIO.read(this.book.getCover());
                SwingUtilities.invokeLater(() -> {
                    this.remove(this.loader);

                    this.setLayout(new GridLayout(3, 1));

                    JPanel buttons = new JPanel();
                    JButton viewButton = getViewButton();
                    buttons.add(viewButton);

                    buttons.add(new JButton("Edit"));

                    JButton removeButton = getRemoveButton();
                    buttons.add(removeButton);

                    JPanel bookInfo = new JPanel();
                    bookInfo.setLayout(new GridLayout(1, 2));
                    JLabel cover = new JLabel(new ImageIcon(image));
                    bookInfo.add(cover);
                    JPanel bookTextInfo = new JPanel();
                    bookTextInfo.setLayout(new GridLayout(2, 1));
                    bookTextInfo.add(new Text(book.getTitle()), FlowLayout.LEFT);
                    bookInfo.add(bookTextInfo);

                    this.add(bookInfo, BorderLayout.WEST);
                    this.add(buttons, BorderLayout.CENTER);
                    this.revalidate();
                    this.repaint();
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private JButton getViewButton() {
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(this.book.getLink().toURI());
            } catch (Exception ignored) {
            }
        });
        return viewButton;
    }

    private JButton getRemoveButton() {
        JButton removeButton = new JButton("Delete Book from Database");
        removeButton.addActionListener(e -> {
            try {
                bookRepository.remove(this.book);
                this.parent.bookListModel.removeElement(this.book);
                this.parent.groupedPanel.revalidate();
                this.dispose();
            } catch (DatabaseException ex) {
                throw new RuntimeException(ex);
            }
        });
        return removeButton;
    }
}
