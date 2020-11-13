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
	<h1>Manage Amenities</h1>
	<div style="color: #00BF00;">${successMessage}</div>
	<br/>
	<form method="get" action="/WEBProjRA502013/AddAmenityServlet">
		<input class="button" id="addNewAmenity" type="submit" value="Add New Amenity"/>
	</form>
	<br />
	<table border="1" align="center" >
       <c:forEach var="element" items="${sessionScope.amenities}">
       	<tr>
       	   <td align="left"><label><c:out value="${element.name}"></c:out></label></td>
       	   <td align="left"><label><c:out value="${element.details}"></c:out></label></td>
           <td align="center">
	           	<form method="get" action="/WEBProjRA502013/EditAmenityServlet">
	           		<input hidden="true" type="text" name="currentRow" value="${element.id}"/>
	           		<input class="button" id="${element.id}" type="submit" value="Edit"/>
	           	</form>
           </td>
           <td align="center">
	           	<input class="deleteButton" id="${element.name}" 
	           		onclick="displayDeleteModal('${element.name}','${element.id}')" type="submit" value="Delete"/>
           </td>
        </tr>
       </c:forEach>
	</table>
</div>

<div id="id01" class="modal">
</div>

<div id="id02" class="modal2">
  <span onclick="closeDeleteModal()" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" method="post" action="/WEBProjRA502013/DeleteAmenityServlet">
    <div class="container">
      <h1 class="deleteModalHeader" id="deleteHeader">Delete Amenity</h1>
      <p>Are you sure you want to delete this amenity?</p>

      <div class="clearfix">
       	<input hidden="true" type="text" id="currentSelectedAmenity" name="currentSelectedAmenity" value="a"/>
       	<input class="deleteButton small" id="" type="submit" value="Yes"/>
        <button onclick="closeDeleteModal()" type="button" class="button small">No</button>
      </div>
    </div>
  </form>
</div>

<script type="text/javascript" src="js/addApartment.js"></script>
<script>
function displayDeleteModal(name, id) {
	document.getElementById('id01').style.display='block';
	document.getElementById('id02').style.display='block';
	document.getElementById("deleteHeader").innerHTML = "Delete " + name;
	document.getElementById("currentSelectedAmenity").value = id;
}
function closeDeleteModal() {
	document.getElementById('id01').style.display='none';
	document.getElementById('id02').style.display='none';
}
</script>
</body>
</html>