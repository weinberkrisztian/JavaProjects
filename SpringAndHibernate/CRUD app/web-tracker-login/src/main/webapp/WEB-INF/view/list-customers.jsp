<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css" rel="stylesheet" />
<!-- <link href="/web-tracker-login/src/main/resources/css/style.css" type="text/css" rel="stylesheet" /> -->


<title>List Customers</title>
</head>
<body>

<div id="wrapper">
	<div id="header">
		<h2>CRM List of Customers</h2>
	</div>
</div>

<div id="container">
	<div id="context">
	<security:authorize access="hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')">
	<input type="button" value="Add Customer" class="add-button" onclick="window.location.href='showFormToAdd'; return false; " />
	</security:authorize>
	<form:form action="searchCustomer"  method="GET" >
	Search for Customer: <input type="text" name="theSearchParam" />
	<input type="submit" class="add-button" value="Search" />
	
	</form:form>
	
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<security:authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_EMPLOYEE')">
				<th>Action</th>
				</security:authorize>
			</tr>
			<c:forEach var="tempCustomer" items="${customers}">

			<c:url value="/customer/showFormForUpdate" var="updateLink" >
			<c:param name="customerId" value="${tempCustomer.id}"></c:param>
			
			 </c:url>
			<c:url value="/customer/delete" var="deleteLink" >
			<c:param name="customerId" value="${tempCustomer.id}"></c:param>
			
			 </c:url>

			<tr>
				<td> ${tempCustomer.firstName} </td>
				<td> ${tempCustomer.lastName} </td>
				<td> ${tempCustomer.email} </td>
				<security:authorize access="hasRole('ROLE_ADMIN') and hasRole('ROLE_EMPLOYEE')">
				<td> <a href="${updateLink}">Update</a>
				|
				<a href="${deleteLink}" onclick="if(!(confirm('You want to delete that Customer?'))) return false">Delete</a></td>
				</security:authorize>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
<br>
<form:form action="${pageContext.request.contextPath}/logout" method="POST" >

<input type="submit" value="Logout" class="add-button" />

</form:form>


</body>
</html>