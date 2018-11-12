<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>	
a {
    cursor: pointer;
}
</style>
 <!-- Bootstrap Core CSS -->
	<script src="./resources/jquery.datetimepicker.js" type="text/javascript"></script>
	<script src="./resources/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="./resources/jquery-ui-1.9.2.custom.min.js" type="text/javascript"></script>
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <link href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <link href="./resources/jquery.datetimepicker.css" rel="stylesheet">
    <!-- Bootstrap Core JavaScript -->
    <!-- <script src="js/bootstrap.min.js"></script> -->
</head>
<body>
<center> <a href="<c:url value="/tenantDetails"/>"> Tenant Details</a> &nbsp;&nbsp;&nbsp;
         <a href="<c:url value="/lmomspAgreementDetails"/>"> Agreement Details</a> </center>
</body>
</html>