<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultar salario</title>
</head>
<body>
<div class="contenedor">
    <h1>Consultar salario de un empleado</h1>
    <form action="${pageContext.request.contextPath}/empleados" method="post">
        <input type="hidden" name="accion" value="salario">
        <label for="dni">DNI del empleado</label>
        <input type="text" id="dni" name="dni" required placeholder="12345678A">
        <br><br>
        <button type="submit">Buscar</button>
        <a class="boton volver" href="javascript:history.back()">Volver</a>
    </form>
</div>
</body>
</html>