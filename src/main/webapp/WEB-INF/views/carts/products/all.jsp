<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All products in cart</title>
</head>
<body>
<h1>Products in the cart admin version</h1>

<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    <c:forEach var="products" items="${cart.getProducts()}">
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
                <a href="${pageContext.request.contextPath}/admin/cart/remove?productId=${products.id}&cartId=${cart.id}">
                    <button>Remove</button>
                </a>
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
