// presentation/OrderWindow.java
package presentation;

import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderWindow extends JFrame {
    private JComboBox<Client> clientComboBox;
    private JComboBox<Product> productComboBox;
    private JTextField quantityField;
    private JButton createOrderButton;
    private JTable orderTable;

    public OrderWindow() {
        setTitle("Order Operations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        initComboBoxes();
        initFields();
        initButtons();
        initTable();

        // You need to add your own implementation to interact with the database
        // loadOrdersFromDatabase();
    }

    private void initComboBoxes() {
        clientComboBox = new JComboBox<>();
        clientComboBox.setBounds(25, 25, 200, 25);
        add(clientComboBox);

        productComboBox = new JComboBox<>();
        productComboBox.setBounds(25, 60, 200, 25);
        add(productComboBox);

        // You need to add your own implementation to interact with the database
        // loadClientsAndProductsFromDatabase();
    }

    private void initFields() {
        quantityField = new JTextField();
        quantityField.setBounds(250, 25, 50, 25);
        add(quantityField);
    }

    private void initButtons() {
        createOrderButton = new JButton("Create Order");
        createOrderButton.setBounds(250, 60, 150, 25);
        add(createOrderButton);

        // Implement event handlers for createOrderButton
    }

    private void initTable() {
        orderTable = new JTable();
        orderTable.setBounds(25, 150, 750, 400);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Client");
        model.addColumn("Product");
        model.addColumn("Quantity");
        orderTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(orderTable);
        scrollPane.setBounds(25, 150, 750, 400);
        add(scrollPane);
    }

    // Load clients and products from the database and populate the combo boxes
    private void loadClientsAndProductsFromDatabase() {
        // You need to add your own implementation to interact with the database
        // e.g., List<Client> clients = clientDAO.getAllClients();
        //       List<Product> products = productDAO.getAllProducts();
        List<Client> clients = null; // Replace with actual implementation
        List<Product> products = null; // Replace with actual implementation

        if (clients != null) {
            for (Client client : clients) {
                clientComboBox.addItem(client);
            }
        }

        if (products != null) {
            for (Product product : products) {
                productComboBox.addItem(product);
            }
        }
    }

    // Load orders from the database and populate the table
    // Load orders from the database and populate the table
    private void loadOrdersFromDatabase() {
        // You need to add your own implementation to interact with the database
        // e.g., List<Order> orders = orderDAO.getAllOrders();
        List<Order> orders = null; // Replace with actual implementation

        if (orders != null) {
            DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
            model.setRowCount(0);

            for (Order order : orders) {
                model.addRow(new Object[]{
                        order.getId(),
                        order.getClientId(), // Use the correct method or field name from your Order model
                        order.getProductId(), // Use the correct method or field name from your Order model
                        order.getQuantity()
                });
            }
        }
    }

}
