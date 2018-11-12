<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<section>
	<!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					Change<strong> Password</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./home.do">Home</a></li>
						<li class="active">Change Password</li>
					</ol>
				</div>
				<div id="id_container" class="error" style="color:red; width: 100%; align: center;"><ol id="id_ol"></ol></div>
			 	<div id="id_container1" class="success" style="color: green; width: 100%; align: center;"><ol id="id_ol"></ol></div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
						
							<form:form action="#" id="changePwdFormID" method="post" commandName="usersDTO">
							<form:hidden path="loginID"  value="${loginID}"/>
							<div id="id_container" class="error"><ol id="id_ol"></ol></div>
							<div id="id_container1" class="success"><ol id="id_ol"></ol></div>
							         	 <%-- <tr>
							           	 	<td>
							            		<P class="form" align="center"><label>Old Password</label>
							            			<form:input  path="oldPwd"  id="oldPwdID"/>
							            		</P>
							            </td>
							           </tr> --%>
							           <div class="panel-content">
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
							            		<label class="control-label">New Password</label>
							            			<form:password path="newPwd" id="newPwdID" class="form-control form-white "/>
							            		</div>
							            		</div>
							            		</div>
							            		<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
							            		<label class="control-label">Confirm Password</label>
							            			<form:password path="cnfPwd" id="cfmPwdID" class="form-control form-white "/>
							            		</div>
							            		</div>
							            		</div>
							            		
							            		<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">&nbsp;</label>
															<button class="btn btn-embossed btn-primary" id="saveID"  value="Save">Save Password</button>
															<button class="btn btn-embossed btn-danger" id="cancelID" value="Back">Cancel</button>
															<div class="option-group">&nbsp;</div>
														</div>
													</div>
												</div>
							            		</div>
							</form:form>
							<!-- END FORM -->
						</div>
						<!-- END MAIN PANEL CONTENT -->
					</div>
					<!-- END MAIN PANEL -->
				</div>
				<!-- HERE COMES YOUR CONTENT -->
			</div>
			<!-- END MAIN ROW -->
			<div class="clear"></div>
		</div>
		<!-- END PAGE CONTENT -->
	</div>
</section>


<script>
$(document).ready(function() {

	$('#newPwdID').keyup(function(){
	    str = $(this).val()
	    str = str.replace(/\s/g,'')
	    $(this).val(str)
	});
	
	$('#id_container').hide();
	$('#id_container1').hide();
	var dbMsg = "${errorMsg}";
	
	if(dbMsg!=""){
		$("#id_container").addClass('dberrorMsg');
		$('#id_container').find('ol').append('<li style="list-style: none;">${errorMsg}</li>');
		$("#id_container").show();	
	}
	
var dbsuccessMsg = "${successMsg}";
	
	if(dbsuccessMsg!=""){
		/* $("#id_container1").addClass('dberrorMsg');
		$('#id_container1').find('ol').append('<li style="list-style: none;">${successMsg}</li>');
		$("#id_container1").show();	 */
		
		alert("Password Changed Successfully. Please Re-Login !");
		
		//$("#changePwdFormID").attr('method',"get");
		 $("#changePwdFormID").attr('action',"changePasswordLogin.do");
		  $("#changePwdFormID").submit();
		
		
	}

 
  $("#saveID").click(function(){

	  var check_cnt = 0;
	  $('#id_container').find('ol').html(''); 
	 
	  /*  $.ajaxSetup({
    		async: false
    	});
	  alert($("#oldPwdID").val()+"old password");
	  
  $.getJSON("checkOldPassword.do", {oldPwd: $("#oldPwdID").val()}, function(res){
			 alert("in getjson res");
				for(var i=0;i<res.length;i++){
					alert(res+"res");
				}
		 });   */
  
   /*  if($("#oldPwdID").val()==""){
		$("#id_container").addClass('dberrorMsg');
		$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter old Password</li>');
		$("#id_container").show();	
		check_cnt=check_cnt+1;
	}    */
	  
	if($("#newPwdID").val()==""){
			$("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter New Password</li>');
			$("#id_container").show();	
			check_cnt=check_cnt+1;
		} 
	else if($("#newPwdID").val().length < 4 ){
		$("#id_container").addClass('dberrorMsg');
		$('#id_container').find('ol').append('<li style="list-style: none;">New Password must be atleast 4 characters</li>');
		$("#id_container").show();
		$("#newPwdID").val('');	
		
		check_cnt=check_cnt+1;
	} 
	  
	  if($("#cfmPwdID").val()==""){
			$("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Confirm Password</li>');
			$("#id_container").show();
			$("#newPwdID").val('');		
			check_cnt=check_cnt+1;
		} 
	  
	  else if($("#newPwdID").val()!=$("#cfmPwdID").val()) {
		  $("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">New Password and confirm Password are not Matched</li>');
			$("#id_container").show();	
			$("#cfmPwdID").val('');	
			$("#newPwdID").val('');
			check_cnt=check_cnt+1;
	   }
	  
	  if(check_cnt == 0)
	  {
		  $("#changePwdFormID").attr('action',"updatePassword.do");
		  $("#changePwdFormID").submit();
	  }
	  else{
		  return false;
	  }  
	 
	  
  });

  $("#cancelID").click(function() {
		$("#changePwdFormID").attr('action', "home.do");
		$("#changePwdFormID").submit();
	});
  
  });


</script>