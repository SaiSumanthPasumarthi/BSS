<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/mspLmoCpeRequest.js"></script>

<div id="monthlyCafDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					<strong>Pending Approvals</strong>
				</h2>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<div class="row main-row">
								<div class="col-lg-12">
									<div class="panel main-panel">
										<div class="panel-content main-panel-content">
										<div class="row m-b-5"> <div id="alist" style="font-size: 100%; color:red;"></div><br>
										<div id="rlist" style="font-size: 100%; color:red;"></div>
										</div>
											<form action="./SecondApproverManagerUpdate" method="POST" id="secondapprove" onload="SubmitSecondApprove()">
												<div class="row m-b-5">
													<div class="col-sm-12">

														<table class="table table-alt" id="approvalslist">
															<thead>
																<tr>
																	<th width="15%">lmocode</th>
																	<th width="15%">Cheque/DD.No</th>
																	<th width="15%">Payment Mode</th>
																	<th width="15%">Deposit Date</th>
																	<th width="15%">Amount</th>
																	<th width="15%">Transaction Ref ID</th>
																	<th width="15%">Comments</th>
																	<th width="15%">Approve</th>
																	<th width="15%">Reject</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="cellData" varStatus="loop"
																	items="${pendinglist}">
																	<c:if test = "${cellData.status == 1}">
																	<tr class="bulkrec">
																		<td width="15%">
																		<input type="text"
																			class="form-control form-white"
																			placeholder="Enter Comments" name="lmocode"
																			id="${ cellData.id}-lmocode"
																			value="${cellData.lmocode}"  readonly/>
																			<input type="hidden"
																			class="form-control form-white"
																			placeholder="Enter Comments" name="amount"
																			id="${ cellData.id}-amount"
																			value="${cellData.amount}"  readonly/>
																		
																			</td>
																		<td width="15%"><c:out
																				value="${cellData.cheque_ddno}"></c:out></td>
																		<td width="15%"><c:out
																				value="${cellData.payment_mode}"></c:out></td>
																		<td width="15%"><input type="text"
																			class="date-picker form-control form-white"
																			placeholder="Select a date..." name="effectivefrom"
																			id="${ cellData.id}-dep_date"
																			value="${cellData.dep_date}"
																			onfocus="showCalendar(this)"
																			onmouseover="showCalendar(this)" /></td>
																		<td width="15%"><input type="text"
																			class="form-control form-white"
																			placeholder="Enter amount" name="amount"
																			id="${ cellData.id}-amount"
																			value="${cellData.amount}" readonly />
																			</td>
																		<td width="15%"><input type="text"
																			class="form-control form-white"
																			placeholder="Enter Transaction ID" name="trans_id"
																			id="${ cellData.id}-trans_id"
																			value="${cellData.trans_id}" /></td>
																		<td width="15%"><input type="text"
																			class="form-control form-white"
																			placeholder="Enter Comments" name="comments"
																			id="${ cellData.id}-comments"
																			value="${cellData.comments}" /></td>
																		<%-- <td width="15%"><c:out value="${cellData.id}"></c:out></td> --%>
																		<td class="check"><input type=checkbox
																			id="${ cellData.id}-value" name="selectbox1" class="selectbox"
																			value="${cellData.id}"
																			onchange="concatData(this,${cellData.id})"></td>
																			<td class="check"><input type=checkbox
																			id="${ cellData.id}-value1" name="selectbox2" class="selectbox"
																			value="${cellData.id}"
																			onchange="concatData1(this,${cellData.id})"></td>
																	</tr>
																	</c:if>
																</c:forEach>


															</tbody>
														</table>
														<div class="form-group">
															<button class="btn btn-embossed btn-primary"
																type="Submit" id="chequesearch">
																Submit
															</button>
														</div>
													</div>

												</div>
											</form>


										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>



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
</div>
<!-- END MAIN CONTENT -->
