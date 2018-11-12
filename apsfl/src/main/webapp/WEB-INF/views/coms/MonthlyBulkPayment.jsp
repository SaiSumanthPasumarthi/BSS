<%@page import="com.arbiva.apsfl.coms.dto.ComsHelperDTO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	$(document)
			.ready(
					function() {
						
						$("#monthlyPaymentTable1").dataTable(); 
						var hiddenYear = $('#hiddenSelectYear').val();
						var min_year = new Date().getFullYear();
						min_year = 2016;
						max_year = min_year + 15;
						var div_data = "";
						for (var i = min_year; i <= max_year; i++) {
							if (i == hiddenYear) {
								div_data = div_data
										+ "<option selected value=" + i+ ">"
										+ i + "</option>";
							} else {
								div_data = div_data
										+ "<option  value=" + i+ ">" + i
										+ "</option>";
							}
						}
						$(div_data).appendTo('#yearListId');

						$('#customerSearch')
								.click(
										function() {

											var month = $('#monthsListId')
													.val() == undefined ? ""
													: $('#monthsListId').val();
											var year = $('#yearListId').val() == undefined ? ""
													: $('#yearListId').val();

											if (month == "--Select--"
													&& year == "--Select--") {
												alert("Please Select month and year.");

											}
										});
					});
</script>


<div id="monthlyCafDiv">
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>
					Customer<strong> List</strong>
				</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./">Home</a></li>
						<li class="active">Customer List</li>
					</ol>
				</div>
			</div>
			<div class="row main-row">
			<div class="col-lg-12">
			<div class="panel main-panel">
			<div class="panel-content main-panel-content">
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">
							<form name="monthlyBulkPaymentForm" id="monthlybulkPaymentForm" action="./searchBulkMonthlyPaymentCafDetails" method="post">
								<div class="row">
									<div class="col-sm-12">
										<div class="row">
											<div class="column">
 											
 												
 												
 												
									
									
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Month:<span
												style="color: red;">*</span></label>
										</div>
										<div class="form-group">

											<select id='monthsListId' name="invmn"
												class="form-control form-white" required="required">

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




									<input type="hidden" id="hiddenSelectYear" value="${year}">
									<div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Year:<span
												style="color: red;">*</span></label>
										</div>
										<div class="form-group">
											<select id='yearListId' name="invyr"
												class="form-control form-white" required="required">
												<option selected value=''>--SELECT--</option>
											</select>
										</div>
									</div>

									<div class="col-sm-3">
										<div class="form-group">

											<div class="option-group"></div>
										</div>
									</div>
 												
 
 												
													<div class="form-group"> 
 														<%-- <label class="control-label">Select Date<span style="color: red;"> </span></label> 
 														<input type="month" name="effectivefrom"   class="form-control form-white number" maxlength="10" placeholder="Enter Year:MM-YYYY" min="2017-05" max="2030-12" value="${effectivefrom}">   --%>
														<input type="hidden" id="hitcount"  name="hitcount" value="0">
 													</div>
 												
 												<div class="column">
 												<div class="col-sm-3">	
													<div class="form-group"> 
 														<button class="btn btn-embossed btn-primary" type="Submit" id=customerSearch>Customer Search</button>
 														<c:if test="${not empty message}">
															<h1 align="right"><font color='green' size="3">${message}</font></h1>
														</c:if>
 													</div>
 												</div>
 												
 												<div class="col-sm-3" style="visibility: hidden">	
													<div class="form-group"> 
 														<label class="control-label">To Date<span style="color: red;"> </span></label> 
 														<input type="month" name="effectiveto" id="effectiveto"  class="form-control form-white number" maxlength="10" placeholder="Enter Year:YYYY-MM" min="2017-05" max="2030-12">  
 													</div>
 												</div>
 												
 											</div>
 											 
 										</div>
 									</div>
 								</div>
 								</div>
 								</form>
 								<form name="clistform" id="clistform" action="./MonthlyBulkPayment" method="GET" onsubmit="retun checkbal()">
 								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Available Balance</label> 
										<input type="text" id="lmoWallet" name="lmoWallet" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${lmoWalletBalence}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Total Payable Amount</label> 
										<input type=text id="payableamount" name="payableamount"  class="form-control form-white" required readonly>
										<%-- <input type="hidden" id="effectivefrom"  name="effectivefrom" value="${effectivefrom}"> --%>
										<input type="hidden"  name="invmn" value="${month}">
										<input type="hidden"  name="invyr" value="${year}">
										
									</div>
								</div>
								<div class="col-sm-3">
									<div class="form-group">
										<label class="control-label">Used Amount</label> 
										<input type="text" id="lmoWallet" name="lmoWallet" value = "<fmt:formatNumber groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" value="${UsedAmount}"/>" class="form-control form-white" required readonly>
									</div>
								</div>
 									<div class="row m-b-5">
												<div class="col-sm-12">
													<table class="table table-alt" id="monthlyPaymentTable1">
														<thead>
															<tr>
																<th width="15%">Caf Number</th>
																<th width="15%">Customer Name</th>
																<th width="12%">Mobile Number</th>
																<th width="15%">Due Amount(Rs)</th>
																<th width="5%">Select</th>
		
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${clist}" var="payment">
																<tr class="bulkrec">
																	<td class="cafno">${payment.cafno}</td>
																	<td class="customername">${payment.customername}</td>
																	<td class="mobilenumber">${payment.mobile}</td>
																	<td class="dueamount" id="dueamount">${payment.dueamount}<input type="hidden" id="dueamount" name=dueamount value="${payment.dueamount}"></td>
																	<td class="check"><input type=checkbox id="selectbox" name="selectbox" class="selectbox" value="${payment.cafno}" onchange="updatePayableaamount(this,${payment.dueamount})"></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<div>
	 
	
															  <button class="btn btn-embossed btn-primary" type="Submit" id="mycount">Submit</button>
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
