<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
$(document).ready(function() {
	$("#entSubscriberTable").dataTable({
		"bProcessing" : false,
		"bServerSide" : true,
		"sort" : "position",
		"sAjaxSource" : "entSubscriberListReportPagination",
		"aoColumnDefs" : [
		                  {"sWidth": "8%" , "aTargets": [ 0 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "15%" , "aTargets": [ 1 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "10%" , "aTargets": [ 2 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "8%" ,"aTargets": [ 3 ] ,  "sClass" : "table-bordered"}, 
		                  {"sWidth": "10%" , "aTargets": [ 4 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "8%" , "aTargets": [ 5 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "8%" , "aTargets": [ 6 ] , "sClass" : "table-bordered"}, 
		                  {"sWidth": "8%" , "aTargets": [ 7 ] , "sClass" : "table-bordered"},
		                  {"sWidth": "8%" , "aTargets": [ 8 ] , "sClass" : "table-bordered"},
		                  {"sWidth": "8%" , "aTargets": [ 9 ] , "sClass" : "table-bordered"},
		            	],
		"aoColumns" : [
					{"mData" : "custname"},
					{"mData" : "parentcustname"},
				    {"mData" : "tenantname" },
				    {"mData" : "totalcafs" },
					{"mData" : "pendingforcafeditcount"},
					{"mData" : "pendingforpaymentcount"},
					{"mData" : "pendingforprovisioncount"},
			        {"mData" : "activecount"},
					{"mData" : "suspendcount"},
					{"mData" : "inactivecount"}
				]
			});
		});
</script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Government Subscriber Report</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="row">

															<table class="table table-alt" id="entSubscriberTable">
																<thead>
																	<tr>
																		<th>Customer Name</th>
																		<th>Parent Customer Name</th>
																		<th>LMO Name</th>
																		<th>Total CAF's</th>
																		<th>Pending For CAF Edit</th>
																		<th>Pending For CAF Payment</th>
																		<th>Pending For CAF Provision</th>
																		<th>CAF Active</th>
																		<th>CAF Suspend</th>
																		<th>CAF Inactive</th>
																	</tr>
																</thead>
															</table>
														<a href="./downloadEntSubscriberReport"><INPUT TYPE="SUBMIT" VALUE="Download" class="btn btn-success"></a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>