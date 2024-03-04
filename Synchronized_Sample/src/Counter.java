public class Counter {
    private int count = 0;
    public synchronized void Increment() {
        count++;
    }
    public synchronized int getCount() {
        return count;
    }
}
