<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<section>
	<!-- BEGIN MAIN CONTENT -->
	<div class="main-content">
		<!-- BEGIN PAGE CONTENT -->
		<div class="page-content page-width">
			<div class="page-title">
				<h2>Billing Engine</h2>
				<div class="breadcrumb-wrapper">
					<ol class="breadcrumb">
						<li><a href="./home.do">Home</a></li>
						<li class="active">Billing Engine</li>
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
									<form:form action="#/billingenginesubmit" method="POST"
										id="beengineID" commandName="billingEngineDTO">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Billing Engine</h3>
											</div>
											<div class="panel-content">
												<div class="row">
												
												<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Submit Type</label>&nbsp;
															<form:select id="submitType" style="width: 200px;"
																path="submitType" name="submitType"
																class="form-control form-white select">
																<option value="-1">--Select Submit Type</option>
																<option value="GENERATE_INVOICE">Generate Invoice</option>
																<option value="GENERATE_PDF">Generate PDF</option>
																<option value="SEND_SMS_EMAIL">Send SMS And
																	Email</option>

															</form:select>
														</div>
													</div>
												
													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Customer Type</label>&nbsp;
															<form:select path="customerType" id="customerType"
																style="width: 200px;" name="custType"
																class="form-control form-white select">
																<option value="-1">--Select Customer Type</option>
																<option value="INDIVIDUAL">Individual</option>
																<option value="ENTERPRISE_GOVT">Enterprise Govt</option>
																<option value="ENTERPRISE_PRIVATE">Entreprise
																	Private</option>
															</form:select>
														</div>
													</div>



													<div class="col-sm-3" id="distHidID">
														<div class="form-group">
															<label class="control-label">District</label>&nbsp;
															<form:select id="districtID" style="width: 200px;"
																name="district" path="district"
																class="form-control form-white select">
																<option value="-1">--Select District--</option>
																<c:forEach items="${districtsList}" var="dist">
																	<option value="${dist.districtUid}">${dist.districtName}</option>
																</c:forEach>
															</form:select>
														</div>
													</div>


												</div>
												<div class="row">

													

													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Year</label>
															<form:input type="text" name="year" id="yearID"
																path="year" class="form-control form-white number"
																maxlength="6" placeholder="Enter Year(yyyy)" />
														</div>
													</div>

													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">Month</label>
															<form:input type="text" name="month" id="monthID"
																path="month" class="form-control form-white number"
																maxlength="2" placeholder="Enter Month(mm)" />
														</div>
													</div>


												</div>


												<div class="row">


													<div class="col-sm-3">
														<div class="form-group">
															<label class="control-label">&nbsp;</label>
															<button class="btn btn-embossed btn-success" id="saveID"
																value="Save">Submit</button>
														</div>
													</div>
												</div>



											</div>
										</div>
									</form:form>
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
	<!-- END MAIN CONTENT -->
	<!-- END FOOTER -->
</section>
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a>

