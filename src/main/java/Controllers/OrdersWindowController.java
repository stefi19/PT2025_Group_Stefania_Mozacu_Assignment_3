package Controllers;

import BusinessLogicLayer.OrderBLL;
import DataAccessObject.ClientDAO;
import DataAccessObject.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
/**
 * Controller for the Order placement window, it allows selecting client and product, entering quantity, and placing orders.
 */
public class OrdersWindowController {
    @FXML private ComboBox<Client> clientBox;
    @FXML private ComboBox<Product> productBox;
    @FXML private TextField quantityField;
    @FXML private Label messageLabel;
    private final OrderBLL orderBLL=new OrderBLL();
    private final ClientDAO clientDAO=new ClientDAO();
    private final ProductDAO productDAO=new ProductDAO();
    /**
     * Initializes the ComboBoxes with clients and products from the database.
     */
    @FXML
    public void initialize()
    {
        clientBox.setItems(FXCollections.observableArrayList(clientDAO.findAll()));
        productBox.setItems(FXCollections.observableArrayList(productDAO.findAll()));
    }
    /**
     * Handles the place order action.
     * Validates inputs, creates the order via business logic layer, and shows success or error messages.
     */
    @FXML
    public void handlePlaceOrder()
    {
        try
        {
            Client client=clientBox.getValue();
            Product product=productBox.getValue();
            int quantity=Integer.parseInt(quantityField.getText());
            if (client==null||product==null)
            {
                messageLabel.setText("Select both client and product.");
                return;
            }
            Order order=new Order(client.getId(), product.getId(), quantity);
            boolean success=orderBLL.createOrder(order, client.getName(), product.getName());
            if (success)
            {
                messageLabel.setText("Order placed successfully.");
                productBox.setItems(FXCollections.observableArrayList(productDAO.findAll()));
            }
            else
            {
                messageLabel.setText("Not enough stock or product unavailable.");
            }
        } catch (Exception e) {
            messageLabel.setText("Invalid input.");
        }
    }
}
