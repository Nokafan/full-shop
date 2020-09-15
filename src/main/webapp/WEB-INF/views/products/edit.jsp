<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products</title>
</head>
<body>
<h1>All products</h1>

<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Buy</th>
    </tr>
    <c:forEach var="products" items="${products}">
        <tr>
            <td>
                <c:out value="${products.id}"/>
            </td>
            <td>
                <c:out value="${products.name}"/>
            </td>
            <td>
                <c:out value="${products.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/products/remove?id=${products.id}">
                    <button>Remove</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br>
<a href="${pageContext.request.contextPath}/products/add">Add new products</a>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/carts/cart">Cart</a>
</body>
</html>
