import java.util.ArrayList;

public class Buffer {
    private final int capacity;
    //Contain products
    private final ArrayList<Integer> products;

    public Buffer(int capacity) {
        this.capacity = capacity;
        products = new ArrayList<>();
    }

    public void addProduct(int product, int producerId) {
        System.out.println("Producer " + producerId + " has just added " + product);
        products.add(product);
        System.out.println("Stock: " + products.size());
    }

    public void removeProduct(int customerId) {
        System.out.println("Customer: " + customerId + "has bought item " + products.get(0));
        products.remove(0);
        System.out.println("Stock: " + products.size());
    }

    public int getSize() {
        return this.products.size();
    }

    public int getCapacity() {
        return capacity;
    }
}

