<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>

<head>
	<title>List of customers</title>
	
	<!-- reference out style sheet -->
	<link type = "text/css"
		  rel = "stylesheet"
		  href = "${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>

<div id = "wrapper">
	<div id = "header">
		<h2>CRM - CUSTOMER RELATIONSHIP MANGER</h2>
	</div>
	<div id = "container">
		<div id = "content">
		<input type = "button" value = "Add Customer"
			   onclick = "window.location.href = 'showFormForAdd';return false;"
			   class = "add-button"
		/>
		<input type = "button" value = "Show All"
		onclick = "window.location.href = 'list';return false;"
			   class = "add-button"
		/>
		
		<form:form action="search" method="GET">
        Search customer: <input type="text" name="theSearchName" />
                
        <input type="submit" value="Search" class="add-button" />
        </form:form>   
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:choose>
				<c:when test = "${fn:length(customers)!=0}">
				<c:forEach var = "tempCustomer" items = "${customers}">
					<c:url var = "updateLink" value = "/customer/showFormForUpdate">
						<c:param name = "customerId" value = "${tempCustomer.id}"/>
					
					</c:url>
					
					<c:url var = "deleteLink" value = "/customer/delete">
						<c:param name = "customerId" value = "${tempCustomer.id}"/>
					
					</c:url>
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
							<a href = "${updateLink}">Update </a>
							|
							<a href = "${deleteLink}" onclick = "if(!(confirm('Are you sure You want to delete this customer'))) return false" >Delete</a>
						
						</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
				<h1>No data to show </h1>
				</c:otherwise>
				</c:choose>
			</table>
			
		</div>
	
	</div>
</div>

</body>

</html>