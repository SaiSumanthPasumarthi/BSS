	
	function cpeAllocation(param) {
		$("#allocateCpeFormInputId").val(param);
		$("#allocateCpeFormId").submit();
	};
	
	function viewCpeAllocation(param) {
		$("#viewCpeFormInputId").val(param);
		$("#viewCpeFormId").submit();
	};

$(document).ready(function() {
	
	 $("#cpe-Type-Update-Id").prop('disabled', true);
	 $("#cpe-submit-Update-id").prop('disabled', true);
	
	
	$('#cpe-Model-Text-Info-button-Id').on('input',function(e){
		$("#cpe-Type-Update-Id").prop('disabled', true);
		 $("#cpe-submit-Update-id").prop('disabled', true);
	});
	
	$('#cpeStockUpload').on('click', function(){	
		var id = $('#tenant_id').val();	
		var prefix= $("#cpe-prefix-Select-Id option:selected" ).text();
		if(id == "")
			alert("Please select value from suggestion dropdown");
		
		else if(prefix =="")
    		alert("Please Select Prefix");
		else
			{ajaxindicatorstart('Please Wait...');
			$('#cpeStockUploadFormId').submit();
			}

	});
	
	$(document).on("click", ".viewBroadcasterPriceDetails", function () {
		ajaxindicatorstart('Please Wait...');
		var i = $(".viewBroadcasterPriceDetails").index(this);
		$(".viewRateCardForm:eq("+i+")").attr('action',"viewBroadcasterPriceDetails");
		$(".viewRateCardForm:eq("+i+")").submit();
		
	});
	

	  $(document).on("click", ".viewChannelListDetails", function () {
		ajaxindicatorstart('Please Wait...');
		var i = $(".viewChannelListDetails").index(this);
		$(".viewChannelListForm:eq("+i+")").attr('action',"viewChannelDetails");
		$(".viewChannelListForm:eq("+i+")").submit();
		
	});
	
	  $(document).on("click", ".viewInvoiceDetails", function () {
		ajaxindicatorstart('Please Wait...');
		var i = $(".viewInvoiceDetails").index(this);
		$(".viewInvoiceDetailsForm:eq("+i+")").attr('action',"viewInvoiceDetails");
		$(".viewInvoiceDetailsForm:eq("+i+")").submit();
		
	});
	
	$("#cpe-Model-Div-Id").hide();
    $('#add-Cpe-Button-Id').on('click',function() {
    	var flag = true;
    	var deviceCost = parseFloat($("#deviceCostId").val());
    	var profileId = $("#cpe-Device-Select-Id ").val();
    	var deviceName = $("#cpe-Device-Select-Id option:selected").text();
    	var quantity = $("#cpe-Quantity").val();
    	var deviceType = $("#cpe-Type-Id option:selected").text();
    	var totalCost = deviceCost * quantity;
    	if(deviceType == "--Select--")
    		alert("Please Select CPE Type..");
    	else if(deviceName == "-Select-" || deviceName == "")
    		alert("Please Select CPE Model..");
    	else if(quantity == "")
    		alert("Please Enter No Of Devices..");
    	else{
    		if($('#cpe-gridTable > tbody  > tr').length >= 1){
    			$('#cpe-gridTable > tbody  > tr').each(function() {
    				var profileIdFromTable = $(this).find('.profileId').text();
    				if(profileIdFromTable == profileId){
    					alert("Same Device Already Added...");
    					flag = false;
    					return false;
    				}
    			});
    		}
    		if(flag){
    			$('#cpe-gridTable  tbody  ').append('<tr><td class="profileId" hidden>'+profileId+'</td> <td>'+deviceType+'</td> <td>'+deviceName+'</td>  <td>'+deviceCost.toFixed(2)+'</td> <td class="quantity">'+quantity+'</td> <td class="totalCost">'+totalCost.toFixed(2)+'</td>'+
     	 			   '<td> '+
     	 				'<a href="javascript:void(0);" class="btn btn-sm btn-danger cpeDetailsListDelete" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a></td>'+
     	 				' </tr>');
    			
       	    	$('#priceDetailsListId').remove();
       	    	$("#cpe-Quantity").val("");
       	    	$("option:selected").prop("selected", false)
    		}
    		
    	}
    });
    
    $('#saveCpeOrders').on('click',function() {
    	if($('#cpe-gridTable > tbody  > tr').length == 0){
    		alert("Please Add Atleast One Order ....");
    	}else{
    		var cpeOrdersList = [];
    		
    		$('#cpe-gridTable > tbody  > tr').each(function() {
				var profileId = $(this).find('.profileId').text();
				var quantity = $(this).find('.quantity').text();
				var totalCost = $(this).find('.totalCost').text();
				var cpeOrder ={
						profileId : profileId,
						quantity  : quantity,
						totalCost : totalCost,
				}
				cpeOrdersList.push(cpeOrder);
			});
    		 cpeOrdersList = JSON.stringify(cpeOrdersList); 
    		
         	$.ajax({ 
       		 async:false,
       	     type: 'POST', 
       	     url: 'saveCpeOrders', 
       	     data: cpeOrdersList,
       	     contentType: 'application/json',
       	     success: function(response) { 
       	    	$('#statusMessageId').html("");
       	    	$('#statusMessageId').append(response);
       	    	$('#cpe-gridTable > tbody  > tr').remove();
       	    	$('#priceDetailsListId').remove();
       	    	$("#cpe-Quantity").val("");
       	    	$("option:selected").prop("selected", false)
       	     }
         	});
    	}
    });
    
	
});

