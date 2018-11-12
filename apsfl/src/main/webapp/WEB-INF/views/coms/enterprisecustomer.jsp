<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	$("#entCustomerTables").dataTable({
		"bProcessing" : false,
		"bServerSide" : true,
		"sort" : "position",
		"sAjaxSource" : "enterpriseCustomersPagination",
		"createdRow": function( row, data, dataIndex ) {
			var dataCount = dataIndex + 1;
			$(row).addClass( 'treegrid-'+dataCount+' expanded' );
		},
		"aoColumnDefs": [
	                      { "sWidth": "25%", "aTargets": [ 0 ], sClass : "orgName" },
	                      { "sWidth": "0%", "aTargets": [ 1 ], sClass : "hiddenField custCode" },
	                      { "sWidth": "8%", "aTargets": [ 2 ] },
	                      { "sWidth": "13%", "aTargets": [ 3 ] },
	                      { "sWidth": "14%", "aTargets": [ 4 ] },
	                      { "sWidth": "15%", "aTargets": [ 5 ] },
	                      { "sWidth": "11%", "aTargets": [ 6 ] },
	                      { "sWidth": "12%", "aTargets": [ 7 ], orderable: false },
	                      { "sWidth": "0%", "aTargets": [ 8 ], sClass : "tenantType hiddenField" },
	                      { "sWidth": "0%", "aTargets": [ 9 ], sClass : "count hiddenField" },
	                     ], 
		"aoColumns" : [
						{"mData": "",
							"render" : function(data, type, full, meta) {
		                    	return "<td style='cursor:pointer'><a href='#'>"+full.name+"</a></td>";
		                 	 }
				        },
						{"mData": "",
		                	"render" : function(data, type, full, meta){
		                    	return "<td>"+full.custCode+"</td>";
		                 	 }
				        }, 
						{"mData" : "regnCode"},
						{"mData" : "entTypeLov"},
						{"mData" : "offcTypeLov"},
						{"mData" : "pocName"},
						{"mData": "",
		                	"render" : function(data, type, full, meta) {
		                    	return "<td class='customerName' >"+full.stdCode+"-"+full.landlineNo+"</td>";
		                 	 }
				        },
						{"mData" : "",
							"render" : function(data, type,full, meta) {
								var tdData =  ' <td align="center" style="word-break:break-all;"> '
				                tdData += '<form style="float: left; padding-left: 5px;" name="showEntCustomerForm" action="#" class="showEntCustomerForm" method="post">';
				                tdData += '<input type="hidden" name="custTypeLov" value="'+full.custTypeLov+'" />';
				                tdData += '<input type="hidden" name="custType" value="'+full.entTypeLov+'" />';
				                tdData += '<input type="hidden" name="custCode" value="'+full.custCode+'" />';
				                tdData += '<input type="hidden" name="orgName" value="'+full.name+'" />';
				                tdData += '<input type="hidden" name="billCycle" value="'+full.billCycle+'" />';
				                tdData += '<input type="hidden" name="dateOfIncorporation" value="'+full.dateOfIncorporation+'" />';
				                tdData += '<input type="hidden" name="custId" value="'+full.custId+'" />';
				                tdData += '<input type="hidden" name="uidNo" value="'+full.regnCode+'" />';
				                tdData += '<input type="hidden" name="emailId" value="'+full.emailId+'" />';
				                tdData += '<input type="hidden" name="pocName" value="'+full.pocName+'" />';
				                tdData += '<input type="hidden" name="officeTypeLov" value="'+full.offcTypeLov+'" />';
				                tdData += '<input type="hidden" id="tenantType" value="'+full.tenantType+'" />';
				                if(full.tenantType == 'APSFL') {
		                  		   tdData += '<span style="cursor:pointer" class="showEntCustomer" title="BulkCafs Upload"><img src="./resources/images/upload.png"></span>';
		                  		} else {
		                  			tdData += '<span style="cursor:pointer" class="showEntCustomer" title="Add Customer"><img src="./resources/images/apf_add.png"></span>';
		                  		}
				                tdData += ' </form>';
				                tdData += '<form style="float: left; padding-left: 5px;" name="addNodeForm" action="#" class="addNodeForm" method="post">';
				                tdData += '<input type="hidden" name="custTypeLov" value="'+full.custTypeLov+'" />';
				                tdData += '<input type="hidden" name="custType" value="'+full.entTypeLov+'" />';
				                tdData += '<input type="hidden" name="emailId" value="'+full.emailId+'" />';
				                tdData += '<input type="hidden" name="customerCode" value="'+full.custCode+'" />';
				                tdData += '<input type="hidden" name="billCycle" value="'+full.billCycle+'"  />';
				                tdData += '<input type="hidden" name="officeTypeLov" value="'+full.offcTypeLov+'" />';
				                tdData += '<input type="hidden" name="orgCode" value="'+full.regnCode+'" />';
				                tdData += '<input type="hidden" name="dateOfIncorporation" value="'+full.dateOfIncorporation+'" />';
				                tdData += '<span style="cursor:pointer" class="addNode"  title="Add Node"><img src="./resources/images/apf_add.png"></span>';
				                tdData += ' </form>';
	                  		    if(full.tenantType == 'APSFL') {
			                  	    tdData += '<form style="float: left; padding-left: 5px;" name="viewEntCustomerForm" action="#" class="viewEntCustomerForm" method="post">';
		                  		    tdData += '<input type="hidden" name="custTypeLov" value="'+full.custTypeLov+'" />';
		                  		  	tdData += '<input type="hidden" name="custType" value="'+full.entTypeLov+'" />';
		                  		    tdData += '<input type="hidden" name="custCode" value="'+full.custCode+'" />';
		                  		    tdData += '<input type="hidden" name="orgName" value="'+full.name+'" />';
		                  		    tdData += '<input type="hidden" name="billCycle" value="'+full.billCycle+'"  />';
		                  		    tdData += '<input type="hidden" name="dateOfIncorporation" value="'+full.dateOfIncorporation+'" />';
		                  		    tdData += '<input type="hidden" name="custId" value="'+full.custId+'" />';
		                  		    tdData += '<input type="hidden" name="uidNo" value="'+full.regnCode+'" />';
		                  		    tdData += '<input type="hidden" name="emailId" value="'+full.emailId+'" />';
		                  		    tdData += '<input type="hidden" name="pocName" value="'+full.pocName+'" />';
		                  		    tdData += '<input type="hidden" name="customerCode" value="'+full.custCode+'" />';
		                  		    tdData += '<input type="hidden" name="bulkUploadFlag" value="SingalCaf" />';
		                  		    tdData += '<input type="hidden" name="officeTypeLov" value="'+full.offcTypeLov+'" />';
		                  		    tdData += '<span style="cursor:pointer" class="viewEntCustomer"  title="Add CAF"><img src="./resources/images/apf_add.png"></span>';
		                  		    tdData += ' </form>';
	                  		    }
	                  		  if(full.status == '0') {
		                  		    tdData += '<form style="float: left; padding-left: 5px;" name="editEntCustomerForm" action="#" class="editEntCustomerForm" method="post">';
		                  		    tdData += '<input type="hidden" name="customerCode" value="'+full.custCode+'" />';
		                  		    tdData += '<input type="hidden" name="modify" value="1" />';
		                  		    tdData += '<span style="cursor:pointer" class="editEntCustomer" title="Edit Organization"><img src="./resources/images/apf_edit.png"></span>';
	                  			    tdData += ' </form>';
					                tdData += ' </td>';
	                  		  }
				                return tdData;
							}
						},
						{"mData": "",
							"render" : function(data, type, full, meta){
		                    	return "<td class='tenantType hiddenField' style='display:none;'>"+full.tenantType+"</td>";
		                 	 }
				        },  
				        {"mData": "",
							"render" : function(data, type, full, meta){
		                    	return "<td class='hiddenField' style='display:none;'>"+full.rowCount+"</td>";
		                 	 }
				        },  
					]
			});
		});
