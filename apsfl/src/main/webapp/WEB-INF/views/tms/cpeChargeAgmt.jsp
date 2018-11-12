<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.model-div-size {
	max-height: 500px;
	overflow-y: scroll;
}

.SumoSelect > .CaptionCont{
    width: 208px !important;
}

.ScrollStyle {
	max-height: 150px;
	overflow-y: auto;
	overflow-x: auto;
}
</style>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>
						CPE Charges<strong> Agreement</strong>
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
							
							<div class="row" id="cpeTemplListTableDivId">
										<div class="col-sm-12">
											<table class="table table-alt" id="cpeTemplListTable">
												<thead>
													<tr>
														<th>Template Code</th>
														<th>Template Name</th>
														<th>No of Partners</th>
														<th>Select</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${cpeTmplList}" var="templ" varStatus="rowCount">
														<tr>
															<td class = "tmplCode">${templ.rstmplCode}</td>
															<td>${templ.rstmplName}</td>
															<td>${templ.partnerCnt}</td>
															<td><input type="radio" name="r1" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class=" row col-sm-12">
											<div class="col-sm-4" id="chargeCodesDateByTemplate">
											</div>
											<div class="col-sm-4" id="selecedTemplateTenantsdata1">
											</div>
											<div class="col-sm-4" id="selecedTemplateChargeCodesdata1">
												 
											</div>
									</div>
									<div class="row col-sm-12">
										<button id="id_save_cpeChrgArmt" class="btn btn-embossed btn-primary " > Save</button>
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
		$('#cpeTemplListTable').dataTable();
		var tmplCode = "";
		$('#cpeTemplListTable').on("click",'input[type=radio]',function() {
			
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
	
		$('#id_save_cpeChrgArmt').click(function(){
			ajaxindicatorstart("Loading..");
			var tenantsList = [];
			var flag = false;
			$('#tenantsCpeAgrtListTable > tbody > tr').each(function() {
				var row = $(this);
				var slno = row.find('td:eq(0)').text();
				var tenantType = row.find('td:eq(1)').text();
				var partnerCode = $(this).find('select[name="tenantSelect"] option:selected').val();
				if(partnerCode == "" || partnerCode == undefined){
					alert("Please select tenant at sno :: " +slno);
					flag = false;
					return false;
				}else if(tmplCode == "" ||  tmplCode == undefined){
					alert("Please select Template  ");
					flag = false;
					return false;
				}else{
					flag = true;
					var tenantObj = {
			       			"partnertype":tenantType,
			    		    "partnertypeslno": slno,
			    		    "partnercode": partnerCode,
			    		};
					tenantsList.push(tenantObj);
				}
			});
			
					if(flag){
						
						var agrmtObj = {
			        		    "templCode": tmplCode,
			        		    "partnrsList": tenantsList,
			        		};
						agrmtObj = JSON.stringify(agrmtObj);
						$.ajax({ 
			        	     type: 'POST', 
			        	     url: 'saveCpeChrgAgmt', 
			        	     data: agrmtObj,
			        	     contentType: 'application/json',
			        	     success: function(response) {
			        	    	 if(response === "Success"){
			        	    		 alert("Agreement is created ....");
			        	    		 location.reload();
			        	    	 }
			        	    	 else
			        	    		 alert("Agreement is not Created .... ");
			        	     },
			        	  });
					 	ajaxindicatorstop();
					}
					
					
				
				ajaxindicatorstop();
			});
			
});
	
	

	
	</script>