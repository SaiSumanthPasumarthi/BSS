<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
$(document).ready(function() {
	
	 $(document).on('change' , '.email_Id' , function() {
			
			var emailVal = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
			emailCheck = $.trim($('.email_Id').val());
			if(emailVal.test(emailCheck)) 
				 $(".validationText").text("").show();
			else 
				 $(".validationText").text("Please Enter Valid E-Mail..!!").show();
			 
			});
}); 
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
</script>
<body>
	
	<div class="">
	<div class="panel-body">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width-css">
		<div class="page-title" style="margin-top:50px;">
			<h2>
				<strong > Payment Info </strong>
			</h2>
			<div class="breadcrumb-wrapper" style="text-align: right;">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Payment Gateway</li>
				</ol>
			</div>
		</div>
<form action="./paymentRequest" class="CreditCardPayment" method="POST">
		<div id="mainDiv" class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel" style="margin-top:50px;">
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
														<!-- <th> Name</th> -->
														<th>Total Amount</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<%-- <td>${paymentHelperDTO.tenantName}</td> --%>
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
															<label class="control-label">Email ID <span style="color: red;">*</span></label><p>
															<input type="email"  maxlength="100"
															class="form-control form-white validateClass email_Id"
															placeholder="Enter Email Id" required="required">
															<div class="validationText" style = "color : #994F4F;"></div>
															</div>
														</div>
														<div class="col-sm-6">
																<div class="form-group">
																	<label class="control-label">Mobile Number <span style="color: red;">*</span></label><p> 
																	<input type="text"  maxlength="10"
																	class="form-control form-white validateClass mobile_Id"
																		placeholder="Enter Mobile Number" onkeypress="return isNumber(event)"  required="required">
																		<div class="validationTextMNo" style = "color : #994F4F;"></div> 
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
																<br><br>
															<input type="radio" name="r1" value="BillDesk" class="billDeskRadio" > Bill Desk
															</div>
															<div class="col-sm-2">
																<img src="./resources/images/digitsecure.png" alt="user1">
																<br><br>
																<input type="radio" name="r1" value="DigitSecure" class="digitSecureRadio" > DigitSecure
															</div>
															<div class="col-sm-8">
																<button  class="btn btn-embossed btn-primary payNow"
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
				<input type="hidden" name="deliveryId" class="dlvId" value="${paymentHelperDTO.dlvId}" />
				<input type="hidden" name="amount" value="${paymentHelperDTO.totalCost}"   />
				<input type="hidden" name="gatewayType" class="gateWayTypeId" />
				<input type="hidden" name="paymentType" value="${paymentHelperDTO.paymentType}"  />
				<input type="hidden" name="emailId" class="email_Id_form"  />
				<input type="hidden" name="phone" class="mobile_Id_form"/>
			</c:if>
			<c:if test="${paymentHelperDTO.paymentType eq 'onlineSelfCareBeforeLogin'}">
				<%-- <input type="hidden" name="customerId" value="${paymentHelperDTO.tenantCode},${paymentHelperDTO.cafNo}" /> --%>
				<input type="hidden" name="amount" value="${paymentHelperDTO.totalCost}"   />
				<input type="hidden" name="gatewayType" class="gateWayTypeId" />
				<input type="hidden" name="paymentType" value="${paymentHelperDTO.paymentType}"  />
				<input type="hidden" name="emailId" class="email_Id_form"  />
				<input type="hidden" name="phone" class="mobile_Id_form"/>
				<%-- <input type="hidden" name="deliveryId" class="dlvId" value="${paymentHelperDTO.dlvId}" /> --%>
			</c:if>
			<c:if test="${paymentHelperDTO.paymentType eq 'onlineSelfCareLMOAPSFLPayment'}">
				<%-- <input type="hidden" name="customerId" value="${paymentHelperDTO.tenantCode},${paymentHelperDTO.cafNo}" /> --%>
				<input type="hidden" name="amount" value="${paymentHelperDTO.totalCost}"   />
				<input type="hidden" name="gatewayType" class="gateWayTypeId" />
				<input type="hidden" name="paymentType" value="${paymentHelperDTO.paymentType}"  />
				<input type="hidden" name="emailId" class="email_Id_form"  />
				<input type="hidden" name="phone" class="mobile_Id_form"/>
				<%-- <input type="hidden" name="deliveryId" class="dlvId" value="${paymentHelperDTO.dlvId}" /> --%>
			</c:if>
	</form>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
		
		
	</div>
	<!-- END PAGE CONTENT -->
	</div>
</div>
