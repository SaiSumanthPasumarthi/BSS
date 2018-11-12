<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="./resources/js/portSplitter.js"></script>

<body>
	
	<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong> Port Splitting </strong>
			</h2>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
				</ol>
			</div>
		</div>
	<form action="./savePortSplitter"  method="POST">
		<div id="mainDiv" class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
									<div class="row">
										<div class="col-sm-12">
											<div class="panel">
											<span class="col-sm-offset-5">${meassage}</span>
												<div class="panel-header bg-light">
													<h3>Port Splitting </h3>
												</div>
												<div class="panel-content">
													<div class="row">
													<div class="col-sm-3 form-group">
													<label><b>Olt Serial Number</b></label>
													<select id = "olt_SrlNo_Select_Id" required="required" name ="oltSrno" class="form-control form-white">
													<option value="">--Select--</option>
													<c:forEach items="${oltSerNoList}" var="oltSrno">
													<option value="${oltSrno[0]}">${oltSrno[1]}</option>
													</c:forEach>
													</select>
													</div>
													<div class="col-sm-3 form-group" id ="olt_Port_div_Id"></div>
													<div class="col-sm-3 form-group" id='olt_L1_size_div_Id'></div>
													<div class="col-sm-3 form-group" id='olt_L1_Slot_details_div_Id'></div>
													</div>
													<div class="row">
													<div class="col-sm-3 form-group" id='olt_L1_Ids_div_Id'></div>
													<div class="col-sm-3 form-group" id='olt_L2_size_div_Id'></div>
													<div class="col-sm-3 form-group" id='olt_L3_size_div_Id'></div>
													<div class="col-sm-3"><div class="col-sm-* form-group" id='olt_L3_Slot_details_div_Id'></div><button class="btn btn-embossed btn-primary" type="submit" id = "submit_Button_Id_Port_Split">Submit</button></div>
													</div>
												</div>
											</div>
										</div>
								</div>
								<!-- END ROW -->
								
					</div>
					<!-- END MAIN PANEL CONTENT -->
				</div>
				<!-- END MAIN PANEL -->
			</div>
			<!-- HERE COMES YOUR CONTENT -->
		</div>
		
	</form>
		<!-- END MAIN ROW -->
		<div class="clear"></div>
		
		
	</div>
	<!-- END PAGE CONTENT -->
</div>

