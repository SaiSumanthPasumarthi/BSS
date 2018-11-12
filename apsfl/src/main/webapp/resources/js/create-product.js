$(function() {
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentDate = date.getDate();
	var currentYear = date.getFullYear();
	$("#Datepicker1").datepicker({
	minDate: new Date(currentYear, currentMonth, currentDate),
	changeMonth: true,
	changeYear: true,
	});
});

	function enableOrDisSelect(v){	
		$(".rowdata").not("#intr"+v).hide();
		$("#intr"+v).toggle();	
		}

	function getChargeCodes(value,rowCount,srvcName,coreSrvcCode){	
		
		$('#rowCount').val(rowCount);
		$('#srvcName').val(srvcName);
		$('#coreSrvcCode').val(coreSrvcCode);

		var html = '<table class="table  table-alt" id = "chargeDynTableId"><thead>'
			+'<th>Charge Name</th>'
			+'<th> Charge Amount</th>'
			+'<th hidden> Charge Code</th>'
			+'<th> Gl Code</th>'
			+'<th> Tax Amount</th>'
			+'</thead>'
			+'<tbody>';
		$.ajax({ 
	   		 async:false,
	   	     type: 'GET', 
	   	     url: 'getChargeCodesForPackagePriceVersioning', 
	   	     data: {value:value},
	   	     contentType: 'application/json',
	   	     success: function(data) { 
	   	    	 $('#chargeCodesTableForPV').html("");
				 $.each( data, function(id, chargeCode){
		 					 html = html +'<tr><td class = "chargeName">'+chargeCode.chargeName+'</td>'
		 					 			 +'<td ><input type="text" class="form-control form-white numbersOnly chargeAmt" maxlength="80"  /></td>'
		 					 		   	+'<td class = "chargeCode" hidden>'+chargeCode.chargeCode+'</td>'
		 					 			+'<td class = "glCode">'+chargeCode.glCode+'</td>'
		 					 			+'<td class = "taxAmt">'+chargeCode.taxAmt+'</td></tr>'
				 });
				 		html = html +' </tbody></table>';
						$('#chargeCodesTableForPV').append(html);
	   	     }
			});
		}
	
	function getValuesFromPopupAndSetToSelectedTable(){
		var loopIndex = 0;
		var srvcName = $('#srvcName').val();
		var coreSrvcCode = $('#coreSrvcCode').val();
		var rowCount = $('#rowCount').val();
		var srvcIndex = rowCount -1 ;
		var chargIndex = $('#inTable'+rowCount+' > tbody > tr').length;
		$('#chargeDynTableId > tbody').find('tr').each(function() {
			var chargeName = $(this).find('.chargeName').text();
			var chargeAmt = $(this).find('.chargeAmt').val();
			var chargeCode = $(this).find('.chargeCode').text();
			var glCode = $(this).find('.glCode').text();
			var taxAmt = $(this).find('.taxAmt').text();
			
			if(chargeAmt != ""){
				var html = '<tr>';
				if(coreSrvcCode == 'VOIP')
					html = html + '<td>'+srvcName+'(S)</td>';
				html = html +'<td>'+chargeName+'</td>'
				+'<td><input name="servicesList['+srvcIndex+'].chargesList['+chargIndex+'].chargeAmt" class="form-control form-white numbersOnly" value="'+chargeAmt+'" required="required"> </td>'
				+'<td>'+taxAmt+'</td>'
				+'<td>'+glCode+'</td>'
				+'<td hidden="hidden"><input name="servicesList['+srvcIndex+'].chargesList['+chargIndex+'].featureCode" value="0"/></td>'
				+'<td hidden="hidden"><input name="servicesList['+srvcIndex+'].chargesList['+chargIndex+'].chargeCode" value="'+chargeCode+'"/></td>'
				+'</tr>';
				
				$('#inTable'+rowCount+' > tbody ').append(html);
				chargIndex = chargIndex + 1;
				loopIndex = loopIndex + 1;
			}
		if(loopIndex > 0)
			$('#imgp'+rowCount).hide();
	 })
	
	}