$(document).on('click', '.cpeDetailsListDelete', function(event) {
	var rowindex = $(this).closest('tr').index();
	$(this).closest('tr').remove();
 });


$(document).on('click','#delete-prefix',function(event) {
var prefixes = [];
var cpeType = $("#cpe-Type-Id option:selected").text();

if ($('#cpe-prefix-Select-Id option:selected').length > 0)
$("#cpe-prefix-Select-Id > option:selected").each(function() {
	prefixes.push(this.text);

});


prefixes = JSON.stringify(prefixes);

		if ( prefixes!=null){
		
if (confirm("Are you sure you want to delete the selected Prefix Value(s) ?")) {

		 	$.ajax({ 
        	     type: 'POST', 
        	     url: 'deletePrefix' + '?cpeType=' +cpeType, 
        	     data: prefixes,
        	     contentType: 'application/json',
        	     
        	     success: function(response) { 
        	    	 getCpePrefixesByCpeType();
        	     },
        	  });
		 	
			} else {
			    //dont do anything
			}
		}
	});






function getAllCpeModelByCpeType() {
	var cpeType = $("#cpe-Type-Id option:selected").text();
	getCpePrefixesByCpeType();
	 $("#cpe-Model-Div-Id").html("");
	var html = '<div class="form-group">' 
		+'<label class="control-label"> Select Model <span style="color: red;">*</span></label>';
								
	 $.ajax({ 
	     type: 'GET', 
	     async:false,
	     url: 'getAllCpeModelByCpeType', 
	     data: 	{cpeType : cpeType},
	     success: function(response) { 
	    	 html = html	 +'<div><select class="form-control form-white " id="cpe-Device-Select-Id" onchange="getcpeprice()">'; 
	    	 html = html +'<option value="">-Select-</option>';
	    	 $.each(response, function(key,cpeModel) { 
	    		 html = html +'<option value='+cpeModel.profileId+'>'+cpeModel.cpeModel+'</option>';
	    	 });
	    	 html = html + '</select> </div> </div>';
	    	 $("#cpe-Model-Div-Id").show();
	    	 $("#cpe-Model-Div-Id").append(html);
	     }
	     });
}


function cpeProfileChange(profileId, cpeType) {
	
	
	 $("#cpe-Model-Div-Id").html("");
	var html = '<div class="form-group">' 
		+'<label class="control-label">New CPE Model <span style="color: red;">*</span></label>';
										
	 $.ajax({ 
	     type: 'GET', 
	     async:false,
	     url: 'getAllCpeModelByCpeType', 
	     data: 	{cpeType : cpeType, profileId : profileId},
	     success: function(response) { 
	    	 html = html	 +'<div><select class="form-control form-white " id="cpe-Device-Select-Id" name = "newProfileId"  required="required">'; 
	    	 html = html +'<option  value="">-Select-</option>';
	    	 $.each(response, function(key,cpeModel) { 
	    		 html = html +'<option value='+cpeModel.profileId+'>'+cpeModel.cpeModel+'</option>';
	    	 });
	    	 html = html + '</select> </div> </div>';
	    	 
	    	 
	    	 $("#cpe-Model-Div-Id").show();
	    	 $("#cpe-Model-Div-Id").append(html);
	     }
	     });
}


