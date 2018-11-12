<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="./resources/js/create-product.js"></script>

<script src="./resources/js/jquery.autocomplete.min.js"></script>
<script src="./resources/js/bootstrap-select.min.js"></script>
<script src="./resources/js/defaults-en_US.js"></script>
<link href="./resources/css/main.css" rel="stylesheet">
<link href="./resources/css/bootstrap-select.min.css" rel="stylesheet">


<script type="text/javascript">
	$(document).ready(function() {
		$('#searchButtons1').click(function() {
			var message = $('#messagesListId').val() == undefined ? "" : $('#messagesListId').val();
			var selectSubScribersCodes = [];
			var fpPosition = "0";
			var fpFontType = "0";
			var fpFontSize = "0";
			var fpFontColor = "0";
			var fpDuration = "0";
			var fpfingerPrintType = "0";
			var fpChannel = "0";
			var fpUserCanCloseMessage = "0";
			var fpBgColor = "0";
			var fpMessage = "0";
			var fpCount = 0;
			var fpStatus = "O";
			/**Added Subscriber Code**/
			var osdSubCode = "0";
			var osdFrequency = $('#osdFrequency').val() == undefined ? "" : $('#osdFrequency').val();
			if (message == "--Select--" || message == "" || osdFrequency == "") {
				alert("Please Select Message and Please Enter Frequency value");
			} else {
				fpPosition = $('#osdPosition').val();
				fpFontSize = $('#osdFontSize').val();
				fpFontType = $('#osdFontType').val();
				fpFontColor = $('#osdFontColor').val();
				fpBgColor = $('#osdBgColor').val();
				fpDuration = $('#osdDuration').val();
				fpMessage = message;
				fpUserCanCloseMessage = $('#osdUserCanCloseMessage').val();
				osdSubCode = $('#osdSubCode').val();
				/* osdFrequency = $('#osdFrequency').val(); */
				/* alert("Message:"+message); */
				if(osdSubCode!=""){
					var osdSubCodes = osdSubCode.split(',');
					for(var i = 0; i < osdSubCodes.length; i++) {
						fpCount++;
						//alert(osdSubCodes[i]);
						selectSubScribersCodes.push(osdSubCodes[i]);
					}
				}
				if(fpCount <= 0) {
					$('#individualCafFPActivationError').text("Please Upload atleast one Caf.");
				} else {
					//alert("calling");
					$('#individualCafFPActivationError').text('');
					var fingerPrintObject = {
		        		    "fpPosition": fpPosition,
		        		    "fpFontType": fpFontType,
		        		    "fpFontSize": fpFontSize,
		        		    "fpFontColor": fpFontColor,
		        		    "fpDuration": fpDuration,
		        		    "fpfingerPrintType" : fpfingerPrintType,
		        		    "fpChannel" : fpChannel,
		        		    "fpBgColor" : fpBgColor,
		        		    "fpMessage" : fpMessage,
		        		    "fpUserCanCloseMessage" : fpUserCanCloseMessage,
		        		    "fpStatus" : fpStatus,
		        		    "selectSubScribersCodes" : selectSubScribersCodes,
		        		    /* "fpFrequency" : osdFrequency, */
		        		};
		           	var fingerPrintJson = JSON.stringify(fingerPrintObject);
		           	/* alert(fingerPrintJson); */
		        	$.ajax({ 
		        		 async:false,
		        	     type: 'POST', 
		        	     url: 'getFingerPrintDetails', 
		        	     data: fingerPrintJson,
		        	     contentType: 'application/json',
						 success : function(response) {
							 if(response != ""){
								 $('#osdSubCode').val("");
								 $('#messagesListId').val("");
								 $('#osdUserCanCloseMessage').val("true");
								 $('#osdFrequency').val("");
							 }
							 alert(response);
							 /* window.location.reload(); */
						}
					});
				}
			}
			
		});
	});
	function isNumber(evt) {
        var iKeyCode = (evt.which) ? evt.which : evt.keyCode
        if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57)){
            alert("Please Enter numeric values");
            return false;
        }

        return true;
    } 
