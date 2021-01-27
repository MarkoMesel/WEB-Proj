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
	<div class="mainPanel">
		<div style="color: #00BF00;">${successMessage}</div>
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
				<input type="submit" class="submit-button edit-profile" value="Edit Profile"/>
			</form>
			<form method="get" action="/WEBProjRA502013/ChangePasswordServlet">
				<input type="submit" class="submit-button change-password" value="Change Password"/>
			</form>
			<form method="get" action="/WEBProjRA502013/UserOverviewServlet">
				<input type="${sessionScope.role == 'GUEST' ? 'hidden' : 'submit'}" class="submit-button user-overview" value="User Overview"/>
			</form>
			<form method="get" action="/WEBProjRA502013/AddApartmentServlet">
				<input type="${sessionScope.role == 'HOST' ? 'submit' : 'hidden'}" class="submit-button add-apartment" value="Add Apartment"/>
			</form>
			<form method="get" action="/WEBProjRA502013/ManageAmenitiesServlet">
				<input type="${sessionScope.role == 'ADMIN' ? 'submit' : 'hidden'}" class="submit-button manage-amenities" value="Manage Amenities"/>
			</form>
			<form method="get" action="/WEBProjRA502013/AddHostServlet">
				<input type="${sessionScope.role == 'ADMIN' ? 'submit' : 'hidden'}" class="submit-button add-host" value="Add Host"/>
			</form>
			<form method="get" action="/WEBProjRA502013/ReservationOverviewServlet">
				<input type="submit" class="submit-button reservation-overview" value="Reservation Overview"/>
			</form>
		</c:if>
		<form method="get" action="/WEBProjRA502013/ApartmentOverviewServlet">
			<input type="submit" class="submit-button apartment-overview" value="Apartment Overview"/>
		</form>
		<c:if test="${sessionScope.id == null}">
			<input type="submit" class="submit-button register" value="Register Account" onClick="window.location='registerGuest.jsp';"/>
			<br/>
			<input type="submit" class="submit-button login" value="Login" onClick="window.location='login.jsp';"/>
		</c:if>
		<c:if test="${sessionScope.id != null}">
			<form method="post" action="/WEBProjRA502013/LogoutServlet">
				<input type="submit" class="submit-button logout" value="Logout"/>
			</form>
		</c:if>
	</div>
</body>
</html>