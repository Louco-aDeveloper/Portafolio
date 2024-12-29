package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/automac";  // Asegúrate de que 'tu_base_de_datos' sea el nombre correcto
    private static final String USER = "root";  // Reemplaza con tu nombre de usuario de MySQL
    private static final String PASSWORD = "123456";  // Reemplaza con tu contraseña de MySQL

    // Este método devuelve la conexión con la base de datos
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
