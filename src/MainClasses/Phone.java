package MainClasses;

public class Phone{
    private String modelName;
    private String brand;
    private double price;
    private int stock;
   private int id;

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

@Override
public String toString() {
    return brand + " " + modelName; // Example: Apple iPhone 15
}


   
    public Phone(String modelName, String brand, double price, int stock) {
        this.modelName = modelName;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public String getModelName() {
        return modelName;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public void reduceStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        }
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }
}
