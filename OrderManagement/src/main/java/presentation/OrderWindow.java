// presentation/OrderWindow.java
package presentation;

import businessLayer.ClientService;
import businessLayer.OrderService;
import businessLayer.ProductService;
import dataAccessLayer.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderWindow extends JFrame {
    private JComboBox<Client> clientComboBox;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;
    private JButton orderButton;
    private JTable orderTable;

    private OrderService orderService;
    private ClientService clientService;
    private ProductService productService;

    public OrderWindow() {
        setTitle("Order Operations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        orderService = new OrderService();
        clientService = new ClientService();
        productService = new ProductService();

        initFields();
        initButton();
        initTable();

        loadOrdersFromDatabase();
    }

    private void initFields() {
        List<Client> clients = clientService.getAllClients();
        clientComboBox = new JComboBox<>(clients.toArray(new Client[0]));
        clientComboBox.setBounds(25, 25, 200, 25);
        add(clientComboBox);

        List<Product> products = productService.getAllProducts();
        productComboBox = new JComboBox<>(products.toArray(new Product[0]));
        productComboBox.setBounds(25, 60, 200, 25);
        add(productComboBox);

        quantityField = new JTextField();
        quantityField.setBounds(25, 95, 200, 25);
        add(quantityField);
    }

    private void initButton() {
        orderButton = new JButton("Order");
        orderButton.setBounds(250, 25, 100, 25);
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = (Client) clientComboBox.getSelectedItem();
                Product product = (Product) productComboBox.getSelectedItem();

                if (client == null || product == null) {
                    JOptionPane.showMessageDialog(null, "Please select a client and a product!");
                    return;
                }

                int quantity;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid quantity entered!");
                    return;
                }

                if (product.getStock() < quantity) {
                    JOptionPane.showMessageDialog(null, "Not enough stock!");
                    return;
                }

                Order order = new Order(0, client.getId(), product.getId(), quantity);
                orderService.addOrder(order);
                loadOrdersFromDatabase();

                product.setStock(product.getStock() - quantity);
                productService.updateProduct(product);

                // Create new LogDAO instance
                LogDAO logDAO = new LogDAO();

                // Create a new Bill instance
                double totalPrice = product.getPrice() * quantity;  // Assuming that a `getPrice()` method exists in the Product class
                Bill bill = new Bill(0, client.getId(), product.getId(), quantity, totalPrice);

                // Insert the bill into the database
                logDAO.insert(bill);
            }

        });
        add(orderButton);
    }

    private void initTable() {
        orderTable = new JTable();
        orderTable.setBounds(25, 150, 750, 400);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Order ID");
        model.addColumn("Client");
        model.addColumn("Product");
        model.addColumn("Quantity");
        orderTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(25, 150, 750, 400);
        add(scrollPane);
    }


    // Load orders from the database and populate the table
    private void loadOrdersFromDatabase() {
        List<Order> orders = orderService.getAllOrders();

        if (orders != null) {
            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            model.setRowCount(0);

            for (Order order : orders) {
                Client client = clientService.findClientById(order.getClientId());
                Product product = productService.findProductById(order.getProductId());
                model.addRow(new Object[]{order.getId(), client.getName(), product.getName(), order.getQuantity()});
            }
        }
    }
}
