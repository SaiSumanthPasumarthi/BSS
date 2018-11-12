<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<script src="./resources/js/create-product.js"></script>

<!-- END HEADER -->
<!-- BEGIN MAIN CONTENT -->
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Display<strong> Package </strong>
			</h2>
				<div style="margin: auto;width: 20%"><label id="serviceNameLable" style="color:#006600">${returnValue}</label></div>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Display Package </li>
				</ol>
			</div>
		</div>
	
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form" class="form-validation">
							
							<div class="row">
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Package  Information</h3>
										</div>
										<div class="panel-content">
											
											<!-- END ROW INNER-->

											<div class="row">
												<div class="col-sm-12 ">
													<table class="table  table-alt" id="pcsProductsTable">
														<thead>
															<tr>
																<th rowspan="2">Package  Name</th>
																<th rowspan="2">Package Type</th>
																<th rowspan="2">Customer Type</th>
																<th rowspan="2">Valid From</th>
																<th rowspan="2">Lock in Months</th>
																<th rowspan="2">Security Deposit</th>
																<th rowspan="2">Activation Charge</th>
																<th rowspan="2">Resume Charge</th>
																<th rowspan="2">Disconnection Charge</th>
																<th colspan="3" align="center"><div style="padding-left: 85px;">Monthly Charge</div></th>
																<!-- <th rowspan="2">Package Price Change</th> -->
															</tr>
															<tr>
															<th>Charge Amount </th>
															<th>Tax Amount</th>
															<th>Total Charge</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="product" items="${productsList}">
																<tr>
																	<td><a
																		href=" <c:url value="/pcsGetAllServicesByProductIds">
							         <c:param name="prodcode" value="${product.productCode}"/>
							         <c:param name="effectiveDate" value= "${product.effectiveFrom}"/> 
							         <c:param name="tenantcode" value= "${product.tenantcode}"/> 
							        </c:url> ">
							       								    <b>${product.productName} </b></a></td>
																	<td>${product.prodType}</td>
																	<td>${product.custType}</td>
																	<td>${product.effectiveFrom}</td>
																	<td>${product.lockInDays}</td>
																	<td>${product.depositsCharge}</td>
																	<td>${product.activationCharge}</td>
																	<td>${product.resumeCharge}</td>
																	<td>${product.disconnectionCharge}</td>
																	<td>${product.monthlyCharge}</td>
																	<td><f:formatNumber value="${product.monthlyTax}" maxFractionDigits="2" /></td>
																	<td><f:formatNumber value="${product.productCharge}"
																			maxFractionDigits="2" /></td> 
																	<%-- <td><a href=" <c:url value="/getProdPrices">
																         <c:param name="prodcode" value="${product.productCode}"/>
																         <c:param name="effectiveDate" value= "${product.effectiveFrom}"/> 
																         <c:param name="tenantcode" value= "${product.tenantcode}"/> 
																        </c:url> ">
								       								    <b>Change Prices</b></a></td> --%>
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
							<!--  <button class="btn btn-embossed btn-dark" type="button" onclick="window.location.href='create-product.html'">&laquo; Back</button> -->
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
<!-- END MAIN CONTENT -->

