package BusinessLogicLayer;
import BusinessLogicLayer.Validators.*;
import DataAccessObject.ClientDAO;
import Model.Product;
import java.util.List;
import DataAccessObject.ProductDAO;
/**
 * Business logic layer for managing products in the database
 */
public class ProductBLL {
    private final ProductDAO productDAO;
    private final List<Validator<Product>> validators;
    /**
     * Constructor that initializes the data access and product validators
     */
    public ProductBLL()
    {
        productDAO=new ProductDAO();
        validators=List.of(new ProductNameValidator(), new PriceValidator(), new QuantityValidator());
    }
    /**
     * If validated, it inserts a product in the database.
     * @param product to insert
     * @return inserted product or null if it was not validated
     */
    public Product insert(Product product)
    {
        validators.forEach(v->v.validate(product));
        return productDAO.insert(product);
    }
    /**
     * If validated, it updates a product in the database.
     * @param product to insert
     * @return updated product or null if it was not validated
     */
    public Product update(Product product)
    {
        validators.forEach(v->v.validate(product));
        return productDAO.update(product);
    }
    /**
     * Gets all products from database
     * @return the list of products
     */
    public List<Product> findAll()
    {
        return productDAO.findAll();
    }
    /**
     * Finds a product by its ID.
     * @param id product's id
     * @return product or null if not found
     */
    public Product findById(int id)
    {
        return productDAO.findById(id);
    }
    /**
     * Deletes a product from the database.
     * @param product to delete
     */
    public void delete(Product product)
    {
        productDAO.delete(product);
    }
}
