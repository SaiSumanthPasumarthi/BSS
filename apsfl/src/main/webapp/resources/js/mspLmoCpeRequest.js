$(document).ready(function showCalendar(event) {
	var eventId = event.id;
	$('#' + eventId).datepicker({
		dateFormat : 'yy-mm-dd',
		minDate : new Date(2016, 01, 01),
		numberOfMonths : 1,
		changeMonth : true,
		changeYear : true,
		onSelect : function(selected) {
			var dt = new Date(selected);
			$('#' + eventId).val(selected);
		}
	});
});
$(document)
		.ready(
				function() {

					/*$( '#managerdistWiseCafReport').submit();*/
					var query = window.location.search.substring(1);
					var vars = query.split("&");
					if(vars.length>1){
						var pair = vars[0].split("=");
						if (pair[0] == "alist") {
							while(pair[1].includes('%')) pair[1]=pair[1].replace('%',' ,');
							pair[1]=pair[1].slice(0,-1);
							if(pair[1]!=""){
								document.getElementById('alist').style.color="green";
								document.getElementById('alist').innerHTML="Successfully Approved for ["+pair[1]+"]";
							}
						}
						var pair1 = vars[1].split("=");
						if (pair1[0] == "rlist") {
							while(pair1[1].includes('%')) pair1[1]=pair1[1].replace('%',' ,');
							pair[1]=pair[1].slice(0,-1);
							if(pair1[1]!=""){
								document.getElementById('rlist').style.color="red";
								document.getElementById('rlist').innerHTML="Successfully Rejected for ["+pair1[1].replace('%',' ,')+"]";
							}
						}
					
					}
					var reqval=$('#selectoption').val();
					$('#cafslist').hide('fast');
					$('#ticketlist').hide('fast');
					$("#"+reqval+"").show('fast');
					/*$('#ticketscount').hide('fast');
					$('#cafscount').hide('fast');*/
					$('#cafstilldate').dataTable();
					$('pendingttlist').dataTable();
					
					$('#cafs').click(function() {
						/*$('#ticketscount').hide('fast');*/
						$('#ticketlist').hide('fast');
						/*$('#cafscount').show('fast');*/
						$('#cafslist').show('fast');
					});
					$('#tickets').click(function() {
						/*$('#cafscount').hide('fast');*/
						$('#cafslist').hide('fast');
						/*$('#ticketscount').show('fase');*/
						$('#ticketlist').show('fast');
					});
					$('#cafs1').click(function() {
						/*$('#ticketscount').hide('fast');*/
						$('#ticketlist').hide('fast');
						/*$('#cafscount').show('fast');*/
						$('#cafslist').show('fast');
					});
					
					$('#tickets1').click(function() {
						/*$('#cafscount').hide('fast');*/
						$('#cafslist').hide('fast');
						/*$('#ticketscount').show('fase');*/
						$('#ticketlist').show('fast');
					});
					$("#effectivefrom").datepicker(
							{
						dateFormat: 'yy-mm-dd',
						minDate: new Date(2016,01, 01),
					    numberOfMonths: 1,
					    changeMonth: true,
					    changeYear: true,
					    onSelect: function (selected) {
					        var dt = new Date(selected);
					        dt.setDate(dt.getDate() + 1);
									$("#effectiveto").datepicker("option",
											"minDate", dt);
					    }
					});
					$("#effectiveto").datepicker(
							{
						
						dateFormat: 'yy-mm-dd',
						maxDate: '+1Y',
					    numberOfMonths: 1,
					    changeMonth: true,
					    changeYear: true,
					    onSelect: function (selected) {
					    	/*$('#managerdistWiseCafReport').submit();*/
					        var dt = new Date(selected);
					        dt.setDate(dt.getDate() - 1);
									$("#effectivefrom").datepicker("option",
											"maxDate", dt);
					      
					    }
					});
					
					 /*$("#effectivefrom").datepicker({
					    	dateFormat: 'yy-mm-dd',
					    	numberOfMonths: 1,
					        changeMonth: true,
					        changeYear: true,
					        onSelect: function (selected) {
					            var dt = new Date(selected);
					            dt.setDate(dt.getDate() - 1);
					            alert(dt);
					           ;
					        }
					    });
					 
					 
					  $("#effectiveto").datepicker({
					    	dateFormat: 'yy-mm-dd',
					    	numberOfMonths: 1,
					        changeMonth: true,
					        changeYear: true,
					        onSelect: function (selected) {
					            var dt = new Date(selected);
					            dt.setDate(dt.getDate() - 1);
					           ;
					        }
					    });
					    */

					$('#lmoMspTabId').dataTable();
					$('#approvalslist').dataTable();
					$('#pendingttlist').dataTable();
					$('#subWise8Table').dataTable();
					$('#subWise16Table').dataTable();

					$('#msoDetailsWithAlignedLMOsTable').dataTable({
						"scrollX" : true,
						"drawCallback" : function(settings, json) {
							$('.dataTables_scrollBody thead tr').css({
								visibility : 'collapse'
							});
						}
					});

					$('#lmoStockCountTable').dataTable();
					$('#districtWiseCafTable').dataTable();

					$('#msoWiseCpeStockCountTable').dataTable();

					$('#tenantWiseCpeStockTable').dataTable();

					$('#mspLmoTableId').dataTable({
						/* "paging": false, */
						"pageLength" : 100,
						"ordering" : false,
						"info" : false,
					/* "searching" : false */
					});

					$(document)
							.on(
									"click",
									"#addNewLmoId",
									function(event) {
										var x = $('#sizeOfListId').val();
										var html = '<tr> <td hidden="hidden">'
												+ '<input type="text"  name = "lmoCpeRequest['
												+ x
												+ '].msoLmoId" ></td>'
												+ '<td><input  type="text"  class="form-control form-white lmoName" required="required" style="width : 100%" maxlength="250" ></td>'
												+ '<td><input  type="text" value="0"  class="form-control form-white noEmi"  style="width : 100%" disabled="disabled" ></td>'
												+ '<td><input  type="number" value="0" class="form-control form-white noEmi1" required="required" style="width : 100%" >'
												+ '<td><input  type="text" value="0"  class="form-control form-white 36Emi"  style="width : 100%" disabled="disabled" ></td>'
												+ '<td><input  type="number" value="0" class="form-control form-white 36Emi1" required="required"  style="width : 100%">'
												+ '<td><input  type="text" value="0"  class="form-control form-white 48Emi"  style="width : 100%" disabled="disabled" ></td>'
												+ '<td><input  type="number" value="0" class="form-control form-white 48Emi1" required="required" style="width : 75%" >'
												+ '<a href="#" class="btn btn-sm btn-danger deleteRow" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a></td>'
												+ '</tr>';
										$('#mspLmoTableBodyId').append(html);
										$('#sizeOfListId').val(parseInt(x) + 1);
									});

					$(document).on('click', '.deleteRow', function(event) {
						$(this).closest('tr').remove();
					});

					$(".numbersOnly_sai")
							.numeric({
								negative : false
							})
							.keyup(
									function(e) {
										var yourInput = $(this).val();
										re = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi;
										var isSplChar = re.test(yourInput);
										if (isSplChar) {
											yourInput = yourInput
													.replace(
															/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi,
															'');
										}

										$(this).val(yourInput); // update input
																// with new
																// value
									});

					$(document)
							.on(
									'click',
									'#lmoMsoButtonId',
									function(event) {
										var flag = false;
										var i = 0;
										$('#mspLmoTableId > tbody > tr')
												.each(
														function() {
															flag = true;
															var msoLmoId = $(
																	this)
																	.find(
																			'.msoLmoId')
																	.val();
															var lmoName = $(
																	this).find(
																	'.lmoName')
																	.val();
															var noEmi = parseInt($(
																	this).find(
																	'.noEmi')
																	.val());
															var noEmi1 = parseInt($(
																	this).find(
																	'.noEmi1')
																	.val());
															var Emi36 = parseInt($(
																	this).find(
																	'.36Emi')
																	.val());
															var Emi36_1 = parseInt($(
																	this).find(
																	'.36Emi1')
																	.val());
															var Emi48 = parseInt($(
																	this).find(
																	'.48Emi')
																	.val());
															var Emi48_1 = parseInt($(
																	this).find(
																	'.48Emi1')
																	.val());

															if (lmoName != undefined) {
																if (lmoName == "") {
																	alert("LMO Name Required");
																	flag = false;
																	return false;
																} else if ($(
																		this)
																		.find(
																				'.noEmi1')
																		.val().length > 5) {
																	alert(lmoName
																			+ "  UpFront CPEs should be less than 99999");
																	flag = false;
																	return false;
																} else if ($(
																		this)
																		.find(
																				'.36Emi1')
																		.val().length > 5) {
																	alert(lmoName
																			+ " CPE-36 should be less than 99999");
																	flag = false;
																	return false;
																} else if ($(
																		this)
																		.find(
																				'.48Emi1')
																		.val().length > 5) {
																	alert(lmoName
																			+ "  CPE-48 should be less than 99999");
																	flag = false;
																	return false;
																} else if (noEmi
																		+ noEmi1 < 0) {
																	alert(lmoName
																			+ "  4000 CPE Request Are Invalid");
																	flag = false;
																	return false;
																} else if (Emi36
																		+ Emi36_1 < 0) {
																	alert(lmoName
																			+ "  1700 CPE Request Are Invalid");
																	flag = false;
																	return false;
																} else if (Emi48
																		+ Emi48_1 < 0) {
																	alert(lmoName
																			+ "  500 CPE Request Are Invalid");
																	flag = false;
																	return false;
																}
																if (lmoName != null
																		&& lmoName != "") {
																	if (noEmi1 != 0
																			|| Emi36_1 != 0
																			|| Emi48_1 != 0
																			|| msoLmoId == undefined) {
																		$(
																				'#lmoMsoCpeFormId')
																				.append(
																						$(
																								'<input>')
																								.attr(
																										'type',
																										'hidden')
																								.attr(
																										'name',
																										'lmoCpeRequest['
																												+ i
																												+ '].msoLmoId')
																								.val(
																										msoLmoId));
																		$(
																				'#lmoMsoCpeFormId')
																				.append(
																						$(
																								'<input>')
																								.attr(
																										'type',
																										'hidden')
																								.attr(
																										'name',
																										'lmoCpeRequest['
																												+ i
																												+ '].lmoName')
																								.val(
																										lmoName));
																		$(
																				'#lmoMsoCpeFormId')
																				.append(
																						$(
																								'<input>')
																								.attr(
																										'type',
																										'hidden')
																								.attr(
																										'name',
																										'lmoCpeRequest['
																												+ i
																												+ '].noEmiDemandQty')
																								.val(
																										noEmi1));
																		$(
																				'#lmoMsoCpeFormId')
																				.append(
																						$(
																								'<input>')
																								.attr(
																										'type',
																										'hidden')
																								.attr(
																										'name',
																										'lmoCpeRequest['
																												+ i
																												+ '].emi36DemandQty')
																								.val(
																										Emi36_1));
																		$(
																				'#lmoMsoCpeFormId')
																				.append(
																						$(
																								'<input>')
																								.attr(
																										'type',
																										'hidden')
																								.attr(
																										'name',
																										'lmoCpeRequest['
																												+ i
																												+ '].emi48DemandQty')
																								.val(
																										Emi48_1));
																		i++;
																	}
																}
															}
														});
										if (flag)
											$('#lmoMsoCpeFormId').submit();
									});
				});
