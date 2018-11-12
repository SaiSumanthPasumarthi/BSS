
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/aaaUsageReportHour.js"></script>
<style>
.modal-dialog {
    height: 500px;
    overflow-y: auto;
}
</style>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>HSI Usage report for customer</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>HSI Usage report for customer</li>
						</ol>
					</div>
				</div>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="row">
													<div class="panel panel-default ">
														<div class=" panel-body ">
															<div class="panel-group">
																<div class="panel panel-default">
																	<div class="panel-body">
																		<form name="aaaUsage_Form" id="aaaUsagePerMonth_Form" action="getTotalAAAUsagePerMonthCustomer" method="POST">
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Month:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">

																							<select id='monthsListId' name="month" class="form-control form-white" required="required">
																								
																								<option selected value=''>--SELECT--</option>
																								<c:choose>
																									<c:when test="${month eq '01'}">
																										<option selected value="01">January</option>
																									</c:when>
																									<c:otherwise>
																										<option value="01">January</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '02'}">
																										<option selected value="02">February</option>
																									</c:when>
																									<c:otherwise>
																										<option value="02">February</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '03'}">
																										<option selected value="03">March</option>
																									</c:when>
																									<c:otherwise>
																											<option value="03">March</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '04'}">
																											<option selected value="04">April</option>
																									</c:when>
																									<c:otherwise>
																											<option value="04">April</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '05'}">
																										<option selected value="05">May</option>
																									</c:when>
																									<c:otherwise>
																										<option value="05">May</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '06'}">
																										<option selected value="06">June</option>
																									</c:when>
																									<c:otherwise>
																										<option value="06">June</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '07'}">
																										<option selected value="07">July</option>
																									</c:when>
																									<c:otherwise>
																										<option value="07">July</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '08'}">
																										<option selected value="08">August</option>
																									</c:when>
																									<c:otherwise>
																										<option value="08">August</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '09'}">
																										<option selected value="09">September</option>
																									</c:when>
																									<c:otherwise>
																										<option value="09">September</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '10'}">
																										<option selected value="10">October</option>
																									</c:when>
																									<c:otherwise>
																										<option value="10">October</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '11'}">
																										<option selected value="11">November</option>
																									</c:when>
																									<c:otherwise>
																										<option value="11">November</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '12'}">
																										<option selected value="12">December</option>
																									</c:when>
																									<c:otherwise>
																										<option value="12">December</option>
																									</c:otherwise>
																								</c:choose>
																							</select>
																						</div>
																					</div>
																					<input type="hidden" id = "hiddenSelectYear" value="${year}">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Year:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">
																							<select id='yearListId' name="year"
																								class="form-control form-white" required="required">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Account Number<span style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">
																							<input class="form-control form-white" type="text" name="cafNo" required="required" value="${cafNo}">
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label"></label>
																						</div>
																						<div class="form-group">
																							<button type="submit"
																								class="btn btn-embossed btn-primary pull-left">
																								<i class="fa fa-search"></i>Search
																							</button>
																						</div>
																					</div>
																				</div>
																			</div>
																		</form>
																	</div>
																</div>
															</div>
														</div>
														<div class="panel panel-default">
															<div class="panel-body">
																<div class="col-sm-12">
																	<table class="table table-alt" >
																		<thead>
																		<tr>
																		<th>Customer Name</th>
																		<th>Plan Data Size [GB]</th>
																		<th>Year</th>
																		<th>Month</th>
																		<th>Total Download [GB]</th>
																		<th>Total Upload [GB]</th>
																		<th>Total Usage [GB]</th>
																		<!-- <th>Subscribers Count</th> -->
																		</tr>
																		</thead>
																		<tbody>
																			<tr>
																				<c:if test="${ not empty monthlySummary}">
																					<td>${monthlySummary.custName}</td>
																					<td>${monthlySummary.planUsage}</td>
																					<td>${monthlySummary.usageYYYY}</td>
																					<td>${monthlySummary.usageMM}</td>
																					<td>${monthlySummary.totalDwlSize}</td>
																					<td>${monthlySummary.totalUplSize}</td>
																					<td>${monthlySummary.totalUsedSize}</td>
																					<%-- <td>${monthlySummary.subsCount}</td> --%>
																				</c:if>
																			</tr>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
														
														<div class="panel panel-default">
															<div class="panel-body">
																<div class="col-sm-12">
																	<table class="table table-alt" id="hsiPerMonthAndHourCustTableId">
																		<thead>
																		<tr>
																		<th>Year</th>
																		<th>Date-Month</th>
																		<th>Total Download [GB]</th>
																		<th>Total Upload [GB]</th>
																		<th>Total Usage [GB]</th>
																		<!-- <th>Subscribers Count</th>-->
																		<!-- <th>View Hour Wise</th> -->
																		<th>View SixHours Wise</th>
																		
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		<th hidden="hidden">1down</th>
																		
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		<th hidden="hidden">1up</th>
																		</tr>
																		</thead>
																		<tbody>
																			<c:forEach items="${list}"var="monthlySummary" varStatus="count">
																				<tr>
																					<td>${monthlySummary.usageYYYY}</td>
																					<td>${monthlySummary.monthDay}</td>
																					<td>${monthlySummary.daydnldusage}</td>
																					<td>${monthlySummary.dayupldusage}</td>
																					<td>${monthlySummary.dayTotalUsage}</td>
																					<%-- <td>${monthlySummary.daysubscount}</td> --%>
