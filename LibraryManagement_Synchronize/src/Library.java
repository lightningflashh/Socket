import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public synchronized void add(Book book) {
        books.add(book);
        System.out.println("A book: " + book.getTitle() + " has just added!");
    }

    public synchronized void borrow(long id, String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("A book " + title + " has been borrowed by " + id);
                    return;
                }
            }
        }
        System.out.println(id + " can't borrow this book");
    }

    public synchronized void sendBack(long id, String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("A book " + title + " has been returned by " + id);
                    return;
                }
            }
        }
        System.out.println("The library doesn't have this one" );
    }

    public void display() {
        System.out.println("Library books: "); {
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
    }
}
