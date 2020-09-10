<head>
    <title>Add product</title>
</head>
<body>
<h1>Add product</h1>
<h1>please add information</h1>
<h2 style="color: blue">${message}</h2>
<form method="post" action="${pageContext.request.contextPath}/products/addProduct">
    Name: <input type="text" name="name" required>
    Price: <input type="number" step="0.01" name="price" required>
    <br><br>
    <button type="submit">Add product</button>
    <br><br>
</form>
<a href="${pageContext.request.contextPath}/">Main</a>
<br><br>
<a href="${pageContext.request.contextPath}/products/all">All products</a>
<br><br>
<a href="${pageContext.request.contextPath}/cart">Cart</a>
</body>
</html>
