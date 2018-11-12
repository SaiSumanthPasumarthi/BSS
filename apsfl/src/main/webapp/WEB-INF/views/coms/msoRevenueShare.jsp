<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 <script type="text/javascript">
 $(document).ready(function() {
 
 $('#msoRevenueShareDataTable').dataTable();
 
 var hiddenYear = $('#hiddenSelectYear').val();
	var min_year = new Date().getFullYear();
	min_year = 2016;
	max_year = min_year + 15;
	var div_data = "";
	for (var i = min_year; i<=max_year; i++){
		if(i == hiddenYear){
			 div_data = div_data + "<option selected value=" + i+ ">" + i + "</option>";
		}else{
			 div_data = div_data + "<option  value=" + i+ ">" + i + "</option>";
		}
	}
	$(div_data).appendTo('#yearListId');	
	$('#searchButtons1').click(function() {
		var month = $('#monthsListId').val() == undefined ? "" : $('#monthsListId').val();
		var year = $('#yearListId').val() == undefined ? "" : $('#yearListId').val(); 
       if (month ==  "--Select--" || year == "--Select--" || month == " " || year == " ") {
		 alert("Please Select month and year .");
			
		} 
	});


	 var totals=[0,0,0,0,0];
		var $dataRows=$("#msoRevenueShareDataTable tr:not('.totalColumn, .titleRow')");
			var value = "";
		    $dataRows.each(function() {
		        $(this).find('.rowData').each(function(i) {
		        	if($(this).html().trim() == "") {
		        		value = "0";
		        	} else {
		        		value = $(this).html().trim();
		        	}
		        	
		            totals[i] += parseFloat(value);
		        });
		    });
		    $("#msoRevenueShareDataTable td.totalCol").each(function(i) {
		    	if(totals[i] > 0) {
		    		var num = totals[i];
		    	} else {
		    		var num = totals[i];
		    	}
		        $(this).html(num);
		    });
		    
 });
 
 
	
 </script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2> MonthWise Revenue Share Details </h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>Display of MsoShare</li>
						</ol>
					</div>
				</div>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row" align="center">
									<div class="col-sm-12">
										<div class="row main-row">
											<div class="col-lg-12">
												<div class="panel main-panel">
													<div class="panel-content main-panel-content">
													
					<!-- 	year month selection	form start-->
											<form name="LoginTenantRevenueShare"  id="mLoginTenantRevenueShare" action="./LoginTenantRevenueShare"  method="GET">
		
							                        <div class="col-sm-12" id="submitdiv">
															<div class=" panel-body ">
															<div class="panel-group">
																<div class="panel panel-default">
																	<div class="panel-body">
													
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Month:<span
																								style="color: red;">*</span></label>
																						</div>
																						<div class="form-group">

																							<select id='monthsListId' name="month" class="form-control form-white" required="required">
																								
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
																					<input type="hidden" id = "hiddenSelectYear" value="${year}">
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
																							<label class="control-label"></label>
																						</div>
																						<div >
																							<input class="btn btn-embossed btn-primary" type="submit" value ="submit" id="searchButtons1"/>
																						</div>
																					</div>
																				</div>
																			</div>
																		
																	</div>
																</div>
															</div>
														</div>
							
							         </div>
							
							        </form>	
							        
							        							        <div class="row">
										<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">MSO Code :</label><span style="font-weight:bold;color: #c13b34;"> ${tenantCode}</span>
												</div>
											</div> 
																					<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Month:</label><span style="font-weight:bold;color: #c13b34;"> ${month}</span>
												
											</div>
											</div>
																					<div class="col-sm-3">
											<div class="form-group">
												<label style="font-weight:bold; color: #c13b34;"class="control-label">Year :</label><span style="font-weight:bold;color: #c13b34;"> ${year}</span>
												
											</div>
											</div>

									</div>													
																							
									<div class="row">
									<input type="hidden" id="msoRevShare" value=${revnList } />
										<table class="table table-alt" id="msoRevenueShareDataTable">
										
											<thead>
												<tr>
												    <th>SlNo.</th>
												    <th>LMO Code</th>
												    <th>APSFL Share</th>
													<th>MSO Share</th>
													<th>LMO Share</th>
													<th>Total Bill</th>
													<th>Caf Count</th>
													
												</tr>
											</thead>
											
											<tbody>
													<c:forEach items="${revnList}"
														var="msoRevsh" varStatus="theCount">
															<tr>
																<td align="center">${theCount.count}</td>
																<td>${msoRevsh.lmoCode}</td>
																<td class="rowData" >${msoRevsh.apsflShare}</td>
																<td class="rowData" >${msoRevsh.msoShare}</td>
																<td class="rowData" >${msoRevsh.lmoShare}</td>
																<td class="rowData" >${msoRevsh.totalBill}</td>
																<td class="rowData" >${msoRevsh.cafcount}</td>
																
															</tr>
																</c:forEach>
																
																
																
																<tr class="totalColumn">
																				<td style="font-weight: bold" class="aCenter">Total</td>
																				<td style="font-weight: bold"></td>
																				<td class="totalCol" style="font-weight: bold"></td>
																				<td class="totalCol" style="font-weight: bold"></td>
																				<td class="totalCol" style="font-weight: bold"></td>
																				<td class="totalCol" style="font-weight: bold"></td>
																				<td class="totalCol" style="font-weight: bold"></td>
																				
																	</tr>
															</tbody>
										          </table>
										          
										          <div class="" style="">
												<button style="" type="button"
													class="btn btn-embossed btn-primary">
													<a style="color: white;" href="./lmosUnderMsoRevenueShareDownload">Download</a>
												</button>
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