function showdata(v){	
	$(".rowdata").not("#intr"+v).hide();			
	$("#intr"+v).toggle();	

	if ($("#intr"+v).is(':visible'))
	 { 
		$("#imgp"+v).hide();
		$("#imgm"+v).show();	
			
		$(".imgm").not("#imgm"+v).hide();		
		$(".imgp").not("#imgp"+v).show();													
	}	
	else{
		$(".imgp").show();
		$(".imgm").hide();	
		}	
	}

/*$(".rowdata11").click(function(){
    $(this).toggleClass("highlight");
    $(this).find('i').toggleClass('fa fa-plus fa fa-minus');
});*/


function setOtherFields() {
	$("#chargeTypeTableDivId").html("");
	$('#telephonyFeatures').html("");
	$('#viewSrvcFeatButton').hide();
	$('#viewtelePhoneyValues').hide();
	$('#isVoipId').val('false');
     var x = $("#serviceNameId").val();
     var serCode = x.split(",");
    $('#serviceCodeId').val(serCode[0]);
    $('#CoreServiceNameId').val(serCode[4]);
    $('#taxAmountId').val(serCode[2]);
    $('#multSrvcsAllowedId').val(serCode[3]);
    $('#CoreServiceCodeId').val(serCode[1]);
    $('#chargeTypeId ')[0].sumo.unSelectAll();
   
    
    if(serCode[1] === "IPTV"){
    	$('#viewSrvcFeatButton').show();
    	$("#viewSrvcFeatButton").html('View Channels');
    }else if(serCode[1] === "VOIP"){
    	$('#viewtelePhoneyValues').show();
    	$("#viewtelePhoneyValues").html('View Telephoney');
    	$('#isVoipId').val('true');
    	var html = '<lable><h2><b>Service Features </b></h2></lable><lable id="chargeTypesrvcFeatuLable" style="color: #ff0000"></lable><table class="table  table-alt" id = "voipSrvcFeaturesTable"><thead>'
    		+'<th> Feature Name</th>'
			+'<th hidden> Feature Id</th>'
			+'<th>Charge Types</th>'
			+'</thead>'
			+'<tbody>';
    	
    	var addlSrvcCode =  serCode[0];
		$.ajax({ 
   		 async:false,
   	     type: 'GET', 
   	     url: 'getTelePhonySrvcFeatures', 
   	     data: {addlSrvcCode:addlSrvcCode},
   	     contentType: 'application/json',
   	     success: function(data) { 
   	    	 $('#telephonyFeatures').html("");
   	    	 var i = 0;
			 $.each( data.srvcFeaturesList, function(id, srvcFeature){
				
				 html = html + '<tr><td class="srvcFeatureName" id="srvcFeatureName'+i+'">'+srvcFeature.featureName+'</td>'
	 				+'<td hidden class="srvcFeatureId" id ="srvcFeatureId'+i+'">'+srvcFeature.featureCode+'</td>'
	 				+'<td><select class="form-control form-white chargeTypeClass" onChange="getChargeTypes()"  id="srvcFeaturesChargeTypesId'+i+'"  multiple>';
	 				$.each(data.chargeTypeList, function(idx, chargeTypeObj){ 
	 					 html = html +'<option value = "'+chargeTypeObj.chargeCode+'">'+chargeTypeObj.chargeName+'</option>'
	 		    	});
	 				 html = html +'</select></td></tr>';
	 				 i++;
			 });
			 		html = html +' </tbody></table>';
			 		$('#telephonyFeatures').show();
					$('#telephonyFeatures').append(html);
					
					 $('.chargeTypeClass').SumoSelect({
						 placeholder: "Please Select Charge Types",
						 triggerChangeCombined : false,
					 });
   	     }
		});
		
    }
    
   
    
}
//function for to get core services by package type(Base, Add-on, One-time)

