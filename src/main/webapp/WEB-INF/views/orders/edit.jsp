<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ALl your orders</title>
</head>
<body>
<h1>All your orders</h1>
<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Details</th>
        <th>Action</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/detail?id=${order.id}">Details</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/order/remove?id=${order.id}">
                    <button>Remove</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
</body>
</html>
