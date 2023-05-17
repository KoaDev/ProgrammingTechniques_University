package presentation;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Orders Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);

        JButton clientButton = new JButton("Client Operations");
        clientButton.setBounds(100, 50, 200, 50);
        clientButton.addActionListener(e -> new ClientWindow().setVisible(true));
        add(clientButton);

        JButton productButton = new JButton("Product Operations");
        productButton.setBounds(100, 150, 200, 50);
        productButton.addActionListener(e -> new ProductWindow().setVisible(true));
        add(productButton);

        JButton orderButton = new JButton("Order Operations");
        orderButton.setBounds(100, 250, 200, 50);
        orderButton.addActionListener(e -> new OrderWindow().setVisible(true));
        add(orderButton);
    }

    public static void main(String[] args) {
        new MainWindow().setVisible(true);
    }
}