var servicesList = [];
var coreSrvcType = "";
function prodValidityDiv() {
	$('#telephonyFeatures').html("");
	 $('#gridTable tbody tr').remove();
	 $('#chargeDynTableId  tr').remove();
	 $('#serviceNameId option').remove();
	 $('#serviceNameId ').append('<option value="">-Select-</option>');
	 $('#serviceCodeId').val("");
	 $('#CoreServiceNameId').val("");
	 
	  servicesList = [];
	var prodType = $("#prodTypeId").val();
    if(prodType.charAt(0) == 'O'){
    	 $("#prodValidityDivId").show();
    	 $("#prodValidityId").val("");
    }else{
    	$("#prodValidityDivId").hide();
    	$("#prodValidityId").val(0);
    }
    $( "#coreSrvcDropDownId" ).remove();
    
    $.getJSON("getAllCoreServicesByProductType", {prodType:prodType}, function(data){
    	var selectDiv = '<div class="col-sm-3"  id="coreSrvcDropDownId" >' 
    		         +'<div class="form-group">'
    		         +'<label class="control-label"> Select Core Service </label>'
    		         +'<span style="color: red;">*</span>';
    		        
    					if(prodType.charAt(0) == 'B'){
    						coreSrvcType = 'M';
    						selectDiv = selectDiv	 +'<select class="form-control form-white productValidateClass" onChange="getSelectedOptions()" id="coreSrvcSelectId"';
    						selectDiv = selectDiv +' multiple >';
    					}
    					else{
    						 coreSrvcType = 'S';
    						selectDiv = selectDiv	 +'<select class="form-control form-white productValidateClass" onChange="getSelectedOptions()" id="coreSrvcSelectId"';
    						selectDiv = selectDiv +'> <option value="">-Select-</option>';
    					}
    					
    	$.each(data, function(idx, coreService){ 
    		selectDiv = selectDiv +'<option value = "'+coreService.servCode+'">'+coreService.servName+'</option>'
    	});
    	selectDiv = selectDiv + '</select>'
    	  						+'</div>'
    	  						+'</div>';
    	 $('#packageRowDiv2Id').append(selectDiv);
    	 $('#coreSrvcSelectId').SumoSelect({
    		 okCancelInMulti: true ,
    		 placeholder: "Please Select Core Service"
    	 });
    });
    
}

// To get additional services by selected core services v
function getSelectedOptions() {
		$('#telephonyFeatures').html("");
		$('#gridTable tbody tr').remove();
		$('#chargeDynTableId  tr').remove();
		$('#serviceNameId option').remove();
		$('#serviceNameId ').append('<option value="">-Select-</option>');
		$('#serviceCodeId').val("");
		$('#CoreServiceNameId').val("");
		
		
		 servicesList = [];
	
	var coreSrvcList = [];
	 coreSrvcList =  $("#coreSrvcSelectId").val();
	 var coreSrvcList = 'coreSrvcList='+coreSrvcList;
	 
	 $.ajax({ 
	     type: 'GET', 
	     url: 'getAllAdditionalSrvcsByCoreSrvcCode', 
	     data: coreSrvcList,
	     success: function(response) { 
	    	 $('#serviceNameId').find('option').remove();  
	    	 $('<option>').val("").text("--Select--").appendTo('#serviceNameId');
	    	 $.each(response, function(key,srvc) {              
	    	      $('<option>').val(srvc.srvccode+','+srvc.coresrvccode+','+srvc.totalTax+','+srvc.multSrvcsAllowed+','+srvc.coresrvcName).text(srvc.srvcname).appendTo( $('#serviceNameId'));
	    	 });
	     }
	     });
};

