
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.arbiva.apsfl.util.*"%> --%>

<script type="text/javascript" src="./resources/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-sliderAccess.js"></script>

<style>
.ct-active{
	
	 color:green ;
	 font-weight: bold;
	 back-ground-color: red;
	 	}

</style>





<script type="text/javascript" >
$(document).ready(function() {
	
		
	$("#offlinepaymentTable tbody tr:first").addClass("ct-active");
		
	
	 	$('#offlinepaymentTable').dataTable(); 
	 	

	
	$("#valid_date").datepicker({
		dateFormat: 'yy-mm-dd',
		numberOfMonths: 1,
	    changeMonth: true,
	    changeYear: true,
	    onSelect: function (selected) {
	        var dt = new Date(selected);
	        dt.setDate(dt.getDate() - 1);
	      
	    }
	});
	

	$("#submit").click(function () { 
		if ($("#valid_date").val() == "" )
	{
		alert("Please enter date");
		return false; } })
		
			$("#Reject").click(function () { 
		if ($("#valid_date").val() == "" )
	{
		alert("Please enter date");
		return false; } })
		
		
		
			$("#submit").click(function () { 
		 $("#status").val("1");
	     })
		
			$("#Reject").click(function () { 
		$("#status").val("2");
	   })
	   
	   
  	$("#cheque_ddno").val("");
	$("#lmocode").val("");
	$("#bankname").val("");
	$("#amount").val("");
	$("#valid_date").val("");
	$("#comments").val("");
	$("#payment_mode").val("");
	$("#comments").val("");
	
 		  
/* 	var cheque_ddno = document.getElementById('cheque_ddno');
	cheque_ddno.oninvalid = function(cheque_ddno) {
		
	    event.target.setCustomValidity('Username should only contain lowercase letters. e.g. john');
	} */
	  

	
});
</script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title" style="margin-left: 50px">
					<h2>LMO Cheque/DD  Payment Entry Screen</h2>

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
																
																 <form:form method="POST" modelAttribute="OfflinePayment" id="form_id" enctype="multipart/form-data" action="savelmoofflinepayment1">  
																	<div class="row">
																		<div class="col-lg-12">
																		
																		<div class="col-sm-3">
                                                                       <div class="form-group">
						                                              <label class="control-label">NEFT/Cheque/DD No.<span style="color: red;">*</span></label>                    							
						                                                 <form:input id="cheque_ddno" path="cheque_ddno" cssClass="form-control form-white" placeholder="Enter ChequeNo." maxlength="20" pattern="[A-Za-z0-9]+" title="Please enter alphanumeric only" required="required"/>
					                                                   </div>
				                                                      </div>
				                                                        
					                                                        <div class="col-sm-3">
					                                                        <div class="form-group">
				 	                                                          <label class="control-label">LMO Code<span style="color: red;">*</span></label>
				 	                                                         <form:input id="lmocode" path="lmocode" cssClass="form-control form-white" placeholder="Enter LMO COde" maxlength="10" pattern="[A-Za-z0-9]+" title="Please enter alphanumeric only" required="required"/> 
		                                                                  </div>
				                                                          </div>
				                                                          
				                                                            
			                                                               <div class="col-sm-3">
					                                                       <div class="form-group">
					                                                        <label class="form-label">Bank Name<span style="color: red;">*</span></label>
					                                                          <form:input id="bankname" path="bankname" cssClass="form-control form-white " placeholder="Enter bankname" maxlength="30" pattern="^[a-zA-Z ]+$" title="Please enter Alphabet only" required="required"/> 
					                                                         </div>
			                                                                </div>			                                                                
                                               
				                                                        <div class="col-sm-3">
					                                                        <div class="form-group">
				 	                                                          <label class="control-label">Amount<span style="color: red;">*</span></label>
				 	                                                         <form:input id="amount" path="amount" cssClass="form-control form-white" placeholder="Enter Amount" maxlength="20" pattern="[0-9]+" title="Please enter numbers only" required="required"/> 
		                                                                    </div>
				                                                            </div>
				                                                            
				                                                            <div class="col-sm-3">
					                                                       <div class="form-group">
					                                                        <label class="form-label">Payment Mode<span style="color: red;">*</span></label>
					                                              <form:select  id="payment_mode" path="payment_mode" cssClass="form-control form-white" required="required">
																<form:option value="">--Select--</form:option>
																<form:option value="Cheque payment">Cheque payment</form:option>
																<form:option value="DD payment">DD payment</form:option>
																<form:option value="NEFT">NEFT</form:option>
																	
																</form:select> 
			                                                                </div>
			                                                                </div>

																			
																			<div class="col-sm-3">
																				<div class="form-group">
																					<label class="form-label">Cheque/DD Date<span
																						style="color: red;">*</span></label>
																						 <form:input id="valid_date" path="valid_date" cssClass="form-control form-white number" placeholder="Enter Amount" maxlength="20" required="required"/>
																				</div>
																			</div>
																			
																				<div class="col-sm-3">																			
																				<div class="form-group">
																					<label class="form-label">Enter Comment</label>
                                                                               <form:input id="comments" path="comments" cssClass="form-control form-white" placeholder="Enter comments" maxlength="50"/>
																				</div>
																			</div>
																			
																			

       																		<div class="col-md-4">
																				<div class="form-group">
																					<label class="control-label"></label>
																				</div>
																				<div class="form-group">
																				
																					<button class="btn btn-embossed btn-primary"
																						type="submit" id="submit">
																						Approve
																					</button>
																				</div>
																			</div>
																			
																		       <div class="col-md-4">
																				<div class="form-group">
																					<label class="control-label"></label>
																				</div>
																				<div class="form-group">
																				 <form:input  type="hidden" id="status" path="status" cssClass="form-control form-white number"/>
																					<button class="btn btn-embossed btn-primary"
																						type="submit" id="Reject">
																						Reject
																					</button>
																				</div>
																			</div>	
																			
																			
																			
																</div>
																</div>
																

																	
															 </form:form> 
															 
	
																											
			
															<div style="text-align: center;"><label class="form-label" > <span style="font-size: 150%;margin: auto;text-align: center;">${statusCodes} </span></label>
															</div>
															

												<div class="form-group">
													<a
														href="
															<c:url value="approver1ChequeDetails">
															
															</c:url>">
															<INPUT	TYPE="BUTTON" VALUE="showlist" class="btn btn-success">
													</a>

												</div>
															
															
															<div>
															</div>
														
															
															 <div class="row">
															 

															<table class="table table-alt" id="offlinepaymentTable">
																<thead>
																	<tr class="titleRow"
																		style="background-color: rgb(242, 242, 242);">
																		<th>S.no</th>
																		<th>Cheque/DD No.</th>
																		<th>LmoCode</th>
																		<th>Bank Name</th>
																		<th>Status</th>
																		<th>Amount</th>

																	</tr>


																</thead>
																<tbody>
																	<c:forEach items="${offline_PaymentList}"
																		var="ChequeDetails" varStatus="theCount">
																		<tr>
																			<td><c:out value="${theCount.count}"></c:out></td>
																			<td><c:out value="${ChequeDetails.cheque_ddno}"></c:out></td>
																			<td><c:out value="${ChequeDetails.lmocode}"></c:out></td>
																			<td><c:out value="${ChequeDetails.bankname}"></c:out></td>
																			<td><c:out value="${ChequeDetails.status}"></c:out></td>
																			<td><c:out
																					value="${ChequeDetails.amount}"></c:out></td>
													
																		</tr>
																	</c:forEach>
																</tbody>

															</table>
															 
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
