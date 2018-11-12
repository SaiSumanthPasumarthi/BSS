
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Assign Public IP</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>Assign Public IP</li>
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
												<div class=" panel-body ">
													<form method="post" action="getCafsIpAddress">
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Track Id</label> <input
																		type="text" id="trackId" name="trackId"
																		class="form-control form-white"
																		placeholder="Enter Track Id" value="" maxlength="12">
																</div>
															</div>
															<div class="col-sm-1">
																<br /> <label class="control-label">OR</label>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Customer Id</label> <input
																		type="text" name="custId" id="custId"
																		class="form-control form-white number" maxlength="10"
																		placeholder="Enter Customer Id">
																</div>
															</div>
															<div class="col-sm-1">
																<br /> <label class="control-label">OR</label>
															</div>
															<div class="col-sm-2">
																<div class="form-group">
																	<label class="control-label">Account Number</label> <input
																		type="text" name="cafNum" id="cafID"
																		class="form-control form-white number"
																		placeholder="Enter CAF Number">
																</div>
															</div>
															<br />
															<div class="col-sm-1">
																<div class="form-group">
																	<button type="submit" id="seachIPButtonId"
																		class="btn btn-embossed btn-primary">
																		<i class="fa fa-search"></i>Search
																	</button>
																</div>
															</div>
														</div>
													</form>
												</div>
												<div class=" panel-body ">
													<div class="col-lg-12">
														<div class="row">
															<table class="table table-alt" id="publicIpTableId">
																<thead>
																	<tr>
																		<th>Account Number</th>
																		<th>Customer Name</th>
																		<th>POP Name</th>
																		<th>OLT ID</th>
																		<th>PON ID</th>
																		<th>ONU ID</th>
																		<th>Address</th>
																		<th>Village</th>
																		<th>Mandal</th>
																		<th>District</th>
																		<th></th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${customerList}" var="custoList">
																		<tr>
																			<td><c:out value="${custoList[0]}"></c:out></td>
																			<td><c:out value="${custoList[1]}"></c:out></td>
																			<td><c:out value="${custoList[2]}"></c:out></td>
																			<td><c:out value="${custoList[3]}"></c:out></td>
																			<td><c:out value="${custoList[4]}"></c:out></td>
																			<td><c:out value="${custoList[5]}"></c:out></td>
																			<td><c:out value="${custoList[6]}"></c:out></td>
																			<td><c:out value="${custoList[7]}"></c:out></td>
																			<td><c:out value="${custoList[8]}"></c:out></td>
																			<td><c:out value="${custoList[9]}"></c:out></td>
																			<td><input type="radio" name="custCafSelected"></td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>
													</div>
												</div>
												<div id="errorpIPId" class="text-center" style="color: red;">
													<h4>
														<label class="control-label"> </label>
													</h4>
												</div>
												<div class=" panel-body ">

													<div class="panel-header bg-light" id="ipGridId">
														<h3>Public IP Address</h3>
													</div>
													<div class="panel-content">
														<div class="row">

															<div class="panel panel-default ">
																<div class=" panel-body ">
																	<div class=" panel-body " id="publicIPId">
																		<form method="post" >
																			<div class="row">
																				<div class="col-sm-4">
																					<label class="control-label">IP Address</label><label
																						style="color: red;">*</label> <input type="text"
																						id="ipAddressId" class="allow_decimal" name="ipAddressCheck"
																						class="form-control form-white"
																						placeholder="Enter Public IP Address" value=""
																						maxlength="15" required="required">
																						<input type="hidden" id="ipCafHiddenId" name="CafNumHidden" value=" ">
																				</div>
																				
																				<div class="col-sm-1">
																					<div class="form-group">
																						<button type="button" id="submitPublicIP"
																							class="btn btn-embossed btn-primary">
																							Submit</button>
																					</div>
																				</div>
																			</div>
																		</form>
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
				</div>
			</div>
		</div>
	</section>
</body>

<script>
	 
	$(document).ready(function() {
						$("#publicIpTableId").dataTable();
						$('#publicIPId').hide();
						$('#ipGridId').hide();

						$(document) .on( 'click', '#seachIPButtonId', function() {
											var trackId = $('#trackId').val();
											var custId = $('#custId').val();
											var cafId = $('#cafID').val();

											if (trackId == '') {
												if (custId == '')
													if (cafId == '') {
														alert("Please Provide Atlease One Input i.e Track Id/Cust Id/ Caf Number");
														return false;
													} else
														return true;
											}
										});
						 
						$(document).on("click","input:radio[name=custCafSelected]",function() {
									$('#ipGridId').show();
									$('#publicIPId').show();
									var cafNum = $(this).closest("tr").find('td:eq(0)').text();
								 	$('#ipCafHiddenId').val(cafNum);
									 
									$.ajax({
										url: 'getIpAddress',
										contentType : 'application/json',
										async : false,
										data : { cafNum : cafNum },
										success : function(data) {
											 if(data == "")
												  $('#ipAddressId').val('');
											 else
											$('#ipAddressId').val(data);
										}
									});

								});
						$(document) .on( 'click', '#submitPublicIP', function() {
							  var cafNum= $('#ipCafHiddenId').val();
						    var pIpAddress = $('#ipAddressId').val();
						     
						    $.ajax({
						    	url: 'updateIpAddress',
						    	contentType:  'application/json',
						    	async : false,
						    	data :{
						    		cafNum : cafNum ,
						    		pIpAddress : pIpAddress,	  
						    	},
						    	success: function(data)
						    	{
						    		if(data == "")
						    			{
						    			$('#errorpIPId').show().text("Update Failed... Entered Public IP Address Already Assigned or Invalid...!!!");
										$("#errorpIPId").delay(3000).fadeOut(500);
										$('#errorpIPId:contains("")').css('color', 'red');
										return false;
						    			}
						    		$('#errorpIPId').show().text(data);
									$("#errorpIPId").delay(3000).fadeOut(500);
									$('#errorpIPId:contains("Failed")').css('color', 'red');
									$('#errorpIPId:contains("Successfully Updated")').css('color', 'green');
									return false;
						    	}
						    });
						     
						});
						
						 $(".allow_decimal").on("input", function(evt) {
							   var self = $(this);
							   self.val(self.val().replace(/[^0-9\.]/g, ''));
							   if ((evt.which != 46 || self.val().indexOf('.') != -1) && (evt.which < 48 || evt.which > 57)) 
							   {
							     evt.preventDefault();
							   }
							 });
						 
					});
</script>