</script>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Enterprise<strong> Customer</strong>
			</h2>
			<c:if test="${not empty message && !fn:contains(message, 'Error-No phonenos available in the given STD area')}">
				<center id="comsErrorMsg">
					<font color='green' size="3">${message}</font>
				</center>
			</c:if>
			<c:if test="${not empty message && fn:contains(message, 'Error-No phonenos available in the given STD area')}">
				<center id="comsErrorMsg">
					<font color='red' size="3">${message}</font>
				</center>
			</c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Enterprise Customer</li>
				</ol>
			</div>
		</div>
		<a href="./createEntCustomer"><button class="btn btn-embossed btn-primary" type="button">Create Organization</button></a>
		<c:if test="${tenantType == 'APSFL'}">
			<a href="./cafBulkUploadErrors"><button class="btn btn-embossed btn-primary" type="button">View Caf Bulk Upload Errors</button></a>
		</c:if>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row m-b-10">
							<div class="col-sm-12">
								<table class="table table-alt" id="entCustomerTables">
									<thead>
										<tr>
											<th width="27%">Organization Name</th>
											<th style="display: none;"></th>
											<th width="8%">Organization<br /> Code</th>
											<th width="13%">Org Type</th>
											<th width="14%">Org Sub Type</th>
											<th width="15%">Contact Person<br /> Name</th>
											<th width="11%">Contact No</th>
											<th width="12%"></th>
											<th style="display: none;"></th>
											<th style="display: none;"></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<!-- END ROW INNER-->
						<div class="clear"></div>
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
<!-- END MAIN CONTENT -->