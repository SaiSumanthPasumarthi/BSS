
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>




<!-- <script type="text/javascript"
	src="./resources/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="./resources/js/jquery-ui-sliderAccess.js"></script> -->
	
	<style>
	tfoot input {
        width: 100%;
        padding: 3px;
        box-sizing: border-box;
    }
	</style>

<script type="text/javascript">
	$(document).ready(function() {

 		$('#lmowalletbalanceaftercchequeTable').dataTable(); 
		
		$("#effectivefrom").datepicker({
			dateFormat: 'yy-mm-dd',
			numberOfMonths: 1,
		    changeMonth: true,
		    changeYear: true,
		    onSelect: function (selected) {
		        var dt = new Date(selected);
		        dt.setDate(dt.getDate() + 1);
		    }
		});
		$("#effectiveto").datepicker({
			dateFormat: 'yy-mm-dd',
			numberOfMonths: 1,
		    changeMonth: true,
		    changeYear: true,
		    onSelect: function (selected) {
		        var dt = new Date(selected);
		        dt.setDate(dt.getDate() - 1);
		      
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
				<div class="page-title" style="margin-left: 50px">
					<h2>LMO NEFT/Cheque/DD payment details</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<div class="row main-row" style="margin-left: 45px">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="panel panel-default">
															<div class="panel-body">

																<form action="./getLmoWalletUpdateByChequePayment"
																	name="getLmoWalletUpdateByChequePayment"
																	id="getLmoWalletUpdateByChequePayment" method="POST"
																	novalidate>
																	<div class="row">

																		<div class="col-sm-2">
																			<div class="form-group">
																				<label class="form-label">LmoCode</label> <input id="lmocode"
																					name="lmocode" class="form-control form-white "
																					placeholder="Enter LMOCode" maxlength="30"
																					required="required" />
																			</div>
																		</div>
																		
														              <div class="col-sm-2">
																			<div class="form-group">
																				<label class="form-label">NEFT/Cheque/DD No.</label> <input id="cheque_ddno"
																					name="cheque_ddno" class="form-control form-white "
																					placeholder="Enter Cheque/DD No." maxlength="30"
																					required="required" />
																			</div>
																		</div>
																		
																		<div class="col-sm-2">
																				<div class="form-group">
																					<label class="form-label">From</label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectivefrom" id="effectivefrom" value="${hiddenfromdate}"/>
																				</div>
																			</div>
																			
																			<div class="col-sm-2">
																				<div class="form-group">
																					<label class="form-label">To</label> <input type="text"
																						class="date-picker form-control form-white"
																						placeholder="Select a date..."
																						name="effectiveto" id="effectiveto"  value="${hiddentodate}"/>
																				</div>
																			</div>

																		<div class="col-sm-2">
																			<div class="form-group">
																				<label class="control-label"></label>
																			</div>
																			
																			<div class="row">
																			<div class="form-group">
																				<button class="btn btn-embossed btn-primary"
																					type="Submit" id="searchButtons1">
																					<i class="fa fa-search"></i>Search
																				</button>
																			</div>
																			</div>
																		</div>

																	</div>
																</form>
																
																<div class="row">
																
																
																</div>


																<div class="row">
																	<table class="table table-alt"
																		id="lmowalletbalanceaftercchequeTable">
																		<thead>
																			<tr class="titleRow"
																				style="background-color: rgb(242, 242, 242);">
																				<th>S.no</th>
																				<th>LmoCode</th>
																				<th>Cheque/DD No.</th>
																				<th>Cheque/DD Amount</th>
																				<th>Name Of Bank</th>
																				<th>Payment mode</th>
																				<th>Status</th>
																				<th>Deposited date</th>
																				<th>LMO Deposited Amnt</th>

																			</tr>


																		</thead>
																		<tbody>
																			<c:forEach
																				items="${lmoWalletUpdateByChequePaymentList}"
																				var="LmoWalletDetails" varStatus="theCount">
																				<tr>
																					<td><c:out value="${theCount.count}"></c:out></td>

																					<td><c:out value="${LmoWalletDetails.lmoCode}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.cheque_DDno}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.cheque_Dd_Amount}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.bankName}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.paymentMode}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.status}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.dep_date}"></c:out></td>
																					<td><c:out
																							value="${LmoWalletDetails.lmo_walletAmnt}"></c:out></td>

																				</tr>
																			</c:forEach>
																		</tbody>


																	</table>

																	<a
																		href="
																					<c:url value="./downloadgetLmoWalletUpdateByChequePayment">
																						 <c:param name="lmocode" value="${lmocode}"/>
																						 <c:param name="cheque_ddno" value="${lmocode}"/>
																						 <c:param name="effectivefrom" value="${effectivefrom}"/>
																						 <c:param name="effectiveto" value="${effectiveto}"/>
																						 

																					</c:url>">
																		<INPUT TYPE="SUBMIT" VALUE="Download"
																		class="btn btn-success">
																	</a>

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
		</div>
	</section>
</body>
