public class Producer extends Thread{
    private final int id;
    private final Buffer buffer;

    public Producer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int i = 0;
        while(true) {
            if (buffer.getCapacity() > buffer.getSize()) {
                try {
                    buffer.addProduct(i++, this.id);
                    sleep(Math.round(Math.random() * 10));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
