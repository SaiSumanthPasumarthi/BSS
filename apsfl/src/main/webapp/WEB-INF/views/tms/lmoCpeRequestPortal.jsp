<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="./resources/js/mspLmoCpeRequest.js"></script>

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>MSO/LMO CPE Request</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				<form action="./lmoCpeRequestSavePortal" method="POST" id="lmoMsoCpeFormId" >
				</form>
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>MSO/LMO CPE Request</h3>
											</div>
											<div class="panel-content">
											<div class="row">
											<div class="col-sm-6">
											<label>MSO Name</label>
											<input class="form-control form-white" type="text" disabled="disabled" value="${tenantname}">
											</div>
											<div class="col-sm-6"><b><label>${returnVal}</label></b></div>
											</div><br>
											<input type="hidden" id="sizeOfListId"  value="${fn:length(lmosList)}">
												<div class="row">
													<table class="table table-alt" id = "mspLmoTableId" >
													<thead>
													<tr>
													 <th hidden="hidden"></th>
													 <th hidden="hidden"></th>
													 <th> LMO Name</th>
													 <th>Total UpFront </th>
													 <th>UpFront CPE (Rs.4000)</th>
													 <th> Total CPE-36</th>
													 <th>CPE-36 Months (Rs.1700)</th>
													  <th>Total CPE-48 </th>
													 <th>CPE-48 Months (Rs.500) 
													 <img class="imgp" id="addNewLmoId" onMouseOver="this.style.cursor='pointer'" src="./resources/images/apf_add.png" align="right">
													 </th>
													</tr>
													</thead>
													<tbody id="mspLmoTableBodyId">
													<c:forEach items="${lmosList}" var="lmo" varStatus="loop">
													<tr>
													<td hidden="hidden"><input type="text" value="${lmo[0]}" class="msoLmoId" name = "lmoCpeRequest[${loop.index}].msoLmoId" maxlength="250"></td>
													<td hidden="hidden"><input type="text" value="${lmo[1]}"   name = "lmoCpeRequest[${loop.index}].lmoName"></td>
													<td><input  type="text" value="${lmo[1]}"  class="form-control form-white lmoName"  style="width : 100%" disabled="disabled" ></td>
													<td><input  type="text" value="${lmo[2]}"  class="form-control form-white noEmi"  style="width : 100%" disabled="disabled" ></td>
													<td><input  type="number" value="0" class="form-control form-white numbersOnly_sai noEmi1" required="required" style="width : 100%"></td>
													<td><input  type="text" value="${lmo[3]}"  class="form-control form-white 36Emi"  style="width : 100%" disabled="disabled" ></td>
													<td><input  type="number" value="0" class="form-control form-white numbersOnly_sai 36Emi1" required="required" style="width : 100%"></td>
													<td><input  type="text" value="${lmo[4]}"  class="form-control form-white 48Emi"  style="width : 100%" disabled="disabled" ></td>
													<td><input  type="number" value="0" class="form-control form-white numbersOnly_sai 48Emi1" required="required"  style="width : 85%"></td>
													</tr>
													</c:forEach>
													</tbody>
													</table>
												</div>
												<div class="row">
												<button class="btn btn-success" type="button" id="lmoMsoButtonId">Submit</button>
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