<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>         
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Trouble Ticket</h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li class="active">Create TT</li>
          </ol>
        </div>
      </div>
      
       <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="createTTFormID" method="post" commandName="troubleTicketDTO">
			
			
			<input type="hidden" name="ticketFor" id="ticketForHiddenID"/>
			<input type="hidden" name="tenantcode" id="tenantCodeID"/>
			<input type="hidden" name="flag" id="flagID" value="searchFLAG"/>
			
			  <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>Create TT for ${ticketFor}</strong></h3>
                    </div>
                    <div class="panel-content" align="center">
                      <div class="row">
                      
                       <div class="col-sm-2">
                   			<div class="form-group">
                      		<label class="control-label">Code</label> 
                      		<input type="text" name="assignedTo" id="assignedToID" class="form-control form-white" maxlength="50" placeholder="Enter Code">
                      		
                    		</div>
                        </div>
                        
                        
					    <div class="col-sm-2">
                   			<div class="form-group">
                      		<label class="control-label">Mobile Number</label> 
                      		<input type="text" name="contactMobile" id="MobileNoID" class="form-control form-white number" maxlength="10" placeholder="Enter Mobile Number">
                      		
                    		</div>
                        </div>
					                           
                        <div class="col-sm-2">
                   			<div class="form-group">
                      		<label class="control-label">Landline Number</label> 
                      		<input type="text" name="contactLandline" id="landlineNoID" class="form-control form-white" maxlength="20" placeholder="Enter Landline Number">
                      	
                    		</div>
                        </div>
                        </div>
                        
                         <div class="row">
					  
                        					
						 <div class="col-sm-8" style="float: left;">
                   			<div class="form-group">
                      		<label class="control-label"></label>
                      		<input type="button" id="searchID" value="Search" class="btn btn-embossed btn-primary" />
                      		<input type="submit" id="cancelID" value="Cancel" class="btn btn-outline btn-danger"/>
                    		</div>
                        </div>
						
						
						</div>
                      </div>   
                        <div class="row">
                       <div class="col-sm-10" id="ttHistoryDivID">
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
	
	$("input:text").keypress(function(event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
	$("#cancelID").click(function(){
		$("#createTTFormID").attr('action',"createTT.do");
		$("#createTTFormID").submit();
	});
	
	$('#ttHistoryDivID').html("");
	
	$("#ticketForHiddenID").val("${tenantType}");
	
	//$(".createTTClass").click(function(){
		
	$(document).on('click', '.createTTClass', function(){
		
		var selectedTenant = $(this).closest('tr').find('.tenantHiddenClass').val();
		
		$("#tenantCodeID").val(selectedTenant);
		$("#createTTFormID").attr('action',"createTroubleTicket.do");
		$("#createTTFormID").submit();
		
	});
	
	
	$("#searchID").click(function(){
			
			$('#ttHistoryDivID').html("");
			$('#id_container').find('ol').html("");
			 var check_cnt = 0;
			  if( $("#landlineNoID").val()== "" && $("#MobileNoID").val()=="" && $("#assignedToID").val()==""){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Atleast one field</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}   
			  
			  if( $("#MobileNoID").val()!="" && $("#MobileNoID").val().length<10){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Valid Mobile Number</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}   
			  if(check_cnt == 0)
			  {
		   
			var search = {
					"tenantTypeLov":"${tenantType}",
				    "contactLandline": $("#landlineNoID").val(),
				    "contactMobile": $("#MobileNoID").val(),
				    "code":$("#assignedToID").val(),
				   };
		   	
		  // 	var troubleTicketDTO = JSON.stringify(troubleTicket); 
		 //  alert('in click111'+"${tenantType}");
		   $.ajax({ 
      	     type: 'GET', 
      	     //contentType : 'application/json; charset=utf-8',
      	     dataType : 'json',
      	     async: false,
      	     url: 'getLMOorMSPsInfo', 
      	     data:search,
      	     beforeSend: function() {
			   ajaxindicatorstart("Loading...")
            },
            complete: function() {
               ajaxindicatorstop()
            },
      	     success: function(data) { 
      	   // 	 alert("in success");
      	    //	 alert(data.length);
				 var html = "";
				 var tabelBody ="";
				 html ='<table class="table table-alt" id="ttHistoryID" >'
					 	+' <thead>'
					 	+'<tr>'
					 	+'<th>Name</th>'
					 	+'<th>Mobile No</th>'
					 	+'<th>LandLine No</th>'
					 	+'<th>Email</th>'
					 	+'<th>Address</th>'
					 	+'<th>Action</th>'
					 	+'</tr>'
					 	+'</thead>'
					 	+'<tbody>';
					 	if(data.length == 0){
					 		$('#ttHistoryID').dataTable( {
					 			  "columns": [
					 			    { "searchable": false },
					 			    null,
					 			    null,
					 			    null,
					 			   null,
					 			   null					 			  ]
					 			} );
					 	}
					 	else{
				 $.each(data, function(idx, obj){ 
					 html = html +'<tr>'
					 				+'<td>'+obj.localOfficePocName+'</td>'
					 				+'<td>'+obj.localOfficePocMobileNo1+'</td>'
					 				+'<td>'+obj.localOfficeLandline1+'</td>'
					 				+'<td>'+obj.localOfficeEmailId1+'</td>'
					 				+'<td>'+obj.localOfficeAddress1+'</td>'
					 				+'<td><input type="hidden" class="tenantHiddenClass" value="'+obj.tenantCode+'"/><a href="#" class="createTTClass">Create TT</a></td>'
					 				
					 				+'</tr>';
				 });
					 	}
				 html = html + '</tbody>'
				 				+ '</table>';
				 $('#ttHistoryDivID').append(html);
				 $("#ttHistoryID").dataTable();
	}
      	     });}
			  else{
				  return false;
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