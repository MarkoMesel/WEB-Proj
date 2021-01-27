<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Amenities</title>
	<link href="css/mainstyle.css" rel="stylesheet" type="text/css">
	<link href="css/deletemodal.css" rel="stylesheet" type="text/css">
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
	<h1>Comments for chosen apartment</h1>
	<br/>
	<c:if test="${sessionScope.role == 'GUEST' && sessionScope.commentPermission == 'true'}">
		<form method="get" action="/WEBProjRA502013/AddCommentServlet">
			<input class="submit-button basic" id="addComment" type="submit" value="Add Comment"/>
		</form>
	</c:if>
	<br />
	<table class="table-overview">
		<c:forEach var="comment" items="${sessionScope.comments}">
			<c:if test="${!(sessionScope.role == 'GUEST' && comment.hidden == 'true')}">
				<tr>
					<td class="bottom-spacing">
						<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
							<label><b>${comment.hidden == 'true' ? 'Hidden' : 'Can be seen'}</b></label><br/>
						</c:if>
						<label>Comment by <b>${comment.guest}</b></label><br/>
						<label>Rating: <b>${comment.rating}</b></label><br/>
						<label><b>${comment.message}</b></label><br/>
				<c:if test="${sessionScope.role == 'HOST'}">
					<form style="display:inline-block;" method="post" action="/WEBProjRA502013/ViewCommentsServlet">
				 		<input type="hidden" name="currentRow" value="${comment.id}"/>
				 		<input id="${comment.id}" type="submit" 
				 			value="${comment.hidden == 'true' ? 'Unhide From Guest' : 'Hide From Guest'}"/>
				 	</form>
				</c:if>
				</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</div>

<script type="text/javascript" src="js/addApartment.js"></script>
</body>
</html>