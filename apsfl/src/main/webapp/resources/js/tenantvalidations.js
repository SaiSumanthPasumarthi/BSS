/*----------------LMO Validations -----------------*/

$(document).ready(function() {
	
	packageCount = 0;
	var tenantTypeList = [];
	var flag = true;
	
	var rsTemplType = "";
	
	$('#radioDivId1').hide();
	$('#SinglRev').hide();
	
	var prodTable = $('#productsListTable').dataTable({
		 "sPaginationType": "full_numbers",
		 "lengthMenu": [5,10,20, 40, 60, 80, 100],
		 "pageLength": 5
	});
	
	
	// When we select multiple packages, storing all products information in hidden table
	$('#productsListTable').on("click",'input[type=checkbox]',function() {
		if ($(this).is(":checked")) {
			var checkId = $(this).val();
			$('#productsListTable').find('tr').each(function() {
				if ($(this).find('input[type="checkbox"]').is(':checked')) {
					if(checkId === $(this).find('input[type="checkbox"]').val()){
						var prodCode = $(this).find('.prodCode').text();
						var tenantCode = $(this).find('.tenantCode').text();
						var effectiveFrom = $(this).find('.effectiveFrom').text();
						var prodName = $(this).find('.prodName').text();
						var prodType = $(this).find('.prodType').text();
						 $('#selectedProductTableHidden').append('<tr>'
									+'<td class="id">' +checkId+'</td>'
									+'<td class="prodCode">' +prodCode+'</td> '
									+'<td class="tenantCode">' +tenantCode+'</td> '
									+'<td class="effectiveFrom">' +effectiveFrom+'</td> '
									+'<td class="prodName">' +prodName+'</td> '
									+'<td class="prodType">' +prodType+'</td> '
									+'</tr> ');
						 packageCount++;
						 getAllServcsBySelectedProducts();
					}
				}
			});
		}else{
			var checkId = $(this).val();
			$('#selectedProductTableHidden').find('tr').each(function() {
				if(checkId === $(this).find('.id').text()){
					$(this).remove();
					packageCount--;
					getAllServcsBySelectedProducts();
				}
			});
		}
});
	
		// Getting All Selected Products From Products Hidden Table and Getting All Services Of The Product To Display
		function getAllServcsBySelectedProducts(){
	    ajaxindicatorstart("Loading..")
		$("#productinfo").show();
		$("#prodServicelistTable > tbody").html("");
		$('#selectedProductTableHidden').find('tr').each(function() {
				var prodCode = $(this).find('.prodCode').text();
				var tenantCode = $(this).find('.tenantCode').text();
				var effectiveFrom = $(this).find('.effectiveFrom').text();
				var prodCode = $(this).find('.prodCode').text();
				 $.ajax({ 
		    	     url: 'getAllServicesByProduct', 
		    	     data: {prodCode:prodCode,tenantCode:tenantCode,effectiveFrom:effectiveFrom},
		    	     contentType: 'application/json',
		    	     async: false,
		    	     success: function(data) {
		    	    	  $.each(data, function(idx, obj){ 
							  $('#prodServicelistTable').append('<tr>'
									+'<td>' +obj.prodName+'</td> '
									+'<td>' +obj.srvcName+'</td> '
									+'<td>' +obj.componentCode+'</td>'
									+'<td>' +obj.charTypeName+'</td> '
									+'<td>' +obj.chargeAmt+'</td> '
									);
						  			
						  		});
		    	     }
				 });
		 })
		ajaxindicatorstop();
		}
		
	// To Get Template Information By Selected  Template Code and getting tenants list by template to append drop down 
	$("#selecttoshow").click(function() {
		$(document).ajaxStart(function () {
		 //   $('#loading').show();  // show loading indicator
			ajaxindicatorstart("Loading details.Please Wait...");
		});
		$('#SinglRev').hide();
		
		var tenantNo;
		var arr = [];
		i = 0;
		var flag=false;
		 var prodType="";
		$("#selecedtemplatedata").show();
		$("#selecedtemplatedata").html("");
		
		 $('#selectedProductTableHidden').find('tr').each(function() {
			  prodType = $(this).find('.prodType').text();
		 });
		 
		 
		$('#templateList').find('tr').each(function() {
			var row = $(this);
			if (row.find('input[type="radio"]').is(':checked')) {
				var templCode = row.find('.tmplCode').text();
				$('#templCodeHidden').val(templCode);
				 $.getJSON("getAllTenantsByTemplCode", {tmplCode:templCode}, function(data){
					
					 tenantTypeList = [];
					 var html = "";
					 var tabelBody ="";
					 html ='<table class="table table-alt ScrollStyle" id="tenantsListTable" >'
						 	+' <thead>'
						 	+'<tr>'
						 	+'<th>Sno</th>'
						 	+'<th>Tenant Type</th>'
						 	+'<th>Select Tenant</th>'
						 	+'</tr>'
						 	+'</thead>'
						 	+'<tbody>';
					 $.each(data, function(idx, obj){ 
						 var tenantTypeAndSno = {
								 tenantType : obj.tenantType,
								 sNo : obj.sno,
						 }
						 tenantTypeList.push(tenantTypeAndSno);
						 arr[i++] =obj.tenantType;
						 html = html +'<tr>'
						 				+'<td>'+obj.sno+'</td>'
						 				+'<td>'+obj.tenantType+'</td>'
						 				+'<td><select class="form-control form-white SearchTenantClass" name="tenantSelect"  id="'+idx+'" width = "90%">'
						 				+'<option value="">-Select-</option>';
						 $.each(obj.tenantList, function(idx, tenant){
							 html = html +'<option value = "'+tenant.tenantCode+'">'+tenant.name+' | '+tenant.tenantCode+'</option>'
						 });
						 html = html + '</select>'
						 				+'</td>'
						 				+'</tr>';
					 });
					 html = html + '</tbody>'
					 				+ '</table>';
					 $('#selecedtemplatedata').append(html);
					 $('.SearchTenantClass').SumoSelect({search: true,});
					 $('#radioDivId1').show();
					 if(arr.length==2){
						 $.each(arr , function(index, val) { 
							  if(val=='LMO' || val=='APSFL'){
								  if(prodType=='One Time' && packageCount===1){
									  flag=true;
								  }
							  }
						});
					 }
					 if(flag){
						 $('#SinglRev').show();
					 }
					 
				 });
			};
		});
		$(document).ajaxStop(function() 
				{
				 //   $('#loading').hide();  // hide loading indicator
			ajaxindicatorstop();
				});
	});

// On Select Radio Button, we will get all selected products from hidden table and create new table with products for multiple template select 
	$(document).on('change','.addOnClass11',function() {
		 $('#selectedProdDivId').html("");
		 $('.backButtonDivId').html("");
		var templCodeHidden = $('#templCodeHidden').val();
		if ($(this).val() == "multiTempl") {
			$('#multipleTempDevId').show();
			$("html, body").animate({ scrollTop: 0 }, "fast");
			var count = 0;
			var html ='<table class="table  table-alt" id="selectedProdlistTableId">'
				 	+' <thead>'
				 	+'<tr>'
				 	+'<th>Package Name</th>'
				 	+'<th>Select Revenue Sharing Template</th>'
				 	+'<th hidden="hidden">Effective From</th>'
					+'<th hidden="hidden">Product Code</th>'
					+'<th hidden="hidden">Tenant Code</th>'
					+'<th hidden="hidden">Product Type</th>'
				 	+'</tr>'
				 	+'</thead>'
				 	+'<tbody>';
			 $.getJSON("getAllSimilarTemplatesByTemplCode", {templCode:templCodeHidden}, function(data){
				 $('#selectedProductTableHidden').find('tr').each(function() {
						var prodCode = $(this).find('.prodCode').text();
						var tenantCode = $(this).find('.tenantCode').text();
						var effectiveFrom = $(this).find('.effectiveFrom').text();
						var prodName = $(this).find('.prodName').text();
						var prodType = $(this).find('.prodType').text();
						
						html = html+ '<tr><td>'+prodName+'</td>'
									+ '<td><select class="form-control form-white templClass" name="templSelect" >'
									+ '<option value="">-Select-</option>';
									$.each(data, function(idx, template){
										html = html + '<option value = "'+template.rstmplCode+'">'+template.rstmplName+'</option>';
									 });
									html = html	+ '</select></td>'
									+ '<td hidden="hidden" class="effectiveFrom">'+effectiveFrom+'</td>'
									+ '<td hidden="hidden" class="prodCode">'+prodCode+'</td>'
									+ '<td hidden="hidden" class="tenantCode">'+tenantCode+'</td>'
									+ '<td hidden="hidden" class="prodName">'+prodName+'</td>'
									+ '<td hidden="hidden" class="prodType">'+prodType+'</td></tr>';
						count++;
				});
				 
				 html = html+ '</tbody></table>';
					if(count != 0){
						
						$('#tenantsListTable > tbody > tr').each(function() {
							var row = $(this);
							var slno = row.find('td:eq(0)').text();
							var tenantType = row.find('td:eq(1)').text();
							var partnerCode = $(this).find('select[name="tenantSelect"] option:selected').val();
							if(partnerCode == ""){
								$('.addOnClass11').prop('checked', false);
								alert("Please select tenant at sno :: " +slno);
								flag = false;
								return false;
							}
						});
					if(flag){
						 $('.divHideAndShow').hide();
						 $('#selectedProdDivId').append(html);
						 $('.backButtonDivId').append('<button class="btn btn-primary btn-embossed pull-right" type="button" id="backButtonId" >Back</button>');
						 rsTemplType = 'M';
					 }else{
						 flag = true;
					 }
					}
					else{
						alert("Please Select Package ..")
						$('.addOnClass11').prop('checked', false);
					}
						
			 });
			
		}else if ($(this).val() == "SinglRev"){
			$('#selectedProdDivId').html("");
			 $('#selectedTempViewDivId').html("");
			 rsTemplType = 'SR';
		}
		else{
			 $('#selectedProdDivId').html("");
			 $('#selectedTempViewDivId').html("");
			 rsTemplType = 'S';
		}
	
	});
	
	//To Get Template By Selected Template Code Drop Down
	
	$(document).on('change', '.templClass',function(){
		ajaxindicatorstart("Loading..");
		var tCode = $(this).val();
		//var templName = $(this).text();
		var templName = $("option:selected",this).text();
		var html = "";
		
		 html = html + '<div class="panel  col-sm-12 "><div class="panel-header bg-light">'
		 	+'<div class="row">'
			+'<div class="col-sm-6"><h3>Revenue Sharing Details</h3></div>'
			+'<div class="col-sm-6"><h3>'+templName+'</h3></div> </div></div>' 
			+'<div class="row panel-content pre-scrollable">';
	
		 $("#selectedTempViewDivId").html("");
		 $.ajax({ 
    	     url: 'getAllTemplateRegions', 
    	     data: {tmplCode:tCode},
    	     contentType: 'application/json',
    	     async: false,
    	     success: function(regions) { 
    	    	 console.log(regions);
			 $.each(regions, function(idx, reg){
				 $.ajax({ 
		    	     url: 'getAllTenantTypeByTemplateCode', 
		    	     data: {tmplCode:tCode, region : reg[1]},
		    	     contentType: 'application/json',
		    	     async: false,
		    	     success: function(data) {
					 html = html+'<label class="control-label">'+reg[0]+'</label>'	
					 			+'<table  class="table  table-alt" > <thead> <tr>';
								  $.each(data, function(idx, obj){ 
									  html = html+'<th>'+obj.tenanttype+'</th>';
								  		});
								  html = html+'</tr></thead> <tbody><tr>';
								  $.each(data, function(idx, obj){ 
									  html = html+'<td>'+obj.revshareperc+'%</td>';
								  		});
								  html = html+'</tr> </tbody></table>';
				 			}
						 });
			 });
			 html = html+'</div> </div>';
			 $('#selectedTempViewDivId').append(html);
			 html="";
    	    }
		 });
		
		 ajaxindicatorstop();
		
	});
	
	// To get Templates by no of partners 
	$('#modalbutton').click(function (){
		$("#rsPopUpDiv").html("");
		 $('#selectedProdDivId').html("");
		 $('#selectedTempViewDivId').html("");
		 $('.addOnClass11').prop('checked', false);
		var count = $('#count').val();
		  $.getJSON("getTemplatesByCount", {count:count}, function(data){
			  $("#templateList > tbody").html("");
			  $.each(data, function(idx, obj){ 
				  $('#templateList').append('<tr>'
						+'<td><input type="radio"  class="radioClass" name="Code" ></td>'
						+'<td class ="tmplCode">' +obj.rstmplCode+'</td>'
						+'<td>' +obj.rstmplName+'</td> '
						+'<td>' +obj.partnerCnt+'</td></tr> ');
			  			
			  		});
				 });
	})

	// To Get template Details by template code.

	$('#templateList').on('click' ,'input[type=radio]', function() {
			if ($(this).is(":checked")) {
				var html = "";
					$('#templateList').find('tr').each(function() {
					var row = $(this);
					if (row.find('input[type="radio"]').is(':checked')) {
						var tCode = row.find('.tmplCode').text();
						 $("#rsPopUpDiv").html("");
						 $.ajax({ 
			        	     url: 'getAllTemplateRegions', 
			        	     data: {tmplCode:tCode},
			        	     contentType: 'application/json',
			        	     async: false,
			        	     success: function(regions) { 
						/* $.getJSON("getAllTemplateRegions", {tmplCode:tCode}, function(regions){*/
							 $.each(regions, function(idx, region){
								 $.getJSON("getAllTenantTypeByTemplateCode", {tmplCode:tCode, region : region[1]}, function(data){
									 html = html+'<div class="row"><label class="control-label">'+region[0]+'</label>'	
									 			+'<table  class="table  table-alt"> <thead> <tr>';
									  $.each(data, function(idx, obj){ 
										  html = html+'<th>'+obj.tenanttype+'</th>';
									  		});
									  html = html+'</tr></thead> <tbody><tr>';
									  $.each(data, function(idx, obj){ 
										  html = html+'<td>'+obj.revshareperc+'%</td>';
									  		});
									  		html = html+'</tr> </tbody></table>';
									  		 $('#rsPopUpDiv').append(html);
									  		html="";
										 });
							 });
			        	     }
						 });
					} 
				});
				$("#sharedetails").show();
				$("#selecttoshow").prop("disabled", false);// popup select
															// disable operation
			} else {
				$("#sharedetails").hide();
				$("#selecttoshow").prop("disabled", true);// popup select
															// enable operation
			}
		});
	
	//Back Button Function
	
			$(document).on('click', '#backButtonId',function(){
				$('#selectedProdDivId').html("");
				$('#selectedTempViewDivId').html("");
				$('.divHideAndShow').show();
				rsTemplType = "";
				$('#multipleTempDevId').hide();
				
				$('.addOnClass11').prop('checked', false);
			});
	
	
	
	
	//Saving Agreement Data
	$('#saveProductAgreement').click(function(){
		ajaxindicatorstart("Loading..");
		var prodCode = "";
		var tenantCode = "";
		var effectiveFrom = "";
		var templCodeHidden = $('#templCodeHidden').val();
		var localFlag = true;
		var tenantDetailsList = [];
		
		if(packageCount === 0){
			alert("Please select package ...");
		} else if ($.trim( $('#selecedtemplatedata').html() ).length) {
			// To Get All Selected Tenants and creating tenants object
			$('#tenantsListTable > tbody > tr').each(function() {
				var row = $(this);
				var slno = row.find('td:eq(0)').text();
				var tenantType = row.find('td:eq(1)').text();
				var partnerCode = $(this).find('select[name="tenantSelect"] option:selected').val();
				if(partnerCode == "" && rsTemplType === 'SR'){
					localFlag = true;
				}else if(partnerCode == ""){
					$('#selectedProdDivId').html("");
					$('#selectedTempViewDivId').html("");
					$('.divHideAndShow').show();
					$('#multipleTempDevId').hide();
					$('.addOnClass11').prop('checked', false);
					alert("Please select tenant at sno :: " +slno);
					localFlag = false;
					return false;
				}
				
			 	var tenantObj = {
		       			"partnertype":tenantType,
		    		    "partnertypeslno": slno,
		    		    "partnercode": partnerCode,
		    		};
			 	// Adding All Tenant objects to tenants List
			 	tenantDetailsList.push(tenantObj);
			});
			
			if(localFlag){
				var packageAgreeList = [];
				
						 // If having single template for all packages
						 if(rsTemplType === 'S'){
								//To get all selected products from hidden table
							 $('#selectedProductTableHidden').find('tr').each(function() {
							 prodCode = $(this).find('.prodCode').text();
							 tenantCode = $(this).find('.tenantCode').text();
							 effectiveFrom = $(this).find('.effectiveFrom').text();
							 var prodName = $(this).find('.prodName').text();
							 var prodType = $(this).find('.prodType').text();
							 var productAgreementObj = {
					        		    "tenantCode": tenantCode,
					        		    "prodCode": prodCode,
					        		    "agrFDate": effectiveFrom,
					        		    "tmplCode": templCodeHidden,
					        		    "prodName": prodName,
					        		    "partnrsList": tenantDetailsList,
					        		    "prodType":prodType,
					        		    "flag":rsTemplType,
					        		};
							 packageAgreeList.push(productAgreementObj);
						 });
						 } else if(rsTemplType === 'SR'){
								//To get all selected products from hidden table
							 $('#selectedProductTableHidden').find('tr').each(function() {
							 prodCode = $(this).find('.prodCode').text();
							 tenantCode = $(this).find('.tenantCode').text();
							 effectiveFrom = $(this).find('.effectiveFrom').text();
							 var prodName = $(this).find('.prodName').text();
							 var prodType = $(this).find('.prodType').text();
							 var productAgreementObj = {
					        		    "tenantCode": tenantCode,
					        		    "prodCode": prodCode,
					        		    "agrFDate": effectiveFrom,
					        		    "tmplCode": templCodeHidden,
					        		    "prodName": prodName,
					        		    "partnrsList": tenantDetailsList,
					        		    "prodType":prodType,
					        		    "flag":rsTemplType,
					        		};
							 packageAgreeList.push(productAgreementObj);
						 });
						 }else if(rsTemplType === 'M'){
							 // If having separate template for each package
							 
							 // To get all selected products and its template code
							 $('#selectedProdlistTableId > tbody > tr').each(function() {
								 	 prodCode = $(this).find('.prodCode').text();
									 tenantCode = $(this).find('.tenantCode').text();
									 effectiveFrom = $(this).find('.effectiveFrom').text();
									 var prodName = $(this).find('.prodName').text();
									 var prodType = $(this).find('.prodType').text();
									var templCode = $(this).find('select[name="templSelect"] option:selected').val();
									if(templCode == ""){
										var index = $(this).index();
										var templateNo = index+1
										alert("Please select Template at sno :: " + templateNo);
										localFlag = false;
										return false;
									}
									 var productAgreementObj = {
							        		    "tenantCode": tenantCode,
							        		    "prodCode": prodCode,
							        		    "agrFDate": effectiveFrom,
							        		    "tmplCode": templCode,
							        		    "prodName": prodName,
							        		    "partnrsList": tenantDetailsList,
							        		    "prodType":prodType,
							        		    "flag":rsTemplType,
							        		};
									 packageAgreeList.push(productAgreementObj);
								});
							 
							 
						 }else{
							// If Radio button is not selected
							 alert("Please Select Revenue Share Type For Package ..");
							 localFlag = false;
							 return false;
						 }
						 
					
						 
				
				if(localFlag && flag){
					//Adding All packages to package list for saving 
					ajaxindicatorstart("Loading..");
					$("#saveProductAgreement").attr("disabled", "disabled");
					packageAgreeList = JSON.stringify(packageAgreeList);
				 	$.ajax({ 
		        	     type: 'POST', 
		        	     url: 'saveProductAgreementURL', 
		        	     data: packageAgreeList,
		        	     contentType: 'application/json',
		        	     success: function(response) { 
		        	    	 if(response === "Success"){
		        	    		 alert("Package agreement is created");
		        	    		if ( sendTenantsDataToCorpus(packageAgreeList)){
		        	    		 }
	        	    		 
		        	    		 $("#saveProductAgreement").removeAttr("disabled");
		        	    		 location.reload();
		        	    		
		        	    	 }
		        	    	 else
		        	    		 alert("Agreement is already done with the same tenants and "+response);
		        	    	 	$("#saveProductAgreement").removeAttr("disabled");
		        	     },
		        	  });
				 	ajaxindicatorstop();
				}
			}
			
		}else{
			alert("Please Select Template ...")
			ajaxindicatorstop();
		}
		ajaxindicatorstop();
	});
	// End Of Product Agreement
	
	
	
	
	//Posting LMO/MSO data to corpus
	function sendTenantsDataToCorpus(packageAgreeList){
		
		
		$.ajax({ 
   	     type: 'POST', 
   	     url: 'createPartner', 
   	     data: packageAgreeList,
   	     contentType: 'application/json',
   	     success: function(response) { 
   	    	 if(response === "Success"){
   	    		 alert("post  data to corpus " +response); 
   	    		 return true;
   	    	 }
   	    	 else
   	    		 alert("Post to corpus "+response);
   	    	 return false;
   	    	 	
   	     },
   	  });
		return true;
	};
	
	
	$(".number").on("keydown", function (e) {
		  	var ctrlDown = false;
	        var ctrlKey = 17, vKey = 86, cKey = 67;
	        if (e.keyCode === ctrlKey){
	            ctrlDown = true;
	        }
	        // Allow: backspace, delete, tab, escape, enter and .
	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110]) !== -1 ||
	                // Allow: Ctrl
	            (e.keyCode === ctrlKey) ||
	                // Allow: Ctrl+A
	            (e.keyCode === 65 && e.ctrlKey === true) ||
	                // Allow: Ctrl+v
	            (e.keyCode === vKey && ctrlDown) ||
	                // Allow: Ctrl+c
	            (e.keyCode === cKey && ctrlDown) ||
	                // Allow: home, end, left, right, down, up
	            (e.keyCode >= 35 && e.keyCode <= 40)) {
	            // let it happen, don't do anything
	            return;
	        }
	        // Ensure that it is a number and stop the keypress
	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	            e.preventDefault();
	        }
	}); 
	$(".nonumbers").keypress(function (e) {
	     if (!(e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57))) {
	    	 return false;
	     }
	});  
	/*$(".numbersOnly").on("keypress keyup blur",function (event)
	$(".numbersOnly").keypress(function (event) {
	      $(this).val($(this).val().replace(/[^0-9\.]/g,''));
	            if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
	                event.preventDefault();
	            }
	});*/
		        
	$('.specialCharacters').keyup(function() {
		var yourInput = $(this).val();
		re = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar) {
			var no_spl_char = yourInput.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
			$(this).val(no_spl_char);
		}
	});
	
	/*function lettersOnly(evt)*/
	$(".lettersOnly").keypress(function (evt) {
	       evt = (evt) ? evt : event;
	       var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :
	          ((evt.which) ? evt.which : 0));
	       if (charCode > 31 && (charCode < 65 || charCode > 90) &&
	          (charCode < 97 || charCode > 122)) {
	          return false;
	       }
	       return true;
	});
	
	$("#creditLimitId").numeric();
	
	var tenantType = $("#tenantTypeLov option:selected").text();
	if(tenantType === 'LMO'){
		$("#creditLimitId").attr("readonly", false);
	}else{
		$("#creditLimitId").attr("readonly", true);
	}
	
	$('#tenantTypeLov').change(function(){
		var tenantType = $("#tenantTypeLov option:selected").text();
		if(tenantType === 'LMO'){
			$("#creditLimitId").attr("readonly", false);
		}else{
			$("#creditLimitId").attr("readonly", true);
		}
	   });
	
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentDate = date.getDate();
	var currentYear = date.getFullYear();
	
	
	/*----------------Date Validation In Date Picker -----------------*/
	
	$("#agreementDate").datepicker();
	$("#agreementFrom").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        minDate : new Date(currentYear, currentMonth, currentDate),
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() + 1);
            $("#agreementTo").datepicker("option", "minDate", dt);
        }
    });
    $("#agreementTo").datepicker({
    	minDate : new Date(currentYear, currentMonth, currentDate),
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
            $("#agreementFrom").datepicker("option", "maxDate", dt);
        }
    });
    $("#portalMibLicenseExpDate").datepicker({
    	dateFormat: 'dd-mm-yy',
    	minDate : new Date(currentYear, currentMonth, currentDate),
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
         }
    });
    $("#portalDasLicenseExpDate").datepicker({
    	dateFormat: 'dd-mm-yy',
    	minDate : new Date(currentYear, currentMonth, currentDate),
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
         }
    });
    $("#portalPostExpDate").datepicker({
    	dateFormat: 'dd-mm-yy',
    	minDate : new Date(currentYear, currentMonth, currentDate),
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
         }
    });
    $("#effectiveFrom").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() + 1);
            $("#effectiveTO").datepicker("option", "minDate", dt);
        }
    });
    $("#effectiveTO").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
            $("#effectiveFrom").datepicker("option", "maxDate", dt);
        }
    });

    $("#effectiveFrom1").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() + 1);
            $("#effectiveTO1").datepicker("option", "minDate", dt);
        }
    });
    $("#effectiveTO1").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
            $("#effectiveFrom1").datepicker("option", "maxDate", dt);
        }
    });

    $("#effectiveFrom2").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() + 1);
            $("#effectiveTO2").datepicker("option", "minDate", dt);
        }
    });
    $("#effectiveTO2").datepicker({
        numberOfMonths: 1,
        changeMonth: true,
        changeYear: true,
        onSelect: function (selected) {
            var dt = new Date(selected);
            dt.setDate(dt.getDate() - 1);
            $("#effectiveFrom2").datepicker("option", "maxDate", dt);
        }
    });
    
    $("#toDate").datepicker({
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
    $("#fromDate").datepicker({
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
    
    
    $('body').on('focus',".sentTranstDate", function(){
    	$(this).datetimepicker({
        	//minDate : new Date(currentYear, currentMonth, currentDate),
    		dateFormat: 'dd-mm-yy',
    		timeFormat: 'HH:mm:ss',
            numberOfMonths: 1,
            changeMonth: true,
            changeYear: true,
            onSelect: function (selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() - 1);
             }
        });
    });

	
	$(".date-custom").datepicker({
		minDate : new Date(currentYear, currentMonth, currentDate),
	});

	$('#sendBackTextAreaId').hide();
	$('#sendBackButtonId').hide();
	$('#sendBackLableId').hide();
	$('#rejectLableId').hide();
	$('#rejectButtonId').hide();
	$('#coreServiceMulti').SumoSelect();

	$('.galleryImgs').viewer();
	
	$('#tenantTable').dataTable({"sPaginationType": "full_numbers",});
    
	
	$("#lom_form").validate({
		rules : {
			name : {
				required : true
			},
			tenantTypeLov : {
				required : true
			},
			region : {
				required : true
			},
			tenantCode : {
				required : true
			},

			licenserefno : {
				required : true
			},
			doclov : {
				required : true
			},
			/*
			 * licenceId : { required : true },
			 */
			effectiveFrom : {
				required : true
			},
			effectiveTO : {
				required : true
			},
			docUniqueId1 : {
				required : true
			},
			doclov1 : {
				required : true
			},
			/*
			 * idProof : { required : true },
			 */
			effectiveFrom1 : {
				required : true
			},
			effectiveTO1 : {
				required : true
			},
			docUniqueId2 : {
				required : true
			},
			doclov2 : {
				required : true
			},
			/*
			 * addressProof : { required : true },
			 */
			effectiveFrom2 : {
				required : true
			},
			effectiveTO2 : {
				required : true
			},

			accountNo : {
				required : true
			},
			ifscCode : {
				required : true
			},
			acctTypelov : {
				required : true
			},
			branchName : {
				required : true
			},
			bankNamelov : {
				required : true
			},
			portalEnrllmentno : {
				required : true
			},
			
		},
		// Specify the validation error messages
		messages : {
			name : "Please enter your Name",
			tenantTypeLov : "Please select Tenant Type Lov",
			tenantCode : "Please enter your Tenant Code",
			region : "Please enter your Region",

			licenceId : "Please upload your Licence ID",
			licenserefno : "Please enter your Licence Ref.No",
			doclov : "Please enter your Document Lov",
			effectiveFrom : "Please select your Effective From Date",
			effectiveTO : "Please select your Effective To Date",

			docUniqueId1 : "Please enter your Document Unique Id",
			doclov1 : "Please enter your Document Lov",
			idProof : "Please upload your ID Proof",
			effectiveFrom1 : "Please select your Effective From Date",
			effectiveTO1 : "Please select your Effective To Date",

			docUniqueId2 : "Please enter your Document Unique Id",
			doclov2 : "Please enter your Document Lov",
			addressProof : "Please upload your Address Proof",
			effectiveFrom2 : "Please select your Effective From Date",
			effectiveTO2 : "Please select your Effective To Date",

			accountNo : "Please enter your Account Number",
			ifscCode : "Please enter your IFSC Code",
			bankNamelov : "Please enter your Bank Name",
			branchName : "Please enter your Branch Name",
			acctTypelov : "Please enter your Account Type",
			
			portalEnrllmentno : "Please enter your Enrollment number",
			
		},

		submitHandler : function(form) {
			form.submit();
		}
	});

	/* -------------------- LMO Agreement Validations -------------------- */

	$("#lomAgreement_form").validate({
		rules : {
			tenantCode : {
				required : true
			},
			agreementDate : {
				required : true
			},
			agrFDate : {
				required : true
			},
			agreementTo : {
				required : true
			},

		},
		// Specify the validation error messages
		messages : {
			tenantCode : "Please select your Tenant Code",
			coreService : "Please select your Core Service",
			agrFDate : "Please select your Agreement From Date",
			agreementTo : "Please enter your Agreement To Date",
			agreementDate : "Please select your Date of Agreement",
			agreementCopy : "Please upload your Agreement Copy",
		},

		submitHandler : function(form) {
			form.submit();
		},
	});

	/* -------------------- LMO MSP Agreement Validations -------------------- */

	$("#lomAgreement_form").validate({
		rules : {
			mspCode : {
				required : true
			},
			agreementFrom : {
				required : true
			},
			agreementTo : {
				required : true
			},
			agreementDate : {
				required : true
			},

			depositAmount : {
				required : true
			},

		},
		// Specify the validation error messages
		messages : {
			mspCode : "Please select your MSP Name",
			coreserviceCode : "Please enter your Core Service",
			agreementFrom : "Please select your Agreement From Date",
			agreementTo : "Please enter your Agreement To Date",
			agreementDate : "Please enter your Agreement Date",
			agreementCopy : "Please upload your Agreement Copy",
			depositAmount : "Please enter your Deposit Amount",
			lmoWalletAmount : "Please enter your Wallet Amount",
		},

		submitHandler : function(form) {
			form.submit();
		}
	});

	/* -------------------- LMO MSP Agreement Validations -------------------- */

	$("#lmoMsp_form").validate({
		rules : {
			lmoCode : {
				required : true
			},
			coreServiceMulti : {
				required : true
			},
			agreementFrom : {
				required : true
			},
			agreementTo : {
				required : true
			},
			agreementDate : {
				required : true
			},
			lmoWalletAmount : {
				required : true
			},
			region : {
				required : true
			},

		},
		// Specify the validation error messages
		messages : {
			lmoCode : "Please select your LMO Name",
			coreserviceCode : "Please Select  your Core Service",
			agreementFrom : "Please select your Agreement From Date",
			agreementTo : "Please enter your Agreement To Date",
			agreementDate : "Please enter your Agreement Date",
			agreementCopy : "Please upload your Agreement Copy",
			lmoWalletAmount : "Please enter your Wallet Amount",
			region : "Please select Region",
		},

		submitHandler : function(form) {
			form.submit();
		}
	});

	//view agreements kiran 
	 var DistTable = $('#ViewProdAgremntTable').dataTable({
		 "sPaginationType": "full_numbers",
		 "lengthMenu": [5,10,20, 40, 60, 80, 100],
		 "pageLength": 5
	});
	
	$('#ViewProdAgremntTable').on('click' ,'input[type=radio]', function() {
		if ($(this).is(":checked")) {
			var html = "";
				$('#ViewProdAgremntTable').find('tr').each(function() {
				var row = $(this);
				if (row.find('input[type="radio"]').is(':checked')) {
					
					//starting of  View services
					$("#viewprodServicelistTable > tbody").html("");
					var prodCode = $(this).find('.prodCode').text();
					var tenantCode = $(this).find('.tenantCode').text();
					var effectiveFrom = $(this).find('.effectiveFrom').text();
					  $.getJSON("getAllServicesByProduct", {prodCode:prodCode,tenantCode:tenantCode,effectiveFrom:effectiveFrom}, function(data){
						 // $('#prodServicelistTable').empty()
						  $.each(data, function(idx, obj){ 
							  $('#viewprodServicelistTable').append('<tr>'
									+'<td>' +obj.prodName+'</td> '
									+'<td>' +obj.srvcName+'</td> '
									+'<td>' +obj.componentCode+'</td>'
									+'<td>' +obj.charTypeName+'</td> '
									+'<td>' +obj.chargeAmt+'</td> '
									/*+'<td>' +obj.glName+'</td> '
									+'<td>' +obj.glCode+'</td> '*/
									);
						  			
						  		});
							 });
					
					//start of View Revenu sharing
					var tCode = row.find('.tmplCode').text();
					alert(tCode);
					 $("#rsPopUpDiv1").html("");
					 $.getJSON("getAllTemplateRegions", {tmplCode:tCode}, function(regions){
						 $.each(regions, function(idx, region){
							 $.getJSON("getAllTenantTypeByTemplateCode", {tmplCode:tCode, region : region[1]}, function(data){
								 html = html+'<div class="row col-sm-10"><label class="control-label">&nbsp;&nbsp;&nbsp;'+region[0]+'</label>'	
								 			+'<table  class="table  table-alt"> <thead> <tr>';
								  $.each(data, function(idx, obj){ 
									  html = html+'<th>'+obj.tenanttype+'</th>';
								  		});
								  html = html+'</tr></thead> <tbody><tr>';
								  $.each(data, function(idx, obj){ 
									  html = html+'<td>'+obj.revshareperc+'%</td>';
								  		});
								  		html = html+'</tr> </tbody></table>';
								  		 $('#rsPopUpDiv1').append(html);
								  		html="";
									 });
							 });						
					 });//end of getJSON
					 
					 
				} //end of if
			});
			
		} 
	});
	
	$("#oltform").validate({
		errorPlacement : function(error, element) {
			error.insertBefore(element);
		},
		submitHandler : function(form) {
			var popdistrict = $('#popdistrict').val();
			var popmandal = $('#popmandal').val();
			if (popdistrict != "" && popmandal != "" ) {
				form.submit();
				ajaxindicatorstart('Please Wait...');
			} else {
				$('#error').text("Please Select District And Mandal.");
			}
		}
	});
	
});

function clicked() {
	if (confirm('Do you want to submit?')) {
		yourformelement.submit();
	} else {
		return false;
	}
}

$("#agreementCopy, #addressProof, #idProof, #licenceId")
		.change(
				function() {
					var max_file_size = 10;
					var min_file_size = 3;
					var f_bytes = this.files[0].size;
					var name = this.files[0].name;

					var minname = 2;
					var firstindex = name.substr(0, name.lastIndexOf('.'));

					/*
					 * if(name.split(".").length>2 || name.split("%").length>1 ||
					 * name.split("0").length>1 || firstindex.length<minname) {
					 * alert("Numbers & Spaces are not Allowed in File Name");
					 * $("#jpgtpdx").val(""); return false; }
					 */

					{
						var ext = name.substring(name.lastIndexOf(".") + 1,
								name.length);
						if (ext != 'jpg' && ext != 'JPG' && ext != 'JPEG'
								&& ext != 'JPEG' && ext != 'png'
								&& ext != 'PNG' && ext != 'pdf' && ext != 'PDF')

						{
							alert('You selected a " .'
									+ name.substring(name.lastIndexOf(".") + 1,
											name.length)
									+ ' "  file;  Please Select a " .jpg/ .jpeg/ .png/ .pdf/ " file.');
							$(
									"#agreementCopy, #addressProof, #idProof, #licenceId")
									.val("");
							return false;
						} else {
							var type = this.files[0].type;
							var f_kBytes = f_bytes / 1024;
							var f_kBytesMin = f_bytes / 1024;
							var f_mbytes = f_kBytes / 1024;

							if (f_mbytes > max_file_size) {
								alert("The size of the file your trying to upload exceeds the limit");
								$(
										"#agreementCopy, #addressProof, #idProof, #licenceId")
										.val("");
							}
							if (f_kBytesMin < min_file_size) {

								alert("The size of the file your trying to upload did not meet the minimum limit");
								$(
										"#agreementCopy, #addressProof, #idProof, #licenceId")
										.val("");
							}
							return true;
						}
					}
				});
