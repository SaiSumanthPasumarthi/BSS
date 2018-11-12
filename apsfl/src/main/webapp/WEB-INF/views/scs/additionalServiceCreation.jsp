<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
<!DOCTYPE html>
<html lang="en">
<script src="./resources/js/addl-service.js"></script>

<section>
<form:form action="#" id="rolesFormID" modelAttribute="addlServieForm" method="get">
  <!-- BEGIN MAIN CONTENT -->
  <div class="main-content createSrvcs"> 
   <!--  <div style="padding: 10px; margin: auto; width: 50%">
		<font color="Green"><label id="id_msg"></label></font>
	</div> -->
	<div id="id_container" class="error" style="margin: auto;width: 20%"><ol id="id_ol"></ol></div>
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong> Additional Service</strong></h2>
        <input type="hidden" value="${editStatus}" id="id_editStatus"/>
        <input type="hidden" value="${viewStatus}" id="id_viewStatus"/>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./viewAddlServices">Home</a> </li>
            <li class="active">Create Additional Service</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<form role="form" class="form-validation">
              <div class="row">
              	  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Core Service</label>
                     <div class="option-group">
                     	<form:select  path="coreServCode" id="coreServListID" class="form-control form-white addlSrvcClass">
    						<form:option value="">-Select-</form:option>
    						<form:options items="${coreSrvcsList}" itemValue="servCode" itemLabel="servName"></form:options>
  					 	</form:select>
                     </div>
                    </div>
                  </div>                
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Additional Service Code</label>
                      <form:input id="addServCodeID" path="code" cssClass="form-control form-white addlSrvcClass" placeholder="Enter Additional Service Code" maxlength="25"/>
                    </div>
                  </div>
                  <div class="col-sm-4">
                    <div class="form-group">
                      <label class="control-label">Additional Service Name</label>
                      <form:input id="addServNameID" path="name" class="form-control form-white addlSrvcClass" placeholder="Enter Additional Service Name" maxlength="100"/>
                    </div>
                  </div>
              </div>
              
              <!-- END ROW -->
              <div class="row" id="srvcFeatDivId">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3 id="SrvcFeaturesHeader"></h3>
                    </div>
                    <div class="panel-content" >
                      <div class="row" style ="height:300px; overflow:auto"  id="srvcFeatContentIptvDivId">
                      </div>
                      <div class="row" style ="height:auto; overflow:auto"  id="srvcFeatContentVoipDivId">
                      </div>
                    </div>
                    
                  </div>
                </div>
              </div> 
              <!-- END ROW -->
              <div class="row hideClass">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Service Parameters (Provisioning / Chargeable)</h3>
                    </div>
                    <div class="panel-content">                        
                      <div class="row">
                        <div class="col-sm-12 disable-search">
                          <table id="parametersTable" class="table table-alt">
                            <thead>
                              <tr>
                                <th style="width:10%;">Code</th>
                                <th style="width:17%;">Label</th>
                                <th style="width:17%;">Name</th>                               
                                <th style="width:10%;">Value</th>                               
                                <th style="width:15%;">Type</th>                               
                              </tr>
                            </thead>
                            <tbody>
                            <c:choose>
  								<c:when test="${editStatus == 'viewOnly'}">
                              		<c:forEach items="${addlSrvcParamDTOList}" var="addlsrvcs" varStatus="status">
			                    		<tr>                    
			                    			<td>${addlsrvcs.paramCode}</td>
			                    			<td>${addlsrvcs.paramDefaultLabel}</td>
			                    			<td>${addlsrvcs.paramName}</td>
			                    			<td>${addlsrvcs.paramValue}</td>
			                    			<td>${addlsrvcs.paramType}</td>
			                    		</tr>
			                  		</c:forEach>
			                  	</c:when>
			              	</c:choose>
                          </table>
                        </div>
                      </div>
                      <!-- END ROW INNER--> 
                      
                    </div>
                  </div>
                </div>
              </div>
              <!-- END ROW -->
            
             <c:if test="${fn:length(srvcFeatList) gt 0}">
			  
                <div class="row srvcF">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Service Features</h3>
                    </div>
                    <div class="panel-content">  
                    <c:choose>
                    <c:when test="${editChannels eq 'edit'}">
                    <div class="row"  style ="height:300px; overflow:auto">
                    <c:forEach var="srvcFaturesAll" items="${srvcFaturesAll}" varStatus="loop">
                        <c:choose>
                        <c:when test="${srvcFaturesAll.selectedVal eq 'selected'}">
                        <div class=" col-sm-3">
                         <input type="checkbox" class=" srvcFeatCheckBoxClass" value="${srvcFaturesAll.featurecode}" checked="checked">${srvcFaturesAll.featurename} 
                       </div>
                        </c:when>
                        <c:otherwise>
                        <div class=" col-sm-3">
                         <input type="checkbox" class="srvcFeatCheckBoxClass" value="${srvcFaturesAll.featurecode}" >${srvcFaturesAll.featurename} 
						</div>                       
						</c:otherwise>
                        </c:choose>
                        
                     </c:forEach>
                     </div>
                    </c:when>
                    <c:otherwise>
                    <div class="row">
                        <c:forEach var="srvcFeat" items="${srvcFeatList}" varStatus="counter">
                        <label class="col-sm-2">${srvcFeat.featurename}</label>
                        </c:forEach>
                      </div>
                    </c:otherwise>
                    </c:choose>                     
                      
                      <!-- END ROW INNER--> 
                      
                    </div>
                  </div>
                </div>
              </div>
            </c:if>
              <!-- END ROW -->
			  <div class="row hideClass">
                <div class="col-sm-6">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Tax Definitions</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
                        <div class="col-sm-12 disable-search">
                          <table id="taxesTable" class="table table-alt">
                            <thead>
                              <tr>
                                <th>Code</th>
                                <th>Tax Percentage</th>                             
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${taxList}" var="srvctax" varStatus="status">
			                    <tr>                    
			                    	<td>${srvctax.taxCode}</td>
			                    	<td>${srvctax.taxPerc}</td>
			                    </tr>
			                  </c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <c:if test="${editChannels eq 'edit'}">
                 <div class="col-sm-6">
	                <div class="col-sm-6"> <button type="button" class="btn btn-embossed btn-primary" id="editChannel_button_id">Update Channels </button> </div>
                </div>
                </c:if>
                <div class="col-sm-6">
	                <div class = "ftaCheckBoxClassDiv"><input type="checkbox" class = "ftaCheckBoxClass"/> FTA Package? </div>
		         	<div id="dialog" title="Free TO Air">
						<p>Are You Sure <br> You Want To Declare This Package As Free To Air?</p>
					</div>
                </div>
              </div>
              <!-- END ROW -->
                       
              	<input type="button" value="Submit" name="Submit" id="saveID" class="btn btn-embossed btn-primary saveClass hiddenClass" />
				<input type="button" value="Cancel" name="Cancel" id="cancelID" class="btn btn-embossed btn-danger hiddenClass" />
			  </form>
			  <form action="./corpusPackageSaveCall" id="corposeCallForm"  method="get">
			  <input type="hidden" name="srvcCode" value="${addlServieForm.code}">
			  <input type="hidden" name="effFrom" value="${addlServieForm.effectiveFrom}">
			  </form>
			  
			  
			  <form action="./updateChannel" id="updateChannel_form_id"  method="GET">
			  <input type="hidden" name="srvcCode" value="${addlServieForm.code}">
			  <input type="hidden" name="effFrom" value="${addlServieForm.effectiveFrom}">
			   <input type="hidden" name="channelsEffFrom" id = "channelsEffFrom_Id">
			   <input type="hidden" name="srvcFeaturesCodes" id = "srvcFeaturesCodes">
			  </form>
			  
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
  									<!-- View Services -->
   <!-- BEGIN MAIN CONTENT -->
  <div class="main-content viewSrvcs"> 
	<!-- <div id="id_container" class="error" style="margin: auto;width: 20%"><ol id="id_ol"></ol></div> -->
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
    
      <div class="page-title">
        <h2><strong>Additional Services</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="./viewAddlServices">Home</a> </li>
            <li class="active">Additional Services</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<form role="form" class="form-validation">
			 <div class="row">
				 <div class="col-sm-12">
	             	<div style="margin: auto;width: 20%"><label id="id_msg">${message}</label></div>
	             </div>
              </div>
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-content">
                       <div class="row">
						 <div class="col-sm-4 ">
							<div class="option-group form-group">
						    	<button onclick="location.href='./additionalServicesCreation';" id="addsvrcID" class="btn btn-embossed btn-primary " type="button"><i class="icon-plus"></i>Add Additional Service</button></div>
						 	</div>
					   </div>                       
                       <div class="row" style="padding-top: 10px">
                        <div class="col-sm-12">
                          <table id="addlSrvcsTable" class="table table-alt">
                            <thead>
                              <tr>
                              	<th style="width:5%;">SNo</th>
                                <th style="width:20%;">Core Service Name</th>
                                <th style="width:15%;">Service Code</th>
                                <th style="width:20%;">Service Name</th>
                                <th style="width:2%;"></th>
                                <th style="width:5%;"></th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${addlServcList}" var="addlServcs" varStatus="loop">
                   			 <tr>
                    			<td>${loop.count}</td>
                    			<td>${addlServcs.coreSrvcName}</td>
                    			<td>${addlServcs.srvcCode}</td>
                    			<td style="word-break:break-all;">${addlServcs.srvcName}</td>
                    			<td><a href="./editAdditionalService?coreSrvcCode=${addlServcs.coresrvcCode}&srvcCode=${addlServcs.srvcCode}&effFrom=${addlServcs.effectivefrom}">View</a></td>
                    			<c:choose>
                    			<c:when test="${addlServcs.coreSrvcName == 'IPTV' && fn:toUpperCase(addlServcs.partnerCode) == 'APSFL'}">
                    			<td><a href="./editAdditionalService?coreSrvcCode=${addlServcs.coresrvcCode}&srvcCode=${addlServcs.srvcCode}&effFrom=${addlServcs.effectivefrom}&editChannels=edit">Edit Channels</a></td>
                   				</c:when>
                   				<c:otherwise>
                   				<td> </td>
                   				</c:otherwise>
                   				</c:choose>
                   				
                   			 </tr>
                    		</c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                      <!-- END ROW INNER--> 
                    </div>
                  </div>
                </div>
              </div>
              <!-- END ROW -->
			  </form>
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
  </form:form> 
</section>
</html>