package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
/**
 * Controller class for handling UI interactions in the hello view.
 */
public class HelloController {
    @FXML
    private Label welcomeText;
    /**
     * Event handler for the hello button click.
     * Updates the welcomeText label with a greeting text
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}