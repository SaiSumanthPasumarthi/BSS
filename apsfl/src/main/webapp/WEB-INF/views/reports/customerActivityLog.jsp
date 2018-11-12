<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>


<script type="text/javascript">
$(document).ready(function() {
	
	 $(document).on('click','#customerActivityLogButton',function() {
		  
		  if(($("#effectiveFrom_coms").val()=='' && $("#effectiveTo_coms").val()== '')){
			  alert("Please Select DateRange");
			  return false;
		  }
		  if(($("#nwCode").val()== '') && ($("#stbMacId").val()== '')){
			  alert("Please Select SubscriberCode or Mac Id");
			  return false;
		  } 
	 });
});
</script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="row main-row">
					<div class="col-lg-12">
					    <c:if test="${id eq 14}">
							<div class="row main-row">
								<div class="col-lg-12">
									<div class="panel main-panel">
										<div class="panel-content main-panel-content">
											<form name="report" id="report" method="get" action="./customerActivityLog">
												<!-- END ROW INNER-->
												<div class="row">
													<div class="col-sm-12">
														<div class="row">
															<div class="col-sm-2">
																<div class="form-group">
																	<label class="control-label">From Date<span style="color: red;">*</span></label> 
																	<input type="text" name="date1" id="effectiveFrom_coms" <c:if test="${not empty fromDate}"> value="${fromDate}" </c:if> class="date-picker form-control form-white" placeholder="Select From Date">
																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group">
																	<label class="control-label">To Date<span style="color: red;">*</span></label> 
																	<input type="text" name="date2" id="effectiveTo_coms" <c:if test="${not empty toDate}"> value="${toDate}" </c:if> class="date-picker form-control form-white" placeholder="Select From Date">
																</div>
															</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">Network Subscribercode<span style="color: red;">*</span></label> 
																	<input type="text" name="nwCode" id="nwCode" <c:if test="${not empty nwCode}"> value="${nwCode}" </c:if> class="form-control form-white" placeholder="Enter Subscribercode">
																</div>
															</div>
															<div class="col-sm-1">
                        													<br>
                        													<label class="control-label">OR</label>
                    													</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">STB MacId<span style="color: red;">*</span></label> 
																	<input type="text" name="stbMacId" id="stbMacId" <c:if test="${not empty stbMacId}"> value="${stbMacId}" </c:if> class="form-control form-white" placeholder="Enter MacId">
																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group">
																	<label class="control-label hide-mobile">&nbsp;</label>
																	<div class="option-group">
																		<button class="btn btn-embossed btn-primary" type="submit" id="customerActivityLogButton">
																			<i class="fa fa-search"></i>Search
																		</button>
																	</div>
																</div>
															</div>
															<div class="clear"></div>
														</div>
													</div>
												</div>
											</form>
											<c:choose>
												<c:when test="${not empty reportsVO}">
													<c:if test="${not empty message}">
														<center>
															<font color='red' size="3">${message}</font>
														</center>
													</c:if>
													<c:if test="${not empty message1}">
														<font style="float: right;" color='green' size="3">${message1}</font>
													</c:if>
													<table class="table table-alt" id="customerActivityLogTable">
														<thead>
															<tr>
																<th>Aadhar No</th>
																<th>NWSubscr.Code</th>
																<th>STB SrlNo</th>
																<th>STB MacId</th>
																<th>Package Name</th>
																<th>Activity</th>
																<th>Transaction Date</th>
																<th>Message</th>
															</tr>
														</thead>
														<tbody>
															<c:choose>
																<c:when test="${not empty reportsVO}">
																	<c:forEach items="${reportsVO.cafDates}" var="cafs">
																		<tr>
																			<td style="padding-right: 30px">${cafs.aadharNo}</td>
																			<td style="padding-right: 30px">${cafs.nwSubCode}</td>
																			<td style="padding-right: 30px">${cafs.stbSrlNo}</td>
																			<td style="padding-right: 30px">${cafs.stbMacAddress}</td>
																			<td style="padding-right: 30px">${cafs.serviceCode}</td>
																			<td style="padding-right: 30px">Customer Services Activated</td>
																			<td>${cafs.actDate}</td>
																			<td style="padding-right: 30px">NA</td>
																		</tr>
																		<c:if test="${cafs.suspDates != 'NA'}">
																			<c:forTokens items="${cafs.suspDates}" delims="," var="name" varStatus="status">
																				<tr>
																					<td style="padding-right: 30px">${cafs.aadharNo}</td>
																					<td style="padding-right: 30px">${cafs.nwSubCode}</td>
																					<td style="padding-right: 30px">${cafs.stbSrlNo}</td>
																					<td style="padding-right: 30px">${cafs.stbMacAddress}</td>
																					<td style="padding-right: 30px">${cafs.serviceCode}</td>
																					<td style="padding-right: 30px">Customer Suspended</td>
																					<td><c:out value="${name}" /></td>
																					<td style="padding-right: 30px">NA</td>
																				</tr>
																			</c:forTokens>
																		</c:if>
																		<c:if test="${cafs.suspDates != 'NA'}">
																			<c:forTokens items="${cafs.resumedates}" delims="," var="name1">
																				<tr>
																					<td style="padding-right: 30px">${cafs.aadharNo}</td>
																					<td style="padding-right: 30px">${cafs.nwSubCode}</td>
																					<td style="padding-right: 30px">${cafs.stbSrlNo}</td>
																					<td style="padding-right: 30px">${cafs.stbMacAddress}</td>
																					<td style="padding-right: 30px">${cafs.serviceCode}</td>
																					<td style="padding-right: 30px">Customer Services Resumed</td>
																					<td><c:out value="${name1}" /></td>
																					<td style="padding-right: 30px">NA</td>
																				</tr>
																			</c:forTokens>
																		</c:if>
																		<c:if test="${cafs.deactDate!= 'NA'}">
																			<tr>
																				<td style="padding-right: 30px">${cafs.aadharNo}</td>
																				<td style="padding-right: 30px">${cafs.nwSubCode}</td>
																				<td style="padding-right: 30px">${cafs.stbSrlNo}</td>
																				<td style="padding-right: 30px">${cafs.stbMacAddress}</td>
																				<td style="padding-right: 30px">${cafs.serviceCode}</td>
																				<td style="padding-right: 30px">Customer Deactivated</td>
																				<td>${cafs.deactDate}</td>
																				<td style="padding-right: 30px">NA</td>
																			</tr>
																		</c:if>
																	</c:forEach>
																	<c:forEach items="${reportsVO.osdFingPrnt}" var="osdDtls">
																		<c:if test="${osdDtls.requestType=='OSD'}">
																			<tr>
																				<td style="padding-right: 30px">${osdDtls.aadharNo}</td>
																				<td style="padding-right: 30px">${osdDtls.nwSubCode}</td>
																				<td style="padding-right: 30px">${osdDtls.stbSrlNo}</td>
																				<td style="padding-right: 30px">${osdDtls.stbMacAddress}</td>
																				<td style="padding-right: 30px">${osdDtls.serviceCode}</td>
																				<td style="padding-right: 30px">OSD</td>
																				<td>${osdDtls.requestDate}</td>
																				<td style="word-break: break-all;">${osdDtls.detailedMessage}</td>
																			</tr>
																		</c:if>
																		<c:if test="${osdDtls.requestType=='FINGER_PRINT'}">
																			<tr>
																				<td style="padding-right: 30px">${osdDtls.aadharNo}</td>
																				<td style="padding-right: 30px">${osdDtls.nwSubCode}</td>
																				<td style="padding-right: 30px">${osdDtls.stbSrlNo}</td>
																				<td style="padding-right: 30px">${osdDtls.stbMacAddress}</td>
																				<td style="padding-right: 30px">${osdDtls.serviceCode}</td>
																				<td style="padding-right: 30px">FINGER PRINT</td>
																				<td>${osdDtls.requestDate}</td>
																				<td style="padding-right: 30px">NA</td>
																			</tr>
																		</c:if>
																		<c:if test="${osdDtls.requestType=='SCROLL_TEXT'}">
																			<tr>
																				<td style="padding-right: 30px">${osdDtls.aadharNo}</td>
																				<td style="padding-right: 30px">${osdDtls.nwSubCode}</td>
																				<td style="padding-right: 30px">${osdDtls.stbSrlNo}</td>
																				<td style="padding-right: 30px">${osdDtls.stbMacAddress}</td>
																				<td style="padding-right: 30px">${osdDtls.serviceCode}</td>
																				<td style="padding-right: 30px">SCROLL TEXT</td>
																				<td>${osdDtls.requestDate}</td>
																				<td style="word-break: break-all;">${osdDtls.detailedMessage}</td>
																			</tr>
																		</c:if>
																		<c:if test="${osdDtls.requestType=='blacklist'}">
																			<tr>
																				<td style="padding-right: 30px">${osdDtls.aadharNo}</td>
																				<td style="padding-right: 30px">${osdDtls.nwSubCode}</td>
																				<td style="padding-right: 30px">${osdDtls.stbSrlNo}</td>
																				<td style="padding-right: 30px">${osdDtls.stbMacAddress}</td>
																				<td style="padding-right: 30px">${osdDtls.serviceCode}</td>
																				<td style="padding-right: 30px">Customer Blacklisted</td>
																				<td>${osdDtls.requestDate}</td>
																				<td style="padding-right: 30px">NA</td>
																			</tr>
																		</c:if>
																	</c:forEach>
																</c:when>
															</c:choose>
														</tbody>
													</table>
													<form name="report" id="report" method="get" action="./downloadcustomerActivityLog">
														<input type="hidden" name="download" value="true">
														<input type="hidden" name="date1" value="${fromDate}">
														<input type="hidden" name="date2" value="${toDate}">
														<input type="hidden" name="nwCode" value="${nwCode}">
														<input type="hidden" name="stbMacId" value="${stbMacId}">
														<button class="btn btn-embossed btn-primary" type="submit">Download</button>
													</form>
												</c:when>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>			