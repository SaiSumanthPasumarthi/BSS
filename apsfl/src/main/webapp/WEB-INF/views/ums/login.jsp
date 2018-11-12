<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
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
<body class="account separate-inputs boxed theme-blue" data-page="login" >
<!-- BEGIN LOGIN BOX -->
<div class="container" id="login-block">

  <div class="row">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
    <label>${error}</label>
      <div class="account-wall">
	  <img src="./resources/images/APSFL.png" alt="logo" class="login-logo" />
        <form:form class="form-signin" id="loginFormID" action="#" role="form" method="post" accept-charset="utf-8" commandName="usersDTO">
          <div class="append-icon m-b-15">
            <input type="text" name="j_username" id="loginID" autocomplete="off" autofocus class="form-control form-white username" placeholder="LoginId" required>
            <i class="icon-user"></i> </div>
          <div class="append-icon m-b-15">
            <input type="password" name="j_password" id="pwdInString" autocomplete="off" class="form-control form-white password" placeholder="Password" required>
            <i class="icon-lock"></i> </div>
          <button type="submit" id="submit-form" class="btn btn-primary btn-block btn-embossed">Sign In</button>
         <!--  <input type="button" id="submit-form" value="Log In" class="btn btn-primary btn-block btn-embossed"/> -->
          <div class="clearfix">
          <div class="col-sm-">
           <p class="pull-left underline"><a  id="forgotPasswordID" href="./forgotPasswordPage">Forgot password.?</a></p>
          </div>
          <div class = "col-sm-8">
           <a  id="downloadApk" href="./downLoadApk">
   			 <button type="button" class="btn-primary">Download Mobile App</button>
    		</a>
          </div>
          </div>
        </form:form>
      </div>
    </div>
  </div>
   
</div>
<!-- END LOCKSCREEN BOX -->
<script src="./resources/js/jquery-1.11.1.min.js"></script> 
<script src="./resources/js/jquery-migrate-1.2.1.min.js"></script> 
<script src="./resources/js/bootstrap.min.js"></script> 
<script src="./resources/js/nprogress.js"></script> <!-- Progress bar on page load --> 
<script src="./resources/js/jquery.validate.min.js"></script> 
<script src="./resources/js/menu.js"></script> <!-- Menu --> 
<script src="./resources/js/login1.js"></script> <!-- Login page scripts --> 
<script src="./resources/js/jquery.cookie.js"></script>
</body>

<script type="text/javascript">
$(document).ready(function() {
	$.removeCookie("color-theme", {path: "/"});
/* var dbMsg = "${msg}";
	
	if(dbMsg!=""){
		$("#login-block").addClass('dberrorMsg');
		$('#login-block').find('ol').append('<li style="list-style: none;">${msg}</li>');
		$("#login-block").show();	
		dbMsg=="";
	}
	 */
	
	/*  $("#forgotPasswordID").click(function(){
		 alert("in click");
		  $("#loginFormID").attr('action',"sendPasswordToMail.do");
		  $("#loginFormID").submit();
		});  */
		
	$("#submit-form").click(function(){
		 var check_cnt = 0;
		 
		 if(check_cnt==0)
			{
// 				$('#login-block').hide();
 				  $("#loginFormID").attr('action',"j_spring_security_check");
				  $("#loginFormID").submit();
			}
	
	});	
});
history.forward();
</script> 
<style>
	.underline:hover {
    text-decoration: underline;
}
</style>
</html>
 