<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>         
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Error Management</h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li><a href="#">Error Management</a> </li>
            <li class="active">ME TP Error Process</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="meUsageErrorsFormID" method="post" commandName="usageErrorsSearchDTO">
			
			
			<input type="hidden" name="recordID" class="recHiddenClass"/>
			 <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                      <h3><strong>ME TP Error Process</strong></h3>
                    </div>
                    <div class="panel-content" align="center">
                      <div class="row">
                         
                         <div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">From</label> 														   
							<input type="text" name="fromDate" id="fromDatepickerID" class="date-picker form-control form-white" style="width: 200px; " maxlength="50" placeholder="From">
						</div>
						</div>
						
						<div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">To</label> 														   
							<input type="text" name="toDate" id="toDatepickerID" class="date-picker form-control form-white" style="width: 200px; " maxlength="50" placeholder="To">
						</div>
						</div>
					  
                       	<div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">Status</label> 														   
							<form:select path="status" id="statusID" style="width: 200px; " class="form-control form-white">
								<form:option value="-1" label="--Select Status--" />
								<form:options items="${statusMap}"/>
							</form:select>
						</div>
						 </div>
						 <div class="col-sm-4" style="float:center;">
                   			<div class="form-group">
                      		<label class="control-label"></label>
                      		<input type="button" id="searchID" value="Search" class="btn btn-embossed btn-primary" />
                      		
                    		</div>
                        </div>
					    </div>   
                     </div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                       <div class="col-sm-10" id="usageErrorsDivID">
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
	
	$(document).on("click",".updateClass",function() { 
		alert("in click");
		
		$(".recHiddenClass").val($(this).closest('tr').children('td:eq(1)').text());
		
		alert($(".recHiddenClass").val());
		
		  $("#meUsageErrorsFormID").attr('action',"updateTPError.do");
		  $("#meUsageErrorsFormID").submit();
	});
	/*  $("#fromDatepickerID").datepicker({
			maxDate : new Date(),
		}); */
	 
	 $("#fromDatepickerID").datepicker({
			
			format : 'dd-mm-yy',
			autoclose : true,
			changeMonth: true,
	        changeYear: true,
	        maxDate:new Date(),
	        onSelect: function (selected) {
	            var dt = new Date(selected);
	            dt.setDate(dt.getDate());
	           
	            $("#toDatepickerID").datepicker("option", "minDate", dt);
	        }
		});
		
		 $("#fromDatepickerID").datepicker({
				maxDate : new Date(),
			});
		 
		 $("#toDatepickerID").datepicker({
				
				format : 'dd-mm-yy',
				//startDate : '0d',
				autoclose : true,
				changeMonth: true,
		        changeYear: true,
		        maxDate:new Date(),
		        onSelect: function (selected) {
		            var dt = new Date(selected);
		            dt.setDate(dt.getDate());
		           
		            $("#fromDatepickerID").datepicker("option", "maxDate", dt);
		        }
			});
			
			
			 $("#toDatepickerID").datepicker({
					maxDate : new Date(),
				});
	
	$("#searchID").click(function(){
			$('#id_container').find('ol').html("");
			$('#usageErrorsDivID').html("");
			 var check_cnt = 0;
			 
			   if($("#fromDatepickerID").val()!=""){	
				  if($("#toDatepickerID").val() == ""){
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select To Date</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}  }
			  
			  if($("#toDatepickerID").val()!=""){	
				  if($("#fromDatepickerID").val() == ""){
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select From Date</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}}
			  
			  if($("#statusID option:selected").val()=="-1" && $("#fromDatepickerID").val()== "" && $("#toDatepickerID").val()== ""){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select From Date and To Date or any other field</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}   
			  
			  if(check_cnt == 0)
			  {
				var usageErrorsSearchDTO = {
				    "status": $("#statusID :selected").val(),
				    "fromDate": $("#fromDatepickerID").val(),
				    "toDate": $("#toDatepickerID").val(),
				};
		   	
		   $.ajax({ 
      	     type: 'GET', 
      	     dataType : 'json',
      	     async: false,
      	     url: 'getTPUsageErrors', 
      	     data:usageErrorsSearchDTO,
      	     success: function(data) { 
      	    	 var html = "";
				 var tabelBody ="";
				 html ='<table class="table table-alt data_table  datatable full-width" id="usageErrorsID">'
					 	+' <thead>'
					 	+'<tr>'
					 	+'<th>File Seq. No.</th>'
					 	+'<th>Record ID</th>'
					 	+'<th>Service Sub Type</th>'
					 	+'<th>Role Of Node</th>'
					 	+'<th>Service Category</th>'
					 	+'<th>Calling Party Address</th>'
					 	+'<th>Called Party Address</th>'
					 	+'<th>Start Time</th>'
					 	+'<th>End Time</th>'
					 	+'<th>Call Duration</th>'
					 	+'<th>Service ID</th>'
					 	+'<th>Error Code</th>' 
					 	+'<th>Created On</th>'
					 	+'<th>Processed On</th>'
					 	+'<th>Status</th>'
					 	+'<th>Action</th>'
					 	+'</tr>'
					 	+'</thead>'
					 	+'<tbody>';
					 	if(data.length == 0){
					 		$('#usageErrorsID').dataTable( {
					 			  "columns": [
					 			    { "searchable": false },
					 			    null,
					 			    null,
					 			    null,
					 			   null,
					 			    null,
					 			    null,
					 			   null,
					 			    null,
					 			    null,
					 			   null,
					 			    null,
					 			    null,
					 			   null,
					 			    null,
					 			    null,
					 			   null,
					 			    null,
					 			    null,
					 			    null,
					 			    null,
					 			    null,
					 			    null,
					 			    null,
					 			   null,
					 			  null,
					 			 null,
					 			null,
					 			null,
					 			    ]
					 			} );
					 	}
					 	else{
				 $.each(data, function(idx, obj){ 
					 html = html +'<tr>'
								    +'<td>'+obj.fileSeqNo+'</td>'
					 				+'<td>'+obj.recid+'</td>'
					 				+'<td>'+obj.srcSrvcSubtype+'</td>'
					 				+'<td>'+obj.roleOfNode+'</td>'
					 				+'<td>'+obj.serviceCategory+'</td>'
					 				+'<td>'+obj.callingPartyAddress+'</td>' 
					 				+'<td>'+obj.calledPartyAddress+'</td>'
					 				+'<td>'+obj.starttime+'</td>'
					 				+'<td>'+obj.endtime+'</td>'
					 				+'<td>'+obj.callDuration+'</td>'
					 				+'<td>'+obj.serviceID+'</td>' 
					 				+'<td>'+obj.errorcode+'</td>' 
					 				+'<td>'+obj.createdon+'</td>'
					 				+'<td>'+obj.processedon+'</td>'
					 				+'<td>'+obj.status+'</td>'
					 				+'<td><a href="#" class="updateClass">Update</a></td>'
					 				+'</tr>';
				 });
					 	}
				 html = html + '</tbody>'
				 				+ '</table>';
				 $('#usageErrorsDivID').append(html);
				 $("#usageErrorsID").dataTable();
				    
		   
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
<script src="./resources/js/tableExport.js"></script> <!-- Select Inputs -->
<script src="./resources/js/jquery.base64.js"></script> <!-- Export table -->
<script src="./resources/js/select2.min.js"></script> <!-- Export table -->
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/dataTables.bootstrap.js"></script>
<script src="./resources/js/jquery.table2excel.js"></script>
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing --> 
<script src="./resources/js/table_dynamic.js"></script> <!-- Tables Dynamic --> 