function getChargeTypes() {
	$("#serviceNameLable").html("");
	$("#chargeTypesrvcFeatuLable").html("");
	var isVoip = $('#isVoipId').val();
	var taxAmount = $("#taxAmountId").val();
	var chargeTypeIds = "";
	var srvcFeatureObj = "";
	var requestData = "";
	var srvcFeaturesObjList = [];
	var serviceName = $("#serviceNameId :selected").text();
	var html = '<label id="chargeAmounLable" style="color: #ff0000"></label>'
				+'<table class="table  table-alt" id = "chargeDynTableId"><thead>'
				+'<th>Service/Feature Name</th>'
				+'<th> Charge Name</th>'
				+'<th hidden> Charge Type</th>'
				+'<th>Enter Charge Amount <span style="color: red;">*</span></th>'
				+'<th>Tax Percentage</th>'
				+'<th>Gl Code</th>'
				+'<th hidden>Feature Id</th>'
				+'</thead>'
				+'<tbody>';
	
	$("#chargeTypeTableDivId").html("");
	$("#duplicateServiceErrorId").html("");
	$("#chargeTypeLable").html("");
	if(isVoip === 'true'){
		 chargeTypeIds =  $(".chargeTypeClass").val();
		var rowCount = $('#voipSrvcFeaturesTable tbody tr').length;
		for(var j=0; j < rowCount; j++){
			var featureName = $("#srvcFeatureName"+j).text();
			var featureCode = $("#srvcFeatureId"+j).text();
			var chargeCode =  $("#srvcFeaturesChargeTypesId"+j).val();
				if(chargeCode == null)
					chargeCode = "";
				if(featureCode != "" && chargeCode != null && chargeCode != ""){
					srvcFeatureObj = {
							"featureName" : featureName,	
							"featureCode"   : featureCode,
							"chargeCode"  : chargeCode
						}
						srvcFeaturesObjList.push(srvcFeatureObj);
				}
		}
			srvcFeatureObj = {
				"featureName" : "dummy",	
				"featureCode"   : "0",
				"chargeCode"  : chargeTypeIds
			}
			srvcFeaturesObjList.push(srvcFeatureObj);
			srvcFeaturesObjList = JSON.stringify(srvcFeaturesObjList); 
		requestData = 'srvcFeaturesObjList='+srvcFeaturesObjList;
	}else{
		 chargeTypeIds =  $(".chargeTypeClass").val();
		  requestData = 'chargeTypeIdsList='+chargeTypeIds;
	}
	var srvccode = $("#serviceCodeId").val();
	if(srvccode != null && srvccode != ""){
		 $.ajax({ 
			 async:false,
		     type: 'GET', 
		     url: 'getAllChargeTypesByChargeCodes', 
		     data: requestData+'&srvcCode='+srvccode,
		     success: function(response) { 
		    	 $.each(response.chargeTypes, function(key,chargeType) {
		    		 	if(chargeType.featureName.trim() == "dummy")
		    		 		 html = html +'<tr><td class="featureName"> '+serviceName+'(S) </td>';
		 				else
		 					 html = html +'<tr><td class="featureName"> '+chargeType.featureName+'(F) </td>';
		    		 	 html = html +' <td class="chargeName">'+chargeType.chargeName+'</td>'
		    		 				+'<td hidden class="chargeCodeClass">'+chargeType.chargeCode+'</td>'
		    		 				+'<td> <input type="text" class="form-control form-white numbersOnly" maxlength="80"  id ="chargeAmtId" />  </td>'
		    		 				+'<td> '+chargeType.taxAmt+'  </td>  '
		    		 				+'<td> '+chargeType.glCode+'  </td>'
		    		 				+'<td hidden class="featureCode">'+chargeType.featureCode+'</td></tr>';
		    	 });
		    	 html = html + '</tbody></table>';
		    	 $("#chargeTypeTableDivId").append(html);
		     }
		     });
 	}else{
 		$("#serviceNameLable").html("Please Select Service");
 	}
	
}



