<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Display <strong>RateCard</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Display RateCard</li>
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
										<h3>RateCard Details</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-alt" id="productsTable">
													<thead>
														<tr>
															<th width="7%">S.No</th>
															<th width="10%">PriceGroupCode</th>
															<th width="10%">Price</th>
															<th width="7%">View</th>
															<!-- <th>Edit/Delete</th> -->
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty broadcasterCodeList}">
															<c:forEach items="${broadcasterCodeList}" var="broadcasterPriceDetails"
																varStatus="rowNum">
																<tr>
																	<td>${rowNum.count}</td>
																	<td>${broadcasterPriceDetails[0]}</td>
																	<td>${broadcasterPriceDetails[1]}</td>
																	<td align="center">
																		<form class="viewChannelListForm"  method="post">
																			<input type="hidden" name="priceGroupCode" value="${broadcasterPriceDetails[0]}" /> 
																			<span class="viewChannelListDetails" onmouseover="this.style.cursor='pointer'" title="View Channel Details"><img src="./resources/images/apf_view.png"></span>
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