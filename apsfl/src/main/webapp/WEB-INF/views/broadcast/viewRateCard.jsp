<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Display <strong>Broadcasters</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Display Broadcasters</li>
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
										<h3>Broadcasters Information</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-alt" id="productsTable">
													<thead>
														<tr>
															<th width="7%">S.No</th>
															<th width="15%">Broadcaster Code</th>
															<th width="7%">View</th>
															<!-- <th>Edit/Delete</th> -->
														</tr>
													</thead>
													<tbody>
														<c:if test="${not empty broadcasterCodeList}">
															<c:forEach items="${broadcasterCodeList}" var="broadcasters"
																varStatus="rowNum">
																<tr>
																	<td>${rowNum.count}</td>
																	<td>${broadcasters}</td>
																	<td align="center">
																		<form class="viewRateCardForm"  method="post">
																			<input type="hidden" name="broadcasterCode" value="${broadcasters}" /> 
																			<span class="viewBroadcasterPriceDetails" onmouseover="this.style.cursor='pointer'" title="View RateCard Details"><img src="./resources/images/apf_view.png"></span>
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