$(document).ready(function() {
	
	$('#viewSrvcFeatButton').hide();
	$('#viewtelePhoneyValues').hide();
	
	$('#chargeTypeId ').SumoSelect({
		 placeholder: "Please Select Charge Types",
		 triggerChangeCombined : false,
	 });
	

	
	
	$('.specialCharactersNotAllow').keyup(function() {
		var yourInput = $(this).val();
		re = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi;
		var isSplChar = re.test(yourInput);
		if(isSplChar) {
		var no_spl_char = yourInput.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
		$(this).val(no_spl_char);
		}
		});
	$("#prodValidityDivId").hide();
	$("#prodValidityId").val(0);
	
	 $('#pcsProductsTable').dataTable({
		 "sPaginationType": "full_numbers",
	 });
	
	var tableIndex = -1;
	
	$( "#serviceNameId" ).change(function() {
		$("#duplicateServiceErrorId").html("");
		$("#serviceNameLable").html("");
	});
	
	$('#viewSrvcFeatButton').on('click', function(){
		var addlSrvcCode =   $('#serviceCodeId').val();
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
	
	$('#viewSrvcFeatButton1').on('click', function(){
		var addlSrvcCode =   $('#serviceCodeId').val();
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
	
	$('#viewtelePhoneyValues').on('click', function(){
		var coreSrvcCode =   $('#CoreServiceCodeId').val();
		$.ajax({ 
   		 async:false,
   	     type: 'GET', 
   	     url: 'getAllTelePhoneyValues', 
   	     data: {coreSrvcCode:coreSrvcCode},
   	     contentType: 'application/json',
   	     success: function(data) { 
   	    	 $('#telePhoneyPopUpId').html("");
			 var html = "";
				 $.each( data.list, function(key, value){
				 if(key == 'Off Peack'){
					 html = html + '<label>'+key+'</label>';
					 html = html + '<table class="table table-alt">'
					 			 + '<thead><tr>'
					 			 + '<th>Start Time</th>'
					 			 + '<th>End Time</th>'
					 			 +' </tr></thead> '
					 			 +'<tbody>';
					 $.each( value, function(k, val){
						 html = html + '<tr>'
						 			 + '<td>'+val.startTime+'</td>'
						 			 + '<td>'+val.endTime+'</td>'
						 			 + '</tr>';
					 });
					 html = html + '</tbody></table>';
				 }
				 
				 if(key == 'Special Days'){
					 html = html + '<label>'+key+'</label>';
					 html = html + '<table class="table table-alt">'
					 			 + '<thead><tr>'
					 			 + '<th>MM/DD</th>'
					 			 + '<th>YYYY</th>'
					 			 + '<th>Day Name</th>'
					 			 +' </tr></thead> '
					 			 +'<tbody>';
					 $.each( value, function(k, val){
						 html = html + '<tr>'
						 			 + '<td>'+val.splMMDD+'</td>'
						 			 + '<td>'+val.splYYYY+'</td>'
						 			 + '<td>'+val.spldayName+'</td>'
						 			 + '</tr>';
					 });
					 html = html + '</tbody></table>';
				 }
				 
				 if(key == 'Call Category'){
					 html = html + '<label>'+key+'</label>';
					 html = html + '<table class="table table-alt">'
					 			 + '<thead><tr>'
					 			 + '<th>Category Id</th>'
					 			 + '<th>Country Code</th>'
					 			 + '<th>Country Name</th>'
					 			 + '<th>Area Code</th>'
					 			 + '<th>Seconds Per Pulse</th>'
					 			 +' </tr></thead> '
					 			 +'<tbody>';
					 $.each( value, function(k, val){
						 html = html + '<tr>'
						 			 + '<td>'+val.catgId+'</td>'
						 			 + '<td>'+val.countryCode+'</td>'
						 			 + '<td>'+val.countryName+'</td>'
						 			 + '<td>'+val.areaCode+'</td>'
						 			 + '<td>'+val.secondsPerPulse+'</td>'
						 			 + '</tr>';
					 });
					 html = html + '</tbody></table>';
				 }
				 
				 if(key == 'Unit Rates'){
					 html = html + '<label>'+key+'</label>';
					 html = html + '<table class="table table-alt">'
					 			 + '<thead><tr>'
					 			 + '<th>Category Id</th>'
					 			 + '<th>General Rate</th>'
					 			 + '<th>General Off Peak Rate</th>'
					 			 + '<th>Special Rate</th>'
					 			 + '<th>Special Off Peak Rate</th>'
					 			 +' </tr></thead> '
					 			 +'<tbody>';
					 $.each( value, function(k, val){
						 html = html + '<tr>'
						 			 + '<td>'+val.genRate+'</td>'
						 			 + '<td>'+val.genopkRate+'</td>'
						 			 + '<td>'+val.splRate+'</td>'
						 			 + '<td>'+val.splopkRate+'</td>'
						 			 + '</tr>';
					 });
					 html = html + '</tbody></table>';
				 }
			   });
			 $('#telePhoneyPopUpId').append(html);
			 
   	     }
		});
	});

	

	$( "#chargeAmountId" ).keypress(function() {
		$("#chargeAmounLable").html("");
	});

	    jQuery.validator.addClassRules('productValidateClass', { 
	        required: true
	    });
	    $('.productValidateClass').validate();
	    
	    jQuery.validator.addClassRules('validateClass', { 
	        required: true
	    });
	    $('.validateClass').validate();
	
	$("#chargeAmountId").numeric({
		negative : false,
	});
	$("#lockInDaysId,#prodValidityId,#cpe-Quantity").numeric({negative : false}).keyup(function (e) {
	    var yourInput = $(this).val();
		re = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi;
		var isSplChar = re.test(yourInput);
		if(yourInput.substring(0, 1) === '0')
			yourInput = '';
		if(isSplChar ) {
			yourInput = yourInput.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
		}
	    $(this).val(yourInput);   //update input with new value
	});
	
	
	
	$('#hiddenTable').hide();
	
	
    $('#addServiceButtonId').on('click',function() {
    	$("#duplicateServiceErrorId").html("");
    	var chargeType = $("#chargeTypeId").val();
    	var chargeTypeNames = null;
    	var chargeAmount = 0;
    	var serviceNameId = $("#serviceNameId").val();
    	var serviceType = $("#serviceTypeId").val();
    	var serviceName = $("#serviceNameId :selected").text();
    	var srvccode = $("#serviceCodeId").val();
    	var coreService = $("#CoreServiceNameId").val();
    	var coreServiceCode = $("#CoreServiceCodeId").val();
    	var effectiveFrom = $("#Datepicker3").val();
    	var taxAmount = $("#taxAmountId").val();
    	var multSrvcsAllowed = $("#multSrvcsAllowedId").val();
    	var flag = true;
    	var chargesList = [];
    	
    	//Creating new object with all charges and taxes
    	$('#chargeDynTableId  > tbody > tr').each(function() {
    		
    		if($(this).find('input[type="text"]').val() === ""){
    			$("#chargeAmounLable").html("Please Enter Charge Amount");
    			flag = false;
    			return false;
    		}
    			
    	 	var chargeObj = {
           			"chargeCode":$(this).find('.chargeCodeClass').text(),
        		    "chargeAmt": $(this).find('input[type="text"]').val(),
        		    "featureCode":$(this).find('.featureCode').text(),
        		};
    	 	 
    	  var featureName = $(this).find('.featureName').text();
   	 	 if(chargeTypeNames === null){
   	 			 chargeTypeNames = featureName+"-"+$(this).find('.chargeName').text()+"("+$(this).find('input[type="text"]').val()+")";
   	 	 }
   		 else{
   				 chargeTypeNames += " , "+featureName+"-"+$(this).find('.chargeName').text()+"("+$(this).find('input[type="text"]').val()+")";
   			 
   		 }
    	 	 
    	 	chargeAmount = chargeAmount + parseInt($(this).find('input[type="text"]').val()),
    	 	chargesList.push(chargeObj);
    	});
    	
    	var rowCount = $('#voipSrvcFeaturesTable tbody tr').length;
		for(var j=0; j < rowCount; j++){
			var featureName = $("#srvcFeatureName"+j).text();
			var chargeCode =  $("#srvcFeaturesChargeTypesId"+j).val();
			if(chargeCode == "" || chargeCode == null){
				$("#chargeTypesrvcFeatuLable").html("Please Select Charge Type For "+featureName);
				flag = false;
    			return false;
			}
		}
    	
    	if(flag){
    	 if(serviceNameId == "") 
    		$("#serviceNameLable").html("Please Select Service");
    	else if(chargeType == "" || chargeType == null)
    		$("#chargeTypeLable").html("Please Select Charge Type");
    	else {
    		var coreSrvcName =  $("#CoreServiceNameId").val();
    		 $("#chargeAmounLable").html("");
        	 $("#serviceNameId").val("");
        	 $('#chargeTypeId ')[0].sumo.unSelectAll();
        	 $("#chargeTypeTableDivId").html("");
        	 $("#chargeAmountId").val("");
        	 $("#serviceCodeId").val("");
        	 $("#CoreServiceNameId").val("");
        	 $("#taxAmountId").val("");
        	 $('#telephonyFeatures').html("");
       	var serviceObj = {
       			"srvccode":srvccode,
    		    "serviceType": serviceType,
    		    "serviceName": serviceName,
    		    "effectiveFrom": effectiveFrom,
    		    "chargeAmount": chargeAmount,
    		    "coreServiceCode" : coreServiceCode,
    		    "chargesList" : chargesList,
    		};
       	var duplicateService = false;
       	var errorMessage = "";
       	if(multSrvcsAllowed == "Y"){
       		for (index = 0; index < servicesList.length; index++) {
          		 if(duplicateService == true)
          			 break;
                var loopObj = servicesList[index] ;
                var srvccodeFromList =  loopObj["srvccode"];
                if(srvccodeFromList == srvccode){
                	 duplicateService = true;
                	 errorMessage = "Selected Additional Service Is Already Added To This Package."
                }
                else
               	 duplicateService = false;
            }
       	}
       	else if(servicesList.length > 0)
		{
       	 for (index = 0; index < servicesList.length; index++) {
       		 if(duplicateService == true)
       			 break;
             var loopObj = servicesList[index] ;
             var coreSrvcCode =  loopObj["coreServiceCode"];
             var srvccodeFromList =  loopObj["srvccode"];
             if(srvccodeFromList == srvccode){
             	 duplicateService = true;
             	 errorMessage = "Selected Additional Service Is Already Added To This Package."
             }else if(coreSrvcCode == coreServiceCode){
            	 errorMessage = "The Core Service ::  " + coreSrvcName + " Is Already Added To This Package."
            		 duplicateService = true;
             }
             else
            	 duplicateService = false;
         }
		}
           if(duplicateService == false){
        	   $('#gridTable').append('<tr><td>'+serviceName+'</td> <td>'+srvccode+'</td> <td>'+coreService+'</td> <td>'+chargeTypeNames+'</td> <td  hidden="true">'+taxAmount+'</td>'+
        			   '<td> '+
						'<a href="javascript:void(0);" class="btn btn-sm btn-danger srvcParamClass11" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a></td>'+
						' </tr>');
           	   servicesList.push(serviceObj);
           	 $('#viewtelePhoneyValues').hide();
             $('#viewSrvcFeatButton').hide();
           	$('.serviceValidateClass').val("");
           }else{
        	   $("#duplicateServiceErrorId").html(errorMessage);
        	   $('.serviceValidateClass').val("");
           }
    	}
    	 
    	}
	});
    
    $(document).on('click', '.srvcParamClass11', function(event) {
		var rowindex = $(this).closest('tr').index();
		servicesList.splice(rowindex, 1);
		$(this).closest('tr').remove();
	 });
    
    //Payment Gate Way Script 
    
    $('#payNow').click(function() {
    	
		if( $('.validateClass').valid()){
			$('#email_Id_form').val($('#email_Id').val());
			$('#mobile_Id_form').val($('#mobile_Id').val());

			if ($('#billDeskRadio').is(':checked')) {
				$('#gateWayTypeId').val("BILLDESK");
				$('#CreditCardPayment').submit();
			} else if ($('#digitSecureRadio').is(':checked')) {
				$('#gateWayTypeId').val("DIGITSIGNATURE");
				$('#CreditCardPayment').submit();
			} else {
				alert(" Please Select Payment Gateway Type");
				event.preventDefault();
			}
		}
		
	});
   
    function servicesListSize(){
    	if(servicesList.length == 0)
			return false;
		else
			return true;
    };
    
    $('#saveProductInfoId').click(function() {
    	if($('.productValidateClass').valid()) {
    		
    		if(servicesListSize())
    		{
    			ajaxindicatorstart('Please Wait...');
        		var productCode = $("#productCodeId").val();
            	var productName = $("#productNameId").val();
            	var effectiveFrom = $("#Datepicker1").val();
            	var lockInDays = $("#lockInDaysId").val();
            	var custType = $("#custTypeId").val();
            	var prodType = $("#prodTypeId").val();
            	var prodValidity = $("#prodValidityId").val();
            	if(lockInDays == null || lockInDays == "")
            		lockInDays = 0;
            	var tenantcode = $("#tenantcodeId").val();
            	
            	
              
           	var productObj = {
        		    "productCode": productCode,
        		    "productName": productName,
        		    "effectiveFrom": effectiveFrom,
        		    "lockInDays": lockInDays,
        		    "servicesList": servicesList,
        		    "tenantcode" : tenantcode,
        		    "prodType" : prodType,
        		    "custType" : custType,
        		    "prodValidity" : prodValidity,
        		};
           	
           	var product = JSON.stringify(productObj); 
        	$.ajax({ 
        		 async:false,
        	     type: 'POST', 
        	     url: 'saveProductData', 
        	     data: product,
        	     contentType: 'application/json',
        	     success: function(response) { 
        	    	// var product = JSON.stringify(response); 
        	    	 
        	            var effectiveDate = response.effDate;
        	            var productCode = response.productCode;
        	            var outPut = response.outPut;
        	            var errorCode = response.errorCode;
        	            var errorMessage = response.errorMessage;
        	            var tenantcode = response.tenantcode;
        	            var desc = response.desc;
        	            if(outPut == "success"){
        	            	var returnValue = "Package Created Successfully...";
        	            	$('#returnValueId').val(returnValue);
        	            	 $('#showProductsList').attr('action', './pcsHome');
        	            	 $('#showProductsList').submit();
        	            	 
        	            /*	 $('#showProductsList').submit(function(eventObj) {
        	            		    $(this).append('<input type="hidden" name="returnValue" value="Product Created Successfully..." /> ');
        	            		    return true;
        	            		});*/
        	            }else{
        	            	 $('#productException').html("Package Is Not Created.. ");
        	            	 $('#productException1').html("Reason Is ::: "+ errorMessage);
        	                /*$('#hiddenTable').show();
        	                $('#hiddenTable').append('<tr><td>'+errorCode+'</td> <td>'+errorMessage+'</td> <td>'+desc+'</td> </tr>');*/
        	            }
        	            ajaxindicatorstop();
        	     },
        	     error: function (response) {
        	    	 ajaxindicatorstop();
        	    	    var errorCode = response.errorCode;
        	            var errorMessage = response.errorMessage;
        	            var desc = response.desc;
        	            $('#productException').html("Package Is Not Created.. ");
   	            	 $('#productException1').html("Reason Is ::: "+ errorMessage);
        	          /*  $('#hiddenTable').show();
                        $('#hiddenTable').append('<tr><td>'+errorCode+'</td> <td>'+errorMessage+'</td> <td>'+desc+'</td> </tr>');*/
        	        }
        	  }); 
        		
    		}
    		else{
    			$("#duplicateServiceErrorId").html("Please Enter Atleast One Aditional Service.");
    		}
    	}
    	
	});
    
    /*$('#cancelButtonId').click(function() {
        location.reload();
    });*/
    
    function ajaxindicatorstart(text)
    {
    	if(jQuery('body').find('#resultLoading').attr('id') != 'resultLoading'){
    	jQuery('body').append('<div id="resultLoading" style="display:none"><div><img src="../resources/images/ajax-loader.gif"><div>'+text+'</div></div><div class="bg"></div></div>');
    	}
    	
    	jQuery('#resultLoading').css({
    		'width':'100%',
    		'height':'100%',
    		'position':'fixed',
    		'z-index':'10000000',
    		'top':'0',
    		'left':'0',
    		'right':'0',
    		'bottom':'0',
    		'margin':'auto'
    	});	
    	
    	jQuery('#resultLoading .bg').css({
    		'background':'#000000',
    		'opacity':'0.7',
    		'width':'100%',
    		'height':'100%',
    		'position':'absolute',
    		'top':'0'
    	});
    	
    	jQuery('#resultLoading>div:first').css({
    		'width': '250px',
    		'height':'75px',
    		'text-align': 'center',
    		'position': 'fixed',
    		'top':'0',
    		'left':'0',
    		'right':'0',
    		'bottom':'0',
    		'margin':'auto',
    		'font-size':'16px',
    		'z-index':'10',
    		'color':'#ffffff'
    		
    	});

        jQuery('#resultLoading .bg').height('100%');
        jQuery('#resultLoading').fadeIn(300);
        jQuery('body').css('cursor', 'wait');
    }

    
    function ajaxindicatorstop()
    {
        jQuery('#resultLoading .bg').height('100%');
        jQuery('#resultLoading').fadeOut(300);
        jQuery('body').css('cursor', 'default');
    }
    

  /*  $("#product_form").validate({
    	rules : {
    		ProductCode : {
    			required : true
    		},
    		ProductName : {
    			required : true
    		}

    	},
    	messages : {
    		productCodeId : "Please Enter Product Code",
    		productNameId : "Please Enter Product Name",
    	},
    });*/
    
});



