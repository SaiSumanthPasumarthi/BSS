<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="admin-themes-lab">
<meta name="author" content="themes-lab">
</head>
<body>
<form:form method="POST" modelAttribute="TenantForm" id="form_id" enctype="multipart/form-data">
<section>
  <!-- BEGIN MAIN CONTENT -->
  <div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Tenant</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./">HOME</a> </li>
            <li class="active">TENANT SERVICE</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
            <div class="row">
				<div class="col-sm-12">
				<div class="panel">
				<div class="panel-header bg-light">
					<h3>Tenant Information</h3>
				</div>
				<div class="panel-content">
				<div class="row">
				<input type="hidden" name="tenantViewStatus" id="id_status" value="${viewStatus}"/>
				<input type="hidden" name="tenantTypelogin" id="tenantTypelogin" value="${tenantType}"/>
				<input type="hidden" id="id_tenentId" value="${tenantId}"/>
					<div class="col-sm-4">
						<div class="form-group">
						<label class="control-label">Tenant Type <span style="color: red;">*</span></label>
						<div class="option-group">
						 <form:select  path="tenantTypeLov" id="tenantTypeID" cssClass="form-control form-white tenantRegClass tenanttypeEnter">
    				 		<form:option value="">-Select-</form:option>
    						<form:options items="${tenantTypes}" itemValue="lovValue" itemLabel="lovValue"></form:options>
  					 	 </form:select>
  					 	 <form:select  path="tenantTypeLov" id="tenantTypeID" cssClass="form-control form-white tenantRegClass tenanttypeEdit" disabled="true" style="display:none;">
    				 		<form:option value="">-Select-</form:option>
    						<form:options items="${tenantTypes}" itemValue="lovValue" itemLabel="lovValue"></form:options>
  					 	 </form:select>
						</div>
						</div>
					</div>
					<form:hidden id="tenantId" path="tenantId" value="${tenantId}"/>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Tenant Code<span style="color: red;">*</span></label>
						 <form:input id="tenantCode" path="tenantCode" cssClass="form-control form-white specialCharacters tenantRegClass" placeholder="Enter Tenant Code" maxlength="20"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Enrollment Number<span style="color: red;">*</span></label> 
						 <form:input id="portalEnrllmentno" path="portalEnrllmentno" cssClass="form-control form-white specialCharacters tenantRegClass" placeholder="Enter Enrollment Number" maxlength="100"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Name<span style="color: red;">*</span></label> 
						 <form:input id="name" path="name" cssClass="form-control form-white charectersonly tenantRegClass" placeholder="Enter Name" maxlength="100"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="form-group">
						 <label class="form-label">Aadhar</label> 
						 <form:input id="aadharCardNo" path="aadharCardNo" cssClass="form-control form-white number" placeholder="Enter Aadhar Card Number" maxlength="12"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">PAN</label>
						 <form:input id="panNo" path="panNo" cssClass="form-control form-white specialCharacters" placeholder="Enter PAN" maxlength="10"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
					  	 <label class="control-label">TAN</label> 
					  	 <form:input id="tanNo" path="tanNo" cssClass="form-control form-white number" placeholder="Enter TAN" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">TIN</label>
						 <form:input id="tinNo" path="tinNo" cssClass="form-control form-white number" placeholder="Enter TIN" maxlength="50"/>
					 	</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
						 <label class="form-label">GST</label> 
						 <form:input id="gstNo" path="gstNo" cssClass="form-control form-white number" placeholder="Enter GST" maxlength="50"/>
					 	</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">VAT</label>
						 <form:input id="vatNo" path="vatNo" cssClass="form-control form-white number" placeholder="Enter VAT" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Postal Registration No</label> 
						 <form:input id="portalPostalRegno" path="portalPostalRegno" cssClass="form-control form-white" placeholder="Enter Postal Registration No" maxlength="50"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Postal Expiration Date</label>
						 <form:input id="portalPostExpDate" path="portalPostExpDate" cssClass="form-control form-white" placeholder="Expiration Date" maxlength="50"/>
						</div>
					</div>
				</div>
				<div class="row lmoClass">
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Credit Limit</label>
						 <form:input id="creditLimit" path="creditLimit" cssClass="form-control form-white number" placeholder="Credit Limit" maxlength="50" />
						</div>
					</div>
					<div class="col-sm-4 walletClass">
						<div class="form-group">
						 <label class="control-label">Wallet Amount</label>
						 <form:input id="walletAmt" path="walletAmt" cssClass="form-control form-white number" placeholder="Wallet Amount" maxlength="50" />
						</div>
					</div>
				</div>
			</div><!-- end panel-content -->
			</div><!-- end panel -->
		</div><!-- end col-sm-12 -->
		</div><!-- end row -->	
		<c:if test="${tenantType == 'LMO'}">
		<div class="row">
				<div class="col-sm-12">
				<div class="panel">
				<div class="panel-header bg-light">
					<h3>Substations Mapping</h3>
				</div>
				<div class="panel-content">
				<div class="row">
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">District</label>
					    	<form:select path="" id="v_districtID" cssClass="form-control form-white villClass">
			    				<form:option value="">-Select-</form:option>
			    				<form:options items="${apDistrictList}"></form:options>	 		
			  				</form:select>         
					    </div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">Substation</label>
			  				<select class="form-control form-white" id="v_substationID">
			  				</select>           
					    </div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">Count</label>
					    	 <form:input id="v_countID" path="" cssClass="form-control form-white number" placeholder="Enter Count" maxlength="50"/>
					    </div>
					</div>
					</div>
					<div class="row">
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">Installation District</label>
					    	<form:select path="" id="v_instDistID" cssClass="form-control form-white villClass">
			    				<form:option value="">-Select-</form:option>
			    				<form:options items="${apDistrictList}"></form:options>
			  				</form:select>         
					    </div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">Installation Mandal</label>
					    	<form:select path="" id="v_mandalID" cssClass="form-control form-white villClass">
			    				<form:option value="">-Select-</form:option>
			  				</form:select>         
					    </div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">Installation Village</label><br>
			  				<select class="form-control form-white" id="v_villageID" multiple>
			  				</select>      
					    </div>
					</div>
					<div class="col-sm-3">
						<div class="form-group">
							<label class="form-label">&nbsp;</label>
							<button class="btn btn-embossed btn-success" id="id_addbtn" type="button" value="Add">Add</button>       
					    </div>
					</div>
				</div>
				<div class="row">
                        <div class="col-sm-12 disable-search">
                         <input type="hidden" value="${fn:length(tbareasList)}" id="id_substabCnt"/>
                          <table id="subsMappingTable" class="table table-alt">
                            <thead>
                              <tr>
                                <th>District</th>
                                <th>Mandal</th>
                                <th>Village</th>
                                <th>Substations</th> 
                                <th>Count</th>
                                <th class="delaction" >Action</th>
								<!-- <th class="disableView">Action</th> -->
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${tbareasList}" var="tba" varStatus="status">
			                    <tr>                  
			                    	<td><input type="hidden" name="tenantsbaList[${status.index}].districtuid" value="${tba.districtuid}"/>${tba.districtName}</td>
			                    	<td><input type="hidden" name="tenantsbaList[${status.index}].mandalslno" value="${tba.mandalslno}"/>${tba.mandalName}</td>
			                    	<td><input type="hidden" name="tenantsbaList[${status.index}].villageslno" value="${tba.villageslno}"/>${tba.villageName}</td>
			                    	<td><input type="hidden" name="tenantsbaList[${status.index}].substnuid" value="${tba.substnuid}"/>${tba.subStationName}</td>
			                    	<td><input type="hidden" name="tenantsbaList[${status.index}].subscribercnt" value="${tba.subscribercnt}"/>${tba.subscribercnt}</td>
			                    	<td class="delaction"  ></td>
			                    	<!-- <td class="disableView">
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-dark villagesVListEdit" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a>
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-danger villagesVListDel" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>
			                    	</td> -->
			                    </tr>
			                </c:forEach> 
                            </tbody>
                          </table>
                        </div>
                </div>
			</div><!-- end panel-content -->
			</div><!-- end panel -->
		</div><!-- end col-sm-12 -->
		</div><!-- end row -->
		</c:if>
		<%-- <div class="row">
				<div class="col-sm-12">
				<div class="panel">
				<div class="panel-header bg-light">
					<h3>Substations</h3>
				</div>
				<div class="panel-content">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label class="form-label">Substation</label>
					    	<form:select path="" id="v_substationID" cssClass="form-control form-white subsClass">
			    				<form:option value="">-Select-</form:option>
			    				<form:options items="${vsubstationList}"></form:options>	 		
			  				</form:select>         
					    </div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="form-label">&nbsp;</label>
							<button class="btn btn-embossed btn-success" id="id_addsubsbtn" type="button" value="Add">Add</button>       
					    </div>
					    <input type="hidden" id="id_substationsEnrolled" name="substationsEnrolled" value="${TenantForm.substationsEnrolled}"/>
					</div>
				</div>
				<div class="row">
                        <div class="col-sm-12 disable-search">
                          <table id="subStationTable" class="table table-alt">
                            <thead>
                              <tr>
                                <th>Substations</th>    
								<!-- <th class="disableView">Action</th> -->
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${substationsList}" var="subs" varStatus="status">
			                    <tr>                    
			                    	<td>${subs.subStationName}</td>
			                    	<!-- <td class="disableView">
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-dark villagesVListEdit" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a>
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-danger villagesVListDel" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>
			                    	</td> -->
			                    </tr>
			                </c:forEach> 
                            </tbody>
                          </table>
                        </div>
                </div>
			</div><!-- end panel-content -->
			</div><!-- end panel -->
		</div><!-- end col-sm-12 -->
		</div><!-- end row --> --%>
		<div class="row">
			<div class="col-sm-12">
			<div class="panel">
			<div class="panel-header bg-light">
				<h3>Registered Office Details</h3>
			</div>
			<div class="panel-content">
			<div class="row">
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Point of Contact Name</label> 
						 <form:input id="pocName" path="pocName" cssClass="form-control form-white nonumbers" placeholder="Enter Point of Contact Name" maxlength="40"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Mobile Number</label>
						 <form:input id="pocMobileNo1" path="pocMobileNo1" cssClass="form-control form-white number" placeholder="Enter Point of Contact Mobile Number" maxlength="10"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Email Id<span style="color: red;">*</span></label>
						 <c:choose> 
						 <c:when test="${viewStatus == 'Edit'}">
							<form:input id="emailId1" path="emailId1" cssClass="form-control form-white tenantRegClass" placeholder="Enter Email Id" maxlength="100"/>
						 </c:when>
						 <c:otherwise>
							<input type="email" id="emailId1" name="emailId1" class="form-control form-white tenantRegClass" placeholder="Enter Email Id" maxlength="100"/>
						 </c:otherwise>
						 </c:choose>
						</div>
					</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Address Line 1</label>
					 <form:input id="address1" path="address1" cssClass="form-control form-white" placeholder="Enter Address 1" maxlength="225"/>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Address Line 2</label>
					 <form:input id="address2" path="address2" cssClass="form-control form-white" placeholder="Enter Address 2" maxlength="225"/>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
					 <label class="form-label">Locality</label>
					 <form:input id="locality" path="locality" cssClass="form-control form-white" placeholder="Enter Locality" maxlength="100"/> 
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
						<label class="control-label">Area</label>
						<form:input id="area" path="area" cssClass="form-control form-white" placeholder="Enter Area" maxlength="100"/> 
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
				 	 <label class="control-label">City</label>
				 	 <form:input id="city" path="city" cssClass="form-control form-white" placeholder="Enter City" maxlength="50"/> 
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
					 <label class="form-label">State</label>
					 <form:input id="stateName" path="stateName" cssClass="form-control form-white " placeholder="Enter State" maxlength="50"/> 
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
						<div class="form-group">
							<label class="form-label">District <span style="color: red;">*</span></label>
					    	<form:select path="portal_districtid" id="portal_districtid" cssClass="form-control form-white tenantRegClass">
			    				<form:option value="">-Select-</form:option>
			    				<form:options items="${apDistrictList}"></form:options>
			  				</form:select>         
					    </div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="form-label"> Mandal <span style="color: red;">*</span></label>
					    	<form:select path="portal_mandalid" id="portal_mandalid" cssClass="form-control form-white tenantRegClass">
			    				<form:option value="">-Select-</form:option>
			    				<form:options items="${apMandalList}"></form:options>
			  				</form:select>         
					    </div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
							<label class="form-label"> Village <span style="color: red;">*</span></label>
							<form:select path="portal_villageid" id="portal_villageid" cssClass="form-control form-white tenantRegClass">
			    				<form:option value="">-Select-</form:option>
			    				<form:options items="${apVillageList}"></form:options>
			  				</form:select> 
					    </div>
					</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Pin Code</label>
					 <form:input id="pincode" path="pincode" cssClass="form-control form-white" placeholder="Enter Pincode" maxlength="6"/>  
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Landline No</label>
					 <div class="clear"></div>
					 <div class="col-sm-4 p-l-0">
					 	<div class="form-group">
					 	 <form:input id="stdcode" path="stdcode" cssClass="form-control form-white number" placeholder="Enter STD Code" maxlength="5"/>
						</div>
					 </div>
					 <div class="col-sm-8 p-l-0">
						<div class="form-group">
						 <form:input id="landline1" path="landline1" cssClass="form-control form-white number" placeholder="Enter Land Line No" maxlength="10"/>
						</div>
					 </div>
					 <div class="clear"></div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Fax</label> 
					 <form:input id="fax1" path="fax1" cssClass="form-control form-white" placeholder="Enter Fax" maxlength="10"/>
					</div>
				</div>
			</div>
			</div>
		</div>
		</div>
		</div><!-- end row -->
		<div class="row">
			<div class="col-sm-12">
			<div class="panel">
			<div class="panel-header bg-light">
				<h3>Local Office Details</h3>
			</div>
			<div class="panel-content">
			<div class="row">
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Point of Contact Name</label> 
						 <form:input id="localOfficePocName" path="localOfficePocName" cssClass="form-control form-white nonumbers" placeholder="Enter Point of Contact Name" maxlength="50"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Mobile Number</label>
						 <form:input id="localOfficePocMobileNo1" path="localOfficePocMobileNo1" cssClass="form-control form-white number" placeholder="Enter Point of Contact Mobile Number" maxlength="10"/>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="form-group">
						 <label class="control-label">Email Id</label>
						 <c:choose>
						 <c:when test="${viewStatus == 'Edit'}">
						 	<form:input id="localOfficeEmailId1" path="localOfficeEmailId1" cssClass="form-control form-white" placeholder="Enter Email Id" maxlength="100"/>
						 </c:when>
						 <c:otherwise>
						 	<input type="email" id="localOfficeEmailId1" name="localOfficeEmailId1" class="form-control form-white" placeholder="Enter Email Id" maxlength="100"/>
						 </c:otherwise> 
						 </c:choose>
						</div>
					</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Address Line 1</label>
					 <form:input id="localOfficeAddress1" path="localOfficeAddress1" cssClass="form-control form-white" placeholder="Enter Address 1" maxlength="225"/>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Address Line 2</label>
					 <form:input id="localOfficeAddress2" path="localOfficeAddress2" cssClass="form-control form-white" placeholder="Enter Address 2" maxlength="225"/>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
					 <label class="form-label">Locality</label>
					 <form:input id="localOfficeLocality" path="localOfficeLocality" cssClass="form-control form-white" placeholder="Enter Locality" maxlength="100"/> 
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
						<label class="control-label">Area</label>
						<form:input id="localOfficeArea" path="localOfficeArea" cssClass="form-control form-white" placeholder="Enter Area" maxlength="100"/> 
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
				 	 <label class="control-label">City</label>
				 	 <form:input id="localOfficeCity" path="localOfficeCity" cssClass="form-control form-white" placeholder="Enter City" maxlength="50"/> 
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
					 <label class="form-label">State</label>
					 <form:input id="localOfficeStateName" path="localOfficeStateName" cssClass="form-control form-white" placeholder="Enter State" maxlength="50"/> 
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Pin Code</label>
					 <form:input id="localOfficePincode" path="localOfficePincode" cssClass="form-control form-white" placeholder="Enter Pincode" maxlength="6"/>  
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Landline No</label>
					 <div class="clear"></div>
					 <div class="col-sm-4 p-l-0">
					 	<div class="form-group">
					 	 <form:input id="localOfficestdcode" path="localOfficestdcode" cssClass="form-control form-white number" placeholder="Enter STD Code" maxlength="5"/>
						</div>
					 </div>
					 <div class="col-sm-8 p-l-0">
						<div class="form-group">
						 <form:input id="localOfficeLandline1" path="localOfficeLandline1" cssClass="form-control form-white number" placeholder="Enter Land Line No" maxlength="10"/>
						</div>
					 </div>
					 <div class="clear"></div>
					</div>
				</div>
				<div class="col-sm-4">
					<div class="form-group">
					 <label class="control-label">Fax</label> 
					 <form:input id="localOfficeFax1" path="localOfficeFax1" cssClass="form-control form-white" placeholder="Enter Fax" maxlength="10"/>
					</div>
				</div>
			</div>
			</div>
		</div>
		</div>
		</div><!-- end row -->
		<div class="row">
		  <div class="col-sm-12">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>Serving Areas <img style="cursor: pointer;" class="addAreas" src="./resources/images/Add_Assets.png" align="right"/></h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-12">
		            <div class="form-group">
		            <c:choose>
		            <c:when test="${AreasStatus == 'Create'}">
					      <div class="panel cIndex" style="border: 1px solid #d4d5d6;">
							<div class="panel-content">
					        <div class="row areadivView">
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">Area Name</label>
					              <input type="text" name="pareas[0].areaname" id="areanameID" class="form-control form-white areaClass" maxlength="75" placeholder="Enter Area Name"/>
					            </div>
					          </div>
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="form-label">Cable Type</label>
					              <!-- <input type="text" id="areas_cabletypeID" class="form-control form-white areaClass" maxlength="30" placeholder="Enter Cabletype Id"/> -->
					              <form:select path="pareas[0].areas_cabletypeid" id="cableTypeID" cssClass="form-control form-white areaClass">
			    				  	<form:option value="">-Select-</form:option>
			    					<form:options items="${cableList}"></form:options>
			  					  </form:select>
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="control-label">Running Cable Length</label>
					              <input type="text" name="pareas[0].runningcablelen" id="runningcablelenID" class="form-control form-white areaClass number" maxlength="16" placeholder="Enter Running Cablelen"/>
					            </div>
					          </div>
					          <div class="col-md-3">
								<div style="float:right; clear:right;">
									<img style="cursor: pointer;" width="15" height="15" class="closeAreas" src="./resources/images/close1.png">
					            </div>
							  </div>
					        </div>
					        <div class="row areadivView">
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">State</label> 
					            	<!-- <input type="text" id="stateID" class="form-control form-white areaClass number" placeholder="Enter State Id" /> -->
					            	<form:select path="pareas[0].stateid" id="stateID" cssClass="form-control form-white areaClass stateClass">
			    				 		<form:option value="">-Select-</form:option>
			    						<form:options items="${stateList}"></form:options>
			  					 	</form:select>
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="form-label">District</label>
					              <!-- <input type="text" id="districtID" class="form-control form-white areaClass number" placeholder="Enter District Id" /> -->
					              <form:select path="pareas[0].districtid" id="districtID" cssClass="form-control form-white areaClass districtClass">
			    				 		<form:option value="">-Select-</form:option>
			    						<form:options items="${districtList}"></form:options>
			  					  </form:select>
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="control-label">Mandal</label>
					              <!-- <input type="text" id="mandalID" class="form-control form-white areaClass number" placeholder="Enter Mandal Id" /> -->
					              <form:select path="pareas[0].mandalid" id="mandalID" cssClass="form-control form-white areaClass mandalClass">
			    				 		<form:option value="">-Select-</form:option>
			    						<form:options items="${mandalList}"></form:options>
			  					  </form:select>
					            </div>
					          </div>
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">Village</label>
					              <!-- <input type="text" id="villageID" class="form-control form-white areaClass number" placeholder="Enter Village Id" /> -->
					              <form:select path="pareas[0].villageid" id="villageID" cssClass="form-control form-white areaClass villageClass">
			    				 		<form:option value="">-Select-</form:option>
			    						<form:options items="${villageList}"></form:options>
			  					  </form:select>
					            </div>
					          </div>
					        </div>
					        <div class="row areadivView">
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="form-label">Subscription Count</label> 
					              <input type="text" name="pareas[0].subscription_cnt" id="subscription_cntID" class="form-control form-white areaClass number" placeholder="Enter Subscription Cnt" />
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="control-label">Connection Count</label> 
					              <input type="text" name="pareas[0].conn_cnt" id="conn_cntID" class="form-control form-white areaClass number" placeholder="Enter Connection Count" />
					            </div>
					          </div>
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">Digital Connection Count</label> 
					              <input type="text" name="pareas[0].digconn_cnt" id="digconn_cntID" class="form-control form-white areaClass number" placeholder="Enter Digital Connection Count" />
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="control-label">Analog Connection Count</label>
					              <input type="text" name="pareas[0].anlconn_cnt" id="anlconn_cntID" class="form-control form-white areaClass number" placeholder="Enter Analog Connection Count" />
					            </div>
					          </div>
					        </div>
					       </div>
					     </div>
				     </c:when>
				     <c:when test="${AreasStatus == 'Edit'}">
				     	<c:forEach items="${portalareasList}" var="areaParam" varStatus="status">
				     	 	<div class="panel cIndex" style="border: 1px solid #d4d5d6;">
			                    	<div class="panel-content">
									   <div class="row">
								          <div class="col-sm-3">
								            <div class="form-group">
								              <input type="hidden" name="pareas[${status.index}].areaid" value="${areaParam.areaid}"/>
								              <label class="control-label">Area Name</label>
								              <input type="text" name="pareas[${status.index}].areaname" value="${areaParam.areaname}" class="form-control form-white areaClass" maxlength="75" placeholder="Enter Area Name"/>
								            </div>
								          </div>
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="form-label">Cable Type</label>
								              <select name="pareas[${status.index}].areas_cabletypeid" id="cableTypeID" class="form-control form-white areaClass">
												<option value="">--Select--</option>
												<c:forEach var="cables" items="${cableList}">
													<c:choose>
														<c:when test="${not empty areaParam.areas_cabletypeid && cables.value == areaParam.areas_cabletypeid}">
															<option value="${cables.key}" selected>${cables.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${cables.key}">${cables.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="control-label">Running Cable Length</label>
								              <input type="text" name="pareas[${status.index}].runningcablelen" value="${areaParam.runningcablelen}" class="form-control form-white areaClass number" maxlength="16" placeholder="Enter Running Cablelen"/>
								            </div>
								          </div>
								          <div class="col-md-3">
											<div style="float:right; clear:right;">
												<img style="cursor: pointer;" width="15" height="15" class="closeAreas" src="./resources/images/close1.png">
								            </div>
										  </div>
								        </div>
								        <div class="row">
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="control-label">State</label> 
								              <select name="pareas[${status.index}].stateid" id="stateID" class="form-control form-white areaClass stateClass">
												<option value="">--Select--</option>
												<c:forEach var="states" items="${stateList}">
													<c:choose>
														<c:when test="${not empty areaParam.stateid && states.key == areaParam.stateid}">
															<option value="${states.key}" selected>${states.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${states.key}">${states.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="form-label">District</label>
								              <%-- <input type="text" value="${areaParam.districtid}" class="form-control form-white areaClass number" placeholder="Enter District Id" /> --%>
								               <select name="pareas[${status.index}].districtid" id="districtID" class="form-control form-white areaClass districtClass">
												<option value="">--Select--</option>
												<c:forEach var="districts" items="${areaParam.districtList}">
													<c:choose>
														<c:when test="${not empty areaParam.districtid && districts.key == areaParam.districtid}">
															<option value="${districts.key}" selected>${districts.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${districts.key}">${districts.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="control-label">Mandal</label>
								              <%-- <input type="text" value="${areaParam.mandalid}" class="form-control form-white areaClass number" placeholder="Enter Mandal Id" /> --%>
								               <select name="pareas[${status.index}].mandalid" id="mandalID" class="form-control form-white areaClass mandalClass">
												<option value="">--Select--</option>
												<c:forEach var="mandals" items="${areaParam.mandalList}">
													<c:choose>
														<c:when test="${not empty areaParam.mandalid && mandals.key == areaParam.mandalid}">
															<option value="${mandals.key}" selected>${mandals.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${mandals.key}">${mandals.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="control-label">Village</label>
								              <%-- <input type="text" value="${areaParam.villageid}" class="form-control form-white areaClass number" placeholder="Enter Village Id" /> --%>
								              <select name="pareas[${status.index}].villageid" id="villageID" class="form-control form-white areaClass villageClass">
												<option value="">--Select--</option>
												<c:forEach var="villages" items="${areaParam.villageList}">
													<c:choose>
														<c:when test="${not empty areaParam.villageid && villages.key == areaParam.villageid}">
															<option value="${villages.key}" selected>${villages.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${villages.key}">${villages.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								        </div>
								        <div class="row">
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="form-label">Subscription Count</label> 
								              <input type="text"  name="pareas[${status.index}].subscription_cnt" value="${areaParam.subscription_cnt}" class="form-control form-white areaClass number" placeholder="Enter Subscription Cnt" />
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="control-label">Connection Count</label> 
								              <input type="text" name="pareas[${status.index}].conn_cnt" value="${areaParam.conn_cnt}" class="form-control form-white areaClass number" placeholder="Enter Connection Count" />
								            </div>
								          </div>
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="control-label">Digital Connection Count</label> 
								              <input type="text" name="pareas[${status.index}].digconn_cnt" value="${areaParam.digconn_cnt}" class="form-control form-white areaClass number" placeholder="Enter Digital Connection Count" />
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="control-label">Analog Connection Count</label>
								              <input type="text" name="pareas[${status.index}].anlconn_cnt" value="${areaParam.anlconn_cnt}" class="form-control form-white areaClass number" placeholder="Enter Analog Connection Count" />
								            </div>
								          </div>
								        </div>
								      </div>
								</div>
			               </c:forEach>
				     </c:when>
				     </c:choose>
				     </div>
				     <div class="form-group areasINDiv">
		             </div>
				  </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div><!-- end row -->
		<div class="row">
		  <div class="col-sm-12">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>Serving Assets <img style="cursor: pointer;" class="addAssets" src="./resources/images/Add_Assets.png" align="right"/></h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-12">
		            <div class="form-group">
		            <c:choose>
		            <c:when test="${AssetsStatus == 'Create'}">
			            <div class="panel rIndex" style="border: 1px solid #d4d5d6;">
			              <div class="panel-content">
			              	<div class="row assestsdivView">
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">Cable Type</label>
					              <form:select path="passets[0].cabletypeid" id="cableTypeID" cssClass="form-control form-white tenantRegClass1">
	    				 				<form:option value="">-Select-</form:option>
	    								<form:options items="${cableList}"></form:options>
	  					 			</form:select>
					            </div>
					          </div>
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="form-label">Asset Type</label>
					              <form:select path="passets[0].assettypeid" id="assetTypeID" cssClass="form-control form-white tenantRegClass1">
	    				 				<form:option value="">-Select-</form:option>
	    								<form:options items="${assetsList}"></form:options>
	  					 			</form:select>
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="control-label">Route Name</label>
					              <input type="text" id="routenameID" name="passets[0].routename" class="form-control form-white tenantRegClass1" placeholder="Enter Route Name" />
					            </div>
					          </div>
					          <div class="col-md-2">
					            <div class="form-group">
					              <label class="control-label">Sent Transtime</label> 
					              <input id="senttranstimeID" name="passets[0].senttranstime" class="form-control form-white tenantRegClass1 sentTranstDate" placeholder="Enter Sent Transtime" />
					            </div>
					          </div>
					          <div class="col-md-1">
							      <div style="float:right; clear:right;">
							      	<img style="cursor: pointer;" width="15" height="15" class="closeAssets" src="./resources/images/close1.png">
			                      </div>
					          </div>
					        </div>
					        <div class="row assestsdivView">
					        <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">ImieNumber</label>
					              <input type="text" id="imienoID" name="passets[0].imieno" class="form-control form-white tenantRegClass1 number" placeholder="Enter ImieNumber" />
					            </div>
					          </div>
					          <div class="col-md-3">
					            <div class="form-group">
					              <label class="form-label">Version Number</label>
					              <input type="text" id="versionnoID" name="passets[0].versionno" class="form-control form-white tenantRegClass1 number" placeholder="Enter Version Number" />
					            </div>
					          </div>
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="control-label">Route Map</label> 
					              <!-- <input type="text" id="routemapID" name="passets[0].routemap" class="form-control form-white tenantRegClass number" placeholder="Enter Route Map" /> -->
					              <input type="file" name="passets[0].routemap" id="routemapID" class="form-control form-white" />
					            </div>
					          </div>
					          <div class="col-sm-3">
					            <div class="form-group">
					              <label class="form-label">Captured Assets</label>
					              <!-- <input type="text" id="capturedassetsID" name="passets[0].capturedassets"  class="form-control form-white tenantRegClass number" placeholder="Enter Captured Assets" /> -->
					              <input type="file" name="passets[0].capturedassets" id="capturedassetsID" class="form-control form-white" />
					            </div>
					          </div>
					         </div>
							</div>
						</div>
					</c:when>
					<c:when test="${AssetsStatus == 'Edit'}">
						<c:forEach items="${portalassetsList}" var="assetsParam" varStatus="status">
			                  <div class="panel rIndex" style="border: 1px solid #d4d5d6;"> 
									  <div class="panel-content">
								        <div class="row">
								          <div class="col-sm-3">
								            <div class="form-group">
								              <input type="hidden" name="passets[${status.index}].routemapid" value="${assetsParam.routemapid}"/>
								              <label class="control-label">Cable Type</label>
			  					 			  <select name="passets[${status.index}].cabletypeid" id="cableTypeID" class="form-control form-white tenantRegClass1">
												<option value="">--Select--</option>
												<c:forEach var="cables" items="${cableList}">
													<c:choose>
														<c:when test="${not empty assetsParam.cabletypeid && cables.value == assetsParam.cabletypeid}">
															<option value="${cables.key}" selected>${cables.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${cables.key}">${cables.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="form-label">Asset Type</label>
								              <select name="passets[${status.index}].assettypeid" id="assetTypeID" class="form-control form-white tenantRegClass1">
												<option value="">--Select--</option>
												<c:forEach var="assetsss" items="${assetsList}">
													<c:choose>
														<c:when test="${not empty assetsParam.assettypeid && assetsss.value == assetsParam.assettypeid}">
															<option value="${assetsss.key}" selected>${assetsss.value}</option>
														</c:when>
														<c:otherwise>
															<option value="${assetsss.key}">${assetsss.value}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											  </select>
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="control-label">Route Name</label>
								              <input type="text" name="passets[${status.index}].routename" value="${assetsParam.routename}" class="form-control form-white tenantRegClass1" placeholder="Enter Route Name" />
								            </div>
								          </div>
								          <div class="col-md-2">
								            <div class="form-group">
								              <label class="control-label">Sent Transtime</label> 
								              <input type="text" name="passets[${status.index}].senttranstime" value="${assetsParam.senttranstime}" class="form-control form-white tenantRegClass1 sentTranstDate" placeholder="Enter Sent Transtime" />
								            </div>
								          </div>
								          <div class="col-md-1">
										      <div style="float:right; clear:right;">
										      	<img style="cursor: pointer;" width="15" height="15" class="closeAssets" src="./resources/images/close1.png">
						                      </div>
								          </div>
								        </div>
								        <div class="row">
								        <div class="col-sm-3">
								            <div class="form-group">
								              <label class="control-label">ImieNumber</label>
								              <input type="text" name="passets[${status.index}].imieno" value="${assetsParam.imieno}" class="form-control form-white tenantRegClass1 number" placeholder="Enter ImieNumber" />
								            </div>
								          </div>
								          <div class="col-md-3">
								            <div class="form-group">
								              <label class="form-label">Version Number</label>
								              <input type="text" name="passets[${status.index}].versionno" value="${assetsParam.versionno}" class="form-control form-white tenantRegClass1 number" placeholder="Enter Version Number" />
								            </div>
								          </div>
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="control-label">Route Map</label>
								              <span class="galleryImgs"> 
								              	<a href="#"><img width="60" src="./routeMapImage?id=${assetsParam.routemapid}"/></a>
								              </span> 
								              <input type="file" name="passets[${status.index}].routemap" id="routemapID" class="form-control form-white" />
								            </div>
								          </div>
								          <div class="col-sm-3">
								            <div class="form-group">
								              <label class="form-label">Captured Assets</label>
								              <span class="galleryImgs"> 
								              	<a href="#"><img width="60" src="./capturedAssetsImage?id=${assetsParam.routemapid}"/></a>
								              </span>
								              <input type="file" name="passets[${status.index}].capturedassets" id="capturedassetsID" class="form-control form-white" />
								            </div>
								          </div>
								        </div>
								       </div>
								  </div>
			            	</c:forEach>
					</c:when>
					</c:choose>
		            </div>
		            <div class="form-group assetsINDiv">
		            </div>
		          </div>
		        </div>
		        <!-- END ROW INNER-->
		        <!-- END ROW INNER-->
		      </div>
		    </div>
		  </div>
		</div><!-- end row -->
		<div class="row">
		  <div class="col-sm-6">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>RMSPs with associated years</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-12">
		            <div class="form-group">
		              <table id="taxTableID" class="table  table-alt">
		                <thead>
		                  <tr>
		                    <th>SNo</th>
		                    <th>Regional MSP</th>
		                    <th>Associated Years</th>
		                  </tr>
		                </thead>
		                <tbody>
		                  <tr>
		                    <td>1</td>
		                    <td><form:input path="portalRgnmsp1" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalRegMsp1AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>2</td>
		                    <td><form:input path="portalRgnMsp2" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalRegMsp2AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>3</td>
		                    <td><form:input path="portalRgnMsp3" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalRegMsp3AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>4</td>
		                    <td><form:input path="portalRgnMsp4" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalRegMsp4AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>5</td>
		                    <td><form:input path="portalRgnMsp5" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalRegMsp5Assocyrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>6</td>
		                    <td><form:input path="portalRgnMsp6" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalRegMsp6Assocyrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                </tbody>
		              </table>
		            </div>
		          </div>
		        </div>
		        <!-- END ROW INNER-->
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>MSPs with associated years</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-12">
		            <div class="form-group">
		              <table id="taxTableID" class="table  table-alt">
		                <thead>
		                  <tr>
		                    <th>SNo</th>
		                    <th>Local MSP</th>
		                    <th>Associated Years</th>
		                  </tr>
		                </thead>
		                <tbody>
		                  <tr>
		                    <td>1</td>
		                    <td><form:input path="portalLocmsp1" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalLocMsp1AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>2</td>
		                    <td><form:input path="portalLocMsp2" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalLocMsp2AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>3</td>
		                    <td><form:input path="portalLocMsp3" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalLocMsp3AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>4</td>
		                    <td><form:input path="portalLocMsp4" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalLocMsp4AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>5</td>
		                    <td><form:input path="portalLocMsp5" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalLocMsp5AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>6</td>
		                    <td><form:input path="portalLocMsp6" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalLocMsp6AssocYrs" cssClass="localTextCss number" /></td>
		                  </tr>
		                </tbody>
		              </table>
		            </div>
		          </div>
		        </div>
		        <!-- END ROW INNER-->
		      </div>
		    </div>
		  </div>
		 </div><!-- end row -->
		<div class="row">
		  <div class="col-sm-12">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>Associated Sub-Stations</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-12">
		            <div class="form-group">
		              <table id="taxTableID" class="table  table-alt">
		                <thead>
		                  <tr>
		                    <th>SNo</th>
		                    <th>Sub Station Id</th>
		                    <th>Sub Station Distance</th>
		                  </tr>
		                </thead>
		                <tbody>
		                  <tr>
		                    <td>1</td>
		                    <td><form:input path="portalSubstn1Id" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalSubstn1Distance" cssClass="localTextCss number" /></td>
		                  </tr>
		                  <tr>
		                    <td>2</td>
		                    <td><form:input path="portalSubstn2Id" cssClass="localTextCss" /></td>
		                    <td><form:input path="portalSubstn2Distance" cssClass="localTextCss number" /></td>
		                  </tr>
		                </tbody>
		              </table>
		            </div>
		          </div>
		          <!-- END ROW INNER-->
		          <!-- END ROW INNER-->
		        </div>
		      </div>
		    </div>
		  </div>
		</div><!-- end row -->
		<div class="row mspClass">
		  <div class="col-sm-12">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>MSP specific fields</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Mso Name</label>
		              <form:input path="portalMsoName" id="portalMsoName" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Mso Name" maxlength="255"/>
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">DasLicence Provider</label> 
		              <form:input path="portalDasLicenceProvider" id="portalDasLicenceProvider" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal DasLicence Provider" maxlength="255"/>
		            </div>
		          </div>
		          <!-- END ROW INNER-->
		          <!-- END ROW INNER-->
		        </div>
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Digital Connections Count</label>
		              <form:input path="portalDgtConnCnt" id="portalDgtConnCnt" cssClass="form-control form-white number mspSpecific" placeholder="Enter Portal DigitalConn Cnt"
		                />
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Analog Connections Count</label>
		              <form:input path="portalAnlConnCnt" id="portalAnlConnCnt" cssClass="form-control form-white number mspSpecific" placeholder="Enter Portal AnalogConn Cnt" 
		                />
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="form-group">
		              <label class="form-label">Das License</label> 
		              <form:input path="portalDasLicense" id="portalDasLicense" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Das License"
		                />
		            </div>
		          </div>
		        </div>
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Das Licence Type</label>
		              <form:input path="portalDasLicenceType" id="portalDasLicenceType" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Das Licence Type"
		                />
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Das License Holder</label>
		              <form:input path="portalDasLicenseHolder" id="portalDasLicenseHolder" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Das License Holder" maxlength="255" />
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="form-group">
		              <label class="control-label">Das License ExpDate</label> 
		              <form:input path="portalDasLicenseExpDate" id="portalDasLicenseExpDate" cssClass="form-control form-white mspSpecific" placeholder="Select Portal Das License ExpDate"
		                />
		            </div>
		          </div>
		        </div>
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">No Of Pay Channels</label>
		              <form:input path="portalPaychnlCnt" id="portalPaychnlCnt" cssClass="form-control form-white number mspSpecific" placeholder="Enter Portal Paychnl Cnt"
		                />
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Type Of Company</label>
		              <form:input path="portalCompanyType" id="portalCompanyType" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Company Type"
		                />
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="form-group">
		              <label class="form-label">Partner Name</label>
		              <form:input path="portalPartnerName" id="portalPartnerName" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Partner Name"
		                />
		            </div>
		          </div>
		        </div>
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">No Of House Holds</label> 
		              <form:input path="portalHouseHoldCnt" id="portalHouseHoldCnt" cssClass="form-control form-white number mspSpecific" placeholder="Enter Portal House Hold Cnt"
		                />
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">MIB License Number</label>
		              <form:input path="portalMibLicenseNo" id="portalMibLicenseNo" cssClass="form-control form-white mspSpecific" placeholder="Enter Portal Mib License No"
		                />
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="form-group">
		              <label class="control-label">MIB License Expiry Date</label> 
		              <form:input path="portalMibLicenseExpDate" id="portalMibLicenseExpDate" cssClass="form-control form-white mspSpecific" placeholder="Select Portal Mib License ExpDate"
		                />
		            </div>
		          </div>
		        </div>
		        <!-- END ROW INNER-->
		        <!-- END ROW INNER-->
		      </div>
		    </div>
		  </div>
		</div><!-- end row -->
				<div class="row">
		  <div class="col-sm-6">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>ID Proof Details</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">ID Proof Number</label> 
		              <form:input path="docUniqueId1" id="docUniqueId1" cssClass="form-control form-white specialCharacters tenantRegClass1 idProofClass"  maxlength="20" placeholder="Enter ID Proof Number"/>
		            </div>
		          </div>
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Document</label>
		              <div class="option-group">
		             	 <form:select  path="doclov1" id="doclov1" cssClass="form-control form-white tenantRegClass1 idProofClass">
    				 		<form:option value="">-Select-</form:option>
    						<form:options items="${poiDocList}" itemValue="lovId" itemLabel="lovValue"></form:options>
  					 	 </form:select>
		              </div>
		            </div>
		          </div>
		        </div>
		       <%--  <div class="row">
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Valid From</label> 
		              <form:input path="effectiveFrom1" id="effectiveFrom1" cssClass="form-control form-white date tenantRegClass1"  placeholder="Select a date..."/>
		            </div>
		          </div>
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Valid To</label> 
		              <form:input path="effectiveTO1" id="effectiveTO1" cssClass="form-control form-white date tenantRegClass1"  placeholder="Select a date..."/>
		            </div>
		          </div>
		        </div> --%>
		        <div class="row">
		        	<div class="col-md-6">
		             <div class="form-group">
		                <label class="form-label">Upload ID Proof</label>
		                <input type="file" name="idProof" id="idProof" class="form-control form-white" />
		             </div>
		          	</div>
		          	<div class="col-md-6 filediv">
		             <div class="form-group">
		              <label class="form-label">&nbsp;</label><br>
		        		<span class="galleryImgs"> 
		        			<a href="#"><img width="60" src="downloadFiles?path=${IdDoc}"/></a>
						</span>
					 </div>
					</div>
		        </div>
		      </div>
		    </div>
		  </div>
		<!-- </div> --><!-- end row -->
		<!-- <div class="row"> -->
		  <div class="col-sm-6">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>Address Proof Details</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Address Proof Number</label> 
		              <form:input path="docUniqueId2" id="docUniqueId2" cssClass="form-control form-white specialCharacters tenantRegClass1 addrProofClass"  maxlength="20" placeholder="Enter Address Proof Number"/>
		            </div>
		          </div>
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Document</label>
		              <div class="option-group">
		              	<form:select  path="doclov2" id="doclov2" cssClass="form-control form-white tenantRegClass1 addrProofClass">
    				 		<form:option value="">-Select-</form:option>
    						<form:options items="${poaDocList}" itemValue="lovId" itemLabel="lovValue"></form:options>
  					 	 </form:select>
		              </div>
		            </div>
		          </div>
		        </div>
		        <%-- <div class="row">
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Valid From</label> 
		              <form:input path="effectiveFrom2" id="effectiveFrom2" cssClass="form-control form-white date tenantRegClass1"  placeholder="Select a date..."/>
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Valid To</label>
		              <form:input path="effectiveTO2" id="effectiveTO2" cssClass="form-control form-white date tenantRegClass1"  placeholder="Select a date..."/>
		            </div>
		          </div>
		        </div> --%>
		        <div class="row">
		         	<div class="col-md-6">
		             <div class="form-group">
		                <label class="form-label">Upload Address Proof</label>
		                <input type="file" name="addressProof" id="addressProof" class="form-control form-white" />
		             </div>
		          	</div>
		          	<div class="col-md-6 filediv">
		             <div class="form-group">
		             	<label class="form-label">&nbsp;</label><br>
		        		<span class="galleryImgs"> 
		        			<a href="#"><img width="60" src="downloadFiles?path=${addressDoc}"/></a>
						</span>
					 </div>
					</div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div class="row">
		  <div class="col-sm-6">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>License Details</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">License Number</label> 
		              <form:input path="licenserefno" id="licenserefno" maxlength="20" class="form-control form-white specialCharacters tenantRegClass1" placeholder="Enter License Number"/>
		            </div>
		          </div>
		          <div class="col-md-6">
						<div class="form-group">
						<label class="form-label">Region</label>
						<div class="option-group">
						 <form:select  path="region" id="regionID" cssClass="form-control form-white tenantRegClass1">
    				 		<form:option value="">-Select-</form:option>
    						<form:options items="${regionList}" itemValue="regionName" itemLabel="regionName"></form:options>
  					 	 </form:select>
						</div>
						</div>
				  </div>
				  </div>
				  <div class="row">
		          <div class="col-md-6">
		            <div class="form-group">
		                <label class="form-label">Upload License</label>
		                <input type="file" name="licenceId" id="licenceId" class="form-control form-white" />
		            </div>
		          </div>
		          <div class="col-md-6 filediv">
		             <div class="form-group">
		             	<label class="form-label">&nbsp;</label><br>
		        		<span class="galleryImgs"> 
		        			<a href="#"><img width="60" src="downloadFiles?path=${licenseDoc}"/></a>
						</span>
					</div>
				  </div>
		        </div>
		        <%-- <div class="row">
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Valid From</label> 
		              <form:input path="effectiveFrom" id="effectiveFrom" cssClass="form-control form-white date tenantRegClass1"  placeholder="Select a date..."/>
		            </div>
		          </div>
		          <div class="col-sm-6">
		            <div class="form-group">
		              <label class="control-label">Valid To</label> 
		              <form:input path="effectiveTO" id="effectiveTO" cssClass="form-control form-white date tenantRegClass1" placeholder="Select a date..."/>
		            </div>
		          </div>
		        </div> --%>
		      </div>
		    </div>
		  </div>
		  <div class="col-sm-6">
		    <div class="panel">
		      <div class="panel-header bg-light">
		        <h3>Bank Details</h3>
		      </div>
		      <div class="panel-content">
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">A/C Number</label> 
		              <form:input path="accountNo" id="accountNo" cssClass="form-control form-white number tenantRegClass1 bankClass"  maxlength="33" placeholder="Enter Account Number"/>
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">IFSC Code</label>
		              <form:input path="ifscCode" id="ifscCode" cssClass="form-control form-white specialCharacters tenantRegClass1 bankClass"  maxlength="11" placeholder="Enter IFSC Code"/>
		            </div>
		          </div>
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Account Type</label>
		              <%-- <form:input path="acctTypelov" id="acctTypelov" cssClass="form-control form-white lettersOnly tenantRegClass"  maxlength="30" placeholder="Enter Account Type"/> --%>
		              <form:select path="acctTypelov" id="acctTypelov" cssClass="form-control form-white tenantRegClass1 bankClass">
		              	<form:option value="">-Select-</form:option>
		              	<form:option value="Savings">Savings</form:option>
		              	<form:option value="Current">Current</form:option>
		              </form:select>
		            </div>
		          </div>
		        </div>
		        <div class="row">
		          <div class="col-sm-4">
		            <div class="form-group">
		              <label class="control-label">Branch Name</label>
		              <form:input path="branchName" id="branchName" cssClass="form-control form-white lettersOnly tenantRegClass1 bankClass"  maxlength="30" placeholder="Enter Branch Name"/>
		            </div>
		          </div>
		          <div class="col-md-4">
		            <div class="form-group">
		              <label class="form-label">Bank Name</label>
		              <form:input path="bankNamelov" id="bankNamelov" cssClass="form-control form-white lettersOnly tenantRegClass1 bankClass"  maxlength="50" placeholder="Enter Bank Name"/>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div><!-- end row -->

		<div id="areasHidden_div"></div>
		<div id="assestsHidden_div"></div>
            		<button class="btn btn-embossed btn-success submitdiv" id="id_lmosubmit" type="button"
								value="Save">Submit</button>
					<a href="./"><button class="btn btn-embossed btn-danger" type="button">Cancel</button></a>
            </div><!-- END MAIN PANEL CONTENT --> 
          </div><!-- END MAIN PANEL --> 
        </div><!-- HERE COMES YOUR CONTENT --> 
      </div><!-- END MAIN ROW -->
      <div class="clear"></div>
    </div><!-- END PAGE CONTENT -->   
  </div><!-- END MAIN CONTENT -->
</section>
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
</form:form>
<!-- <script src="./resources/js/jquery-1.11.1.min.js"></script> 
<script src="./resources/js/lmo.js" type="text/javascript"></script> -->
</body>
<style>
.localTextCss {
  border:none;
  width:100%;
  height:100%;
  padding: 0 4px 0 4px;
}

.toggle{
 display:inline-block;
 height:20px;
 width:20px;
 background:url("http://icons.iconarchive.com/icons/pixelmixer/basic/48/plus-icon.png");
 background-size: 20px 20px;
 background-repeat: no-repeat;
 cursor: pointer;
}
.toggle.expanded{
  background:url("http://cdn2.iconfinder.com/data/icons/onebit/PNG/onebit_32.png");
  background-size: 20px 20px;
  background-repeat: no-repeat;
  cursor: pointer;
}
</style>
<script type="text/javascript" src="./resources/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-sliderAccess.js"></script>
</html>