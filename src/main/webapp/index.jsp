<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Empresa - Menú</title>
</head>
<body>
<div class="contenedor">
    <h1>Gestión de empleados</h1>
    <ul class="menu">
        <li><a class="boton" href="${pageContext.request.contextPath}/empleados?accion=listar">
            Mostrar todos los empleados</a></li>
        <li><a class="boton" href="${pageContext.request.contextPath}/formularioSalario.jsp">
            Mostrar el salario de un empleado</a></li>
        <li><a class="boton" href="${pageContext.request.contextPath}/formularioBusqueda.jsp">
            Buscar / modificar empleados</a></li>
    </ul>
</div>
</body>
</html>