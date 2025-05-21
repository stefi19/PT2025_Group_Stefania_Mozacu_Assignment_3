package BusinessLogicLayer;
import BusinessLogicLayer.Validators.*;
import DataAccessObject.ClientDAO;
import Model.Product;
import java.util.List;
import DataAccessObject.ProductDAO;
public class ProductBLL {
    private final ProductDAO productDAO;
    private final List<Validator<Product>> validators;
    public ProductBLL()
    {
        productDAO=new ProductDAO();
        validators=List.of(new ProductNameValidator(), new PriceValidator(), new QuantityValidator());
    }
    public Product insert(Product product)
    {
        validators.forEach(v->v.validate(product));
        return productDAO.insert(product);
    }
    public Product update(Product product)
    {
        validators.forEach(v->v.validate(product));
        return productDAO.update(product);
    }
    public List<Product> findAll()
    {
        return productDAO.findAll();
    }
    public Product findById(int id)
    {
        return productDAO.findById(id);
    }
    public void delete(Product product)
    {
        productDAO.delete(product);
    }
}
