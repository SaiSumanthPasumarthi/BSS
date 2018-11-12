
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="./resources/js/termCell.js"></script>
<!-- custom file -->
<%-- @Srinivas V @since March 15 2017  --%>
<style>
div.dataTables_scrollBody thead th,
div.dataTables_scrollBody thead td {
line-height: 0;
opacity:0.0;
width: 0px;
height:0px;
}
</style>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Welcome to APSFL</h2>

					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
							<li>TERM Cell</li>
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
												<div class="row">
													<div class="panel panel-default ">
														 <div class="panel-heading"> 
																<h4>Total No. Of Subscribers:  <strong>${toalCount}</strong> As on  <strong>${cDate}.</strong> </h4>
														 </div>
														<div class=" panel-body ">
															<div class="panel-group">
																<div class="panel panel-default">
																	<div class="panel-body">
																		<form name="termCellReportForm" id="termCellReportForm"
																			action="termCellReportDownload">
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="radio-inline" id='detailSearchlbl'> 
																							<input type="radio" name="detailSearch" id="detailSearch" value="districwise">Districts:
																							</label>
																						</div>
																						<div class="form-group">
																							<select id='districtID' name="districtID" class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																								<c:forEach var="dList" items="${districtList}">
																									<option value="${dList[0]}">
																										<c:out value="${dList[1]}" />
																									</option>
																								</c:forEach>
																							</select>
																						</div>
																					</div>
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">STD Codes:</label>
																						</div>
																						<div class="form-group">
																							<select id='stdCodesID' name="stdCodesID" class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					
																					<div class="col-sm-3">
																						<div class="form-group">
																							<label class="control-label">Mandals:</label>
																						</div>
																						<div class="form-group">
																							<select id='mandalsID' name="mandalsID" class="form-control form-white">
																								<option selected value=''>--SELECT--</option>
																							</select>
																						</div>
																					</div>
																					
																					<div class="col-sm-3">
																						<div class="form-group">
																							 
																								<label class="radio-inline" id='directSearchlbl'>
																								 <input type="radio" name="directSearch" id='directSearch' value="districwise">STD-Phone No:
																							    </label>
																						</div>
																						<div class="form-group">
																							<input type="text" id='landLineNum' name="landLineNum" class="form-control form-white number" placeholder="Enter STD-Phone No " maxlength="11">
																						</div>
																					</div>
																				</div>
																			</div>
																			<div class="row">
																				<div class="col-lg-12">
																					<div class="form-group">
																						<label class="control-label"> </label>
																					</div>
																					<div class="form-group">
																						<button type="button" id="seachtermCellId" class="btn btn-embossed btn-primary pull-right">
																							<i class="fa fa-search"></i>Search
																						</button>
																					</div>
																				</div>
																			</div>
																		</form>

																	</div>
																</div>
																<div class="panel panel-default">
																	<div class="panel-body">
																		<div class="col-sm-12">
																			<table class="table table-alt no-wrap"	id='termCellTableId'>
																				<thead>
																					<tr>
																						<th>Account No</th>
																						<th>Customer Name</th>
																						<th>Aadhar Number</th>
																						<th>Customer Type</th>
																						<th>Land Line Number</th>
																						<th>Date Of Activation</th>
																						<th>Active Plan</th>
																						<th>Address</th>
																					</tr>
																				</thead>
																				<tbody>
																				</tbody>
																			</table>
																		</div>
																	</div>
																</div>
																<button type="button" id="downLoadTermCellReportId"	class="btn btn-embossed btn-primary pull-left">
																	<i class="fa fa-download" aria-hidden="true"></i>Download
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
