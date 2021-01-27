<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Overview</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="site-header">
		<div class="site-header-content">
		<a href="home.jsp">
			<img src="images/logo.png" alt="webProjLogo">
		</a>
		</div>
	</div>
	<div class="div-wrapper maximum-width">
		<h1 align="center">User Overview</h1>
		<div class="div-left-side">
			<table class="table-overview">
				<c:forEach var="user" items="${sessionScope.users}">
					<tr>
					<td>
						<div class="div-wrapper">
						  <label>Username: <b>${user.username}</b></label><br/>
						  <label>First Name: <b>${user.firstName}</b></label><br/>
						  <label>Last Name: <b>${user.lastName}</b></label><br/>
						  <label>Gender: <b>${user.gender}</b></label><br/>
						</div>  
					  </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	<div class="div-find-filter">
			<form method="post" id="findFiterUserForm" action="/WEBProjRA502013/FindUserServlet">
		<h2>Find/Filter</h2>
		<table class="table-entry table-overview">
			<tr >
				<td><label class="label-entry">Find or Filter?</label>
					<input type="radio" class="sub-entry" id="findRBtn" name="rBtn" onclick="changeAction(this);" value="Find" checked>
					<label for="findRBtn">Find</label><br>
					<input type="radio" class="sub-entry" id="filterRBtn" name="rBtn" onclick="changeAction(this);" value="Filter">
					<label for="filterRBtn">Filter</label><br>
				</td>
			</tr>
		<c:if test="${sessionScope.role == 'ADMIN'}">
			<tr>
				<td><label class="label-entry">By Role</label>
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
			<td>
				<label class="label-entry">By Username</label>
				<input type="text" name="username" id="username"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">By First Name</label>
				<input type="text" name="firstName" id="firstName"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">By Last Name</label>
				<input type="text" name="lastName" id="lastName"/>
			</td>
		</tr>
		<tr>
			<td>
				<label class="label-entry">By Gender</label>
				<select name="gender">
					<option value="NONE">Ignore Gender</option>
					<option value="MALE">Male</option>
					<option value="FEMALE">Female</option>
				</select>
			</td>
		</tr>
	</table>
	<input type="submit" id="findFilterSubmitBtn" class="submit-button basic" value="Find User"/>
	</form>
	</div>
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