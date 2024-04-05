package application;

public class Item {
    private String id;
    private String name;
    

    private double price;

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }


    public Item(String id, String name) {
        this.id = id;
        this.name = name;
        
    }
    @Override
    public String toString() {
        return name; 
    }
}
