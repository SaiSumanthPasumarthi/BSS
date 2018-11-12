
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/aaaUsageReportHour.js"></script>
<!-- custom file -->
<%-- @Srinivas V @since March 15 2017  --%>
<style>
div.dataTables_scrollBody thead th, div.dataTables_scrollBody thead td {
	line-height: 0;
	opacity: 0.0;
	width: 0px;
	height: 0px;
}
</style>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>AAA Usage Report By Hours</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>AAA Usage Report By Hours</li>
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
																		<form name="aaaUsage_Form" id="aaaUsageByHourReport_Form">
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Month:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">

																							<select id='monthsListId' name="monthSelected" class="form-control form-white">
																								
																								<option selected value=''>--SELECT--</option>
																								<c:choose>
																									<c:when test="${month eq '1'}">
																										<option selected value="1">January</option>
																									</c:when>
																									<c:otherwise>
																										<option value="1">January</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '2'}">
																										<option selected value="2">February</option>
																									</c:when>
																									<c:otherwise>
																										<option value="2">February</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '3'}">
																										<option selected value="3">March</option>
																									</c:when>
																									<c:otherwise>
																											<option value="3">March</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '4'}">
																											<option selected value="4">April</option>
																									</c:when>
																									<c:otherwise>
																											<option value="4">April</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '5'}">
																										<option selected value="5">May</option>
																									</c:when>
																									<c:otherwise>
																										<option value="5">May</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '6'}">
																										<option selected value="6">June</option>
																									</c:when>
																									<c:otherwise>
																										<option value="6">June</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '7'}">
																										<option selected value="7">July</option>
																									</c:when>
																									<c:otherwise>
																										<option value="7">July</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '8'}">
																										<option selected value="8">August</option>
																									</c:when>
																									<c:otherwise>
																										<option value="8">August</option>
																									</c:otherwise>
																								</c:choose>
																								<c:choose>
																									<c:when test="${month eq '9'}">
																										<option selected value="9">September</option>
																									</c:when>
																									<c:otherwise>
																										<option value="9">September</option>
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
																							<select id='yearListId' name="yearSelected"
																								class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Account Number:</label>
																						</div>
																						<div class="form-group">
																							<input type="text" id='cafNumSearchId'
																								name="cafSelected"
																								class="form-control form-white number"
																								placeholder="Enter Account Number" maxlength="10">
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label"></label>
																						</div>
																						<div class="form-group">
																							<button type="button" id="aaaUsgaeByIDSearch"
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
																	<table class="table table-alt no-wrap"
																		id="aaaUsageByHoursTable">
																		<thead>
																			<tr>
																				<th rowspan="2">Account No${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">Customer / Organization Name${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">ONU Serial No${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">ONU MAC Address${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">Date${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">Total Used Size[MB]${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">Total Download Size[MB]${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th rowspan="2">Total Upload Size[MB]${'&nbsp;&nbsp;&nbsp;'}</th>
																			
																				<th colspan="2"  style="text-align:center;">0-1 Hour [MB]</th>
																				<th colspan="2"  style="text-align:center;">1-2 Hour [MB]</th>
																				<th colspan="2"  style="text-align:center;">2-3 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">3-4 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">4-5 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">5-6 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">6-7 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">7-8 Hour [MB]</th>
																				<th colspan="2"  style="text-align:center;">8-9 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">9-10 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">10-11 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">11-12 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">12-13 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">13-14 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">14-15 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">15-16 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">16-17 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">17-18 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">18-19 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">19-20 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">20-21 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">21-22 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">22-23 Hour [MB]</th>
																				<th colspan="2" style="text-align:center;">23-24 Hour [MB]</th>
																			</tr>
																			<tr>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Download${'&nbsp;&nbsp;&nbsp;'}</th>
																				<th>Upload${'&nbsp;&nbsp;&nbsp;'}</th>
																			</tr>
																		</thead>
																		<tbody id="aaaUsageByHourTableBody">
																			<c:forEach items="${list}" var="value">
																				<tr>
																					<td>${value.cafSelected}</td>
																					<td>${value.customerName}</td>
																					<td>${value.onuSerialNo}</td>
																					<td>${value.onuMacAddress}</td>
																					<td>${value.monthSelected}</td>
																					<td>${value.totalUsage}</td>
																					<td>${value.totalUsageOnDay}</td>
																					<td>${value.uploadTotal}</td>
																					<td>${value.zeroTo1}</td>
																					<td>${value.up0to1}</td>
																					<td>${value.oneTo2}</td>
																					<td>${value.up1to2}</td>
																					<td>${value.twoTo3}</td>
																					<td>${value.up2to3}</td>
																					<td>${value.threeTo4}</td>
																					<td>${value.up3to4}</td>
																					<td>${value.fourTo5}</td>
																					<td>${value.up4to5}</td>
																					<td>${value.fiveTo6}</td>
																					<td>${value.up5to6}</td>
																					<td>${value.sixTo7}</td>
																					<td>${value.up6to7}</td>
																					<td>${value.sevenTo8}</td>
																					<td>${value.up7to8}</td>
																					<td>${value.eightTo9}</td>
																					<td>${value.up8to9}</td>
																					<td>${value.nineTo10}</td>
																					<td>${value.up9to10}</td>
																					<td>${value.tenTo11}</td>
																					<td>${value.up10to11}</td>
																					<td>${value.elevenTo12}</td>
																					<td>${value.up11to12}</td>
																					<td>${value.twelveTo13}</td>
																					<td>${value.up12to13}</td>
																					<td>${value.thirteenTo14}</td>
																					<td>${value.up13to14}</td>
																					<td>${value.fourteenTo15}</td>
																					<td>${value.up14to15}</td>
																					<td>${value.fifteenTo16}</td>
																					<td>${value.up15to16}</td>
																					<td>${value.sixteenTo17}</td>
																					<td>${value.up16to17}</td>
																					<td>${value.seventeenTo18}</td>
																					<td>${value.up17to18}</td>
																					<td>${value.eighteenTo19}</td>
																					<td>${value.up18to19}</td>
																					<td>${value.nineteenTo20}</td>
																					<td>${value.up19to20}</td>
																					<td>${value.twentyTo21}</td>
																					<td>${value.up20to21}</td>
																					<td>${value.twenty1To22}</td>
																					<td>${value.up21to22}</td>
																					<td>${value.twenty2To23}</td>
																					<td>${value.up22to23}</td>
																					<td>${value.twenty3To24}</td>
																					<td>${value.up23to24}</td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
														<div class="panel panel-default" id="dataTable_DivPanelId">
															<div class="panel-body">
																<div class="col-sm-12"></div>
																<button type="button" id="downLoadAAAByHours"
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
