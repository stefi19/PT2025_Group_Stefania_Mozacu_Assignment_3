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

public class ClientsWindowController {
    @FXML private TableView<Client> clientTable;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;

    private final ClientBLL clientBLL=new ClientBLL();
    private final ObservableList<Client> clients=FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        setupTable();
        loadClients();
        clientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal!=null)
            {
                nameField.setText(newVal.getName());
                emailField.setText(newVal.getEmail());
                addressField.setText(newVal.getAddress());
            }
        });
    }
    private void setupTable()
    {
        TableColumn<Client, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Client, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        TableColumn<Client, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));
        clientTable.getColumns().addAll(idCol, nameCol, emailCol, addressCol);
        clientTable.setItems(clients);
    }
    @FXML
    public void loadClients() {
        clients.setAll(clientBLL.findAll());
    }

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
    private void clearForm()
    {
        nameField.clear();
        emailField.clear();
        addressField.clear();
    }
    private void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
