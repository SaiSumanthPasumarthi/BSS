<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.modal-dialog {
    width: 1000px;
    margin: 30px auto;
}

</style>
 <div class="main-content" > 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Balance<strong> Adjustments  CAF's</strong></h2>
        <label id="error" style="text-align: center;color: green;" ${message} ></label>
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
            
			  <c:if test="${not empty Smsg}"> <center><font color='green' size="3">${Smsg}</font></center></c:if>
			  <c:if test="${not empty Emsg}"> <center><font color='red' size="3">${Emsg}</font></center></c:if>
			  <label style="color:red; font-size:3;" id="message"></label> 
                   	<!-- if cafList is empty donot display div -->
                      <c:if test="${not empty cafList}">
	                      <div class="row m-b-5">
	                        <div class="col-sm-12">
		                          <table class="table table-alt balanceAdjustCafDetailsTable" id="balanceAdjustCafDetailsTable">
										<thead>
											<tr>
												<th>Bill No</th>
												<th>Customer ID</th>
												<th>Account No</th>
												<th>Bill Month</th>
												<th>Bill Year</th>
												<th>Bill Date</th>
												<th>Select</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${cafList}" var="caf" varStatus="rowNum">
													<tr>
														<td class="billNo">${caf.custinvno}</td>
														<td>${caf.custId}</td>
														<td class="clickedCafNo">${caf.acctcafno}</td>
														<td class="clickedMonth">${caf.invMonth}</td>
														<td>${caf.invYear}</td>
														<td>${caf.invDate}</td>
														<td><input type='radio' class='radioCafs'  name='radioCaf' data-toggle="modal" data-target="#myModal"/></td>
													</tr>
											</c:forEach>
										</tbody>
									</table>
									
	                        </div>
	                      </div>
						<div class="clear">&nbsp;</div>
			 		  </c:if>
			 		  <div id="myModal" class="modal fade" role="dialog">
						  <div class="modal-dialog">
						    <!-- Modal content-->
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						        <h4 class="modal-title">Balance Adjustments</h4>
						      </div>
						      <div class="modal-body" id="cafsPopUpDiv">
						      <table id="cafBalAdjModalTable" class="table table-alt cafBalAdjModalTable">
						      	<thead>
									<tr>
										<th>Bill No</th>
										<th>Account No</th>
										<th>Charge Code</th>
										<th>Product Code</th>
										<th>Charge Amount</th>
										<th>Tax Amount</th>
										<th>Adjustment Amount</th>
									</tr>
								</thead>
								<tbody></tbody>
						      </table>
						      </div>
						      <div class="modal-footer">
						      	<label id="balanceError" style="text-align: left;color: red;"></label>
						      	<button type="button" class="btn btn-default" id="balAdjBalanceSubmit">Submit</button>
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						      </div>
						    </div>
					  	</div>
					</div>
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