function getCpePrefixesByCpeType() {
	var cpeType = $("#cpe-Type-Id option:selected").text();
	
	
	 $("#cpe-Prefixes-Id").html("");
	var html = '<div class="form-group">' 
		+'<label class="control-label"> Cpe Prefixes <span style="color: red;">*</span></label>';
								
	 $.ajax({ 
	     type: 'GET', 
	     async:false,
	     url: 'getCpePrefixesByCpeType', 
	     data: 	{cpeType : cpeType},
	     success: function(response) { 
	    	 html = html	 +'<div><select name= "cpePrefixes" class=" selectpicker " multiple id="cpe-prefix-Select-Id">'; 
	    	// html = html +'<option value="">-Select-</option>';
	    	 $.each(response, function(index,value) { 
	    		 
	    		 html = html +'<option>'+value+'</option>';
	    	 });
	    	 html = html + '</select> </div> </div>';
	    	 
	    	 $("#cpe-Prefixes-Id").show(); 	 
	    	 $("#cpe-Prefixes-Id").append(html);
	    	 $('.selectpicker').selectpicker();
	    	 $("#add-delete-buttons").show();
	    	 
	     }
	     });
}

function openPopUp()
{
var prefixValue="";
var cpeType = $("#cpe-Type-Id option:selected").text();


  $('#divId').css('display','block');
  $( "#divId" ).dialog({
	    resizable: true,
	    height:200,
	    modal: true,
	    buttons: {
	      "Cancel": function() {
	        $( this ).dialog( "close" );
	        $("#divId :input").val("");
	      },
	      "Ok": function() {
	       
	    	  $( this ).dialog( "close" );
	    	  prefixValue=$("#divId :input").val();
	    	  $("#divId :input").val("");
	    	
	    	  $.ajax({ 
	    		     type: 'POST', 
	    		     async:false,
	    		     url: 'addPrefix', 
	    		     data: {prefixValue:prefixValue, cpeType:cpeType},
	    		    
	    		     success: function(response) { 
	    		    
	    		    	getCpePrefixesByCpeType();
	    		     }
	    		     });
	    	  
	    	  
	    	  
	      }
	    }
	  });
  
  
}


function getcpeprice() {
	var html = "";
	var profileId = $("#cpe-Device-Select-Id option:selected").val();
	$('#cpe_profile_Id').val(profileId);
	if(profileId == "")
		$("#cpe-cost-details-id").html("");
	else{
	 $("#cpe-cost-details-id").html("");
	 html = html+'<div><table class="table table-alt" id="priceDetailsListId">'
	 			+'<thead>'
	 			+'<tr>'
	 			+'<td> Customer Cost </td>'
	 			+'<td> Customer Rent</td>'
	 			+'<td> Purchase Cost</td>'
	 			+'<td> Instalation Charges</td>'
	 			+'</tr></thead>'
	 $.ajax({ 
	     type: 'GET', 
	     async:false,
	     url: 'getCpeChargesByProfileId', 
	     data: 	{profileId : profileId},
	     success: function(response) { 
	    	 var custCost = response.custCost == null ? 0 : response.custCost;
	    	 var custRent = response.custRent == null ? 0 : response.custRent;
	    	 var purchaseCost = response.purchaseCost == null ? 0 : response.purchaseCost;
	    	 var instalationCost = response.instalationCost == null ? 0 : response.instalationCost;
	    	 
	    	 html = html +'<tr><tbody><tr><td>'+custCost+'</td><td>'+custRent+'</td><td>'+purchaseCost+'</td><td>'+instalationCost+'</td></tr></tbody>';
	    	 html = html + '</table> </div> </div>';
	    	 $("#cpe-cost-details-id").append(html);
	    	 $("#deviceCostId").val(response.purchaseCost);
	    	 
	     }
	     });
	}
}





$(document).on('click', '#cpe-Model-Div-Info-button-Id', function(event) {
	
	var cpeSrlNo = $("#cpe-Model-Text-Info-button-Id").val();
	var html = '';
	if(cpeSrlNo != ''){
		 $("#cpe-Model-Div-Info-Id").html("");
		 $.ajax({ 
		     type: 'GET', 
		     async:false,
		     url: 'getCpeModelInfoBySrlNo', 
		     data: 	{cpeSrlNo : cpeSrlNo},
		     success: function(response) {
		    
		    	 $("#cpe-Type-Update-Id").prop('disabled', false);
		    	 $("#cpe-submit-Update-id").prop('disabled', false);
		    	 $("#cpe-Model-Div-Info-Id").val(response.cpeModel);
		    	 $("#cpe-Type-Update-Id").val(response.cpeTypeLov);
		    	 cpeProfileChange(response.cpeProfileId, response.cpeTypeLov);
		    	 
		     	},
		     error: function(response) { 
		    	 $("#cpe-Type-Update-Id").prop('disabled', true);
		    	 $("#cpe-submit-Update-id").prop('disabled', true);
		    	 $("#cpe-Model-Div-Info-Id").val("");
		    	 $("#cpe-Model-Div-Info-Id").val("CPE device is not found....");
		     } 
		     });
		 

		
	}else{
		alert("Please Enter CPE Serial No ..");
	}
 });