function concatData(event, str1) {
	$('#' + str1 + '-value1').prop('checked', false);
	if (event.checked == true) {
		var temp
		var s1=$('#' + str1 + '-dep_date').val();
		var s2=$('#' + str1 + '-trans_id').val();
		var s3=$('#' + str1 + '-amount').val();
		var s4=$('#' + str1 + '-comments').val();
		var str2=$('#' + str1 +'-lmocode').val();
		if (s1 == "" || s2 == "") {
			alert("Missing Transaction or Deposit Date ");
			$('#' + str1 + '-value').prop("checked", false);
		} else {
			if (s1 === "")
				s1 = "NA";
			if (s2 === "")
				s2 = "NA";
			if (s3 === "")
				s3 = "NA";
			$('#' + str1 + '-dep_date').prop('readonly', 'true');
			$('#' + str1 + '-trans_id').prop('readonly', 'true');
			$('#' + str1 + '-comments').prop('readonly', 'true');
			var finalstr = str1 + "#" + s1 + "#" + s2.toString() + "#" + s3
					+ "#" + s4 + "#" + str2 + "#";
		$('#'+str1+'-value').val(finalstr);
		$('#fstr').val(temp);
		}
	} else {
		$('#' + str1 + '-dep_date').removeAttr('readonly');
		$('#' + str1 + '-trans_id').removeAttr('readonly');
		$('#' + str1 + '-comments').removeAttr('readonly');
		$(this).val("");
		$('#fstr').val("");
	}
}
function concatData1(event, str1) {
	$('#' + str1 + '-value').prop('checked', false);
	if (event.checked == true) {
		var temp
		var s1 = $('#' + str1 + '-dep_date').val();
		var s2 = $('#' + str1 + '-trans_id').val();
		var s3 = $('#' + str1 + '-amount').val();
		var s4 = $('#' + str1 + '-comments').val();
		var str2 = $('#' + str1 + '-lmocode').val();
/*		if (s1 == "" || s2 == "") {*/
			if (s1 === "")
				s1 = "NA";
			if (s2 === "")
				s2 = "NA";
			if (s3 === "")
				s3 = "NA";
			var finalstr = str1 + "#" + s1 + "#" + s2.toString() + "#" + s3
					+ "#" + s4 + "#" + str2 + "#";
			$('#' + str1 + '-value1').val(finalstr);
			$('#fstr').val(temp);
		/*}*/
	} else {
		$(this).val("");
		$('#fstr').val("");
	}
}
function showCalendar(event) {
	var eventId = event.id;
	$('#' + eventId).datepicker({
		dateFormat : 'yy-mm-dd',
		minDate : new Date(2016, 01, 01),
		numberOfMonths : 1,
		changeMonth : true,
		changeYear : true,
		onSelect : function(selected) {
			var dt = new Date(selected);
			$('#' + eventId).val(selected);
		}
	});
}
function SubmitSecondApprove() {
	$('secondapprove').submit();
}
