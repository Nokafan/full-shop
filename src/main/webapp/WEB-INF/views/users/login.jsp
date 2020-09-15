<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h2 style="color: red">${message}</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
    Login: <input type="text" name="login" required>
    Password: <input type="password" name="password" required>
    <button type="submit">Login</button>
</form>
    <br><br>
    <a href="${pageContext.request.contextPath}/">To main page</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/registration">To registration page</a>
    <br>
</body>
</html>
