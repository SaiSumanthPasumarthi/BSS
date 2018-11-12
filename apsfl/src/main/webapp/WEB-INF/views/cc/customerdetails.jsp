<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<section>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong>Customer Details</strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Customer Care</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-content">
										<form name="custDtlsForm" id="custDtlsForm" action="./ccCustomerDetails" method="post">
											<div class="row">
												<div class="col-sm-12">
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">Customer Mobile Number</label> 
															<input type="text" name="custMob" id="custMob" class="form-control form-white " placeholder="Enter Registered Mobile Number" value="<c:if test="${not empty Mobile}">${Mobile}</c:if>" maxlength="10">
														</div>
													</div>
													<div class="col-sm-1">
														<br> <label class="control-label">OR</label>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label">Customer Id</label> 
															<input type="text" name="custId" id="custId" class="form-control form-white" placeholder="Enter Registerd customer Id" value="<c:if test="${not empty Id}">${Id}</c:if>">
														</div>
													</div>
													<div class="col-sm-2">
														<div class="form-group">
															<label class="control-label hide-mobile">&nbsp;</label>
															<div class="option-group">
																<button class="btn btn-embossed btn-primary" type="submit" id="custDtlsSearch">
																	<i class="fa fa-search"></i>Search
																</button>
															</div>
														</div>
													</div>
												</div>
											</div>
										</form>
									</div>
										<c:if test="${not empty message1}">
											<font style="float: center;" color='green' size="3">${message1}</font>
										</c:if>
									<c:if test="${not empty customerList}">
										<div class="panel-header bg-light">
											<h3>Customer Details</h3>
										</div>
										<div class="panel-content">
											<div class="row">
												<div class="col-sm-12">
													<table class="table table-alt" id="custDtls">
														<thead>
															<tr>
															    <th>S.No</th>
															    <th>Customer Name</th>
																<th>Caf No</th>
																<th>Available Balance</th>
																<th hidden="hidden">Cust Id</th>
																<th hidden="hidden">Dist Id</th>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${customerList}" var="custList" varStatus="rowNum">
																<tr>
																    <td>${rowNum.count}</td>
																	<td class="custName2">${custList[0]} ${custList[1]}</td>
																	<td class="cafNo1">${custList[2]}</td>
																	<td>${custList[4]}</td>
																	<td hidden="hidden" class="custId2">${custList[6]}</td>
																	<td hidden="hidden" class="distId1">${custList[5]}</td>
																	<td><input type="radio" name="radio" id="addBalRadio" class="form-white"/></td>
																	  
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<!-- END ROW INNER-->
										</div>
											<div class="row" id ="pymntDiv">
											   <div class="col-sm-12">
										       <div class="panel-header bg-light" style="padding: 3px;margin-bottom: 15px;">
											       <h3>Payment Information</h3>
										       </div>
										       <div class="col-sm-3">
											       <table>
													 <tbody>
														 <tr>
														     <td><b>Enter Amount</b> &nbsp;&nbsp;&nbsp;</td>
														     <td><input type="text" name="addAmt" id="addAmt" class="form-control form-white number " placeholder="Enter Amount" maxlength="9"></td>
														 </tr>
													</tbody>
												  </table>
												</div>
											    <div class="col-sm-4">
											       <div class="panel-header">
											       <table>
											         <tr>
											         <td>
											            <b>Select Payment Mode:</b> &nbsp;&nbsp;&nbsp;</td>
											          <td>
												      <select name="paymentMode" id="paymentMode" class="form-control form-white" style="width: 150px">
												           <option value="" style="max-width: 35px;">Credit Card</option>
												      </select>
												      </td>
												      </tr>
											       </table>
											       </div>
											    </div>
											       <div class="col-sm-3">
														<button class="btn btn-embossed btn-primary" type="submit" id="paymentBtn">Pay Now</button>
												  </div>
										   </div>
									      </div>
									</c:if>
								</div>
							</div>
						</div>
						
						<!-- END ROW -->

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
<!-- END MAIN CONTENT -->
        <c:forEach items="${customerList}" var="custList">
		<form action="./custCreditCardPayment" id="creditCardPaymentId" method="POST">
			<input type="hidden" name="custId1" id="custId1"/>
			<input type="hidden" name="paymentMode" id="paymentModeId" />
			<input type="hidden" name="addAmt1" id="addAmt1"/>
			<input type="hidden" name="custName" id="custName"/>
			<input type="hidden" name="distId" id="distId"/>
			<input type="hidden" name="cafNo" id="cafNo">
		</form>
		</c:forEach>
</section>
<script type="text/javascript">
     jQuery(document).ready(function() {
    	 
    	 $('#pymntDiv').hide();
    	 
    	 $('#custDtls').on('click' ,'input[type=radio]', function() {
    			if ($(this).is(":checked")) {
    				 $('#pymntDiv').show();
	    		     $('#custDtls').find('tr').each(function() {
	    			 var row = $(this);
	    			 if (row.find('input[type="radio"]').is(':checked')) {
			    		var custId = $(this).find('.custId2').text();
						var custName = $(this).find('.custName2').text();
								 var cafNo = $(this).find('.cafNo1').text();
								 var distId = $(this).find('.distId1').text();
								 $('#custId1').val(custId);
								 $('#custName').val(custName);
								 $('#distId').val(distId);
								 $('#cafNo').val(cafNo);
								 
    			} 
    					
    			});
    				
    			}
    	 });
    	 
    	 $('#custDtlsSearch').on( 'click', function () {
    		 
	    	 if ($('#custMob').val() == '' && $('#custId').val() == '')  {  
				 
	             alert('Please Enter Mobile Number OR Customer ID'); 
	             return false;
	    	 }
	    	 
    	 });
    	 
	    	 
    	 $('#paymentBtn').on( 'click', function () {
    		 
			 var paymentMode = "card";
			 var amt = $('#addAmt').val();
			 if (amt == '') {  
				 
                 alert('Please enter Amount'); 
                 return false;
             } else {
            	 
            	 $('#addAmt1').val(amt);
             }
			 $('#paymentModeId').val(paymentMode);
			 $('#creditCardPaymentId').submit();
			 
    	 });
    	 
     });	 
    	 
</script>