
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>        
<style>
.ui_tpicker_time_input{
	width: 100%  !important;
}

@media (max-width: 767px) {
    .glyphicon-one-fine-red-dot:before {
        margin-top:0;
    }
}
.nopadding {
   padding: 0 !important;
   margin: 0 !important;
}

span[class*="glyphicon-one-fine"] {
    margin-bottom: -.8em;
    overflow: hidden;
}
.glyphicon-one-fine-red-dot:before {
    content:"\25cf";
    font-size: 2.8em;
    color: red;
    padding-top: 50px;
    padding: 0 !important;
    margin-top: 40px;
}
.glyphicon-one-fine-green-dot:before {
    content:"\25cf";
    font-size: 2.8em;
    color: green;
}
.glyphicon-one-fine-full-dot:before {
    content:"\25cf";
    font-size: 3em;
}

</style> 

<div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Trouble Ticket</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./home.do">Home</a> </li>
            <li class="active"><c:if test="${flag!='updateTTFLAG'}">Create</c:if> <c:if test="${flag=='updateTTFLAG'}">Update</c:if> TT</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<%-- <form role="form" class="form-validation"> --%>
			<form:form action="#" id="createTTicketFormID" method="post" commandName="troubleTicketDTO" enctype="multipart/form-data">
			<input type="hidden" id="parTTNOID"/>
			<input type="hidden" name="ticketFor" id="ticketForHiddenID"/>
			<input type="hidden" name="ticketForr" id="statusttID"/>
			<input type="hidden"  id="domainID"/>
			<input type="hidden" name="dynAttFlag" id="dynAttFlagID"/>
			<input type="hidden" name="createdOn" id="createdOnID"/>
			<input type="hidden" name="createdBy" id="createdByID"/>
			<input type="hidden" name="expcloseDate" id="expcloseDateID"/>
			<input type="hidden" name="issue" id="issueNameID"/>
			<input type="hidden" id="loginTenantCodeID"/> 
			<input type="hidden" name="image" id="image"/>
			<input type="hidden"  name="imagePath" value="${imgPath}"/>
			<input type="hidden"  name="ipAddr" value="${ipAddr}" class="ipAddrClass"/>
			<input type="hidden"  name="agoraSerCode" value="${agoraSerCode}" class="agoraSerCodeClass"/>
			<input type="hidden" name="flag" id="flagID"/>
			<input type="hidden" name="ticketNo" id="ticketNOID"/>
			
			<input type="hidden" name="status" id="statusIDD"/>
			
			<input type="hidden" name="usageFlag" id="usageFlag"/>
			<input type="hidden" name="NocParent" id="NocParent"/>
			<input type="hidden" name="notApplicable" id="notApplicable"/>
			<input type="hidden" name="usageCode" id="usageCode"/>
			<input type="hidden" name="noUsageData" id="noUsageData"/>
			<input type="hidden" name="currentStatus" id="currentStatusID"/>
			<input type="hidden" name="oldIssue"  value="${issueCodeVal}"/>
			
			
			 
              <!-- END ROW -->
             <div id="id_container" class="error"   style="color:red;float:center"><ol id="id_ol"></ol></div>
			 <div id="id_container1" class="success"   style="color:green;float:center;"><ol id="id_ol"></ol></div>
              <!-- END ROW -->
             
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                   <div class="panel-header bg-light">
                    <h3><c:if test="${flag!='updateTTFLAG'}">Create </c:if><c:if test="${flag=='updateTTFLAG'}">Update </c:if> TT for <span id="ticketForSpanID"></span><c:if test="${flag=='updateTTFLAG'}">(Ticket No:${ticketNo})</c:if></h3>
                    </div>
                    <div class="panel-content" align="center">
                    <div class="row">
					  
                        
                        <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Name :</label> 
							<label class="control-label"><b>${custName}</b></label>  														   
							<input type="hidden" name="custName" id="custNameID" style="width: 200px;background-color:#ECEDEE;" maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Mobile Number :</label> 
							<label class="control-label"><b>${contactMobile}</b></label>  														   
							<input type="hidden" name="contactMobile" id="contactMobileID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Landline Number :</label> 
							<label class="control-label"><b>${contactLandline}</b></label>														   
							<input type="hidden" name="contactLandline" id="contactLandlineID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						</div>  
					<div class="row customerDIVClass" style="dispaly:none;">
					  <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Account No :</label> 
						    <label class="control-label"><b>${cafNo}</b></label>														   
							<input type="hidden" name="cafNo" id="cafNOID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
                        
                        <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label" id="customerIDD">Customer ID :</label>
							<label class="control-label"><b>${customerID}</b></label> 														   
							<input type="hidden" name="customerID" id="customerIDD" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
							<input type="hidden" name="custID" id="custIDD"></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">LMO Code :</label> 
							<label class="control-label"><b>${tenantCode}</b></label>														   
							<input type="hidden" name="tenantcode" id="tenantCodeIDD" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label" id="tenantMobileID">LMO Mobile No. :</label>
							<label class="control-label"><b>${tenantMobile}</b></label> 														   
							<input type="hidden" name="tenantMobile" id="tenantMobileID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						</div>
					 <div class="row customerDIVClass" style="dispaly:none; margin-top: -12px;">
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">PoP Name :</label> 	
						<label class="control-label"><b>${subStationName}</b></label>													   
							<input type="hidden" name="subStationName" id="substnNoID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						
						
						<div class="col-sm-3" >
						<div class="form-group" style="margin-top: -19px;">													
						<label class="control-label">OLT Status :</label> 			
						<label class="control-label"><c:if test="${oltStatusFlag!='Y'}"><%-- <canvas id="myCanvas" width="50" height="16"></canvas>  --%><span class="glyphicon glyphicon-one-fine-green-dot nopadding" style="height:35px;padding-top:0;"></span>  Working</c:if><c:if test="${oltStatusFlag!='N'}"><span class="glyphicon glyphicon-one-fine-red-dot nopadding" style="height:35px;padding-top:0;"></span>Not working</c:if></label>											   
							<input type="hidden" name="ipAddr" id="ipAddrID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<!-- <label class="control-label">ONT Status : </label>  -->			
						<label class="control-label"><c:if test="${oltStatusFlag!='Y'}"><canvas id="myCanvas" width="0" height="16"></canvas>ONT Status : <a href="#" class="oltStatusClass" data-toggle="modal" data-target="#myModal">ONT Status</a></c:if><c:if test="${oltStatusFlag!='N'}"><a href="#" class="apsflnocClass" val="${districtid}" id="apsflnocID" data-toggle="modal" data-target="#myModalTT">Generate APSFL Noc Ticket</a><span id="isParent"><input type="checkbox" name="parentNOC" id="parentTTNoc" class="parentTTNoc" value="parentTT"/> Is Parent</span></c:if></label>											   
							<input type="hidden" name="ipAddr" id="ipAddrID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3" >
						<div class="form-group">													
						<label class="control-label">ONU Reboot : <a href="#" class="onuRebootClass" val="${popOltIpaddress}" val1="${oltCardNo}" val2="${oltPort}" val3="${oltONUID}" >ONU Reboot </a></label> 			
						<%-- <div class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><input type="hidden" class="ticketDesClass" value="${ticketDesc}"><input type="hidden" class="createdByClass" value="${createdBy}"><a href="#" class="cafUsageClass" val="${cafNo}" val1="${districtid}" data-toggle="modal" data-target="#myModal13">Caf Usage</a></div>' --%>
						</div>
						</div>
						
						
						
						
						</div>
                       <div class="row customerDIVClass" style="dispaly:none;">
                       
                       <div class="col-sm-3" >
						<div class="form-group">													
						<label class="control-label">HSI Usage : <a href="#" class="cafUsageClass" val="${cafNo}" val1="${districtid}" data-toggle="modal" data-target="#myModal13">HSI Usage</a></label> 			
						<%-- <div class="unknwnColumns"><input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><input type="hidden" class="ticketDesClass" value="${ticketDesc}"><input type="hidden" class="createdByClass" value="${createdBy}"><a href="#" class="cafUsageClass" val="${cafNo}" val1="${districtid}" data-toggle="modal" data-target="#myModal13">Caf Usage</a></div>' --%>
						</div>
						</div>
                       
                       
                       <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">OLT Serial No :</label> 			
						<label class="control-label"><b>${oltSerialNo}</b></label>											   
							<input type="hidden" name="oltSerialNo" id="oltSerialNoID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
                       
                        <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">OLT Port No :</label> 			
						<label class="control-label"><b>${port}</b></label>											   
							<input type="hidden" name="port" id="portIDD" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">ONU ID :</label> 
							<label class="control-label"><b>${oltONUID}</b></label>														   
							<input type="hidden" name="oltONUID" id="oltONUID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<%-- <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Aadhar No :</label> 	
							<label class="control-label"><b>${aadharNo}</b></label>													   
							<input type="hidden" name="aadharNo" id="aadharNoID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div> --%>
						
						
						</div>
					 <div class="row customerDIVClass" style="dispaly:none;">
					 
					 <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">APSFL Track ID / Aadhar No :</label> 
							<label class="control-label"><b>${apsflTrackID}</b></label>														   
							<input type="hidden" name="apsflTrackID" id="apsflTrackID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
					 
					 <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">ONU Model :</label> 	
							<label class="control-label"><b>${onuModel}</b></label>													   
							<input type="hidden" name="onuModel" id="onuModelID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
					 
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">ONU Serial No :</label>
							<label class="control-label"><b>${onuSerialNo}</b></label> 														   
							<input type="hidden" name="onuSerialNo" id="onuSerialNoID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
							<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">ONU MAC Address :</label> 	
						<label class="control-label"><b>${onuMACAddr}</b></label>													   
							<input type="hidden" name="onuMACAddr" id="onuMACAddrID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
                        
                       <!--  <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">ONU Register No</label> 														   
							<input type="text" name="onuRegNo" id="onuRegNoID" style="width: 200px;background-color:#ECEDEE; " maxlength="50" class="form-control form-white" readOnly></input>
						<input type="hidden" name="custID" id="custIDD"></input>
						</div>
						</div> -->
						</div>
						<c:if test="${not empty cafDetailsVO}">
						<div class="row customerDIVClass" style="dispaly:none;">
                       <div class="col-sm-12" >
							<table  class="table table-alt data_table  datatable full-width" id="packageDetailsID">
							 <thead>
			 					<tr> 
			 						<th>Product Name</th>
			 						<th>Product Type</th>
								 	<th>Product Charge</th>
								 	<th>Product Tax</th>
								 	<th>Service Name</th>
								 	<th>Created On</th>
								 	<th>STB Account No</th>
								</tr>
			 				</thead><!-- iterate over the collection using forEach loop -->
			 					<tbody>
									<c:forEach var="user" items="${cafDetailsVO}">
									<tr>
									<td>${user.prodname}</td>
									<td>${user.prodtype}</td>
									<td>${user.prodcharge}</td>
									<td>${user.prodtax}</td>
									<td>${user.srvcname}</td>
						 			<td>${user.createdon}</td>
									<td>${user.stbcafno}</td>
									</tr>
							        </c:forEach>
					     		</tbody>
						  </table>
					</div> 
				</div>
				</c:if>
				<c:if test="${not empty iptDTO}">
				 <div class="row customerDIVClass" style="dispaly:none;">
				 <div class="col-sm-12" >
				<table  class="table table-alt data_table  datatable full-width" id="iptvID">
				<thead>
				<tr> 
				<th>IPTV Box Model</th>
				<th>IPTV Box Serial No</th>
				<th>IPTV Box MAC Address</th>
				<th>IPTV Subscriber Code</th>
				</tr>
				</thead><!-- iterate over the collection using forEach loop -->
				<tbody>
				<c:forEach var="iptv" items="${iptDTO}">
				<tr>
				<td>${iptv.cpe_model}</td>
				<td>${iptv.stbslno}</td>
				<td>${iptv.stbmacaddr}</td>
				<td>${iptv.nwsubscode}</td>
				</tr>
				       </c:forEach>
				    </tbody>
				 </table>
				</div> 
				</div> 
				</c:if>
                  <div class="row">
					  <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Email :</label> 
							<label class="control-label"><b>${contactemail}</b></label>														   
							<input type="hidden" name="contactemail" id="contactemailID" style="width: 200px; background-color:#ECEDEE;" maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Address :</label> 	
							<label class="control-label"><b>${contactAddr}</b></label>														   
							<input type="hidden" name="contactAddr" id="contactAddrID" style="width: 200px; background-color:#ECEDEE;" maxlength="50" class="form-control form-white" readOnly></input>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group updateDIVClass">
						<label for="control-label">TT History :</label>
						<input type="hidden" class="ticketNoClass" value="'+obj.ticketNo+'"><input type="hidden" class="ticketDesClass" value="${ticketDesc}"><input type="hidden" class="createdByClass" value="${createdBy}"><a href="#" class="ttHistoryClass" val="${ticketNo}" val1="${createdBy}" val2="${ticketDesc}" data-toggle="modal" data-target="#myModal12">TT History</a>
						</div>
						</div>
						
						
						
						<%--  <div class="col-sm-4 ttCategoryClass">
						<div class="form-group">													
						<label class="control-label">TT Category<span style="color: red;">*</span></label> 														   
							<form:select path="tickCategory" id="ttCategoryID" style="width: 200px; " class="form-control form-white select">
								<form:option selected="true" value="Individual" label="Individual" />
								<form:option value="Group" label="Group" />
								<form:options items="${ticketCat}"/>
							</form:select>
						</div>
						</div> --%>
                        
                       
						</div>
						
						<%-- 
						<div class="row ticketCatDIVClass" style="display:none;">
						<div class="col-sm-4 distClass" >
						<div class="form-group">													
						<label class="control-label">District<span style="color: red;">*</span></label>&nbsp;
							<form:select path="districts" id="districtID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select District--" />
								<form:option value="0" label="All" />
								<form:options items="${districtsMap}" />
							</form:select> 
						</div>
						</div>
						
					  <div class="col-sm-4 popClass">
						<div class="form-group">													
						<label class="control-label">POP</label>													   
							<form:select path="pops" id="popID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select POP--" />
								<form:option value="All" label="All" />
								<form:options items="${statusMap}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">OLT</label>														   
							<form:select path="olts" id="oltID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select OLT--" />
								<form:option value="All" label="All" />
							    <form:options items="${assignedToMap}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">Port</label> 														   
							<form:select path="ports" id="portID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Port--" />
								<form:option value="All" label="All" />
							    <form:options items="${assignedToMap}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">Split L1</label>														   
							<form:select path="splitL1" id="splitL1ID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Split L1--" />
								<form:option value="All" label="All" />
							    <form:options items="${assignedToMap}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-4">
						<div class="form-group">													
						<label class="control-label">Split L2</label>														   
							<form:select path="splitL2" id="splitL2ID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Split L2--" />
								<form:option value="All" label="All" />
							    <form:options items="${assignedToMap}" />
							</form:select>
						</div>
						</div>
						
						 <div class="col-sm-4 smsClass">
						<div class="form-group">													
						<label class="control-label">SMS<span style="color: red;">*</span></label> 														   
						<textarea name="groupTTMsg" id="groupTTMsgID" style="width: 230px; height:80px;" class="form-control form-white"></textarea>
						</div>
						</div>
						</div> --%>
						
						<div class="row">
						
						 <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">TT Type<span style="color: red;">*</span></label> 														   
							<form:select path="ticketType" id="ttTypeID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select TT Type--" />
								<form:options items="${ticketType}"/>
							</form:select>
						</div>
						</div>
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Issue Type<span style="color: red;">*</span></label> 														   
							<form:select path="issueType" id="issueTypeID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Issue Type--" />
								<form:options items="${issueType}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Issue<span style="color: red;">*</span></label> 														   
							<form:select path="issueCode" id="issueID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Issue--" />
								<form:options items="${issueMapVal}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-3 distDIVClass">
						<div class="form-group">													
						<label class="control-label">District<span style="color: red;">*</span></label>&nbsp;
							<form:select path="districts" id="distID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select District--" />
								<form:options items="${districtsMap}" />
							</form:select> 
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Complaint in Brief<span style="color: red;">*</span></label> 														   
							
							<textarea name="ticketDesc" id="ticketDescID" style="width: 230px; height:80px;" class="form-control form-white"></textarea>
						</div>
						</div>
					
						</div>
						<div class="row dynamicIssueAttributesDIV">
					  
						</div>
						
						<div class="row updateDIVClassforDist" style="display:none;">
						<%-- <c:if test="${not empty attrValMap}">
							<c:forEach items="${attrValMap}" var="type" varStatus="rowNum">
							<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">${type.key}</label>
								<label class="control-label"><b>${type.value}</b></label>  	
							</div>
							</div>
							</c:forEach>
						</c:if> --%>
						</div>
						
						<div class="row updateDIVClass" style="display:none;">
						  <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Current Status</label>&nbsp;
						<label class="control-label"><b>${statusName}</b></label> 
						</div>
						</div>
						
					  <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Status</label>													   
							<form:select path="dummyVar" id="statusID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Please Select--" />
								<form:options items="${statusMap}" />
							</form:select>
						</div>
						</div>
						
						<div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label">Assigned To</label><span style="color: red;">*</span> 														   
							<form:select path="assignedTo" id="assignedToID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Please Select--" />
							    <form:options items="${assignedToMap}" />
							</form:select>
						</div>
						</div>
						</div>
                        <div class="row updateDIVClass closureDIVClass" style="display:none;">
                         <div class="col-sm-3 parentTicketNoClass">
						<div class="form-group">													
						<label class="control-label">Parent Ticket No.<span style="color: red;">*</span></label>
							<input type="text" name="parentticketno" id="parentticketnoID" style="width: 200px;" class="form-control form-white"></input> 														   
						</div>
						</div>
                        <div class="col-sm-3">
						<div class="form-group">													
						<label class="control-label statusbasedremks">Remarks<span style="color: red;">*</span></label> 														   
						<label class="control-label statusbaseCrectiveAct">Corrective Action<span style="color: red;">*</span></label>
							<textarea name="ticketRemark" id="ticketRemarkID" style="width: 230px; height:80px;" class="form-control form-white"></textarea>
						</div>
						</div>
						<div class="col-sm-3 reasonClass" style="display:none;">
						<div class="form-group">
						<label class="control-label">Reason<span style="color: red;">*</span></label>
						<textarea name="ticketReason" id="ticketReasonID" style="width: 230px; height:80px;" class="form-control form-white ticketReasonClass" maxlength="250"></textarea>
						</div>
						</div>
						
						<div class="col-sm-3 ttCloserDateClass" style="display:none;">
						<div class="form-group">
						<label class="control-label">Closure Date<span style="color: red;">*</span></label>
						<input id="modifiedOnID" name="modifiedOnString" class="form-control form-white" style="width: 200px;" placeholder="Select Closure Date" />
						</div>
						</div>
						
						<div class="col-sm-3 ttCloserDateClass" style="display:none;">
						<div class="form-group">
						<label class="control-label">Issue Category<span style="color: red;">*</span></label>
						<form:select path="actualIssue" id="actualIssueID" style="width: 200px; " class="form-control form-white select">
								<form:option value="-1" label="--Select Issue Category--" />
								<form:options items="${actualTicketMap}"/>
							</form:select>
						</div>
						</div>
						
						</div>  
						  <div class="row">
						<div class="col-sm-3">
						                  <div class="form-group">
						<div class="element">
						<label for="image">Upload file</label>
						<input type="file" name="images" id="upload_file1" class="upload_file1" multiple="multiple"/>
						</div>
						<div id="moreImageUpload"></div>
						<div class="clear"></div>
						<div id="moreImageUploadLink" style="display:none;margin-left: 10px;">
						<a href="javascript:void(0);" id="attachMore">Attach another file</a>
						</div>
						<ol>
						<c:forEach items="${troubleTickeimages}" var="image">
						<li><c:out value="${image}"/>
						<%-- ${image.originalFilename}<img width="100"
						src="<c:url value="/image/"/>${image.originalFilename}" /> --%>
						</li>
						</c:forEach>
						</ol>
						<c:if test="${dwFlag=='Y'}">
						<a href="#" class="imagepathh">Download Files</a>
						</c:if>
						</div>
						</div>
						<div class="col-sm-6 customerFeedback" style="display:none;">
							<div class="form-group">
							<label class="control-label">Feedback<span style="color: red;">*</span></label>
								<c:forEach var="feedback" items="${feedbackMap}" varStatus="row">
 								<input type="radio" id="feedbackID" name="feedback" value="${feedback.value}"/>${feedback.value}
								</c:forEach>
							</div>
						</div>
						</div> 
						
						 </div> 
					       <div class="row">
					       <div class="col-sm-7" style="float: left;">
                   			<div class="form-group">
                      		<label class="control-label"></label>
                      		<c:if test="${flag!='updateTTFLAG'}">
                      		<input type="submit" id="saveID" value="Create TT" class="btn btn-embossed btn-primary" />
                      		</c:if> 
                      		<c:if test="${flag=='updateTTFLAG'}">
                      		<input type="submit" id="saveID" value="Update TT" class="btn btn-embossed btn-primary" />
                      		</c:if>
            				<input type="submit" id="cancelID" value="Cancel" class="btn btn-outline btn-danger"/>
                    		</div>
                        </div>    
                        </div>  
                        <div id="myModal" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size" >
													<div class="modal-header" style="  margin-top: -30px; padding-top: 20px;  border-top-width: -10px; padding-bottom: 0px;	" >
														<h3 class="modal-title"> ONT Status
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em; margin-top: 0px;height: 23px;padding-top: 0px" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body" style="padding-bottom: 4px;">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" > 
																<!-- <div class="ScrollStyle" style="width: 962px; overflow-x: auto;"> -->
																	<!-- <table class="table table-alt" id="jsonErrListID"> -->
																	<table  class="table table-alt data_table  datatable dt-responsive nowrap" id="jsonErrListID" style="width: 100%;">
																		
																	</table>
																<!-- </div> -->
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
										</div>
							
							<div id="myModal12" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size" style="width: 1190px; left: -280px">
													<div class="modal-header">
														<h3 class="modal-title"> TT Status
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em;" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" style="width: 1160px; overflow-x: auto;">
																<div class="ScrollStyle" style="width: 1162px; overflow-x: auto;">
																	<table class="table table-alt" id="jsonErrListID12">
																		
																	</table>
																</div>
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
							</div>
							
							<div id="myModal13" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size" style="width: 790px; left: -55px">
													<div class="modal-header" style="  margin-top: -30px; padding-top: 20px;  border-top-width: -10px; padding-bottom: 0px;	" >
														<h3 class="modal-title"> HSI Usage Details (for Current Month)
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em; margin-top: 0px;height: 23px;padding-top: 0px" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body" style="padding-bottom: 4px;" >
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" style="width: 760px; overflow-x: auto; ">
																<!-- <div class="ScrollStyle" style="width: 1162px; overflow-x: auto;"> -->
																	<table class="table table-alt" id="jsonErrListID13">
																		
																	</table>
																<!-- </div> -->
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
							</div>
							
							<div id="myModal8" class="modal fade" role="dialog">
											<div class="modal-dialog ">
												<!-- Modal content-->
												<div class="modal-content model-div-size" style="width: 1190px; left: -280px">
													<div class="modal-header">
														<h3 class="modal-title"> ONT Reboot
															<button type="button" class="btn btn-default" id="popUpCloseButtonId" style="float: right; clear: right; margin-top: -0.5em;" data-dismiss="modal">Close</button>
														</h3>
													</div>
													<div class="modal-body">
														<!-- END ROW INNER-->
														<div class="row m-b-12">
															<div class="col-sm-12">
															<div class="panel panel-default" style="width: 1160px; overflow-x: auto;">
																<!-- <div class="ScrollStyle" style="width: 1162px; overflow-x: auto;"> -->
																	<table class="table table-alt" id="jsonErrListID8">
																		
																	</table>
																<!-- </div> -->
																</div>
															</div>
														</div>
														<!-- END ROW INNER-->
													</div>
												</div>
											</div>
							</div>
                        
                        
                        
                           		  
                      </div>
                      </div>
                    </div>
                    </div>
                     </form:form>
                  </div>
                 
                </div>
              </div>
              <!-- END ROW -->   
              
			  
			  <!-- END FORM -->
            </div>
            <!-- END MAIN PANEL CONTENT --> 
          </div>
          <!-- END MAIN PANEL --> 
        </div>
        <!-- HERE COMES YOUR CONTENT --> 
      </div>
      <!-- END MAIN ROW -->
      <!-- END PAGE CONTENT -->   
 
  <!-- END MAIN CONTENT --> 
