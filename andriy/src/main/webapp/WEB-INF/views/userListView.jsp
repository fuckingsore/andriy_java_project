<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Catalog</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Admin Catalog</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Status</th>
        <th></th>
    </tr>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.status}</td>
            <td>
                <c:choose>
                    <c:when test="${user.status == 'blocked'}">
                        <a href="unblockUser?id=${user.id}">Unblock</a>
                    </c:when>
                    <c:otherwise>
                        <a href="blockUser?id=${user.id}">Block</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>