<script>
	$(document)
			.ready(
					function() {

						$("#distHidID").hide();
						$("#id_container").hide();
						$("#id_container1").hide();
						
						$("#submitType").val("-1");
						$("#customerType").val("-1");
						$("#districtID").val("-1");
						$("#monthID").val("");
						$("#yearID").val("");

						var dbsuccessMsg = "${successMsg}";
						var dberrorMsg = "${errorMsg}";

						if (dbsuccessMsg != "") {
							$("#id_container1").addClass('dberrorMsg');
							$('#id_container1')
									.find('ol')
									.append(
											'<li style="list-style: none;">${successMsg}</li>');
							$("#id_container1").show();
						}

						if (dberrorMsg != "") {
							$("#id_container").addClass('dberrorMsg');
							$('#id_container')
									.find('ol')
									.append(
											'<li style="list-style: none;">${errorMsg}</li>');
							$("#id_container").show();
						}

						$("#customerType").change(function() {

							var ctype = $("#customerType").val();
							if (ctype === "INDIVIDUAL") {
								$("#distHidID").show();
							} else {
								$("#distHidID").hide();
							}

						});

	$("#saveID").click(function() {
							
			var date = new Date();
			var yyyy = date.getFullYear();
			var mm = date.getMonth()+1;
							
			$("#id_container").hide();
			$("#id_container1").hide();
			$('#id_container').find('ol').html('');
							
			var flag = true;

			//alert("SAVE CLICK "+flag);
							
							if (($("#submitType").val() == "-1")) {
								$('#id_container').find('ol').append('<li style="list-style: none;">Select Submit Type</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							
							else if($("#submitType").val() !== "-1" && $("#submitType").val() !== "SEND_SMS_EMAIL"){
							
							if ($("#customerType").val() == "-1") {
								$('#id_container').find('ol').append('<li style="list-style: none;">Select Customer Type</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							if (($("#customerType").val() !== "-1" && $("#customerType").val() === "INDIVIDUAL") && $("#districtID").val() === "-1") {
								
								$('#id_container').find('ol').append('<li style="list-style: none;">Select District</li>');
								$("#id_container").show();
								flag =false;
							}
							if ($("#submitType").val() == "-1") {
								$('#id_container').find('ol').append('<li style="list-style: none;">Select Submit Type</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							if ( $("#yearID").val() == "") {
								$('#id_container').find('ol').append('<li style="list-style: none;">Enter Year</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							
							if ( $("#monthID").val()=="") {
								$('#id_container').find('ol').append('<li style="list-style: none;">Enter Month</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							if ($("#yearID").val() !== "" && $("#yearID").val() > yyyy) {
								$('#id_container').find('ol').append('<li style="list-style: none;">Year not grater than Current Year</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							 if ($("#monthID").val() !== "" && $("#monthID").val() > 12) {
								$('#id_container').find('ol').append('<li style="list-style: none;">Invalid Month</li>');
								$("#id_container").show();
								
								flag =false;
							}
							
							
							  if ($("#monthID").val() !== ""   && ($("#yearID").val() == yyyy && $("#monthID").val() >= mm)) {
								$('#id_container').find('ol').append('<li style="list-style: none;">Month shoud be previous Month</li>');
								$("#id_container").show();
								
								flag =false;
							}  
							
							 
							
						}
							else  if($("#submitType").val() !== "-1" && $("#submitType").val() === "SEND_SMS_EMAIL"){
								
							  if ( $("#yearID").val() == "") {
									$('#id_container').find('ol').append('<li style="list-style: none;">Enter Year</li>');
									$("#id_container").show();
									
									flag =false;
								}
								
								
								if ( $("#monthID").val()=="") {
									$('#id_container').find('ol').append('<li style="list-style: none;">Enter Month</li>');
									$("#id_container").show();
									
									flag =false;
								}
								
								if ($("#yearID").val() !== "" && $("#yearID").val() > yyyy) {
									$('#id_container').find('ol').append('<li style="list-style: none;">Year not grater than Current Year</li>');
									$("#id_container").show();
									
									flag =false;
								}
								
								if ($("#monthID").val() !== "" &&  $("#monthID").val() > 12) {
									$('#id_container').find('ol').append('<li style="list-style: none;">Invalid Month</li>');
									$("#id_container").show();
									
									flag =false;
								}
								
								
								  if ($("#monthID").val() !== ""   && ($("#yearID").val() == yyyy && $("#monthID").val() >= mm)) {
									$('#id_container').find('ol').append('<li style="list-style: none;">Month shoud be previous Month</li>');
									$("#id_container").show();
									
									flag =false;
								}  
								
							} 
							
						//	alert("FLAG "+flag);
							if(flag){
								
							$("#beengineID").attr("action", "billingenginesubmit");
							$("#beengineID").submit();
							}else{
							return flag;
							}
						});

					});
</script>