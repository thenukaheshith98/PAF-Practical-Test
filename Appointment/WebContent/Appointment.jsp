<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   	<%@ page import="com.Appointment" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/Appointment.js"></script>
	<meta charset="ISO-8859-1">
<title>Appointment</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">

				<h2>Appointment Management </h2>
				<form id="formItem" name="formItem" method="post" action="Appointment.jsp">
					Patient ID: 
					<input id="p_id" name="p_id" type="text"class="form-control form-control-sm">
					 <br> Patient Name:
					<input id="p_name" name="p_name" type="text"class="form-control form-control-sm"> 
					<br> Patient Age: 
					<input id="p_age" name="p_age" type="text"class="form-control form-control-sm"> 
					<br> Type of case: 
					<input id="type_of_case" name="type_of_case" type="text"class="form-control form-control-sm">
					 <br> Doctor Name: 
					<input id="d_name" name="d_name" type="text"class="form-control form-control-sm">
					 <br>  
					<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
				<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>


				<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
  
   <br>

  		 <div id="divItemsGrid"> 
			 <%
			   
			   Appointment a = new Appointment();
			   out.print(a.readItems());
			   %>
		</div>

			</div>
		</div>
	</div>

</body>
</html>