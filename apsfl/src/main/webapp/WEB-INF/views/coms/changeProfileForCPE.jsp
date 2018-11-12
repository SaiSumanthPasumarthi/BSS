<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Change CPE Profile</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
			<form action="./changeProfileForCPE"  method="POST" >
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div style="margin: auto; width: 60%" id="statusMessageId">
								<span>${message}</span>
								</div>
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>Change CPE Profile</h3>
											</div>
											<div class="panel-content">
												<div class="row">
													
													<div class="col-sm-4" id="cpe-Model-Div-Id1">
														<div class="form-group">
															<label class="control-label">CPE Serial No : <span style="color: red;">*</span></label>
															<input type="text" id="cpe-Model-Text-Info-button-Id"  class="form-control form-white" placeholder="Enter CPE Serial No" required="required" name = "cpeSrlNo">
														</div>
													</div>
													<div class="col-sm-4" >
													<label class="control-label"> Current CPE Model :</label>
													<input type="text" class="form-control form-white"  id="cpe-Model-Div-Info-Id" readonly >
													</div>
													<div class="col-sm-4">
													<label class="control-label"> </label><br><br>
													<button class="btn btn-embossed btn-primary" type="button" id = "cpe-Model-Div-Info-button-Id" >Get Current CPE Model</button>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4">
														<div class="form-group">
															<label class="control-label">CPE Type <span style="color: red;">*</span></label>
															<div>
<%-- 																<select name="cpeType" id="cpe-Type-Update-Id" class="form-control form-white" onchange="cpeProfileChange()" required="required">
																	<option value="">--Select--</option>
																	<c:forEach var="cpeTypeVar" items="${cpeMasterList}">
																		<option value="">${cpeTypeVar.cpeTypeLov}</option>
																	</c:forEach>
																</select> --%>
																
																<input type="text" class="form-control form-white" name="cpeType" id="cpe-Type-Update-Id" required="required" readonly >
																
															</div>
														</div>
													</div>
													<div class="col-sm-4" id="cpe-Model-Div-Id"></div>
												</div>
												<div class="row"></div>
												<div class="row"></div>
												<div class="row">
												</div>
												<div class="row"></div>
												<div class="row"></div>
												<br>
												<br>
												<div class="row" style="margin-left: 800px;">
												<div class="col-sm-6"><button class="btn btn-embossed btn-primary" type="submit" id="cpe-submit-Update-id" >Submit</button></div>
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
		 </div>
		</div>

</section>

