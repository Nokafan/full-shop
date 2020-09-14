<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inject data</title>
</head>
<body>
<h1>Data to inject</h1>
<br>
<form method="post" action="${pageContext.request.contextPath}/inject">
    Data: <input type="radio" name="check" value="true" checked/>Add
    <input type="radio" name="check" value="false"/>Do not add
    <br><br>
    <button type="submit">Submit</button>
</form>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
</body>
</html>
