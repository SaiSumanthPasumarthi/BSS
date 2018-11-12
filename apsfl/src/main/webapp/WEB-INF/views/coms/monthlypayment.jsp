<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="monthlyCafDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					Monthly<strong> Payment</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Monthly Payment</li>
					</ol>
				</div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form name="monthlyPaymentForm" id="monthlyPaymentForm" action="./searchMonthlyPaymentCafDetails" method="post">
								<div class="row">
									<div class="col-sm-12">
										<div class="row">
<!-- 											<div class="col-sm-3"> -->
<!-- 												<div class="form-group"> -->
<!-- 													<label class="control-label">Mobile Number<span style="color: red;">*</span></label>  -->
<%-- 													<input type="text" name="mobileNo" id="mobileNo" <c:if test="${not empty monthlyPaymentVO.mobileNo}"> value="${monthlyPaymentVO.mobileNo}" </c:if> class="form-control form-white number" maxlength="10" placeholder="Mobile Number"> --%>
<!-- 												</div> -->
<!-- 												</div> -->
												
<!-- 											<div class="col-sm-3" align="right">  -->
<!--  												(Or)</div>  -->
 												<div class="column">
 												<div class="col-sm-3">	 
 												<div class="form-group"> 
 													<label class="control-label">Caf No<span style="color: red;"> </span></label> 
 													<input type="text" name="cafNo" id="cafNo"  class="form-control form-white number" maxlength="10" placeholder="Caf No">  
 												</div> 
											</div>
											
											<div class="col-sm-1">
														<br> <label class="control-label">Or</label>
													</div>
											
											<div class="col-sm-3">
											<div class="form-group">
												<label class="control-label">Mobile Number</label> 
												<input type="text" name="mobileNo" id="mobileNo" class="form-control form-white" placeholder="Mobile Number">
											</div>
										</div>
											
											
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label hide-mobile">&nbsp;</label>
													<div class="option-group">
														<button class="btn btn-embossed btn-primary" type="submit"> <i class="fa fa-search"></i>Search</button>
												
														
														
													</div>
												</div>
											</div>
										</div>
										</div>
										<!-- END ROW INNER-->
										
										<c:if test="${not empty message}"> <center><font color='red' size="3">${message}</font></center></c:if>
										<c:if test="${not empty monthlyPaymentList}">
											<div class="row m-b-5">
												<div class="col-sm-12">
													<table class="table table-alt" id="monthlyPaymentTable">
														<thead>
															<tr>
																<th width="15%">Customer Name</th>
																<th width="15%">Mobile Number</th>
																<th width="12%">Aadhar/Registration Number</th>
																<th width="13%">Customer Type</th>
																<!-- <th width="20%">Packages</th> -->
																<th width="15%">Due Amount(Rs)</th>
																<th width="5%"></th>
																<th width="10%" style="display: none;">CustId</th>
																<th width="10%" style="display: none;">CustType</th>
																<th width="10%" style="display: none;">DistrictUid</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${monthlyPaymentList}" var="payment">
																<tr>
																	<td class="customerName">${payment.fName} ${payment.lName}</td>
																	<td class="mobileNo">${payment.mobileNo}</td>
																	<td class="aadharNo">${payment.aadharNo}</td>
																	<td class="">${payment.custTypelov}</td>
																	<%-- <td class="prodName">${payment[5]}</td> --%>
																	<td class="totalCharge"><fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${payment.dueAmount}" /></td>
																	<td><input type="radio" name="monthlyRadio" id="monthlyRadio" class="monthlyRadio" /></td> 
																	<td class="custId" style="display: none;">${payment.custId}</td>
																	<td class="custType" style="display: none;">${payment.custTypelov}</td>
																	<td class="districtUid" style="display: none;">${payment.instDistrict}</td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</c:if>
									</div>
								</div>
							</form>
							<form action="./searchBulkMonthlyPaymentCafDetails" method="POST">
								<button class="btn btn-embossed btn-primary" type="submit">Monthly Bulk Payment</button>
							</form>
							
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
</div>
<!-- END MAIN CONTENT -->

