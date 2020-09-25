<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Main page</h1>
<br>
<h2 style="color: blue">${message}</h2>
<a href="${pageContext.request.contextPath}/login"><button>Login</button></a>
<br><br>
<a href="${pageContext.request.contextPath}/logout"><button>Logout</button></a>
<br><br>
<a href="${pageContext.request.contextPath}/registration">To registration page</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
<br><br>
<a href="${pageContext.request.contextPath}/orders/all">All user orders</a>
<br><br>
<a href="${pageContext.request.contextPath}/carts/cart">User cart</a>
<br><br>
<hr>
<h1>Admin section</h1>
<a href="${pageContext.request.contextPath}/users/all">All users</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/add">Add new products</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/edit">All products edit section</a>
<br><br>
<a href="${pageContext.request.contextPath}/carts/edit">All carts edit section</a>
<br><br>
<a href="${pageContext.request.contextPath}/orders/edit">All orders edit section</a>
<br><br>
</body>
</html>
