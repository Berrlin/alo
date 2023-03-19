<%--
  Created by IntelliJ IDEA.
  User: Mai Quoc Tuan
  Date: 3/19/2023
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="address" method="post">
    <p>
        <input name="id" type="hidden" value="${person.id}">
    </p>
    <p>
        <input name="name" type="text" placeholder="name" value="${person.name}">
    </p>
    <p>
        <c:forEach items="${person.addresses}" var="address">
            <input name="number" value="${address.number}">
            <input name="street" value="${address.street}">
        </c:forEach>
    </p>
    <p>
        <input name="action" type="submit" value="update">
    </p>
</form>
</body>
</html>
