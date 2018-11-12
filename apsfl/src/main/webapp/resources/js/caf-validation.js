$(document).ready(function(){
	$("#approveCafTable, #paymentTable, #monthlyPaymentTable, #productsTable, #customerActivityLogTable").dataTable({
        "sPaginationType": "full_numbers",
    });
	
	$('#districtWiseCafTable').find("tr:even").css("background-color", "#f2f2f2");
	
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentDate = date.getDate();
	var currentYear = date.getFullYear();
	 $("#effectiveFrom_coms").datepicker({
	       numberOfMonths: 1,
	       changeMonth: true,
	       changeYear: true,
	      // minDate : new Date(currentYear, currentMonth, currentDate),
	       onSelect: function (selected) {
	           var dt = new Date(selected);
	           dt.setDate(dt.getDate());
	           $("#effectiveTo_coms").datepicker("option", "minDate", dt);
	       }
	   });
	 
	   $("#effectiveTo_coms").datepicker({
	    minDate : new Date(currentYear, currentMonth, currentDate),
	       numberOfMonths: 1,
	       changeMonth: true,
	       changeYear: true,
	       onSelect: function (selected) {
	           var dt = new Date(selected);
	           dt.setDate(dt.getDate());
	           $("#effectiveFrom_coms").datepicker("option", "maxDate", dt);
	       }
	   });
	
	/*$("#effectiveFrom").datepicker({
		changeMonth: true,
    	changeYear: true,
    	format: "MM/dd/yyyy",
	    onSelect: function(selected) {
	    $("#effectiveTo").datepicker("option","minDate", selected)
	   }
	});
	 
    $("#effectiveTo").datepicker({
    	changeMonth: true,
    	changeYear: true,
    	format: "MM/dd/yyyy",
        onSelect: function(selected) {
        $("#effectiveFrom").datepicker("option","maxDate", selected)
        }
    });  */
	
	$("#customerform").validate({
		rules : {
			cafNo : {
			      required: true,
			      number: true
			    },
			custType : "required",
			lmoCode : "required",
			lmoName : "required",
			aadharNumber : {
			      required: true,
			      number: true,
			      minlength: 12
			    },
			titleLovName : "required",
			firstName : {
			      required: true,
			      //minlength: 3
			    },
			    lastName : {
			      required: true,
			      //minlength: 3
			    },
			dob : "required",
			gender : "required",   
			//pocName : "required",
			/*emailId : {
				required : true,
				email : true
			},*/
			billCycle : "required",
			//segment : "required",
			//channel : "required",
			//macAddress : "required",
			location : "required",
			address1 : "required",
			city : {
			      required: true,
			    },
			    locality : {
			      required: true,
			      minlength: 3
			    },    
			state : "required",
			lockPeriod : "required",
			//stbModel : "required",
			//stbLease : "required",
			//stbDevice  : "required",
			//stbPrice  : "required",
			popId : "required",
			popDistrict : "required",
			popMandal : "required",
			//oltId : "required",
			//oltPortId : "required",
			//cpeModal : "required",
			//stbModel : "required",
			//cpeDevice : "required",
			//cpePrice : "required",
			//cableCharge : "required",
			district : "required",
			mandal : "required",
			/*pinCode : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 6
			} , */
			mobileNo : {
				required : true,
				number :true,
				minlength: 10,
				maxlength: 10
			},
			cpeLease : "required"
			/*poiLov :"required",
			poiDocumentNo :"required",
			poiIssuingAuthority :"required",
			poiPlaceOfIssue :"required",
			poiDateOfIssue :"required",
			poiDocument : "required",
			poaLov :"required",
			poaDocumentNo :"required",
			poaIssuingAuthority :"required",
			poaPlaceOfIssue :"required",
			poaDateOfIssue :"required",
			poaDocument : "required"*/
		},
		messages : {
			projCategory : "Please select Project Category",
			committee : "Please select AMC",
			projWorkId : "Please select Name of the Work",
			district : "Please select District",
			date : "Please enter Date",
			area : "Please select District",
			mandal : "Please select Mandal",
			cpeLease : "Please select Yes or No",
			cableCharge : "Please enter Extra Cable Charge"
		},
		
		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			var custType = $('#custType').val();
			var dob = $('#indDob').val();
			if(custType == "INDIVIDUAL" && dob != "" ) {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if(custType == "ENTERPRISE" && $('#pocName').val() != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else {
				$('#dobError').text("Please Select Date.");
				$('#pocError').text("Please Enter POC Name.");
			}
		}
	});
	
	$("#packagesform").validate({
		rules : {
			//macAddress : "required",
			onuMacAddress : "required",
			cpeId : "required",
			//stbLease : "required",
			//stbDevice  : "required",
			//stbPrice  : "required",
			//stbSerialNo  : "required",
			popId : "required",
			oltId : "required",
			oltPortId : "required",
			l1Slot : "required",
			l2Slot : "required",
			l3Slot : "required",
			cpeModal : "required",
			//stbModel : "required",
			installmentCount : "required",
			cpePrice : "required",
			cableCharge : "required",
			cpeLease : "required",
			vpnService : "required"
		},
		messages : {
			projCategory : "Please select Project Category",
			committee : "Please select AMC",
			projWorkId : "Please select Name of the Work",
			district : "Please select District",
			date : "Please enter Date",
			area : "Please select District",
			mandal : "Please select Mandal",
			cpeLease : "Please select Yes or No",
			cableCharge : "Please enter Extra Cable Charge"
		}
	});
	
	$("#bulkCafUploadForm").validate({
		rules : {
			bulkUploadFile : {
				required : true,
				extension: "xls|xlsx"
			},
			mobileNo : {
				required : true,
				number :true,
				minlength: 10,
				maxlength: 10
			},
			location : "required",
			//firstName : "required",
			pocName : "required",
			lmoCode  : "required",
			district : "required",
			mandal : "required",
			apsflUniqueId : "required"
		},
		messages : {
			bulkUploadFile : "Please choose valid xlxs or xlx file",
		},

		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			form.submit();
			ajaxindicatorstart('Please Wait...');
		} 
	});
	
	$("#VPNServiceUploadForm").validate({
		rules : {
			vpnUploadFile : {
				required : true,
				extension: "xls|xlsx"
			},
		},
		messages : {
			vpnUploadFile : "Please choose valid xlxs or xlx file",
		},

		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			form.submit();
			ajaxindicatorstart('Please Wait...');
		} 
	});
	
	$("#entcustomerform").validate({
		rules : {
			custType : "required",
			//uidNo : "required",
			pocDesignation : "required",
			titleLovName : "required",
			orgName : "required",
			dateOfIncorporation : "required",
			emailId : {
				required : true,
				email : true
			},
			address1 : "required",
			locality : "required",
			city : "required",
			pinCode : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 6
			} , 
			pocmobile : {
				required : true,
				number :true,
				minlength: 10,
				maxlength: 10
			},
			stdCode : {
				required : true,
				number :true,
				minlength: 3,
				maxlength: 5
			} ,
			landlineNo : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 10
			} ,
			billCycle : "required",
			officeTypeLov : "required",
			pocName : "required",
			area : "required",
			mandal : "required",
			blAddress1 : "required",
			blLocality : "required",
			blCity : "required",
			blPinCode : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 6
			} , 
			blStdCode : {
				required : true,
				number :true,
				minlength: 3,
				maxlength: 5
			} ,
			blLandlineNo : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 10
			} ,
			blArea : "required",
			blMandal : "required"
		},
		messages : {
			projCategory : "Please select Project Category",
			committee : "Please select AMC",
			projWorkId : "Please select Name of the Work",
			district : "Please select District",
			date : "Please enter Date"
		},

		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			//if($('#dateOfIncorporation').val() != "" ) {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			//} else {
				//$('#dobError').text("Please Select Date.");
			//}
		}
	});
	
	$("#entcustomernodeform").validate({
		rules : {
			orgName : "required",
			pocName : "required",
			//uidNo : "required",
			pocDesignation : "required",
			pocmobile : {
				required : true,
				number :true,
				minlength: 10,
				maxlength: 10
			},
			address1 : "required",
			locality : "required",
			city : "required",
			area : "required",
			mandal : "required",
			pinCode : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 6
			} , 
			stdCode : {
				required : true,
				number :true,
				minlength: 3,
				maxlength: 5
			} ,
			landlineNo : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 10
			} ,
			blAddress1 : "required",
			blLocality : "required",
			blCity : "required",
			blArea : "required",
			blMandal : "required",
			blPinCode : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 6
			} , 
			blStdCode : {
				required : true,
				number :true,
				minlength: 3,
				maxlength: 5
			} ,
			blLandlineNo : {
				required : true,
				number :true,
				minlength: 6,
				maxlength: 10
			}
		},
		messages : {
			date : "Please enter Date"
		},
		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			var pmntResponsible = $('input[name="paymentResponsible"]:checked').val();
			var emailId = $('#emailId').val();
			if(pmntResponsible == "1" && emailId != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if((pmntResponsible == "0" && emailId != "") || (pmntResponsible == "0" && emailId == "")) {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else {
				$('#emailError').text("Please enter valid EmailId.");
			}
		}
	});

	$("#paymentForm").validate({
		rules : {
			paymentMode : "required",
			paidAmount : "required"
			//ddNo : "required",
			//bankName : "required",
			//branchName : "required"
		},
		messages : {
			userFirstname : "Please enter First Name",
			userLoginid : "Please enter Login Id",
			//ddNo : "Please enter DD Number",
			//bankName : "Please enter Bank Name",
			//branchName : "Please enter Branch Name",
		},
		
		errorPlacement : function(error, element) {
			error.insertBefore(element);
			ajaxindicatorstop();
		},

		submitHandler : function(form) {
			var ruserLimit = 0;
			var totalCharge = 0;
			var paidAmount = 0;
			//var feasibility = $('#feasibility').val();
			var custType = $('#custType').val();
			var entCustType = $('#entCustType').val();
			if(custType == "ENTERPRISE" && entCustType == "GOVT") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else {
				if($('#ruserLimit').val() != "") {
					ruserLimit = parseFloat($('#ruserLimit').val());
				}
				if($('#totalProdCharge').val() != "") {
					totalCharge = parseFloat($('#totalProdCharge').val());
				}
				if($('#paidAmount').val() != "") {
					paidAmount = parseFloat($('#paidAmount').val());
				}
				
				form.submit();
				
/*				if(ruserLimit != 0) {
					if(paidAmount >= totalCharge) {
						if(paidAmount <= ruserLimit) {
							form.submit();
							ajaxindicatorstart('Please Wait...');
						} else {
							$('#paymentError').text("In Sufficient Wallet Balance.");
						}
					} else {
						$('#paymentError').text("Amount to be paid should be greater than or equal to the Total Payable Amount.");
					}
				} else {
					$('#paymentError').text("Amount to be paid should be greater than or equal to the Total Payable Amount.");
				}*/
			}
		} 
	});
	
	/*$("#monthlyPaymentForm").validate({
		rules : {
			mobileNo : {
				required : true,
				number : true,
				maxlength : 10,
				minlength: 10
			},
		},
		messages : {
			mobileNo : "Please enter Minimum 10 Numbers",
		},

		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			$('#mymonthlyPaymentForm').html("");
			form.submit();
			ajaxindicatorstart('Please Wait...');
		} 
	});*/
	$("#mymonthlyPaymentForm").validate({
		rules : {
			
		},
		messages : {
			
		},

		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			$('#mymonthlyPaymentForm').html("");
			form.submit();
			ajaxindicatorstart('Please Wait...');
		} 
	});
	$("#makeMonthlyPaymentForm").validate({
		rules : {
			paymentMode : "required",
			paidAmount : "required"
		},
		messages : {
			userFirstname : "Please enter First Name",
			userLoginid : "Please enter Login Id",
		},
		
		errorPlacement : function(error, element) {
			error.insertBefore(element);
			ajaxindicatorstop();
		},

		submitHandler : function(form) {
			var ruserLimit = 0;
			var paidAmount = 0;
			var tenantType = $('#tenantType').val();
			if($('#ruserLimit').val() != "") {
				ruserLimit = parseFloat($('#ruserLimit').val());
			}
			if($('#paidAmount').val() != "") {
				paidAmount = parseFloat($('#paidAmount').val());
			}
			if(tenantType == "LMO") {
				if(ruserLimit != 0 && paidAmount != 0) {
					if(paidAmount <= ruserLimit) {
						form.submit();
						ajaxindicatorstart('Please Wait...');
					} else {
						$('#paymentError').text("In Sufficient Wallet Balance.");
					}
				} else {
					$('#paymentError').text("Amount to be paid should be Greater than zero. or In Sufficient Wallet Balance.");
				}
			} else {
				if(paidAmount > 0) {
					form.submit();
					ajaxindicatorstart('Please Wait...');
				} else {
					$('#paymentError').text("Amount to be paid should be Greater than zero. or In Sufficient Wallet Balance.");
				}
			}
		}
	});
	
	
	$("#addPackagepaymentForm").validate({
		rules : {
			paymentMode : "required",
			paidAmount : "required"
		},
		messages : {
			userFirstname : "Please enter First Name",
			userLoginid : "Please enter Login Id",
		},
		
		errorPlacement : function(error, element) {
			error.insertBefore(element);
			ajaxindicatorstop();
		},

		submitHandler : function(form) {
			var ruserLimit = 0;
			var paidAmount = 0;
			var tenantType = $('#tenantType').val();
			var pkgType = $.trim($('.pkgType').text());
			var custBal = parseFloat($('#custBal').val());
			var totalPayableCharge = parseFloat($('#totalProdCharge').val());
			var oldPackageCode = $('#oldPackageCode').val();
			var newPackageCode = $('#newPackageCode').val();
			if($('#ruserLimit').val() != "") {
				ruserLimit = parseFloat($('#ruserLimit').val());
			}
			if($.isNumeric($('#paidAmount').val()) ) {
				paidAmount = parseFloat($('#paidAmount').val());
			}
			if(pkgType == "Add On" || pkgType == "One Time" || pkgType == "Base") {    //packageType if AddOn
				if(tenantType == "LMO") {
					if(oldPackageCode === "HomeMini" && newPackageCode === "HomeBasic"){
						form.submit();
						ajaxindicatorstart('Please Wait...');
					}
					else{
						if(ruserLimit > 0) {
							if(paidAmount - custBal - totalPayableCharge >= 0 && paidAmount <= ruserLimit) {
								form.submit();
								ajaxindicatorstart('Please Wait...');
							} else {
								$('#paymentErrors').text("In Sufficient Wallet Balance.");
							}
						} else {
							$('#paymentErrors').text("Amount to be paid should be Greater than zero. or In Sufficient Wallet Balance.");
						}
					}
				} else {
					if(paidAmount > 0) {
						form.submit();
						ajaxindicatorstart('Please Wait...');
					} else {
						$('#paymentErrors').text("Amount to be paid should be Greater than zero. or In Sufficient Wallet Balance.");
					}
				}
			}
		}
	});

	/*$("#multiActionForm").validate({
		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			var fromDate = $('#effectiveFrom_coms').val();
			var toDate = $('#effectiveTo_coms').val();
			var status = $('#status').val();
			var aadharNo = $('#aadharNo').val();
			var cafNo = $('#cafNo').val();
			var apsflTrackId = $('#apsflTrackId').val();
			var orgName = $('#organizationName').val();
			var cafType = $('#cafType').val();
			if (fromDate != "" && toDate != "" && status != "" && aadharNo != "" && cafNo != "" && apsflTrackId != "" && orgName != "" && cafType != "" ) {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate != "" && toDate != "" && status != "" && aadharNo != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate != "" && toDate != "" && status != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate != "" && toDate != "" && cafNo != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate != "" && toDate != "" && aadharNo != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate == "" && toDate == "" && aadharNo != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate == "" && toDate == "" && cafNo != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate == "" && toDate == "" && status != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate == "" && toDate == "" && apsflTrackId != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate == "" && toDate == "" && cafType != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate == "" && toDate == "" && orgName != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else if (fromDate != "" && toDate != "") {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else {
				$('#error').text("Please Select From and To Date OR any one of the Field .");
			}
		
		}
	});*/
	
	$("#saveEditForm").validate({
		rules : {
			emailId : {
				required : true,
				email : true
			},
			mobileNo : {
				required : true,
				number :true,
				minlength: 10,
				maxlength: 10
			},
			stdCode : {
				number :true,
				minlength: 3,
				maxlength: 5
			} ,
			landLineNo : {
				number :true,
				minlength: 6,
				maxlength: 10
			} ,
			mobileNo1: {
				number :true,
				minlength: 10,
				maxlength: 10
			},
		},
		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
				form.submit();
		}
	});
});


$("#monthlyPaymentForm").validate({
	errorPlacement : function(error, element) {
		error.insertBefore(element);
	},
	submitHandler : function(form) {
		//alert("nadsncjanjcn");
		var mobileNo = $('#mobileNo').val();
		var cafNo = $('#cafNo').val();
		if (mobileNo != "" || cafNo!= "") {
			form.submit();
			ajaxindicatorstart('Please Wait...');
		} else {
			$('#error').text("Please Enter Either Mobile No or Caf No.");
		}
	
	}
});

