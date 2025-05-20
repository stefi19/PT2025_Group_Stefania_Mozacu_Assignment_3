package BusinessLogicLayer.Validators;
import Model.Product;
public class QuantityValidator implements Validator<Product>
{
    @Override
    public void validate(Product p)
    {
        if (p.getQuantity()<0)
        {
            throw new IllegalArgumentException("Product quantity must be non-negative!");
        }
    }
}
