        <div id="page-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="col-lg-12">
                  <h3 class="page-header">Product Creation</h3>
                </div>
                </div>
              <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div align="left" style="margin-left:20px;">
<form id="product_form" action="#">
<table width="1000px">
  <tr>
    <td width="800px" valign="top"><table width="100%">
      <tr>
        <td width="60px">Code</td>
        <td width="140px"><input type="text" name="productCode" class="form-control2 productClass" id="productCodeId"/></td>
        <td width="60px">Name</td>
        <td width="140px"><input type="text" name="productName" class="form-control2 productClass" id="productNameId"/></td>
        <td width="70px">Valid From</td>
        <td width="130px"><input type="text" id="Datepicker1" class="form-control2"/></td>
       <!--  <td width="70px">Valid To</td>
        <td width="130px"><input type="text" id="Datepicker2" class="form-control2"/></td> -->
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="100%">
      <tr>
        <td><h4>ADD Services/Product</h4></td>
       
      </tr>
      
		<font color="red"><label id="id_srvcParamErr"></label></font>
      <tr>
        <td width="1000px"><table width="100%">
          <tr>
            <td width="100px">Type</td>
            <td width="100px"><select class="form-control2" id="serviceTypeId">
                                                <option value="S">Service</option>
                      </select></td>
            <td width="100px">Name</td>
            <td width="100px"><select class="form-control2" id="serviceNameId">
													<c:forEach var="service" items="${servicesList}">
															<option value="${service.srvccode}">${service.srvcname}</option>
														</c:forEach>
												</select></td>
            <!-- <td width="100px">Effective From</td>
            <td width="100px"><input type="text" id="Datepicker3" class="form-control2"/></td> -->
            <td width="100px">Lock In Days</td>
            <td width="100px"><select class="form-control2" id="lockInDaysId">
                                                <option value="0">0</option>
                                                <option value="10">10</option>
                                                <option value="20">20</option>
                                                <option value="30">30</option>
                                                <option value="40">40</option>
                      </select></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td width="100px">Charge Type</td>
            <td width="100px"><select class="form-control2" id="chargeTypeId">
                                                <option value="Monthly">Monthly Rental</option>
                                                <option value="Event">Event based</option>
                      </select></td>
            <td width="100px">Charge Name</td>
            <td width="100px"><input type="text" name="chargename" class="form-control2 serviceValidateClass" id="chargeNameId"/></td>
            <td width="100px">Charge Amount</td>
            <td width="100px"><input type="text" name="chargeAmount" class="form-control2 serviceValidateClass" id="chargeAmountId"/></td>
            <td>&nbsp;</td>
            <td><button type="Button" class="btn btn-success" id="addService">Add Service</button></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td valign="top">&nbsp; 
    <table width="100%" class="table table-striped table-bordered table-hover" id="gridTable">
      <tr>
        <th>Service / Product Name</th>
        <th>Type</th>
        <th>Name</th>
        <th>Charge Type</th>
        <th>Charge</th>
        <th>&nbsp;</th>
        <th>&nbsp;</th>
      </tr>
    
     
    </table></td>
  </tr>
  <tr>
  	<td>
    	<table width="100%">
        	<tr>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="280px" align="right"><button type="Button" class="btn btn-primary" id="saveProductInfoId">Submit</button></td>
       			<td width="20px" align="right"><button type="Button" class="btn btn-warning">Cancel</button></td>
      		</tr>
         </table>
     </td>
  </tr>
  <tr>
    <td valign="top"><table width="100%">
      <tr>
        <td width="150px">&nbsp;</td>
        <td width="150px">&nbsp;</td>
        <td width="150px">&nbsp;</td>
        <td width="100px"></td>
        <td  width="100px"></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<form action="" id="showProductDetails"></form>

            </div>
            <!-- /.row -->
           
        </div>
        <!-- /#page-wrapper -->
        
         <table width="100%" class="table table-striped table-bordered table-hover" id="hiddenTable">
      <tr>
        <th>Error Code</th>
        <th>Error Message</th>
        <th>Description</th>
      </tr>
    
     
    </table>

    </div>
    <!-- /#wrapper -->

   
<script type="text/javascript">
$(function() {
	var date = new Date();
	var currentMonth = date.getMonth();
	var currentDate = date.getDate();
	var currentYear = date.getFullYear();
	$("#Datepicker1").datepicker({
	minDate: new Date(currentYear, currentMonth, currentDate)
	});
});
/* $(function() {
	$( "#Datepicker1" ).datepicker(); 
});
$(function() {
	$( "#Datepicker2" ).datepicker(); 
});
$(function() {
	$( "#Datepicker3" ).datepicker(); 
}); */




