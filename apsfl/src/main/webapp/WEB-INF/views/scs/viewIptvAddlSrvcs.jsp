<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<!-- END HEADER -->
<!-- BEGIN MAIN CONTENT -->

<style>
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
}
</style>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> View Logs</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">View Logs</li>
				</ol>
			</div>
		</div>
	
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>View Logs</h3>
										</div>
										<div class="panel-content">
											
											<!-- END ROW INNER-->

											<div class="row">
												<div class="col-sm-12 " >
													<table class="table  table-alt"  id="table_1_Id">
														<thead>
															<tr>
															<th>Package Code</th>
															<th>Package Name</th>
															<th>Effective From</th>
															<th>Effective To</th>
															<th>Transaction Date</th>
															<th style="width:12%;">Channels List</th>
															<th style="width:12%;">Added Channels</th>
															<th style="width:12%;">Removed Channels</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="srvc" items="${addlServcList}">
																<tr>
																	<td>${srvc.srvcCode}</td>
																	<td>${srvc.srvcName}</td>
																	<td>${srvc.effectivefrom}</td>
																	<td>${srvc.effectiveTo}</td>
																	<td>${srvc.createdon}</td>
																	<td align="center" class="viewChannels"><span  data-toggle="modal" data-target="#myModal" ><input type="hidden" class="viewChannels_Id" value="${srvc.allChannels}"><img src="./resources/images/apf_view.png"></span></td>
																	<td align="center" class="added_viewChannels" ><span data-toggle="modal" data-target="#myModal" ><input type="hidden" class="added_viewChannels_Id"  value="${srvc.addedChannels}"><img src="./resources/images/apf_view.png"></span></td>
																	<td align="center"  class="deleted_viewChannels" ><span data-toggle="modal" data-target="#myModal"  ><input type="hidden" class="deleted_viewChannels_Id" value="${srvc.deltedChannels}"><img src="./resources/images/apf_view.png"></span></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<!-- END ROW INNER-->
										</div>
									</div>
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
		
		<div id="myModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Service Features</h4>
		      </div>
		      <div class="modal-body" id="srvcFutuPopUoDiv">
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->
<script type="text/javascript">
$(document).ready(function() {
	
	$("#table_1_Id").dataTable();
	
	$(document).on('click','.viewChannels',function() {
		var featureCodes  = $(this).find('.viewChannels_Id').val();
		$.ajax({ 
			 async:false,
		     type: 'GET', 
		     url: 'getAllSrvdFeaturesByFeatuesCodes', 
		     data: {featureCodes:featureCodes},
		     contentType: 'application/json',
		     success: function(data) { 
		    	 $('#srvcFutuPopUoDiv').html("");
		    	 var i = 0;
			 var html = "";

			 if(data.length  > 0){

				 $.each( data, function(){
					 if(i == 0)
						 html = html + '<div class = "row" >'
					 html = html + '<div class = "col-sm-2">'+this.featurename+'</div>'
					 i++;
					 if(i == 6){
						 html = html + '</div>'
						 i = 0;
					 }
				 });
			}else{
				 html = html + '<div class = "row" > <div><h2> No Records Found </h2> </div> </div>'
				}
			
			 $('#srvcFutuPopUoDiv').append(html);
		     }
		}); 
	});

	$(document).on('click','.added_viewChannels',function() {
		var featureCodes = $(this).find('.added_viewChannels_Id').val();
		$.ajax({ 
			 async:false,
		     type: 'GET', 
		     url: 'getAllSrvdFeaturesByFeatuesCodes', 
		     data: {featureCodes:featureCodes},
		     contentType: 'application/json',
		     success: function(data) { 
		    	 $('#srvcFutuPopUoDiv').html("");
		    	 var i = 0;
			 var html = "";

			 if(data.length  > 0){

				 $.each( data, function(){
					 if(i == 0)
						 html = html + '<div class = "row" >'
					 html = html + '<div class = "col-sm-2">'+this.featurename+'</div>'
					 i++;
					 if(i == 6){
						 html = html + '</div>'
						 i = 0;
					 }
				 });
			}else{
				 html = html + '<div class = "row" > <div><h2> No Added Channels</h2> </div> </div>'
				}
			
			 $('#srvcFutuPopUoDiv').append(html);
		     }
		});
	});


	$(document).on('click','.deleted_viewChannels',function() {
		var featureCodes = $(this).find('.deleted_viewChannels_Id').val();
		$.ajax({ 
			 async:false,
		     type: 'GET', 
		     url: 'getAllSrvdFeaturesByFeatuesCodes', 
		     data: {featureCodes:featureCodes},
		     contentType: 'application/json',
		     success: function(data) { 
		    	 $('#srvcFutuPopUoDiv').html("");
		    	 var i = 0;
			 var html = "";

			 if(data.length  > 0){

				 $.each( data, function(){
					 if(i == 0)
						 html = html + '<div class = "row" >'
					 html = html + '<div class = "col-sm-2">'+this.featurename+'</div>'
					 i++;
					 if(i == 6){
						 html = html + '</div>'
						 i = 0;
					 }
				 });
			}else{
				 html = html + '<div class = "row" > <div><h2> No Deleted Channels </h2> </div> </div>'
				}
			
			 $('#srvcFutuPopUoDiv').append(html);
		     }
		});
	});
});

</script>
