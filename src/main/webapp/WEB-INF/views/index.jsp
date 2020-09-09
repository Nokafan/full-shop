<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Main page</h1>
<br><br>
<h2 style="color: blue">${message}</h2>
<a href="${pageContext.request.contextPath}/injectData">To inject data page</a>
<br><br>
<a href="${pageContext.request.contextPath}/registration">To register</a>
<br><br>
<a href="${pageContext.request.contextPath}/users/all">To all users</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/addProduct">To add products</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">To all products</a>
<br><br>
<a href="${pageContext.request.contextPath}/cart/all">Cart</a>
<br><br>
</body>
</html>
