package Controllers;

import Model.Bill;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;
/**
 * Controller for the Bill (receipt) window.
 * Displays details of a completed order as a receipt.
 */
public class BillWindowController {
    @FXML private Label clientNameLabel;
    @FXML private Label productNameLabel;
    @FXML private Label quantityLabel;
    @FXML private Label totalPriceLabel;
    @FXML private Label dateLabel;

    private Bill bill;
    /**
     * Sets the bill object to display and updates the UI.
     * @param bill the Bill instance to display
     */
    public void setBill(Bill bill){
        this.bill=bill;
        displayBillDetails();
    }
    /**
     * Displays the bill details in the UI labels.
     */
    private void displayBillDetails(){
        if(bill!=null){
            clientNameLabel.setText("Client: "+bill.clientName());
            productNameLabel.setText("Product: "+bill.productName());
            quantityLabel.setText("Quantity: "+bill.quantity());
            totalPriceLabel.setText(String.format("Total: $%.2f",bill.totalPrice()));
            DateTimeFormatter fmt=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dateLabel.setText("Date: "+bill.createdAt().format(fmt));
        }
    }
    /**
     * Closes the bill window.
     */
    @FXML
    public void closeWindow(){
        Stage stage=(Stage)clientNameLabel.getScene().getWindow();
        stage.close();
    }
}
