<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Overview</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<form method="post" action="/WEBProjRA502013/UserOverviewServlet">
		<h1>Find Users</h1>
		<table border="1" align="center">
		<tr>
			<td>By Username (leave blank to ignore):</td>
			<td><input type="text" name="username" id="username"/></td>
		</tr>
		<tr>
			<td>By First Name (leave blank to ignore):</td>
			<td><input type="text" name="firstName" id="firstName"/></td>
		</tr>
		<tr>
			<td>By Last Name (leave blank to ignore):</td>
			<td><input type="text" name="lastName" id="lastName"/></td>
		</tr>
		<tr><td>By Gender:</td>
			<td>
				<select name="gender">
					<option value="NONE">Ignore Gender</option>
					<option value="MALE">Male</option>
					<option value="FEMALE">Female</option>
				</select>
			</td>
		</tr>
		<tr><td colspan="2"><input type="submit" class="submitButton" value="Find Users"/></td></tr>
	</table>
	</form>
	<h1>User Overview</h1>
	<table border="1" align="center">
		<tr>
		  <th>Username</th>
		  <th>First Name</th>
		  <th>Last Name</th>
		  <th>Gender</th>
		</tr>
		<c:forEach var="user" items="${sessionScope.users}">
			<tr>
			  <td><c:out value="${user.username}" /></td>
			  <td><c:out value="${user.firstName}" /></td>
			  <td><c:out value="${user.lastName}" /></td>
			  <td><c:out value="${user.gender}" /></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>