package restday.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {

    private final String url = "jdbc:postgresql://localhost:5432/dayoff";
    private final String user = "postgres";
    private final String password = "123456";

    public Connection connect()  {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
