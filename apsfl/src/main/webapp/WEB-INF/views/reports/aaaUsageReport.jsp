
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="./resources/js/aaaUsageReport.js"></script>
<!-- custom file -->
<%-- @Srinivas V @since March 15 2017  --%>
 <style>
div.dataTables_scrollBody thead th,
div.dataTables_scrollBody thead td {
line-height: 0;
opacity:0.0;
width: 0px;
height:0px;
}
</style>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>HSI Session Wise Report</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>HSI Session Wise Report</li>
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
																		<form name="aaaUsageForm" id="aaaUsageReportForm"
																			action="aaaUsageReportDownload">
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Districts:</label>
																						</div>
																						<div class="form-group">
																							<select id='districtListId' name="districtId"
																								class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																								<c:forEach var="dList" items="${districtList}">
																									<option value="${dList[0]}">
																										<c:out value="${dList[1]}" />
																									</option>
																								</c:forEach>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Mandals:</label>
																						</div>
																						<div class="form-group">
																							<select id='mandalsListId' name="MandalId"
																								class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Villages:</label>
																						</div>
																						<div class="form-group">
																							<select id='villagesListId' name="VillageId"
																								class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Mobile Number:</label>
																						</div>
																						<div class="form-group">
																							<input type="text" id='mobileNumUsageReportid'
																								name="mobileNO"
																								class="form-control form-white number"
																								placeholder="Enter Mobile No " maxlength="10">
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">From Date:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">
																							<input type="text" name="fromDate"
																								id="dateFromAaaUsage"
																								class="form-control form-white"
																								placeholder="Select From Date"
																								required="required">
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">To Date:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">
																							<input type="text" name="toDate"
																								id="dateToAaaUsage"
																								class="form-control form-white"
																								placeholder="Select To Date" required="required">
																						</div>
																					</div>
																					<div class="row">
																						<div class="col-sm-3">
																							<div class="form-group">
																								<label class="control-label">Account Number:</label>
																							</div>
																							<div class="form-group">
																								<input type="text" id="cafNo" name="accountCafNo"
																									class="form-control form-white number"
																									placeholder="Enter Account Number" maxlength="10">
																							</div>
																						</div>
																						<div class="col-sm-3">
																							<div class="form-group">
																								<label class="control-label">Aadhar Number:</label>
																							</div>
																							<div class="form-group">
																								<input type="text" id='aadharNoUsageReportId'
																									name="aadharNo"
																									class="form-control form-white number"
																									placeholder="Enter Aadhar No" maxlength="12">
																							</div>
																						</div>
																						
																						
																						<div class="col-sm-3">
																						<div>
																							<label class="control-label">Month:<span
																								style="color: red;">*</span></label>
																						</div>
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
																						
																						<input type="hidden" id = "hiddenSelectYear" value="${year}">
																						
																						<div class="col-sm-3">
																						<div>
																							<label class="control-label">Year:<span
																								style="color: red;">*</span></label>
																						</div>
																							<select id='yearListId' name="year"
																								class="form-control form-white" required="required">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					<div class="form-group">
																						<label class="control-label"> </label>
																					</div>
																					<div class="form-group">
																						<button type="button" id="seachAaaUsageReportId"
																							class="btn btn-embossed btn-primary pull-right">
																							<i class="fa fa-search"></i>Search
																						</button>
																					</div>
																				</div>
																			</div>
																		</form>
																	</div>
																</div>
																<div class="panel panel-default" id="dataTable_DivPanelId">
																	<div class="panel-body">
																		<div class="col-sm-12">
																			<table class="table table-alt no-wrap"	id="aaaUsageReportTable">
																				<thead>
																					<tr>
																						<th>Start Time${'&nbsp;&nbsp;&nbsp; '}</th>
																						<th>End Time${'&nbsp;&nbsp;&nbsp; '}</th>
																						<th>Session Time${' &nbsp;&nbsp;&nbsp; '}</th>
																						<th>Session Terminate Cause ${'&nbsp;&nbsp;&nbsp;'}</th>
																						<th>Account No ${'&nbsp;&nbsp;&nbsp;'} </th>
																						<th>Customer / Organization Name${'&nbsp;&nbsp;&nbsp;'} </th>
																						<th>PoP Name${' &nbsp;&nbsp;&nbsp; '}</th>
																						<th>Total Size [MB]${'&nbsp;&nbsp;&nbsp;'}</th>
																						<th>Download Size [MB]${' &nbsp;&nbsp;&nbsp; '}</th>
																						<th>Upload Size [MB]${' &nbsp;&nbsp;&nbsp;'}</th>
																					</tr>
																				</thead>
																			</table>
																		</div>
																	</div>
																</div>
																<button type="button" id="downLoadAAAReportId"
																	class="btn btn-embossed btn-primary pull-left">
																	<i class="fa fa-download" aria-hidden="true"></i>Download
																</button>
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
			</div>
		</div>
	</section>
</body>