<div id="monthlyPaymentDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Payment<strong> Entry</strong></h2>
				<label id="paymentError" style="text-align: center; color: red;"></label>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Payment Entry</li>
					</ol>
				</div>
				<c:if test="${not empty alert && tenantType == 'LMO'}">
					<h1 align="right"><font color='red' size="3">${alert}</font></h1>
				</c:if>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<div class="row">
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Name</label> 
										<input type="text" name="lmoName" value="${lmoName}" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Wallet</label> 
										<input type="text" id="lmoWallet" name="lmoWallet" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${lmoWalletBalence}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Credit Limit</label> 
										<input type="text" id="creditLimit" name="creditLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${creditLimit}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Remaining Limit</label> 
										<c:choose>
											<c:when test="${flag == 'true'}">
												<input type="text" id="ruserLimit" name="ruserLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" disabled>
											</c:when>
											<c:otherwise>
												<input type="text" id="ruserLimit" name="ruserLimit" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" required readonly>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Payment Entry Information</h3>
										</div>
										<form role="form" class="form-validation" name="paymentForm" id="makeMonthlyPaymentForm" action="#" method="post">
											<div class="row">
												<div class="col-sm-12">
													<div class="panel-content">
														<div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Customer Name/Organization Name</label> 
																	<input type="hidden" name="custId" id="custId"> 
																	<!-- <input type="hidden" name="custType" id="custType"> -->
																	<input type="hidden" name="district" id="districtUid"> 
																	<input type="text" name="customerName" id="customerName" class="form-control form-white" readonly required>
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Aadhar/Registration Number</label> 
																	<input type="text" name="aadharNumber" id="aadharNumber" class="form-control form-white" readonly required>
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Customer Type</label> 
																	<input type="text" name="custType" id="custType" class="form-control form-white" readonly required>
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Due Amount</label> 
																	<input type="hidden" id="flag" value="${flag}">
																	<input type="hidden" name="feasibility" id="feasibility" value="Y"> 
																	<input type="hidden" name="lmoCode" value="${lmoCode}"> 
																	<input type="hidden" id="tenantType" value="${tenantType}"> 
																	<input type="hidden" name="payment" value="Monthly"> 
																	<input type="hidden" name="mobileNo" value="${monthlyPaymentVO.mobileNo}">
																	<input type="hidden" name="cafNo" value="${monthlyPaymentVO.cafNo}">
																	<input type="text" name="totalCharge" id="totalProdCharge" style="font-weight: bold;" class="form-control form-white" readonly required>
																</div>
															</div>
	
														</div>
														<!-- END ROW INNER-->
														<div class="row">
															<div class="col-sm-3">
																<label class="control-label">Payment Mode<span style="color: red;">*</span></label>
																<div class="option-group">
																	<c:choose>
																		<c:when test="${flag == 'true' && tenantType == 'LMO'}">
																			<select id="paymentMode" name="paymentMode" required class="form-control form-white" disabled>
																				<option value="">--Select--</option>
																				<c:forEach var="lovs" items="${paymentList}">
																					<c:choose>
																						<c:when test="${not empty customerCafVO && lovs.lovId == customerCafVO.channel}">
																							<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																						</c:otherwise>
																					</c:choose>
																				</c:forEach>
																			</select>
																		</c:when>
																		<c:otherwise>
																			<select id="paymentMode" name="paymentMode" required class="form-control form-white">
																				<option value="">--Select--</option>
																				<c:forEach var="lovs" items="${paymentList}">
																					<c:choose>
																						<c:when test="${not empty customerCafVO && lovs.lovId == customerCafVO.channel}">
																							<option value="${lovs.lovValue}" selected>${lovs.lovValue}</option>
																						</c:when>
																						<c:otherwise>
																							<option value="${lovs.lovValue}">${lovs.lovValue}</option>
																						</c:otherwise>
																					</c:choose>
																				</c:forEach>
																			</select>
																		</c:otherwise>
																	</c:choose>
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Amount<span style="color: red;">*</span></label>
																	<c:choose>
																		<c:when test="${flag == 'true' && tenantType == 'LMO'}">
																			<input type="text" id="paidAmount" name="paidAmount" class="form-control form-white numbersOnly" placeholder="Amount to be paid" disabled>
																		</c:when>
																		<c:otherwise>
																			<input type="text" id="paidAmount" name="paidAmount" class="form-control form-white numbersOnly" placeholder="Amount to be paid" required>
																		</c:otherwise>
																	</c:choose>
																</div>
															</div>
															<!-- <div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Date<span style="color: red;">*</span></label> 
																	<label id="dobError" style="text-align: center; color: red;"></label> 
																	<input type="text" name="ddDate" id="ddDate" class="date-picker form-control form-white" readonly placeholder="Select Date...">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">DD No</label>
																	<input type="text" name="ddNo" id="ddNo" disabled class="form-control form-white" placeholder="DD-No">
																</div>
															</div> -->
															<div class="clear"></div>
														</div>
														<!-- <div class="row">
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Drawn on Bank</label>
																	<input type="text" name="bankName" id="bankName" disabled class="form-control form-white" placeholder="Bank Name">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Branch</label> 
																	<input type="text" name="branchName" id="branchName" disabled class="form-control form-white" placeholder="Branch Name">
																</div>
															</div>
														</div> -->
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
											<!-- END ROW -->
											<c:choose>
												<c:when test="${flag == 'true' && tenantType == 'LMO'}">
													<button class="btn btn-embossed btn-primary" id="monthlyPaymentButton" type="submit" disabled>Submit</button>
												</c:when>
												<c:otherwise>
													<button class="btn btn-embossed btn-primary" id="monthlyPaymentButton" type="submit">Submit</button>
												</c:otherwise>
											</c:choose>
											<a href="./monthlypayment"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
										</form>
																				<!-- END FORM -->
									</div>
									<!-- END MAIN PANEL CONTENT -->
								</div>
								<!-- END MAIN PANEL -->
							</div>
							<!-- HERE COMES YOUR CONTENT -->
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
</div>
