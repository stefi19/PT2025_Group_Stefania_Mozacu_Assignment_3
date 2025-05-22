package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Controller for the main window, it handles opening different management windows: Clients, Products, and Orders.
 */
public class MainController
{
    /**
     * Opens the client management window.
     */
    @FXML
    private void openClientWindow()
    {
        loadWindow("/Presentation/ClientsWindow.fxml", "Client Management");
    }
    /**
     * Opens the product management window.
     */
    @FXML
    private void openProductWindow()
    {
        loadWindow("/Presentation/ProductsWindow.fxml", "Product Management");
    }
    /**
     * Opens the order placement window.
     */
    @FXML
    private void openOrderWindow()
    {
        loadWindow("/Presentation/OrdersWindow.fxml", "Place Order");
    }
    /**
     * Loads a window from the specified FXML path with the given title
     * @param fxmlPath the path to the FXML resource
     * @param title the window title
     */
    private void loadWindow(String fxmlPath, String title)
    {
        try
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root=loader.load();
            Stage stage=new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
