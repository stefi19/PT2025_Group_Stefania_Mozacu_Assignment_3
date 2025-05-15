package Model;

public class Order {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    public Order(){}; // required for reflection
    public Order(int clientId, int productId, int quantity)
    {
        this.clientId=clientId;
        this.productId=productId;
        this.quantity=quantity;
    }// used for inserting into the database
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
