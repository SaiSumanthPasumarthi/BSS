<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Role Service Mapping</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./home.do">Home</a></li>
						<li class="active">Role Service Mapping</li>
					</ol>
				</div>
				<div id="id_container" class="error" width="100%" align="center" style="color:red"><ol id="id_ol"></ol></div>
			    <div id="id_container1" class="success" width="100%" align="center" style="color:green"><ol id="id_ol"></ol></div>
			</div>

			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content" align="center">
						<div class="row">
							<form:form action="#" id="rolesFormID" method="post" commandName="rolesDTO">
							
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>
												Role<strong> Service Mapping</strong>
											</h3>
										</div>
									 <div class="panel-content" align="center">
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group" align="center">													
														<label class="control-label">Role Name</label> 														   
															<form:select path="roleName" id="roleNameSelectID" style="width: 200px; " class="form-control form-white">
																<form:option value="-1" label="--Select a Role Name--" />
																<form:options items="${rolesListMap}" />
															</form:select>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
						
							
							
						</form:form>
						</div>
                          <div class="row">
                          <div class="col-sm-12 disable-search" >
							<form:form action="#" id="servicesFormID" method="post" commandName="functionsDTO">
								<form:hidden path="rolesHiddenValue" id="roleHidID" class="roleHidClass" />
								<form:hidden path="servicesHiddenValues" id="serviceHiddenID" />
	
								
									<!-- <div class="col-sm-10 disable-search"> -->
										<table class="table  table-alt"  id="ServiceNamesTable">
											<thead>
												<tr>
													<th width="80%">Service Name</th>
													<th width="20%">Grant ?</th>
	
												</tr>
											</thead>
											<tbody>
	                                	     <c:forEach var="functionsListVar" items="${functionsList}">
	                                				<tr>
	                                					<td>${functionsListVar.funcName}</td>
	                                					<td><form:checkbox path="rowID" id="${functionsListVar.funcID}" value="${functionsListVar.funcName}" 
	                                										class="checkboxClass" style="width: 20px;height: 20px;"/></td>
	                                				</tr>
	                                		</c:forEach>
	                                        </tbody>
										</table>
									<!-- </div> -->
									<div id="">
										<button class="btn btn-embossed btn-success" type="submit"
											id="saveID" value="Save" style="margin-left: 810px;">Save</button>
									</div>
								
							</form:form>
						</div>
						</div>
				</div>
						
					</div>
				</div>

			</div>
			</div>
		</div>
<style>
a {
	cursor: pointer;
}
</style>
<script>
$(document).ready(function() {
	$("#ServiceNamesTable").find("tr:even").css("background-color", "#f2f2f2");
	$("#id_container").show().delay(5000).fadeOut('slow');
	$("#id_container1").show().delay(5000).fadeOut('slow');
	
		 // $('#ServiceNamesTable').dataTable({"sPaginationType": "full_numbers",});

			/*  var DistTable = $('#ServiceNamesTable').dataTable({
		 "sPaginationType": "full_numbers",
		 "lengthMenu": [ 50, 60, 80, 100],
		 "pageLength": 50
	});   */
	
	$('#id_container').hide();
	$('#id_container1').hide();
	
	var dbMsg = "${errorMsg}";
	
	if(dbMsg!=""){
		$("#id_container").addClass('error');
		$('#id_container').find('ol').append('<li style="list-style: none;">${errorMsg}</li>');
		$("#id_container").show();	
	}
	
    var dbsuccessMsg = "${successMsg}";
	
	if(dbsuccessMsg!=""){
		$("#id_container1").addClass('success');
		$('#id_container1').find('ol').append('<li style="list-style: none;">${successMsg}</li>');
		$("#id_container1").show();	
	}  
	  
		
	 $('input[type="checkbox"]').prop('checked',false); 
	  
   $('.checkboxClass').live('click',function (){	  
	  
	  if($("#roleNameSelectID").val()=="-1")
			{
			alert("Please Select Role");
			$(this).prop("checked", false);
			} 	 
   });   
 
 
 	$("#saveID").click(function(){
	 
	    $('#id_container').hide();
		$('#id_container1').hide();
	 
	    $('#id_container').find('ol').html('');
		$('#id_container1').find('ol').html('');
	 
	  var checkCount = $("input[name='rowID']:checked").length;
	  // alert(checkCount+" checkcount")
	  var serv='';
		if($("#roleNameSelectID option:selected").text()=="-1"){
			alert("Please select Role");
			return false;
		}
		
		 else if(checkCount==0){
             alert("Select Atleast One checkbox");
             return false;
           } 
		 
		 
		else {
			   $("#roleHidID").val($("#roleNameSelectID option:selected").text());
			   $("input[name='rowID']:checked").each(function () {
	              // alert($(this).val()+"  ddddd")
	               var servIDS = $(this).val();
	                if(servIDS!=""){
	                	serv += servIDS+"_";
	                }                  
					
	               });		 
			  
				  $("#serviceHiddenID").val(serv);				  
				  $("#servicesFormID").attr('action',"addRoleServices.do");
				  $("#servicesFormID").submit(); 
			}
 });
 
 
 $("#roleNameSelectID").change(function(){
	 
	    $('#id_container').hide();
		$('#id_container1').hide();
	 
	    $('#id_container').find('ol').html('');
		$('#id_container1').find('ol').html('');
		
	  $('input[type="checkbox"]').prop('checked',false); 
		  
		   $.ajaxSetup({
	      		async: false
	      	});
		  
		   $.getJSON("getRoleServicesList.do", {roleID: $("#roleNameSelectID option:selected").text()}, function(res){
			 
			   $(".checkboxClass").prop('disabled', false);
			   $("#saveID").prop('disabled', false);
			for(var i=0;i<res.length;i++){	
				//alert(res[i].funcName+" function name in json ");
				$("#"+res[i].funcName).prop('checked', true);
			}
			if(res.length > 0){
				if(res[0].checkRole == "success"){
					$(".checkboxClass").prop('disabled', true);
					$("#saveID").prop('disabled', true);
				}else
					$(".checkboxClass").prop('disabled', false);
			}
		
	  });
 });
  
});

</script>



