
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.add(new Book("Lập trình Java", "NCT"));
        library.add(new Book("Introduction to Algorithms", "Thomas H. Cormen"));
        library.add(new Book("Clean Code", "Robert C. Martin"));
        library.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        library.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.add(new Book("1984", "George Orwell"));
        library.add(new Book("The Catcher in the Rye", "J.D. Salinger"));
        library.add(new Book("The Lord of the Rings", "J.R.R. Tolkien"));
        library.add(new Book("Pride and Prejudice", "Jane Austen"));
        library.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling"));

        //library.display();

//        library.borrow("1984");
//        library.borrow("1984");
//        library.borrow("Clean Code");
//        library.sendBack("1984");
//        library.borrow("1984");

        Thread st1 = new Student(1, library, "Lap trinh Java");
        Thread st2 = new Student(2, library, "Lap trinh Java");
        Thread st3 = new Student(3, library, "Harry Potter and the Philosopher's Stone");
        Thread st4 = new Student(4, library, "Harry Potter and the Philosopher's Stone");

        st1.start();
        st2.start();
        st3.start();
        st4.start();

        try {
            st1.join();
            st2.join();
            st3.join();
            st4.join();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}