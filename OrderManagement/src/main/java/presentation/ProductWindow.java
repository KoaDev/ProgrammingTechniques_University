// presentation/ProductWindow.java
package presentation;

import businessLayer.ProductService;
import dataAccessLayer.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductWindow extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private JTable productTable;
    private final ProductService productService;


    public ProductWindow() {
        setTitle("Product Operations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);
        productService = new ProductService();
        initFields();
        initButtons();
        initTable();

        loadProductsFromDatabase();
    }

    private void initFields() {
        nameField = new JTextField();
        nameField.setBounds(25, 25, 200, 25);
        add(nameField);

        priceField = new JTextField();
        priceField.setBounds(25, 60, 200, 25);
        add(priceField);

        stockField = new JTextField();
        stockField.setBounds(25, 95, 200, 25);
        add(stockField);
    }

    private void initButtons() {
        JButton addButton = new JButton("Add");
        addButton.setBounds(250, 25, 100, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int stock = Integer.parseInt(stockField.getText());
                Product product = new Product(0, name, price, stock);
                productService.addProduct(product);
                loadProductsFromDatabase();
            }
        });
        add(addButton);

        JButton editButton = new JButton("Edit");
        editButton.setBounds(250, 60, 100, 25);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) productTable.getValueAt(selectedRow, 0);
                    String name = nameField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    int stock = Integer.parseInt(stockField.getText());
                    Product product = new Product(id, name, price, stock);
                    productService.updateProduct(product);
                    loadProductsFromDatabase();
                }
            }
        });
        add(editButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 95, 100, 25);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) productTable.getValueAt(selectedRow, 0);
                    productService.deleteProduct(id);
                    loadProductsFromDatabase();
                }
            }
        });
        add(deleteButton);
    }

    private void initTable() {
        productTable = new JTable();
        productTable.setBounds(25, 150, 750, 400);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Stock");
        productTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBounds(25, 150, 750, 400);
        add(scrollPane);
    }

    // Load products from the database and populate the table
    private void loadProductsFromDatabase() {
        List<Product> products = productService.getAllProducts();

        if (products != null) {
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            model.setRowCount(0);

            for (Product product : products) {
                model.addRow(new Object[]{product.getId(), product.getName(), product.getPrice(), product.getStock()});
            }
        }
    }
}


