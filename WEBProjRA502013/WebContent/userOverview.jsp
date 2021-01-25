<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Overview</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<form method="post" id="findFiterUserForm" action="/WEBProjRA502013/FindUserServlet">
		<h1>Find/Filter</h1>
		<table border="1" align="center">
			<tr>
				<td>Find or Filter?</td>
				<td>
					<input type="radio" id="findRBtn" name="rBtn" onclick="changeAction(this);" value="Find" checked>
					<label for="findRBtn">Find</label><br>
				</td>
				<td>
					<input type="radio" id="filterRBtn" name="rBtn" onclick="changeAction(this);" value="Filter">
					<label for="filterRBtn">Filter</label><br>
				</td>
			</tr>
		<c:if test="${sessionScope.role == 'ADMIN'}">
			<tr><td>By Role:</td>
				<td colspan="2">
					<select name="userRoleSearch">
						<option value="NONE">Ignore Role</option>
						<option value="ADMIN">Administrator</option>
						<option value="HOST">Host</option>
						<option value="GUEST">Guest</option>
					</select>
				</td>
			</tr>
		</c:if>
		<tr>
			<td>By Username (leave blank to ignore):</td>
			<td colspan="2"><input type="text" name="username" id="username"/></td>
		</tr>
		<tr>
			<td>By First Name (leave blank to ignore):</td>
			<td colspan="2"><input type="text" name="firstName" id="firstName"/></td>
		</tr>
		<tr>
			<td>By Last Name (leave blank to ignore):</td>
			<td colspan="2"><input type="text" name="lastName" id="lastName"/></td>
		</tr>
		<tr><td>By Gender:</td>
			<td colspan="2">
				<select name="gender">
					<option value="NONE">Ignore Gender</option>
					<option value="MALE">Male</option>
					<option value="FEMALE">Female</option>
				</select>
			</td>
		</tr>
		<tr><td colspan="3"><input type="submit" id="findFilterSubmitBtn" class="submit-button basic" value="Find User"/></td></tr>
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

<script>
function changeAction(myRadio) {
    var currentId = myRadio.id;
    if(currentId=="findRBtn") {
    	document.getElementById("findFiterUserForm").action = "\/WEBProjRA502013\/FindUserServlet";
    	document.getElementById("findFilterSubmitBtn").value = "Find User";
    } else if(currentId=="filterRBtn") {
    	document.getElementById("findFiterUserForm").action = "\/WEBProjRA502013\/FilterUserServlet";
    	document.getElementById("findFilterSubmitBtn").value = "Filter Users";
	}
}
</script>

</body>
</html>