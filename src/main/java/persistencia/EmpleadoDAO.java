package persistencia;

import java.sql.*;
import java.util.ArrayList;

import laboral.*;

public class EmpleadoDAO {
    public void insertar (Empleado emp) throws SQLException {
        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos)" +
                " VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conexion();
        PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, emp.dni);
            ps.setString(2, emp.nombre);
            ps.setString(3, String.valueOf(emp.sexo));
            ps.setInt(4, emp.getCategoria());
            ps.setInt(5, emp.anyos);

            ps.executeUpdate();
        }
    }

    public static void actualizar(Empleado emp, String dniOriginal) throws SQLException {

        String sql = "UPDATE empleados SET nombre = ?, dni = ?, sexo = ?, categoria = ?, anyos = ? " +
                "WHERE dni = ?";

        try (Connection conexion = ConexionDB.conexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, emp.nombre);
            ps.setString(2, emp.dni);
            ps.setString(3, String.valueOf(emp.sexo));
            ps.setInt(4, emp.getCategoria());
            ps.setInt(5, emp.anyos);
            ps.setString(6, dniOriginal);

            ps.executeUpdate();
        }
    }


    public static void mostrar () throws SQLException {
        String sql = "SELECT * FROM empleados";

        try (Connection conn = ConexionDB.conexion();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");
                String sexo = rs.getString("sexo");
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");

                System.out.println("Nombre: " + nombre);
                System.out.println("DNI: " + dni);
                System.out.println("Sexo: " + sexo);
                System.out.println("Categoria: " + categoria);
                System.out.println("Años trabajados: " + anyos);
            }
        }
    }

    public static Empleado buscarPorDni(String dni) throws SQLException, DatosNoCorrectosException{

        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados " +
                "WHERE dni = ?";

        try (Connection conexion = ConexionDB.conexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Empleado emp = new Empleado();

                    emp.dni = rs.getString("dni");
                    emp.nombre = rs.getString("nombre");
                    emp.sexo = rs.getString("sexo").charAt(0);
                    emp.setCategoria(rs.getInt("categoria"));
                    emp.anyos = rs.getInt("anyos");

                    return emp;
                }
            }
        }

        return null;
    }

    public static ArrayList<Empleado> obtenerTodos() throws SQLException, DatosNoCorrectosException {
        ArrayList<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT dni, nombre, sexo, categoria, anyos FROM empleados";

        try (Connection conexion = ConexionDB.conexion();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado emp = new Empleado();

                emp.dni = rs.getString("dni");
                emp.nombre = rs.getString("nombre");
                emp.sexo = rs.getString("sexo").charAt(0);
                emp.setCategoria(rs.getInt("categoria"));
                emp.anyos = rs.getInt("anyos");

                empleados.add(emp);
            }
        }
        return empleados;
    }

    /**
     * Busca empleados combinando (AND) los criterios que no estén vacíos.
     * Nombre y DNI se buscan por coincidencia parcial.
     *
     * @return empleados que cumplen los criterios, ordenados por nombre
     */
    public static ArrayList<Empleado> buscar(String nombre, String dni, String sexo, Integer categoria, Integer anyos) throws SQLException, DatosNoCorrectosException {

        StringBuilder sql = new StringBuilder("SELECT dni, nombre, sexo, categoria, anyos FROM empleados WHERE 1=1");
        ArrayList<Object> params = new ArrayList<>();

        if (nombre != null && !nombre.isBlank()) {
            sql.append(" AND nombre LIKE ?");
            params.add("%" + nombre.trim() + "%");
        }
        if (dni != null && !dni.isBlank()) {
            sql.append(" AND dni LIKE ?");
            params.add("%" + dni.trim() + "%");
        }
        if (sexo != null && !sexo.isBlank()) {
            sql.append(" AND sexo = ?");
            params.add(sexo);
        }
        if (categoria != null) {
            sql.append(" AND categoria = ?");
            params.add(categoria);
        }
        if (anyos != null) {
            sql.append(" AND anyos = ?");
            params.add(anyos);
        }
        sql.append(" ORDER BY nombre");

        ArrayList<Empleado> lista = new ArrayList<>();
        try (Connection conexion = ConexionDB.conexion();
             PreparedStatement ps = conexion.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Empleado(
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("sexo").charAt(0),
                            rs.getInt("categoria"),
                            rs.getInt("anyos")));
                }
            }
        }
        return lista;
    }

}
