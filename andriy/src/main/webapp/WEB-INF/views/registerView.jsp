<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Register Page</h3>
<p style="color: red;">${errorString}</p>


<form method="POST" action="${pageContext.request.contextPath}/register">
  <table border="0">
    <tr>
      <td>User Name</td>
      <td><input type="text" name="username"/> </td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input type="text" name="email"/> </td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="text" name="password"/> </td>
    </tr>
    <tr>
      <td colspan ="2">
        <input type="submit" value= "Submit" />
        <a href="${pageContext.request.contextPath}/">Cancel</a>
      </td>
    </tr>
  </table>
</form>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>