package BusinessLogicLayer.Validators;
import Model.Product;
public class PriceValidator implements Validator<Product>
{
    @Override
    public void validate(Product p)
    {
        if (p.getPrice()<0)
        {
            throw new IllegalArgumentException("Product price must be non-negative!");
        }
    }
}
