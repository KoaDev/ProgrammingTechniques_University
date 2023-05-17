package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Order;

import java.util.List;

public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }

    public void addOrder(Order order) {
        orderDAO.createOrder(order);
    }

    public void findOrderById(int id) {
        orderDAO.findOrderById(id);
    }

    public void deleteOrder(int id) {
        orderDAO.deleteOrder(id);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

}