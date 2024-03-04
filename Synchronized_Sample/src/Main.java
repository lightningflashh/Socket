
public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread( () -> {
                for (int i = 0; i < 10000; i++) {
                    counter.Increment();
                }
            }
        );

       Thread t2 = new Thread( () -> {
            for (int i = 0; i < 10000; i++) {
                counter.Increment();
            }
        }
       );

       t1.start();
       t2.start();

       try {
           t1.join();
           t2.join();
       } catch (Exception exception) {
           exception.printStackTrace();
       }

        System.out.println("Counter: " + counter.getCount());
    }
}

