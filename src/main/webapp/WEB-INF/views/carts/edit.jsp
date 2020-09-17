<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carts admin</title>
</head>
<body>
<h1>Carts admin mode</h1>
<br>
<br>
<table border="1">
    <tr>
        <th>Cart ID</th>
        <th>User ID</th>
        <th>Detail</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="cart" items="${carts}">
        <tr>
            <td>
                <c:out value="${cart.id}"/>
            </td>
            <td>
                <c:out value="${cart.userId}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/cart/detail?id=${cart.id}">
                    <button>Details</button></a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/cart/clear?id=${cart.id}">
                    <button>Clear</button></a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>

<a href="${pageContext.request.contextPath}/products/add"><button>To create new product</button></a>
<br>
<br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
</body>
</html>
