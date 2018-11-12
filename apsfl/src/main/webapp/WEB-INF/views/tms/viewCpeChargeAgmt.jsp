<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>
						CPE Charges<strong> Agreement View</strong>
					</h2>
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
							
							<div class="row" >
										<div class="col-sm-12">
											<table class="table table-alt" id="cpeAggrListTable">
												<thead>
													<tr>
														<th>Agreement Id</th>
														<th>From</th>
														<th>Tenant Name</th>
														<th>Tenant Type</th>
														<th hidden="hidden">Template Code</th>
														<th>Select</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${cpeAggrList}" var="aggr1" varStatus="rowCount">
														<tr>
															<td>${aggr1.aggrId}</td>
															<td>${aggr1.aggrFDate}</td>
															<td>${aggr1.tenantName}</td>
															<td>${aggr1.tenantType}</td>
															<td hidden="hidden" class = "tmplCode">${aggr1.templCode}</td>
															<td><input type="radio" name="r1" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class=" row col-sm-12">
											<div class="col-sm-6" id="chargeCodesDateByTemplate">
											</div>
											<!-- <div class="col-sm-4" id="selecedTemplateTenantsdata1">
											</div> -->
											<div class="col-sm-6" id="selecedTemplateChargeCodesdata1">
											</div>
									</div>
									
									<div class=" row "> </div>
									<div class=" row "> </div>
									<div class=" row "> </div>
									<div class=" row "> </div>
									<div class=" row "> </div>
									<div class=" row "> </div>
									<div class=" row "> </div>
									<div class=" row "> </div>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('#cpeAggrListTable').dataTable();
		var tmplCode = "";
		$('#cpeAggrListTable').on("click",'input[type=radio]',function() {
			
			ajaxindicatorstart("Loading..");
			
			var $row = $(this).closest('tr');
			tmplCode = $row.find('.tmplCode').text();
			 $('#selecedTemplateTenantsdata1').html("");
			 $('#selecedTemplateChargeCodesdata1').html("");
			 $('#chargeCodesDateByTemplate').html("");
			 
			 
			 $.ajax({ 
        	     type: 'GET', 
        	     async: false,
        	     url: 'getAllTenantsByTemplCode', 
        	     data: {tmplCode:tmplCode},
        	     contentType: 'application/json',
        	     success: function(data) { 
				 var html = "";
				 var tabelBody ="";
				 html ='<table class="table table-alt" id="tenantsCpeAgrtListTable" >'
					 	+' <thead>'
					 	+'<tr>'
					 	+'<th>Sno</th>'
					 	+'<th>Tenant Type</th>'
					 	+'<th>Select Tenant</th>'
					 	+'</tr>'
					 	+'</thead>'
					 	+'<tbody>';
				 $.each(data, function(idx, obj){ 
					 html = html +'<tr>'
					 				+'<td>'+obj.sno+'</td>'
					 				+'<td>'+obj.tenantType+'</td>'
					 				+'<td><select class="form-control form-white SearchTenantClass" name="tenantSelect"  id="'+idx+'" width = "90%">'
					 				+'<option value="">-Select-</option>';
					 $.each(obj.tenantList, function(idx, tenant){
						 html = html +'<option value = "'+tenant.tenantCode+'">'+tenant.name+'</option>'
					 });
					 html = html + '</select>'
					 				+'</td>'
					 				+'</tr>';
				 });
				 html = html + '</tbody>'
				 				+ '</table>';
				 $('#selecedTemplateTenantsdata1').append(html);
				 $('.SearchTenantClass').SumoSelect({search: true,});
				 
				 $.ajax({ 
	        	     url: 'getAllTemplateRegions', 
	        	     data: {tmplCode:tmplCode},
	        	     contentType: 'application/json',
	        	     async: false,
	        	     success: function(regions) { 
	        	    	 var html = "";
	        	    	 html = html+'<div class="panel" >'
	        	    	 +'    <div class="panel-header bg-light" >'
	        	    	 +' <h3>Revenue Sharing</h3>'
	        	    	 +' </div>'
	        	    	 +' <div class=" panel-content ScrollStyle " > ';
					 $.each(regions, function(idx, region){
						 $.ajax({ 
			        	     url: 'getAllTenantTypeByTemplateCode', 
			        	     data: {tmplCode:tmplCode, region : region[1]},
			        	     contentType: 'application/json',
			        	     async: false,
			        	     success: function(data) {
							 html = html+'<div class="row"><label class="control-label">'+region[0]+'</label>'	
							 			+'<table  class="table  table-alt" id = "table_Id2"> <thead> <tr>';
							  $.each(data, function(idx, obj){ 
								  html = html+'<th>'+obj.tenanttype+'</th>';
							  		});
							  html = html+'</tr></thead> <tbody><tr>';
							  $.each(data, function(idx, obj){ 
								  html = html+'<td>'+obj.revshareperc+'%</td>';
							  		});
							  		html = html+'</tr> </tbody></table>'
							  				 	+' </div>'
						}
					 });
					});
						 	 +' </div>'
		    				 +' </div>';
		  				 $('#selecedTemplateChargeCodesdata1').append(html);
		  				html="";
					 
					 $.ajax({ 
		        	     url: 'getChargeCodesByTemplate', 
		        	     data: {tmplCode:tmplCode},
		        	     contentType: 'application/json',
		        	     async: false,
		        	     success: function(data) { 
		        	    	 var html = "";
		        	    	 html = html+'<div class="row">'	
					 			+'<table  class="table  table-alt" id="table_Id3"><thead><tr><th>Charge Name</th></tr></thead> <tbody> ';
		        	    	 $.each(data, function(idx, obj){ 
								  html = html+'<tr><td>'+obj.chargeName+'</td></tr>';
							  		});
		        	    	 html = html+' </tbody></table>';
					  		 $('#chargeCodesDateByTemplate').append(html);
					  		html="";
		        	     }
					 });
					 
	        	     }
				 });
				 
        	    }
			 });
			 
			 ajaxindicatorstop();
			 
			 $('#tenantsCpeAgrtListTable, #table_Id2, #table_Id3').DataTable( {
			        "paging":   false,
			        "ordering": false,
			        "info":     false,
			        "searching": false
			    } );
	});
			
});
</script>