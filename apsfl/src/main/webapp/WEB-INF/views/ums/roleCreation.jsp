<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>         
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>User Role Management</h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li class="active">Role Creation</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="rolesFormID" method="post" commandName="rolesDTO">
			<form:hidden path="roleID"  id="roleHidID" class="roleHidClass"/>
			 
              <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3>User<strong> Role Management</strong></h3>
                    </div>
                    <div class="panel-content" align="center">
                      <div class="row">
					  	<div class="col-sm-4">
                   			<div class="form-group">
                      		<label class="control-label">Role Name</label>
                      		<form:input type="text" path="roleName" id="roleNameHidID" maxlength="90" class="form-control form-white" placeholder="Enter Role Name" value="" style="width: 200px; "/>
                    		</div>
                        </div>
                        
                        <div class="col-sm-1">
                   			<div class="form-group">
                      		<label class="control-label">Active ?</label>
                      		<form:checkbox path="rowID" value="1" style="width: 20px;height: 20px;" class="activeClass" id="activeID" disabled="true" checked="checked"/>
                    		</div>
                        </div>
                        
                        <div class="col-sm-7" style="float: left;">
                   			<div class="form-group">
                      		<label class="control-label"></label>
                      		<input type="submit" id="saveID" value="Save" class="btn btn-embossed btn-primary" /> 
            				<input type="submit" id="updateID" value="Update" class="btn btn-outline btn-danger"/>
                    		</div>
                        </div>
                      </div>
                      
                      <%-- <div width="60%" align="right">
                      <td>Active ?<form:checkbox path="rowID" value="1" class="activeClass" id="activeID" disabled="true" checked="checked"/>
                      </td>
                      <!-- <td> 
            				<input type="submit" id="saveID" value="Save" class="btn btn-embossed btn-primary" /> 
            				<input type="submit" id="updateID" value="Update" class="btn btn-outline btn-danger"/>
            		  </td> -->
              		  </div> --%>
              		  
              		  <div class="row">
                        <div class="col-sm-12 enable-search">
                          <table class="table table-alt" id="resultList">
                            <thead>
                              <tr>
                                <th width="20%">Role Code</th>
                                <th width="50%">Role Name</th>
                                <th width="10%">Active ?</th>
                                <th width="10%">Edit</th>                            
                              </tr>                             
                            </thead>
                            <tbody>                            
                                <c:forEach items="${rolesList}" var="rolesListVar" varStatus="status">
                              <tr>
                                <td><c:out value="${rolesListVar.roleID}"/></td>
                                <td><input type="hidden" name="roleHiddenID" value="${rolesListVar.roleID}" id="rolesHidID" class="roleHiddenClass"/><c:out value="${rolesListVar.roleName}"/></td>
                                	<c:if test="${rolesListVar.status==1}">
                                <td><form:checkbox path="gridRowID" value="1" style="width: 20px;height: 20px;" class="gridactiveClass" id="gridactiveID" disabled="true" checked="checked"/></td>
                                	</c:if>
							        
							        <c:if test="${rolesListVar.status==0}">
                                <td><form:checkbox path="gridRowID" value="0" style="width: 20px;height: 20px;" class="griduncheckedactiveClass" id="activeID" disabled="true"/></td> 
                                	</c:if>  
                                	<td><a class="btn btn-sm btn-dark editAnchorClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a></td>
       								<!-- <td><a id="deleteAnchorID" class="deleteAnchorClass"><img src="./resources/um/images/pms_delete.png" width="24" height="24"></a></td> -->                             
                              </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      <!--   <tr>
       			 			<td align="left" style="padding-right: 280px;"><div id="paginationDivID"></div> </td>
       	    			</tr> -->
                      </div>              		  
                      </div>
                      </div>
                    </div>
                  </div>
                  </form:form>
                </div>
              </div>
              <!-- END ROW -->   
              
			  
			  <!-- END FORM -->
            </div>
            <!-- END MAIN PANEL CONTENT --> 
          </div>
          <!-- END MAIN PANEL --> 
        </div>
        <!-- HERE COMES YOUR CONTENT --> 
      </div>
      <!-- END MAIN ROW -->
      <!-- END PAGE CONTENT -->   
 
  <!-- END MAIN CONTENT --> 
<style>	
a {
    cursor: pointer;
}
</style>

