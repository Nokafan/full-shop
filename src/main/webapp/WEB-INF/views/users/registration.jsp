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
    Name: <input type="text" name="name">
    <br><br>
    Login: <input type="text" name="login">
    <br><br>
    Password: <input type="password" name="pwd">
    <br><br>
    Repeat password: <input type="password" name="pwd-repeat">
    <br><br>
    <button type="submit">Register</button>
    <br><br>
</form>
</body>
</html>
