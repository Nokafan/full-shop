<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
<h1>User registration</h1>
<h1>Please add information</h1>
<h2 style="color: red">${message}</h2>
<form method="post" action="${pageContext.request.contextPath}/registration">
    Name: <input type="text" name="name" required>

    Login: <input type="text" name="login" required>

    Password: <input type="password" name="pwd" required>

    Repeat password: <input type="password" name="pwd-repeat" required>
    <br><br>
    <button type="submit">Register</button>
</form>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/login">To login page</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
<br><br>
</body>
</html>
