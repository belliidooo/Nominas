package persistencia;

import java.sql.*;

public class ConexionDB {
    private static final String URL = "jdbc:mariadb://localhost:3306/nominas";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static Connection conexion () throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
