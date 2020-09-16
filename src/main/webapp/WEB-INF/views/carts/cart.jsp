<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My cart</title>
</head>
<body>
<h1>My cart</h1>
<br>
<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
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
                <a href="${pageContext.request.contextPath}/user/cart/remove?id=${products.id}">
                    <button>Remove</button>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>

<form method="post" action="${pageContext.request.contextPath}/order/create">
    <button type="submit">Place order</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/carts/edit"><button>Carts edit section</button></a>
<br><br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
</body>
</html>
