package Model;
import java.time.LocalDateTime;
public final class Bill {
    private final int id;
    private final String clientName;
    private final String productName;
    private final int quantity;
    private final double totalPrice;
    private final LocalDateTime createdAt;
    public Bill(int id, String clientName, String productName, int quantity, double totalPrice, LocalDateTime createdAt)
    {
        this.id=id;
        this.clientName=clientName;
        this.productName=productName;
        this.quantity=quantity;
        this.totalPrice=totalPrice;
        this.createdAt=createdAt;
    }
    public int getId()
    {
        return id;
    }
    public String getClientName()
    {
        return clientName;
    }
    public String getProductName()
    {
        return productName;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public double getTotalPrice()
    {
        return totalPrice;
    }
    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }
}
