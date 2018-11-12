
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="./resources/js/create-product.js"></script>
<!-- BEGIN MAIN CONTENT -->
<div class="main-content" >
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> Package Details </strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Update</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form"  action="./updateProdPrices">
							
							<div class="row">
								<div class="col-sm-12">
								<label>${status}</label>
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Package  Information</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Code</label> <input
															type="text" name="productCode" class="form-control form-white"
															value="${product.productCode}" readonly="readonly">
													</div>
												</div>
												
												<div class="col-md-3">
													<div class="form-group">
														<label class="form-label">Tenant Code</label> <input
															type="text" name="tenantcode" class="date-picker form-control form-white"
															value="${product.tenantcode}" readonly="readonly">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Name</label> <input
															type="text"  class="form-control form-white"
															value="${product.productName}" readonly="readonly">
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<label class="form-label">Product Effective From</label> <input
															type="text" name="effectiveFrom" class="date-picker form-control form-white"
															value="${product.validFrom}" readonly="readonly">
															<input type = "text" id="prodEffcToId" hidden="hidden" value="${product.validTo}" />
													</div>
												</div>
												
												
											</div>
											
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Customer Type</label> <input
															type="text"  class="form-control form-white"
															value="${product.custType}" readonly="readonly">
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Package Type</label> <input
															type="text" class="form-control form-white"
															value="${product.prodType}" readonly="readonly">
													</div>
												</div>
												<div class="col-md-3">
													<div class="form-group">
														<label class="form-label">Lock In Months</label> <input
															type="text" class="date-picker form-control form-white"
															value="${product.lockInDays}" readonly="readonly">
													</div>
												</div>
												
												<div class="col-md-3">
													<div class="form-group">
														<label class="form-label">Charge Effective From</label> <input
															type="text"  name="chargesEffectiveFrom" class="date-picker form-control form-white productValidateClass" id="Datepicker1"
															 required="required" >
													</div>
												</div>
											</div>
											<!-- END ROW INNER-->
												<input hidden="hidden" id="rowCount">
												<input hidden="hidden" id="srvcName">
												<input hidden="hidden" id="coreSrvcCode">
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
															
																<tr id="outtr${service}" >
																	<td>${service.serviceName}</td>
																	<td>${service.srvccode}</td>
																	<td>${service.coreServiceName} 
																	<img class="imgp" id="imgp${rowNum.count}" 
																	onClick="javascript:getChargeCodes('${service.srvccode},${product.productCode},${product.tenantcode}',
																	'${rowNum.count}','${service.serviceName}','${service.coreServiceCode}')"  
																	onMouseOver="this.style.cursor='pointer'"
																	data-toggle="modal" data-target="#popup" src="./resources/images/apf_add.png" align="right">
																	</td>
																	<td hidden="hidden"><input name="servicesList[${rowNum.index}].srvccode" value="${service.srvccode}"/></td>
																</tr>
																<tr id="intr${rowNum.count}"  class="rowdata" >
																	<td colspan="3" style="padding-left: 30px;">
																	<table class="table table-alt" id="inTable${rowNum.count}" style="width: 98%; align: center; padding-left: 100px;">
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
																	<c:forEach var = "charge" items="${service.chargesList}" varStatus="charIndex">
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
																	<td><input name="servicesList[${rowNum.index}].chargesList[${charIndex.index}].chargeAmt" class="form-control form-white numbersOnly" value="${charge.chargeAmt}" required="required"> </td>
																	<td>${charge.taxPercentage}</td>
																	<td>${charge.glCode}</td>
																	<td hidden="hidden"><input name="servicesList[${rowNum.index}].chargesList[${charIndex.index}].featureCode" value="${charge.featureCode}"/></td>
																	<td hidden="hidden"><input name="servicesList[${rowNum.index}].chargesList[${charIndex.index}].chargeCode" value="${charge.chargeCode}"/></td>
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
											<button class="btn btn-embossed btn-primary" type="submit" id="updateProdChar">Update</button>
											<a href ="./home"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
										</div>
									</div>
								</div>
							</div>
							<!-- END ROW -->
							
						</form>
						<!-- END FORM -->
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		
		
			<div id="popup" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal">&times;</button>
			        <h4 class="modal-title">Charge Codes</h4>
			      </div>
			      <div class="modal-body" id="chargeCodesTableForPV">
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-success" data-dismiss="modal" onclick="getValuesFromPopupAndSetToSelectedTable()">Select</button>
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
