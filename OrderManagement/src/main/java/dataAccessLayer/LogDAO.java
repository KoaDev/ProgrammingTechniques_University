// dataAccessLayer/LogDAO.java
package dataAccessLayer;

import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {

    // Insert Bill record
    public void insert(Bill bill) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO logs (client_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bill.clientId());
            preparedStatement.setInt(2, bill.productId());
            preparedStatement.setInt(3, bill.quantity());
            preparedStatement.setDouble(4, bill.totalPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all Bill records
    public List<Bill> readAll() {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM logs";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int clientId = resultSet.getInt("client_id");
                int productId = resultSet.getInt("product_id");
                int quantity = resultSet.getInt("quantity");
                double totalPrice = resultSet.getDouble("total_price");

                Bill bill = new Bill(id, clientId, productId, quantity, totalPrice);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bills;
    }
}
