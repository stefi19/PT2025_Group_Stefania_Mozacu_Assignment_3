package Model;
/**
 * Represents a product with id, name, quantity, and price.
 * Provides constructors for insertion (without id) and reading from database (with id).
 * Includes getters and setters for all fields.
 */
public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    /**
     * No-argument constructor required for reflection.
     */
    public Product() {} // required for reflection
    /**
     * Constructor for creating a new product before database insertion.
     * @param name product name
     * @param quantity product quantity
     * @param price product price
     */
    public Product(String name, int quantity, double price)
    {
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }// used for inserting into the database
    /**
     * Constructor for product objects read from the database.
     * @param id product id
     * @param name product name
     * @param quantity product quantity
     * @param price product price
     */
    public Product(int id, String name, int quantity, double price)
    {
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }// used for reading from the database
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double price)
    {
        this.price=price;
    }
    @Override
    public String toString() {
        return name + " - " + quantity + " pcs";
    }

}
