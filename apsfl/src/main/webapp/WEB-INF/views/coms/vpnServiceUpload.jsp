<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				<strong>VPN Service Upload</strong>
			</h2>
			<c:if test="${not empty message}"> <center ><font color='green' size="3">${message}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">VPN Service Upload</li>
				</ol>
			</div>
		</div>
		<a href ="./vpnUploadErrors"><button class="btn btn-embossed btn-primary" type="button">View VPN Upload Errors</button></a>
		<%-- <label id="serviceNameLable" style="color: #006600">${returnValue}</label> --%>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-header bg-light">
										<h3>VPN Service Information <label id="" style="text-align: center;color: red;"></label></h3>
									</div>
									<div class="panel-content">
										<form role="form" class="form-validation" name="VPNServiceUploadForm" id="VPNServiceUploadForm" action="<c:url value="/saveVPNServiceExcelUpload" />"
											method="post" enctype="multipart/form-data" method="post">
											<div class="row">
												<div class="col-sm-3">
													<div class="form-group">
														<label class="control-label">Select File<span style="color: red;">*</span></label>
														<input type="file" name="vpnUploadFile" id="vpnUploadFile" >
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<input type="submit" value="Submit" class="btn btn-embossed btn-primary"/>
													</div>
												</div>
												<div class="col-sm-3">
													<div class="form-group">
														<a href ="./downloadExcelSheet?from=VPNService"><button class="btn btn-embossed btn-primary" type="button">Download Template</button></a>
													</div>
												</div>
											</div>
										</form>
									</div>
									
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