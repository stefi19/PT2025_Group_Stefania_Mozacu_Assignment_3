package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application class that loads and shows the main window.
 */
public class HelloApplication extends Application
{
    /**
     * Loads the main window FXML and displays it
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/Presentation/MainWindow.fxml"));
        Scene scene=new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/Presentation/style.css").toExternalForm());
        stage.setTitle("Home - Orders Management");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Main method, launches the JavaFX application.
     * @param args Command line arguments (unused)
     */
    public static void main(String[] args) {
        launch();
    }
}
