package persistencia;

import java.sql.*;
import laboral.*;

public class NominaDAO {

    public static void mostrarSalarioEspecifico(String dni) throws SQLException {
         String sql = "SELECT sueldo FROM nominas WHERE dni = ?";

         try (Connection conn = ConexionDB.conexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {
             ps.setString(1, dni);

             ResultSet rs = ps.executeQuery();

             while (rs.next()) {
                 int sueldo = rs.getInt("sueldo");

                 System.out.println("DNI: " + dni);
                 System.out.println("Sueldo: " + sueldo);
             }
         }
    }

    public static void actualizarSueldo(String dni, int sueldo) throws SQLException {

        String sql = "UPDATE nominas SET sueldo = ? WHERE dni = ?";

        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sueldo);
            ps.setString(2, dni);

            ps.executeUpdate();
        }
    }

    public static Integer obtenerSalario(String dni) throws SQLException {
        String sql = "SELECT sueldo FROM nominas WHERE dni = ?";

        try (Connection conn = ConexionDB.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("sueldo") : null;
            }
        }
    }

}

