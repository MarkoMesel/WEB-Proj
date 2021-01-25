<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Comment</title>
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
	<h1>Add Comment</h1>
	<form method="post" action="/WEBProjRA502013/AddCommentServlet">
	<div style="color: #00BF00;">${successMessage}</div>
	<table border="1" align="center">
		<tr>
			<td>Message:</td>
			<td><input type="text" name="commentMessage" id="commentMessage" value=""/></td>
			<td><label id="commentMessageError"></label></td>
		</tr>
		<tr>
		<td>Rating:</td>
			<td align="left">
				<select name="commentRating" id="commentRating">
					<option value="0">0</option>
				  	<c:forEach begin="0" end="4" varStatus="loop">
						<option value="${loop.index}.5">${loop.index}.5</option>
				  		<option value="${(loop.index)+1}">${(loop.index)+1}</option>
				  	</c:forEach>
				</select>
			</td>
		</tr>
		<tr><td colspan="3"><input type="submit" class="submit-button basic" value="Add Comment"/></td></tr>
	</table>
	</form>
</div>
<script type="text/javascript" src="js/messageValidation.js"></script>
</body>
</html>