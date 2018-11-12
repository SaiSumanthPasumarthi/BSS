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


<!-- Created By SaiSumanth --> 
<body>
<section>
	<!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Tenant Wallet Transaction</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./home.do">Home</a></li>
						<li class="active">Tenant Wallet Transaction</li>
					</ol>
				</div>


			</div>
			<div class="row main-row">
				<div class="col-lg-12">
					<div class="panel main-panel">
						<div class="panel-content main-panel-content">

							<div id="id_container" class="error"
								style="color: red; width: 100%; align: center;">
								<ol id="id_ol"></ol>
							</div>
							<div id="id_container1" class="success"
								style="color: green; width: 100%; align: center;">
								<ol id="id_ol"></ol>
							</div>


							<div class="row">
								<div class="col-sm-12">
								<form action="#/tenantWalletTrans" method="GET"
										 commandName="tenantWalletTransDTO"> 
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Tenant Wallet Transaction</h3>
											</div>
											<div class="panel-content">
					
												<!-- <div class="row">
                                                  	<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Year</label>
															<input type="text" name="year" 
																maxlength="4" placeholder="Enter Year(yyyy)" />
														</div>
													</div>

													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Month</label>
															<input type="text" name="month" 
																maxlength="2" placeholder="Enter Month(mm)" />
														</div>
													</div>
													 -->
													 <div class="col-sm-3">
										<div class="form-group">
											<label class="control-label">Month:<span
												style="color: red;">*</span></label>
										</div>
										<div class="form-group">

											<select id='monthsListId' name="month"
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
											<select id='yearListId' name="year"
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
											</div>


												<div class="row">


													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">&nbsp;</label>
															<button class="btn btn-embossed btn-success" id="saveID1"
																value="Save">Submit</button>
														</div>
													</div>
												</div>
												</div>
												</div>
												
                                        </form> 
                                         <c:if test="${not empty year}">
                                           <c:choose>
												<c:when test="${not empty tenantWalletTransDTOList}">
												
													
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
														<div class="row">
															<table class="table table-alt" >
																<thead>
																	<tr>
																		<th>LMO Code</th>
																		<th>NetWork Name</th>
																		<th>POC Name </th>
																		<th>POC Mobile no.</th>
																		<th>District </th>
																		<th>Mandal</th>
																		<th>Village</th>
																		<th>Transaction Date</th>
																		<th>Transaction RefNo.</th>
																		<th>Transaction Amount</th>
																	</tr>
																</thead>
																<tbody>
																<c:choose>
																<c:when test="${not empty tenantWalletTransDTOList}">
																	<c:forEach items="${tenantWalletTransDTOList}" var="list">
																		<tr>
																			
																		 
																		    <td>${list.tenantcode}</td>
																			<td>${list.tenantname}</td>
																			<td>${list.regoff_pocname}</td>
																			<td>${list.regoff_pocmob1}</td>
																			<td>${list.districtname}</td>
																			<td>${list.mandalname}</td>
																			<td>${list.villagename}</td>
																			<td>${list.trandate}</td>
																			<td>${list.tranrefno}</td>
															    			<td>${list.tranamt}</td>
																								 
																		 </tr>
																	</c:forEach>
																	</c:when>
																	
                           
															</c:choose>
																	</tbody>
															</table>
														 <form action="./downloadtenantWalletTrans"  method="GET">
														<input type="hidden" name="download" value="true">
														<input type="hidden" name="year" value="${year}">
														<input type="hidden" name="month" value="${month}">
														<button class="btn btn-embossed btn-primary" type="submit">Download</button>
													</form>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								
								</c:when>
								<c:otherwise>
                                     <c:if test="${not empty message}">
														<center>
															<font color='red' size="3">${message}</font>
														</center>
													</c:if>
                            </c:otherwise>
								
											</c:choose>
		</c:if>
		
											</div>
										</div>
								
								</div>

							 </div>

							
						</div>
						
					</div>
				

				</div>
				
			</div>
		
			<div class="clear"></div>
		
	<!-- END MAIN CONTENT -->
	<!-- END FOOTER -->
	</section>
	</body>