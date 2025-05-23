package Controllers;

import BusinessLogicLayer.ClientBLL;
import Model.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller for managing clients in the graphical user interface
 * Allows adding, deleting, editing, and viewing clients
 */
public class ClientsWindowController {
    @FXML private TableView<Client> clientTable;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    private final ClientBLL clientBLL=new ClientBLL();
    private final ObservableList<Client> clients=FXCollections.observableArrayList();
    /**
     * Initializes the controller, it sets up table and loads clients, also listens for row selection to populate form fields.
     */
    @FXML
    public void initialize()
    {
        loadClients();
        setupTable();
        clientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal!=null)
            {
                nameField.setText(newVal.getName());
                emailField.setText(newVal.getEmail());
                addressField.setText(newVal.getAddress());
            }
        });
    }
    /**
     * Configures table columns and the client properties
     */
    private void setupTable()
    {
        ReflectionTable.populateTable(clientTable, clients);
    }
    /**
     * Loads all clients from business logic layer into the table
     */
    @FXML
    public void loadClients() {
        clients.setAll(clientBLL.findAll());
    }
    /**
     * Handles adding a new client from form fields and shows error alert on failure.
     */
    @FXML
    public void handleAddClient()
    {
        try
        {
            Client c=new Client(nameField.getText(), emailField.getText(), addressField.getText());
            clientBLL.insert(c);
            loadClients();
            clearForm();
        } catch (Exception e)
        {
            showAlert("Error", e.getMessage());
        }
    }
    /**
     * Handles deleting selected client from table.
     */
    @FXML
    public void handleDeleteClient()
    {
        Client selected=clientTable.getSelectionModel().getSelectedItem();
        if (selected!=null)
        {
            clientBLL.delete(selected);
            loadClients();
        }
    }
    /**
     * Handles editing selected client with form data and shows error if no selection or update fails.
     */
    @FXML
    public void handleEditClient()
    {
        Client selected=clientTable.getSelectionModel().getSelectedItem();
        if (selected!=null)
        {
            try
            {
                selected.setName(nameField.getText());
                selected.setEmail(emailField.getText());
                selected.setAddress(addressField.getText());
                clientBLL.update(selected);
                loadClients();
                clearForm();
            } catch (Exception e)
            {
                showAlert("Error", "Could not update client: " + e.getMessage());
            }
        } else
        {
            showAlert("No Selection", "Please select a client to edit.");
        }
    }
    /**
     * Clears all form text fields
     */
    private void clearForm()
    {
        nameField.clear();
        emailField.clear();
        addressField.clear();
    }
    /**
     * Shows an error alert dialog.
     * @param title of the alert window.
     * @param message to show inside the alert.
     */
    private void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
