package BusinessLogicLayer.Validators;
import Model.Product;
/**
 * Validator for product quantity, to make sure it is zero or positive.
 */
public class QuantityValidator implements Validator<Product>
{
    /**
     * Validates the product quantity.
     * @param p the product to validate
     */
    @Override
    public void validate(Product p)
    {
        if (p.getQuantity()<0)
        {
            throw new IllegalArgumentException("Product quantity must be non-negative!");
        }
    }
}
