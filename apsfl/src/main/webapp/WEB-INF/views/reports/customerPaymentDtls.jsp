<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%>


<script type="text/javascript">
$(document).ready(function() {
	
	 $(document).on('click','#customerPaymentDtlsButton',function() {
		  
	  if(($("#effectiveFrom_coms").val()=='' && $("#effectiveTo_coms").val()!= '')){
		 alert("Please Select From Date");
		 return false;
	  } 
	   if(($("#effectiveFrom_coms").val()!='' && $("#effectiveTo_coms").val()== '')){
		  alert("Please Select To Date");
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
					    <c:if test="${id eq 15}">
							<div class="row main-row">
								<div class="col-lg-12">
									<div class="panel main-panel">
										<div class="panel-content main-panel-content">
											<form name="report" id="report" method="get" action="./customerPaymentDtls">
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
															<div class="col-sm-1">
                        													<br>
                        													<label class="control-label">OR</label>
                    													</div>
															<div class="col-sm-3">
																<div class="form-group">
																	<label class="control-label">CustomerId<span style="color: red;">*</span></label> 
																	<input type="text" name="custId" id="custId" <c:if test="${not empty custId}"> value="${custId}" </c:if> class="form-control form-white" placeholder="Enter CustomerId">
																</div>
															</div>
															<div class="col-sm-2">
																<div class="form-group">
																	<label class="control-label hide-mobile">&nbsp;</label>
																	<div class="option-group">
																		<button class="btn btn-embossed btn-primary" type="submit" id="customerPaymentDtlsButton">
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
												<c:when test="${not empty paymentVOList}">
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
																<th>Payment Cust Id</th>
																<th>Customer Name</th>
																<th>Account Caf No.</th>
																<th>Payment Id</th>
																<th>Payment Amount</th>
																<th>Payment Mode</th>
																<th>Payment Date</th>
															</tr>
														</thead>
														<tbody>
															<c:choose>
																<c:when test="${not empty paymentVOList}">
																	<c:forEach items="${paymentVOList}" var="cafs">
																		<tr>
																			<td style="padding-right: 30px">${cafs.pmntCustId}</td>
																			<td style="padding-right: 30px">${cafs.customerName} ${cafs.mName} ${cafs.lName}</td>
																			<td style="padding-right: 30px">${cafs.acctCafNo}</td>
																			<td style="padding-right: 30px">${cafs.pmntId}</td>
																			<td style="padding-right: 30px">${cafs.paidAmount}</td>
																			<td style="padding-right: 30px">${cafs.paymentMode}</td>
																			<td style="padding-right: 30px">${cafs.pmntDate}</td>
																		</tr>
																	</c:forEach>
																</c:when>
															</c:choose>
														</tbody>
													</table>
													<form name="report" id="report" method="get" action="./downloadcustomerPaymentDtls">
														<input type="hidden" name="download" value="true">
														<input type="hidden" name="date1" value="${fromDate}">
														<input type="hidden" name="date2" value="${toDate}">
														<input type="hidden" name="custId" value="${custId}">
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