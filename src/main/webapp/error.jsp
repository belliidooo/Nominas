<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<div class="contenedor">
    <h1>Ha ocurrido un problema</h1>
    <p class="error">
        <c:out value="${empty mensaje ? 'Ha ocurrido un error inesperado o la página no existe.' : mensaje}"/>
    </p>
    <a class="boton volver" href="javascript:history.back()">Volver</a>
    <a class="boton" href="${pageContext.request.contextPath}/index.jsp">Ir al menú</a>
</div>
</body>
</html>