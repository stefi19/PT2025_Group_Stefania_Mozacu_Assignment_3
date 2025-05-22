package BusinessLogicLayer.Validators;
import Model.Product;
/**
 * Validates that the price of a product is not negative.
 */
public class PriceValidator implements Validator<Product>
{
    /**
     * Validates that the product price is non-negative.
     * @param p the Product to validate
     */
    @Override
    public void validate(Product p)
    {
        if (p.getPrice()<0)
        {
            throw new IllegalArgumentException("Product price must be non-negative!");
        }
    }
}
