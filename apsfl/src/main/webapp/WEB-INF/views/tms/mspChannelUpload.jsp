<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="./resources/js/create-product.js"></script>
<script src="./resources/js/olt-port-allocation.js"></script>
<script src="./resources/js/jquery.autocomplete.min.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">

<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>MSP Upload</h2>
					<c:if test="${not empty message}"> <center ><font color='green' size="3">${message}</font></center></c:if>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
					<!-- Create Cpe Order -->
			
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="panel-content">
								<form action="<c:url value="/mspChannelUpload" /> " name="mspUploadFormId" id="mspUploadFormId" method="POST" enctype="multipart/form-data">
								<div class="row">
									<div class="col-sm-12">
										<div class="panel">
											<div class="panel-header bg-light">
												<h3>MSP Upload</h3>
											</div>
									 	</div>
									 	<div class="panel-content">
									 	<div class="row">
										<input type="file" name="mspFile" required="required"> 
										</div>
										<div class="row"></div>
										<div class="row" style="margin-left: 400px;">
											<div class="col-sm-4"><button class="btn btn-embossed btn-primary" type="submit" id="mspUpload" >Submit</button></div>
											<div class="col-sm-8"><a class="btn btn-embossed btn-primary" href="./downloadExcelFileForChnlUpload"> Download Template</a></div>
										</div>
										</div>
								  	</div>
										
								</div>
								</form>
								<div class="row">
				<div class="col-sm-12">
						<table class="table table-alt" id="productsTable">
							<thead>
								<tr>
								    <th>S.No</th>
									<th>Upload Id</th>
									<th>File Name</th>
									<th>Upload Date</th>
									<th>Total Records</th>
									<th>Success Records</th>
									<th>Failure Records</th>
									<th>Download Error Report</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${not empty channelList}">
									<c:forEach items="${channelList}" var="channel"
										varStatus="rowNum">
										<tr>
											<td>${rowNum.count}</td>
											<td>${channel.uploadId}</td>
											<td>${channel.fileName}</td>
											<td>${channel.fileSize}</td>
											<td>${channel.noofRows}</td>
											<td>${channel.successRecords}</td>
											<td>${channel.noofRows-channel.successRecords}</td>
											<td align="center">
												<form class="mspChannelForm" name="mspChannelForm" action="#" method="post">
													<input type="hidden" name="uploadid" value="${channel.uploadId}" /> 
													<input type="hidden" name="fileName" value="${channel.fileName}" />
													<span class="mspChannel" onmouseover="this.style.cursor='pointer'" title="Download File"><img src="./resources/images/downloadicon.png"></span>
												</form>
											</td>
										</tr>
									</c:forEach>
								</c:if>
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

</section>

