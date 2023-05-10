// presentation/ClientWindow.java
package presentation;

import dataAccessLayer.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientWindow extends JFrame {
    private JTextField nameField;
    private JTextField addressField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable clientTable;
    private ClientDAO clientDAO; // Create a ClientDAO instance


    public ClientWindow() {
        setTitle("Client Operations");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        clientDAO = new ClientDAO(); // Initialize the ClientDAO instance

        initFields();
        initButtons();
        initTable();

        loadClientsFromDatabase();
    }

    private void initFields() {
        nameField = new JTextField();
        nameField.setBounds(25, 25, 200, 25);
        add(nameField);

        addressField = new JTextField();
        addressField.setBounds(25, 60, 200, 25);
        add(addressField);
    }

    private void initButtons() {
        addButton = new JButton("Add");
        addButton.setBounds(250, 25, 100, 25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                Client client = new Client(0, name, address);
                clientDAO.addClient(client);
                loadClientsFromDatabase();
            }
        });
        add(addButton);

        editButton = new JButton("Edit");
        editButton.setBounds(250, 60, 100, 25);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) clientTable.getValueAt(selectedRow, 0);
                    String name = nameField.getText();
                    String address = addressField.getText();
                    Client client = new Client(id, name, address);
                    clientDAO.updateClient(client);
                    loadClientsFromDatabase();
                }
            }
        });
        add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(250, 95, 100, 25);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) clientTable.getValueAt(selectedRow, 0);
                    clientDAO.deleteClient(id);
                    loadClientsFromDatabase();
                }
            }
        });
        add(deleteButton);
    }

    private void initTable() {
        clientTable = new JTable();
        clientTable.setBounds(25, 150, 750, 400);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Address");
        clientTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBounds(25, 150, 750, 400);
        add(scrollPane);
    }


    // Load clients from the database and populate the table
    private void loadClientsFromDatabase() {
        List<Client> clients = clientDAO.getAllClients(); // Use the ClientDAO instance to interact with the database

        if (clients != null) {
            DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
            model.setRowCount(0);

            for (Client client : clients) {
                model.addRow(new Object[]{client.getId(), client.getName(), client.getAddress()});
            }
        }
    }
}

