package estafet;

import estafet.dbconnection.DatabaseConnection;
import estafet.model.Customer;
import estafet.util.ResultSetMapper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MainResultSetObjectWithAnnotations {
    public static void main(String... args) {

        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<Customer>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(query);
            List<Customer> customersList = resultSetMapper.mapResultSetToObject(resultSet, Customer.class);
            // print out the list retrieved from database
            if (customersList != null) {
                for (Customer customer : customersList) {
                    System.out.println(customer);
                }
            } else {
                System.out.println("ResultSet is empty. Please check if database table is empty");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
