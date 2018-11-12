<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page session="false"%>
<style>
	table.dataTable tbody td {
		word-break: break-word;
		vertical-align: top;
	}
	.hiddenField {
		display: none;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$(".message_id").show().delay(5000).fadeOut('slow');
		$("#tenantTables").dataTable({
			"bProcessing" : false,
			"bServerSide" : true,
			"sort" : "position",
			"sAjaxSource" : "tmsHomePagination",
			"aoColumnDefs" : [
			                  {"sWidth": "10%" , "aTargets": [ 0 ] }, 
			                  {"sWidth": "10%" , "aTargets": [ 1 ] }, 
			                  {"sWidth": "10%" , "aTargets": [ 2 ] }, 
			                  {"sWidth": "10%" ,"aTargets": [ 3 ] }, 
			                  {"sWidth": "10%" , "aTargets": [ 4 ] }, 
			                  {"sWidth": "10%" , "aTargets": [ 5 ] }, 
			            	],
			"aoColumns" : [
			   			{"mData": "",
						    "render" : function(data, type, full, meta){
						       return '<form name = "viewpageForm" class="viewpageForm" action="#" method="post"><input type="hidden" value="'+full.tenantId+'" name="tenantId"/></form><a class="viewPageTenantCode" style="cursor: pointer;" >'+full.tenantCode+'</a>';
						    }
						},
						{"mData" : "enrollmentNo"},
						{"mData" : "tenantName"},
						{"mData" : "tenantTypeLov"},
						{"mData" : "mobile"},
						{"mData": "",
						    "render" : function(data, type, full, meta){
						    	var tdData =  '<td width="10%">';
						    	if(full.tenantType == 'APSFL' || full.tenantType == 'DistrictManager') {
							    	if(full.status == 1) {
							    		tdData += '<form action="<c:url value="/approveTenant"/>" method="post">';
								    	tdData += '<input type="hidden" value="'+full.tenantId+'" name="tenantId">';
								    	tdData += '<input type="hidden" value="accept" name="action">';
								    	tdData += '<input type="submit" class="btn btn-embossed btn-success" value="Approve" style="float: left;" />';
								    	tdData += '</form>';
								    	tdData += '<form action="<c:url value="/approveTenant"/>" method="post">';
								    	tdData += '<input type="hidden" value="'+full.tenantId+'" name="tenantId">';
								    	tdData += '<input type="hidden" value="reject" name="action">';
								    	tdData += '<input type="submit" class="btn btn-embossed btn-danger" value="Reject" style="float: left;" />';
								    	tdData += '</form>';
								    	} else if(full.status == '2') {
								    		tdData += 'Approved';
									    	}
								    	else if(full.status == '3') {
								    		tdData += 'Rejected';
									    	}
							    	} else {
							    		if(full.status == '1')
										    tdData += 'Pending';
									    if(full.status == '2')
										    tdData += 'Approved';
									    if(full.status == '3')
										    tdData += 'Rejected';
								    	}
						    	tdData += '</td>';
						       return tdData;
						    }
						}
					]
				});
			});
</script>
<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Tenant</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">HOME</a> </li>
            <li class="active">TENANT Details</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
	            <div class="row">
	            	<center>
						<font class="message_id" color="Green">${message}</font>
					</center>
	            	<center>
						<font class="message_id" color="Red">${error}</font>
					</center>
					<center>
						<font class="message_id" color="Green"><b>${success}</b></font>
					</center>
	            </div>
	            <div class="row">
									<div class="col-sm-12">
										<table class="table table-alt" id="tenantTables">
											<thead>
												<tr>
													<!-- <th width="5%">S.No</th> -->
													<th >Tenant Code</th>
													<th >Enrollment No</th>
													<th >Tenant Name</th>
													<th >Tenant Type </th>
													<th >Contact Number</th>
													<th >Status</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>