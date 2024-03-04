// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(50);
        Producer p1 = new Producer(333, buffer);
        Consumer c1 = new Consumer(123, buffer);

        p1.start();

        c1.start();
    }
}