
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="./resources/js/create-product.js"></script>
<style>
.modal-dialog {
    width: 1200px;
    margin: 30px auto;
}
</style>
<!-- BEGIN MAIN CONTENT -->
<div class="main-content" >
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Display<strong> Package </strong>
			</h2>
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
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Code</label> <input
															type="text" name="Code" class="form-control form-white"
															value="${product.productCode}" readonly="readonly">
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Name</label> <input
															type="text" name="Name" class="form-control form-white"
															value="${product.productName}" readonly="readonly">
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Effective From</label> <input
															type="text" class="date-picker form-control form-white"
															value="${product.validFrom}" readonly="readonly">
													</div>
												</div>
											</div>
											
											<div class="row">
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Customer Type</label> <input
															type="text" name="Code" class="form-control form-white"
															value="${product.custType}" readonly="readonly">
													</div>
												</div>
												<div class="col-sm-4">
													<div class="form-group">
														<label class="control-label">Package Type</label> <input
															type="text" name="Name" class="form-control form-white"
															value="${product.prodType}" readonly="readonly">
													</div>
												</div>
												<div class="col-md-4">
													<div class="form-group">
														<label class="form-label">Lock In Months</label> <input
															type="text" class="date-picker form-control form-white"
															value="${product.lockInDays}" readonly="readonly">
													</div>
												</div>
											</div>
											<!-- END ROW INNER-->

											<div class="row">
												<div class="col-sm-12 disable-search">
													<table class="table  table-alt">
														<thead>
															<tr>
																<th>Service Name</th>
																<th>Code</th>
																<th>Core Service</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="service"  items="${product.servicesList}" varStatus="rowNum">
																<tr id="outtr${service}" class="trfirst" onClick="showdata(${rowNum.count})" onMouseOver="this.style.cursor='pointer'" title="Click To See Details">
																	<td>${service.serviceName}</td>
																	<td>${service.srvccode}</td>
																	<td>${service.coreServiceName}
																	<c:if test="${service.coreServiceCode eq 'IPTV'}">
																		<button class="btn btn-embossed btn-primary " style=" margin-left: 40%" data-toggle="modal" data-target="#myModal" type="button" id="viewSrvcFeatButton1">
																		View Channels</button>
																		<input type="hidden" id="serviceCodeId" value="${service.srvccode}">
																	</c:if> 
																	<img class="imgp" id="imgp${rowNum.count}"  src="./resources/images/apf_add.png" align="right">
																	<img class="imgm" id="imgm${rowNum.count}"  src="./resources/images/apf_sub.png" align="right" style="display: none;">
																	</td>
																</tr>
																<tr id="intr${rowNum.count}"  class="rowdata" style="display: none">
																	<td colspan="3" style="padding-left: 30px;">
																	<table class="table table-alt" style="width: 98%; align: center; padding-left: 100px;">
																	<thead>
																	<tr>
																	<c:if test="${service.coreServiceCode eq 'VOIP'}">
																		<th>Service/Feature Name</th>
																	</c:if>
																	<th>Charge Type</th>
																	<th>Charge Amount</th>
																	<th>Tax Percentage</th>
																	<th>GL Code</th>
																	</tr>
																	</thead>
																	<tbody>
																	<c:forEach var = "charge" items="${service.chargesList}">
																	<tr>
																	<c:if test="${service.coreServiceCode eq 'VOIP'}">
																		<c:choose>
																		<c:when test="${fn:trim(charge.featureName)  eq ''}">
																			<td>${service.serviceName}(S)</td>
																		</c:when>
																		<c:otherwise>
																			<td>${charge.featureName}(F)</td>
																		</c:otherwise>
																	</c:choose>
																	</c:if>
																	<td>${charge.chargeName}</td>
																	<td>${charge.chargeAmt}</td>
																	<td>${charge.taxPercentage}</td>
																	<td>${charge.glCode}</td>
																	</tr>
																	</c:forEach>
																	</tbody>
																	</table>
																	</td>
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

		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Service Features</h4>
					</div>
					<div class="modal-body" id="srvcFutuPopUoDiv"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END MAIN CONTENT -->
