package Model;
import java.time.LocalDateTime;

public record Bill(int id, String clientName, String productName, int quantity, double totalPrice,
                   LocalDateTime createdAt) {
}
