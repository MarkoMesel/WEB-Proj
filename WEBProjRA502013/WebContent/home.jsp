<%@page import="java.io.Console"%>
<%@page import="java.awt.print.Printable"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
		<link href="css/mainpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="mainPanel">
		<h1>Welcome!</h1>
		<c:if test="${sessionScope.id != null}">
			<c:choose>
				<c:when test="${sessionScope.role == 'ADMIN'}">
				    <p> You are admin. </p>
				</c:when>
				<c:when test="${sessionScope.role == 'HOST'}">
				    <p> You are host. </p>
				</c:when>
				<c:when test="${sessionScope.role == 'GUEST'}">
					<p> You are guest. </p>
				</c:when>
				<c:otherwise>
					<p> What are you???</p>
				</c:otherwise>
			</c:choose>
			
			<form method="get" action="/WEBProjRA502013/EditProfileServlet">
				<input type="submit" class="submitButton" value="Edit Profile"/>
			</form>
			<form method="get" action="/WEBProjRA502013/UserOverviewServlet">
				<input type="${sessionScope.role == 'GUEST' ? 'hidden' : 'submit'}" class="submitButton" value="User Overview"/>
			</form>
			<form method="get" action="/WEBProjRA502013/AddApartmentServlet">
				<input type="${sessionScope.role == 'HOST' ? 'submit' : 'hidden'}" class="submitButton" value="Add Apartment"/>
			</form>
			<form method="get" action="/WEBProjRA502013/ManageAmenitiesServlet">
				<input type="${sessionScope.role == 'ADMIN' ? 'submit' : 'hidden'}" class="submitButton" value="Manage Amenities"/>
			</form>
		</c:if>
		<form method="get" action="/WEBProjRA502013/ApartmentOverviewServlet">
			<input type="submit" class="submitButton" value="Apartment Overview"/>
		</form>
		<c:if test="${sessionScope.id != null}">
			<form method="post" action="/WEBProjRA502013/LogoutServlet">
				<input type="submit" class="submitButton" value="Logout"/>
			</form>
		</c:if>
	</div>
</body>
</html>