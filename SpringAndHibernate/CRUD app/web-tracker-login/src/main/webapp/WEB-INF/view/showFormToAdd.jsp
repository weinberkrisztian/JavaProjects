            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Add Customer form</title>

<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"   /> 
<link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/resources/css/add-customer-style.css"   /> 


</head>
<body>

<div id="wrapper">
<div id="header">
	<h2>CRM</h2>

</div>
</div>


<div id="container">
	<h2>Save Customer</h2>
	<form:form  action="saveCustomer" method="POST" modelAttribute="customers"  >
	
		<table>
			<tbody>
				<tr>
					<td><label>First Name :</label></td>
					<td><form:input path="firstName"/></td>	
				</tr>
				<tr>
					<td><label>Last Name :</label></td>
					<td><form:input path="lastName"/></td>	
				</tr>
				<tr>
					<td><label>Email :</label></td>
					<td><form:input path="email"/></td>	
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save"/></td>	
				</tr>
				
				
			</tbody>
		</table>
	</form:form>
	
	<div style="clear; both;"></div>
				
				<p>
				<a href="${pageContext.request.contextPath}/customer/list">
					Bakc to the Customers List
				</a>
				
				</p>
	
	
</div>



</body>
</html>