<!-- 																					<td><a data-toggle="modal" href=#
									 													data-target="#myModal" class="viewHrCust"> View
																							Hour Wise Records</a></td> --> 
																																															<td><a data-toggle="modal" href=#
																						data-target="#myModal1" class="viewSixHrCust"> View
																							SixHours Wise Records</a></td>

																					<td hidden="hidden" class="1dow">${monthlySummary.dlh01}</td>
																					<td hidden="hidden" class="2dow">${monthlySummary.dlh02}</td>
																					<td hidden="hidden" class="3dow">${monthlySummary.dlh03}</td>
																					<td hidden="hidden" class="4dow">${monthlySummary.dlh04}</td>
																					<td hidden="hidden" class="5dow">${monthlySummary.dlh05}</td>
																					<td hidden="hidden" class="6dow">${monthlySummary.dlh06}</td>
																					<td hidden="hidden" class="7dow">${monthlySummary.dlh07}</td>
																					<td hidden="hidden" class="8dow">${monthlySummary.dlh08}</td>
																					<td hidden="hidden" class="9dow">${monthlySummary.dlh09}</td>
																					<td hidden="hidden" class="10dow">${monthlySummary.dlh10}</td>
																					<td hidden="hidden" class="11dow">${monthlySummary.dlh11}</td>
																					<td hidden="hidden" class="12dow">${monthlySummary.dlh12}</td>
																					<td hidden="hidden" class="13dow">${monthlySummary.dlh13}</td>
																					<td hidden="hidden" class="14dow">${monthlySummary.dlh14}</td>
																					<td hidden="hidden" class="15dow">${monthlySummary.dlh15}</td>
																					<td hidden="hidden" class="16dow">${monthlySummary.dlh16}</td>
																					<td hidden="hidden" class="17dow">${monthlySummary.dlh17}</td>
																					<td hidden="hidden" class="18dow">${monthlySummary.dlh18}</td>
																					<td hidden="hidden" class="19dow">${monthlySummary.dlh19}</td>
																					<td hidden="hidden" class="20dow">${monthlySummary.dlh20}</td>
																					<td hidden="hidden" class="21dow">${monthlySummary.dlh21}</td>
																					<td hidden="hidden" class="22dow">${monthlySummary.dlh22}</td>
																					<td hidden="hidden" class="23dow">${monthlySummary.dlh23}</td>
																					<td hidden="hidden" class="24dow">${monthlySummary.dlh24}</td>

																					<td hidden="hidden" class="1up">${monthlySummary.ulh01}</td>
																					<td hidden="hidden" class="2up">${monthlySummary.ulh02}</td>
																					<td hidden="hidden" class="3up">${monthlySummary.ulh03}</td>
																					<td hidden="hidden" class="4up">${monthlySummary.ulh04}</td>
																					<td hidden="hidden" class="5up">${monthlySummary.ulh05}</td>
																					<td hidden="hidden" class="6up">${monthlySummary.ulh06}</td>
																					<td hidden="hidden" class="7up">${monthlySummary.ulh07}</td>
																					<td hidden="hidden" class="8up">${monthlySummary.ulh08}</td>
																					<td hidden="hidden" class="9up">${monthlySummary.ulh09}</td>
																					<td hidden="hidden" class="10up">${monthlySummary.ulh10}</td>
																					<td hidden="hidden" class="11up">${monthlySummary.ulh11}</td>
																					<td hidden="hidden" class="12up">${monthlySummary.ulh12}</td>
																					<td hidden="hidden" class="13up">${monthlySummary.ulh13}</td>
																					<td hidden="hidden" class="14up">${monthlySummary.ulh14}</td>
																					<td hidden="hidden" class="15up">${monthlySummary.ulh15}</td>
																					<td hidden="hidden" class="16up">${monthlySummary.ulh16}</td>
																					<td hidden="hidden" class="17up">${monthlySummary.ulh17}</td>
																					<td hidden="hidden" class="18up">${monthlySummary.ulh18}</td>
																					<td hidden="hidden" class="19up">${monthlySummary.ulh19}</td>
																					<td hidden="hidden" class="20up">${monthlySummary.ulh20}</td>
																					<td hidden="hidden" class="21up">${monthlySummary.ulh21}</td>
																					<td hidden="hidden" class="22up">${monthlySummary.ulh22}</td>
																					<td hidden="hidden" class="23up">${monthlySummary.ulh23}</td>
																					<td hidden="hidden" class="24up">${monthlySummary.ulh24}</td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													<!-- 	<div class="panel panel-default" id="dataTable_DivPanelId">
															<div class="panel-body">
																<div class="col-sm-12"></div>
																<button type="button" id="downLoadAAAByHours"
																	class="btn btn-embossed btn-primary pull-left">
																	<i class="fa fa-download" aria-hidden="true"></i>Download
																</button>
															</div>
														</div> -->
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Hour Wise Usage</h4>
      </div>
      <div class="modal-body" id="hsiHourUsagePopUoDiv">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<div id="myModal1" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">SixHours Wise Usage</h4>
      </div>
      <div class="modal-body" id="hsiHourUsagePopUoDiv3">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>

