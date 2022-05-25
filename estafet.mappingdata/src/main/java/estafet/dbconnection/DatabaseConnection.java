package estafet.dbconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    /**
     * Creates instance of Database Connection through Singleton design pattern
     */

    private static final String FILE_PATH = "src/main/resources/config.properties";

    private static DatabaseConnection instance;
    private Connection connection;
    private PropertiesHelper propertiesHelper;

    private DatabaseConnection() throws IOException {
        propertiesHelper = PropertiesHelper.getInstance();
        try {
            this.connection = DriverManager.getConnection(
                    propertiesHelper.getUrl(),
                    propertiesHelper.getUser(),
                    propertiesHelper.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DatabaseConnection getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

}
