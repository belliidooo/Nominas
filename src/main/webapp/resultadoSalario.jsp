<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Salario del empleado</title>
</head>
<body>
<div class="contenedor">
    <h1>Salario del empleado</h1>
    <table>
        <tr><th>DNI</th><th>Sueldo</th></tr>
        <tr>
            <td>${dni}</td>
            <td><fmt:formatNumber value="${sueldo}" type="number"
                                  minFractionDigits="2" maxFractionDigits="2"/> €</td>
        </tr>
    </table>
    <a class="boton volver" href="javascript:history.back()">Volver</a>
</div>
</body>
</html>