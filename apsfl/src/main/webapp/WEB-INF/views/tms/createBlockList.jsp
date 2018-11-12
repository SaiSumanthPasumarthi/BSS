<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <div class="main-content" > 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Create<strong> BlackList</strong></h2>
        <label id="error" style="text-align: center;color: green;" value="${message}"></label>
        <label id="error1" style="text-align: center; color: red;"></label>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">Home</a> </li>
            <li class="active">Create BlackList</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
            <form name="createBlockListForm" id="createBlockListForm" method="post" >
              <div class="row">
                <div class="col-sm-12">
                      <div class="row">
                        <div class="col-sm-2">
                          <div class="form-group">
                            <label class="control-label">Mobile Number</label>
                            <input type="text" name="mobileNo" id="mobileNo" class="form-control form-white " maxlength="10"  value="<c:if test="${not empty searchTypeMob}">${searchTypeMob}</c:if>"
                            placeholder="Please Enter">
                          </div>
                        </div>
                        <div class="col-sm-1">
                            <br>
                            <label class="control-label">OR</label>
                        </div>
                        <div class="col-sm-2">
                          <div class="form-group">
                            <label class="control-label">STB-SerialNo</label>
                            <input type="text" name="stbNo" id="stbNo" class="form-control form-white "  value="<c:if test="${not empty searchTypeStb}">${searchTypeStb}</c:if>" 
                            placeholder="Please Enter">
                          </div>
                        </div>
                        <div class="col-sm-1">
                            <br>
                            <label class="control-label">OR</label>
                        </div>
                        <div class="col-sm-2">
                          <div class="form-group">
                            <label class="control-label">STB-MacAddress</label>
                            <input type="text" name="stbMac" id="stbMac" class="form-control form-white "  value="<c:if test="${not empty searchTypeStbMac}">${searchTypeStbMac}</c:if>" 
                            placeholder="Please Enter">
                          </div>
                        </div>
                        <div class="col-sm-2">
                          <div class="form-group">
                            <label class="control-label hide-mobile">&nbsp;</label>
                            <div class="option-group">
                              <button class="btn btn-embossed btn-primary" type="button" id="BlockListSearchButton" ><i class="fa fa-search"></i>Search</button>
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
		                          <table class="table table-alt" id = "cafToBlockListTable">
											<thead>
												<tr>
													<th>Caf-No</th>
													<th>Aadhar-No</th>
													<th  hidden="hidden">Customer-Id</th>
													<th>Customer-Name</th>
													<th>Mobile Number</th>
													<th>STB-SerialNo</th>
													<th>STB-MacAddress</th>
													<th hidden="hidden">stbcafno</th>
													<th>Select</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cafList}" var="caf" varStatus="rowNum">
												  <c:if test="${caf.status=='6'}">
												  	<%-- <c:if test="${((not empty searchTypeMob) && (searchTypeMob==caf.phone)) || ((not empty searchTypeStb) && (searchTypeStb==caf.stbslno)) || ((not empty searchTypeStbMac) && (searchTypeStbMac==caf.stbmac))}"> --%>
														<tr>
															<td class="cafno">${caf.cafno}</td>
															<td>${caf.aadharno}</td>
															<td  hidden="hidden" class="custid">${caf.custid}</td>
															<td >${caf.fname} ${caf.lname }</td>
															<td>${caf.phone}</td>
															<td>${caf.stbslno}</td>
															<td>${caf.stbmac}</td>
															<td hidden="hidden"  class="stbcafno">${caf.stbcafno}</td>
															<td><input type='radio' class='radiostb' id="radiostb" name='radios'/></td>
														</tr>
													<%-- </c:if> --%>
												</c:if>
											</c:forEach>
												<%-- </c:if> --%>
											</tbody>
										</table>
										<div>
					                      <form name="BlockCustomerForm" id="BlockCustomerForm" method="post">
						                     <table cellspacing="5" cellpadding="5" id="resonTable">
							                    <tr>
							                   		 <td >
														<input type="hidden" id ="affectedcafs" name = "affectedcafs">
														<input type="hidden" id ="custid" name = "custid">
														<input type="hidden" id ="stbcafno" name = "stbcafno">
							                     		<label class="control-label pull-left">Reason for Blocking :: </label>
							                     	</td >
						                     		<td >
						                     			<textarea style="width: 500px; height: 40px;" class="form-control form-white" name="reason" id="reasonForBlocking" 
						                     			placeholder="Reason for Blocking" required="required"></textarea>
						                     		</td >
						                     		<td> &nbsp;&nbsp; </td>
													<td ><button class="btn btn-embossed btn-primary" type="button" id="MarkasBlock" disabled>Mark As Block</button></td>
													<td ><button class="btn btn-embossed btn-danger" type="button" id="blacklistcancel">Cancel</button>
												 	</td>
												</tr>
												
											</table>
										</form>
									</div>
	                        </div>
	                      </div>
						<div class="clear">&nbsp;</div>
			 		  </c:if>
			 		  <div class="clear"></div>
			 		  <div class="row">
							<div class="col-sm-12">
								<div class="panel">
									<div class="panel-header bg-light">
										<h3> BlackList Details</h3>
									</div>
									<div class="panel-content">
										<div class="row">
											<div class="col-sm-12">
							<table class="table table-alt " id="blockListedDetailsTable">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Aadhar-No</th>
										<th >Caf-No</th>
										<th>Mobile Number</th>
										<th >STB-SerialNo</th>
										<th>STB-MacAddress</th>
										<th >BlackListed-Date</th>
										<th >Reason</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									 <c:forEach items="${blockedList}" var="blocked" varStatus="rowNum">
										<tr>
											<td>${rowNum.count}</td>
											<td>${blocked.custid}</td>
											<td>${blocked.affectedcafs}</td>
											<td>${blocked.phone}</td>
											<td>${blocked.stbSlno}</td>
											<td>${blocked.stbmac}</td>
											<td>
												<c:if test="${blocked.status=='0'}">${blocked.effectivefrom}</c:if>
												<c:if test="${blocked.status!='0'}">${blocked.approvedon}</c:if>
											</td>
											<td>${blocked.reason}</td>
											<td align="center">
												<c:choose>
													<c:when test="${blocked.status=='0'}">Pending</c:when>
												    <c:when test="${blocked.status=='1'}">APPROVED</c:when>  
												    <c:when test="${blocked.status=='2'}">REJECTED</c:when>  
												    <c:when test="${blocked.status=='4'}">Corpus Failed</c:when>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					</div>
					</div>
					</div>
					</div>
					</div>
					</div>
					
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