<style>	
a {
    cursor: pointer;
}
</style>
<script type="text/javascript">
function del_file(eleId) {
var ele = document.getElementById("delete_file" + eleId);
ele.parentNode.removeChild(ele);
}
</script>
<script>
$(document).ready(function() {
	
	//alert("Districtss::" + "${districtid}");
	
	$(document).on('click', '.ttHistoryClass', function(){
	
	$("#jsonErrListID12").empty();
	var ticketNo = $(this).attr("val");
	//alert(obj.createdBy);
	//var ticketNo = $(this).closest('tr').find('.ticketNoClass').val();
	var createdBy = $(this).attr("val1");	
	var ticketDesc =$(this).attr("val2");
	var t='';
	var tt ='';
			$("#ticketNoID").val(ticketNo);
			t= t+'<thead><tr><td colspan="4" style="border:none;"><strong>TT Number: </strong>'+ticketNo+'</td></tr> <tr><td colspan="4" style="border:none;"><strong>Created By: </strong>'+createdBy+'</td></tr><tr><td colspan="4" style="border:none;white-space: nowrap;"><strong>Complaint: </strong>'+ticketDesc+'</td></tr>';
			//alert('ticketNo'+ticketNo);
			t= t+'<tr><th>Date</th><th>Assign To</th><th>Assign To Mobile No</th><th>Remarks</th><th>Status</th></tr></thead><tbody>';
			$('.trrClass').remove();
			$.ajax({ 
			     type: 'GET', 
			     url:  'getTTHistory?ttNumber='+ticketNo,
			     async: false,
			     success: function(data){ 
			    	//alert('success21'+data);
			    	 $.each(data, function(idx, obj){ 
			    		 
			    		
			    		 
			    		// var createdBy1 = obj.createdBy;
			    		 
			    	 
			    		 tt = tt + ' <tr class="trrClass"> '+
							' <td><div id="reqjson" class="jjson">'+obj.effectiveFrm+'</div></td> '+
							' <td><div id="resjson" class="jjson">'+obj.assignedTo+'</div></td> '+
							' <td><div id="resjson" class="jjson">'+obj.mobileNo+'</div></td> '+
							' <td><span>'+obj.remarks+'</span></td> '+
							' <td>'+obj.currentStatus+'</td> '+
							' <td style="display: none"><div id="resjson12" class="createdByClass">'+obj.createdBy+'</div></td> '+
							' <td style="display: none"><div id="resjson12" class="ticketDesClass">'+obj.ticketDesc+'</div></td> '+
							' </tr> ';
			    		// i++;
			    	 });
			    	 
			    	 t = t+tt+"</tbody>";
			    	// alert(t+"t");
			    	 $("#jsonErrListID12").append(t);
			    	
			    	 
			     }
			
			 
			});
			
});

	$(document).on('click', '.apsflnocClass', function(){
		
		var districts = $(this).attr("val");
				 $.ajax({ 
				     type: 'GET', 
				     url:  'createAPSFLNocTT?districts='+districts,
				     async: false,
				     success: function(data){ 
				    	 
				    	 //alert(data);
				$("#parentticketnoID").val(data);
				$("#parTTNOID").val(data);
				alert("Genrate APSFL Noc ticket Sucessfully with Ticket No : "+ $("#parentticketnoID").val());
				
				     }
				}); 
	});
	
	$(document).on('click', '.parentTTNoc', function(){
		//alert("clikss");
		if($("#parentticketnoID").val()==null || $("#parentticketnoID").val()==''){
			alert("Please Genarate APSFL Noc Ticket");
			$("#parentTTNoc").prop('checked') == false
			 var checkBoxes = $("input[name=parentNOC]");
	        checkBoxes.prop("checked", !checkBoxes.prop("checked"));
			
		}
		else{
			//alert($("#parentticketnoID").val()+"In Else");
		}
		//alert("In Click");
		/* if($("#parentTTNoc").prop('checked') == true){
		    //do something
			alert("Checked11aa");
			$("#parentticketnoID").val($("#NocParent").val());
		} */
	});
	$(document).on('click', '.oltStatusClass', function(){
		//alert("in clcik11");
      // alert("${cpeProfileID}");
		$("#jsonErrListID").empty();
		
		var agoraSerCode = $(this).closest('tr').find('.agoraSerCodeClass').val();
		var cpeProfileID = $(this).closest('tr').find('.cpeProfileIDClass').val();
		//added by mohit
		var cafno = $(this).closest('tr').find('#cafNOID').val();
		var agoraSerCode="${agoraSerCode}"
		var cpeProfileID="${cpeProfileID}"
		var cafno="${cafNo}"
		var t='';
		var tt ='';
		
		
				 $.ajax({ 
				     type: 'GET', 
				     url:  'getOltStatus?agoraSerCode='+agoraSerCode+"&cpeprofileid="+cpeProfileID+"&cafno="+cafno,			     
				     async: false,
				     success: function(data){ 
				    	//alert('success21'+data);
				    	 //$.each(data, function(idx, obj){ 
				    
				   // if(data.statusCode!=200 ){
				    	
			//alert(data.statusCode);
				     if(data.statusCode==500) {
				    	
				    	 $("#notApplicable").val("NA");
				    		/* $("#usageCode").val(" ONT is switched off ");
				    		tt = tt+' <tr class="trrClass"> <td  colspan="6" align = "center"><div id="reqjson" class="jjson" align="center"><h2 align = "center">'+$("#usageCode").val()+'</h2></div></td></tr>'; */
				    		tt = tt + ' <tr> ';
				    	
				    		   t= t+'<thead><tr><th>Operational State </th><th>Last down cause </th></tr></thead><tbody>';
				    		if(data.operationalState!=null){
				    			tt = tt +	' <td><div id="reqjson" class="jjson">'+data.operationalState+'</div></td> ';
			    			 }
			    			 else{
			    			 tt = tt +	' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
			    			 }
			    		     if(data.lastDownCause){
			    					 tt = tt +	' <td><div id="reqjson" class="jjson">'+data.lastDownCause+'</div></td> ';
			    			 }
			    			 else{
								tt = tt +	' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> </tr>';
							}
			    		     
			    		   //  alert("Operational State : "+data.operationalState+ "Last down cause : "+data.lastDownCause);
				     } 
                         if(data.statusCode==422) {
				    		
                         t= t+'<thead><tr><th>Operational State </th><th>Temperature </th><th>Received power 1490nm (dBm) </th><th>Transmitted power 1310nm (dBm) </th><th>Distance (in KM) </th></tr></thead><tbody>';
				    		$("#usageCode").val(" ONT ID / ONT Profile not matching with Agora ");
				    		//alert(data.statusCode+"code "+$("#usageCode").val());
				    		tt = tt+' <tr> <td  colspan="6" align = "center"><div id="reqjson" class="jjson" align="center"><h2 align = "center">'+$("#usageCode").val()+'</h2></div></td></tr>';
				    	}
				     if( data.statusCode==404) {
				    	 
				    	 t= t+'<thead><tr><th>Operational State </th><th>Temperature </th><th>Received power 1490nm (dBm) </th><th>Transmitted power 1310nm (dBm) </th><th>Distance (in KM) </th></tr></thead><tbody>';	
				    	 //alert(data.message)
				    		//$("#usageCode").val("ONT is Abscent");
				    	  //  alert("Status"+data+"Code"+data.statusCode);
				    		tt = tt+' <tr> <td  colspan="6" align="center"><div id="reqjson" class="jjson" align="center">'+data.message+'</div></td></tr>';
				    	} 
				   // }
                         if(data.statusCode==200){
				//
				t= t+'<thead><tr><th>Operational State </th><th>Temperature </th><th>Received power 1490nm (dBm) </th><th>Transmitted power 1310nm (dBm) </th><th>Distance (in KM) </th></tr></thead><tbody>';
                        	 $("#notApplicable").val("NA");
				    		 tt = tt + ' <tr> ';
				    			 if(data.operationalState!=null){
				    			 tt = tt +	' <td><div id="reqjson" class="jjson">'+data.operationalState+'</div></td> ';
				    			 }
				    			 else{
				    			 tt = tt +	' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
				    			 }
				    		    /*  if(data.lastDownCause){
				    					 tt = tt +	' <td><div id="reqjson" class="jjson">'+data.lastDownCause+'</div></td> ';
				    			 }
				    			 else{
									tt = tt +	' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
								} */
								if(data.temperature!=null){
									tt = tt +	' <td><div id="resjson" class="jjson">'+data.temperature+'</div></td> ';
								}else{
									tt = tt +		' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
								}
								if(data.rxPower1490nm!=null){
									tt = tt +		' <td><div id="resjson" class="jjson">'+data.rxPower1490nm+'</div></td> ';
								}
								else{
									tt = tt +		' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
								}
								if(data.txPower1310nm!=null)
									{
									tt = tt +	' <td><div id="resjson" class="jjson">'+data.txPower1310nm+'</div></td> ';
									}
								else{
									tt = tt +		' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
								}
								if(data.distance!=null){
									tt = tt +	' <td><div id="resjson" class="jjson">'+data.distance+'</div></td> ';
								}
								else{
									tt = tt +	' <td><div id="resjson" class="jjson">'+$("#notApplicable").val()+'</div></td> ';
								}
								' </tr> ';
				    	 }
				    	 t = t+tt+"</tbody>";
				    	 //alert(t+"t");
				    	 $("#jsonErrListID").append(t);
				    	
				    	 
				     }
				}); 
	});
				 
	$(document).on('click', '.onuRebootClass', function(){
		//alert("in clcik11");
      // alert("${cpeProfileID}");
		//$("#jsonErrListID8").empty();
		var popOltIpaddress =  $(this).attr("val");
		var oltCardNo = $(this).attr("val1");
		var oltPort =  $(this).attr("val2");
		var oltONUID = $(this).attr("val3");
		
		//alert(popOltIpaddress+"card no :"+oltCardNo+" oltPort :"+oltPort+" oltONUID :"+oltONUID);
		
		var t='';
		var tt ='';
		//t= t+'<thead><tr><th>Operational State </th><th>Temperature </th><th>RX Power 1550nm </th><th>RX Power 1490nm  </th><th>TX Power 1310nm  </th><th>Distance (in KM) </th></tr></thead><tbody>';
				 $.ajax({ 
				     type: 'GET', 
				     url:  'getOntReboot?popOltIpaddress='+popOltIpaddress+"&oltCardNo="+oltCardNo+"&oltPort="+oltPort+"&oltONUID="+oltONUID,
				     async: false,
				     success: function(data){ 
				    	 
				    	alert(data);
				    	 //$.each(data, function(idx, obj){ 
				    
				   // if(data.statusCode!=200 ){
				    	
			//alert(data.statusCode);
			//tt = tt+' <tr class="trrClass"> <td  colspan="6" align="center"><div id="reqjson" class="jjson" align="center">'+data+'</div></td></tr>';
				    	/* if(data.statusCode!=202) {
				    		
				    		$("#usageCode").val("Unable to Reboot ONT");
				    		tt = tt+' <tr class="trrClass"> <td  colspan="6" align = "center"><div id="reqjson" class="jjson" align="center"><h1 align = "center">'+$("#usageCode").val()+'</h1></div></td></tr>';
				    	}
                        
                         if(data.statusCode==202 ){
				
                        	 tt = tt+' <tr class="trrClass"> <td  colspan="6" align="center"><div id="reqjson" class="jjson" align="center">'+data.message+'</div></td></tr>';
				    	 } */
				    	// t = t+tt+"</tbody>";
				    	 //alert(t+"t");
				    	// $("#jsonErrListID8").append(t);
				    	
				    	 
				     }
				}); 
	});

				 
	$(document).on('click', '.cafUsageClass', function(){
		//alert("in clcik11");
	//	alert("click");
		//alert(districts);
		$("#jsonErrListID13").empty();
		
		var cafNo =  $(this).attr("val");
		var districtID = $(this).attr("val1");
		var t='';
		var tt ='';
		
				 $.ajax({ 
				     type: 'GET', 
				     url:  'getCafUsageInfo?cafNo='+cafNo+'&districtID='+districtID,
				     async: false,
				     success: function(data){ 
			
	//	alert(data+"bbb");
				    	var baseData=data.baseDnldSize;
				    	var totalusage=data.dnldUsage;
				    	if(data.dnldUsage>data.baseDnldSize)
				    	 {
				    			$("#usageFlag").val("Y");
				    	 }
				    	else{
				    		$("#usageFlag").val("N");
				    	}
				    	 //$.each(data, function(idx, obj){
				    		  $("#noUsageData").val(" No Usage Details Found ");
				    		  
				    	 t= t+'<thead><tr><th style="width: 180px;">Base Data Limit (GBs) </th><th style="width: 180px;">Total Usage (GBs) </th><th> Normal Speed </th style="width: 180px;"><th>Threashold Speed </th></tr></thead><tbody>';
				    		 tt = tt + ' <tr> ';
				    		 if(data.baseDnldSize!=0){
								tt = tt +' <td>'+data.baseDnldSize+'</td>';
				    		 }else{
				    			 tt = tt +' <td>'+$("#noUsageData").val()+'</td>';
				    		 }
				    		 if ($("#usageFlag").val()!='Y'){
				    			 if(data.dnldUsage!=0){
				    				 tt = tt +' <td>'+data.dnldUsage+'&nbsp;&nbsp;<i class="fa fa-circle fa-lg" style="color:green;" ></i></td>';
				    			 }
				    			 else{
				    				 tt = tt + ' <td>'+$("#noUsageData").val()+'&nbsp;&nbsp;<i class="fa fa-circle fa-lg" style="color:green;" ></i></td>';
				    			 }
				    		 }
				    		 if ($("#usageFlag").val()!='N'){
				 				if(data.dnldUsage!=0){
				 					 tt = tt +  ' <td>'+data.dnldUsage+'&nbsp;&nbsp;<i class="fa fa-circle fa-lg" style="color:red;" ></i></td>';
					    		 }else
					    			 {
					    			 tt = tt	 + ' <td>'+$("#noUsageData").val()+'&nbsp;&nbsp;<i class="fa fa-circle fa-lg" style="color:red;"></i></td>';
					    			 }
				    		 }
				    		 tt = tt +	' <td>'+data.normalSpeed+'</td> ';
				    		 tt = tt +		' <td>'+data.threasholdspeed+'</td> ';
				    		 tt = tt +	' </tr> '; 
	/* 	if(data.baseDnldSize!=0){
				  //alert(data.baseDnldSize);
				
			t= t+'<tbody><tr><td style="border:none;white-space: nowrap;padding-top: 25px; width: 358px; padding-bottom: 25px"><strong> </strong>'+data.baseDnldSize+'</td>';
		}
		else{
			t= t+'<tbody><tr><td style="border:none;white-space: nowrap;padding-top: 25px; width: 358px; padding-bottom: 25px"><strong></strong>'+ $("#noUsageData").val()+'</td>';
	}
				  //	t= t+'<td style="border:none;white-space: nowrap; width: 308px;"><strong></strong>';
			 if ($("#usageFlag").val()!='Y'){
				 if(data.dnldUsage!=0){
			//	alert("bbb");
					 t= t+''+data.dnldUsage+'<span class="glyphicon glyphicon-one-fine-green-dot nopadding" style="height:35px;padding-top:0; width: 100px;"></span>';
			 }
				  else{
					//alert("vamsee");
					 t= t+''+ $("#noUsageData").val()+'<span class="glyphicon glyphicon-one-fine-green-dot nopadding" style="height:35px;padding-top:0; width: 100px;"></span>'; 
				 } 
			 }
			if ($("#usageFlag").val()!='N'){
				if(data.dnldUsage!=0){	
				t= t+''+data.dnldUsage+'<span class="glyphicon glyphicon-one-fine-red-dot nopadding" style="height:35px;padding-top:0;"></span></td>';
			 }
				 else{
					 t= t+''+ $("#noUsageData").val()+'<span class="glyphicon glyphicon-one-fine-red-dot nopadding" style="height:35px;padding-top:0;"></span></td>';
				 }
					 
			}    
					t= t+'<td style="border:none;white-space: nowrap;padding-top: 25px;"><strong></strong>'+data.normalSpeed+'</td>';
		
			 
					t= t+'<td style="border:none;white-space: nowrap;padding-top: 25px;"><strong></strong>'+data.threasholdspeed+'</td></tr></tbody>';
		
	 */				
				    	
	            t = t+tt+"</tbody>";  
	           $("#jsonErrListID13").append(t);
	    	}	 
	
				}); 
	});
	  
	
	
	  if("${ticketFor}" == "Customer" || "${ticketFor}" == "CUSTOMER")
	  	$("#distID").prop("disabled", true);
	  
	$("#domainID").val("${domain}");
	$("#loginTenantCodeID").val("${tenantCode}");
	
	
	 $("input[id^='upload_file']").each(function() {
		 var id = parseInt(this.id.replace("upload_file", ""));
		 $("#upload_file" + id).change(function() {
		 if ($("#upload_file" + id).val() != "") {
		 $("#moreImageUploadLink").show();
		 }
		 });
		 });
	 
	 var upload_number = 2;
	 $('#attachMore').click(function() {
	 //add more file
	 var moreUploadTag = '';
	 moreUploadTag += '<div class="element"><label for="upload_file"' + upload_number + '>Upload File</label>';
	 moreUploadTag += '<input type="file" id="upload_file' + upload_number + '" name="images" class="upload_file1,images' + upload_number + '"/>';
	 moreUploadTag += '&nbsp;<a href="javascript:del_file(' + upload_number + ')" style="cursor:pointer;" onclick="return confirm(\"Are you really want to delete ?\")">Delete</a></div>';
	 $('<dl id="delete_file' + upload_number + '">' + moreUploadTag + '</dl>').fadeIn('slow').appendTo('#moreImageUpload');
	 upload_number++;
	 });

	 $(document).on('change', '.upload_file1', function() {
		 // Does some stuff and logs the event to the console
		 var val = $(this).val();

		   switch(val.substring(val.lastIndexOf('.') + 1).toLowerCase()){
		       case 'gif': case 'jpg':case 'jpeg': case 'png':
		          // alert("an image");
		           break;
		       default:
		           $(this).val('');
		           // error message here
		          
		           alert("Allowed file formats are jpg, jpeg, png, gif");
		           break;
		   } 
	 });

	
	$("#modifiedOnID").datetimepicker({
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
	$(".ticketCatDIVClass").hide();
	$(".ttCloserDateClass").hide();
	$(".customerFeedback").hide();
	$(".statusbaseCrectiveAct").hide();
	$(".select").select2({
		
	});
	$(".customerDIVClass").hide();
	$(".reasonClass").hide();
	$(".statusbaseCrectiveAct").hide();
	$(".updateDIVClass").hide();
	$(".updateDIVClassforDist").hide();
	
	$(".dynamicIssueAttributesDIV").hide();
	
	$("#ttTypeID").prop("disabled", false);
	$("#issueTypeID").prop("disabled", false);
	$("#issueID").prop("disabled", false);
	$("#ticketDescID").prop("readonly", false).css("background-color","#ffffff");
	     
	$("#ticketNOID").val("${ticketNo}");
	$("#ticketForSpanID").html("${ticketFor}");
	$("#ticketForHiddenID").val("${ticketFor}");
	
	$("#tenantCodeID").val("${tenantCode}");
	$("#tenantCodeIDD").val("${tenantCode}");
	$("#tenantMobileID").val("${tenantMobile}");

	$("#custNameID").val("${custName}");
	$("#contactMobileID").val("${contactMobile}");
	$("#contactLandlineID").val("${contactLandline}");
	$("#contactemailID").val("${contactemail}");
	$("#contactAddrID").val("${contactAddr}");
	
	if("${ticketFor}" == "Customer")
		{
		$("#cafNOID").val("${cafNo}");
		//alert("${districtid}");
		$("#distID").select2("val", "${districtid}");
		$("#tenantCodeID").val("${tenantCode}");
		$("#custIDD").val("${custID}");
		$("#customerIDD").val("${customerID}");
		
		$("#substnNoID").val("${subStationName}");
		$("#oltSerialNoID").val("${oltSerialNo}");
		$("#ipAddrID").val("${ipAddr}");
		$("#portIDD").val("${port}");
		$("#onuRegNoID").val("${onuRegNo}");
		$("#oltONUID").val("${oltONUID}");
		$("#tenantMobileID").val("${tenantMobile}");
		
		$("#apsflTrackID").val("${apsflTrackID}");
		$("#onuModelID").val("${onuModel}");
		$("#onuSerialNoID").val("${onuSerialNo}");
		$("#onuMACAddrID").val("${onuMACAddr}");
		$("#aadharNoID").val("${aadharNo}");
		
		$(".customerDIVClass").show();
		$(".updateDIVClass").hide();
		//$(".ttCategoryClass").hide();
		
		}
	else{
		
		$(".customerDIVClass").hide();
		$(".updateDIVClass").hide();
		$("#custIDD").val("${custID}");
		$("#tenantCodeID").val("${tenantCode}");
		
	}
	
	
	$('#districtID').change(function(){
		var districtID = $(this).val();
		
		$("#popID").empty();
		$("#popID").append('<option value="-1">--Select POP--</option>');
		if(districtID!="0") {
		$("#popID").append('<option value="All">All</option>');
		}
		$("#popID").select2("val", "-1");
		
		$("#oltID").empty();
		$("#oltID").append('<option value="-1">--Select OLT--</option>');
		$("#oltID").select2("val", "-1");
		
		
		$("#portID").empty();
		$("#portID").append('<option value="-1">--Select Port--</option>');
		$("#portID").select2("val", "-1");
		
		$("#splitL1ID").empty();
		$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL1ID").select2("val", "-1");
		
		$("#splitL2ID").empty();
		$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL2ID").select2("val", "-1");
		
		if(districtID!="-1") {
		$.ajax({ 
		     type: 'GET', 
		     url:  'getSubsList?stateID=1&districtID='+districtID+"&ticketFor="+$("#ticketForHiddenID").val()+"&tenantCode="+$("#loginTenantCodeID").val(), 
		     success: function(result){
		    	  	 $.each(result, function(key, value){ 
	      	    		$('#popID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
		     }
		  });
		}
	});
	
	
	$('#popID').change(function(){	
		var subStnSlno = $(this).val();
		$("#oltID").empty();
		$("#oltID").append('<option value="-1">--Select OLT--</option>');
		if(subStnSlno!="All") {
		$("#oltID").append('<option value="All">All</option>');
		}
		$("#oltID").select2("val", "-1");
		
		$("#portID").empty();
		$("#portID").append('<option value="-1">--Select Port--</option>');
		$("#portID").select2("val", "-1");
		
		$("#splitL1ID").empty();
		$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL1ID").select2("val", "-1");
		
		$("#splitL2ID").empty();
		$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL2ID").select2("val", "-1");
		
		//alert(subStnSlno);
		var lmoCode = '';
		if($("#ticketForHiddenID").val()=='LMO')
		{
			lmoCode = $("#loginTenantCodeID").val();
		}
		if($("#ticketForHiddenID").val()=='MSP')
		{
			lmoCode = $("#loginTenantCodeID").val();
		}
		if(subStnSlno!="-1") {
			$.ajax({
				type : "GET",
				url : "getOLTSBySubstationsrlno",
				dataType : "json",
				data : "&subStnSlno="+ subStnSlno+"&lmoCode="+lmoCode+"&ticketFor="+$("#ticketForHiddenID").val(),
				success : function(response) {
					$.each(response, function(key,val) {            
						$('#oltID').append('<option value=\"'+key+'\">'+val+'</option>');
		        	});
				}
			});
		}
	
	});
		
	
	/* Get OLT Port-Id Data By OLT Srl Number*/
	$('#oltID').change(function() {
		var oltSrlNo = $('#oltID').val();
		$("#portID").empty();
		$("#portID").append('<option value="-1">--Select Port--</option>');
		if(oltSrlNo!="All") {
		$("#portID").append('<option value="All">All</option>');
		}
		$("#portID").select2("val", "-1");
		
		$("#splitL1ID").empty();
		$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL1ID").select2("val", "-1");
		
		$("#splitL2ID").empty();
		$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL2ID").select2("val", "-1");
		
		var lmoCode = '';
		if($("#ticketForHiddenID").val()=='LMO')
		{
			lmoCode = $("#loginTenantCodeID").val();
		}
		if($("#ticketForHiddenID").val()=='MSP')
		{
			lmoCode = $("#loginTenantCodeID").val();
		}
		if(oltSrlNo!="") {
			$.ajax({
				type : "GET",
				url : "getOLTPortsByOltSrlNo",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&ticketFor="+$("#ticketForHiddenID").val(),
				success : function(response) {
						 $.each(response, function(key, value){ 
			      	    		$('#portID').append('<option value=\"'+key+'\">'+key+'</option>');
		      	    	 });
				}
			});
		}
	});

	
	$('#portID').change(function() {
		var oltPort = $(this).val();
		var oltSrlNo= $('#oltID').val();
		
		$("#splitL1ID").empty();
		$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
		if(oltPort!="All") {
		$("#splitL1ID").append('<option value="All">All</option>');
		}
		$("#splitL1ID").select2("val", "-1");
		
		$("#splitL2ID").empty();
		$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
		$("#splitL2ID").select2("val", "-1");
		
		var lmoCode = '';
		if($("#ticketForHiddenID").val()=='LMO')
		{
			lmoCode = $("#loginTenantCodeID").val();
		}
		if(oltPort!="" && oltSrlNo!="") {
			$.ajax({
				type : "GET",
				url : "getOLTPortsSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort,
				success : function(response) {
					
						 $.each(response.level1SlotList, function(key, value){ 
			      	    		$('#splitL1ID').append('<option value=\"'+value+'\">'+value+'</option>');
		      	    	 });
				}
			});
		}
	});
	
	

	$('#splitL1ID').change(function() {
		var oltPort = $('#portID').val();
		var oltSrlNo= $('#oltID').val();
		var l1slot=$(this).val();
		
		$("#splitL2ID").empty();
		$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
		if(l1slot!="All") {
		$("#splitL2ID").append('<option value="All">All</option>');
		}
		$("#splitL2ID").select2("val", "-1");
		
		var lmoCode = '';
		if($("#ticketForHiddenID").val()=='LMO')
		{
			lmoCode = $("#loginTenantCodeID").val();
		}
		if(oltPort!="" && oltSrlNo!="") {
			$.ajax({
				type : "GET",
				url : "getOLTPortsSplitterData",
				dataType : "json",
				data : "&oltSrlNo="+ oltSrlNo+"&lmoCode="+lmoCode+"&oltPort="+oltPort+"&l1slot="+l1slot,
				success : function(response) {
					
						 $.each(response.level2SlotList, function(key, value){ 
			      	    		$('#splitL2ID').append('<option value=\"'+value+'\">'+value+'</option>');
		      	    	 });
				}
			});
		}
	});
	
	$("#statusID").change(function()
			{
		
			if($(this).val()=="4")
			{
				if("${ticketFor}" == "CUSTOMER" || "${ticketFor}" == "Customer"  )
				{
					$(".customerFeedback").show();
				}
				else{
					$(".customerFeedback").hide();
				}
				$(".reasonClass").show();
				$(".statusbaseCrectiveAct").show();
				$(".statusbasedremks").hide();
				$(".ttCloserDateClass").show();//customerFeedback
				
				
				/* if($("#ticketForHiddenID").val()!='Customer')
				{
					if($("#ttCategoryID").val()=='Group'){
						$('.smsClass').show();
							var confName="GROUP_TT_CLOSE";
							$.ajax({
								type : "GET",
								url : "getConfigItems",
								dataType : "text",
								data : "&configName="+ confName,
								success : function(response) {
									     $("#groupTTMsgID").val(response);
						       }
							});
					}
				} */
			}
			else if($(this).val()=="5")
				{
				$(".reasonClass").show();
				$(".statusbaseCrectiveAct").hide();
				$(".statusbasedremks").show();
				$(".ttCloserDateClass").hide();
				$(".customerFeedback").hide();
				}
		else
			{
			$(".reasonClass").hide();
			$(".statusbaseCrectiveAct").hide();
			$(".statusbasedremks").show();
			$(".ttCloserDateClass").hide();
			$(".customerFeedback").hide();
			}
		
			if($(this).val()=="7")
			{
			$(".parentTicketNoClass").show();
			}
			else
				{
				$(".parentTicketNoClass").hide();
				}
		
				if($(this).val()=="2" || $(this).val()=="6"){
					$("#assignedToID").prop("disabled", false);
					
				}
				else
				{
					$("#assignedToID").prop("disabled", true);
					$("#assignedToID").val("${assignedToVal}");
				}
			});
	
	
	
	/* $("#ttCategoryID").change(function(){
		if($(this).val()=='Group'){ */
			/* $(".distDIVClass").hide();
			var confName="GROUP_TT_OPEN";
			//alert('dfd'+confName);
			$.ajax({
				type : "GET",
				url : "getConfigItems",
				dataType : "text",
				data : "&configName="+ confName,
				success : function(response) {
					//alert('dfd'+response);
					     $("#groupTTMsgID").val(response);
		       }
			}); */
			/* 
			
			if($("#ticketForHiddenID").val()=='LMO')
			{
				$(".ticketCatDIVClass").show();
				$("#districtID").empty();
				$("#districtID").append('<option value="-1">--Select District--</option>');
				$("#districtID").select2("val", "-1");
				$.ajax({
					type : "GET",
					url : "getDistrictLists",
					dataType : "json",
					data : "&typeOfUser="+"${ticketFor}"+"&tenantcode="+ $("#loginTenantCodeID").val(),
					success : function(response) {
						$.each(response, function(key,val) {     
							$('#districtID').append('<option value=\"'+key+'\">'+val+'</option>');
			        	});
					}
				}); */
				//$(".distClass").hide();
				/* $("#popID").empty();
				$("#popID").append('<option value="-1">--Select POP--</option>');
				$("#popID").append('<option value="All">All</option>');
				$("#popID").select2("val", "-1");
				$.ajax({
					type : "GET",
					url : "getOLTPortsByLmoCode",
					dataType : "json",
					data : "&lmoCode="+ $("#loginTenantCodeID").val(),
					success : function(response) {
						$.each(response, function(key,val) {     
							$('#popID').append('<option value=\"'+key+'\">'+val+'</option>');
			        	});
					}
				}); */
			//}
			//else 
			/* 	if($("#ticketForHiddenID").val()!='Customer')
				{
					$(".ticketCatDIVClass").show();
				}
		}
		else 
		{
			$(".distDIVClass").show();
			$(".ticketCatDIVClass").hide();
			
			if($("#domainID").val()=='LMO')
			{
				$("#districtID").empty();
				$("#districtID").append('<option value="-1">--Select District--</option>');
				$("#districtID").select2("val", "-1");
			
			}
			
			$("#popID").empty();
			$("#popID").append('<option value="-1">--Select POP--</option>');
			$("#popID").select2("val", "-1");
			
			$("#oltID").empty();
			$("#oltID").append('<option value="-1">--Select OLT--</option>');
			$("#oltID").select2("val", "-1");
			
			
			$("#portID").empty();
			$("#portID").append('<option value="-1">--Select Port--</option>');
			$("#portID").select2("val", "-1");
			
			$("#splitL1ID").empty();
			$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
			$("#splitL1ID").select2("val", "-1");
			
			$("#splitL2ID").empty();
			$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
			$("#splitL2ID").select2("val", "-1");
			
		}
	});
		 */
	$("#ttTypeID").change(function(){
		
		$("#issueTypeID").empty();
		$("#issueTypeID").append('<option value="-1">--Select Issue Type--</option>');
		$("#issueTypeID").select2("val", "-1");
		$("#issueID").empty();
		$("#issueID").append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");
		$('.dynamicIssueAttributesDIV').html("");
		$(".dynamicIssueAttributesDIV").hide();
		$(".updateDIVClassforDist").hide();
		
		//alert($(this).val()+"issueType");
		//alert('in');
		var issueTypeObj = {
				"ttType":$(this).val(),
				"ticketFor":$("#ticketForHiddenID").val(),
			   };
		
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     //contentType : 'application/json; charset=utf-8',
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssueTypes', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	// alert(data+"data");
	      	    	 $.each(data, function(key, value){ 
	      	    		$('#issueTypeID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 });
	
	$("#issueTypeID").change(function(){
		//alert($("#ticketForHiddenID").val()+"ticketfor"+$("#ttTypeID").val());
		$("#issueID").empty();
		$('#issueID').append('<option value="-1">--Select Issue--</option>');
		$("#issueID").select2("val", "-1");
		$('.dynamicIssueAttributesDIV').html("");
		$(".dynamicIssueAttributesDIV").hide();
		$(".updateDIVClassforDist").hide();
		//alert($(this).val()+"issueType");
		//alert('in');
		var issueTypeObj = {
				"issueType":$(this).val(),
				"tickerFor":$("#ticketForHiddenID").val(),
				"ticketType":$("#ttTypeID").val(),
			   };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     //contentType : 'application/json; charset=utf-8',
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssues', 
	      	     data:issueTypeObj,
	      	     success: function(data) { 
	      	    	//alert(data+"data");
	      	    	 $.each(data, function(key, value){ 
	      	    		 //alert(key);
	      	    		$('#issueID').append('<option value=\"'+key+'\">'+value+'</option>');
	      	    	 });
	      	     }
	      	     });
	 
	});
	
	$("#issueID").change(function(){
		var issueVal="${issueCodeVal}";
		if(issueVal==$("#issueID").val())
		{	
		/* $('.updateDIVClassforDist').html("");
		$(".updateDIVClassforDist").show(); */
		$('.dynamicIssueAttributesDIV').html("");
		$(".dynamicIssueAttributesDIV").hide(); 
		}
		else{
		$('.updateDIVClassforDist').html("");
		$(".updateDIVClassforDist").hide();
		}
		$('.dynamicIssueAttributesDIV').html("");
		$(".dynamicIssueAttributesDIV").hide();
		 
		 var html = "";
		var issueCodeObj = {
				"issueCode":$(this).val(),
			   };
		
	 	  $.ajax({ 
	      	     type: 'GET', 
	      	     dataType : 'json',
	      	     async: false,
	      	     url: 'getIssueAttributes', 
	      	     data:issueCodeObj,
	      	     success: function(data) { 
	      	          	    
	      	  	$.each(data, function(idx, ttIssueCodeAttributesDTO){ 
	      	  		
	      	  	 html=html+'<div class="col-sm-4">'+
					'<div class="form-group">'+												
					'<label class="control-label labClass">'+ttIssueCodeAttributesDTO.attrName;	
					if(ttIssueCodeAttributesDTO.mandatoryFlag=='Y')
						{
							html=html+'<span style="color: red;">*</span></label>';
						}
					else
						{
						html=html+'</label>';
						}
					var attName=ttIssueCodeAttributesDTO.attrName;
					var attNameWithOutSpaces=attName.replace(" ","_");
					if(ttIssueCodeAttributesDTO.uiType=='LIST_BOX')
					{
						if(ttIssueCodeAttributesDTO.lovMap!=null){
						html=html+'<input type="hidden" class="selectHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><select name="'+attNameWithOutSpaces+'" style="width:200px;" class="form-control form-white selectSearchClass">';
						html=html+'<option value="-1">--Please Select--</option>';
						$.each(ttIssueCodeAttributesDTO.lovMap, function(key, lovMap){ 
								html=html+'<option value=\"'+lovMap+'\">'+lovMap+'</option>';
						  });
						html=html+'</select>';
						}
						else
							{
							html=html+'<input type="hidden" class="selectHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" name="'+ttIssueCodeAttributesDTO.attrName+'"/></div></div>';
							}
					}
					else if(ttIssueCodeAttributesDTO.uiType=='RADIO_BUTTON')
					{
						if(ttIssueCodeAttributesDTO.lovMap!=null){
							html=html+'<input type="hidden" class="radioHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/>';
						$.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){ 
							html=html+'<input type="radio" name="'+attNameWithOutSpaces+'" value="'+value+'" > '+value+'</input>';
						  });
						}
						else{
							html=html+'<input type="hidden" class="radioHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" name="'+attNameWithOutSpaces+'"/></div></div>';
						}
					}
					else if(ttIssueCodeAttributesDTO.uiType=='CHECK_BOX')
					{
						if(ttIssueCodeAttributesDTO.lovMap!=null){
							
							html=html+'<input type="hidden" class="checkboxHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/>';
						$.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){ 
							html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" > '+value+'</input>';
						  });
						}
						else
							{
							html=html+'<input type="hidden" class="checkboxHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" name="'+attNameWithOutSpaces+'"/></div></div>';
							}
					}
					else
					{
						html=html+'<input type="hidden" class="textHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" name="'+attNameWithOutSpaces+'" style="width: 200px; " maxlength="50" class="form-control form-white"</input>';
					}
					
					html=html+'</div></div>';
					//alert('after'+html);
				});
	      	  	
	      	  		$('.dynamicIssueAttributesDIV').show();
					$('.dynamicIssueAttributesDIV').append(html);
	      	    	
	      	     }
	      	     });
	 	 $(".selectSearchClass").select2({
	 		
	 	});
	 	var issueVal="${issueCodeVal}";
	 	if(issueVal==$("#issueID").val())

	 	{	
	 	$(".updateDIVClassforDist").show();
	 	$('.dynamicIssueAttributesDIV').html("");
	 	$(".dynamicIssueAttributesDIV").hide();
	 	}
	 	else{
	 	$(".updateDIVClassforDist").hide();
	 	}
	});
	
	if("${flag}" == "updateTTFLAG"){
		
		//$("#ttCategoryID").prop("disabled", true);
		//alert("${parentTicketNo}");
		  if("${parentTicketNo}"!="" && "${parentTicketNo}"!=null){
			  // alert("In loop");
			  // alert("${parentTicketNo}");
			   $("#isParent").hide();
			   $("#apsflnocID").hide();
		   }
		  else{
			  $("#isParent").show();
			   $("#apsflnocID").show();
			  //alert("mnj");
		  }
		//alert("in");
		var html = "";
			var issueCodeObj = {
					"issueCode":"${issueCodeVal}",
					"ttNumber":"${ticketNo}",
				   };
	
		  $.ajax({ 
		   	     type: 'GET', 
		   	     dataType : 'json',
		   	     async: false,
		   	     url: 'getIssueUpdateAttributes', 
		   	     data:issueCodeObj,
		   	     success: function(data) { 
		   	          	    
		   	  	$.each(data, function(idx, ttIssueCodeAttributesDTO){ 
		   	  		
		   	  	 html=html+'<div class="col-sm-3">'+
						'<div class="form-group">'+												
						'<label class="control-label labClass">'+ttIssueCodeAttributesDTO.attrName;	
						if(ttIssueCodeAttributesDTO.mandatoryFlag=='Y')
							{
								html=html+'<span style="color: red;">*</span></label>';
							}
						else
							{
							html=html+'</label>';
							}
						var attName=ttIssueCodeAttributesDTO.attrName;
						
						
						var attNameWithOutSpaces=attName.replace(" ","_");
						if(ttIssueCodeAttributesDTO.uiType=='LIST_BOX')
						{
							if(ttIssueCodeAttributesDTO.lovMap!=null){
							html=html+'<input type="hidden" class="selectHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><select name="'+attNameWithOutSpaces+'" value="'+ttIssueCodeAttributesDTO.attrValue+'" style="width:200px;" class="form-control form-white selectSearchClass">';
							html=html+'<option value="'+ttIssueCodeAttributesDTO.attrValue+'">'+ttIssueCodeAttributesDTO.attrValue+'</option>';
							$.each(ttIssueCodeAttributesDTO.lovMap, function(key, lovMap){ 
									html=html+'<option value=\"'+lovMap+'\">'+lovMap+'</option>';
							  });
							html=html+'</select>';
							}
							else
								{
								html=html+'<input type="hidden" class="selectHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+ttIssueCodeAttributesDTO.attrName+'"/></div></div>';
								}
						}
						else if(ttIssueCodeAttributesDTO.uiType=='RADIO_BUTTON')
						{
							if(ttIssueCodeAttributesDTO.lovMap!=null){
								html=html+'<input type="hidden" class="radioHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/>';
							$.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){ 
								
								if(ttIssueCodeAttributesDTO.attrValue==value){
								html=html+'<input type="radio" name="'+attNameWithOutSpaces+'" value="'+value+'" class="radioClas" checked="checked" >' +value+'</input>';
								}
								else{
									html=html+'<input type="radio" name="'+attNameWithOutSpaces+'" value="'+value+'" class="radioClas" >' +value+'</input>';
								}
								});
							}
							else{
								html=html+'<input type="hidden" class="radioHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="radio" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+attNameWithOutSpaces+'"/></div></div>';
							}
						}
						else if(ttIssueCodeAttributesDTO.uiType=='CHECK_BOX')
						{
							//String s=ttIssueCodeAttributesDTO.attrValue;
							//alert("nnn");
						
							if(ttIssueCodeAttributesDTO.lovMap!=null){
								html=html+'<input type="hidden" class="checkboxHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/>';
								//var a=ttIssueCodeAttributesDTO.attrValue.split(',');
							/* $.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){
								//alert(value);
						
								$.each(a , function(b, val){
									//alert("bb"+val);
									//alert(val);
									//alert(val);
									//alert(value);
								if(val==value){
									alert("...");
									html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+val+'" checked="checked"> '+val+'</input>';
								}
								else{
									html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" > '+value+'</input>';
								}
								
								});
							 
							}); */
								$.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){
									//alert(check(value));
									if(check(value)){
										//alert("select");
										html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" checked="checked"> '+value+'</input>';
									}
									else{
										//alert("not");
										html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" > '+value+'</input>';
									}
								});
							function check(selectedValue) {
								var a=ttIssueCodeAttributesDTO.attrValue.split(',');
								var temp=true;
								for(var i=0;i<a.length;i++){
								//alert(selectedValue + "  "+ a[i]);
								if(!(selectedValue==a[i])){
									//alert("not eqal"+selectedValue + "  "+ a[i]);
									temp=false;
										}
								else{
									temp=true;
								break;
								}
							}
							return temp;
							}
							}
							else
								{
								html=html+'<input type="hidden" class="checkboxHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+attNameWithOutSpaces+'"/></div></div>';
								}
						}
						else
						{
							html=html+'<input type="hidden" class="textHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" id="attrID" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+attNameWithOutSpaces+'" style="width: 200px; " maxlength="50" class="form-control form-white"</input>';
						}
						
						html=html+'</div></div>';
						//alert('after'+html);
					});
		   	  	
		   	  		$('.dynamicIssueAttributesDIV').show();
						$('.dynamicIssueAttributesDIV').append(html);
		   	    	
		   	     }
		   	     });
			 $(".selectSearchClass").select2({
				
			});

			 $("#issueID").change(function(){
					//alert("${issueCodeVal}"+"mm"+$("#issueID").val());
					var issueVal="${issueCodeVal}";
					if(issueVal==$("#issueID").val())
				 	{
						   $('.updateDIVClassforDist').html("");
						   $(".updateDIVClassforDist").hide(); 
							var html = "";
								var issueCodeObj = {
										"issueCode":"${issueCodeVal}",
										"ttNumber":"${ticketNo}",
									   };
						
							  $.ajax({ 
							   	     type: 'GET', 
							   	     dataType : 'json',
							   	     async: false,
							   	     url: 'getIssueUpdateAttributes', 
							   	     data:issueCodeObj,
							   	     success: function(data) { 
							   	          	    
							   	  	$.each(data, function(idx, ttIssueCodeAttributesDTO){ 
							   	  		
							   	  	 html=html+'<div class="col-sm-3">'+
											'<div class="form-group">'+												
											'<label class="control-label labClass">'+ttIssueCodeAttributesDTO.attrName;	
											if(ttIssueCodeAttributesDTO.mandatoryFlag=='Y')
												{
													html=html+'<span style="color: red;">*</span></label>';
												}
											else
												{
												html=html+'</label>';
												}
											var attName=ttIssueCodeAttributesDTO.attrName;
											
											
											var attNameWithOutSpaces=attName.replace(" ","_");
											if(ttIssueCodeAttributesDTO.uiType=='LIST_BOX')
											{
												if(ttIssueCodeAttributesDTO.lovMap!=null){
												html=html+'<input type="hidden" class="selectHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><select name="'+attNameWithOutSpaces+'" value="'+ttIssueCodeAttributesDTO.attrValue+'" style="width:200px;" class="form-control form-white selectSearchClass">';
												html=html+'<option value="'+ttIssueCodeAttributesDTO.attrValue+'">'+ttIssueCodeAttributesDTO.attrValue+'</option>';
												$.each(ttIssueCodeAttributesDTO.lovMap, function(key, lovMap){ 
														html=html+'<option value=\"'+lovMap+'\">'+lovMap+'</option>';
												  });
												html=html+'</select>';
												}
												else
													{
													html=html+'<input type="hidden" class="selectHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+ttIssueCodeAttributesDTO.attrName+'"/></div></div>';
													}
											}
											else if(ttIssueCodeAttributesDTO.uiType=='RADIO_BUTTON')
											{
												if(ttIssueCodeAttributesDTO.lovMap!=null){
													html=html+'<input type="hidden" class="radioHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/>';
												$.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){ 
													
													if(ttIssueCodeAttributesDTO.attrValue==value){
													html=html+'<input type="radio" name="'+attNameWithOutSpaces+'" value="'+value+'" class="radioClas" checked="checked" >' +value+'</input>';
													}
													else{
														html=html+'<input type="radio" name="'+attNameWithOutSpaces+'" value="'+value+'" class="radioClas" >' +value+'</input>';
													}
													});
												}
												else{
													html=html+'<input type="hidden" class="radioHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="radio" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+attNameWithOutSpaces+'"/></div></div>';
												}
											}
											else if(ttIssueCodeAttributesDTO.uiType=='CHECK_BOX')
											{
												//String s=ttIssueCodeAttributesDTO.attrValue;
												//alert("nnn");
											
												if(ttIssueCodeAttributesDTO.lovMap!=null){
													html=html+'<input type="hidden" class="checkboxHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/>';
													//var a=ttIssueCodeAttributesDTO.attrValue.split(',');
												/* $.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){
													//alert(value);
											
													$.each(a , function(b, val){
														//alert("bb"+val);
														//alert(val);
														//alert(val);
														//alert(value);
													if(val==value){
														alert("...");
														html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+val+'" checked="checked"> '+val+'</input>';
													}
													else{
														html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" > '+value+'</input>';
													}
													
													});
												 
												}); */
													$.each(ttIssueCodeAttributesDTO.lovMap, function(key, value){
														//alert(check(value));
														if(check(value)){
															//alert("select");
															html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" checked="checked"> '+value+'</input>';
														}
														else{
															//alert("not");
															html=html+'<input type="checkbox"  name="'+attNameWithOutSpaces+'"  value="'+value+'" > '+value+'</input>';
														}
													});
												function check(selectedValue) {
													var a=ttIssueCodeAttributesDTO.attrValue.split(',');
													var temp=true;
													for(var i=0;i<a.length;i++){
													//alert(selectedValue + "  "+ a[i]);
													if(!(selectedValue==a[i])){
														//alert("not eqal"+selectedValue + "  "+ a[i]);
														temp=false;
															}
													else{
														temp=true;
													break;
													}
												}
												return temp;
												}
												}
												else
													{
													html=html+'<input type="hidden" class="checkboxHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+attNameWithOutSpaces+'"/></div></div>';
													}
											}
											else
											{
												html=html+'<input type="hidden" class="textHiddenClass" value="'+ttIssueCodeAttributesDTO.mandatoryFlag+'"/><input type="text" id="attrID" value="'+ttIssueCodeAttributesDTO.attrValue+'" name="'+attNameWithOutSpaces+'" style="width: 200px; " maxlength="50" class="form-control form-white"</input>';
											}
											
											html=html+'</div></div>';
											//alert('after'+html);
										});
							   	  	
							   	  		$('.updateDIVClassforDist').show();
										$('.updateDIVClassforDist').append(html);
							   	    	
							   	     }
							   	     });
								 $(".selectSearchClass").select2({
								});
				 	// alert("Update change");
				  
				 	}
			
	 });
		$(".updateDIVClass").show();
		/* $(".updateDIVClassforDist").show(); */
		
		if("${ticketFor}" == "Customer" || "${ticketFor}" == "CUSTOMER")
		{
		$(".customerDIVClass").show();
		$("#cafNOID").val("${cafNo}");
		$("#distID").select2("val", "${districtid}");
		$("#tenantCodeID").val("${tenantCode}");
		$("#custIDD").val("${custID}");
		$("#customerIDD").val("${customerID}");
		
		$("#substnNoID").val("${subStationName}");
		$("#oltSerialNoID").val("${oltSerialNo}");
		$("#ipAddrID").val("${ipAddr}");
		$("#portIDD").val("${port}");
		$("#onuRegNoID").val("${onuRegNo}");
		$("#oltONUID").val("${oltONUID}");
		
		$("#apsflTrackID").val("${apsflTrackID}");
		$("#onuModelID").val("${onuModel}");
		$("#onuSerialNoID").val("${onuSerialNo}");
		$("#onuMACAddrID").val("${onuMACAddr}");
		$("#aadharNoID").val("${aadharNo}");
		
		$(".customerDIVClass").show();
		
		}
		//alert("${parentTicketNo}");
		if("${parentTicketNo}"!="" && "${parentTicketNo}"!=null){
			$("#parentticketnoID").val("${parentTicketNo}");
			$(".parentTicketNoClass").show();
		}
		else{
		$(".parentTicketNoClass").hide();}
		//alert("${ticketTypeVal}"+"${ticketTypeVal}");
		$("#ttTypeID").val("${ticketTypeVal}");
		$("#issueID").val("${issueCodeVal}");
		
		$("#distID").val("${districtid}");
		
		$("#issueTypeID").val("${issueTypeVal}");
		//alert("${issueTypeVal}");
		$("#ticketDescID").val("${ticketDesc}");
		$("#assignedToID").val("${assignedToVal}");
		$("#assignedToID").select2("val", "${assignedToVal}");
		//alert("${assignedToVal}");
		//$("#statusID").val("${statusVal}");
		$("#ticketRemarkID").val("${ticketRemark}");
		$("#createdOnID").val("${createdOn}");
		$("#createdByID").val("${createdBy}");
		$("#expcloseDateID").val("${expcloseDate}");
		$("#assignedToID").prop("disabled", true);
		
		/* $("#ttTypeID").prop("disabled", true);
		$("#issueTypeID").prop("disabled", true);
		$("#issueID").prop("disabled", true); 
		
		$("#ticketDescID").prop("readonly", true).css("background-color","#ECEDEE");*/
		
		//alert("${districtid}"+"district");
		
		/* if("${tickCategory}"=="Group")
		{
			$('.distDIVClass').hide();
			$("#ttCategoryID").select2("val", "Group");
			$('.smsClass').hide();
			if($("#ticketForHiddenID").val()=='LMO')
			{
				$("#districtID").empty();
				$("#districtID").append('<option value="-1">--Select District--</option>');
				$("#districtID").select2("val", "-1");
				$.ajax({
					type : "GET",
					async: false,
					url : "getDistrictLists",
					dataType : "json",
					data : "&typeOfUser="+"${ticketFor}"+"&tenantcode="+ "${grupLmoTenantCode}",
					success : function(response) {
						$.each(response, function(key,val) {     
							$('#districtID').append('<option value=\"'+key+'\">'+val+'</option>');
			        	});
					}
				});
				if("${districtid}"!="")
					$("#districtID").select2("val", "${districtid}");
				$("#districtID").prop("disabled", true);
				$(".ticketCatDIVClass").show();
				$("#ttCategoryID").prop("disabled", true);
				//$(".distClass").hide();
				$("#popID").empty();
				$("#popID").append('<option value="-1">--Select POP--</option>');
				$("#popID").append('<option value="All">All</option>');
				$("#popID").select2("val", "-1");
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTPortsByLmoCode",
					dataType : "json",
					data : "&lmoCode="+ "${grupLmoTenantCode}",
					success : function(response) {
						$.each(response, function(key,val) {     
							$('#popID').append('<option value=\"'+key+'\">'+val+'</option>');
			        	});
					}
				});
				if("${popid}"!="")
					$("#popID").select2("val", "${popid}");
				$("#popID").prop("disabled", true);
				$("#oltID").empty();
				$("#oltID").append('<option value="-1">--Select OLT--</option>');
				$("#oltID").append('<option value="All">All</option>');
				$("#oltID").select2("val", "-1");
				if("${popid}"!='All' && "${popid}"!=''){
				
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTSBySubstationsrlno",
					dataType : "json",
					data : "&subStnSlno="+ "${popid}"+"&lmoCode="+"${grupLmoTenantCode}"+"&ticketFor="+"${ticketFor}",
					success : function(response) {
						$.each(response, function(key,val) {  
								$('#oltID').append('<option value=\"'+key+'\">'+val+'</option>');
			        	});
					}
				});
				}
				
				if("${oltid}"!="")
					$("#oltID").select2("val", "${oltid}");
				$("#oltID").prop("disabled", true);
				$("#portID").empty();
				$("#portID").append('<option value="-1">--Select Port--</option>');
				$("#portID").append('<option value="All">All</option>');
				$("#portID").select2("val", "-1");
				var lmoCode = '';
				if("${oltid}"!='All' && "${oltid}"!=''){
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTPortsByOltSrlNo",
					dataType : "json",
					data : "&oltSrlNo="+ "${oltid}"+"&lmoCode="+"${grupLmoTenantCode}"+"&ticketFor="+"${ticketFor}",
					success : function(response) {
							 $.each(response, function(key, value){ 
				      	    		$('#portID').append('<option value=\"'+key+'\">'+key+'</option>');
			      	    	 });
					}
				});
				}
				if("${portid}"!="")
					$("#portID").select2("val", "${portid}");
				$("#portID").prop("disabled", true);
				$("#splitL1ID").empty();
				$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
				$("#splitL1ID").append('<option value="All">All</option>');
				$("#splitL1ID").select2("val", "-1");
				var lmoCode = '';
				if("${portid}"!='All' && "${portid}"!=''){
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTPortsSplitterData",
					dataType : "json",
					data : "&oltSrlNo="+ "${oltid}"+"&lmoCode="+"${grupLmoTenantCode}"+"&oltPort="+"${portid}",
					success : function(response) {
						
							 $.each(response.level1SlotList, function(key, value){ 
				      	    		$('#splitL1ID').append('<option value=\"'+value+'\">'+value+'</option>');
			      	    	 });
					}
				});
				}
				if("${splitl1id}"!="")
					$("#splitL1ID").select2("val", "${splitl1id}");
				$("#splitL1ID").prop("disabled", true);
				$("#splitL2ID").empty();
				$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
				$("#splitL2ID").append('<option value="All">All</option>');
				$("#splitL2ID").select2("val", "-1");
				
				var lmoCode = '';
				if("${splitl1id}"!='All' && "${splitl1id}"!=''){
					$.ajax({
						type : "GET",
						async: false,
						url : "getOLTPortsSplitterData",
						dataType : "json",
						data : "&oltSrlNo="+ "${oltid}"+"&lmoCode="+"${grupLmoTenantCode}"+"&oltPort="+"${portid}"+"&l1slot="+"${splitl1id}",
						success : function(response) {
							
								 $.each(response.level2SlotList, function(key, value){ 
					      	    		$('#splitL2ID').append('<option value=\"'+value+'\">'+value+'</option>');
				      	    	 });
						}
					});
				}
					if("${splitl2id}"!="")
						$("#splitL2ID").select2("val", "${splitl2id}");
					$("#splitL2ID").prop("disabled", true);
			}
			else if($("#ticketForHiddenID").val()!='Customer')
				{
				//alert("else if");
				$(".ticketCatDIVClass").show();
				$("#ttCategoryID").prop("disabled", true);
				//$('#districtID').val("${districtid}");
				$("#districtID").select2("val","${districtid}");
				$("#districtID").prop("disabled", true);
				$("#popID").empty();
				$("#popID").append('<option value="-1">--Select POP--</option>');
				$("#popID").append('<option value="All">All</option>');
				$("#popID").select2("val", "-1");
				if("${districtid}"!='0' && "${districtid}"!=''){
				$.ajax({ 
				     type: 'GET', 
				     async: false,
				     url:  'getSubsList?stateID=1&districtID='+"${districtid}"+"&ticketFor="+"${ticketFor}"+"&tenantCode="+"${grupLmoTenantCode}", 
				     success: function(result){
				    	 //alert(result+"resulteeee");
				    	  	 $.each(result, function(key, value){ 
			      	    		$('#popID').append('<option value=\"'+key+'\">'+value+'</option>');
			      	    	 });
				     }
				  });
				}
				// alert("abc"+"${popid}");
				if("${popid}"!="")
					$("#popID").select2("val", "${popid}");
				$("#popID").prop("disabled", true);
				$("#oltID").empty();
				$("#oltID").append('<option value="-1">--Select OLT--</option>');
				$("#oltID").append('<option value="All">All</option>');
				$("#oltID").select2("val", "-1");
				
				
				var lmoCode = '';
				
				if($("#ticketForHiddenID").val()=='MSP'){
					 lmoCode = "${grupLmoTenantCode}";
				}
				
				if("${popid}"!='All' && "${popid}"!=''){
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTSBySubstationsrlno",
					dataType : "json",
					data : "&subStnSlno="+ "${popid}"+"&lmoCode="+lmoCode+"&ticketFor="+"${ticketFor}",
					success : function(response) {
						$.each(response, function(key,val) {            
							$('#oltID').append('<option value=\"'+key+'\">'+val+'</option>');
			        	});
					}
				});
				}
				if("${oltid}"!="")
					$("#oltID").select2("val", "${oltid}");
				$("#oltID").prop("disabled", true);
				$("#portID").empty();
				$("#portID").append('<option value="-1">--Select Port--</option>');
				$("#portID").append('<option value="All">All</option>');
				$("#portID").select2("val", "-1");
				var lmoCode = '';
				if($("#ticketForHiddenID").val()=='MSP'){
					 lmoCode = "${grupLmoTenantCode}";
					}
				if("${oltid}"!='All' && "${oltid}"!=''){
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTPortsByOltSrlNo",
					dataType : "json",
					data : "&oltSrlNo="+ "${oltid}"+"&lmoCode="+lmoCode+"&ticketFor="+"${ticketFor}",
					success : function(response) {
							 $.each(response, function(key, value){ 
				      	    		$('#portID').append('<option value=\"'+key+'\">'+key+'</option>');
			      	    	 });
					}
				});
				}
				if("${portid}"!="")
					$("#portID").select2("val", "${portid}");
				$("#portID").prop("disabled", true);
				$("#splitL1ID").empty();
				$("#splitL1ID").append('<option value="-1">--Select Split L1--</option>');
				$("#splitL1ID").append('<option value="All">All</option>');
				$("#splitL1ID").select2("val", "-1");
				var lmoCode = '';
			
				if("${portid}"!='All' && "${portid}"!=''){
				$.ajax({
					type : "GET",
					async: false,
					url : "getOLTPortsSplitterData",
					dataType : "json",
					data : "&oltSrlNo="+ "${oltid}"+"&lmoCode="+lmoCode+"&oltPort="+"${portid}",
					success : function(response) {
						
							 $.each(response.level1SlotList, function(key, value){ 
				      	    		$('#splitL1ID').append('<option value=\"'+value+'\">'+value+'</option>');
			      	    	 });
					}
				});
				}
				if("${splitl1id}"!="")
					$("#splitL1ID").select2("val", "${splitl1id}");
				$("#splitL1ID").prop("disabled", true);
				
				$("#splitL2ID").empty();
				$("#splitL2ID").append('<option value="-1">--Select Split L1--</option>');
				$("#splitL2ID").append('<option value="All">All</option>');
				$("#splitL2ID").select2("val", "-1");
				
				var lmoCode = '';
				
				if("${splitl1id}"!='All' && "${splitl1id}"!=''){
				$.ajax({
						type : "GET",
						async: false,
						url : "getOLTPortsSplitterData",
						dataType : "json",
						data : "&oltSrlNo="+ "${oltid}"+"&lmoCode="+lmoCode+"&oltPort="+"${portid}"+"&l1slot="+"${splitl1id}",
						success : function(response) {
								 $.each(response.level2SlotList, function(key, value){ 
					      	    		$('#splitL2ID').append('<option value=\"'+value+'\">'+value+'</option>');
				      	    	 });
						}
					});
				}
					if("${splitl2id}"!="")
						$("#splitL2ID").select2("val", "${splitl2id}");
					$("#splitL2ID").prop("disabled", true);
				}
		}
		else
			{
			$("#distID").select2("val", "${districtid}");
			$("#ttCategoryID").prop("disabled", true);
			} */
		$("#distID").select2("val", "${districtid}");
		if("${statusVal}" == "1")
			{
			$("#statusID option[value='1']").remove();
			$("#statusID option[value='3']").remove();
			$("#statusID option[value='4']").remove();
			$("#statusID option[value='5']").remove();
			$("#statusID option[value='6']").remove();
			//$("#statusID option[value='7']").remove();
			$("#statusID option[value='8']").remove();
			$("#statusID option[value='9']").remove();
			}
		
		if("${statusVal}" == "2")
		{
		$("#statusID option[value='1']").remove();
		$("#statusID option[value='2']").remove();
		$("#statusID option[value='4']").remove();
		$("#statusID option[value='8']").remove();
		$("#statusID option[value='9']").remove();
		}
		
		if("${statusVal}" == "3")
		{
		$("#statusID option[value='1']").remove();
		$("#statusID option[value='2']").remove();
		$("#statusID option[value='3']").remove();
		$("#statusID option[value='5']").remove();
		$("#statusID option[value='6']").remove();
		$("#statusID option[value='9']").remove();
		//$("#statusID option[value='7']").remove();
		}
		
		if("${statusVal}" == "4")
		{
			$("#statusID option[value='1']").remove();
			$("#statusID option[value='2']").remove();
			$("#statusID option[value='3']").remove();
			$("#statusID option[value='5']").remove();
			$("#statusID option[value='6']").remove();
			$("#statusID option[value='7']").remove();
			$("#statusID option[value='8']").remove();
			$("#statusID option[value='9']").remove();
			$("#statusID option[value='4']").remove();
			$("#ticketReasonID").val("${ticketReason}");
			$(".reasonClass").show();
			$(".statusbasedremks").hide();
			$(".statusbaseCrectiveAct").show();
			$(".ttCloserDateClass").show();
		}
		if("${statusVal}" == "5")
		{
			$("#statusID option[value='1']").remove();
			$("#statusID option[value='2']").remove();
			$("#statusID option[value='3']").remove();
			$("#statusID option[value='4']").remove();
			$("#statusID option[value='5']").remove();
			$("#statusID option[value='6']").remove();
			$("#statusID option[value='7']").remove();
			$("#statusID option[value='9']").remove();
			$("#ticketReasonID").val("${ticketReason}");
			$(".reasonClass").show();
		}
		if("${statusVal}" == "6")
		{
			$("#statusID option[value='1']").remove();
			$("#statusID option[value='2']").remove();
			$("#statusID option[value='4']").remove();
			//$("#statusID option[value='6']").remove();
			$("#statusID option[value='8']").remove();
			$("#statusID option[value='9']").remove();
		}
		if("${statusVal}" == "7")
		{
			$("#statusID option[value='1']").remove();
			$("#statusID option[value='2']").remove();
			$("#statusID option[value='4']").remove();
			$("#statusID option[value='5']").remove();
			$("#statusID option[value='6']").remove();
			$("#statusID option[value='7']").remove();
			$("#statusID option[value='8']").remove();
			$("#statusID option[value='9']").remove();
		}
		if("${statusVal}" == "8")
		{
			$("#statusID option[value='1']").remove();
			$("#statusID option[value='2']").remove();
			$("#statusID option[value='3']").remove();
			$("#statusID option[value='4']").remove();
			$("#statusID option[value='5']").remove();
			//$("#statusID option[value='7']").remove();
			$("#statusID option[value='9']").remove();
			$("#statusID option[value='8']").remove();
		}
		}
	
	$("#saveID").click(function(){
	/* 	if($('#statusID').val()== "-1"){
			$('#statusID').val($('#statusttID').val());
			alert($('#statusID').val($('#statusttID').val()));
			alert($('#statusID').val());
	} */
			var attrList='';
			var attCount=0;
			 var check_cnt = 0;
			$("#saveID").prop("disabled", true);
			$('#id_container').find('ol').html("");
			var issueVal="${issueCodeVal}";
			//if(issueVal!=$("#issueID").val()){
			$('.labClass').each(function() {
				var key=$(this).text().replace("*","");
	  			  var value='';
	  			  //alert("before alert");
	  			  var MandatoryFlag='';
	  			  
	  				  
	  				if($(this).next().attr('type')=='hidden')
					 {
	  					MandatoryFlag=$(this).next().val();
					  }
	  			  if($(this).next().next().attr('type')=='radio')
	  			 {
	  				var attNameWithOutSpaces=key.replace(" ","_");
	  			  	value=$('input:radio[name='+attNameWithOutSpaces+']:checked').val();
					
					if(value==null||value=='undefined')
						{
						value='';
						 
						
						if(MandatoryFlag=='Y')
							{
							//ERROR
							$("#id_container").addClass('dberrorMsg');
							$('#id_container').find('ol').append('<li style="list-style: none;">Please Select '+key+'</li>');
							$("#id_container").show();	
							check_cnt=check_cnt+1;
							}
						}
	  			 }
	  			  else  if($(this).next().next().attr('type')=='checkbox')
	  			 {
	  				//alert("in checkbox");
	  				  var count=0;
	  				  var attNameWithOutSpaces=key.replace(" ","_");
	  				$('input[type="checkbox"]:checked').each(function(){
	  					if(count==1)
	  						value=value+',';
	  					if(this.name==attNameWithOutSpaces){
	  						value=value+this.value;
	  						count=1;
	  					}
	  			        
	  			    }); 
	  				
	  				
	  				if(MandatoryFlag=='Y' && count ==0)
					{
	  					$("#id_container").addClass('dberrorMsg');
						$('#id_container').find('ol').append('<li style="list-style: none;">Please Select '+key+'</li>');
						$("#id_container").show();	
						check_cnt=check_cnt+1;
					}
	  		     }
	  			  else  if($(this).next().next().attr('type')=='text')
	  			  {
	  					value=$(this).next().next().val();
	  					if(value=='' && MandatoryFlag=='Y')
						{
	  						$("#id_container").addClass('dberrorMsg');
							$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter '+key+'</li>');
							$("#id_container").show();	
							check_cnt=check_cnt+1;
						}
	  			  }
	  			  else
	  			  {
	  				var attNameWithOutSpaces=key.replace(" ","_");
	  			  	value=$('select[name='+attNameWithOutSpaces+']').find(":selected").val();
	  				 //alert(value);
	  				 if(value=='-1')
	  					 {
	  					 value='';
	  					if(MandatoryFlag=='Y')
						{
	  						$("#id_container").addClass('dberrorMsg');
							$('#id_container').find('ol').append('<li style="list-style: none;">Please Select '+key+'</li>');
							$("#id_container").show();	
							check_cnt=check_cnt+1;
						}
	  					 }
	  			  }
	  			  
	  			  
	  			if(attCount==1)
	  				attrList=attrList+'&&';
	  			
	  			attrList=attrList+key+':'+value;
	  			//alert(attrList+"attrList");
	  			attCount=1;
	  		});
			//}
			
			//alert(attrList+'attrList');
			$("#dynAttFlagID").val(attrList);
			/* if($("#ticketForHiddenID").val() != "Customer" && $("#ticketForHiddenID").val() !="LMO"){
			if($("#ttCategoryID").val() == "Group"){
				 if($("#districtID").val()=="-1"){				
					    $("#id_container").addClass('dberrorMsg');
						$('#id_container').find('ol').append('<li style="list-style: none;">Please Select District</li>');
						$("#id_container").show();	
						check_cnt=check_cnt+1;
					} 
				 if( ("${flag}" != "updateTTFLAG" || ("${flag}" == "updateTTFLAG" && $("#statusID").val()=="4" )) && ($("#groupTTMsgID").val()=="" || $("#groupTTMsgID").val().trim()==""))
					 {
					 	$("#id_container").addClass('dberrorMsg');
						$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter SMS</li>');
						$("#id_container").show();	
						check_cnt=check_cnt+1;
					 }
			}
			} */
			
			
		/* 	if($("#ticketForHiddenID").val() == "LMO"){
				if($("#ttCategoryID").val() == "Group"){
					 if($("#popID").val()=="-1"){				
						    $("#id_container").addClass('dberrorMsg');
							$('#id_container').find('ol').append('<li style="list-style: none;">Please Select POP</li>');
							$("#id_container").show();	
							check_cnt=check_cnt+1;
						} 
					 if(("${flag}" != "updateTTFLAG" || ("${flag}" == "updateTTFLAG" && $("#statusID").val()=="4" )) && ($("#groupTTMsgID").val()=="" || $("#groupTTMsgID").val().trim()==""))
					 {
						 $("#id_container").addClass('dberrorMsg');
						$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter SMS</li>');
						$("#id_container").show();	
						check_cnt=check_cnt+1;
					 }
				}
				} */
			 
		
		  if($("#ttTypeID").val()=="-1"){				
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Select TT Type</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}   
		  
		  
		  if($("#issueTypeID").val()=="-1"){				
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Issue Type</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}  
		  
		  
		  if($("#issueID").val()=="-1"){				
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Issue</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}  
		
		  
		  if($("#distID").val()=="-1"){				
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Select District</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}  
		  
		  /* $(document).on('click', '.parentTTNoc', function(){
				//alert("In Click");
				if($("#parentTTNoc").prop('checked') == true){
				    //do something
					alert("Checked11aa");
					$("#parentticketnoID").val($("#NocParent").val());
				}
			}); */
		  
		  if($("#parentTTNoc").prop('checked') == false && ($("#parTTNOID").val()!=null && $("#parTTNOID").val()!='') ){
			    //do something
				//alert("Checked11aa");
				$("#parentticketnoID").val('');
				
			}
		  
		 /*  if($("#ttCategoryID").val()=="Individual")
		  {
		   if($("#distID").val()=="-1" )  
		   {	
		  // alert("checking district for individual");
		     $("#id_container").addClass('dberrorMsg');
		  $('#id_container').find('ol').append('<li style="list-style: none;">Please Select District</li>');
		  $("#id_container").show();	
		  check_cnt=check_cnt+1;
		  } 
		  } */
		  if($("#ticketDescID").val()=="" || !$.trim($("#ticketDescID").val())){				
			    $("#id_container").addClass('dberrorMsg');
				$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Complaint in Brief</li>');
				$("#id_container").show();	
				check_cnt=check_cnt+1;
			}
		  
		  if("${flag}" == "updateTTFLAG"){
			  
			 
			  
			 /*  if($("#statusID").val()=="-1"){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Status</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}   */
			  
				if($("#statusID").val()=="2" || $("#statusID").val()=="6"){
			  if($("#assignedToID").val()=="-1"){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Assigned To</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}  
				}
				//alert($("#statusID").val());
				if($("#statusID").val()=="7"){
					  if($("#parentticketnoID").val().trim()==""){				
						    $("#id_container").addClass('dberrorMsg');
							$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Parent Ticket No.</li>');
							$("#id_container").show();	
							check_cnt=check_cnt+1;
						}  
						}
			  
			  if($("#ticketRemarkID").val()==""){				
				    $("#id_container").addClass('dberrorMsg');
					$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Remarks</li>');
					$("#id_container").show();	
					check_cnt=check_cnt+1;
				}
			  
			  if($("#statusID").val()=="4" || $("#statusID").val()=="5"){
				  if($("#ticketReasonID").val()==""){				
					    $("#id_container").addClass('dberrorMsg');
						$('#id_container').find('ol').append('<li style="list-style: none;">Please Enter Reason</li>');
						$("#id_container").show();	
						check_cnt=check_cnt+1;
					}
				  else
					  {
					  //$("#ticketRemarkID").val($("#ticketRemarkID").val().trim().replace(/(\r\n|\n|\r)/gm,""));
					  $("#ticketRemarkID").val($("#ticketRemarkID").val().trim());
					  }
				  
				  if($("#statusID").val()=="4") 
					  {
					 	  	if($("#modifiedOnID").val()=="")
					  		{
					  			$("#id_container").addClass('dberrorMsg');
								$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Closure Date</li>');
								$("#id_container").show();	
								check_cnt=check_cnt+1;
					  		}
					 	  	
					 	   if($("#actualIssueID").val()=="-1")//feedbackID
					  		{
					  			$("#id_container").addClass('dberrorMsg');
								$('#id_container').find('ol').append('<li style="list-style: none;">Please Select Issue Category</li>');
								$("#id_container").show();	
								check_cnt=check_cnt+1;
					  		}
					 	  /*  if($("#feedbackID").val()=="")//
					  		{ */
					 		  
					 		  if("${ticketFor}" == "CUSTOMER" || "${ticketFor}" == "Customer"  )
							{
					 		  if (!$('input[name=feedback]:checked').val() ) {          
					 		   $("#id_container").addClass('dberrorMsg');
								$('#id_container').find('ol').append('<li style="list-style: none;">Please select feedback</li>');
								$("#id_container").show();	
								check_cnt=check_cnt+1;
					  		}
							}
					  }
			  }
		  }
		  if(check_cnt == 0)
		  {
			  if("${flag}" == "updateTTFLAG"){
				//  alert('in update');
				//  $("#issueNameID").val($(".ticketReasonClass").val());
				 if($("#statusID").val()=="-1"){
				$("#currentStatusID").val($("#statusID option:selected").val());
				 }
				 else{
					 $("#currentStatusID").val($("#statusID option:selected").text());
				 }
				  
				  if($("#statusID").val()=="-1")
					{
					$("#statusIDD").val("${statusVal}");
					}
				else{
					$("#statusIDD").val($("#statusID").val());
				}
				  if($("#statusID").val()!=7 && ("${parentTicketNo}"==null || "${parentTicketNo}"=="")){
					  
					  $("#parentticketnoID").val('');
					     }
				  //check parent ticket no valid or not
				//  alert($("#currentStatusID").val());
				  //alert($("#statusID").val());
				  if($("#statusID").val()=='7' || ("${statusName}"=='Pending' && $("#statusID").val()=='-1'))
					  {
					   	//alert('in status');
						  if($("#parentticketnoID").val()==$("#ticketNOID").val())
						  {
						//	  alert('in status2');
						  	$("#id_container").addClass('dberrorMsg');
							$('#id_container').find('ol').append('<li style="list-style: none;">Parent Ticket No. should not be Current Ticket No.</li>');
							$("#id_container").show();	
							check_cnt=check_cnt+1;
						  }
						  else
						  {
							  //get json call to check tt is present or not. if present it must be not in closed status
							  var issueTypeObj = {
													"parentticketno":$("#parentticketnoID").val(),
													};
					//		 alert('in status3');
							  $.ajax({ 
						      	     type: 'GET', 
						      	     //contentType : 'application/json; charset=utf-8',
						      	     dataType : 'json',
						      	     async: false,
						      	     url: 'checkTTExistence', 
						      	     data:issueTypeObj,
						      	     success: function(data) { 
						      	    	
						      	    	 $.each(data, function(key, value){ 
						      	    		// alert(key+"datdfda"+value);
						      	    		 if(value=="failed"){
						      	    			$("#id_container").addClass('dberrorMsg');
												$('#id_container').find('ol').append('<li style="list-style: none;">Parent Ticket No. is not present in the system (or) Parent Ticket No. is Closed</li>');
												$("#id_container").show();	
						      	    			check_cnt=check_cnt+1;
						      	    			 return false;
						      	    		 }
						      	    	 });
						      	    	/*  if(data=="failed")
						      	    		 {
						      	    			 return false;
						      	    		 } */
						      	     }
						      	     });
						  }
					  }
				//  alert(check_cnt+'sdfsdf');
				  if(check_cnt==0){
			    $("#ttTypeID").prop("disabled", false);
				$("#issueTypeID").prop("disabled", false);
				$("#issueID").prop("disabled", false);
				$("#ticketDescID").prop("readonly", false).css("background-color","#ffffff");
				$("#assignedToID").prop("disabled", false);
				$("#distID").prop("disabled", false);
				$("#ticketRemarkID").val($("#ticketRemarkID").val().trim());
				$("#ticketDescID").val($("#ticketDescID").val().trim());

				$("#ticketRemarkID").val($("#ticketRemarkID").val().trim());
				$("#ticketDescID").val($("#ticketDescID").val().trim());
				
				//$("#ttCategoryID").prop("disabled", false);
				/* if($("#ttCategoryID").val()=='Group')
					{
						if($("#ticketForHiddenID").val()!='LMO'){
							$("#districtID").prop("disabled", false);
						}
						$("#popID").prop("disabled", false);
						$("#oltID").prop("disabled", false);
						$("#portID").prop("disabled", false);
						$("#splitL1ID").prop("disabled", false);
						$("#splitL2ID").prop("disabled", false);
					} */

				  }
				  else{
					  $("#saveID").prop("disabled", false);
					  return false;
				  }
			  }
			  $("#issueNameID").val($("#issueID option:selected").text());
			  //alert('check_cnt'+check_cnt)
			  if(check_cnt==0){
				/*   alert('in if');
				  alert($("#modifiedOnID").val()); */
				  $("#distID").prop("disabled", false);
				 $("#ticketDescID").val($("#ticketDescID").val().trim());
				 $("#ticketDescID").val($("#ticketDescID").val().trim());
			    $("#createTTicketFormID").attr('action',"saveTT.do");
				$("#createTTicketFormID").submit();
			  }
			  else{
				  $("#saveID").prop("disabled", false);
				  return false;
			  }
		  }
		  else{
			  $("#saveID").prop("disabled", false);
			  return false;
		  }
	});

	$(document).on('click', '.imagepathh', function(){
		var imagepath = "${imagePath}";
		
		//alert("ssss");
		//alert(imagepath);
		$("#image").val(imagepath);
		$("#createTTicketFormID").attr('action',"downloadImage.do");
		$("#createTTicketFormID").submit();


		}); 
	
	$("#cancelID").click(function(){
		$("#createTTicketFormID").attr('action',"createTT.do");
		$("#createTTicketFormID").submit();
	});
	});

    

