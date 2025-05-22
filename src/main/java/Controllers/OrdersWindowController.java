package Controllers;

import BusinessLogicLayer.OrderBLL;
import DataAccessObject.ClientDAO;
import DataAccessObject.ProductDAO;
import Model.Bill;
import Model.Client;
import Model.Order;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
     * Controller for placing orders.
     * Manages UI interaction for order creation and receipt display.
     */
    @FXML
    public void handlePlaceOrder(){
        try{
            Client client=clientBox.getValue();
            Product product=productBox.getValue();
            int quantity=Integer.parseInt(quantityField.getText());
            if(client==null || product==null){
                messageLabel.setText("Select both client and product.");
                return;
            }
            Order order=new Order(client.getId(),product.getId(),quantity);
            Bill bill=orderBLL.createOrder(order,client.getName(),product.getName());
            if(bill!=null){
                messageLabel.setText("Order placed successfully.");
                productBox.setItems(FXCollections.observableArrayList(productDAO.findAll()));
                showBillWindow(bill);
            } else {
                messageLabel.setText("Not enough stock or product unavailable.");
            }
        } catch(Exception e){
            messageLabel.setText("Invalid input.");
        }
    }
    /**
     * Opens a new modal window showing the order receipt.
     * @param bill the Bill object to display in the receipt window
     */
    private void showBillWindow(Bill bill){
        try{
            var loader=new FXMLLoader(getClass().getResource("/Presentation/BillWindow.fxml"));
            Parent root=loader.load();
            BillWindowController controller=loader.getController();
            controller.setBill(bill);

            Stage stage=new Stage();
            stage.setTitle("Receipt");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
