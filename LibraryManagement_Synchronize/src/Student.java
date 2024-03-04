public class Student extends Thread{
    private long id;
    private Library lib;
    private String title;

    public Student(long id, Library lib, String title) {
        this.id = id;
        this.lib = lib;
        this.title = title;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println("-----------------------------------");
            lib.borrow(id, title);
            try {
                sleep((long) (Math.random() * 1000));
                lib.sendBack(id, title);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
