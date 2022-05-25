package estafet;

import estafet.dbconnection.DatabaseConnection;
import estafet.model.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.StatementConfiguration;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainUtilityComponentExample {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        ResultSetHandler<Customer> resultSetHandler = new BeanHandler<Customer>(Customer.class);
        QueryRunner run = new QueryRunner();

        try {
            Customer customer = run.query(connection,"SELECT * FROM customer WHERE name=?", resultSetHandler, "Alice");
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
