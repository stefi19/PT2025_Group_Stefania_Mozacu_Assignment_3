package BusinessLogicLayer;
import DataAccessObject.BillDAO;
import DataAccessObject.OrderDAO;
import DataAccessObject.ProductDAO;
import Model.Bill;
import Model.Order;
import Model.Product;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
public class OrderBLL {
    private static final Logger LOGGER=Logger.getLogger(OrderBLL.class.getName());
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final BillDAO billDAO;
    public OrderBLL()
    {
        this.orderDAO=new OrderDAO();
        this.productDAO=new ProductDAO();
        this.billDAO=new BillDAO();
    }
    public boolean createOrder(Order order, String clientName, String productName)
    {
        try
        {
            Product product=productDAO.findById(order.getProductId());
            if(product==null||product.getQuantity()<order.getQuantity())
            {
                LOGGER.warning("Not enough stock or product not found");
                return false;
            }
            product.setQuantity(product.getQuantity()-order.getQuantity());
            productDAO.update(product);
            orderDAO.insert(order);
            double total=order.getQuantity()*product.getPrice();
            Bill bill=new Bill(0,clientName,productName,order.getQuantity(),total,LocalDateTime.now());
            billDAO.insert(bill);
            LOGGER.info("Order created successfully");
            return true;
        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE, "Error creating order", e);
            return false;
        }
    }
}