$(document).ready(function() {
	
	
	
	$('#hiddenTable').hide();
	var servicesList = [];
    $('#addService').click(function() {
    	
    	if($('.serviceValidateClass').valid()){
    		
    		var serviceType = $("#serviceTypeId").val();
        	var serviceName = $("#serviceNameId :selected").text();
        	var coreServiceName = $("#serviceNameId :selected").val();
        	var srvccode = $("#serviceNameId").val();
        	var effectiveFrom = $("#Datepicker3").val();
        	var chargeType = $("#chargeTypeId").val();
        	var chargeName = $("#chargeNameId").val();
        	var chargeAmount = $("#chargeAmountId").val();
        	var lockInDaysId = $("#lockInDaysId").val();
           
       	var serviceObj = {
       			"srvccode":srvccode,
    		    "serviceType": serviceType,
    		    "serviceName": serviceName,
    		    "effectiveFrom": effectiveFrom,
    		    "chargeType": chargeType,
    		    "chargeName": chargeName,
    		    "chargeAmount": chargeAmount,
    		    "lockInDaysId": lockInDaysId,
    		};
       	var duplicateService = false;
       	if(servicesList.length > 0)
		{
       	 for (index = 0; index < servicesList.length; index++) {
             var loopObj = servicesList[index] ;
             var serviceCode =  loopObj["srvccode"];
             if(serviceCode == srvccode)
            	 duplicateService = true;
             else
            	 duplicateService = false;
         }
		}
           if(duplicateService == false){
        	   $('#gridTable').append('<tr class="child"><td>'+serviceName+'</td> <td>'+serviceType+'</td> <td>'+chargeName+'</td> <td>'+chargeType+'</td> <td>'+chargeAmount+'</td> <td>&nbsp;</td> <td>&nbsp;</td></tr>');
           	   servicesList.push(serviceObj);
           	   $('#chargeNameId').text("");
          	   $('#chargeAmountId').text("");
           }else{
        	   $("#id_srvcParamErr").html("Please enter atleast one Service Parameter.");
        	   $('#chargeNameId').text("");
          	   $('#chargeAmountId').text("");
           }
    	}
    	
	});
    
    
    $('#saveProductInfoId').click(function() {
    	
    	if(servicesList.length == 0)
		{
			$("#id_srvcParamErr").html("Please enter atleast one Service Parameter.");
		}
    	else if($('#product_form').valid()) 
		{
    		var productCode = $("#productCodeId").val();
        	var productName = $("#productNameId").val();
        	var validFrom = $("#Datepicker1").val();
        	var validTo = $("#Datepicker2").val();
        	
          
       	var productObj = {
    		    "productCode": productCode,
    		    "productName": productName,
    		    "validFrom": validFrom,
    		    "validTo": validTo,
    		    "servicesList": servicesList,
    		};
       	
       	var product = JSON.stringify(productObj); 
    	
    	alert(product)
    	
    	$.ajax({ 
    	     type: 'POST', 
    	     url: 'saveProductData', 
    	     data: product,
    	     contentType: 'application/json',
    	     dataType: 'json',
    	     success: function(response) { 
    	    	// var product = JSON.stringify(response); 
    	            var effectiveDate = response.effDate;
    	            var productCode = response.productCode;
    	            var outPut = response.outPut;
    	            var errorCode = response.errorCode;
    	            var errorMessage = response.errorMessage;
    	            var desc = response.desc;
    	            if(outPut == "success"){
    	            $('#showProductDetails').attr('action', '<c:url value="/pcs/getAllServicesByProductIds" />');
    	            $('#showProductDetails').append('<input type="hidden" name="effectiveDate" value="'+effectiveDate+'" /> ');
    	            $('#showProductDetails').append('<input type="hidden" name="prodcode" value="'+productCode+'" /> ');
    	            $('#showProductDetails').submit();
    	            }else{
    	                $('#page-wrapper').hide();
    	                $('#hiddenTable').show();
    	                $('#hiddenTable').append('<tr><td>'+errorCode+'</td> <td>'+errorMessage+'</td> <td>'+desc+'</td> </tr>');
    	                
    	            }
    	     },
    	     error: function (response) {
    	    	    var errorCode = response.errorCode;
    	            var errorMessage = response.errorMessage;
    	            var desc = response.desc;
    	            $('#page-wrapper').hide();
    	            $('#hiddenTable').show();
                    $('#hiddenTable').append('<tr><td>'+errorCode+'</td> <td>'+errorMessage+'</td> <td>'+desc+'</td> </tr>');
                    
    	        }
    	
    	
    	  }); 
    		
		}
    	
       
	});
    
    
});
</script>
</body>

</html>