<script>
$(document).ready(function() {
	
	$("#id_container").show().delay(5000).fadeOut('slow');
	$("#id_container1").show().delay(5000).fadeOut('slow');

	$('#resultList').dataTable({"sPaginationType": "full_numbers",});
	
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
		$("#id_container1").addClass('dberrorMsg');
		$('#id_container1').find('ol').append('<li style="list-style: none;">${successMsg}</li>');
		$("#id_container1").show();	
		
	}

  $("#roleNameHidID").val("");
  $("#updateID").hide();
  $("#saveID").show();
  
  $(".editAnchorClass").live('click',function(){
	  $("#updateID").show();
	  $("#saveID").hide();
	  
	  $(".activeClass").prop('disabled', false);
	 	//alert($(this).closest("tr").find($(".gridactiveClass")).val()+"gridactiveclass");
	  if($(this).closest("tr").find($(".gridactiveClass")).val() == "1")
		  {
		     $(".activeClass").prop('checked', true);
		     
		  }
	  else{
		  $(".activeClass").prop('checked', false);
	  }
	  $("#roleHidID").val($(this).closest("tr").find($(".roleHiddenClass")).val());
	  $("#roleNameHidID").val($(this).closest("tr").find("td:eq(1)").text());
  });


  $("#saveID").click(function(){
		var check_cnt = 0;
	  $("#updateID").hide();
	  $("#saveID").show();
	  $('#id_container').find('ol').html('');
	  if($("#roleNameHidID").val()==""){
			$("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Role</li>');
			$("#id_container").show();	
			check_cnt=check_cnt+1;
		} 
	  if(check_cnt == 0)
		  {
	  $("#rolesFormID").attr('action',"addRole.do");
	  $("#rolesFormID").submit();
		  }
	  else{
		  return false;
	  }
  });
  
  /* $('#paginationDivID').smartpaginator({totalrecords: "${totalRecords}",
      recordsperpage: 5, 
	  controlsalways:true,
      datacontainer: 'resultList', 
      dataelement: 'tr',
      theme: 'green'
      });
  
   */
  
  $("#updateID").click(function(){
	  //alert("in update");
	  $("#updateID").hide();
	  $("#saveID").show();
	  var check_cnt = 0;
	  if($("#roleNameHidID").val()==""){
			$("#id_container").addClass('dberrorMsg');
			$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Role</li>');
			$("#id_container").show();	
			check_cnt=check_cnt+1;
		} 
	  if(check_cnt == 0)
	  {
	     if($(".activeClass").val()==1){
		  $("#rolesFormID").attr('action',"modifyRole.do");
		  $("#rolesFormID").submit();
	     }else{
		  $("#roleHidID").val($(this).closest("tr").find($(".roleHiddenClass")).val());
		  $("#roleNameHidID").val($(this).closest("tr").find("td:eq(1)").text());
		  $("#rolesFormID").attr('action',"deleteRole.do");
		  $("#rolesFormID").submit();
	    }
	  }else{
		  return false;
	  }
	  
	 /*  if(confirm("Are you sure you want to modify?")) {
			
		$("#rolesFormID").attr('action',"modifyRole.do");
		  $("#rolesFormID").submit();
		  }
		  else{
			  return false;
		  } */
  });
  
 /*  $(".deleteAnchorClass").click(function(){
	  $("#roleHidID").val($(this).closest("tr").find($(".roleHiddenClass")).val());
	  $("#roleNameHidID").val($(this).closest("tr").find("td:eq(1)").text());
	  
	  if(confirm("Are you sure you want to delete?")) {
			
			$("#rolesFormID").attr('action',"deleteRole.do");
			  $("#rolesFormID").submit();
			  }
			  else{
				  return false;
			  }
  });
   */
   $(".activeClass").change(function(){
	  /* $('#rolesFormID').serialize();
	  alert("in click:::");
	  if($(this).is(":checked"))
	  { alert("in if:::");
		  $(".activeClass").val("1");
	  }
	  else if(!$(this).is(":checked")){
		  alert("in else if:::");
		  $(".activeClass").val("0");
		  
		  alert( $(".activeClass").val()+"after");
	  } */
	  
	   $(this).val( $(":checked").length > 0 ? "1" : "0");
  }); 
   
   $(document).on( "keyup", "#roleNameHidID", function() {
		
   if (this.value.match(/[^a-zA-Z/s]/g)) {
       this.value = this.value.replace(/[^a-zA-Z/s]/g, '');
   }
});
  
});

</script>  
	 <!-- END FOOTER --> 
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
<script src="./resources/js/jquery.mCustomScrollbar.concat.min.js"></script> <!-- Custom Scrollbar sidebar --> 
<script src="./resources/js/select2.min.js"></script> <!-- Select Inputs -->
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing --> 
<script src="./resources/js/table_dynamic.js"></script> <!-- Tables Dynamic --> 