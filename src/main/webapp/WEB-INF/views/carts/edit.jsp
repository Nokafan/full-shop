<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My cart admin</title>
</head>
<body>
<h1>My cart edit mode</h1>
<br>
<br>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/cart/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>

<form method="post" action="${pageContext.request.contextPath}/order/create">
    <button type="submit">Place order</button>
</form>
<a href="${pageContext.request.contextPath}/products/add"><button>To create new product</button></a>
<br>
<br>
<a href="${pageContext.request.contextPath}/">To main page</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
</body>
</html>
