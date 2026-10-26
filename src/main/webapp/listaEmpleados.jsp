<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de empleados</title>
</head>
<body>
<div class="contenedor">
    <h1>Listado de empleados</h1>

    <c:choose>
        <c:when test="${empty empleados}">
            <p class="info">No hay empleados registrados en la base de datos.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Nombre</th><th>DNI</th><th>Sexo</th><th>Categoría</th><th>Años trabajados</th>
                </tr>
                <c:forEach var="e" items="${empleados}">
                    <tr>
                        <td>${e.nombre}</td>
                        <td>${e.dni}</td>
                        <td>${e.sexo}</td>
                        <td>${e.categoria}</td>
                        <td>${e.anyos}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <a class="boton volver" href="javascript:history.back()">Volver</a>
</div>
</body>
</html>