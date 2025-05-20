package BusinessLogicLayer.Validators;

import Model.Product;

public class ProductNameValidator implements Validator<Product>
{
    @Override
    public void validate(Product p)
    {
        if (p.getName()==null||p.getName().trim().isEmpty())
        {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
    }
}
