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
/**
 * Business logic for handling orders.
 */
public class OrderBLL {
    private static final Logger LOGGER=Logger.getLogger(OrderBLL.class.getName());
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;
    private final BillDAO billDAO;
    /**
     * Constructor to initialize data access to orders, products and bills.
     */
    public OrderBLL()
    {
        this.orderDAO=new OrderDAO();
        this.productDAO=new ProductDAO();
        this.billDAO=new BillDAO();
    }
    /**
     * Business logic for orders.
     * Handles order creation, stock update, and bill generation.
     * @param order that needs to be created
     * @param clientName client's name for the bill
     * @param productName product's name for the bill
     */
    public Bill createOrder(Order order, String clientName, String productName){
        try{
            Product product=productDAO.findById(order.getProductId());
            if(product==null || product.getQuantity()<order.getQuantity()){
                LOGGER.warning("Not enough stock or product not found");
                return null;
            }
            product.setQuantity(product.getQuantity()-order.getQuantity());
            productDAO.update(product);
            orderDAO.insert(order);
            double total=order.getQuantity()*product.getPrice();
            Bill bill=new Bill(0,clientName,productName,order.getQuantity(),total,LocalDateTime.now());
            billDAO.insert(bill);
            LOGGER.info("Order created successfully");
            return bill;
        }catch(Exception e){
            LOGGER.log(Level.SEVERE,"Error creating order",e);
            return null;
        }
    }
}
