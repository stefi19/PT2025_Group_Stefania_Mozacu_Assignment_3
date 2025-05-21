package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController
{
    @FXML
    private void openClientWindow()
    {
        loadWindow("/Presentation/ClientsWindow.fxml", "Client Management");
    }
    @FXML
    private void openProductWindow()
    {
        loadWindow("/Presentation/ProductsWindow.fxml", "Product Management");
    }
    @FXML
    private void openOrderWindow()
    {
        loadWindow("/Presentation/OrdersWindow.fxml", "Place Order");
    }
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
