<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Exception Page</title>
</head>
<body>

<h2>You can not access this page!</h2>

<hr>
User: <security:authentication property="principal.username"/>
<br><br>
Role(s): <security:authentication property="principal.authorities"/>

<hr>

<a href="${pageContext.request.contextPath}/">Back to home page</a>
<hr>
<form:form action="${pageContext.request.contextPath}/logout" method="POST" >

<input type="submit" value="Logout" />

</form:form>

</body>
</html>