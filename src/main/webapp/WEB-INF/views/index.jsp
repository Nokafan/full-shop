<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Main page</h1>
<br>
<h2 style="color: blue">${message}</h2>
<a href="${pageContext.request.contextPath}/inject">Inject data page</a>
<br><br>
<a href="${pageContext.request.contextPath}/users/registration">Add new user</a>
<br><br>
<a href="${pageContext.request.contextPath}/users/all">All users</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/add">Add new products</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
<br><br>
<a href="${pageContext.request.contextPath}/orders/all">All orders</a>
<br><br>
<a href="${pageContext.request.contextPath}/carts/cart">Cart</a>
<br><br>
<hr>
<a href="${pageContext.request.contextPath}/carts/edit">Carts edit section</a>
<br><br>
<a href="${pageContext.request.contextPath}/orders/edit">Orders edit section</a>
<br><br>
</body>
</html>
