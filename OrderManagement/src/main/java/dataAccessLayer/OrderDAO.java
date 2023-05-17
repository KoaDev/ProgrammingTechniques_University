package dataAccessLayer;

import model.Order;

import java.util.List;

/**
 * OrderDAO class
 * Extends GenericDAO<Order>
 */
public class OrderDAO extends GenericDAO<Order> {

    /**
     * @return List<Order>
     *     Read all Order records
     */
    public List<Order> getAllOrders() {
        return super.findAll();
    }

    /**
     * @param id int
     * @return Order
     */
    public Order findOrderById(int id) {
        return super.findById(id);
    }

    /**
     * @param order Order
     *              Insert a new Order record
     */
    public void createOrder(Order order) {
        super.insert(order);
    }

    /**
     * @param id int
     *           Delete an Order record
     */
    public void deleteOrder(int id) {
        super.deleteById(id);
    }
}
