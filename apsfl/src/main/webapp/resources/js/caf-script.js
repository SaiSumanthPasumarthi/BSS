var selectedFPSubsCodes = [];
$(document).ready(function() {
	//$("#comsErrorMsg").show().delay(5000).fadeOut('slow');
	//$("#error").show().delay(15000).fadeOut('slow');
	$('#monthlyPaymentTable1').dataTable();
	var count1 = 1000000;
	var iptvPkgArray = [];
	$("#datepicker").datepicker();
	$("#popId").searchable();
	$('#entCustomerTables').treegrid();
	$('#osdDiv').hide();
	$('#stDiv').hide();
	$('#globalDiv').hide();
	$('.vpnSrvcDiv').hide();
	$('.noOfTPConnDiv').hide();
	$('#star').text('*');
 	$('#star').css('color', 'red')
	var prodCoreSrvcCode = $('#prodCoreSrvcCode').val();
	if(prodCoreSrvcCode != undefined) {
		if(prodCoreSrvcCode.indexOf("IPTV") != -1 ) {
			$('.stbDiv').show();
			//$("#stbModel").removeAttr("disabled");
			$("#stbSerialNo").removeAttr("readonly");
			//$(".iptvSrvcCodes").removeAttr("disabled");
			//$("#stbLease1").removeAttr("disabled");
		} else {
			$('.stbDiv').hide();
	    	$("#stbModel").attr("disabled", "disabled");
			$("#stbSerialNo").attr("readonly", "readonly");
			$("#stbLease").attr("disabled", "disabled");
			$("#stbLease1").attr("disabled", "disabled");
			//$(".iptvSrvcCodes").attr("disabled", "disabled");
		}
		if(prodCoreSrvcCode.indexOf("VPN") != -1 ) {
			$('.vpnSrvcDiv').show();
			$("#vpnService").removeAttr("disabled");
		} else {
			$('.vpnSrvcDiv').hide();
			$("#vpnService").attr("disabled", "disabled");
		}
		if(prodCoreSrvcCode.indexOf("VOIP") != -1 ) {
			$('.noOfTPConnDiv').show();
			$("#noOfTPConn").removeAttr("disabled");
		} else {
			$('.noOfTPConnDiv').hide();
			$("#noOfTPConn").attr("disabled", "disabled");
		}
	} else {
		$('.stbDiv').hide();
		$('.vpnSrvcDiv').hide();
	}
	
	var glchecked = $('input[name="selectCaf"]:checked').val();
	if(glchecked == "Gl") {
		$('#globalDiv').hide();
	} else if(glchecked == "Gr") {
		$('#globalDiv').show();
	}
	
	$('mycount').on('click',function(){
		var i=$('hitcount').val();
		$('hitcount').val(i+1);
		$("#clistform").attr('action',"./MonthlyBulkPayment");
		$("#clistform").submit();
		
	});
	
	$('#selectCaf1').on('click', function() {
		$("#groupDataDiv").hide();
		$('#globalDiv').show();
		$('#groupError').text('');
		$('#district').val('');
		$('#mandal').val('');
		$('#village').val('');
	});
	
	
	$('#selectCaf').on('click', function() {
		$("#groupDataDiv").hide();
		$('#globalDiv').hide();
		$('#groupError').text('');
		$('#district').val('');
		$('#mandal').val('');
		$('#village').val('');
	});
		
	$('#fingerPrint').on('change', function() {
		$('#fingerPrintDiv').show();
		$('#osdDiv').hide();
		$('#stDiv').hide();
	});
	$('#fingerPrint1').on('change', function() {
		$('#fingerPrintDiv').hide();
		$('#osdDiv').show();
		$('#stDiv').hide();
	});
	$('#fingerPrint2').on('change', function() {
		$('#fingerPrintDiv').hide();
		$('#osdDiv').hide();
		$('#stDiv').show();
	});
	
	var checkStatus = $(".productCheckbox").is(':checked');
	if(checkStatus) {
		$('#productTableDiv1').show();
		$('#productTableDiv2').show();
	} else {
		$('#productTableDiv1').hide();
		$('#productTableDiv2').hide();
	}
	//$('.stbDiv').hide();
	$('#featureParamDiv').hide();
	$('#approveCafTable').find('tr').each(function() {
		var row = $(this);
		row.find('.products').replaceWith('<td>'+row.find('.products').text().replace(/\,/g,'<br/>')+'</td>');
		row.find('.services').replaceWith('<td>'+row.find('.services').text().replace(/\,/g,'<br/>')+'</td>');
	});
	
	$('#monthlyPaymentTable tr[class !="tableHeader"]').live('click', function() {
		$('.monthlyRadio').on('change', function() {
			$('#monthlyPaymentDiv').show();
			$('#monthlyCafDiv').hide();
			var row = $("#monthlyPaymentTable input[name=monthlyRadio]:checked").closest('tr');
			//var cafNo = row.find('.cafNo').text();
			var customerName = row.find('.customerName').text();
			var aadharNo = row.find('.aadharNo').text();
			var totalCharge = row.find('.totalCharge').text();
			var custId = row.find('.custId').text();
			var custType = row.find('.custType').text();
			var districtUid = row.find('.districtUid').text();
			//$("#cafNo").val(cafNo);
			$("#custId").val(custId);
			$("#aadharNumber").val(aadharNo);
			$("#customerName").val(customerName);
			$("#totalProdCharge").val(totalCharge.replace(/\,/g,''));
			$("#custType").val(custType);
			$("#districtUid").val(districtUid);
			
		});
	});
	
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentDate = date.getDate();
	var currentYear = date.getFullYear();
	$("#indDob, #poiDateOfIssue, #poaDateOfIssue, #dateOfIncorporation").datepicker({
		changeMonth: true,
		changeYear: true,
		format: "MM/dd/yyyy",
		maxDate: new Date(currentYear, currentMonth, currentDate)
	}); 
	
	$(document).on('click','.viewcafDetails',function() {
		$(".intrInfo").hide();
		$(".imgp").show();
		$(".imgm").hide();
		
    	var index = $(".viewcafDetails").index(this);
		var cafNo = $(".custmerCafNo:eq("+index+")").val();
		var custId = $(".custId:eq("+index+")").val();
		$.ajax({
			async:false,
	   	    type: 'GET', 
	   	    url: 'getAllCafDetails', 
	   	    data: {cafNo : cafNo, custId : custId},
	   	    contentType: 'application/json',
	   	    success: function(CafDetailsVO) { 
	   	    	if(CafDetailsVO.customerInfoList.length > 0) {
				    $("#getCustInfo> tbody").html("");
				    $.each(CafDetailsVO.customerInfoList,function(idx,obj) {
				    	var sno=idx+1;
						$("#getCustInfo> tbody").append('<tr>'
							    +'<td>' +obj.firstname+' '+obj.mname+' '+obj.lastname+'</td>'
					            +'<td>' +obj.emailid+'</td>'
					            +'<td>' +obj.dob +'</td>'
					            +'<td>' +obj.contactno+'</td>'
					            +'<td>' +obj.billFrequency+'</td>'
					            +'<td>' +obj.custType+'</td>'
					            +'<td>'+obj.lmoCode+'</td></tr>');
						
				    });
		 		} else { 
		 			$("#getCustInfo> tbody").html("");
		 			$('#getCustInfo> tbody').append('<tr><td colspan="7" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
	   	 	
				if(CafDetailsVO.paymentList.length > 0) {
			 		$("#RecentPaymentsInformation> tbody").html("");
			 		$.each(CafDetailsVO.paymentList,function(idx,obj) {
					var pmntDate = obj.pmntDate.split(" ");
					var cnt=idx+1;
					$('#RecentPaymentsInformation> tbody').append('<tr>'
							+'<td>'+cnt+'</td>'	
					        +'<td>'+obj.pmntRefNo+'</td>'
					        +'<td>'+obj.pmntMode+' </td>'
					        +'<td>'+pmntDate[0]+'</td>'
					        +'<td>'+(parseFloat(obj.pmntAmt)).toFixed(2)+'</td></tr>');  
				    	
					});
		 		} else {
		 			$("#RecentPaymentsInformation> tbody").html("");
		 			$('#RecentPaymentsInformation> tbody').append('<tr><td colspan="5" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
				 
				if(CafDetailsVO.cafDao!=null) {
		 			var emiCharge = CafDetailsVO.cafDao[11] == null || "" ? "0.00" : CafDetailsVO.cafDao[11].toFixed(2);
		 			var charge = CafDetailsVO.cafDao[12] == null || "" ? "0.00" : CafDetailsVO.cafDao[12].toFixed(2);
		 			var cpeInstCount = parseFloat(CafDetailsVO.cafDao[15] == null ? "0" : CafDetailsVO.cafDao[15]);
				    $("#getAllParticularCPE> tbody").html("");
				    $("#getAllParticularCPE> tbody").append('<tr>'
				    		+'<td>'+CafDetailsVO.cafDao[4]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[2]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[3]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[9]+'</td>'
				    		+'<td>'+charge+'</td>'
				    		+'<td>'+emiCharge+'</td>'
				    		+'<td>'+cpeInstCount+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[10]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[16]+'</td></tr>'
				    );
		 		} else { 
		 			$("#getAllParticularCPE> tbody").html("");
		 			$('#getAllParticularCPE> tbody').append('<tr><td colspan="9" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
			 		
		 		if(CafDetailsVO.cafDao!=null) {
		 			var pinCode = CafDetailsVO.cafDao[13] == null || "" ? "NA" : CafDetailsVO.cafDao[13];
				    $("#getinstadres> tbody").html("");
				    $("#getinstadres> tbody").append('<tr>'
				    		+'<td>'+CafDetailsVO.cafDao[5]+','+CafDetailsVO.cafDao[14]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[8]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[7]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[6]+'</td>'
				    		+'<td>'+pinCode+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[0]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[1]+'</td>'
				    		+'<td>'+CafDetailsVO.cafDao[6]+'</td></tr>'
				    		
				    );
		 		} else { 
		 			$("#getinstadres> tbody").html("");
		 			$('#getinstadres> tbody').append('<tr><td colspan="8" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
		 		
				if(CafDetailsVO.newCafList.length > 0) {
					$("#cafdetails> tbody").html("");
				    $.each(CafDetailsVO.newCafList, function(idx,obj) {
				    	var cnt=idx+1;
						var s = +obj.prodcharge+ +obj.prodtax;
						$("#cafdetails> tbody").append('<tr>'
						        	+'<td>' +cnt+'</td>'
						        	/*+'<td>'  +obj.stbcafno+'</td>'*/
						            +'<td>'  +obj.prodname+'</td>'
						            +'<td>' +obj.prodtype +'</td>'
						            +'<td style="word-break: break-all;">' +obj.srvcname+'</td>'
						            +'<td>' +obj.createdon+'</td>'
						            +'<td>' +s.toFixed(2)+'</td></tr>'
						);
				    });
				} else {
					$("#cafdetails> tbody").html("");
				 	$('#cafdetails> tbody').append('<tr><td colspan="6"style="text-align:center"><h2>No Records Found</h2></td></tr>');
				}
						
				//Telephone Number List
		 		if(CafDetailsVO.telephoneNo.length > 0) {
		 			$("#telenoid> tbody").html("");
					   	$.each(CafDetailsVO.telephoneNo,function(idx,obj) {
					   		var cnt=idx+1;
						   	$('#telenoid> tbody').append('<tr>'
				   			+'<td>' +cnt+'</td>'
					        +'<td>'+obj.telephoneNo+'</td></tr>');  
						});
		 		} else {
		 			$("#telenoid> tbody").html("");
		 			$('#telenoid> tbody').append('<tr><td colspan="5" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
						
				//$("#telepassword"+c).val(CafDetailsVO.telephoneNo[1]);
		 		if(CafDetailsVO.telephoneser.length > 0) {
				    $("#telephonetable> tbody").html("");
				    $.each(CafDetailsVO.telephoneser,function(idx,obj) {
						$("#telephonetable> tbody").append('<tr>'
					            +'<td>' +obj.featurecode+'</td>'
					            +'<td>' +obj.prmcode +'</td>'
					            +'<td>' +obj.prmvalue+'</td></tr>' );
				    });
		 		} else { 
		 			$("#telephonetable> tbody").html("");
		 			$('#telephonetable> tbody').append('<tr><td colspan="7" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
			 		
		 		//Billing Information BillInfotable
		 		if(CafDetailsVO.billInfo.length > 0) {
		 			var formData = "";
				    $("#BillInfotable> tbody").html("");
				    $.each(CafDetailsVO.billInfo, function(idx,obj) {
				    	formData = '<form style="float:left;" name="downloadBill" action="#" class="downloadBillForm" method="post">'+
	                    +'<input type="hidden" name = "filePath" value="'+obj.filePath+'" />'+
						+'<span class="downloadBill" style="cursor: pointer;" title="Download Bill"><img src="./resources/images/apf_add.png"></span></form>';
				    	var sno = idx+1;
						$("#BillInfotable> tbody").append('<tr>'
							    +'<td>' +sno+'</td>'
					            +'<td>' +obj.billMonth+'</td>'
					            +'<td>' +obj.amount +'</td>'
					            +'<td>' +obj.invDate+'</td>'
					            +'<td>' +obj.dueDate+'</td>'
					            +'<td><form style="float:left;" name="downloadBill" action="#" class="downloadBillForm" method="post" > <input type="hidden" name = "filePath" value="'+obj.filePath+'" /> <span class="downloadBill" style="cursor: pointer;" title="Download Bill"><img src="./resources/images/downloadicon.png"></span></form></td></tr>');
				    });
		 		} else { 
		 			$("#BillInfotable> tbody").html("");
		 			$('#BillInfotable> tbody').append('<tr><td colspan="7" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
				 		
		 		//Ticket Information 
		 		if(CafDetailsVO.ticketInfoList.length > 0) {
				    $("#TicketInfotable> tbody").html("");
				    $.each(CafDetailsVO.ticketInfoList,function(idx,obj) {
				    	var sno=idx+1;
						$("#TicketInfotable> tbody").append('<tr>'
							    +'<td>' +sno+'</td>'
					            +'<td>' +obj.ticket_no+'</td>'
					            +'<td>' +obj.submitted_on +'</td>'
					            +'<td>' +obj.description+'</td>'
					            +'<td>' +obj.status+'</td>'
					            +'<td>'+obj.closed_on+'</td></tr>');
						
				    });
		 		} else { 
		 			$("#TicketInfotable> tbody").html("");
		 			$('#TicketInfotable> tbody').append('<tr><td colspan="7" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
				 		
		 		//STB Information
		 		if(CafDetailsVO.stbInfoList.length > 0) {
		 			$("#getStbInfo> tbody").html("");
					   	$.each(CafDetailsVO.stbInfoList,function(idx,obj) {
						   	var cnt=idx+1;
						   	var stbboxcharge = obj.stbboxcharge == null || "" ? "0.00" : obj.stbboxcharge;
						   	var stbinstcharge = obj.stbinstcharge == null || "" ? "0.00" : obj.stbinstcharge;
				 			var stbemicount = parseFloat(obj.stbemicount == null ? "0" : obj.stbemicount);
						   	
						   	$('#getStbInfo> tbody').append('<tr>'
							+'<td>'+cnt+'</td>'	
					        +'<td>'+obj.stbcafno+'</td>'
					        +'<td>'+obj.stbprofileid+' </td>'
					        +'<td>'+stbboxcharge+' </td>'
					        +'<td>'+stbinstcharge+' </td>'
					        +'<td>'+stbemicount+' </td>'
					        +'<td>'+obj.stbslno+' </td>'
					        +'<td>'+obj.stbmacaddr+'</td>'
					        +'<td>'+obj.nwsubscode+'</td></tr>');  
						});
		 		} else {
		 			$("#getStbInfo> tbody").html("");
		 			$('#getStbInfo> tbody').append('<tr><td colspan="9" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
	   	    }
		});
	})
	
	$('#monthlyPaymentDiv').hide();
	$('#feasibility').on('change', function() {
		$("#paymentMode").removeAttr("disabled");
		$("#paidAmount").removeAttr("disabled");
		if($('#flag').val() == "true") {
			$("#paymentButton").attr("disabled", "disabled");
		} else {
			$("#paymentButton").removeAttr("disabled");
		}
	});
	
	$('#feasibility1').on('change', function() {
		$("#paymentMode").attr("disabled", "disabled");
		$("#paidAmount").attr("disabled", "disabled");
		$("#paymentButton").removeAttr("disabled");
	});
	
	$('#cableCharge').on('change', function() {
		 var value = $('#cableCharge').val();
         if (value.startsWith(".")) {
             $('#cableCharge').val('');
             $('#cableChargeError').text("you can not insert dot and zero as first character");
         } else {
        	 $('#cableChargeError').text("");
         }
	});
	
	$('#custType').on('change', function() {
		var custType = $('#custType').val();
		if(custType!="") {
			if(custType == "GOVT") {
				custType = "GOVT TYPES";
			} else {
				custType = "PRIVATE TYPES";
			}
			$.ajax({
				type : "GET",
				url : "getEnterpriseSubTypes",
				dataType : "json",
				data : "&custType="+ custType,
				success : function(response) {
					var $select = $('#officeTypeLov');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.lovValue).text(val.lovValue).appendTo($select);
					});
				}
			});
		}
		if(custType == "GOVT TYPES") {
        	$('#entLable').text('TAN No');
		} else if(custType == "PRIVATE TYPES") {
        	$('#entLable').text('Registration No/VAT No/TAN No');
		} else {
			var $select = $('#officeTypeLov');
			$select.find('option').remove();  
			$('<option>').val("").text("--Select--").appendTo($select);
			$('#entLable').text('Organization Number');
		}
	});
	
	$('#entCheckbox').on('change', function() {
		var checked = $(this).is(':checked');
		var address1 = $('#address1').val();
		var address2 = $('#address2').val();
		var locality = $('#locality').val();
		var city = $('#village').val();
		var area = $('#district').val();
		var mandal = $('#mandal').val();
		var pincode = $('#pinCode').val();
		var fax = $('#fax').val();
		var stdcode = $('#stdCode').val();
		var landline =$('#landlineNo').val();
		if(address1 != "" && locality != "" && city != "" && area != "" && pincode != "" && stdcode!= "" && landline != "") {
			$('#addError').text("");
			if(checked) {
				if(city != "" && mandal != "" && area != "") {
					$.ajax({
						type : "GET",
						url : "getVillagesByVillageId",
						dataType : "json",
						data : "&villageSlno="+city+"&mandalSlno="+mandal+"&districtUid="+area,
						success : function(response) {
							var $select = $('#blVillage');
							$select.find('option').remove();  
							$('<option>').val("").text("--Select--").appendTo($select);
				        	   $('<option>').val(response.villageSlno).attr('selected', true).text(response.villageName).appendTo($select);
				        	   $("#blVillage1").val(response.villageSlno);
						}
					});
				}
				if(area != "" && mandal != "") {
					$.ajax({
						type : "GET",
						url : "getMandalsByDistrictIdAndMandalSrlNo",
						dataType : "json",
						data : "&districtId="+area+"&mandalSrlNo="+mandal,
						success : function(response) {
							var $select = $('#blMandal');
							$select.find('option').remove();  
							$('<option>').val("").text("--Select--").appendTo($select);
				        	   $('<option>').val(response.mandalSlno).attr('selected', true).text(response.mandalName).appendTo($select);
				        	   $("#blMandal1").val(response.mandalSlno);
						}
					});
				}
				$("#blAddress1").val(address1);
				$("#blAddress2").val(address2);
				$("#blLocality").val(locality);
				$("#blDistrict").val(area);
				$("#blDistrict1").val(area);
				$("#blDistrict1").attr("name", "blArea");
				$("#blMandal1").attr("name", "blMandal");
				$("#blVillage1").attr("name", "blCity");
				$("#blPinCode").val(pincode);
				$("#blFax").val(fax);
				$("#blStdCode").val(stdcode);
				$("#blLandlineNo").val(landline);
				$("#blAddress1, #blAddress2, #blLocality, #blPinCode, #blFax, #blStdCode, #blLandlineNo").attr("readonly", "readonly");
                $("#blDistrict, #blMandal, #blVillage").attr("disabled", "disabled");
			} else {
				$("#blAddress1, #blAddress2, #blLocality, #blPinCode, #blFax, #blStdCode, #blLandlineNo").removeAttr("readonly");
				$("#blDistrict, #blMandal, #blVillage").removeAttr("disabled", "disabled");
				$("#blDistrict1").removeAttr("name", "blArea");
				$("#blMandal1").removeAttr("name", "blMandal");
				$("#blVillage1").removeAttr("name", "blCity");
				$("#blAddress1").val('');
				$("#blAddress2").val('');
				$("#blLocality").val('');
				$("#blVillage1").val('');
				$("#blVillage").val('');
				$("#blDistrict").val('');
				$("#blMandal").val('');
				$("#blDistrict1").val('');
				$("#blMandal1").val('');
				$("#blPinCode").val('');
				$("#blFax").val('');
				$("#blStdCode").val('');
				$("#blLandlineNo").val('');
			}
		} else {
			$('#addError').text("Please Enter Organization Location Address Details.");
			$("#entCheckbox").prop('checked',false);
		}
	});
	
	$('#aadharNumber').on('keyup', function() {
		$('#getAadharDetails').attr("disabled", "disabled");
		$('#titleLovName, #fatherName, #indDob, #firstName, #lastName, #gender, #pinCode, #mobileNo').val('');
        $('#address1, #address2, #locality, #district, #mandal, #cafVillage').val('');
	});
	
	$(document).on('change','#aadharNumber', function() {
		var aadharNo = $('#aadharNumber').val();
		var custType = $('#custType').val();
		if(validate(aadharNo) && custType == "INDIVIDUAL") {
			$.ajax({ 
				type : "GET",
				async : false,
	       	    url: 'checkBlackListCustomer',
	       	    data: "&aadharNumber="+ aadharNo,
				success : function(response) {
					if($.trim(response) == "false") {
						$('.aadharError').html('');
						$('#getAadharDetails').removeAttr("disabled");
						$('#titleLovName').val('Mr.');
					} else if($.trim(response) == "true") {
						$('#aadharNumber').val('');
						$('#getAadharDetails').attr("disabled", "disabled");
						$('.aadharError').html("Aadhar No is already existed.");
					}
				}
			});
		} else if(custType == "ENTERPRISE") {
			$('#getAadharDetails').attr("disabled", "disabled");
			$('#titleLovName').val('Ms.');
		} else {
			$('#aadharNumber').val('');
			$('#getAadharDetails').attr("disabled", "disabled");
			$('.aadharError').html("Invalid Aadhar Number. Please enter again.");
		}
	});
	
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
	/**Added Subscriber Code**/
	var osdSubCode = "0";
	
	$(document).on('click','#individualCafFPActivationButton',function() {
		var fpStatus = $('input[name="fingerPrint"]:checked').val();
		if(fpStatus == "F") {
			fpPosition = $('#fpPosition').val();
			fpFontSize = $('#fpFontSize').val();
			fpFontType = $('#fpFontType').val();
			fpFontColor = $('#fpFontColor').val();
			fpDuration = $('#fpDuration').val();
			fpfingerPrintType = $('#fpfingerPrintType').val();
			fpChannel = $('#fpChannel').val();
			fpBgColor = $('#fpBgColor').val();
		} else if(fpStatus == "O") {
			fpPosition = $('#osdPosition').val();
			fpFontSize = $('#osdFontSize').val();
			fpFontType = $('#osdFontType').val();
			fpFontColor = $('#osdFontColor').val();
			fpBgColor = $('#osdBgColor').val();
			fpDuration = $('#osdDuration').val();
			fpMessage = $('#osdMessage').val();
			fpUserCanCloseMessage = $('#osdUserCanCloseMessage').val();
			osdSubCode = $('#osdSubCode').val();
			
		} else if(fpStatus == "S") {
			fpMessage = $('#stMessage').val();
			fpDuration = $('#stDuration').val();
		}
		if(fpPosition != "" && fpFontSize != "" && fpFontType != "" && fpFontColor != "" 
			&& fpDuration != "" && fpfingerPrintType != "" && fpChannel != "" 
				&& fpBgColor != "" && fpMessage != "" && fpUserCanCloseMessage != "") {
			$('.individualCafFPActivationHidden').find('tr').each(function() {
				var row = $(this);
					fpCount++;
					var nwsubCode = row.find('.subcriberCode').text();
					selectSubScribersCodes.push(nwsubCode);
			});
			if(osdSubCode!=""){
				var osdSubCodes = osdSubCode.split(',');
				for(var i = 0; i < osdSubCodes.length; i++) {
					fpCount++;
					//alert(osdSubCodes[i]);
					selectSubScribersCodes.push(osdSubCodes[i]);
				}
			}
			
			if(fpCount <= 0) {
				$('#individualCafFPActivationError').text("Please check atleast one Caf.");
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
	        		};
	           	var fingerPrintJson = JSON.stringify(fingerPrintObject);
	           	//alert(fingerPrintJson);
	        	$.ajax({ 
	        		 async:false,
	        	     type: 'POST', 
	        	     url: 'getFingerPrintDetails', 
	        	     data: fingerPrintJson,
	        	     contentType: 'application/json',
					 success : function(response) {
						 alert(response);
						 window.location.reload();
					}
				});
			}
		} else {
			$('#individualCafFPActivationError').text("Please Enter all mandatory fields.");
		}
		selectSubScribersCodes = [];
	});
	
	$(document).on('click','#groupCafFPActivationSubmit',function() {
		if($("#groupCafFPActivation").is(':visible') && $("#groupCafFPActivation > tbody > tr > td").text() != "No data available in table"){
			var fpStatus = $('input[name="fingerPrint"]:checked').val();
			if(fpStatus == "F") {
				fpPosition = $('#fpPosition').val();
				fpFontSize = $('#fpFontSize').val();
				fpFontType = $('#fpFontType').val();
				fpFontColor = $('#fpFontColor').val();
				fpDuration = $('#fpDuration').val();
				fpfingerPrintType = $('#fpfingerPrintType').val();
				fpChannel = $('#fpChannel').val();
				fpBgColor = $('#fpBgColor').val();
			} else if(fpStatus == "O") {
				fpPosition = $('#osdPosition').val();
				fpFontSize = $('#osdFontSize').val();
				fpFontType = $('#osdFontType').val();
				fpFontColor = $('#osdFontColor').val();
				fpBgColor = $('#osdBgColor').val();
				fpDuration = $('#osdDuration').val();
				fpMessage = $('#osdMessage').val();
				fpUserCanCloseMessage = $('#osdUserCanCloseMessage').val();
			} else if(fpStatus == "S") {
				fpMessage = $('#stMessage').val();
				fpDuration = $('#stDuration').val();
			}
			if(fpPosition != "" && fpFontSize != "" && fpFontType != "" && fpFontColor != "" 
				&& fpDuration != "" && fpfingerPrintType != "" && fpChannel != "" 
					&& fpBgColor != "" && fpMessage != "" && fpUserCanCloseMessage != "") {
				
				var glchecked = $('input[name="selectCaf"]:checked').val();
				var district = $("#district").val();
				var mandal = $("#mandal").val();
				var village = $("#village").val();
				
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
		    		    "glchecked" : glchecked,
		    		    "district" : district,
		    		    "mandal" : mandal,
		    		    "village" : village
		    		};
		       	var fingerPrintJson = JSON.stringify(fingerPrintObject);
		    	$.ajax({ 
		    		 async:false,
		    	     type: 'POST', 
		    	     url: 'getFingerPrintDetails', 
		    	     data: fingerPrintJson,
		    	     contentType: 'application/json',
					 success : function(response) {
						 $("#fingerPrintId").text(response);
						 $('#fingerPrint').prop("checked", true);
					}
				});
			} else {
				$('#individualCafFPActivationError').text("Please Enter all mandatory fields.");
			}
		} else {alert("No data available in table to Submit.")}
	});
	
	// When we select multiple checkbox, storing all checkbox information in hidden table
	$('#individualCafFPActivation').on("click",'input[type=checkbox]',function() {
		if ($(this).is(":checked")) {
			var subcriberCode = $(this).closest("tr").find(".subcriberCode").text();
			$('.individualCafFPActivationHidden').append('<tr>'+'<td class="subcriberCode">' +subcriberCode+'</td> '+'</tr> ');
			selectedFPSubsCodes.push(subcriberCode);
		} else {
			var uncheckSubCode = $(this).closest("tr").find(".subcriberCode").text();
			$('.individualCafFPActivationHidden').find('tr').each(function() {
				if(uncheckSubCode == $(this).find('.subcriberCode').text()){
					$(this).remove();
					$.each(selectedFPSubsCodes, function(index, value) {
						if(value == uncheckSubCode) {
							selectedFPSubsCodes.splice(index,1);
							}
						});
				}
			});
		}
	});
	
	var selectedProductList = [];
	var agruniqueids = [];
	var coreSrvcCodes = [];
	var totalCharge = [];
	var totalTax = [];
	var tenantCodes = [];
	var prodCodes = [];
	var prodTax = 0;
	var prodTotalCharge = 0;
	var packageCharge = 0;
	var activationChrg = 0;
	var secDepositChrg = 0;
	var status = false;
	$("#customerCafButton").click(function() {
		var coreSrvcCode;
		var validationStatus;
		var bulkUpload = $('#bulkUpload').val();
		if(bulkUpload == "SingalCaf" || bulkUpload == "MultipleCafs") {
			validationStatus = $("#bulkCafUploadForm").valid();
		} else if(bulkUpload == "No") {
			validationStatus = $("#packagesform").valid();
		}
		if(validationStatus) {
			$('.prodclass').find('tr').each(function() {
				var row = $(this);
				if (row.find('input[type="checkbox"]').is(':checked')) {
					var toatal = row.find('.totalCharge').text();
					var tax = row.find('.taxAmount').text();
					var prod = row.find('.prodName').text();
					var services = row.find('.services').text();
					var agruniqueid = row.find('.agruniqueid').text();
					var prodCharge = row.find('.prodCharge').text();
					var pkgType = row.find('.pkgType').text();
					var prodCode = row.find('.prodCode').text();
					var tenantCode = row.find('.tenantCode').text();
					var actAmount = row.find('.actAmount').text();
					var secDeposit = row.find('.secAmount').text();
					coreSrvcCode = row.find('.coreServiceCode').text();
					agruniqueids.push(agruniqueid);
					prodCodes.push(prodCode);
					tenantCodes.push(tenantCode);
					coreSrvcCodes.push(coreSrvcCode);
					if(coreSrvcCode.includes("VOIP")) {
						$('#featureParamDiv').show();
						$('#selectPackagesDiv').hide();
						var telProdCode = prodCode;
						var telSrvcCode = row.find('.telSrvcCode').text();
						var telTenantCode = row.find('.mspCode').text();
						$("#telProdCode").val(telProdCode);
						$("#telSrvcCode").val(telSrvcCode);
						$("#telTenantCode").val(telTenantCode);
						var featureCodes = row.find('.featureParamCode').text();
						var html = "";
						var html1 = "";
						var html2 = "";
						var maxVal = "";
						var indexCount = 0; 
						if(featureCodes != "") {
							var fCodes = featureCodes.split(',');
							$.each(fCodes, function(key, value) {
								$.ajax({
									type : "GET",
									url : "getFeatureParamsByFeatureCodes",
									dataType : "json",
									async : false,
									data : "&featureCodes="+ value,
									success : function(response) {
										if(response.length > 0) {
											maxVal = "";
											html1 = "";
											html1 = ' <div class="panel-header bg-light"> '+
												    ' 	<h3><label id="ilab_'+indexCount+'">'+value+'</label> '+
												    '       <input type="hidden" name="featureCodeList['+indexCount+'].featureCode" value="'+value+'" /> '+
												    '       <img style="cursor: pointer;" class="addFParams" src="./resources/images/Add_Assets.png" align="right"/></h3> '+
												    ' </div> '+
												    ' <div class="panel iIndex" style="border: 1px solid #d4d5d6;"> '+
												    ' <div class="panel-content">';
											$.each(response, function(k,val) {
												if(val.paramValueType == 'Single Literal'){
													html1 = html1 + ' <div class="row">'
										    		 +'      <div class="col-sm-5">'
										    		 +'          <label class="control-label labelClass">'+val.paramLabel+'</label>'
										    		 +' 	 </div>'
										    		 +'      <div class="col-sm-6">'
										    		 +'          <input type="text" name="featureCodeList['+indexCount+'].paramsList['+k+'].paramValue" class="form-control form-white valueClass" maxlength = "10"/>'
										    		 +'          <input type="hidden" name="featureCodeList['+indexCount+'].paramsList['+k+'].paramCode" class="form-control form-white" value = "'+val.prmCode+'" />'
										    		 +' 	 </div>'
										    		 +'    </div>';
		
												}
												else if(val.paramValueType == 'LOV'){
													var options ='<option value="">-Select-</option>';
													$.each(val.featureList, function(key, value){indexCount
														options = options+'<option value="'+value.lovValue+'">'+value.lovValue+'</option>';
													});
													html1 = html1 + '    <div class="row">'
										    		 +'      <div class="col-sm-5">'
										    		 +'          <label class="control-label labelClass">'+val.paramLabel+'</label>'
										    		 +' 	 </div>'
										    		 +'      <div class="col-sm-6">'
										    		 +'			 <select name="featureCodeList['+indexCount+'].paramsList['+k+'].paramValue" class="form-control form-white valueClass" >'+options+'</select>'
										    		 +'          <input type="hidden" name="featureCodeList['+indexCount+'].paramsList['+k+'].paramCode" class="form-control form-white" value = "'+val.prmCode+'" />'
										    		 +' 	 </div>'
										    		 +'    </div>';
												}
												maxVal = val.maxParamValues;
									        });
											html1 = html1 +'<div id="hid_'+indexCount+'"></div></div></div>' + '<div style = "display:none;"><input type="hidden" id="id_'+indexCount+'" value="'+maxVal+'"/><input type="hidden" id="pcntid_'+indexCount+'" value="0" /></div>';
											indexCount++;
										} else {
											$("#featueParamError").text('No telephone featureparams avaliable for selected package.');
											html1 = ' <div class="panel-header bg-light"> '+
												    '<h3 align = "center"><font color = "red">No Telephone Features configured For Selected Package.</font></h3></div>';
										}
									}
								});
								html2 = html2 + html1;
						   });
						   $("#tel_features").append(html2);
						}
						$(".addFParams").click(function() {
							 var indx = $(".addFParams").index(this);
							 var maxAllowable = $("#id_"+indx).val() - 1;
							 if(maxAllowable > 0)
								 $("#id_"+indx).val(maxAllowable);			 
							 var pCnt =  parseInt($("#pcntid_"+indx).val()) + 1;
							 $("#pcntid_"+indx).val(pCnt);
							 var iilab = $("#ilab_"+indx).text();
							 var html11 = "";
							 if(maxAllowable > 0) {
								 $.ajax({
										type : "GET",
										url : "getFeatureParamsByFeatureCodes",
										dataType : "json",
										async : false,
										data : "&featureCodes="+ iilab,
										success : function(response) {
											html11 = html11 + '<div class="row removeImgClass_'+indx+'_'+pCnt+'" style="float:right; padding-right:60px;"><div class="col-sm-12"><img style="cursor: pointer;" width="15" height="15" class="closeFeatures close_'+indx+'_'+pCnt+'" src="./resources/images/close1.png"><input type="hidden" class="hidFeatures" value="'+indx+'_'+pCnt+'"/></div></div>';
											$.each(response, function(k,val) {
												if(val.paramValueType == 'Single Literal'){
													html11 = html11 + ' <div class="row fValueClass_'+indx+'_'+pCnt+'">'
										    		 +'      <div class="col-sm-5">'
										    		 +'          <label class="control-label labelClass">'+val.paramLabel+'</label>'
										    		 +' 	 </div>'
										    		 +'      <div class="col-sm-6">'
										    		 +'          <input type="text" name="featureCodeList['+indx+'].paramsList['+k+'].paramValue" class="form-control form-white valueClass" />'
										    		 +'          <input type="hidden" name="featureCodeList['+indx+'].paramsList['+k+'].paramCode" class="form-control form-white" value = "'+val.prmCode+'" />'
										    		 +' 	 </div>'
										    		 +'    </div>';
		
												}
												else if(val.paramValueType == 'LOV'){
													var options ='<option value="">-Select-</option>';
													$.each(val.featureList, function(key, value){
														options = options+'<option value="'+value.lovValue+'">'+value.lovValue+'</option>';
													});
													html11 = html11 + ' <div class="row fValueClass_'+indx+'_'+pCnt+'">'
										    		 +'      <div class="col-sm-5">'
										    		 +'          <label class="control-label labelClass">'+val.paramLabel+'</label>'
										    		 +' 	 </div>'
										    		 +'      <div class="col-sm-6">'
										    		 +'			 <select name="featureCodeList['+indx+'].paramsList['+k+'].paramValue" class="form-control form-white valueClass" >'+options+'</select>'
										    		 +' 		 <input type="hidden" name="featureCodeList['+indx+'].paramsList['+k+'].paramCode" class="form-control form-white" value = "'+val.prmCode+'" />'
										    		 +' 	 </div>'
										    		 +'    </div>';
												}
									        });
										}
									});
								 	$("#hid_"+indx).append(html11);
							 }
							 else {
								 alert("Max Permitted Count Reached");	 
							}
							 
					    });
						
						$(document).on('click','.closeFeatures',function() {
							var idz = $(".closeFeatures").index(this);
							var hidcnt = $(".hidFeatures:eq("+idz+")").val();
							$(".fValueClass_"+hidcnt).remove();
							$(".removeImgClass_"+hidcnt).remove();
							var arri = hidcnt.split("_");
							var maxAllowable1 = parseInt($("#id_"+arri[0]).val()) + 1;
							$("#id_"+arri[0]).val(maxAllowable1);
							
						});
					} else {
						$('#productError').text("Please select Package with all Three services.");
					}
				} else {
					$('#productError').text("Please check atleast one Package.");
				}
			});
			$("#agruniqueid").val(agruniqueids);
			$("#tenantCode").val(tenantCodes);
			$("#prodCode").val(prodCodes);
			$("#pkgCoreSrvcCode").val(coreSrvcCodes);
			var voipCoreSrvcCode = "";
			if(coreSrvcCodes.length > 0) {
				$.each(coreSrvcCodes, function(index, value) {
					voipCoreSrvcCode += value +",";
				});
				if(voipCoreSrvcCode.indexOf("VOIP") != -1) {
					$(document).on('click','#cafFeaturesButton',function() {
						status = true;
						$(".valueClass").each(function() {
							var val = $(this).val();
							if(val == "") {
								status = false;
								alert("Please select or enter all values.");
							}
						});
						if(status == true) {
							ajaxindicatorstart('Please Wait...');
							$('#productError').text("");
							if(bulkUpload == "SingalCaf" || bulkUpload == "MultipleCafs") {
								$("#bulkCafUploadForm").attr('action',"saveBulkCafUpload");
								$("#bulkCafUploadForm").submit();
							} else if(bulkUpload == "No") {
								$("#packagesform").attr('action',"createPackages");
								$("#packagesform").submit();
							}
						}
					});
				} else {
					ajaxindicatorstart('Please Wait...');
					$('#productError').text("");
					if(bulkUpload == "SingalCaf" || bulkUpload == "MultipleCafs") {
						$("#bulkCafUploadForm").attr('action',"saveBulkCafUpload");
						$("#bulkCafUploadForm").submit();
					} else if(bulkUpload == "No") {
						$("#packagesform").attr('action',"createPackages");
						$("#packagesform").submit();
					}
				}
			}
		}
	});
	
	$(document).on('click','#enterpriseBulkUploadCafButton',function() {
		var validationStatus = $("#packagesform").valid();
		if(validationStatus) {
			ajaxindicatorstart('Please Wait...');
			$('#productError').text("");
			$("#packagesform").attr('action',"getCafBulkUploadWorkorderForm");
			$("#packagesform").submit();
		}
	});
	
	var BaseArray = [];
	var AddonArray = [];
	var OneTimeArray = [];
	$(".productCheckbox").change(function() {
		var custType = $('#custType').val();
		var stbModel = $('#stbModel').val();
		$('#productTableDiv1').hide();
		$('#productTableDiv2').hide();
	    var checked = $(this).is(':checked');
	    $(".productCheckbox").prop('disabled',true);
	    if(checked) {
	        $(this).prop('disabled',false);
	        $('#productTableDiv1').show();
			$('#productTableDiv2').show();
			var prodType = $(this).closest('tr').find('.prodType').text();
			var srvcCode = $(this).closest('tr').find('.srvcCode').text();
			if($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 ) {
				$('.stbDiv').show();
				$("#stbModel").removeAttr("disabled");
				$("#stbSerialNo").removeAttr("readonly");
				$("#stbLease").removeAttr("disabled");
				$("#stbLease1").removeAttr("disabled");
				BaseArray.push("IPTV");
			}
			if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VPN") != -1 ) {
				$('.vpnSrvcDiv').show();
				$("#vpnService").removeAttr("disabled");
				BaseArray.push("VPN");
			}
			if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VOIP") != -1 ) {
				$('.noOfTPConnDiv').show();
				$("#noOfTPConn").removeAttr("disabled");
				BaseArray.push("VOIP");
			}
			$(".productCheckbox1").each(function() {
				if(($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) || ($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 && custType == 'ENTERPRISE' && stbModel=='NA') ) {
					$(this).prop('disabled',true);
				} else if((srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) || ($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 && custType == 'ENTERPRISE' && stbModel=='NA') ) {
					$(this).prop('disabled',true);
				} else {
					$(this).addClass('addOnClass');
				}
			});
			$(".productCheckbox2").each(function() {
				if(($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) || ($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 && custType == 'ENTERPRISE' && stbModel=='NA') ) {
					$(this).prop('disabled',true);
				} else if((srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) || ($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 && custType == 'ENTERPRISE' && stbModel=='NA') ) {
					$(this).prop('disabled',true);
				} else {
					$(this).addClass('oneTimeClass');
				}
			});
	    } else {
	    	BaseArray.splice(0, BaseArray.length);
	    	$('.stbDiv').hide();
	    	$('.vpnSrvcDiv').hide();
	    	$('.noOfTPConnDiv').hide();
	    	$("#noOfTPConn").attr("disabled", "disabled");
			$("#vpnService").attr("disabled", "disabled");
	    	$("#stbModel").attr("disabled", "disabled");
			$("#stbSerialNo").attr("readonly", "readonly");
			$("#stbLease").attr("disabled", "disabled");
			$("#stbLease1").attr("disabled", "disabled");
	    	$(".productCheckbox").prop('disabled',false);
	    	$(".productCheckbox1").prop('disabled',false);
	    	$(".productCheckbox2").prop('disabled',false);
	    	$(".productCheckbox1").prop('checked',false);
	    	$(".productCheckbox2").prop('checked',false);
	    	$(".iptvselectbox").children('.iptvoption').text('');
	 		$(".iptvselectbox").children('.iptvoption').val('');
	 		iptvPkgArray = [];
	    }
	});
	
	$(document).on('change','.addOnClass',function() {
	    var checked = $(this).is(':checked');
	    var srvcCode = $(this).closest('tr').find('.srvcCode').text();
	    $(".addOnClass").each(function() {
	    	if(checked) {
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 ) {
	    			AddonArray.push("IPTV");
				}
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VPN") != -1 ) {
	    			AddonArray.push("VPN");
				}
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VOIP") != -1 ) {
	    			AddonArray.push("VOIP");
				}
	    		if($(this).closest('tr').find('.addOnClass').is(':checked') && $(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
	   			 	$(this).prop('disabled',false);
		   		} else if($(this).closest('tr').find('.addOnClass').is(':checked') && srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
		   			$(this).prop('disabled',false);
		   		} else if(!$(this).closest('tr').find('.addOnClass').is(':disabled') && $(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) == -1) {
		   			$(this).prop('disabled',false);
		   		} else if(!$(this).closest('tr').find('.addOnClass').is(':disabled') && srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) == -1){
		   			 $(this).prop('disabled',false);
		   		} else {
		   			$(this).prop('disabled',true);
		   		}
	    		$(".oneTimeClass").each(function() {
					if($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
						$(this).prop('disabled',true);
					} else if(srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
						$(this).prop('disabled',true);
					} else {
						$(this).addClass('oneTimeClass1');
					}
				});
	    	} else {
	    		if($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
	    			 $(this).prop('disabled',false);
	    		} else if(srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
	    			 $(this).prop('disabled',false);
	    		}
	    		$(".oneTimeClass").each(function() {
					if($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
						$(this).prop('disabled',false);
					} else if(srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
						$(this).prop('disabled',false);
					}
	    		});
	    		uncheckRemovePkgsFromArray(AddonArray);
	    	}
	    });
	    toDisableOrEnablePkgs();
	});
	
	$(document).on('change','.oneTimeClass',function() {
	    var checked = $(this).is(':checked');
	    var srvcCode = $(this).closest('tr').find('.srvcCode').text();
	    $(".oneTimeClass").each(function() {
	    	if(checked) {
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 ) {
					OneTimeArray.push("IPTV");
				}
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VPN") != -1 ) {
	    			OneTimeArray.push("VPN");
				}
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VOIP") != -1 ) {
	    			OneTimeArray.push("VOIP");
				}
	    		if($(this).closest('tr').find('.oneTimeClass').is(':checked') && $(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
	   			 	$(this).prop('disabled',false);
		   		} else if($(this).closest('tr').find('.oneTimeClass').is(':checked') && srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
		   			$(this).prop('disabled',false);
		   		} else if(!$(this).closest('tr').find('.oneTimeClass').is(':disabled') && $(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) == -1){
		   			 $(this).prop('disabled',false);
		   		} else if(!$(this).closest('tr').find('.oneTimeClass').is(':disabled') && srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) == -1){
		   			 $(this).prop('disabled',false);
		   		} else {
		   			$(this).prop('disabled',true);
		   		}
	    	} else {
	    		if($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
	    			 $(this).prop('disabled',false);
	    		} else if(srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
	    			 $(this).prop('disabled',false);
	    		}
	    		//Code for removing IPTV VPN VOIP from IptvVoipVpnArray
	    		uncheckRemovePkgsFromArray(OneTimeArray);
	    	}
	    });
	    //code for Disabling/Enabling IPTV VOIP VNP Text Boxes
	    toDisableOrEnablePkgs(); 
	});
	
	$(document).on('change','.oneTimeClass1',function() {
	    var checked = $(this).is(':checked');
	    var srvcCode = $(this).closest('tr').find('.srvcCode').text();
	    $(".oneTimeClass1").each(function() {
	    	if(checked) {
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 ) {
					OneTimeArray.push("IPTV");
				}
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VPN") != -1 ) {
	    			OneTimeArray.push("VPN");
				}
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("VOIP") != -1 ) {
	    			OneTimeArray.push("VOIP");
				}
	    		if($(this).closest('tr').find('.oneTimeClass1').is(':checked') && $(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
	    			$(this).prop('disabled',false);
		   		} else if($(this).closest('tr').find('.oneTimeClass1').is(':checked') && srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) != -1) {
		   			$(this).prop('disabled',false);
		   		} else if(!$(this).closest('tr').find('.oneTimeClass1').is(':disabled') && $(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) == -1) {
		   			 $(this).prop('disabled',false);
		   		} else if(!$(this).closest('tr').find('.oneTimeClass1').is(':checked') && srvcCode.indexOf($(this).closest('tr').find('.srvcCode').text()) == -1) {
		   			$(this).prop('disabled',false);
		   		}  else {
		   			$(this).prop('disabled',true);
		   		}
	    	} else {
	    		if($(this).closest('tr').find('.srvcCode').text().indexOf(srvcCode) != -1) {
	    			 $(this).prop('disabled',false);
	    		}
	    		uncheckRemovePkgsFromArray(OneTimeArray);
	    	}
	    });
	    toDisableOrEnablePkgs();  
	});
	
	//Code for removing IPTV VPN VOIP from IptvVoipVpnArray
	function uncheckRemovePkgsFromArray(arrayPassed) {
		if($.inArray('IPTV', arrayPassed) > -1) {
    		$.each(arrayPassed, function(index, value) { 
	    		if("IPTV" == value) {
	    			arrayPassed.splice(index, 1);
	    			return false;
		    	}
	    	});
    	} 
		if($.inArray('VOIP', arrayPassed) > -1) {
    		$.each(arrayPassed, function(index, value) { 
	    		if("VOIP" == value) {
	    			arrayPassed.splice(index, 1);
	    			return false;
		    	}
	    	});
    	} 
		if($.inArray('VPN', arrayPassed) > -1) {
    		$.each(arrayPassed, function(index, value) { 
	    		if("VPN" == value) {
	    			arrayPassed.splice(index, 1);
	    			return false;
		    	}
	    	});
    	}
	}
	
	//code for Disabling/Enabling IPTV VOIP VNP Text Boxes
	function toDisableOrEnablePkgs() {
    	if($.inArray('IPTV', BaseArray) > -1 || $.inArray('IPTV', AddonArray) > -1 || $.inArray('IPTV', OneTimeArray) > -1) {
	    	$('.stbDiv').show();
	    	$("#stbModel").removeAttr("disabled");
			$("#stbSerialNo").removeAttr("readonly");
			$("#stbLease").removeAttr("disabled");
			$("#stbLease1").removeAttr("disabled");
	    } else {
	    	$('.stbDiv').hide();
	    	$("#stbModel").attr("disabled", "disabled");
			$("#stbSerialNo").attr("readonly", "readonly");
			$("#stbLease").attr("disabled", "disabled");
			$("#stbLease1").attr("disabled", "disabled");
	    } 
    	if($.inArray('VOIP', BaseArray) > -1 || $.inArray('VOIP', AddonArray) > -1 || $.inArray('VOIP', OneTimeArray) > -1) {
	    	$(".noOfTPConnDiv").show();
	    	$("#noOfTPConn").removeAttr("disabled");
	    } else {
	    	$(".noOfTPConnDiv").hide();
	    	$("#noOfTPConn").attr("disabled", "disabled");
	    } 
    	if($.inArray('VPN', BaseArray) > -1 || $.inArray('VPN', AddonArray) > -1 || $.inArray('VPN', OneTimeArray) > -1) {
	    	$('.vpnSrvcDiv').show();
	    	$("#vpnService").removeAttr("disabled");
	    } else {
	    	$('.vpnSrvcDiv').hide();
	    	$("#vpnService").attr("disabled", "disabled");
	    }
	}
	
	var iptvselected =[];
	var pkgname = [];
	var pkgcode = [];
	var tenantType = $('#tenantType').val();
	if(tenantType == "LMO") {
		$(document).on('change','.iptvAddonClass,.iptvOneTimeClass',function() {
		    var checked = $(this).is(':checked');
		    if(checked) {
	    		if($(this).closest('tr').find('.coreServiceCode').text().indexOf("IPTV") != -1 ) {
	    			var iptvAgruniqueid = $(this).closest('tr').find('.agruniqueid').text();
					var iptvProdCode = $(this).closest('tr').find('.prodCode').text();
					var iptvTenantCode = $(this).closest('tr').find('.tenantCode').text();
					var iptvProdName = $(this).closest('tr').find('.prodName').text();
					var iptvPkgObject = {
		        		    "iptvAgruniqueid": iptvAgruniqueid,
		        		    "iptvProdCode": iptvProdCode,
		        		    "iptvTenantCode" : iptvTenantCode,
		        		    "iptvProdName" : iptvProdName,
		        		}; 
					iptvPkgArray.push(iptvPkgObject);
	    		}
		    } else {
		    	var iptvProdCode = $(this).closest('tr').find('.prodCode').text();
		    	$.each(iptvPkgArray, function(index, value) { 
		    		if(iptvProdCode == value.iptvProdCode) {
			    		iptvPkgArray.splice(index, 1);
			    		return false;
			    	}
		    	});
		    }
		    AddPkgsToSTBBox();
		});
	} else if(tenantType == "SI") {
		var iptvPkgCodes = $('#iptvPackages').val();
		if(iptvPkgCodes != "" && iptvPkgCodes != undefined) {
			var iptvPkgCodesArray = iptvPkgCodes.split(',');
			$.each(iptvPkgCodesArray, function(index, value) { 
				var iptvPkgObject = {
						"iptvProdCode" : value.split('^')[0],
						 "iptvProdName" : value.split('^')[1],
				};
				iptvPkgArray.push(iptvPkgObject);
			});
			AddPkgsToSTBBox();
		}
	}
	
	//code for add iptv package names to multi-stb boxes.
	function AddPkgsToSTBBox(){
		var $iptvSelect = $('.iptvSrvcCodes');
		if(iptvPkgArray.length > 0) {
			$iptvSelect.removeAttr("disabled");
	    	$('.iptvoption').val("");
	    	$('.iptvoption').text("");
	    	iptvselected = [];
	    	pkgname = [];
	    	pkgcode = [];
	    	var $select = $('.iptvhiddendiv');
	 		$select.find('li').remove();
	 		$.each(iptvPkgArray, function(key,val) {              
	 		   $('<li style="list-style: none;"><input type="checkbox" class="multiStbCheckbox" checked="checked" value="'+val.iptvProdCode+'" />'+val.iptvProdName+'</li>').appendTo($select);
	 		   pkgname.push(val.iptvProdName);
	 		   pkgcode.push(val.iptvProdCode);
	 		});
	 		$(".iptvselectbox").children('.iptvoption').text(pkgname);
	 		$(".iptvselectbox").children('.iptvoption').val(pkgcode);
	    } else {
	    	$(".iptvselectbox").children('.iptvoption').text('');
	 		$(".iptvselectbox").children('.iptvoption').val('');
	    	$(".iptvSrvcCodes").attr("disabled", "disabled");
	    }
	}
	
	// for Multiple STB IPTV checkboxs(if checked or unchecked)
	var indexclick;
	$(document).on('click','.iptvselectbox',function() {
		indexclick = $(this).index('.iptvselectbox');
		$(".iptvhiddendiv:eq("+indexclick+")").slideToggle('fast');
		$(".iptvhiddendiv").not(".iptvhiddendiv:eq("+indexclick+")").hide();
		});
		
		$(document).on('change','.iptvhiddendiv input[type="checkbox"]',function() {
			iptvselected = [];
			var arr = []; // for different div different array
			if($(".iptvselectbox:eq("+indexclick+")").children('.iptvoption').val() != "") {
				arr = $(".iptvselectbox:eq("+indexclick+")").children('.iptvoption').val().split(',');
				$.each(arr, function(index, value) { 
					iptvselected.push(arr[index]);
				});
			}
			
		  var title = $(this).closest('.iptvhiddendiv').find('input[type="checkbox"]').val(),
		    title = $(this).val();

		  if ($(this).is(':checked')) {
			  iptvselected.push(title);
			  $(".iptvselectbox:eq("+indexclick+")").children('.iptvoption').val(iptvselected);
		  } else {
			  $.each(iptvselected, function(index, value) { 
			    	if(value == title) {
			    		iptvselected.splice(index, 1);
			    	}
		    	});
			  $(".iptvselectbox:eq("+indexclick+")").children('.iptvoption').val(iptvselected);
		  }
		//TO show Package Names in select box
		  var arrName = [];
		  $.each(iptvselected, function(index, selValue) {
			  $.each(iptvPkgArray, function(ind, pkgValue) {
				  if(selValue == pkgValue.iptvProdCode) {
					  arrName.push(pkgValue.iptvProdName);
				  }
			  });
		  });
		  $(".iptvselectbox:eq("+indexclick+")").children('.iptvoption').text(arrName);
		  if(iptvselected.length == 0){
			  $(".iptvselectbox:eq("+indexclick+")").children('.iptvoption').text("--Select--");
		  }
	});
	
	$(document).on('keypress','.numbersOnly',function(event) {
		if(event.which == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46) {
			return true
		}
	    if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	    	 return false;
	    }
	    var text = $(this).val();
	    if ((text.indexOf('.') != -1) && (text.substring(text.indexOf('.')).length > 2)) {
	    	 return false;
	    }
	});
	
	$(".number").keypress(function (e) {
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	    	 return false;
	     }
	   });
	
	$('#paidAmount').bind("copy paste",function(e) {
	    e.preventDefault();
	});
		        
	$('.specialCharacters').keyup(function() {
		var yourInput = $(this).val();
		re = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar) {
			var no_spl_char = yourInput.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
			$(this).val(no_spl_char);
		}
	});
	
	$('.addressValidation').bind('keyup', function() { 
	    $(this).val(function(i, val) {
	       // return val.replace(/[^A-Za-z0-9/#&-\s]/gi,''); 
	    });
	});
	
	$('.charectersonly').bind('keyup', function() { 
	    $(this).val(function(i, val) {
	        return val.replace(/[^a-z\s]/gi,''); 
	    });
	});
	
	$("#lockPeriod").numeric({negative : false}).keyup(function (e) {
		   var val = $(this).val();
		   while (val.substring(0, 1) === '0') {   //First character is a '0'.
		       val = val.substring(1);             //Trim the leading '0'
		   }
		   $(this).val(val);   //update input with new value
		});

	$("#getAadharDetails").click(function(event) {
		$("#cafVillage").empty();
		$("#district").empty();
		$("#mandal").empty();
		var aadharNo = $('#aadharNumber').val();
        $.getJSON('getAadharDetails',{aadharNumber : aadharNo},function(aadharDetails) {
        	if(jQuery.isEmptyObject(aadharDetails)) {
        		$('.aadharError').text("Couldn't Retrive Information from Aadhar Server.");
        	} else {
        		$('.aadharError').text("");
        		var name = aadharDetails.name;
        		lastName = name.substr(0,name.indexOf(' '));
        		firstName = name.substr(name.indexOf(' ')+1);
            	/*var items = name.split(' ');
                lastName = "";
                firstName = "";
                for (var i = 0; i <= items.length - 1; i++) {
                	if (i > 0) {
                		firstName += items[i];
                	} else {
                		lastName += items[i]; 
                		}
                }*/
                if(aadharDetails.gender == 'M') {
                	$('#titleLovName').val('Mr.');
                } else {
                	$('#titleLovName').val('Mrs.');
                }
	               $('#fatherName').val(aadharDetails.careof);
	               $('#indDob').val(aadharDetails.dob);
	               $('#firstName').val(firstName);
	               $('#lastName').val(lastName);
	               $('#gender').val(aadharDetails.gender);
	               $('#pinCode').val(aadharDetails.pincode);
	               $('#mobileNo').val(aadharDetails.phoneNo);
	               $('#address1').val(aadharDetails.buildingName);
	               $('#address2').val(aadharDetails.street);
	               $('#locality').val(aadharDetails.village_name);
	               $('<option>').val(aadharDetails.district).attr('selected', true).text(aadharDetails.district_name).appendTo($('#district'));
	               $('<option>').val(aadharDetails.mandal).attr('selected', true).text(aadharDetails.mandal_name).appendTo($('#mandal'));
	               $('<option>').val(aadharDetails.village).attr('selected', true).text(aadharDetails.village_name).appendTo($('#cafVillage'));
        	}
        });
     });
	
	$(document).on('click','.orgName',function() {
		var i = $(".orgName").index(this);
		var parentCustCode = $(this).closest('tr').find('.custCode').text();
		var count2 = parseInt($(this).closest('tr').find('.count').text());
		var tenantType = $(this).closest('tr').find('.tenantType').text();
		var APSFL = 'APSFL'; 
		if(parentCustCode!="") {
			$.ajax({
				type : "GET",
				url : "viewEntNodes",
				dataType : "json",
				data : "&custId="+ parentCustCode,
				success : function(response) {
					for (index = 0; index < response.length; index++) {
						var currentDate = null;
			            var loopObj = response[index];
			            var orgName =  loopObj["custName"];
			            var paymentResposible = loopObj["pmntliabilityflag"];
			            var paymentCustCode = loopObj["parentcustcode"];
			            var custCode =  loopObj["custCode"];
			            var address =  loopObj["address1"];
			            var stdCode =  loopObj["stdCode"] != null ? loopObj["stdCode"] : "NA";
			            var landLine = loopObj["landLine1"] != null ? loopObj["landLine1"] : "NA";
			            var billCycle = loopObj["billfreqLov"];
			            var custType = loopObj["custType"];
			            var custTypeLov = loopObj["custTypeLov"];
			            var officeTypeLov = loopObj["officeTypeLov"];
			            var pocName = loopObj["pocName"];
			            var custId = loopObj["custId"];
			            var orgCode = loopObj["regnCode"];
			            var emailId = loopObj["email1"];
			            var status = loopObj["status"];
			            if(loopObj["dateofinc"] != null) {
			            	 var dob = new Date(loopObj["dateofinc"]);
			            	 var twoDigitMonth = ((dob.getMonth().length+1) === 1)? (dob.getMonth()+1) : '0' + (dob.getMonth()+1);
					         currentDate = dob.getFullYear() + "-" + twoDigitMonth + "-" + dob.getDate();
			            }
			            count1 = count1+1;
			            var img_src= '<img src="./resources/images/apf_add.png" />';
			            var formData = "";
			            if(status == '0') {
                        	  formData ='<form style="float:left; padding-left:5px;" name="editEntNodesForm" action="#" class="editEntNodesForm" method="post">';
                        	  formData += '<input type="hidden" name="customerCode" value="'+custCode+'" />';
                        	  formData += '<input type="hidden" name="modify" value="1" />';
                        	  formData += '<span class="editEntNodeCustomer" style="cursor: pointer;" title="Edit Organization Nodes"><img src="./resources/images/apf_edit.png"></span></form></td>';
			            }
			            if(tenantType == "APSFL") {
			            	$('.treegrid-'+count2+'').treegrid('add', ['<tr class="treegrid-'+count1+'"><td class="orgName" style="cursor: pointer;"><a href="#">'+orgName+'</a></td>'+
					                                                      '<td class="custCode" style="display: none;">'+custCode+'</td> <td>'+orgCode+'</td> <td class="tenantType" style="display: none;">'+tenantType+'</td> <td>'+custType+'</td> <td>'+officeTypeLov+'</td> <td style="word-break: break-all;">'+pocName+'</td> <td>'+stdCode+'-'+landLine+'</td> '+
					                                                      '<td align="center"><form style="float:left;" name="showEntCustomerForm" action="#" class="showEntCustomerForm" method="post">'+
					                                                      '<input type="hidden" name="custType" value="'+custType+'" />'+
					                                                      '<input type="hidden" name="custTypeLov" value="'+custTypeLov+'" />'+
					                                                      '<input type="hidden" name="custCode" value="'+custCode+'" />'+
					                                                      '<input type="hidden" name="orgName" value="'+orgName+'" />'+
					                                                      '<input type="hidden" name="billCycle" value="'+billCycle+'" />'+
					                                                      '<input type="hidden" name="paymentResponsible" value="'+paymentResposible+'" />'+
					                                                      '<input type="hidden" name="dateOfIncorporation" value="'+currentDate+'" />'+
					                                                      '<input type="hidden" name="custId" value="'+custId+'"  />'+
					                                                      '<input type="hidden" name="uidNo" value="'+orgCode+'"  />'+
					                                                      '<input type="hidden" name="pocName" value="'+pocName+'"  />'+
					                                                      '<input type="hidden" name="emailId" value="'+emailId+'"  />'+
					                                                      '<input type="hidden" name="officeTypeLov" value="'+officeTypeLov+'" />'+
					                                                      '<input type="hidden" id="tenantType" value="'+tenantType+'" />'+
																		  '<span class="showEntCustomer" style="cursor: pointer;" title="BulkCafs Upload"><img src="./resources/images/upload.png"></span></form>'+
					                                                      '<form style="float:left; padding-left:5px;" name="addNodeForm" action="#" class="addNodeForm" method="post">'+
					                                                      '<input type="hidden" name="custType" value="'+custType+'" />'+
					                                                      '<input type="hidden" name="custTypeLov" value="'+custTypeLov+'" />'+
					                                                      '<input type="hidden" name="customerCode" value="'+custCode+'" />'+
					                                                      '<input type="hidden" name="orgName" value="'+orgName+'" />'+
					                                                      '<input type="hidden" name="billCycle" value="'+billCycle+'" />'+
					                                                      '<input type="hidden" name="officeTypeLov" value="'+officeTypeLov+'" />'+
					                                                      '<input type="hidden" name="orgCode" value="'+orgCode+'"  />'+
					                                                      '<span class="addNode" style="cursor: pointer;" title="Add Node">'+img_src+'</span></form>'+
					                                                      '<form style="float:left; padding-left:5px;" name="showEntNodesForm" action="#" class="showEntNodesForm" method="post">'+
					                                                      '<input type="hidden" name="customerCode" value="'+custCode+'" />'+
					                                                      '<input type="hidden" name="custType" value="'+custType+'" />'+
					                                                      '<input type="hidden" name="custTypeLov" value="'+custTypeLov+'" />'+
					                                                      '<input type="hidden" name="custCode" value="'+custCode+'" />'+
					                                                      '<input type="hidden" name="orgName" value="'+orgName+'" />'+
					                                                      '<input type="hidden" name="billCycle" value="'+billCycle+'" />'+
					                                                      '<input type="hidden" name="paymentResponsible" value="'+paymentResposible+'" />'+
					                                                      '<input type="hidden" name="dateOfIncorporation" value="'+currentDate+'" />'+
					                                                      '<input type="hidden" name="custId" value="'+custId+'"  />'+
					                                                      '<input type="hidden" name="uidNo" value="'+orgCode+'"  />'+
					                                                      '<input type="hidden" name="pocName" value="'+pocName+'"  />'+
					                                                      '<input type="hidden" name="emailId" value="'+emailId+'"  />'+
					                                                      '<input type="hidden" name="bulkUploadFlag" value="SingalCaf" />'+
					                                                      '<input type="hidden" name="officeTypeLov" value="'+officeTypeLov+'" />'+
					                                                      '<input type="hidden" name="custTypeLov" value="'+custTypeLov+'" />'+
					                                                      '<input type="hidden" name="custType" value="'+custType+'" />'+
					                                                      '<span class="showEntNodes" style="cursor: pointer;" title="Add CAF"><img src="./resources/images/apf_add.png"></span></form>'+
					                                                      ''+formData+' '+ 
					                                                      ' <td class="count" style="display: none;">'+count1+'</td></tr>']);
			            } else {
			            	$('.treegrid-'+count2+'').treegrid('add', ['<tr class="treegrid-'+count1+'"><td class="orgName" style="cursor: pointer;"><a href="#">'+orgName+'</a></td>'+
					                                                      '<td class="custCode" style="display: none;">'+custCode+'</td> <td>'+orgCode+'</td> <td class="tenantType" style="display: none;">'+tenantType+'</td> <td>'+custType+'</td> <td>'+officeTypeLov+'</td> <td style="word-break: break-all;">'+pocName+'</td> <td>'+stdCode+'-'+landLine+'</td> '+
					                                                      '<td align="center"><form style="float:left;" name="showEntCustomerForm" action="#" class="showEntCustomerForm" method="post">'+
					                                                      '<input type="hidden" name="custType" value="'+custType+'" />'+
					                                                      '<input type="hidden" name="custTypeLov" value="'+custTypeLov+'" />'+
					                                                      '<input type="hidden" name="custCode" value="'+custCode+'" />'+
					                                                      '<input type="hidden" name="orgName" value="'+orgName+'" />'+
					                                                      '<input type="hidden" name="billCycle" value="'+billCycle+'" />'+
					                                                      '<input type="hidden" name="paymentResponsible" value="'+paymentResposible+'" />'+
					                                                      '<input type="hidden" name="dateOfIncorporation" value="'+currentDate+'" />'+
					                                                      '<input type="hidden" name="custId" value="'+custId+'"  />'+
					                                                      '<input type="hidden" name="uidNo" value="'+orgCode+'"  />'+
					                                                      '<input type="hidden" name="pocName" value="'+pocName+'"  />'+
					                                                      '<input type="hidden" name="emailId" value="'+emailId+'"  />'+
					                                                      '<input type="hidden" name="officeTypeLov" value="'+officeTypeLov+'" />'+
					                                                      '<input type="hidden" id="tenantType" value="'+tenantType+'" />'+
																		  '<span class="showEntCustomer" style="cursor: pointer;" title="Add Customer"><img src="./resources/images/apf_add.png"></span></form>'+
					                                                      '<form style="float:left; padding-left:5px;" name="addNodeForm" action="#" class="addNodeForm" method="post">'+
					                                                      '<input type="hidden" name="custType" value="'+custType+'" />'+
					                                                      '<input type="hidden" name="custTypeLov" value="'+custTypeLov+'" />'+
					                                                      '<input type="hidden" name="customerCode" value="'+custCode+'" />'+
					                                                      '<input type="hidden" name="orgName" value="'+orgName+'" />'+
					                                                      '<input type="hidden" name="billCycle" value="'+billCycle+'" />'+
					                                                      '<input type="hidden" name="officeTypeLov" value="'+officeTypeLov+'" />'+
					                                                      '<input type="hidden" name="orgCode" value="'+orgCode+'"  />'+
					                                                      '<span class="addNode" style="cursor: pointer;" title="Add Node">'+img_src+'</span></form>'+
					                                                      ''+formData+' '+ 
					                                                      '<td class="count" style="display: none;">'+count1+'</td></tr>']);
			            }
			            
			        }
				}
			});
		}
		$(this).closest('tr').find('td').removeClass( "orgName");
	});
	
	$(document).on('click','.orgNames',function() {
		var i = $(".orgNames").index(this);
		var parentCustCode = $(this).closest('tr').find('.custCode').text();
		var count2 = parseInt($(this).closest('tr').find('.count').text());
		var tenantType = $(this).closest('tr').find('.tenantType').text();
		var APSFL = 'APSFL'; 
		if(parentCustCode!="") {
			$.ajax({
				type : "GET",
				url : "viewEntNodes",
				dataType : "json",
				data : "&custId="+ parentCustCode,
				success : function(response) {
					for (index = 0; index < response.length; index++) {
						var currentDate = null;
			            var loopObj = response[index];
			            var orgName =  loopObj["custName"];
			            //var paymentResposible = loopObj["pmntliabilityflag"];
			            var paymentCustCode = loopObj["parentcustcode"];
			            var custCode =  loopObj["custCode"];
			            //var address =  loopObj["address1"];
			            var stdCode =  loopObj["stdCode"] != null ? loopObj["stdCode"] : "NA";
			            var landLine = loopObj["landLine1"] != null ? loopObj["landLine1"] : "NA";
			            //var billCycle = loopObj["billfreqLov"];
			            var custType = loopObj["custType"];
			            //var custTypeLov = loopObj["custTypeLov"];
			            var officeTypeLov = loopObj["officeTypeLov"];
			            var pocName = loopObj["pocName"];
			            var custId = loopObj["custId"];
			            var orgCode = loopObj["regnCode"];
			            //var emailId = loopObj["email1"];
			            count1 = count1+1;
			            var img_src= '<img src="./resources/images/apf_view.png" />';
			            var formData = "";
		            	$('.treegrid-'+count2+'').treegrid('add', ['<tr class="treegrid-'+count1+'"><td class="orgNames" style="cursor: pointer;"><a href="#">'+orgName+'</a></td>'+
				                                                      '<td class="custCode" style="display: none;">'+custCode+'</td><td class="custId" style="display: none;">'+custId+'</td> <td>'+orgCode+'</td> <td class="tenantType" style="display: none;">'+tenantType+'</td> <td>'+custType+'</td> <td>'+officeTypeLov+'</td> <td style="word-break: break-all;">'+pocName+'</td> <td>'+stdCode+'-'+landLine+'</td> '+
				                                                      '<td align="center">'+
				                                                      '<input type="hidden" name="custId" value="'+custId+'" />'+
				                				                	  '<span style="cursor:pointer" class="viewEntCustDtls" style="cursor: pointer;" data-toggle="modal" data-target="#myModal" title="View Caf Details">'+img_src+'</span>'+
				                                                      '</td>'+
				                                                      ' <td class="count" style="display: none;">'+count1+'</td></tr>']);
					}
				}
			});
		}
		$(this).closest('tr').find('td').removeClass( "orgNames");
	});
	
	$(document).on('click','.viewcafDtls',function() {
	var index = $(".viewcafDtls").index(this);
	var cafNo = $(this).closest('tr').find('.cafNo').text();
	$.ajax({
		async:false,
   	    type: 'GET', 
   	    url: 'getCafInfo', 
   	    data: {cafNo:cafNo},
   	    contentType: 'application/json',
   	    success: function(CafsVO) { 
   	    	if(CafsVO.length > 0) {
			    $("#cafDetailsTable> tbody").html("");
			    $.each(CafsVO,function(idx,obj) {
			    	var tdData =  ' <td style="word-break:break-all;"> '
						$.each(obj.stbSrlNoMacAddr, function(key, value) {
							tdData+= "<table><tr><td>"+key+" - "+value+"</td></tr></table>";
						});
					tdData+="</td>";
					$("#cafDetailsTable> tbody").append('<tr>'
						    +'<td>' +obj.cpeslNo+ '</td>'
				            +'<td>' +obj.cpeMacAddr+ '</td>'
				            +'<td>' +obj.cpeAddr+ '</td>'
				            + tdData+ '<td>' +obj.cafPhoneNo+'</td> </tr>');
					
			    });
		 		} else { 
		 			$("#cafDetailsTable> tbody").html("");
		 			$('#cafDetailsTable> tbody').append('<tr><td colspan="7" style="text-align:center"><h2>No Records Found</h2></td></tr>');
	 			}
   	    	}
		});
	});
	
	$(document).on('change','#status',function() {
		var multiStatusVal = $(this).val();
		if(multiStatusVal == "2" || multiStatusVal == "6") {
			$("#SIPopId").removeAttr("disabled");
			$("#SIPopId").removeClass('disable');
		} else {
			$("#SIPopId").attr("disabled","disabled");
			$("#SIPopId").addClass('disable');
		}
	});
	
	$(document).on('click','.downloadBill',function() {
		var i = $(".downloadBill").index(this);
		$(".downloadBillForm:eq("+i+")").attr('action',"downLoadBill");
		$(".downloadBillForm:eq("+i+")").submit();
	});
	
	$("#searchButton").click(function(event){
		$("#multiActionForm").attr('action',"searchCafDetails");
		$("#multiActionForm").submit();
	});
	
	$("#searchButton").click(function(event){
		alert('cfhjxcfhjdf');
		$("#multiActionForm1").attr('action',"searchCafDetails");
		$("#multiActionForm1").submit();
	});
	
	$(document).on('click','.addNode',function() {
		var i = $(".addNode").index(this);
		$(".addNodeForm:eq("+i+")").attr('action',"nodeForm");
		$(".addNodeForm:eq("+i+")").submit();
	});
	
	$(document).on('click','.showEntCustomer',function() {
		var i = $(".showEntCustomer").index(this);
		var tenantType = $('#tenantType').val();
		if( tenantType== 'APSFL') {
			$(".showEntCustomerForm:eq("+i+")").attr('action',"bulkUploadCafs");
		} else {
			$(".showEntCustomerForm:eq("+i+")").attr('action',"showentcustomer");
		}
		$(".showEntCustomerForm:eq("+i+")").submit();
	});
	
	$(document).on('click','.viewEntCustomer',function() {
		var i = $(".viewEntCustomer").index(this);
		$(".viewEntCustomerForm:eq("+i+")").attr('action',"bulkUploadCafs");
		$(".viewEntCustomerForm:eq("+i+")").submit();
	});
	
	$(document).on('click','.showEntNodes',function() {
		var i = $(".showEntNodes").index(this);
		$(".showEntNodesForm:eq("+i+")").attr('action',"bulkUploadCafs");
		$(".showEntNodesForm:eq("+i+")").submit();
	});
	
	$(document).on('click','.editEntCustomer',function() {
		var i = $(".editEntCustomer").index(this);
		$(".editEntCustomerForm:eq("+i+")").attr('action',"viewEntCustomer");
		$(".editEntCustomerForm:eq("+i+")").submit();
	});
	
	$(".viewEntNodes").click(function(event) {
		var i = $(".viewEntNodes").index(this);
		$(".viewEntNodeForm:eq("+i+")").attr('action',"viewEntNodes");
		$(".viewEntNodeForm:eq("+i+")").submit();
	});
	
	$(document).on('click','.editEntNodeCustomer',function() {
		var i = $(".editEntNodeCustomer").index(this);
		$(".editEntNodesForm:eq("+i+")").attr('action',"showEntNodes");
		$(".editEntNodesForm:eq("+i+")").submit();
	});
	
	$(document).on('click','.addCafs',function() {
		ajaxindicatorstart('Please Wait...');
		var i = $(".addCafs").index(this);
		$(".addCafForm:eq("+i+")").attr('action',"createcustomer");
		$(".addCafForm:eq("+i+")").submit();
	});
	
	$(".viewCustomer").click(function(event) {
		ajaxindicatorstart('Please Wait...');
		var i = $(".viewCustomer").index(this);
		$(".viewCustomerForm:eq("+i+")").attr('action',"cafdetails");
		$(".viewCustomerForm:eq("+i+")").submit();
	});
	
	$("#createCustomer").click(function(event) {
		ajaxindicatorstart('Please Wait...');
		$("#createCustomerForm").attr('action',"createcustomer");
		$("#createCustomerForm").submit();
	});
	
	$("#createCustomerButton").click(function(event) {
		$("#customerform").attr('action',"createCustomerCafDetails");
		$("#customerform").submit();
	});
	
	$("#paymentButton").click(function(event){
		var row = $("#paymentTable input[name=checkStatus]:checked").closest('tr');
		var cafNo = row.find('.cafNo').text();
		var district = row.find('.district').text();
		var mandal = row.find('.mandal').text();
		var village = row.find('.village').text();
		var billCycle = row.find('.billCycle').text();
		var stbModel = row.find('.stbModel').text();
		if(cafNo != "") {
			$('#cafNumber').val(cafNo);
			$('#cafDistrict').val(district);
			$('#cafMandal').val(mandal);
			$('#cafVillage').val(village);
			$('#billCycle').val(billCycle);
			$('#stbProfileId').val(stbModel);
		}
		ajaxindicatorstart('Please Wait...');
		$("#multiActionForm").attr('action',"getSearchCafDetails");
		$("#multiActionForm").submit();
	});
	
	$("#monthlyPaymentButton").click(function(event) {
		$("#makeMonthlyPaymentForm").attr('action',"createPaymentDetails");
		$("#makeMonthlyPaymentForm").submit();
	});
	
	$(document).on('click','.packagesButton',function() {
		ajaxindicatorstart('Please Wait...');
		var i = $(".packagesButton").index(this);
		$(".packagesButtonform:eq("+i+")").attr('action',"getSearchPackageDetails");
		$(".packagesButtonform:eq("+i+")").submit();
	});
	
	//CafCustomer Modify Button
	$(document).on('click','.CafCustModifyButton',function() {
		ajaxindicatorstart('Please Wait...');
		var i = $(".CafCustModifyButton").index(this);
		$(".CafCustModifyButtonform:eq("+i+")").attr('action',"getCafCustDetailsToModify");
		$(".CafCustModifyButtonform:eq("+i+")").submit();
	});
	
	//CafCustomer Modify Button
	$(document).on('click','.APSFLCafModifyButton',function() {
		ajaxindicatorstart('Please Wait...');
		var i = $(".APSFLCafModifyButton").index(this);
		$(".CafCustModifyButtonform:eq("+i+")").attr('action',"getCafDetailsToAPSFLCafModify");
		$(".CafCustModifyButtonform:eq("+i+")").submit();
	});
	
	//addPackages   Button
	$(document).on('click','.ActiveButton',function() {
		ajaxindicatorstart('Please Wait...');
		var i = $(".ActiveButton").index(this);
		$(".ActiveButtonform:eq("+i+")").attr('action',"SearchForAddPackageDetails");
		$(".ActiveButtonform:eq("+i+")").submit();
	});
	
	$(document).on('click','.removePkg', function() {
		$('#pkgerror').text("");
		$('#PkgSubmitButton').hide();
		$("#removePkgTable> tbody").html("");
	    var index = $(".removePkg").index(this);
		var cafNo = $(".cafNo:eq("+index+")").val();
		$.ajax({
			async:false,
	   	    type: 'GET', 
	   	    url: 'getProdInfoByCafNo', 
	   	    data: {cafNo:cafNo},
	   	    contentType: 'application/json',
	   	    success: function(TerminatePkgBO) { 
	   	    	if(TerminatePkgBO.length > 0) {
	   	    		$('#PkgSubmitButton').show();
				    $("#removePkgTable> tbody").html("");
				    $.each(TerminatePkgBO, function(idx, obj) {
				    	var actDate = obj.prodDate != null ? obj.prodDate : "";
				    	var sno=idx+1;
						$("#removePkgTable> tbody").append('<tr>'
								+'<td>' +obj.cafNo+'</td>'
								+'<td>' +obj.prodName+'</td>'
					            +'<td>' +obj.stbSrlNo+'</td>'
					            +'<td>' +actDate+'</td>'
					            +'<td hidden="hidden" class="prodCafNo">' +obj.prodCafNo+'</td>'
					            +'<td hidden="hidden" class="prodCode">' +obj.prodCode+'</td>'
					            +'<td hidden="hidden" class="stbCafNo">' +obj.stbCafNo+'</td>'
					            +'<td hidden="hidden" class="nwSubscriberCode">' +obj.nwSubscriberCode+'</td>'
					            +'<td><input type="checkbox" name="ch1" value = "' +index+'" /></td></tr>');
				    });
		 		} else { 
		 			$("#removePkgTable> tbody").html("");
		 			$('#removePkgTable> tbody').append('<tr><td colspan="7" style="text-align:center"><h2>No Records Found</h2></td></tr>');
		 		}
	   	    }
		});
	})
		
	$("#PkgSubmitButton").click(function() {
	$('#pkgerror').text("");
	var pkgDetailsList = [];
	var selected = 0;
	var checkedStb = 0;
	$('.pkgclass').find('tr').each(function() {
		var row = $(this);
		if (row.find('input[type="checkbox"]').is(':checked')) {
			selected++;
			var prodCafNo = row.find('.prodCafNo').text();
			var prodcode = row.find('.prodCode').text();
			var stbCafNo = row.find('.stbCafNo').text();
			var nwSubscriberCode = row.find('.nwSubscriberCode').text();
			var pkgObj = {
       				"prodCafNo" : prodCafNo,
	       			"prodCode"  : prodcode,
	    		    "stbCafNo" : stbCafNo,
	    		    "nwSubscriberCode" : nwSubscriberCode,
			};
			pkgDetailsList.push(pkgObj);
		} 
	});
		
	if(selected == 0) {
		$('#pkgerror').text("Please check atleast one Check Box");
	} else {
		pkgDetailsList = JSON.stringify(pkgDetailsList);
	 	$.ajax({ 
    	     type: 'POST', 
    	     url: 'terminateAddonIPTVPackage', 
    	     data: pkgDetailsList,
    	     contentType: 'application/json',
    	     success: function(response) { 
    	    	 if(response == "success"){
    	    		 if(window.confirm('Termination is successfully completed...!!!'))
 	    		   	   	$("#myModal .close").click();
    	    	 	}
    	     	},
	 		});
		}
	});
			
	$(".changePkgRadio").click(function(){
		$('#productError').text('');
	});
	
	//To Show STB BOXES DIV for AddPackages
	$(".addPackageRadio").click(function(){
		$('#productError').text('');
		var i = $(".addPackageRadio").index(this);
		var coreSrvcCode = $(".coreServiceCode:eq("+i+")").text();
		var prodcode = $(".prodCode:eq("+i+")").text();
		if(coreSrvcCode.indexOf("IPTV") != -1) {
			$("#STBBoxesCheck").show();
			var iptvCount = $('#iptvCount').val();
			for(var count = 0; count < iptvCount;  count++) {
				$('#srvCodeDiv'+count).append('<input type="hidden" checked="checked" name="iptvBoxList['+count+'].iptvSrvcCodes" value = "'+prodcode+'">');
			}
		} else {
			$('.srvCodeDiv').val('');
			$('.stbCafNosChecked').val('');
			$("#STBBoxesCheck").hide();
		}
	});
	
	// AddPackagesSubmitButton ::
	$("#AddPackagesSubmitButton, #changePackagesSubmitButton").click(function() {
		var pkgTypeflag;
		var agruniqueids = [];
		var tenantCodes = [];
		var prodCodes = [];
		var coreSrvcCodes = "";
		var selected = 0;
		var checkedStb = 0;
		$('.prodclass').find('tr').each(function() {
			var row = $(this);
			//if (row.find('input[type="checkbox"]').is(':checked')) {
			if (row.find('input[type="radio"]').is(':checked')) {
				selected++;
				pkgTypeflag = row.find('.prodType').text();
				var agruniqueid = row.find('.agruniqueid').text();
				var prodCode = row.find('.prodCode').text();
				var tenantCode = row.find('.tenantCode').text();
				coreSrvcCodes = row.find('.coreServiceCode').text();
				agruniqueids.push(agruniqueid);
				prodCodes.push(prodCode);
				tenantCodes.push(tenantCode);
				$("#agruniqueid").val(agruniqueids);
				$("#tenantCode").val(tenantCodes);
				$("#prodCode").val(prodCodes);
				$("#pkgCoreSrvcCode").val(coreSrvcCodes);
				$("#flag").val(pkgTypeflag);
			} 
		});
		
		if(coreSrvcCodes.indexOf("IPTV") != -1 ) {
			var iptvCount = $('#iptvCount').val();
			for(var count = 0; count < iptvCount;  count++) {
				if($('#stbSerialNoChecked'+count).is(':checked')){
					checkedStb++;
				}
			}
		}
		
		if(selected == 0 || ((coreSrvcCodes.indexOf("IPTV") != -1)  && checkedStb == 0)) {
			if(pkgTypeflag == "B") {
				$('#productError').text("Please select package");
			} else if(pkgTypeflag == "A" || pkgTypeflag == "O" ) {
				$('#productError').text("Please select package and one STB Box");
			} else {
				$('#productError').text("Please select package");
			}
		} else {
			ajaxindicatorstart('Please Wait...');
			$('#productError').text("");
			$("#Addpackagesform").attr('action',"createPackages");
			$("#Addpackagesform").submit();
		}
	});
	
	//To Show STB BOXES DIV for ChangePackages
	/*$(".changePkgRadio").click(function(){
			$("#STBBoxesCheck").show();
	});*/
	
	/* Check Duplicate Registration Number for create Organization */ 
	$('#rootUidNo').change(function() {
		var regNo = $(this).val();
		if(regNo != "") {
			$.ajax({
				type : "GET",
				url : "checkDuplicateRegNo",
				data : "&regNo="+ regNo,
				success : function(response) {
					if(response == "true") {
						$('#errorUidNo').text("The Registration No/VAT No/TAN No is already exists.Please try another Registration No/VAT No/TAN No");
						$('#rootUidNo').val("");
					} else if("false") {
						$('#errorUidNo').text("");
					}
				}
			});
		}
	});
	
	/* Check Duplicate APSFL Unique Number for CAF Upload */ 
	$('#apsflUniqueId').change(function() {
		var apsflUniqueId = $(this).val();
		if(apsflUniqueId != "") {
			$.ajax({
				type : "GET",
				url : "checkExistAPSFLCode",
				data : "&apsflUniqueId="+ apsflUniqueId,
				success : function(response) {
					if(response) {
						$('#apsflUniqueIdError').text("");
					} else {
						$('#apsflUniqueIdError').text("Duplicate APSFL Code");
						$('#apsflUniqueId').val("");
					}
				}
			});
		}
	});
	
	/* Get Mandals By District Id */ 
	$('#district').change(function() {
		var districtId = $(this).val();
		if(districtId!="") {
			$.ajax({
				type : "GET",
				url : "getMandalsByDistrictId",
				dataType : "json",
				data : "&districtId="+ districtId,
				success : function(response) {
					var $select = $('#mandal');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.mandalSlno).text(val.mandalName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get Mandals By APSFL District Id */ 
	$('#apsflDistrict').change(function() {
		var districtId = $(this).val();
		districtId = districtId.split(",")[0];
		if(districtId!="") {
			$.ajax({
				type : "GET",
				url : "getMandalsByDistrictId",
				dataType : "json",
				data : "&districtId="+ districtId,
				success : function(response) {
					var $select = $('#apsflMandal');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {
						var value = val.mandalSlno+','+val.mandalName;
		        	   $('<option>').val(value).text(val.mandalName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get SILMO Mandals By SILMO District Name */ 
	$('#SIDistrict').change(function() {
		var district = $(this).val();
		if(district!="") {
			$.ajax({
				type : "GET",
				url : "getSILMOMandals",
				dataType : "json",
				data : "&district="+ district,
				success : function(response) {
					var $select = $('#SIMandal');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.mandalName).text(val.mandalName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get POP Mandals By District Id */ 
	$('#popDistrict').change(function() {
		var districtId = $(this).val();
		if(districtId!="") {
			$.ajax({
				type : "GET",
				url : "getMandalsByDistrictId",
				dataType : "json",
				data : "&districtId="+ districtId,
				success : function(response) {
					var $select = $('#popMandal');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.mandalSlno).text(val.mandalName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get Mandals By District Id */ 
	$('#blDistrict').change(function() {
		var districtId = $(this).val();
		if(districtId!="") {
			$.ajax({
				type : "GET",
				url : "getMandalsByDistrictId",
				dataType : "json",
				data : "&districtId="+ districtId,
				success : function(response) {
					var $select = $('#blMandal');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.mandalSlno).text(val.mandalName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get Villages By District Id and Mandal Id*/
	$('#mandal').change(function() {
		var districtId = $('#district').val();
		var mandalId = $('#mandal').val();
		if(districtId!="" && mandalId !="") {
			$.ajax({
				type : "GET",
				url : "getVillagesByDistrictIdAndMandalId",
				dataType : "json",
				data : "&districtId="+ districtId+"&mandalId="+mandalId,
				success : function(response) {
					var $select = $('#village');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.villageSlno).text(val.villageName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get POP Names By District Id and Mandal Id*/ 
	$('#popMandal').change(function() {
		var districtId = $('#popDistrict').val();
		var mandalId = $('#popMandal').val();
		if(districtId!="" && mandalId !="") {
			$.ajax({
				type : "GET",
				url : "getSubstationsByDistrictIdAndMandalId",
				dataType : "json",
				data : "&districtId="+ districtId+"&mandalId="+mandalId,
				success : function(response) {
					var $select = $('#SIPopId');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.substnUid).text(val.substnName).appendTo($select);
					});
				}
			});
		}
	});
	
	$('#blMandal').change(function() {
		var districtId = $('#blDistrict').val();
		var mandalId = $('#blMandal').val();
		if(districtId!="" && mandalId !="") {
			$.ajax({
				type : "GET",
				url : "getVillagesByDistrictIdAndMandalId",
				dataType : "json",
				data : "&districtId="+ districtId+"&mandalId="+mandalId,
				success : function(response) {
					var $select = $('#blVillage');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.villageSlno).text(val.villageName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get SubStations By District Id and Mandal Id*/ 
	$('#popMandal').change(function() {
		var districtId = $('#popDistrict').val();
		var mandalId = $('#popMandal').val();
		if(districtId!="") {
			$.ajax({
				type : "GET",
				url : "getSubstationsByDistrictIdAndMandalId",
				dataType : "json",
				data : "&districtId="+ districtId+"&mandalId="+mandalId,
				success : function(response) {
					var $select = $('#popId');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val[0]).text(val[1]).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get OLT Data By Substation UID No*/
	/*$('#popId').change(function() {
		var subStnSlno = $('#popId').val();
		if(subStnSlno!="") {
			$.ajax({
				type : "GET",
				url : "getOLTSLNOBySubstationsrlno",
				dataType : "json",
				data : "&subStnSlno="+ subStnSlno,
				success : function(response) {
					var $select = $('#oltId');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.popOltSerialno).text(val.pop_oltlabelno).appendTo($select);
					});
				}
			});
		}
	});*/
	
	/* Get Villages Data By Substation UID No*/
	$('#popId').change(function() {
		$('#cafVillage').val('');
		$('#mandal').val('');
		$('#district').val('');
		var subStnSlno = $('#popId').val();
		var lmoCode = $('#lmoCode').val();
		if(subStnSlno!="" && lmoCode != "") {
			$.ajax({
				type : "GET",
				url : "getVillagesBySubstationId",
				dataType : "json",
				data : "&subStnSlno="+ subStnSlno+"&tenantCode="+lmoCode,
				success : function(response) {
					var $select = $('#cafVillage');
					$select.empty();
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {
						var value = val.villageSlno+','+val.mandalSlno+','+val.districtUid;
		        	   $('<option>').val(value).text(val.villageName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get Mandal and District By village*/
	$('#cafVillage').change(function() {
		var village = $('#cafVillage').val();
		var villageArray = village.split(",");
		if(village != "") {
			$.ajax({
				type : "GET",
				url : "getMandalsByDistrictIdAndMandalSrlNo",
				dataType : "json",
				data : "&districtId="+villageArray[2]+"&mandalSrlNo="+villageArray[1],
				success : function(response) {
					var $select = $('#mandal');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$('<option>').val(response.mandalSlno).attr('selected', true).text(response.mandalName).appendTo($select);
				}
			});
		}
		if(village != "") {
			$.ajax({
				type : "GET",
				url : "getDistrictByDistrictId",
				dataType : "json",
				data : "&districtId="+villageArray[2],
				success : function(response) {
					var $select = $('#district');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$('<option>').val(response.districtUid).attr('selected', true).text(response.districtName).appendTo($select);
				}
			});
		}
	});
	
	/* Get OLT Port-Id Data By OLT Srl Number*/
	$('#oltId').change(function() {
		var oltSrlNo = $('#oltId').val();
		var lmoCode = $('#lmoCode').val();
		var subStnId = $('#subStnId').val();
		var tenantType = $('#tenantType').val();
		lmoCode = tenantType == "SI" ? "APSFL" : lmoCode;
		if(oltSrlNo!="") {
			$.ajax({
				type : "GET",
				url : "getOLTPopIdByOltSrlNo",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode,
				success : function(response) {
					var $select = $('#oltPortId');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.portNo).text(val.portNo).appendTo($select);
					});
				}
			});
		}
		if(subStnId != "" && oltSrlNo != "") {
			$.ajax({
				type : "GET",
				url : "getVPNServicesByPopIdAndOltSrlNo",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&subStnId="+subStnId,
				success : function(response) {
					var $select = $('#vpnService');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response, function(key,val) {              
		        	   $('<option>').val(val.vpnsrvcName).text(val.vpnsrvcName).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get OLT Port Level1 Data By OLT Srl Number*/
	$('#oltPortId').change(function() {
		var oltSrlNo = $('#oltId').val();
		var oltPort = $('#oltPortId').val();
		var lmoCode = $('#lmoCode').val();
		if(oltSrlNo != "" && oltPort != "" && lmoCode != "") {
			$.ajax({
				type : "GET",
				url : "getOLTPortSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort,
				success : function(response) {
					var $select = $('#l1Slot');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response.level1SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select);
					});
				}
			});
		}
	});
	
	/* Get OLT Port Level2 and Level3 Data By Level1 Slot*/
	$('#l1Slot').change(function() {
		
		var oltSrlNo = $('#oltId').val();
		var oltPort = $('#oltPortId').val();
		var lmoCode = $('#lmoCode').val();
		var l1slot = $('#l1Slot').val();
	
		if(oltSrlNo != "" && oltPort != "" && lmoCode != "" && l1slot != "") {
			$.ajax({
				type : "GET",
				url : "getOLTPortSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort+"&l1slot="+l1slot,
				success : function(response) {
					var $select = $('#l2Slot');
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
					$.each(response.level2SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select);
					});
					
					var $select1 = $('#l3Slot');
					$select1.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select1);
					$.each(response.level3SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select1);
					});
				}
			});
		}
	});
	
	
	
	
	
	$('#l2Slot').change(function() {
		var oltSrlNo = $('#oltId').val();
		var oltPort = $('#oltPortId').val();
		var l1Slot = $('#l1Slot').val();
		var l2Slot = $('#l2Slot').val();
		var lmoCode = $('#lmoCode').val();
		var l1L2Join="";
		
		
		
		
		if(oltSrlNo != "" && oltPort != "" && lmoCode != "" && l1Slot != "" && l2Slot!="") {
			l1L2Join=l1Slot.concat("-").concat(l2Slot);
			$.ajax({
				type : "GET",
				url : "getOLTL3PortSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort+"&l1L2slot="+l1L2Join,
				success : function(response) {
					
					var $select1 = $('#l3Slot');
					$select1.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select1);
					$.each(response.level3SlotList, function(key,val) {              
		        	   $('<option>').val(val).text(val).appendTo($select1);
					});
				}
			});
		}
	});
	
	
	
	
	/* Get OLT Port Level1 Data By OLT Srl Number*/
	$('#l3Slot').change(function() {
		var oltSrlNo = $('#oltId').val();
		var oltPort = $('#oltPortId').val();
		var l1Slot = $('#l1Slot').val();
		var l2Slot = $('#l2Slot').val();
		var l3Slot = $('#l3Slot').val();
		var portSlotValue = l1Slot+"-"+l2Slot+"-"+l3Slot+",";
		if(oltSrlNo != "" && oltPort != "" && portSlotValue != "") {
			$.ajax({
				type : "GET",
				url : "checkSplitterValue",
				data : "&oltSrlNo="+ oltSrlNo+"&oltPort="+oltPort+"&portSlotValue="+portSlotValue,
				success : function(response) {
					if(response == "true") {
						$('#slotError').text('Slot is already assigned.');
						$('#l1Slot').val('');
						$('#l2Slot').val('');
						$('#l3Slot').val('');
					} else if("false") {
						$('#slotError').text('');
					}
				}
			});
		}
	});
	
	/*$('#cpeLease1').on('change', function() {
		$("#cpeDeviceStatus").removeClass("disable");
		$("#cpeDeviceStatus").removeAttr("disabled");
	});
	
	$(document).on('change','.stbLease1', function() {
		var indx = $(this).index('.stbLease1');
		$(".stbDeviceStatus:eq("+indx+")").removeClass("disable");
		$(".stbDeviceStatus:eq("+indx+")").removeAttr("disabled");
	});
	
	$('#cpeLease').on('change', function() {
		$("#cpeDeviceStatus").addClass("disable");
		$("#cpeDeviceStatus").attr("disabled", "disabled");
		$("#cpePrice").attr("readonly", "readonly");
		$('#cpeDeviceStatus').val('');
		$('#onuEmiPrice').val('');
		$('#cpePrice').val('');
		$('#instCharge').val(installationCharge);
	});
	
	$(document).on('change','.stbLease', function() {
		var indx = $(this).index('.stbLease');
		$(".stbDeviceStatus:eq("+indx+")").addClass("disable");
		$(".stbDeviceStatus:eq("+indx+")").attr("disabled", "disabled");
		$(".stbPrice:eq("+indx+")").attr("readonly", "readonly");
		$(".stbDeviceStatus:eq("+indx+")").val('');
		$(".stbEmiPrice:eq("+indx+")").val('');
		$(".stbPrice:eq("+indx+")").val('');
	});*/
	
	/* Get EMI Count Based on ONU Model*/
	/*var installationCharge = 0;
	$('#cpeModal').change(function() {
		$('#cpeDeviceStatus').val('');
		$('#instCharge').val('');
		$('#cpePrice').val('');
		$('#onuEmiPrice').val('');
		$('#onuEmiCount').val('');
		var cpeModel = $('#cpeModal').val();
		if(cpeModel != "") {
			$.ajax({
				type : "GET",
				url : "getCpeEmiCountByCpeModel",
				dataType : "json",
				data : "&profileId="+ cpeModel,
				success : function(response) {
					var $select = $('#cpeDeviceStatus');
					$('#cpePrice').val(response.upFrontCharges);
					$('#onuEmiPrice').val(response.emiAmount);
					$('#instCharge').val(response.instcharges);
					$('#onuEmiCount').val(response.emiCount);
					$select.find('option').remove(); 
					$('<option>').val("").text("--Select--").appendTo($select);
		        	$('<option>').val(response.emiCount).attr('selected', true).text(response.emiCount).appendTo($select);
				}
			});
		}
	});*/
	
	/* Get EMI Count Based on IPTV Model*/
	/*$(document).on('change','.stbModel', function() {
		var indx = $(".stbModel").index(this);
		$(".stbPrice:eq("+indx+")").val('');
		$(".stbEmiPrice:eq("+indx+")").val('');
		$(".stbEmiCount:eq("+indx+")").val('');
		var stbModel = $(".stbModel:eq("+indx+")").val();
		$(".stbDeviceStatus:eq("+indx+")").val('');
		if(stbModel != "") {
			$.ajax({
				type : "GET",
				url : "getCpeEmiCountByCpeModel",
				dataType : "json",
				data : "&profileId="+ stbModel,
				success : function(response) {
					var $select = $(".stbDeviceStatus:eq("+indx+")");
					$(".stbPrice:eq("+indx+")").val(response.upFrontCharges);
					$(".stbEmiPrice:eq("+indx+")").val(response.emiAmount);
					$(".stbEmiCount:eq("+indx+")").val(response.emiCount);
					$select.find('option').remove();  
					$('<option>').val("").text("--Select--").appendTo($select);
		        	$('<option>').val(response.emiCount).attr('selected', true).text(response.emiCount).appendTo($select);
				}
			});
		}
	});*/
	
	/* Get CPE Rental and Purchase Charges by CPE_Model*/
	/*$('#cpeDeviceStatus').change(function() {
		$('#cpePrice').val('');
		$('#onuEmiPrice').val('');
		$('#instCharge').val('');
		var cpeModel = $('#cpeModal').val();
		var emiCount = $('#cpeDeviceStatus').val();
		if(cpeModel != "" && emiCount != "") {
			$.ajax({
				type : "GET",
				url : "getCpeChargesByCpeModel",
				dataType : "json",
				data : "&profileId="+ cpeModel+"&emiCount="+emiCount,
				success : function(response) {
					$('#cpePrice').val(response.upFrontCharges);
					$('#onuEmiPrice').val(response.emiAmount);
					$('#instCharge').val(response.instcharges);
				}
			});
		}
	});*/
	
	/*$('#stbModel').change(function() {
		var stbModel = $('#stbModel').val();
		if(stbModel != "") {
			$('#stbDiv').show();
			$("#macAddress").removeAttr("readonly");
			$("#stbSerialNo").removeAttr("readonly");
			$("#stbLease").removeAttr("disabled");
			$("#stbLease1").removeAttr("disabled");
		} else {
			$('#stbDiv').hide();
			$("#macAddress").attr("readonly", "readonly");
			$("#stbSerialNo").attr("readonly", "readonly");
			$("#stbLease").attr("disabled", "disabled");
			$("#stbLease1").attr("disabled", "disabled");
		}
	});*/
	
	/* Get STB Purchase Charges by CPE_Model*/
	/*$(document).on('change','.stbDeviceStatus', function() {
		var indx = $(".stbDeviceStatus").index(this);
		$(".stbPrice:eq("+indx+")").val('');
		$(".stbEmiPrice:eq("+indx+")").val('');
		var stbModel = $(".stbModel:eq("+indx+")").val();
		var emiCount = $(".stbDeviceStatus:eq("+indx+")").val();
		if(stbModel != "" && emiCount != "") {
			$.ajax({
				type : "GET",
				url : "getCpeChargesByCpeModel",
				dataType : "json",
				data : "&profileId="+ stbModel+"&emiCount="+emiCount,
				success : function(response) {
					$(".stbPrice:eq("+indx+")").val(response.upFrontCharges);
					$(".stbEmiPrice:eq("+indx+")").val(response.emiAmount);
				}
			});
		}
	});*/
	
	/* Check Valid CPE Serial Number */ 
	$('#cpeId').change(function() {
		var cpeId = $(this).val();
		var lmoCode = $("#lmoCode").val();
		var mspCodes = $("#lmoAgrmntMspCodes").val();
		$('#cpeDeviceStatus').val('');
		$('#instCharge').val('');
		$('#cpePrice').val('');
		$('#onuEmiPrice').val('');
		$('#onuEmiCount').val('');
		$('#cpeModal').val('');
		$('#onuMacAddress').val('');
		$('#onuModel').val('');
		$('#cpeError').text("");
		//var profileId = $("#cpeModal").val();
		if(cpeId !="" && lmoCode !="") {
			$.ajax({
				type : "GET",
				url : "getCpeAndIptvbosSrlNoCheck",
				data : "&cpeId="+ cpeId+"&lmoCode="+lmoCode+"&lmoAgrmntMspCodes="+mspCodes+"&cpetypelov=ONU",
				success : function(response) {
					if(response.cpemacAddr != null && response.cpemacAddr != "") {
						//$("#onuMacAddress").attr("readonly", "readonly");
						$('#cpeError').text("");
						$('#onuMacAddress').val(response.cpemacAddr);
						var $select = $('#cpeDeviceStatus');
						$('#cpePrice').val(response.upFrontCharges);
						$('#onuEmiPrice').val(response.emiAmount);
						$('#instCharge').val(response.instcharges);
						$('#onuEmiCount').val(response.emiCount);
						$('#onuModel').val(response.profileId);
						$select.find('option').remove(); 
						$('<option>').val("").text("--Select--").appendTo($select);
			        	$('<option>').val(response.emiCount).attr('selected', true).text(response.emiCount).appendTo($select);
			        	var $select1 = $('#cpeModal');
			        	$select1.find('option').remove(); 
						$('<option>').val("").text("--Select--").appendTo($select1);
			        	$('<option>').val(response.profileId).attr('selected', true).text(response.cpeModel).appendTo($select1);
					} else {
						$('#cpeDeviceStatus').val('');
						$('#instCharge').val('');
						$('#cpePrice').val('');
						$('#onuEmiPrice').val('');
						$('#onuEmiCount').val('');
						$('#cpeModal').val('');
						$('#cpeId').val('');
						$('#onuMacAddress').val('');
						$('#onuModel').val('');
						$('#cpeError').text("Enter valid ONU serial number.");
						//$('#onuMacAddress').removeAttr("readonly");
					}
				}
			});
		}
	});
	
	/* Check Valid IPTV Box Serial Number */ 
	var stbarray = [] ;
	$(document).on('change','.stbSerialNo', function() {
		var indx = $(this).index('.stbSerialNo');
		var cpeId = $(".stbSerialNo:eq("+indx+")").val();
		var lmoCode = $("#lmoCode").val();
		var mspCodes = $("#lmoAgrmntMspCodes").val();
		//var profileId = $(".stbModel:eq("+indx+")").val();
		$(".macAddress:eq("+indx+")").val('');
		$(".stbPrice:eq("+indx+")").val('');
		$(".stbEmiPrice:eq("+indx+")").val('');
		$(".stbEmiCount:eq("+indx+")").val('');
		$(".stbModel:eq("+indx+")").val('');
		$(".stbDeviceStatus:eq("+indx+")").val('');
		$(".IPTVModel:eq("+indx+")").val('');
		stbarray[indx] = "";
		$(".stbError:eq("+indx+")").text("");
		var stbSerialNos = "";
		var i = 0; 
		var j = 0;
		//read the entered all stbSerialNo
		if ($(".stbSerialNo:eq("+indx+")").val() != "" && stbarray.length <= 0) {
			stbSerialNos = $(".stbSerialNo:eq("+indx+")").val();
			stbarray[indx] = stbSerialNos;
			j=1;
		} else {
			$.each(stbarray, function(index, value) { 
				if(value == cpeId) {
					i++;
					$(".stbSerialNo:eq("+indx+")").val('');
					$(".macAddress:eq("+indx+")").val('');
					stbarray[indx] = "";
					$(".stbError:eq("+indx+")").text("Entered IPTVBox serial number is Duplicate.");
				} 
			});
			if (i==0 && $(".stbSerialNo:eq("+indx+")").val() != "") {
				stbSerialNos = $(".stbSerialNo:eq("+indx+")").val();
				stbarray[indx] = stbSerialNos;
			}
		}
		if( j==1 || i==0) {
			if(cpeId !="" && lmoCode !="") {
				$.ajax({
					type : "GET",
					url : "getCpeAndIptvbosSrlNoCheck",
					data : "&cpeId="+ cpeId+"&lmoCode="+lmoCode+"&lmoAgrmntMspCodes="+mspCodes+"&cpetypelov=IPTV/Android Box",
					success : function(response) {
						if(response.cpemacAddr != null && response.cpemacAddr != "") {
							$(".stbError:eq("+indx+")").text("");
							$(".macAddress:eq("+indx+")").val(response.cpemacAddr);
							var $select = $(".stbDeviceStatus:eq("+indx+")");
							$(".stbPrice:eq("+indx+")").val(response.upFrontCharges);
							$(".stbEmiPrice:eq("+indx+")").val(response.emiAmount);
							$(".stbEmiCount:eq("+indx+")").val(response.emiCount);
							$(".IPTVModel:eq("+indx+")").val(response.profileId);
							$select.find('option').remove();  
							$('<option>').val("").text("--Select--").appendTo($select);
				        	$('<option>').val(response.emiCount).attr('selected', true).text(response.emiCount).appendTo($select);
				        	var $select1 = $(".stbModel:eq("+indx+")");
				        	$select1.find('option').remove();  
							$('<option>').val("").text("--Select--").appendTo($select1);
				        	$('<option>').val(response.profileId).attr('selected', true).text(response.cpeModel).appendTo($select1);
						} else {
							$(".stbSerialNo:eq("+indx+")").val('');
							$(".macAddress:eq("+indx+")").val('');
							$(".stbPrice:eq("+indx+")").val('');
							$(".stbEmiPrice:eq("+indx+")").val('');
							$(".stbEmiCount:eq("+indx+")").val('');
							$(".stbModel:eq("+indx+")").val('');
							$(".stbDeviceStatus:eq("+indx+")").val('');
							$(".IPTVModel:eq("+indx+")").val('');
							stbarray[indx] = "";
							$(".stbError:eq("+indx+")").text("Enter valid IPTVBox serial number.");
						}
					}
				});
			}
		}
		
	});
	
	/* Check Valid IPTV Box Serial Number */ 
	/*$('#stbSerialNo').change(function() {
		var cpeId = $(this).val();
		var lmoCode = $("#lmoCode").val();
		var mspCodes = $("#lmoAgrmntMspCodes").val();
		var profileId = $("#stbModel").val();
		if(cpeId !="" && lmoCode !="" && profileId) {
			$.ajax({
				type : "GET",
				url : "getCpeAndIptvbosSrlNoCheck",
				data : "&cpeId="+ cpeId+"&lmoCode="+lmoCode+"&profileId="+profileId+"&lmoAgrmntMspCodes="+mspCodes,
				success : function(response) {
					if(response == "false") {
						$('#stbSerialNo').val('');
						$('#stbMacAddress').val('');
						$('#stbError').text("Enter valid IPTVBox serial number.");
						//$('#stbMacAddress').removeAttr("readonly");
					} else {
						//$("#stbMacAddress").attr("readonly", "readonly");
						$('#stbError').text("");
						$('#stbMacAddress').val(response);
					}
				}
			});
		}
	});*/
	
	var totals=[0,0,0,0,0,0,0,0,0,0];
	var $dataRows=$("#districtWiseCafTable tr:not('.totalColumn, .titleRow')");
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
	    $("#districtWiseCafTable td.totalCol").each(function(i) {
	    	var num = totals[i];
	        $(this).html(num);
	    });
	
	var totals=[0,0,0,0,0,0,0,0];
	var $dataRows=$("#workOrderPaymentTable tr:not('.totalColumn, .titleRow')");
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
	    $("#workOrderPaymentTable td.totalCol").each(function(i) {
	    	if(totals[i] > 0) {
	    		var num = totals[i].toFixed(2);
	    	} else {
	    		var num = totals[i]+".00"
	    	}
	        $(this).html(num);
	    });
	    
    var totals=[0,0,0,0,0,0,0,0];
	var $dataRows=$("#paymentOrderTable tr:not('.totalColumn, .titleRow')");
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
	    $("#paymentOrderTable td.totalCol").each(function(i) {
	    	if(totals[i] > 0) {
	    		var num = totals[i].toFixed(2);
	    	} else {
	    		var num = totals[i]+".00"
	    	}
	        $(this).html(num);
	    });
	    
	    var totals=[0,0,0,0,0,0,0,0];
		var $dataRows=$("#paymentCafIPTVTable tr:not('.totalColumn, .titleRow')");
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
		    $("#paymentCafIPTVTable td.totalCol").each(function(i) {
		    	if(totals[i] > 0) {
		    		var num = totals[i].toFixed(2);
		    	} else {
		    		var num = totals[i]+".00"
		    	}
		        $(this).html(num);
		    });
	    
	    var totals=[0,0];
		var $dataRows=$("#subscibersCount tr:not('.totalColumn, .titleRow')");
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
		    $("#subscibersCount td.totalCol").each(function(i) {
		    	if(totals[i] > 0) {
		    		var num = totals[i];
		    	} else {
		    		var num = totals[i];
		    	}
		        $(this).html(num);
		    });
		    
    $('#macAddress').on('change', function() {
		var regex = /^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$/;
		if (regex.test($("#macAddress").val())) {
			$('#macError').text('');
		} else {
			$("#macAddress").val('');
			$('#macError').text("Please Enter Valid MacAddress.");
		}
	});
    
    $('#onuMacAddress').on('change', function() {
		var regex = /^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$/;
		if (regex.test($("#onuMacAddress").val())) {
			$('#onuMacError').text('');
		} else {
			$("#onuMacAddress").val('');
			$('#onuMacError').text("Please Enter Valid MacAddress.");
		}
	});
    
    $('#stbMacAddress').on('change', function() {
		var regex = /^([0-9A-F]{2}[:-]){5}([0-9A-F]{2})$/;
		if (regex.test($("#stbMacAddress").val())) {
			$('#stbMacError').text('');
		} else {
			$("#stbMacAddress").val('');
			$('#stbMacError').text("Please Enter Valid MacAddress.");
		}
	});
    
    $(document).on('click','.viewChanel', function() {
    	var index = $(".viewChanel").index(this);
		var addlSrvcCode = $(".iptvServiceCode:eq("+index+")").val();
		$.ajax({ 
   		 async:false,
   	     type: 'GET', 
   	     url: 'getAllCommaSeperateFeaturesFromSrvc', 
   	     data: {addlSrvcCode:addlSrvcCode},
   	     contentType: 'application/json',
   	     success: function(data) { 
   	    	 $('#srvcFutuPopUoDiv').html("");
   	    	 var i = 0;
			 var html = "";
			 
			 $.each( data, function(){
				 if(i == 0)
					 html = html + '<div class = "row" >'
				 html = html + '<div class = "col-sm-2">'+this.featurename+'</div>'
				 i++;
				 if(i == 6){
					 html = html + '</div>'
					 i = 0;
				 }
			 });
			 $('#srvcFutuPopUoDiv').append(html);
   	     }
		});
	});
    
    var totals=[0,0];
	var $dataRows=$("#subscibersCountList tr:not('.totalColumn, .titleRow')");
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
	    $("#subscibersCountList td.totalCol").each(function(i) {
	    	if(totals[i] > 0) {
	    		var num = totals[i];
	    	} else {
	    		var num = totals[i];
	    	}
	        $(this).html(num);
	    });
	    
    
   // $(document).on('click','paymentResponsible1'){
   $('#paymentResponsible1').click(function() {
	   $('#entCheckbox').attr("disabled", "disabled");
	   $('#blAddress1').attr("readonly", "readonly");
	   $('#blAddress2').attr("readonly", "readonly");
	   $('#blLocality').attr("readonly", "readonly");
	   $('#blDistrict').attr("disabled", "disabled");
	   $('#blMandal').attr("disabled", "disabled");
	   $('#blVillage').attr("disabled", "disabled");
	   $('#blPinCode').attr("readonly", "readonly");
	   $('#blFax').attr("readonly", "readonly");
	   $('#blStdCode').attr("readonly", "readonly");
	   $('#blLandlineNo').attr("readonly", "readonly");
	   $('#star').text('');
	   $('#star').css('color', 'white')
	   $('#emailError').text('');
	   
	   $( "#entCheckbox" ).prop( "checked", false );
	   $('#blAddress1').val("");
	   $('#blAddress2').val("");
	   $('#blLocality').val("");
	   $('#blDistrict').val("");
	   $('#blDistrict1').val("");
	   $('#blMandal').val("");
	   $('#blMandal1').val("");
	   $('#blVillage').val("");
	   $('#blVillage1').val("");
	   $('#blPinCode').val("");
	   $('#blFax').val("");
	   $('#blStdCode').val("");
	   $('#blLandlineNo').val("");
   });
   
   $('#paymentResponsible').click(function() {
	   $('#entCheckbox').removeAttr("disabled");
	   $('#blAddress1').removeAttr("readonly");
	   $('#blAddress2').removeAttr("readonly");
	   $('#blLocality').removeAttr("readonly");
	   $('#blDistrict').removeAttr("disabled");
	   $('#blMandal').removeAttr("disabled");
	   $('#blVillage').removeAttr("disabled");
	   $('#blPinCode').removeAttr("readonly");
	   $('#blFax').removeAttr("readonly");
	   $('#blStdCode').removeAttr("readonly");
	   $('#blLandlineNo').removeAttr("readonly");
	   $('#star').text('*');
   	   $('#star').css('color', 'red')
   });
   
   $(document).on('click','.cafBulkUploadError',function() {
		var i = $(".cafBulkUploadError").index(this);
		$(".cafBulkUploadErrorForm:eq("+i+")").attr('action',"cafBulkUploadErrorDownload");
		$(".cafBulkUploadErrorForm:eq("+i+")").submit();
	});
   
   $(document).on('click','.vpnUploadErrorButton',function() {
		var i = $(".vpnUploadErrorButton").index(this);
		$(".vpnUploadErrorForm:eq("+i+")").attr('action',"vpnUploadErrorDownload");
		$(".vpnUploadErrorForm:eq("+i+")").submit();
	});
   
   $(document).on('click','.editCustomer',function() {
       var i = $(".editCustomer").index(this);
       $(".editCustomerForm:eq("+i+")").attr('action',"editCustomer");
       $(".editCustomerForm:eq("+i+")").submit();
   });
   
   $("#saveEditCustomerButton").click(function(event) {
		$("#saveEditForm").attr('action',"updateCustomerDetails");
		$("#saveEditForm").submit();
   });
	
  $('.editField').on('keypress', function(){
    	if (($('#mobileNo').val() != null) ||  ($('#mobileNo1').val() != null)
    	    	 || ($('#stdCode').val() != null) || ($('#landLineNoOld').val() != null)
    	    	 || ($('#emailIdOld').val() != null)){
            $("#saveEditCustomerButton").removeAttr("disabled");
    	 }
    });
  
  //Adding more IPTVBox Informations
  var arealist = 0;
  $(".addIPTVBoxs").click(function() {
		var s = "";
		var s1 = "";
		var html = '';
		var numItems = $('.cIndex').length;
		if(numItems == 0)
			arealist = 1;
		$.ajax({ 
			 type: 'GET', 
		     url:  'getStbModelList',
		     async: false,
		     success: function(result) { 
		    	 $.each(result, function(key, value) {
		    		 s = s + "<option value='"+value.profileId+"'>"+value.cpeModel+"</option>";
		    	 });
		    	 html =
		    		 '<div class="panel cIndex" style="border: 1px solid #d4d5d6;">'
		    		 +' <div style="float:right; clear:right;">'
		    		 +'		<img style="cursor: pointer;" width="15" height="15" value='+arealist+' class="closeAreas" src="./resources/images/close1.png">'
		    		 +'		<input type="hidden" class="imgindex" value="'+arealist+'">'	
		    		 +' </div>'
		    		 +'  <div class="panel-content">'
		    		 +'    <div class="row ">'
		    		 
		    		 +'	 	<div class="col-sm-3">'
					 +'			<div class="form-group">'
					 +'				<label class="control-label">IPTV Box Serial Number<span style="color: red;">*</span></label>'
					 +'				<label id="stbError" class ="stbError" style="text-align: center;color: red;"></label>' 
					 +'				<input type="text" name="iptvBoxList['+arealist+'].stbSerialNo" id="stbSerialNo" required class="form-control form-white stbSerialNo" maxlength="20" placeholder="Enter IPTV Box Serial Number"  >'
					 +'			</div>'
					 +'		</div>'
					
		    		 +'   	<div class="col-sm-3">'
					 +'			<div class="form-group">'
					 +'				<label class="control-label">IPTV Box Mac-Id<span style="color: red;">*</span></label>'
					 +'				<label id="stbMacError" style="text-align: center;color: red;"></label>'
					 +'				<input type="text" name="iptvBoxList['+arealist+'].macAddress" id="stbMacAddress" required class="form-control form-white macAddress" readonly maxlength="20" placeholder="Enter IPTV Box Mac-Id" readonly >'
					 +'			</div>'
					 +'		</div>'
					 
		    		 +'     <div class="col-sm-3" >'
					 +'			<div class="form-group">'
					 +'			<input type="hidden" name="iptvBoxList['+arealist+'].stbModel" id="" required class="form-control form-white IPTVModel" >'			
					 +' 		<label class="control-label">IPTV Box Model<span style="color: red;">*</span></label>'
					 +'				<div class="option-group">'
					 +'				<select name="" id="stbModel" class="form-control form-white stbModel disable" disabled required >'
					 +'				<option value="">--Select--</option>'
					 +'		  			<c:forEach var = "stbModel" items="${stbModelList}">'
					 +'	 					<c:choose>'
					 +'						<c:when test="${empty customerCafVO}">'+s+
					 +'						</c:when>'
					 +'						</c:choose>'
					 +'					 </c:forEach>'
					 +'			  	</select>'
					 +'		  	</div>'
					 +'	  	  </div>'
					 +'  	</div>'
					 
					 
			    	 +'	  <div class="col-sm-3">'
			    	 +'		 <div class="form-group">'
			    	 +'		 	<label class="control-label">No Of IPTV Box EMI<span style="color: red;">*</span></label>'
			    	 +'         <input type="hidden" name="iptvBoxList['+arealist+'].stbInstallmentCount" id="stbEmiCount" class="form-control form-white stbEmiCount" >'
			    	 +'		 	<div class="option-group">'
			    	 +'				<select name="" id="stbDeviceStatus" required class="form-control form-white disable stbDeviceStatus" disabled>'
			    	 +'					<option value="">--Select--</option>'
			    	 +'				</select>'
			    	 +'			</div>'
			    	 +'		</div>'
			    	 +'	</div>'
		    		 
			    	 +' </div>'
			    	 
			    	 +'<div class="row">'
			    	 
			    	 +' <div class="col-sm-3">'
			    	 +'	<div class="form-group">'
			    	 +'		<label class="control-label">IPTV Box EMI Price<span style="color: red;">*</span></label> '
			    	 +'		<input type="text" name="iptvBoxList['+arealist+'].stbEmiPrice" id="stbEmiPrice" required class="form-control form-white number stbEmiPrice" readonly placeholder="ONU Price/Installment">'
			    	 +'	</div>'
			    	 +' </div>'
			    	 
			    	 +' <div class="col-sm-3">'
			    	 +'	<div class="form-group">'
			    	 +'		<label class="control-label">IPTV Box Upfront Price<span style="color: red;">*</span></label> '
			    	 +'		<input type="text" name="iptvBoxList['+arealist+'].stbPrice" id="stbPrice" required class="form-control form-white number stbPrice" readonly placeholder="IPTV Box Price">'
			    	 +'	</div>'
			    	 +' </div>'
			    	 
			    	 +'	<div class="col-sm-3">'
					 +'		<div class="form-group">'
					 +'			<label class="control-label">IPTV Box Package<span style="color: red;">*</span></label>'
					 +'			<div class="option-group">'
					 +'				<select name="iptvBoxList['+arealist+'].iptvSrvcCodes" id="iptvSrvcCodes'+arealist+'"  class=" form-control form-white iptvSrvcCodes iptvselectbox" required disabled>'
					 +'					<option class="iptvoption" value="">--Select--</option>'
					 +'				</select>'
					 +'			</div>'
					 +'			<div class="iptvhiddendiv" name="iptvBoxList['+arealist+'].iptvhiddendiv" id="iptvhiddendiv'+arealist+'" style ="display:none; overflow: scroll; border-style: groove; border-width: 1px;">'
					 +'				<ul style="list-style: none;"> </ul>'
					 +'			</div>'
					 +'		</div>'
					 +'</div>'
			    	 
					/* +'		<div class="col-sm-2">'
					 +'			<div class="form-group">'
					 +'				<label class="control-label">IPTV Box Own<span style="color: red;">*</span></label>'
					 +'				<div class="clear"></div>'
					 +'				<div class="pull-left" style="padding-left: 0;">'
					 +'					<input type="radio" name="iptvBoxList['+arealist+'].stbLease" id="stbLease" class="form-white stbLease" value="Y" checked >&nbsp;&nbsp;Yes'
					 +'				</div>'
				 	 +'				<div class="col-sm-6">'
					 +'					<input type="radio" name="iptvBoxList['+arealist+'].stbLease" id="stbLease1" class="form-white stbLease1" value="N" >&nbsp;&nbsp;No'
					 +'				</div>'
					 +'				<div class="clear"></div>'
					 +'			</div>'
					 +'		</div>'*/
					
			    	 /*+' <div class="col-sm-3">'
					 +' 	<div class="form-group">'
					 +' 		<label class="control-label">IPTV Box Package<span style="color: red;">*</span></label>'
					 +' 		<div class="option-group">'
					 +' 			<select name="iptvBoxList['+arealist+'].iptvSrvcCodes" id="iptvSrvcCodes'+arealist+'" class="form-control form-white iptvSrvcCodes" required disabled >'
					 +' 				<option value="">--Select--</option>'
					 +'				 </select>'
					 +' 		</div>'
					 +' 	</div>'
					 +' </div>'*/
					 
					/* +'		<div class="col-sm-3" >'
					 +'			<div style="float:right; clear:right;">'
					 +'				<img style="cursor: pointer;" width="15" height="15" value='+arealist+' class="closeAreas" src="./resources/images/close1.png">'
					 +'				<input type="hidden" class="imgindex" value="'+arealist+'">'	
					 +'        </div>'
					 +'     </div>'*/
		    		 +'    </div>'
					 
		    	     +'  </div>'
		    	     +'</div>';
		      }
		});
		
		$(".IPTVDivs").append(html);
		if(tenantType == "SI") {
			$(".stbLease").attr("disabled", "disabled");
			$(".stbLease1").attr("disabled", "disabled");
		}
		if(iptvPkgArray.length > 0) {
	    	$("#iptvSrvcCodes"+arealist+"").removeAttr("disabled");
	    	iptvselected = [];
	    	var $select = $("#iptvhiddendiv"+arealist+"");
	 		$select.find('li').remove();
	 		$.each(iptvPkgArray, function(key,val) {              
	 		   $('<li style="list-style: none;"><input type="checkbox" checked="checked" value="'+val.iptvProdCode+'" />'+val.iptvProdName+'</li>').appendTo($select);
	 		});
	 		$("#iptvSrvcCodes"+arealist+"").children('.iptvoption').text(pkgname);
	 		$("#iptvSrvcCodes"+arealist+"").children('.iptvoption').val(pkgcode);
		} else {
			$("#iptvSrvcCodes"+arealist+"").attr("disabled", "disabled");
		}
		arealist++;
	});
  
  $(document).on('click', '.closeAreas', function(event){
		var index = $(this).index('.closeAreas');
		var imginde = $(this).siblings(".imgindex").val();
	//	$(".cIndex:eq("+index+")").remove();	
		stbarray[imginde] = ""; // To remove STB SErial No. from Array
	  $('.closeAreas:eq("+index+")').parent().parent().remove();
	});
  
  $(document).on('click', '.cafsDownload', function(event){
		$("#multiActionForm").attr('action',"multiActionCafDownload");
		$("#multiActionForm").submit();
	});
  
  //change code 10-1-2018
  
  $(document).on('click', '.cafsDownload1', function(event){
		$("#multiActionForm1").attr('action',"multiActionCafDownload1");
		$("#multiActionForm1").submit();
	});
  
  
  //BalanceAdjustments
  $("#BalanceAdjustmentSearchButton").click(function(event) {
		var custId = $('#custId').val();
		var AadharNo = $('#AadharNo').val();
		if(custId == "" && AadharNo == "" ) {
			$('#error1').text("Please Enter any one of the Field .");
		} else {
			$("#balanceAdjustmentForm").attr('action',"searchBalanceAdjustment");
			$("#balanceAdjustmentForm").submit();
		}
		});

/*$('#balanceAdjustTable tr[class !="tableHeader"]').live('click', function() {
		$('.radioBalance').on('change', function() {
			var row = $("#balanceAdjustTable input[name=radioBalance]:checked").closest('tr');
			$("#BalanceAdjustCustIdForm").attr('action', "showCafinvDetailsByCustinvno");
			$("#BalanceAdjustCustIdForm").submit();
		});
	});*/


$(document).on('click','.radioCafs', function() {
	var index = $(".radioCafs").index(this);
		var acctCafNo = $(".clickedCafNo:eq("+index+")").text();
		var month = $(".clickedMonth:eq("+index+")").text();
		var billNo = $(".billNo:eq("+index+")").text();
		$('#balanceError').text("");
		$.ajax({ 
		 async:false,
	     type: 'GET', 
	     url: 'checkedCafInvCafNos',
	     data: {cafNo:acctCafNo, month:month, billNo:billNo},
	     contentType: 'application/json',
	     success: function(data) { 
			    $("#cafBalAdjModalTable> tbody").html("");
			    $.each(data,function(idx,obj) {
					$("#cafBalAdjModalTable> tbody").append('<tr>'
						    +'<td class="invdtlId">' +obj.custinvno+'</td>'
						    +'<td class="cafNo">' +obj.acctcafno+'</td>'
				            +'<td class="chargeCode">' +obj.chargeCode+'</td>'
				            +'<td class="prodCode">' +obj.prodCode+'</td>'
				            +'<td class="chargeAmt">' +obj.chargeAmt+'</td>'
				            +'<td class="taxAmt">' +obj.taxAmt+'</td>'
				            +'<td><input type="number" class="enteredAdjBal numbersOnly" size="10" placeholder="Enter Amount"></td>'
				            //+'<td><span style="float: left; padding-left: 10px;"><input type="number" class="negBal" size="10" placeholder="Negative Balance"></span><span style="float: left; padding-left: 10px;"><input type="number" class="posBal" size="10" placeholder="Positive Balance"></span></td></tr>');
				            +'<td hidden="hidden" class="custDistUid">' +obj.custDistUid+'</td>'
				            +'<td hidden="hidden" class="pmnCustId">' +obj.pmnCustId+'</td>'
				            +'<td hidden="hidden" class="chargedDate">' +obj.chargedDate+'</td>'
				            +'<td hidden="hidden" class="chargeFDate">' +obj.chargeFDate+'</td>'
				            +'<td hidden="hidden" class="chargeTDate">' +obj.chargeTDate+'</td>'
				            +'<td hidden="hidden" class="rectype">' +obj.rectype+'</td>'
				            +'<td hidden="hidden" class="prodcafno">' +obj.prodcafno+'</td>'
				            +'<td hidden="hidden" class="stbcafno">' +obj.stbcafno+'</td>'
				            +'<td hidden="hidden" class="srvccode">' +obj.srvccode+'</td>'
				            +'<td hidden="hidden" class="tenantcode">' +obj.tenantcode+'</td>'
				            +'<td hidden="hidden" class="rsagruid">' +obj.rsagruid+'</td>'
				            +'<td hidden="hidden" class="enttax">' +obj.entTax+'</td>'
				            +'<td hidden="hidden" class="kisantax">' +obj.kisanTax+'</td>'
				            +'<td hidden="hidden" class="srvctax">' +obj.srvcTax+'</td>'
				            +'<td hidden="hidden" class="swatchtax">' +obj.swatchTax+'</td>'
				            +'<td hidden="hidden" class="featurecode">' +obj.featureCode+'</td>'
				            +'<td hidden="hidden" class="status">' +obj.status+'</td>'
				            +'<td hidden="hidden" class="cafinvno">' +obj.cafinvno+'</td>'
				            +'<td hidden="hidden" class="custinvno">' +obj.custinvno+'</td></tr>');
			    });
		}
	});
});

$(document).on('change','.enteredAdjBal', function() {
	var indx = $(this).index('.enteredAdjBal');
	var enteredAdjBal = parseFloat($(".enteredAdjBal:eq("+indx+")").val());
	var chargeAmt = parseFloat($(".chargeAmt:eq("+indx+")").text());
	var taxAmt = parseFloat($(".taxAmt:eq("+indx+")").text());
	var total = parseFloat(chargeAmt) + parseFloat(taxAmt);
	if(total < enteredAdjBal){
		$(".enteredAdjBal:eq("+indx+")").val("");
		alert("You Entered More Amount");
	}
	
});

$("#balAdjBalanceSubmit").click(function() {
		var totalBalAdjList = [];
		var totalDataList = [];
		var selected = 0;
		$('.cafBalAdjModalTable').find('tbody > tr').each(function() {
			var row = $(this);
			if (row.find('.enteredAdjBal').val() != "") {
				selected++;
				
				var invdtlId =  row.find('.invdtlId').text();
				var acctcafno = row.find('.cafNo').text();
				var chargeCode = row.find('.chargeCode').text()
				var prodCode =  row.find('.prodCode').text();
				var chargeAmt = row.find('.enteredAdjBal').val();
				var taxAmt = "0";
				var custDistUid =  row.find('.custDistUid').text();
				var pmnCustId = row.find('.pmnCustId').text();
				var paidAmount = row.find('.enteredBal').val();
				var chargedDate = row.find('.chargedDate').text();
				var chargeFDate = row.find('.chargeFDate').text();
				var chargeTDate = row.find('.chargeTDate').text();
				var rectype = row.find('.rectype').text();
				var prodcafno = row.find('.prodcafno').text();
				var stbcafno = row.find('.stbcafno').text();
				var srvccode = row.find('.srvccode').text();
				var tenantcode = row.find('.tenantcode').text();
				var rsagruid = row.find('.rsagruid').text();
				var custinvno = row.find('.custinvno').text();
				var enttax = row.find('.enttax').text();
				var kisantax = row.find('.kisantax').text();
				var srvctax = row.find('.srvctax').text();
				var swatchtax = row.find('.swatchtax').text();
				var featurecode = row.find('.featurecode').text();
				var status = row.find('.status').text();
				var cafinvno = row.find('.cafinvno').text();
				
				var dataObject = {
						"invdtlId" : invdtlId,
						"acctcafno" : acctcafno,
						"chargeCode" : chargeCode,
						"prodCode" : prodCode,
						"chargeAmt" : chargeAmt,
						"taxAmt" : taxAmt,
						"custDistUid" : custDistUid,
						"pmnCustId" : pmnCustId,
						"paidAmount" : paidAmount,
						"chargedDate" : chargedDate,
						"chargeFDate" : chargeFDate,
						"chargeTDate" : chargeTDate,
						"rectype" : rectype,
						"prodcafno" : prodcafno,
						"stbcafno" : stbcafno,
						"srvccode" : srvccode,
						"tenantcode" : tenantcode,
						"rsagruid" : rsagruid,
						"custinvno" : custinvno,
						"entTax" : enttax,
						"kisanTax" : kisantax,
						"srvcTax" : srvctax,
						"swatchTax" : swatchtax,
						"featureCode" : featurecode,
						"status" : status,
						"cafinvno" : cafinvno,
				};
				totalBalAdjList.push(dataObject);
			} 
		});
		if(selected == 0) {
			$('#balanceError').text("Please Enter Balance for any one");
		} else {
			totalBalAdjList = JSON.stringify(totalBalAdjList);
			$.ajax({ 
	    	     type: 'POST', 
	    	     url: 'saveBalanceAdjustment', 
	    	     data: totalBalAdjList,
	    	     contentType: 'application/json',
	    	     success: function(response) { 
	    	    	 if(response == "success"){
	    	    		 if(window.confirm('Balance Adjustment is successfully completed...!!!')){}
	 	    		   	   	$("#myModal .close").click();
	    	    	 	} else {
	    	    	 		if(window.confirm('Balance Adjustment is failed ...!!!')){}
	    	    	 	}
	    	     	},
		 		});
		}
	});
  
});

/*$(document).click(function() {
    $('.iptvhiddendiv').hide();
});*/

function hidedata(c){
	$("#intr"+c).hide();
	if ($("#intr"+c).is(':visible')) {
		$("#imgp"+c).hide();
		$("#imgm"+c).show();
		$(".imgm").not("#imgm"+c).hide();
		$(".imgp").not("#imgp"+c).show();
	} else {
		$(".imgp").show();
		$(".imgm").hide();
	 }
}

function fingerPrintShowDate(c) {
	var cafNo = $("#cafno"+c).text();
	if(cafNo != "") {
		$.ajax({
			type : "GET",
			url : "getFingerPrintDetails",
			dataType : "json",
			data : "&cafNo="+ cafNo,
			success : function(response) {
				$("#fingerPrintId").text(response);
			}
		});
	}
}

function deActCaf(c) {
	var cafNo = $("#cafno"+c).text();
	if(cafNo != "") {
		$.ajax({
			type : "GET",
			url : "DeActivateCaf",
			dataType : "json",
			data : "&cafNo="+ cafNo,
			success : function(response) {
				$("#fingerPrintId").text(response);
			}
		});
	}
}

//Aadhar checksum Validation
var d = [

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],

[1, 2, 3, 4, 0, 6, 7, 8, 9, 5],

[2, 3, 4, 0, 1, 7, 8, 9, 5, 6],

[3, 4, 0, 1, 2, 8, 9, 5, 6, 7],

[4, 0, 1, 2, 3, 9, 5, 6, 7, 8],

[5, 9, 8, 7, 6, 0, 4, 3, 2, 1],

[6, 5, 9, 8, 7, 1, 0, 4, 3, 2],

[7, 6, 5, 9, 8, 2, 1, 0, 4, 3],

[8, 7, 6, 5, 9, 3, 2, 1, 0, 4],

[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]

];

// permutation table p

var p = [

[0, 1, 2, 3, 4, 5, 6, 7, 8, 9],

[1, 5, 7, 6, 2, 8, 3, 0, 9, 4],

[5, 8, 0, 3, 7, 9, 6, 1, 4, 2],

[8, 9, 1, 6, 0, 4, 3, 5, 2, 7],

[9, 4, 5, 3, 1, 2, 6, 8, 7, 0],

[4, 2, 8, 6, 5, 7, 3, 9, 0, 1],

[2, 7, 9, 3, 8, 0, 6, 4, 1, 5],

[7, 0, 4, 6, 9, 1, 3, 2, 5, 8]

];

// inverse table inv

var inv = [0, 4, 3, 2, 1, 5, 6, 7, 8, 9];

// converts string or number to an array and inverts it

function invArray(array) {

	if (Object.prototype.toString.call(array) === "[object Number]") {

	array = String(array);

}

if (Object.prototype.toString.call(array) === "[object String]") {

	array = array.split("").map(Number);

}

return array.reverse();

}

// validates checksum

function validate(array) {

	var c = 0;

	var invertedArray = invArray(array);

	for (var i = 0; i < invertedArray.length; i++) {

	c = d[c][p[(i % 8)][invertedArray[i]]];

	}
	return (c === 0);
}

function disableBackButtonPress() {
    window.location.hash = "no-back-button";
    window.location.hash = "Again-No-back-button";
    window.onhashchange = function() {
    window.location.hash = "";
    }
}

function showdata() {
	$(".intrInfo").hide();

	$("#imgm_cpeInfo").hide();
	$("#imgp_cpeInfo").show();
	$("#imgp_pkgInfo").show();
	$("#imgm_pkgInfo").hide();
	$("#imgp_paymntInfo").show();
	$("#imgm_paymntInfo").hide();
	$("#imgp_tcktInfo").show();
	$("#imgm_tcktInfo").hide();

	if ($("#imgp_custInfo").is(':visible')) {
		$("#intr_custInfo").show();
		$("#imgp_custInfo").hide();
		$("#imgm_custInfo").show();
	} else {
		$("#intr_custInfo").hide();
		$("#imgp_custInfo").show();
		$("#imgm_custInfo").hide();
	}
}

function showdata_cpeInfo() {
	$(".intrInfo").hide();
	
	$("#imgp_custInfo").show();
	$("#imgm_custInfo").hide();
    $("#imgp_pkgInfo").show();
	$("#imgm_pkgInfo").hide();
	$("#imgp_paymntInfo").show();
	$("#imgm_paymntInfo").hide();
	$("#imgp_tcktInfo").show();
	$("#imgm_tcktInfo").hide();
	
	if ($("#imgp_cpeInfo").is(':visible')) {
	    $("#intr_cpeInfo").show();
	    $("#imgp_cpeInfo").hide();
		$("#imgm_cpeInfo").show();
	}
	else{
	    $("#intr_cpeInfo").hide();
	    $("#imgp_cpeInfo").show();
		$("#imgm_cpeInfo").hide();
	}
}

function showdata_pkgInfo() {
	$(".intrInfo").hide();
	
	$("#imgp_custInfo").show();
	$("#imgm_custInfo").hide();
	$("#imgp_cpeInfo").show();
	$("#imgm_cpeInfo").hide();
	$("#imgp_paymntInfo").show();
	$("#imgm_paymntInfo").hide();
	$("#imgp_tcktInfo").show();
	$("#imgm_tcktInfo").hide();
	
	if ($("#imgp_pkgInfo").is(':visible')) {
	    $("#intr_pkgInfo").show();
	    $("#imgp_pkgInfo").hide();
		$("#imgm_pkgInfo").show();
	}
	else{
	    $("#intr_pkgInfo").hide();
	    $("#imgp_pkgInfo").show();
		$("#imgm_pkgInfo").hide();
	}
}

function showdata_paymntInfo() {
	$(".intrInfo").hide();
	
	$("#imgp_custInfo").show();
	$("#imgm_custInfo").hide();
	$("#imgp_cpeInfo").show();
	$("#imgm_cpeInfo").hide();
	$("#imgp_pkgInfo").show();
	$("#imgm_pkgInfo").hide();
	$("#imgp_tcktInfo").show();
	$("#imgm_tcktInfo").hide();
	
	if ($("#imgp_paymntInfo").is(':visible')) {
	    $("#intr_paymntInfo").show();
	    $("#imgp_paymntInfo").hide();
		$("#imgm_paymntInfo").show();
	}
	else{
	    $("#intr_paymntInfo").hide();
	    $("#imgp_paymntInfo").show();
		$("#imgm_paymntInfo").hide();
	}
}

function showdata_tcktInfo() {
	$(".intrInfo").hide();
	
	$("#imgp_custInfo").show();
	$("#imgm_custInfo").hide();
	$("#imgp_cpeInfo").show();
	$("#imgm_cpeInfo").hide();
	$("#imgp_pkgInfo").show();
	$("#imgm_pkgInfo").hide();
	$("#imgp_paymntInfo").show();
	$("#imgm_paymntInfo").hide();
	
	$("#intr_paymntInfo").hide();
	if ($("#imgp_tcktInfo").is(':visible')) {
	    $("#intr_tcktInfo").show();
	    $("#imgp_tcktInfo").hide();
		$("#imgm_tcktInfo").show();
	}
	else{
	    $("#intr_tcktInfo").hide();
	    $("#imgp_tcktInfo").show();
		$("#imgm_tcktInfo").hide();
	}
}

function updatePayableaamount(event,amt){
	if(event.checked==true){
		var payableamt=(((parseFloat($('#payableamount').val())+amt)*100)/100).toFixed(2);
		$('#payableamount').val(payableamt);
	}else{
		var payableamt=(((parseFloat($('#payableamount').val())-amt)*100)/100).toFixed(2);
		$('#payableamount').val(payableamt);
	}
}

$(document).ready(function () {
	var total2=parseFloat(0).toFixed(2);
	$('#payableamount').val(total2);
	});

function checkbal(){
	var availbal=Number((((parseFloat($('#lmoWallet').val()))*100)/100).toFixed(2));
	var payableamt=Number((((parseFloat($('#payableamount').val()))*100)/100).toFixed(2));
	if(availbal>payableamt) return true;
	else{
		alert("Insufficient Balance!!!!");
		return false;
	}
	
}
	
