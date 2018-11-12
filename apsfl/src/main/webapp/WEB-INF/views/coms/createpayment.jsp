<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- BEGIN MAIN CONTENT -->
<div id="productFeasabilityChechDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Work<strong> Order</strong></h2>
				<c:if test="${not empty message}">
					<center id="comsErrorMsg"><font color='green' size="3">${message}</font></center>
				</c:if>
				<label id="error" style="text-align: center; color: red;"></label>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Work Order</li>
					</ol>
				</div>
				<c:if test="${not empty alert && ((customerCafVOObject.custType == 'ENTERPRISE' && customerCafVOObject.entCustType != 'GOVT') || (customerCafVOObject.custType == 'INDIVIDUAL'))}">
					<h1 align="right">
						<font color='red' size="3">${alert}</font>
					</h1>
				</c:if>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<c:choose>
								<c:when test="${not empty cpeChargesObject.cpeModel}">
									<form role="form" class="form-validation" name="paymentForm" id="paymentForm" action="./createPaymentDetails" method="post" id="customerFormId">
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Name</label> 
													<input type="hidden" name="lmoCode" id="lmoCode" value="${lmoCode}" readonly> 
													<input type="text" name="lmoName" value="${lmoName}" class="form-control form-white" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Wallet</label> 
													<input type="text" id="lmoWallet" name="lmoWallet" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${lmoWalletBalence}"/>" class="form-control form-white" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Credit Limit</label> 
													<input type="text" id="creditLimit" name="creditLimit" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${creditLimit}"/>" class="form-control form-white" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Remaining Limit</label>
													<c:choose>
														<c:when test="${flag == 'true'}">
															<input type="text" id="ruserLimit" name="ruserLimit" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" disabled>
														</c:when>
														<c:otherwise>
															<input type="text" id="ruserLimit" name="ruserLimit" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" required readonly>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<div class="row">
											<div class="col-sm-12 m-b-10 disable-search">
												<div>
													<b>Selected Packages Details</b>
												</div>
												<table class="table table-alt" id="paymentOrderTable">
													<thead>
														<tr class="titleRow">
															<th width="8%">Package Type</th>
															<th width="17%">Packages</th>
															<th width="25%">Services</th>
															<th width="10%">Security Deposit(Rs)</th>
															<th width="10%">Activation Charge(Rs)</th>
															<th width="10%">Tax(Rs)</th>
															<th width="10%">Total Charge(Rs)</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${productList}" var="product">
															<tr>
																<td class="pkgType">
																	<c:choose>
																		<c:when test="${product.prodtype == 'B'}">Base</c:when>
																		<c:when test="${product.prodtype == 'A'}">Add On</c:when>
																		<c:otherwise>One Time</c:otherwise>
																	</c:choose>
																</td>
																<td>${product.prodName}</td>
																<td>
																	<table>
																		<tbody>
																			<c:forEach items="${product.services}" var="services">
																				<tr>
																					<td class="services">${services.srvcName}(${services.coreSrvcCode})</td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.secDepositCharge}" />
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.activationCharge}" />
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.activationTax }" />
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.activationTax + product.secDepositCharge + product.activationCharge}" />
																</td>
															</tr>
														</c:forEach>
														<tr class="totalColumn">
															<td colspan="3" style="font-weight: bold" class="aCenter">Total</td>
															<td class="totalCol" style="font-weight: bold"></td>
															<td class="totalCol" style="font-weight: bold"></td>
															<td class="totalCol" style="font-weight: bold"></td>
															<td class="totalCol" style="font-weight: bold"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12 m-b-10 disable-search">
												<div>
													<b>ONU Charge Details</b>
												</div>
												<table class="table table-alt" id="paymentCafDeviceTable">
													<thead>
														<tr class="titleRow">
															<th>ONU Model</th>
															<th>ONU Serial Number</th>
															<th>ONU Mac-Id)</th>
															<th>ONU Charge(Rs)</th>
															<th>ONU Installment(Rs)</th>
															<th>Installation Charge(Rs)</th>
															<th>Extra Cable Charge(Rs)</th>
															<th>Tax(Rs)</th>
															<th>Total Charge(Rs)</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>${cpeChargesObject.cpeModel}</td>
															<td>${cpeChargesObject.cpeSrlNo}</td>
															<td>${cpeChargesObject.cpeMacAddress}</td>
															<td class="rowData">
																<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${cpeChargesObject.cpePrice}" />
															</td>
															<td class="rowData">
																<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${cpeChargesObject.cpeRentalPrice}" />
															</td>
															<td class="rowData">
																<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${cpeChargesObject.cpeInstChrg}" />
															</td>
															<td class="rowData">
																<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${cpeChargesObject.cpeExtCableChrg}" />
															</td>
															<td class="rowData">
																<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${cpeChargesObject.cpeTaxAmt}" />
															</td>
															<td class="rowData">
																<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${cpeChargesObject.cpePrice + cpeChargesObject.cpeRentalPrice + cpeChargesObject.cpeInstChrg + cpeChargesObject.cpeExtCableChrg + cpeChargesObject.cpeTaxAmt}" />
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<c:if test="${not empty cpeChargesObject.stbChargesList}">
											<div class="row">
												<div class="col-sm-12 m-b-10 disable-search">
													<div>
														<b>IPTV Charge Details</b>
													</div>
													<table class="table table-alt" id="paymentCafIPTVTable">
														<thead>
															<tr class="titleRow">
																<th>IPTV Box Model</th>
																<th>IPTV Box Packages</th>
																<th>IPTV Box Serial Number</th>
																<th>IPTV Box Mac-Id</th>
																<th>IPTV Box Charge(Rs)</th>
																<th>IPTV Box Installment(Rs)</th>
																<th>IPTV Box Tax(Rs)</th>
																<th>Total Charge(Rs)</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${cpeChargesObject.stbChargesList}" var="stbCharges">
																<tr>
																	<td>${stbCharges.stbModel}</td>
																	<td><c:if test="${not empty stbCharges.stbPackages}">${stbCharges.stbPackages}</c:if></td>
																	<td>${stbCharges.stbSrlNo}</td>
																	<td>${stbCharges.stbMacAddress}</td>
																	<td class="rowData">
																		<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${stbCharges.stbPrice}" />
																	</td>
																	<td class="rowData">
																		<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${stbCharges.stbInstallmentPrice}" />
																	</td>
																	<td class="rowData">
																		<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${stbCharges.stbTax}" />
																	</td>
																	<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${stbCharges.stbPrice + stbCharges.stbInstallmentPrice + stbCharges.stbTax}" />
																	</td>
																</tr>
															</c:forEach>
															<tr class="totalColumn">
																<td colspan="4" style="font-weight: bold" class="aCenter">Total</td>
																<td class="totalCol" style="font-weight: bold"></td>
																<td class="totalCol" style="font-weight: bold"></td>
																<td class="totalCol" style="font-weight: bold"></td>
																<td class="totalCol" style="font-weight: bold"></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</c:if>
										<!-- /END ROW -->
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">CAF Number</label> 
													<input type="text" name="cafNo" id="cafNo" value="${customerCafVOObject.cafNo}" class="form-control form-white" required placeholder="CAF Number" readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Aadhar/Register Number</label>
													<input type="text" name="aadharNumber" id="aadharNumber" value="${customerCafVOObject.aadharNumber}" class="form-control form-white" placeholder="Aadhar Number" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Customer First/Organization Name</label> 
													<input type="hidden" name="custId" id="custId" value="${customerCafVOObject.custId}">
													<input type="hidden" name="custType" id="custType" value="${customerCafVOObject.custType}"> 
													<input type="hidden" name="cpeReleasedCharge" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value = "${cpeChargesObject.cpeCharge}" />" class="form-control form-white" >
													<input type="hidden" name="oltSrlNo" id="oltSrlNo" value="${customerCafVOObject.oltId}"> 
													<input type="hidden" name="oltPortNo" id="oltPortNo" value="${customerCafVOObject.oltPortId}"> 
													<input type="hidden" name="district" id="paymentDistrict" value="${customerCafVOObject.district}"> 
													<input type="hidden" name="village" id="paymentVillage" value="${customerCafVOObject.city}"> 
													<input type="hidden" name="mandal" id="paymentMandal" value="${customerCafVOObject.mandal}"> 
													<input type="hidden" name="" id="entCustType" value="${customerCafVOObject.entCustType}"> 
													<input type="text" name="" id="customerName" value="${customerCafVOObject.firstName}" class="form-control form-white" placeholder="Customer Name" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Customer Surname</label> 
													<input type="text" name="" id="customerName" value="${customerCafVOObject.lastName}" class="form-control form-white" placeholder="Customer Name" readonly>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">VPN Service Name</label> 
													<input type="text" name="" id="vpnSrvcName" <c:choose><c:when test = "${not empty customerCafVOObject.vpnService}">value="${customerCafVOObject.vpnService}"</c:when><c:otherwise>value="NA"</c:otherwise></c:choose> class="form-control form-white" readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Effective Date</label> 
													<input type="text" id="effectiveDate" value="${effectiveDate}" required class="form-control form-white" readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Bill Frequency</label> 
													<input type="text" id="billFrequency" value="${customerCafVOObject.billCycle}" required class="form-control form-white" readonly>
												</div>
											</div>
											<input type="hidden" class="form-white" id="feasibility" name="feasibility" checked value="Y">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Total Payable Amount</label> 
													<input type="text" name="totalCharge" id="totalProdCharge" style="font-weight: bold;" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value = "${charge + cpeChargesObject.totalCharge }" />" required class="form-control form-white" readonly>
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<!-- END ROW INNER-->
										<c:if test="${(customerCafVOObject.custType == 'INDIVIDUAL') || (customerCafVOObject.custType == 'ENTERPRISE' && customerCafVOObject.entCustType != 'GOVT')}">
											<div id="paymentDiv">
												<div class="row">
													<div class="col-sm-12">
														<div class="panel">
															<div class="panel-header bg-light">
																<h3>Payment Entry Information <label id="paymentError" style="text-align: center; color: red;"></label></h3>
															</div>
															<div class="panel-content">
																<div class="row">
																	<div class="col-sm-4">
																		<div class="form-group">
																			<label class="control-label">Payment Mode<span style="color: red;">*</span></label>
																			<div class="option-group">
																				<c:choose>
																					<c:when test="${flag == 'true'}">
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
																	</div>
																	<div class="col-sm-4">
																		<div class="form-group">
																			<label class="control-label">Amount<span style="color: red;">*</span></label>
																			<c:choose>
																				<c:when test="${flag == 'true'}">
																					<input type="text" id="paidAmount" name="paidAmount" class="form-control form-white numbersOnly" placeholder="Amount to be paid" disabled>
																				</c:when>
																				<c:otherwise>
																					<input type="text" id="paidAmount" name="paidAmount" class="form-control form-white numbersOnly" placeholder="Amount to be paid" required>
																				</c:otherwise>
																			</c:choose>
																		</div>
																	</div>
																	<div class="clear"></div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</c:if>
										<!-- END ROW -->
										<c:choose>
											<c:when test="${(flag == 'true') && ((customerCafVOObject.custType == 'INDIVIDUAL') || (customerCafVOObject.custType == 'ENTERPRISE' && customerCafVOObject.entCustType != 'GOVT'))}">
												<button class="btn btn-embossed btn-primary" id="workOrderPaymentButton" type="submit" disabled>Submit</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-embossed btn-primary" id="workOrderPaymentButton" type="submit">Submit</button>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${tenantType == 'SI'}">
												<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
											</c:when>
											<c:when test="${customerCafVOObject.custType == 'ENTERPRISE'}">
												<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
											</c:when>
											<c:otherwise>
												<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
											</c:otherwise>
										</c:choose>
									</form>
								</c:when>
								<c:otherwise>
									<input type="hidden" name="oldPackageCode" id="oldPackageCode" value="${customerCafVOObject.changePkgBO.prodCode}" readonly>
									<input type="hidden" name="newPackageCode" id="newPackageCode" value="${customerCafVOObject.prodCode}" readonly>
									<form role="form" class="form-validation" name="addPackagepaymentForm" id="addPackagepaymentForm" action="./createPaymentDetails" method="post" id="addPackagFormId">
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Name</label> 
													<input type="hidden" name="lmoCode" id="lmoCode" value="${lmoCode}" readonly> 
													<input type="text" name="lmoName" value="${lmoName}" class="form-control form-white" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Wallet</label> 
													<input type="text" id="lmoWallet" name="lmoWallet" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${lmoWalletBalence}"/>" class="form-control form-white" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Credit Limit</label> 
													<input type="text" id="creditLimit" name="creditLimit" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${creditLimit}"/>" class="form-control form-white" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Remaining Limit</label>
													<c:choose>
														<c:when test="${flag == 'true'}">
															<input type="text" id="ruserLimit" name="ruserLimit" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" disabled>
														</c:when>
														<c:otherwise>
															<input type="text" id="ruserLimit" name="ruserLimit" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${actualUserAmount}"/>" class="form-control form-white" required readonly>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<div class="row">
											<div class="col-sm-12 m-b-10 disable-search">
												<div>
													<b>Selected Packages Details</b>
												</div>
												<table class="table table-alt" id="paymentOrderTable">
													<thead>
														<tr class="titleRow">
															<th width="8%">Package Type</th>
															<th width="17%">Packages</th>
															<th width="25%">Services</th>
															<th width="10%">Security Deposit(Rs)</th>
															<th width="10%">Activation Charge(Rs)</th>
															<th width="10%">Tax(Rs)</th>
															<th width="10%">Total Charge(Rs)</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${productList}" var="product">
															<tr>
																<td class="pkgType">
																	<c:choose>
																		<c:when test="${product.prodtype == 'B'}">Base</c:when>
																		<c:when test="${product.prodtype == 'A'}">Add On</c:when>
																		<c:otherwise>One Time</c:otherwise>
																	</c:choose>
																</td>
																<td>${product.prodName}</td>
																<td>
																	<table>
																		<tbody>
																			<c:forEach items="${product.services}" var="services">
																				<tr>
																					<td class="services">${services.srvcName}(${services.coreSrvcCode})</td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.secDepositCharge}" />
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.activationCharge}" />
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.activationTax }" />
																</td>
																<td class="rowData">
																	<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${product.activationTax + product.secDepositCharge + product.activationCharge}" />
																</td>
															</tr>
														</c:forEach>
														<tr class="totalColumn">
															<td colspan="3" style="font-weight: bold" class="aCenter">Total</td>
															<td class="totalCol" style="font-weight: bold"></td>
															<td class="totalCol" style="font-weight: bold"></td>
															<td class="totalCol" style="font-weight: bold"></td>
															<td class="totalCol" style="font-weight: bold"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<!-- /END ROW -->
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">CAF Number</label> 
													<input type="text" name="cafNo" id="cafNo" value="${customerCafVOObject.cafNo}" class="form-control form-white" required placeholder="CAF Number" readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Aadhar/Register Number</label>
													<input type="text" name="aadharNumber" id="aadharNumber" value="${customerCafVOObject.aadharNumber}" class="form-control form-white" placeholder="Aadhar Number" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Customer First/Organization Name</label> 
													<input type="hidden" name="custId" id="custId" value="${customerCafVOObject.custId}">
													<input type="hidden" name="custType" id="custType" value="${customerCafVOObject.custType}">
													<input type="hidden" name="oltSrlNo" id="oltSrlNo" value="${customerCafVOObject.oltId}"> 
													<input type="hidden" name="oltPortNo" id="oltPortNo" value="${customerCafVOObject.oltPortId}"> 
													<input type="hidden" name="district" id="paymentDistrict" value="${customerCafVOObject.district}"> 
													<input type="hidden" name="village" id="paymentVillage" value="${customerCafVOObject.city}"> 
													<input type="hidden" name="mandal" id="paymentMandal" value="${customerCafVOObject.mandal}"> 
													<input type="hidden" name="" id="entCustType" value="${customerCafVOObject.entCustType}"> 
													<input type="text" name="" id="customerName" value="${customerCafVOObject.firstName}" class="form-control form-white" placeholder="Customer Name" required readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Customer Surname</label> 
													<input type="text" name="" id="customerName" value="${customerCafVOObject.lastName}" class="form-control form-white" placeholder="Customer Name" readonly>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">VPN Service Name</label> 
													<input type="text" name="" id="vpnSrvcName" <c:choose><c:when test = "${not empty customerCafVOObject.vpnService}">value="${customerCafVOObject.vpnService}"</c:when><c:otherwise>value="NA"</c:otherwise></c:choose> class="form-control form-white" readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Effective Date</label> 
													<input type="text" id="effectiveDate" value="${effectiveDate}" required class="form-control form-white" readonly>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Bill Frequency</label> 
													<input type="text" id="billFrequency" value="${customerCafVOObject.billCycle}" required class="form-control form-white" readonly>
												</div>
											</div>
											<input type="hidden" class="form-white" id="feasibility" name="feasibility" checked value="Y">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Balance Amount</label> 
													<input type="text" name="custBal" id="custBal" style="font-weight: bold;" value="${custBal}" class="form-control form-white" readonly>
												</div>
											</div>
										</div>
										<div class="row">
											<c:if test="${not empty pkgTypeFlag && pkgTypeFlag == 'chnagePkgPageFlag' }">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Security Deposit Amount</label> 
														<input type="text" name="" id="secDepositAmount" style="font-weight: bold;" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value = "${secDeposit}" />" required class="form-control form-white" readonly>
													</div>
												</div>
											</c:if>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Total Payable Amount</label> 
													<input type="text" name="totalCharge" id="totalProdCharge" style="font-weight: bold;" value="<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value = "${charge + cpeChargesObject.totalCharge + secDeposit}" />" required class="form-control form-white" readonly>
												</div>
											</div>
											<div class="clear"></div>
										</div>
										<!-- END ROW INNER-->
										<div>
											<label id="paymentErrors" style="text-align: center; color: red;"></label>
										</div>
										<c:if test="${(customerCafVOObject.custType == 'INDIVIDUAL' && (flag == 'A' || flag == 'B')) || (customerCafVOObject.custType == 'ENTERPRISE' && customerCafVOObject.entCustType != 'GOVT' && (flag eq 'A' || flag == 'B'))}">
											<div id="paymentDiv">
												<div class="row">
													<div class="col-sm-12">
														<div class="panel">
															<div class="panel-header bg-light">
																<h3>Payment Entry Information</h3>
															</div>
															<div class="panel-content">
																<div class="row">
																	<div class="col-sm-4">
																		<div class="form-group">
																			<label class="control-label">Payment Mode<span style="color: red;">*</span></label>
																			<div class="option-group">
																				<c:choose>
																					<c:when test="${flag == 'true'}">
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
																	</div>
																	<div class="col-sm-4">
																		<div class="form-group">
																			<label class="control-label">Amount<span style="color: red;">*</span></label>
																			<c:choose>
																				<c:when test="${flag == 'true'}">
																					<input type="text" id="paidAmount" name="paidAmount" class="form-control form-white numbersOnly" placeholder="Amount to be paid" disabled>
																				</c:when>
																				<c:otherwise>
																					<input type="text" id="paidAmount" name="paidAmount" class="form-control form-white numbersOnly" value="0.00" placeholder="Amount to be paid" required>
																				</c:otherwise>
																			</c:choose>
																		</div>
																	</div>
																	<div class="clear"></div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</c:if>
										<!-- END ROW -->
										<c:choose>
											<c:when test="${(flag == 'true') && ((customerCafVOObject.custType == 'INDIVIDUAL') || (customerCafVOObject.custType == 'ENTERPRISE' && customerCafVOObject.entCustType != 'GOVT'))}">
												<button class="btn btn-embossed btn-primary" id="workOrderPaymentButton" type="submit" disabled>Submit</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-embossed btn-primary" id="workOrderPaymentButton" type="submit">Submit</button>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${tenantType == 'SI'}">
												<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
											</c:when>
											<c:when test="${customerCafVOObject.custType == 'ENTERPRISE'}">
												<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
											</c:when>
											<c:otherwise>
												<a href="./multiactionsearch"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
											</c:otherwise>
										</c:choose>
									</form>
									<input type="hidden" value="${domain}" id="tenantType" />
								</c:otherwise>
							</c:choose>
							<!-- End of ADD Package Form -->
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