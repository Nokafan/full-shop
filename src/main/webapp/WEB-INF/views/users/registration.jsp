<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<h1>User registration</h1>
<h1>please add information</h1>
<h2 style="color: red">${message}</h2>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Name: <input type="text" name="name" required>
    <br><br>
    Login: <input type="text" name="login" required>
    <br><br>
    Password: <input type="password" name="pwd" required>
    <br><br>
    Repeat password: <input type="password" name="pwd-repeat" required>
    <br><br>
    <button type="submit">Register</button>
    <br><br>
    <a href="${pageContext.request.contextPath}/">To main page</a>
    <br>
</form>
</body>
</html>
