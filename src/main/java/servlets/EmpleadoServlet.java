package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import laboral.DatosNoCorrectosException;
import laboral.Empleado;
import persistencia.EmpleadoDAO;
import persistencia.NominaDAO;

/**
 * Servlet controlador. Según el parámetro "accion" decide qué operación
 * realizar y a qué JSP enviar el resultado.
 */
@WebServlet("/empleados")
public class EmpleadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        procesar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        procesar(request, response);
    }

    private void procesar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "";
        }

        try {
            switch (accion) {
                case "listar":
                    listar(request, response);
                    break;
                case "salario":
                    salario(request, response);
                    break;
                case "buscar":
                    buscar(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;
            }
        } catch (SQLException e) {
            error(request, response, "No se pudo acceder a la base de datos: " + e.getMessage());
        } catch (DatosNoCorrectosException e) {
            error(request, response, "Hay datos no válidos en la base de datos.");
        } catch (NumberFormatException e) {
            error(request, response, "La categoría y los años trabajados deben ser números enteros.");
        }
    }

    /** Opción 1: muestra todos los empleados. */
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, DatosNoCorrectosException, ServletException, IOException {
        ArrayList<Empleado> empleados = EmpleadoDAO.obtenerTodos();
        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/listaEmpleados.jsp").forward(request, response);
    }

    /** Opción 2: muestra el salario de un empleado a partir de su DNI. */
    private void salario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String dni = request.getParameter("dni");
        if (dni == null || dni.isBlank()) {
            error(request, response, "Debes introducir un DNI.");
            return;
        }
        dni = dni.trim().toUpperCase();

        Integer sueldo = NominaDAO.obtenerSalario(dni);
        if (sueldo == null) {
            error(request, response, "No existe ningún empleado con el DNI " + dni + ".");
            return;
        }
        request.setAttribute("dni", dni);
        request.setAttribute("sueldo", sueldo);
        request.getRequestDispatcher("/resultadoSalario.jsp").forward(request, response);
    }

    /** Opción 3: busca empleados por cualquiera de sus datos. */
    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, DatosNoCorrectosException, ServletException, IOException {
        ArrayList<Empleado> empleados = EmpleadoDAO.buscar(
                request.getParameter("nombre"),
                request.getParameter("dni"),
                request.getParameter("sexo"),
                aEntero(request.getParameter("categoria")),
                aEntero(request.getParameter("anyos")));

        request.setAttribute("empleados", empleados);
        request.getRequestDispatcher("/resultadoBusqueda.jsp").forward(request, response);
    }

    /** Convierte un texto a Integer; devuelve null si está vacío. */
    private Integer aEntero(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }
        return Integer.valueOf(texto.trim());
    }

    /** Envía al usuario a la página de error con un mensaje. */
    private void error(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {
        request.setAttribute("mensaje", mensaje);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}