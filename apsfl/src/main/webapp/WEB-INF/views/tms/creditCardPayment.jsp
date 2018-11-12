<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/create-product.js"></script>

<body>
	
	<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> Payment Info </strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
				</ol>
			</div>
		</div>
<form action="./pgRequest" id="CreditCardPayment" method="POST">
		<div id="mainDiv" class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
							<div class="row">
								<div class="col-sm-6">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Tenant/Customer  Information</h3>
										</div>
										<div>
											<table class="table table-alt">
												<thead>
													<tr>
														<th> Name</th>
														<th>Total Amount</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>${paymentHelperDTO.tenantName}</td>
														<td>${paymentHelperDTO.totalCost}</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
								
										<div class="col-sm-6">
												<div class="panel">
													<div class="panel-header bg-light">
												<h3>Required  Information</h3>
												</div>
										
												<div class="panel-content">
													<div class="row">
														<div class="col-sm-6">
															<div class="form-group">
															<label class="control-label">Email ID <span style="color: red;">*</span></label> 
															<input type="email"  maxlength="30"
															class="form-control form-white validateClass "
															placeholder="Enter Email Id" id="email_Id">
															</div>
														</div>
														<div class="col-sm-6">
																<div class="form-group">
																	<label class="control-label">Mobile Number <span style="color: red;">*</span></label> 
																	<input type="number"  maxlength="10"
																	class="form-control form-white validateClass "
																		placeholder="Enter Mobile Number" id="mobile_Id">
																</div>
														</div>
													</div>
												</div>
											</div>
										</div>
							</div>
							
								<!-- END ROW -->
								
									<div class="row">
										<div class="col-sm-12">
											<div class="panel">
												<div class="panel-header bg-light">
													<h3>Select Payment Gateway</h3>
												</div>
												<div class="panel-content">
													<div class="row">
														<label>${returnValue}</label>
															<div class="col-sm-2">
																<img src="./resources/images/billdesk.jpg" alt="user1">
																<br>
															<input type="radio" name="r1" value="BillDesk" id="billDeskRadio" > Bill Desk
															</div>
															<div class="col-sm-2">
																<img src="./resources/images/digitsecure.png" alt="user1">
																<br>
																<input type="radio" name="r1" value="DigitSecure" id="digitSecureRadio" > DigitSecure
															</div>
															<div class="col-sm-8">
																<button id="payNow" class="btn btn-embossed btn-primary"
																	type="submit" style=" margin-left: 515px; margin-top: 55px;">Pay Now</button>
															</div>
													</div>
												</div>
											</div>
										</div>
								</div>
								<!-- END ROW -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		
			<c:if test="${paymentHelperDTO.paymentType eq 'cpe'}">
				<input type="hidden" name="customerId" value="${paymentHelperDTO.tenantCode}" />
				<input type="hidden" name="deliveryId" id="dlvId" value="${paymentHelperDTO.dlvId}" />
				<input type="hidden" name="amount" value="${paymentHelperDTO.totalCost}"   />
				<input type="hidden" name="gatewayType" id="gateWayTypeId" />
				<input type="hidden" name="paymentType" value="${paymentHelperDTO.paymentType}"  />
				<input type="hidden" name="emailId" id="email_Id_form"  />
				<input type="hidden" name="phone" id="mobile_Id_form"/>
			</c:if>
			<c:if test="${paymentHelperDTO.paymentType eq 'onlineSelfCare'}">
				<input type="hidden" name="customerId" value="${paymentHelperDTO.tenantCode},${paymentHelperDTO.cafNo}" />
				<input type="hidden" name="amount" value="${paymentHelperDTO.totalCost}"   />
				<input type="hidden" name="gatewayType" id="gateWayTypeId" />
				<input type="hidden" name="paymentType" value="${paymentHelperDTO.paymentType}"  />
				<input type="hidden" name="emailId" id="email_Id_form"  />
				<input type="hidden" name="phone" id="mobile_Id_form"/>
				<input type="hidden" name="deliveryId" id="dlvId" value="${paymentHelperDTO.dlvId}" />
			</c:if>
	</form>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
		
		
	</div>
	<!-- END PAGE CONTENT -->
</div>

