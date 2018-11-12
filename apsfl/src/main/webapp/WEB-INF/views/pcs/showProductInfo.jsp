        <div id="page-wrapper">
            <div class="row">
              <div class="col-lg-12">
                <div class="col-lg-12">
                  <h3 class="page-header">Product Information</h3>
                </div>
                </div>
              <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div align="left" style="margin-left:20px;">
<table width="1000px">
  <tr>
    <td width="800px" valign="top"><table width="100%">
      <tr>
        <td width="60px">Code</td>
        <td width="140px"><input type="text" name="textfield" class="form-control2" id="productCodeId" readonly="readonly" value="${product.productCode}"/></td>
        <td width="60px">Name</td>
        <td width="140px"><input type="text" name="textfield" class="form-control2" id="productNameId"  readonly="readonly" value="${product.productName}"/></td>
        <td width="70px">Valid From</td>
        <td width="130px"><input type="text" id="Datepicker1" class="form-control2"  readonly="readonly" value="${product.validFrom}"/></td>
        <%-- <td width="70px">Valid To</td>
        <td width="130px"><input type="text" id="Datepicker2" class="form-control2"  readonly="readonly" value="${product.validTo}"/></td> --%>
      </tr>
    </table></td></tr>
    
    <%--  <tr>
    <td valign="top"><table width="100%">
      <tr>
        <td><h4>ADD Services/Product</h4></td>
      </tr>
      <tr>
        <td width="1000px">
        <table width="100%" class="addServiceClass">
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
         <!--    <td width="100px">Effective From</td>
            <td width="100px"><input type="text" id="Datepicker3" class="form-control2"/></td> -->
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td width="100px">Charge Type</td>
            <td width="100px"><select class="form-control2" id="chargeTypeId">
                                                <option value="Monthly">Monthly Rental</option>
                                                <option value="Event">Event based</option>
                      </select></td>
            <td width="100px">Charge Name</td>
            <td width="100px"><input type="text" name="textfield" class="form-control2" id="chargeNameId"/></td>
            <td width="100px">Charge Amount</td>
            <td width="100px"><input type="text" name="textfield" class="form-control2" id="chargeAmountId"/></td>
            <td>&nbsp;</td>
            <td><button type="submit" class="btn btn-success" id="addService">Add Service</button></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr> --%>
    <tr>
    <td valign="top">&nbsp; 
    <table width="100%" class="table table-striped table-bordered table-hover" id="gridTable">
      <tr>
        <th>Service / Product Name</th>
        <th>Type</th>
        <th>Name</th>
        <th>Charge Type</th>
        <th>Charge</th>
        <th>Effective From</th>
        <!-- <th>Delete</th> -->
      </tr>
      
      <c:forEach var="service" items="${product.servicesList}">
      
        <tr>
        <th><input type="text" id="serviceName" readonly="readonly"  value="${service.serviceName}" class="form-control2"/></th>
        <th><input type="text" id="serviceType" readonly="readonly"  value="${service.serviceType}" class="form-control2"/></th>
        <th><input type="text" id="chargeName"  readonly="readonly"  value="${service.chargeName}" class="form-control2"/></th>
        <th><input type="text" id="chargeType" readonly="readonly"  value="${service.chargeType}" class="form-control2"/></th>
        <th><input type="text" id="charge"     readonly="readonly"   value="${service.chargeAmount}" class="form-control2"/></th>
        <th hidden="true"><input type="text" id="srvccode"     value="${service.srvccode}" class="form-control2" /></th>
        <th><input type="text" id="effectiveFrom" value="${service.effectiveFrom}" class="form-control2"  readonly="readonly"  /></th>
      </tr>
      
      </c:forEach>
    </table></td>
  </tr>
  <tr>
  	<td>
    <!-- 	<table width="100%">
        	<tr>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="100px">&nbsp;</td>
        		<td width="280px" align="right"><button type="submit" class="btn btn-primary" id="updateProductInfoId">Update</button></td>
       			<td width="20px" align="right"><button type="submit" class="btn btn-warning" id="cancelId">Cancel</button></td>
      		</tr>
         </table> -->
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

            </div>
            <!-- /.row -->
           
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

 
    
    
<script type="text/javascript">

$(document).ready(function() {
	
	$(function() {
		$(this).find(".effectiveFrom").datepicker(); 
	});
	
	$('#cancelId').click(function(){
		location.reload();
	});
	
	var servicesList = [];
/*     $(' .deleteService').click(function() {
    	
        var serviceName =  $(this).find('#serviceName').html();
        var serviceType =  $(this).find('#serviceType').html();
        var chargeType =   $(this).find('#chargeType').html();
        var chargeName =   $(this).find('#chargeName').html();
        var charge =  	   $(this).find('#charge').html();
        var srvccode  =    $(this).find('#srvccode').html();
        var effectiveFrom  =    $(this).find('#effectiveFrom').html();
        
       
        
     	var serviceObj = {
       			"srvccode":srvccode,
    		    "serviceType": serviceType,
    		    "serviceName": serviceName,
    		    "chargeType": chargeType,
    		    "chargeName": chargeName,
    		    "chargeAmount": charge,
    		    "effectiveFrom" : effectiveFrom,
    		    "addOrDelete" : "delete",
    		};
     
     	servicesList.push(serviceObj);
     	
	}); */
    
    $('.addServiceClass #addService').click(function() {
    	var serviceType = $("#serviceTypeId").val();
    	var serviceName = $(this).find("#serviceNameId").text();
    	var srvccode = $("#serviceNameId").val();
    	var effectiveFrom = $("#Datepicker3").val();
    	var chargeType = $("#chargeTypeId").val();
    	var chargeName = $("#chargeNameId").val();
    	var chargeAmount = $("#chargeAmountId").val();
       $('#gridTable').append('<tr><td>'+serviceName+'</td> <td>'+serviceType+'</td> <td>'+chargeName+'</td> <td>'+chargeType+'</td> <td>'+chargeAmount+'</td>  <td>&nbsp;</td></tr>');
      
   	var serviceObj = {
   			"srvccode":srvccode,
		    "serviceType": serviceType,
		    "serviceName": serviceName,
		    "effectiveFrom": effectiveFrom,
		    "chargeType": chargeType,
		    "chargeName": chargeName,
		    "chargeAmount": chargeAmount,
		    "addOrDelete" : "add",
		};
       
       servicesList.push(serviceObj);
	});
    
    
    $('#updateProductInfoId').click(function() {
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
	     url: 'deleteServicesInProduct', 
	     data: product,
	     contentType: 'application/json',
	     success: function(data) { 
	         alert(data); 
	         location.reload();
	     }
	  }); 
       
	});
    
    
});
</script>
</body>

</html>