</script>  

 <script>
     var canvas = document.getElementById('myCanvas');
     var context = canvas.getContext('2d');
     var centerX = 42 ;
     var centerY = 8 ;
     var radius = 8;

     context.beginPath();
     context.arc(centerX, centerY, radius, 0, 3 * Math.PI, false);
     if("${oltStatusFlag}"=='Y' ){
     context.fillStyle = 'red';
     }
     if("${oltStatusFlag}"=='N' ){
         context.fillStyle = 'green';
         }
     context.fill();
     context.lineWidth = 1;
     context.strokeStyle = '#003300';
     context.stroke();
   </script>
	 <!-- END FOOTER --> 
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
<script src="./resources/js/jquery.mCustomScrollbar.concat.min.js"></script> <!-- Custom Scrollbar sidebar --> 
<script src="./resources/js/select2.min.js"></script> <!-- Select Inputs -->
<script src="./resources/js/bootstrap-tagsinput.min.js"></script> <!-- Select Inputs --> 
<script src="./resources/js/jquery.dataTables.min.js"></script> <!-- Tables Filtering, Sorting & Editing --> 
<script src="./resources/js/table_editable.js"></script> <!-- Tables Editing --> 
<script src="./resources/js/table_dynamic.js"></script> <!-- Tables Dynamic --> 
<script type="text/javascript" src="./resources/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="./resources/js/jquery-ui-sliderAccess.js"></script>
