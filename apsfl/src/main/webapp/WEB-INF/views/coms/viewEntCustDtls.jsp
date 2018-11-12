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
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
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
	                      { "sWidth": "25%", "aTargets": [ 0 ], sClass : "orgNames" },
	                      { "sWidth": "0%", "aTargets": [ 1 ], sClass : "hiddenField custCode" },
	                      { "sWidth": "8%", "aTargets": [ 2 ], sClass : "hiddenField custId" },
	                      { "sWidth": "0%", "aTargets": [ 3 ]},
	                      { "sWidth": "13%", "aTargets": [ 4 ] },
	                      { "sWidth": "14%", "aTargets": [ 5 ] },
	                      { "sWidth": "15%", "aTargets": [ 6 ] },
	                      { "sWidth": "11%", "aTargets": [ 7 ] },
	                      { "sWidth": "12%", "aTargets": [ 8 ], orderable: false },
	                      { "sWidth": "0%", "aTargets": [ 9 ], sClass : "tenantType hiddenField" },
	                      { "sWidth": "0%", "aTargets": [ 10 ], sClass : "count hiddenField" },
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
						{"mData": "",
		                	"render" : function(data, type, full, meta){
		                    	return '<td class="custId" value="'+full.custId+'"/>'+full.custId+"</td>";
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
							      var tdData =  '<td align="center" style="word-break:break-all;"> '
			                  		  tdData += '<input type="hidden" name="custId" value="'+full.custId+'" />';
				                	  tdData += '<span style="cursor:pointer" class="viewEntCustDtls" style="cursor: pointer;" data-toggle="modal" data-target="#myModal" title="View Caf Details"><img src="./resources/images/apf_view.png"></span>';
						              tdData += '</td>';
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
	
	var dataTable;
	var url = "viewEntCafDtlsPagination?"
	var oTable = $('#entCafDetailsTable').dataTable(dataTable);
	$(document).on('click', '.viewEntCustDtls', function(event){
		$("#entCafDetailsTable > tbody").html("");
		var indx = $(this).index('.viewEntCustDtls');
		var customreId = $(this).closest('tr').find('.custId').text();
		$("#exampletext").val(customreId);
		url = url+$('form').serialize(); 
	    oTable.fnDestroy();
	    dataTable ={   
	  		      "bProcessing": false,
	  		      "sort" : "position",
	  		      "bServerSide": true,
	  		      "sAjaxSource" : url,
	  		      "aoColumnDefs": [
			                       { "sWidth": "10%", "aTargets": [ 0 ] },
			                       { "sWidth": "15%", "aTargets": [ 1 ] },
			                       { "sWidth": "10%", "aTargets": [ 2 ] },
			                       { "sWidth": "10%", "aTargets": [ 3 ] },
			                       { "sWidth": "15%", "aTargets": [ 4 ] },
			                       { "sWidth": "15%", "aTargets": [ 5 ] },
			                       { "sWidth": "15%", "aTargets": [ 6 ] },
			                       { "sWidth": "10%", "aTargets": [ 7 ] },
			                       ], 
			      "aoColumns": [
			                      { "mData": "cafNo" },
			                      { "mData": "",
										"render" : function(data, type, full, meta) {
					                    	return "<td style='cursor:pointer'>"+full.fName+" "+full.lName+"</a></td>";
					                 	 }
							      },
				                  { "mData": "cpeslNo" },
				                  { "mData": "cpeMacAddr" },
				                  { "mData": "cpeAddr" },
				                  { "mData": "",
										"render" : function(data, type, full, meta) {
											var tdData =  ' <td align="center" style="word-break:break-all;"> '
											$.each(full.stbSrlNoMacAddr, function(key, value) {
												tdData+= "<table><tr><td>"+key+" - "+value+"</td></tr></table>";
											});
											tdData+="</td>";
											return tdData;
					                 	 }
							      }, 
							      { "mData": "cafPhoneNo" },
							      { "mData": "status" },
						       ],
	    		}
	  		 
	    oTable = $('#entCafDetailsTable').dataTable(dataTable);
	    url ="viewEntCafDtlsPagination?";
	}); //End of click 
	dataTable= $("#entCafDetailsTable").dataTable();
});
</script>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Enterprise<strong> Customer</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Enterprise Customer</li>
				</ol>
			</div>
		</div>
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
											<th style="display: none;"></th>
											<th width="8%">Organization<br /> Code</th>
											<th width="13%">Org Type</th>
											<th width="14%">Org Sub Type</th>
											<th width="15%">Contact Person<br /> Name</th>
											<th width="11%">Contact No</th>
											<th width="12%">Action</th>
											<th style="display: none;"></th>
											<th style="display: none;"></th>
										</tr>
									</thead>
								</table>
								<form name="example" id="example">
									<input type="hidden" id="exampletext" name="custId"/>
								</form>
							</div>
						</div>
							<div id="myModal" class="modal fade" role="dialog" tabindex='-1'>
								<div class="modal-dialog modal-header-info">
								<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header modal-header-info">
											<button type="button" class="close" data-dismiss="modal">
												<font color='#cc1920'>&times;</font>
											</button>
											<h4 class="modal-title">CAF Details</h4>
										</div>
										<div class=" modal-body model-div-sizes">
											<div class="panel">
												<table class="table  table-alt">
													<tbody>
														<tr class="dataRow">
															<td>
																<!--Display data when click the row-->
																<label id="pkgerror" style="text-align: left;color: red;"></label>
																<div class="panel-header bg-light">
																	<h3>CAF Details</h3>
																</div>
																<div class="panel-content">
																	<div class="row">
																		<div class="col-sm-12" id="#divId">
																			<table class="table table-dynamic table-alt" id="entCafDetailsTable">
																				<thead>
																					<tr>
																						<th>Account No</th>
																						<th>Customer Name</th>
																						<th>ONU Serial No</th>
																						<th>ONU Mac Address</th>
																						<th>ONU Place</th>
																						<th>IPTV Serial No - IPTV Mac Address</th>
																						<th>Landline No</th>
																						<th>Status</th>
																				</thead>
																				<tbody>
																				</tbody>
																			</table>
																		</div>
																		<div style="padding-left: 14px;" class="">
																			<button style="display: none;" class="btn btn-embossed btn-primary" type="button" id="PkgSubmitButton" >Submit</button>
																		</div>
																	</div>
																</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										    <div class="" style="padding-left: 1050px;">
												<button type="button" class="btn btn-embossed btn-danger" data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
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