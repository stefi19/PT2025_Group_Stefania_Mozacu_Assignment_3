package BusinessLogicLayer.Validators;

import Model.Product;
/**
 * Validator for the Product name.
 * Checks that the Product name is not null or empty.
 */
public class ProductNameValidator implements Validator<Product>
{
    /**
     * Validates the name field of a Product.
     * @param p the Product to validate
     */
    @Override
    public void validate(Product p)
    {
        if (p.getName()==null||p.getName().trim().isEmpty())
        {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
    }
}
