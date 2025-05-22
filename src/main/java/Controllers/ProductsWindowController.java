package Controllers;

import BusinessLogicLayer.ProductBLL;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
/**
 * Controller for managing products, it allows adding, editing, deleting products and viewing them in a TableView.
 */
public class ProductsWindowController {
    @FXML private TableView<Product> productTable;
    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;

    private final ProductBLL productBLL=new ProductBLL();
    private final ObservableList<Product> products=FXCollections.observableArrayList();
    /**
     * Initializes the controller, it sets up table and loads products, also listens for row selection to populate form fields.
     */
    @FXML
    public void initialize()
    {
        setupTable();
        loadProducts();
        productTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal!=null) {
                nameField.setText(newVal.getName());
                quantityField.setText(String.valueOf(newVal.getQuantity()));
                priceField.setText(String.valueOf(newVal.getPrice()));
            }
        });
    }
    /**
     * Configures the columns of the productTable.
     */
    private void setupTable()
    {
        TableColumn<Product, Integer> idCol=new TableColumn<>("ID");
        idCol.setCellValueFactory(data->new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        TableColumn<Product, String> nameCol=new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        TableColumn<Product, Integer> qtyCol=new TableColumn<>("Quantity");
        qtyCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()).asObject());
        TableColumn<Product, Double> priceCol=new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        productTable.getColumns().addAll(idCol, nameCol, qtyCol, priceCol);
        productTable.setItems(products);
    }
    /**
     * Loads products from the database and updates table data
     */
    @FXML
    public void loadProducts() {
        products.setAll(productBLL.findAll());
    }
    /**
     * Handles adding a new product from form fields and shows error alert on failure.
     */
    @FXML
    public void handleAddProduct()
    {
        try
        {
            String name=nameField.getText();
            int qty=Integer.parseInt(quantityField.getText());
            double price=Double.parseDouble(priceField.getText());
            Product p=new Product(name, qty, price);
            productBLL.insert(p);
            loadProducts();
            clearForm();
        }
        catch (Exception e)
        {
            showAlert("Error", e.getMessage());
        }
    }
    /**
     * Handles deleting the selected product.
     */
    @FXML
    public void handleDeleteProduct()
    {
        Product selected=productTable.getSelectionModel().getSelectedItem();
        if (selected!=null)
        {
            productBLL.delete(selected);
            loadProducts();
        }
    }
    /**
     * Handles editing selected product with form data and shows error if no selection or update fails.
     */
    @FXML
    public void handleEditProduct()
    {
        Product selected=productTable.getSelectionModel().getSelectedItem();
        if (selected!=null)
        {
            try
            {
                selected.setName(nameField.getText());
                selected.setQuantity(Integer.parseInt(quantityField.getText()));
                selected.setPrice(Double.parseDouble(priceField.getText()));
                productBLL.update(selected);
                loadProducts();
                clearForm();
            } catch (Exception e) {
                showAlert("Error", "Could not update product: " + e.getMessage());
            }
        }
        else
        {
            showAlert("No Selection", "Please select a product to edit.");
        }
    }
    /**
     * Clears all input fields.
     */
    private void clearForm()
    {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
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
