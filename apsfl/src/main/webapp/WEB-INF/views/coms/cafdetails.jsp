<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
function showRecord(custId) {
	window.location.href = "<c:url value="/cafdetails"/>?custId=" + custId;
}
</script>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Customer <strong>Details</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li>Home</li>
					<li class="active"><a href="./viewcustomers">Display Customers</a></li>
					<li>Customer Details</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<form role="form">
							<span class="addCafs" onmouseover="this.style.cursor='pointer'"></span>
							<div class="row m-b-10">
								<!-- END ROW INNER-->
								<div class="col-sm-12">
									<div class="panel">
										<div class="panel-header bg-light">
											<h3>Customer Information</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">First Name/Organization Name</label>
														<input type="text" value="${customers.custName}" name="firstName" id="firstName" class="form-control form-white" readonly />
													</div>
												</div>

												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Last Name</label> 
														<input type="text" value="${customers.lName}" name="lastName" id="lastName" class="form-control form-white" readonly />
													</div>
												</div>

												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Father's/Husband Name</label>
														<input type="text" value="${customers.fhName}" name="middleName" id="middleName" class="form-control form-white" readonly />
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Email</label> 
														<input type="text" value="${customers.email1}" name="emailId" id="emailId" class="form-control form-white" readonly />
													</div>
												</div>
											</div>
											<div class="clear"></div>
											<!-- END ROW INNER-->
											<div class="row">
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Date of Birth/Inc</label> 
														<input type="text" value='<fmt:formatDate pattern="MM/dd/yyyy" value="${customers.dateofinc}"/>' name="middleName" id="middleName" class="form-control form-white" readonly />
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Gender</label>
														<div class="option-group">
															<input type="text" <c:choose><c:when test ="${customers.gender eq 'M'.charAt(0) }"> value="Male"</c:when><c:when test ="${customers.gender eq 'F'.charAt(0) }">value="Female"</c:when></c:choose> name="gender" id="gender" class="form-control form-white" readonly />
														</div>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Mobile No.</label> 
														<input type="text" value="${customers.pocMob1}" class="form-control form-white" readonly>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Bill Cycle</label> 
														<input type="text" value=" ${customers.billfreqLov}" class="form-control form-white" readonly>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">Customer Type</label> 
														<input type="text" value=" ${customers.custType}" class="form-control form-white" readonly>
													</div>
												</div>
												<div class="col-sm-2">
													<div class="form-group">
														<label class="control-label">LMO Code</label> 
														<input type="text" value=" ${customers.lmoCode}" class="form-control form-white" readonly>
													</div>
												</div>
												<div class="clear"></div>
											</div>
											<!-- END ROW INNER-->
										</div>
									</div>
								</div>
								<!-- END ROW INNER-->
							</div>
							<!-- END ROW INNER-->
							<c:choose>
								<c:when test="${not empty cafDaoList}">
									<label id="fingerPrintId" style="color: 'green'; size: 3;"></label>
									<div class="row m-b-10">
										<div class="col-sm-12 disable-search">
											<table class="table  table-alt">
												<thead>
													<tr>
														<th>S.No</th>
														<th>CAF No</th>
														<th>Security Deposit</th>
														<th>Balance</th>
														<th>Activation Date</th>
														<th>Status</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${cafDaoList}" var="caf"
														varStatus="rowNum">
														<tr class="cafrow" id="outtr${rowNum.count}">
															<td>${rowNum.count}</td>
															<td class="cafno" id="cafno${rowNum.count}">${caf[0]}</td>
															<td><c:choose><c:when test="${not empty caf[1]}"> <fmt:formatNumber groupingUsed="true" minFractionDigits="2" value="${caf[1]}" /></c:when> <c:otherwise>0.00</c:otherwise></c:choose></td>
															<td><c:choose><c:when test="${not empty caf[2]}"><fmt:formatNumber groupingUsed="true" minFractionDigits="2" value="${caf[2]}" /></c:when><c:otherwise>0.00</c:otherwise></c:choose></td>
															<td>${caf[4]}</td>
															<td>${caf[3]}</td>
															<td class="cafno" id="cafno" align="center">
																<c:if test="${tenantType == 'APSFL'}">
																	<img class="deActCaf" onclick="deActCaf(${rowNum.count})" onmouseover="this.style.cursor='pointer'" title="DeActivate" src="./resources/images/apf_add.png" style="float: right;">
																</c:if> 
																<img class="imgp" onclick="showdata(${rowNum.count})" onmouseover="this.style.cursor='pointer'" id="imgp${rowNum.count}" title="View Caf Details" src="./resources/images/apf_add.png" style="float: right;"> 
																<img class="imgm" id="imgm${rowNum.count}" onclick="hidedata(${rowNum.count})" onmouseover="this.style.cursor='pointer'" title="Hide Caf Details" src="./resources/images/apf_sub.png" style="float: right; display: none;">
															</td>
														</tr>
														<tr class="dataRow" id="intr${rowNum.count}" style="display: none; border: 2px solid #d4d5d6;">
															<td colspan="7" style="background-color: #fff;">
																<!--Display data when click the row-->
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel">
																			<div class="panel-header bg-light">
																				<h3>CPE Installation Address</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="getinstadres${rowNum.count}">
																							<thead>
																								<tr>
																									<th>Address</th>
																									<th>District</th>
																									<th>Mandal</th>
																									<th>City/Village</th>
																									<th>Pincode</th>
																									<th>Longitude</th>
																									<th>Latitude</th>
																									<th>Location</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div> <!-- END ROW -->
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel">
																			<div class="panel-header bg-light">
																				<h3>CPE Information</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="getAllParticularCPE${rowNum.count}">
																							<thead>
																								<tr>
																									<th>POP Name</th>
																									<th>OLT ID</th>
																									<th>OLT Port ID/No</th>
																									<th>ONU Model</th>
																									<th>ONU Ownership</th>
																									<th>ONU Upfront Charge</th>
																									<th>ONU EMI Charge</th>
																									<th>ONU EMI Count</th>
																									<th>ONU SrlNo</th>
																									<th>ONU MacId</th>
																									<!-- <th>IPTVBox Model</th>
																									<th>IPTVBox Ownership</th>
																									<th>IPTVBox Charge</th>
																									<th>IPTVBox EMI Count</th>
																									<th>IPTVBox SrlNo</th>
																									<th>IPTVBox MacId</th> -->
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div> <!-- END ROW -->
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel">
																			<div class="panel-header bg-light">
																				<h3>IPTVBox Information</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="getStbInfo${rowNum.count}">
																							<thead>
																								<tr>
																									<th>S No.</th>
																									<th>IPTVBox Caf No</th>
																									<th>IPTVBox Model</th>
																									<th>IPTVBox Ownership</th>
																									<th>IPTVBox Upfront Charge</th>
																									<th>IPTVBox EMI Charge</th>
																									<th>IPTVBox EMI Count</th>
																									<th>IPTVBox SrlNo</th>
																									<th>IPTVBox MacId</th>
																									<th>N/W Subscriber Code</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div> <!-- END ROW -->
																<div class="row">
																	<div class="col-sm-12">
																		<div class="panel">
																			<div class="panel-header bg-light">
																				<h3>Telephone Details</h3>
																			</div>
																			<div class="panel-content">
																			<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="telenoid${rowNum.count}">
																							<thead>
																								<tr>
																									<th>S No.</th>
																									<th>Telephone No</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																				<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="telephonetable${rowNum.count}">
																							<thead>
																								<tr>
																									<th>Supplementary Service Name</th>
																									<th>Parameter Code</th>
																									<th>Parameter Value</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div> <!-- END ROW -->
																<div class="row">
																	<div class="col-sm-6">
																		<div class="panel"
																			style="height: 300px; overflow-y: auto;">
																			<div class="panel-header bg-light">
																				<h3>
																					Selected Package and Services
																					<c:if test="${not empty orgName}"> For ${orgName}</c:if>
																				</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row">
																					<div class="col-sm-12 disable-search">
																						<table class="table table-dynamic table-alt" id="cafdetails${rowNum.count}">
																							<thead>
																								<tr>
																									<th>S.No</th>
																									<th>IPTV CAF No</th>
																									<th>Package Name</th>
																									<th>Package Type</th>
																									<th>Services</th>
																									<th>Purchased On</th>
																									<th>Total Package Charge</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	
																	<div class="col-sm-6">
																		<div class="panel"
																			style="height: 300px; overflow-y: auto;">
																			<div class="panel-header bg-light">
																				<h3>
																					Payment Information
																				</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row">
																					<div class="col-sm-12 disable-search">
																						<table class="table table-dynamic table-alt" id="RecentPaymentsInformation${rowNum.count}">
																							<thead>
																								<tr>
																									<th>S.NO</th>
																									<th>Payment Ref No</th>
																									<th>Payment Type</th>
																									<th>Payment Date</th>
																									<th>Payment Amount</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
														
																</div>
																<div class="row">
																	<div class="col-sm-6">
																		<div class="panel"
																			style="height: 200px; overflow-y: auto;">
																			<div class="panel-header bg-light">
																				<h3>Bill Information</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row"></div>
																				<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="BillInfotable${rowNum.count}">
																							<thead>
																								<tr>
																								<tr>
																									<th>S.NO</th>
																									<th>Bill Month</th>
																									<th>Amount</th>
																									<th>Due Date</th>
																									<th>Status</th>
																								</tr>
																							</thead>
																							<tbody>

																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																	<div class="col-sm-6">
																		<div class="panel"
																			style="height: 200px; overflow-y: auto;">
																			<div class="panel-header bg-light">
																				<h3>Ticket Information</h3>
																			</div>
																			<div class="panel-content">
																				<div class="row">
																					<div class="col-sm-12 disable-search" id="#divId">
																						<table class="table table-dynamic table-alt" id="TicketInfotable${rowNum.count}">
																							<thead>
																								<tr>
																								<tr>
																									<th>S.NO</th>
																									<th>Ticket No.</th>
																									<th>Submit Date</th>
																									<th>Brief Description</th>
																									<th>Status</th>
																									<th>Close Date</th>
																								</tr>
																							</thead>
																							<tbody>
																							</tbody>
																						</table>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="row">
																</div>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<center>
										<font color='red' size="3">No Cafs found for the search criteria</font>
									</center>
								</c:otherwise>
							</c:choose>
							<div class="clear"></div>
						</form>
						<!-- END FORM -->
					</div>
				</div>
			</div>
			<!--connection data end -->
		</div>
	</div>
	<!-- END MAIN PANEL CONTENT -->
</div>