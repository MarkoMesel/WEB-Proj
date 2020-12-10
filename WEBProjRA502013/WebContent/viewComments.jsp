<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Amenities</title>
	<link href="css/mainpage.css" rel="stylesheet" type="text/css">
	<link href="css/deletemodal.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mainPanel">
	<h1>Comments for chosen apartment</h1>
	<br/>
	<c:if test="${sessionScope.role == 'GUEST' && sessionScope.commentPermission == 'true'}">
		<form method="get" action="/WEBProjRA502013/AddCommentServlet">
			<input class="button" id="addComment" type="submit" value="Add Comment"/>
		</form>
	</c:if>
	<br />
	<table border="1" align="center">
		<tr>
			<th>
				Guest
			</th>
			<th>
				Rating
			</th>
			<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
				<th>
					Accessibility
				</th>
			</c:if>
		</tr>
		<c:forEach var="comment" items="${sessionScope.comments}">
			<c:if test="${!(sessionScope.role == 'GUEST' && comment.hidden == 'true')}">
				<tr>
					<td><c:out value="${comment.guest}" /></td>
					<td><c:out value="${comment.rating}" /></td>
					<c:if test="${sessionScope.role == 'HOST' || sessionScope.role == 'ADMIN'}">
							<td class="${comment.hidden == 'true' ? 'delete' : 'reserve'}">
								${comment.hidden == 'true' ? 'Hidden' : 'Can be seen'}
							</td>
					</c:if>
				</tr>
				<tr >
					<td align="left" colspan="100%"><c:out value="${comment.message}" /></td>
				</tr>
				<c:if test="${sessionScope.role == 'HOST'}">
					<tr>
						<td align="left" colspan="100%">
							<form style="display:inline-block;" method="post" action="/WEBProjRA502013/ViewCommentsServlet">
						 		<input hidden="true" type="text" name="currentRow" value="${comment.id}"/>
						 		<input class="button smaller" id="${comment.id}" type="submit" 
						 			value="${comment.hidden == 'true' ? 'Unhide From Guest' : 'Hide From Guest'}"/>
						 	</form>
					 	</td>
					</tr>
				</c:if>
			</c:if>
		</c:forEach>
	</table>
</div>

<script type="text/javascript" src="js/addApartment.js"></script>
</body>
</html>