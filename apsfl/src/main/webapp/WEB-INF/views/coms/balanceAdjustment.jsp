<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <div class="main-content" > 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Balance<strong> Adjustments</strong></h2>
        <label id="error" style="text-align: center;color: green;" ${message}></label>
        <label id="error1" style="text-align: center; color: red;"></label>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">Home</a> </li>
            <li class="active">Balance Adjustments</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
            <form name="balanceAdjustmentForm" id="balanceAdjustmentForm" method="post" >
              <div class="row">
                <div class="col-sm-12">
                      <div class="row">
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Aadhar No</label>
                            <input type="text" name="AadharNo" id="AadharNo" class="form-control form-white "   value="<c:if test="${not empty searchTypeAadharNo}">${searchTypeAadharNo}</c:if>"  placeholder="Please Enter">
                          </div>
                        </div>
                        <div class="col-sm-1">
                            <br>
                            <label class="control-label">OR</label>
                        </div>
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Customer Id</label>
                            <input type="text" name="custId" id="custId" class="form-control form-white "  value="<c:if test="${not empty searchTypeCustId}">${searchTypeCustId}</c:if>"  placeholder="Please Enter">
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="form-group">
                            <label class="control-label hide-mobile">&nbsp;</label>
                            <div class="option-group">
                              <button class="btn btn-embossed btn-primary" type="button" id="BalanceAdjustmentSearchButton" ><i class="fa fa-search"></i>Search</button>
                            </div>
                          </div>
                        </div>
                      </div>
                </div>
              </div>
			  </form>
			  <c:if test="${not empty Smsg}"> <center><font color='green' size="3">${Smsg}</font></center></c:if>
			  <c:if test="${not empty Emsg}"> <center><font color='red' size="3">${Emsg}</font></center></c:if>
			  <label style="color:red; font-size:3;" id="message"></label> 
                   	<!-- if cafList is empty donot display div -->
                      <c:if test="${not empty cafList}">
	                      <div class="row m-b-5">
	                        <div class="col-sm-12">
		                          <table class="table table-alt" id="balanceAdjustTable">
										<thead>
											<tr>
												<th>Customer ID</th>
												<th>Aadhar No</th>
												<th>Customer Name</th>
												<th>Bill No</th>
												<th>Bill Month</th>
												<th>Bill Year</th>
												<th>Bill Date</th>
												<th>Total Amount</th>
												<th>Select</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${cafList}" var="caf" varStatus="rowNum">
													<tr>
														<td class="customerId">${caf.custId}</td>
														<td>${caf.aadharNo}</td>
														<td>${caf.custName}</td>
														<td>${caf.custinvno}</td>
														<td >${caf.invMonth}</td>
														<td>${caf.invYear}</td>
														<td>${caf.invDate}</td>
														<td>${caf.totalAmount}</td>
														<td>
														<form name="BalanceAdjustCustIdForm" action="<c:url value="/showCafinvDetailsByCustinvno"/>" id="BalanceAdjustCustIdForm" class="BalanceAdjustCustIdForm"method="post">
															<input type="hidden" id="custinvno" name ="custinvno" value="${caf.custinvno}"/>
															<input type="hidden" id="custId" name ="custId" value="${caf.custId}"/>
															<input type='submit' value='Select' class='btn btn-embossed btn-success'/>
														</form>
														</td>
													</tr>
											</c:forEach>
										</tbody>
									</table>
	                        </div>
	                      </div>
						<div class="clear">&nbsp;</div>
			 		  </c:if>
			 		  <div class="clear"></div>
					
			  <!-- END FORM -->
            </div>
            <!-- END MAIN PANEL CONTENT --> 
          </div>
          <!-- END MAIN PANEL --> 
        </div>
        <!-- HERE COMES YOUR CONTENT --> 
      </div>
      <!-- END MAIN ROW -->
      <div class="clear"></div>
    </div>
    <!-- END PAGE CONTENT -->   
  </div>
  <!-- END MAIN CONTENT --> 