<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Check</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Order Check</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Date</th>
    </tr>
    <c:forEach items="${cart}" var="product" >
        <tr>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.date_added}</td>
        </tr>
    </c:forEach>
</table>

<a href="buy" >Buy</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>