package Model;
import java.time.LocalDateTime;
/**
 * Immutable Bill record representing an entry in the Log table.
 * Contains information about the order such as client, product, quantity, total price, and timestamp when the bill was created.
 * @param id          unique identifier of the bill
 * @param clientName  name of the client who made the order
 * @param productName name of the ordered product
 * @param quantity    quantity of the product ordered
 * @param totalPrice  total price for the ordered quantity
 * @param createdAt   timestamp when the bill was created
 */
public record Bill(int id, String clientName, String productName, int quantity, double totalPrice, LocalDateTime createdAt) {
}
