<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>         

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>::APFGC::</title>
<style>	
a {
    cursor: pointer;
}
</style>

</head>
<script>
$(document).ready(function() {
	 $('input[type="checkbox"]').prop('checked',false); 
	$('#id_container').hide();
	$('#id_container1').hide();
	var dbMsg = "${errormsg}";
	
	if(dbMsg!=""){
		$("#id_container").addClass('dberrorMsg');
		$('#id_container').find('ol').append('<li style="list-style: none;">${errormsg}</li>');
		$("#id_container").show();	
		
	}
	
var dbsuccessMsg = "${msg}";
	
	if(dbsuccessMsg!=""){
		$("#id_container1").addClass('dberrorMsg');
		$('#id_container1').find('ol').append('<li style="list-style: none;">${msg}</li>');
		$("#id_container1").show();	
		
	}
  
   $('.checkboxClass').click(function (){
	  
	   //alert("in click:::");
	  
	  if($(".roleNameSelectID").val()=="-1")
			{
			alert("Please Select Role");
			$(this).prop("checked", false);
			} 
	 
   });   
 
 
 $("#saveID").click(function(){
	   var checkCount = $("input[name='rowID']:checked").length;
	  var serv='';
		if($(".roleNameSelectID").val()=="-1"){
			alert("Please select Role");
			return false;
		}
		
		else if(checkCount==0){
             alert("Select Atleast One checkbox");
             return false;
           }
		 
		 
		else if(confirm("Are you sure you want to add?")) {
			   $("#roleHidID").val($(".roleNameSelectID").val());
			   $("input[name='rowID']:checked").each(function () {
	               
	               var servIDS = $(this).val();
	                if(servIDS!=""){
	                	serv += servIDS+"_";
	                }
	                  
					
	               });
			   
			 //  alert(serv+"serv");
			  
				  $("#serviceHiddenID").val(serv);
				  
				  $("#servicesFormID").attr('action',"addRoleServices.do");
				  $("#servicesFormID").submit(); 
			}
	  else{
		  return false;
	  }

 });
 
 
 $(".roleNameSelectID").change(function(){
	  $('input[type="checkbox"]').prop('checked',false); 
		  
		   $.ajaxSetup({
	      		async: false
	      	});
		  
		   $.getJSON("getRoleServicesList.do", {roleID: $(".roleNameSelectID").val()}, function(res){
			 
			for(var i=0;i<res.length;i++){

				$("#"+res[i].funcName).prop('checked', true);
			}
	  });
 });
  
});

</script>
<body>
<br><br><br><br><br><br><br><br><br><br>
 <div width="1024px">
        <div id="page-wrapper">
<!-- Page Heading -->
<form:form action="#" id="rolesFormID" method="post" commandName="rolesDTO">
<div align="center">
<div class="popuptxt" align="center">
 		<%-- <form:hidden path="roleId"  id="roleHidID" class="roleHidClass"/> --%>
		
</div>
</div>

<div id="id_container" class="error"><ol id="id_ol"></ol></div>
<div id="id_container1" class="success"><ol id="id_ol"></ol></div>
<br>
<div class="row">
	<div class="col-lg-6">

 		<div class="form" align="left" style="margin-top:20px;">
			<label>Role Name</label>
			<%-- <form:input path="roleName" id="roleNameHidID" class="form-control"/> --%>
			 
			  <%-- <form:select path="roleName" items="${rolesList}"/> --%>
			 
			   <select path="roleName" class="roleNameSelectID" style="padding: 5px; -webkit-border-radius: 8px; margin-bottom: 7px; margin-top: -10px; margin-left:10px;">    
			   	 <form:option value="-1" label="--Please Select--"/> 
			 	 <form:options items="${rolesListMap}" />
			   </select>
 		</div>
  	</div>
</div>
</form:form>
<form:form action="#" id="servicesFormID" method="post" commandName="functionsDTO">
	<form:hidden path="rolesHiddenValue"  id="roleHidID" class="roleHidClass"/>
    <form:hidden path="servicesHiddenValues" id="serviceHiddenID"/> 
                <div class="row">
        <div class="col-lg-6" align="center">
                       <!--  <h2>Acquisition</h2> -->
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th width="30%" bgcolor="#518ed1"><span style="color:#FFFFFF">Function Name</span></th>
                                        <th width="10%" bgcolor="#518ed1"><span style="color:#FFFFFF">Grant?</span></th>
                                    </tr>
                                </thead>
                                
                                <tbody>
                                	<c:forEach var="functionsListVar" items="${functionsList}">
                                				<tr>
                                					<td>${functionsListVar.funcName}</td>
                                					<td><form:checkbox path="rowID" id="${functionsListVar.funcID}" value="${functionsListVar.funcID}" class="checkboxClass"/></td>
                                				</tr>
                                	</c:forEach>
                                </tbody>
                              <%--   <tbody>
                                	<c:forEach var="moduleNameVar" items="${moduleNameMap}">
                                    <tr>
                                     	<td>${moduleNameVar.value}</td>
                                     	<td></td>
                                     	<td></td>	
                                     	<td></td>			 
                                     	<td><form:checkbox path="rowID" value="1"/></td>
                                     </tr>
                                     
                                     <c:forEach var="subModuleNameVar" items="${subModuleNameMap}">
                                     <c:if test="${moduleNameVar.key == fn:substring(subModuleNameVar.key,0,2)}">
                                     <tr>
                                     	
                                     	<td></td>
                                     	<td>${subModuleNameVar.value}</td>	
                                     	<td></td>			 
                                     	<td></td>
                                     	<td><form:checkbox path="rowID" value="1"/></td>
                                     </tr>
                                     </c:if>
                                  
                                     
                                     <c:forEach var="functionNameVar" items="${functionNameMap}">
										<c:if test="${moduleNameVar.key == fn:substring(functionNameVar.key,0,2)}">
											<c:if test="${subModuleNameVar.key == fn:substring(functionNameVar.key,0,4)}">
                                     		<tr>
                                     			<td></td>
                                     			<td></td>	
                                     			<td>${functionNameVar.value}</td>			 
                                     			<td></td>
                                     			<td><form:checkbox path="rowID" value="1"/></td>
                                     		</tr>
                                     		</c:if>
                                     	</c:if>
                                     	
                                     	<c:forEach var="functionBlockVar" items="${functionBlockMap}">
											<c:if test="${moduleNameVar.key == fn:substring(functionBlockVar.key,0,2)}">
												<c:if test="${subModuleNameVar.key == fn:substring(functionBlockVar.key,0,4)}">
													<c:if test="${functionNameVar.key == fn:substring(functionBlockVar.key,0,6)}">
                                     					<tr>
			                                     			<td></td>
			                                     			<td></td>	
			                                     				 
			                                     			<td></td>
			                                     			<td>${functionBlockVar.value}</td>		
			                                     			<td><form:checkbox path="rowID" value="1"/></td>
                                     					</tr>
                                     				</c:if>
                                     			</c:if>
                                     		</c:if>
                                     	</c:forEach>
                                     		
                                     </c:forEach>
                                    </c:forEach>
                                   
                                     	</c:forEach>
                                </tbody> --%>
                            </table>
                        </div>
          </div>
            </div></form:form>
            <table width="610px">
                            <tr>
                            	<td align="right" id="">
            				<span class="form">
            					<input type="submit" id="saveID" value="Save" style="color:#fff; background-color:#39b54a; border-radius:4px;"/>
          					</span>
          				</td>
                            </tr>
                            </table>
            <!-- /.container-fluid -->
  </div>
  </div>


  

</body>
</html>