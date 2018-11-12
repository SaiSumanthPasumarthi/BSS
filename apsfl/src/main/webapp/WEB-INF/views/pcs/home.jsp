
<div id="page-wrapper" class="row m-b-5">
<table  class="table table-striped table-bordered table-hover " id="gridTable">
      <tr>
        <th>Product Name</th>
        <th>Product Code</th>
        <th>Valid From</th>
        <th>Valid To</th>
        <th>Product Charge</th>
        <th>Tax Amount</th>
        <!-- <th>Delete</th> -->
      </tr>
      
      <c:forEach var="product" items="${productsList}">
      
        <tr>
        <th><a href=" <c:url value="/getAllServicesByProductIds">
         <c:param name="prodcode" value="${product.productCode}"/>
         <c:param name="effectiveDate" value= "${product.validFrom}"/> 
         <c:param name="tenantcode" value= "${product.tenantcode}"/> 
        </c:url> " >  ${product.productName} </a></th>
        <th><input type="text" readonly="readonly"  value="${product.productCode}" class="form-control2"/></th>
        <th><input type="text"  readonly="readonly" value = "${product.validFrom}" class="form-control2"/></th>
        <th><input type="text"  readonly="readonly" value = "${product.validTo}"   class="form-control2"/></th>
        <th><input type="text"   readonly="readonly"   value="${product.productCharge}" class="form-control2"/></th>
        <th><input type="text"  readonly="readonly"  value="${product.taxAmount}" class="form-control2"   /></th>
      </tr>
      
      </c:forEach>
    </table>
</div>