</script>
<body>
	<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Upload Subscribers</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>

				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<form action="uploadSubscribersFile"
									id="uploadSubscribersFormId" method="POST"
									enctype="multipart/form-data">

									<div style="margin: auto; width: 60%" id="statusMessageId">
										<span>${response}</span>
									</div>
									<div class="row">
										<div class="col-sm-12">
											<div class="panel">
												<div class="panel-header bg-light">
													<h3>Upload Subscribers</h3>
												</div>
												<div class="panel-content">
													<div class="row"></div>
													<div class="row"></div>
													<div class="row"></div>
													<div class="row">

														<div style="clear: both;"></div>
														<input type="file" name="uploadSubscribersFile"
															required="required">
													</div>
												</div>

												<div class="row"></div>
												<div class="row"></div>
												<br>
												<div class="row">
													<div class="col-sm-2">${status}</div>
												</div>
												<br>
												<div class="row">
													<div class="col-sm-2">
														<button class="btn btn-embossed btn-primary" type="submit"
															id="uploadSubscribers">Submit</button>
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- </div -->
									

									<%-- <div>
								${status}
								</div> --%>

									<%-- <c:if test="${not empty subscribersList}">
								<div class="row m-b-5">
									<div class="col-sm-12">
										<table class="table table-alt" id="uploadedSubscriberTable">
											<thead>
												<tr>
													<th>S.NO</th>
													<th>Caf No</th>
													<th>Customer Name</th>
													<th>Aadhar No</th>
													<th>Caf Date</th>
													<th>Location</th>
													<th>IPTV Srl No</th>
													<th>IPTV Mac Address</th>
													<th>Subscriber Code</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${subscribersList}" var="subscriber"
													varStatus="rowCount">
													
													<tr>
														<td>${rowCount.count}</td>
														<td>${subscriber.cafNo}</td>
														<td>${subscriber.custName}</td>
														<td>${subscriber.aadharNo}</td>
														<td>${subscriber.cafDate}</td>
														<td>${subscriber.cpePlace}</td>
														<td>${subscriber.stbSlno}</td>
														<td>${subscriber.stbMacaddr}</td>
														<td>${subscriber.nwsubsCode}</td>
													</tr>
													
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							 </c:if> --%>
									<!-- </div>
						</div>
					</div> -->
								</form>
								<c:if test="${not empty subscriberCode}">
									<!-- <div id = "osdDiv"> -->
									<div class="row">
										<div class="col-lg-12">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Position<span
														style="color: red;">*</span></label> <input type="text" name=""
														id="osdPosition" class="form-control form-white"
														value="400,800">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontType<span
														style="color: red;">*</span></label> <input type="text" name=""
														id="osdFontType" class="form-control form-white"
														value="Times Roman">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontSize<span
														style="color: red;">*</span></label> <input type="text" name=""
														id="osdFontSize" class="form-control form-white"
														value="36">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">FontColor<span
														style="color: red;">*</span></label> <input type="text" name=""
														id="osdFontColor" class="form-control form-white"
														value="yellow">
												</div>
											</div>
											<div class="clear"></div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">BgColor<span
														style="color: red;">*</span></label> <input type="text" name=""
														id="osdBgColor" class="form-control form-white"
														value="red">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Duration<span
														style="color: red;">*</span></label> <input type="text" name=""
														id="osdDuration" class="form-control form-white"
														value="20">
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">UserCanCloseMessage<span
														style="color: red;">*</span></label>
													<div class="option-group">
														<select name="" id="osdUserCanCloseMessage"
															class="form-control form-white">
															<option value="true">true</option>
															<option value="false">false</option>
														</select>
													</div>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Message<span
														style="color: red;">*</span></label>
													<div class="option-group">

														<select id='messagesListId' name="message"
															class="form-control form-white" required="required">

															<option value="" selected disabled hidden>--SELECT--</option>
															<!-- <option value="hi" >hi</option> -->
															<c:forEach var="msgs" items="${messages}" varStatus="theCount" >
																	<option value="${msgs}" title="${msgs}">message ${theCount.count}</option>
															</c:forEach>
															<%-- <c:choose>
																<c:when test="${not empty messages}">
																	<c:forEach var="msgs" items="${messages}">
																			<option value="${msgs}">${msgs}</option>
																	</c:forEach>
																</c:when>
															</c:choose> --%>
															<%-- <c:forEach var="messages" items="${messages}">
																	<c:choose>
																	<option value="${messages}">${messages}</option>
																		<c:when test="${not empty cafObject && cafObject.instDistrict == district.districtUid}">
																			<option value="${district.districtUid},${district.districtName}" selected>${district.districtName}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${district.districtUid},${district.districtName}">${district.districtName}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach> --%>
															<!-- <option value="ఏపీఎస్ యఫ్ యల్ వినియోగదారులకు గమనిక : మార్చి నెల నిమిత్తము మీ కేబుల్ బిల్ జమ కాలేదు, దయచేసి మీ లోకల్ కేబుల్ ఆపరేటర్ కి బిల్ చెల్లిచండి లేనియడల  మీ కనెక్షన్ నిలిపివేయబడుతుంది .">APSFL</option>
															<option value="చందాదారులకు గమనిక:మీ బిల్లు సకాలములొ చల్లించనందున ఏపి ఫైబర్ సేవలు నిలిపివేయబడును. వివరాలకొరకు వెంటనే మీ కెబుల్ ఆపరేటర్ను సంప్రదించగలరు.">Subscriber</option>
															<option value="Test P Message">Pending</option>
															<option value="Test B Message">Blocked</option> -->
														</select>
													</div>
												</div>

											</div>
											<div class="clear"></div>
										</div>
									</div>

									<!-- Added for Multiple Subscriber codes -->
									<div class="row">
										<div class="col-lg-12">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">SubscriberCode<span
														style="color: red;">*</span></label>
													<!-- <input type="text" name="" id="osdSubCode" class="form-control form-white"  placeholder= "Subscriber Codes"> -->
													<textarea name="" id="osdSubCode"
														class="form-control form-white"
														placeholder="Subscriber Codes">${subscriberCode}</textarea>
												</div>
											</div>
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label">Frequency<span
														style="color: red;">*</span></label>
													<input type="text" name="" id="osdFrequency" class="form-control form-white"  placeholder= "Frequency" onkeypress="javascript:return isNumber(event)" maxlength="2">
													<%-- <textarea name="" id="osdFrequency"
														class="form-control form-white"
														placeholder="Subscriber Codes">${subscriberCode}</textarea> --%>
												</div>
											</div>
											<!-- <div class="col-sm-3">
												<div class="form-group">
												<h1>హలోఁపపంచం</h1>
												</div>
											</div> -->
											<div class="clear"></div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="col-sm-3">
												<div class="form-group">
													<label class="control-label"></label>
												</div>
												<div>
													<input class="btn btn-embossed btn-primary" type="submit"
														value="submit" id="searchButtons1" />
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
