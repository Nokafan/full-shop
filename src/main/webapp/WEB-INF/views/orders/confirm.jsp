<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<h1>My order created</h1>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
</tr>
<c:forEach var="order" items="${order}">
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
<h1>To approve order press confirm</h1>
<a href="${pageContext.request.contextPath}/order/confirm"><button>Confirm order</button></a>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/carts/cart">Cart</a>
</body>
</html>
