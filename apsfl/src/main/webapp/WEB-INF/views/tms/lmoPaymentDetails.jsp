<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>jJsonViewer</title>
<style>
.modal-dialog {
	width: 1200px;
	margin: 30px auto;
}
</style>
</head>
<body>
	<jsp:useBean id="monthNames" class="java.text.DateFormatSymbols" />
	<c:set value="${monthNames.months}" var="months" />
	<div class="main-content">
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Customer Details</h2>
				<label id="error" style="text-align: center; color: red;"></label>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Customer Details</li>
					</ol>
				</div>
			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form:form method="GET" modelAttribute="" id="jsonErrorFormID">
								<div class="row">
									<div class="col-sm-12">
										<div class="row">
											<div style="margin: auto; width: 30%" id="comsErrorMsg">
												<font face="Times New Roman" size="4px" color="Red">${error}</font>
											</div>
											<div style="margin: auto; width: 30%" id="comsErrorMsg">
												<font face="Times New Roman" size="4px" color="Green">${message}</font>
											</div>
										</div>
										<div class="row">
											<input type="hidden" name="hiddenReqIds" id="hiddenReqIds_ID">
											<table id="provReqListID" class="table table-alt display">
												<thead>
													<tr>
														<th width="15%">Year</th>
														<th width="15%">Month</th>
														<th width="15%">Monthly Due</th>
														<th width="5%"></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${lmoapsflshare}" var="lmoshare"
														varStatus="status">
														<tr>
															<td>${lmoshare.year}</td>
															<td>${months[lmoshare.month-1]}</td>
															<td id="apsflshare">${lmoshare.apsflShare}</td>
															<td><input type="radio" name="radio"
																class="form-white" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="row " id="pymntDiv">
											<div class="col-sm-12">
												<div class="panel-header bg-light">
													<h3>Payment Information</h3>
												</div>
												<div class="col-sm-3" style="bottom: -9px">
													<table>
														<tbody>
															<tr>
																<td><b>Enter Amount</b> &nbsp;&nbsp;&nbsp;</td>
																<td><input type="text" name="addAmt"
																	class="form-control form-white " id="addAmt"
																	placeholder="Enter Amount" maxlength="9"></td>
															</tr>
														</tbody>
													</table>
												</div>
												<div class="col-sm-4" hidden="hidden">
													<div class="panel-header">
														<table>
															<tr>
																<td><b>Select Payment Mode:</b> &nbsp;&nbsp;&nbsp;</td>
																<td><select name="paymentMode"
																	class="form-control form-white " id="paymentMode"
																	style="width: 150px">
																		<option value="" style="max-width: 35px;">Credit
																			Card</option>
																</select></td>
															</tr>
														</table>
													</div>
												</div>
												<!-- <div class="col-sm-3">
													<button style="margin-top: 6px;"
														class="btn btn-embossed btn-primary" id="paymentBtn"
														type="submit">Pay Now</button>
												</div> -->
											</div>
										</div>
										<!-- Modal -->
										<div id="myModal" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size">
													<div class="modal-header"></div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
																<div class="ScrollStyle"
																	style="width: 1160px; overflow-x: auto;"></div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form:form>
							<div class="col-sm-3">
													<button style="margin-top: 6px;"
														class="btn btn-embossed btn-primary" id="paymentBtn"
														type="submit">Pay Now</button>
												</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<form action="./payment" id="PaymentId" method="POST">
			<!-- <input type="hidden" name="custId1" id="custId1"/> -->
			<input type="hidden" name="paymentMode" class="paymentModeId" />
			<input type="hidden" name="addAmt1" id="addAmt1"/>
		</form>
	</div>
	
</body>
<script type="text/javascript">
$(document).ready(function() {
	$('#provReqListID').on("click",'input[type=radio]',function() {
						var checkStatus = false;
						var apsflshare = 0;
						$('#provReqListID').find('tr').each(
								function() {
									if ($(this).find('input[type="radio"]').is(
											':checked')) {
										apsflshare = $.trim($(this).find(
												'#apsflshare').text().replace(
												/[\t\n]+/g, ' '));
										$("#addAmt").val(apsflshare);
									}
								});
						$('#paymentBtn').on('click',function() {
											var paymentMode = "card";
											var amt = $('#addAmt').val();
											if (amt == '') {
												alert('Please enter Amount');
												return false;
											} else if (amt < apsflshare) {

												alert('Please do not enter less than your monthly due Amount');
												return false;
											} else {
												$('#addAmt1').val(amt);
											}
											
											$('#paymentModeId').val(paymentMode);
											$('#PaymentId').submit();

										});

					});
});
</script>
</html>