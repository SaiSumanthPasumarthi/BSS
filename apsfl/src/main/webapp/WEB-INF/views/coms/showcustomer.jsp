<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
table.dataTable tbody td {
    word-break: break-word;
    vertical-align: top;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	var custType = $('#coms_custTypeLov').val() != undefined ? $('#coms_custTypeLov').val() : "INDIVIDUAL";
	var custId = $('#coms_custId').val() != undefined ? $('#coms_custId').val() : "";
	var url = "showCustomersPagination?custType="+custType+"&custId="+custId;
	$("#indCustomerTables").dataTable({
		"bProcessing" : false,
		"bServerSide" : true,
		"sort" : "position",
		"sAjaxSource" : url,
		"aoColumnDefs": [
	                      { "sWidth": "22%", "aTargets": [ 0 ] },
	                      { "sWidth": "12%", "aTargets": [ 1 ] },
	                      { "sWidth": "32%", "aTargets": [ 2 ] },
	                      { "sWidth": "10%", "aTargets": [ 3 ] },
	                      { "sWidth": "20%", "aTargets": [ 4 ] },
	                      { "sWidth": "4%", "aTargets": [ 5 ] , orderable: false},
	                     ], 

		"aoColumns" : [
						{"mData": "",
							"render" : function(data, type, full, meta) {
		                    	return "<td>"+full.custName+" "+full.lName+"</td>";
		                 	 }
				        },
						{"mData" : "aadharNo"},
						{"mData": "",
							"render" : function(data, type, full, meta){
			                 	return "<td>"+full.address1+" "+full.address2+"</td>";
			                }
			             },
						{"mData" : "pocMob1"},
						{"mData" : "pocMob2"},
						{"mData" : "",
							"render" : function(data, type,full, meta) {
								return '<td>'
								+ '<form class="addCafForm" action="#" method="post">'
								+ '<input type="hidden" name="custId" value="'+full.custId+'" />'
								+ '<input type="hidden" name="custCode" value="'+full.custId+'" />'
								+ '<input type="hidden" name="custTypeLov" value="'+full.custTypeLov+'" />'
								+ '<span style="cursor:pointer" type="submit" class="addCafs"  title="Add Caf"><img src="./resources/images/apf_add.png">'
								+ '</form>'
								+ '</td>';
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
			<h2>
				Display<strong> Customers</strong>
			</h2>
			<c:if test="${not empty message}">
			<center id="comsErrorMsg">
				<font color='green' size="3">${message}</font>
			</center>
			</c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Display Customer</li>
				</ol>
			</div>
		</div>
		<form action="" name="createCustomerForm" id="createCustomerForm" method="post">
			<c:if test="${not empty enterpriseCustomerVO}">
				<input type="hidden" name="custCode" value="${enterpriseCustomerVO.custCode}" />
				<input type="hidden" name="custId" id="coms_custId"  value="${enterpriseCustomerVO.custId}" />
				<input type="hidden" name="orgName" value="${enterpriseCustomerVO.orgName}" />
				<input type="hidden" name="billCycle" value="${enterpriseCustomerVO.billCycle}" />
				<input type="hidden" name="dateOfIncorporation" value="${enterpriseCustomerVO.dateOfIncorporation}" />
				<input type="hidden" name="custTypeLov" id = "coms_custTypeLov" value="${enterpriseCustomerVO.custTypeLov}" />
				<input type="hidden" name="uidNo" value="${enterpriseCustomerVO.uidNo}" />
				<input type="hidden" name="emailId" value="${enterpriseCustomerVO.emailId}" />
				<input type="hidden" name="pocName" value="${enterpriseCustomerVO.pocName}" />
			</c:if>
			<button id="createCustomer" class="btn btn-embossed btn-primary" type="submit">New Customer</button>
		</form>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row m-b-10">
							<div class="col-sm-12">
								<table class="table table-alt" id="indCustomerTables">
									<thead>
										<tr>
											<!-- <th width="5%">S.No</th> -->
											<th>Customer Name</th>
											<th>Aadhar No/Registration No</th>
											<th>Address</th>
											<th>Contact No</th>
											<th>Telephone No</th>
											<th>ACTION</th>
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
