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
    </tr>
    <c:forEach var="order" items="${products}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.name}"/>
            </td>
            <td>
                <c:out value="${order.price}"/>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/carts/cart">Cart</a>
</body>
</html>
