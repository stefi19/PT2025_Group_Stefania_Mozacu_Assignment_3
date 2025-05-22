package Model;
/**
 * Represents an order made by a client for a product with a specified quantity.
 * Includes constructors for new orders (without id) and orders loaded from the database.
 * Provides getters and setters for all properties.
 */
public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    /**
     * Default no-arg constructor required for reflection.
     */
    public Order(){}; // required for reflection
    /**
     * Constructor for creating a new order before insertion (without id).
     * @param clientId id of the client making the order
     * @param productId id of the product ordered
     * @param quantity quantity ordered
     */
    public Order(int clientId, int productId, int quantity)
    {
        this.clientId=clientId;
        this.productId=productId;
        this.quantity=quantity;
    }// used for inserting into the database
    /**
     * Constructor for orders retrieved from the database (includes id).
     * @param id unique identifier of the order
     * @param clientId id of the client making the order
     * @param productId id of the product ordered
     * @param quantity quantity ordered
     */
    public Order(int id, int clientId, int productId, int quantity)
    {
        this.id=id;
        this.clientId=clientId;
        this.productId=productId;
        this.quantity=quantity;
    }// used for reading from the database
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getClientId()
    {
        return clientId;
    }
    public void setClientId(int clientId)
    {
        this.clientId=clientId;
    }
    public int getProductId()
    {
        return productId;
    }
    public void setProductId(int productId)
    {
        this.productId=productId;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
}
