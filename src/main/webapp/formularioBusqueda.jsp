<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar empleados</title>
</head>
<body>
<div class="contenedor">
    <h1>Buscar empleados</h1>
    <p>Rellena uno o varios campos. Los que dejes vacíos no se tendrán en cuenta.</p>
    <form action="${pageContext.request.contextPath}/empleados" method="post">
        <input type="hidden" name="accion" value="buscar">

        <label for="nombre">Nombre</label>
        <input type="text" id="nombre" name="nombre">
        <br>

        <label for="dni">DNI</label>
        <input type="text" id="dni" name="dni">
        <br>

        <label for="sexo">Sexo</label>
        <select id="sexo" name="sexo">
            <option value="">-- Cualquiera --</option>
            <option value="M">M</option>
            <option value="F">F</option>
        </select>
        <br>

        <label for="categoria">Categoría</label>
        <input type="number" id="categoria" name="categoria" min="1" max="10">
        <br>

        <label for="anyos">Años trabajados</label>
        <input type="number" id="anyos" name="anyos" min="0">

        <br><br>
        <button type="submit">Buscar</button>
        <a class="boton volver" href="javascript:history.back()">Volver</a>
    </form>
</div>
</body>
</html>