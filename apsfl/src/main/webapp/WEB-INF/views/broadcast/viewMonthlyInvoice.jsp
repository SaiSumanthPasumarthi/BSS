<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Broadcast <strong>Details</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Broadcast Details</li>
				</ol>
			</div>
		</div>
		<label id="serviceNameLable" style="color: #006600">${returnValue}</label>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-header bg-light">
										<h3>Monthly Broadcast Details</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-alt" id="productsTable">
													<thead>
														<tr>
															<th width="7%">S.No</th>
															<th width="10%">Invoice Number</th>
															<th width="10%">Broadcaster</th>
															<th width="10%">Invoice Date</th>
															<th width="10%">Invoice Month Year</th>
															<th width="10%">Invoice Amount  </th>
															<th width="10%">Service Tax  </th>
															<th width="15%">Swatch Bharat Cess </th>
															<th width="10%">Krushi-Kissan Cess</th>
															<th width="10%">Total</th>
															<th width="10%">View</th>
															<!-- <th>Edit/Delete</th> -->
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty monthlyInvoiceList}">
															<c:forEach items="${monthlyInvoiceList}" var="monthlyInvoice"
																varStatus="rowNum">
																<tr>
																	<td>${rowNum.count}</td>
																	<td>${monthlyInvoice[0]}</td>
																	<td>${monthlyInvoice[1]}</td>
																	<td>${monthlyInvoice[2]}</td>
																	<td>${monthlyInvoice[8]}</td>
																	<td>${monthlyInvoice[4]}</td>
																	<td>${monthlyInvoice[5]}</td>
																	<td>${monthlyInvoice[6]}</td>
																	<td>${monthlyInvoice[7]}</td>
																	<td>${monthlyInvoice[9]}</td>
																	<td align="center">
																		<form class="viewInvoiceDetailsForm"  method="post">
																			<input type="hidden" name="invNo" value="${monthlyInvoice[0]}" /> 
																			<span class="viewInvoiceDetails" onmouseover="this.style.cursor='pointer'" title="View Invoice Details"><img src="./resources/images/apf_view.png"></span>
																		</form>
																	</td>
																</tr>
															</c:forEach>
														</c:if>
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
		<!-- END MAIN ROW -->
		<div class="clear"></div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->