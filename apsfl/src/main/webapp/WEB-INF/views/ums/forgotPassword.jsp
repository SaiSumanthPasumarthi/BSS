<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="admin-themes-lab">
<meta name="author" content="themes-lab">
<!-- <link rel="shortcut icon" href="images/favicon.png" type="image/png"> -->
<title>:: Andhra Pradesh State FiberNet Ltd ::</title>
<link href="./resources/css/style.css" rel="stylesheet">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="./resources/js/lada.min.js" rel="stylesheet"> -->
<!-- <link href="./resources/css/theme.css" rel="stylesheet"> -->
<link href="./resources/css/ui.css" rel="stylesheet">
<link href="./resources/css/layout.css" rel="stylesheet">
<link href="./resources/css/custom.css" rel="stylesheet">
<script src="./resources/js/modernizr-2.6.2-respond-1.1.0.min.js"></script>

</head>
<!-- BEGIN BODY -->
<body class="account separate-inputs boxed "
	data-page="login">
	<!-- BEGIN LOGIN BOX -->
	<div class="container" id="login-block">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div id="id_container" class="successMsg" style="color: green">
					<ol id="id_ol"></ol>
				</div>
				<div id="id_container1" class="errorMsg" style="color: red">
					<ol id="id_ol"></ol>
				</div>

				<div class="account-wall">
					<img src="./resources/images/logo.png" alt="logo" class="login-logo" />
					<form action="#" name="forgotPwdDTO" id="forgotPasswordFormID" method="post" >
						<div class="form-group">
							<h2 style="align: center;">
								<font face="Calibri">Forgot Password</font>
							</h2>
							<label class="control-label">Enter your Login ID<span style="color: red;">*</span></label> 
							<input type="text" name="loginID" id="loginID" class="form-control form-white" placeholder="Enter Login ID" required></input>
						</div>
						<div class="form-group">
							<label class="control-label">&nbsp;</label>
							<button class="btn btn-embossed btn-primary" id="saveID" type="button" value="Save">Submit</button>
							<button class="btn btn-embossed btn-danger" id="cancelID" type="button" value="Back">Cancel</button>
						</div>
					</form>
				</div>

			</div>
			<!-- END MAIN PANEL CONTENT -->
		</div>
		<p class="account-copyright">
			<span>Copyright &copy; 2016 TERA SOFTWARE LIMITED. All rights reserved </span>
		</p>
		<!-- END MAIN PANEL -->
	</div>
	<!-- HERE COMES YOUR CONTENT -->
	<!-- END MAIN ROW -->
	<div class="clear"></div>
	<!-- END PAGE CONTENT -->
	<!-- END LOCKSCREEN BOX -->
	<script src="./resources/js/jquery-1.11.1.min.js"></script>
	<script src="./resources/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
	<script src="./resources/js/nprogress.js"></script>
	<!-- Progress bar on page load -->
	<script src="./resources/js/jquery.validate.min.js"></script>
	<script src="./resources/js/menu.js"></script>
	<!-- Menu -->
	<script src="./resources/js/login1.js"></script>
	<!-- Login page scripts -->
	<!-- <script src="./resources/js/custom.js"></script> -->
	<!-- custom file -->
	<script src="./resources/js/jquery.cookie.js"></script>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#loginID').keyup(function() {
			str = $(this).val()
			str = str.replace(/\s/g, '')
			$(this).val(str)
		});
	
		/* $("#id_container").hide();
		$("#id_container1").hide(); */
		//alert("in alert");
		//alert("in ready");
		var dbMsg = "${msg}";
		if (dbMsg != "") {
			$("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">${msg}</li>');
			$("#id_container").show();
		}
		var dbsuccessMsg = "${errormsg}";
		if (dbsuccessMsg != "") {
			$("#id_container1").addClass('dberrorMsg');
			$('#id_container1').find('ol').append('<li style="list-style: none;">${errormsg}</li>');
			$("#id_container1").show();
		}
		$("#saveID").click(function() {
			var check_cnt = 0;
			if($('#loginID').val().length<=0)
			{ 	$("#id_container1").addClass('dberrorMsg');
				$('#id_container1').find('ol').append('<li style="list-style: none;">Please Enter Login ID </li>');
				$("#id_container1").show();
					check_cnt++;
			 } else	if (check_cnt == 0) {
				$("#forgotPasswordFormID").attr('action', "./forgotPwd");
				$("#forgotPasswordFormID").submit();
			}
			else {
				return false;
			}
		});
	
		$("#cancelID").click(function() {
			//$("#forgotPasswordFormID").attr('method',"get");
			$("#forgotPasswordFormID").attr('action', "./changePasswordLogin");
			$("#forgotPasswordFormID").submit();
		});
	});
history.forward();
</script>
</html>
