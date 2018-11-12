<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="admin-themes-lab">
<meta name="author" content="themes-lab">
<link rel="shortcut icon" href="./resources/images/favicon.png" type="image/png">
<title>:: Andhra Pradesh State FiberNet Ltd ::</title>
<script src="./resources/js/core-service.js"></script>
</head>
<body>
<form:form method="POST" modelAttribute="coreServieForm" id="register-form">
<section>
  <!-- BEGIN MAIN CONTENT -->
  <div class="main-content createSrvcs"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong> Core Services</strong></h2>
        <input type="hidden" value="${editStatus}" id="id_editStatus"/>
        <input type="hidden" value="${viewStatus}" id="id_viewStatus"/>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="dashboard.html">Home</a> </li>
            <li class="active">Create Core Services</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<form role="form" class="form-validation">
              <div class="row">                
                  <div class="col-sm-3">
                    <div class="form-group">
                      <label class="control-label">Service Code</label>
                      <form:input id="coresrvcCodeID" path="code" cssClass="form-control form-white coreSrvcClass" placeholder="Enter Service Code" maxlength="25"/>
                    </div>
                  </div>
                  <div class="col-sm-3">
                    <div class="form-group">
                      <label class="control-label">Service Name</label>
                      <form:input id="coresrvcNameID" path="name" cssClass="form-control form-white coreSrvcClass" placeholder="Enter Service Name" maxlength="100"/>
                    </div>
                  </div>
                  <div class="col-sm-3">
                    <div>
                      <label class="control-label">Service Provider Code</label>
                      <form:input id="provCodeID" path="provCode" cssClass="form-control form-white coreSrvcClass" placeholder="Enter Service Provider Code" maxlength="50"/>
                    </div>
                  </div>
                  <div class="col-sm-3">
                    <div>
                      <label class="control-label">Multiple Services Allowed
                      	<form:checkbox id="isMultipleID" path="multiple" />
                      </label>
                    </div>
                    <div>
                      <label class="control-label">One Time Package Allowed
                      	<form:checkbox id="isOneTimeID" path="oneTime" />
                      </label>
                    </div>
                  </div>
              </div>
              <!-- END ROW -->
              <div class="row">
                <div class="col-sm-6">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Provisioning Target</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Type</label>
                            <div class="option-group">
                              <form:select  path="provParams.type" id="provTypeID" cssClass="form-control form-white coreSrvcClass">
    											<form:option value="">-Select-</form:option>
    											<form:options items="${provTargetTypeLov}"></form:options>
  							  </form:select>
                            </div>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Value</label>
                            <form:input id="provValID" path="provParams.value" cssClass="form-control form-white coreSrvcClass" placeholder="Value" maxlength="25"/>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>GL Definitions</h3>
                    </div>
                    <div class="panel-content">
                      <div class="row">
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Name</label>
                            <div class="option-group">
                              	<form:select  path="glCode" id="glCodeID" cssClass="form-control form-white coreSrvcClass">
    											<form:option value="">-Select-</form:option>
    											<form:options items="${glCodeLov}"></form:options>
  								</form:select>
  								 <%-- <select id="glCodeID" class="form-control form-white coreSrvcClass" name="glCode">
  								  <option value="">-Select-</option>
							      <c:forEach var="item" items="${glCodeLov}">
							        <option value="${item.key}">${item.value}</option>
							      </c:forEach>
							    </select> --%>
                            </div>
                          </div>
                        </div>
                        <div class="col-sm-6">
                          <div class="form-group">
                            <label class="control-label">Code</label>
                            <!-- <input type="text" id="id_glName" name="glCode" class="form-control form-white" readonly="readonly"/> -->
                            <form:input id="id_glName" path="glCode" cssClass="form-control form-white" readonly="true"/>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- END ROW -->
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Service Parameters Addition</h3>
                    </div>
                    <div><font color="red"><label id="id_srvcParamErr"></label></font></div>
                    <div class="panel-content">
                      <div class="row disableView">
                        <div class="col-sm-3">
                        <input type="hidden" id="id_srvcParamEditStatus"/>
                          <div class="form-group">
                            <label class="control-label">Code</label>
                            <input type="text" id="paramCodeID" name="paramCode" class="form-control form-white srvcClass" placeholder="Code" maxlength="255"/>
                          </div>
                        </div>
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Name</label>
                            <input type="text" id="paramNameID" name="paramName" class="form-control form-white srvcClass" placeholder="Name" maxlength="255">
                          </div>
                        </div>
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Parameter Type</label>
                            <div class="option-group">
                              <select id="paramTypeID" name="paramType" class="form-control form-white srvcClass">
		                        <option value="">-Select-</option>
		                        <option>PROVISIONING</option>
		                        <option>CHARGEABLE</option>
		                        <option>BOTH</option>
		                      </select>
                            </div>
                          </div>
                        </div>
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Data Type</label>
                            <div class="option-group">
                              	<form:select id="dataTypeID" path="servParams[0]" class="form-control form-white srvcClass">
    			  					<form:option value="">-Select-</form:option>
    								<form:options items="${dataTypeLov}"></form:options>
  				  				</form:select>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- END ROW INNER-->
                      <div class="row disableView">
                      <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Label</label>
                            <input type="text" id="paramLabelID" name="label" class="form-control form-white srvcClass" placeholder="Label" maxlength="255">
                          </div>
                        </div>
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Value Type</label>
                            <div class="option-group">
                               <select id="valueTypeID" name="valueType" class="form-control form-white srvcClass">
		                          <option value="">-Select-</option>
		                          <option value="TEXTBOX">Single Literal</option>
		                          <option value="LISTBOX">LOV</option>
		                        </select>
                            </div>
                          </div>
                        </div>
                        <div class="col-sm-3 lovClass">
                          <div class="form-group">
                            <label class="control-label">LOV</label>
		                        <form:select id="lovID" path="" cssClass="form-control form-white">
    											<form:option value="">-Select-</form:option>
    											<form:options items="${lovs}"></form:options>
  							  	</form:select>
                          </div>
                        </div>
                        <div class="col-sm-3 valueClass">
                          <div class="form-group">
                            <label class="control-label">Value</label>
                            <input type="text" id="paramValueID" name="Value" class="form-control form-white" placeholder="Value" maxlength="255">
                            	<select id="selParamValueID" class="form-control form-white">
		                        </select>
                          </div>
                        </div>
                      </div>
                      <div class="row disableView">
                          <div class="form-group">
                              <button id="addsvrcID" class="btn btn-embossed btn-primary" type="button"><i class="icon-plus"></i>Add Service Parameters</button>
                          </div>
                      </div>
                      <!-- END ROW INNER-->
                      
                      <div class="row">
                        <div class="col-sm-12 disable-search">
                          <table id="svrcParamTableID" class="table table-alt">
                            <thead>
                              <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Label</th>
                                <th>Value</th>  
                                <th class="disableView">Action</th>                             
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${srvcParamList}" var="srvcParam" varStatus="status">
			                    <tr>                    
			                    	<td>${srvcParam.code}</td>
			                    	<td>${srvcParam.name}</td>
			                    	<td>${srvcParam.paramType}</td>
			                    	<td style="display: none;">${srvcParam.dataType}</td>
			                    	<td>${srvcParam.label}</td>
			                    	<td style="display: none;">${srvcParam.paramValueType}</td>
									<td style="display: none;">${srvcParam.prmLOVName}</td>
			                    	<td>${srvcParam.value}</td>
			                    	<td style="display: none;">
									<select>
										<c:forEach items="${srvcParam.prmLOVList}" var="list">
											<option value="${list.key}">${list.value}</option>
  										 </c:forEach>
									</select>
									</td>
			                    	<td class="disableView">
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-dark srvcParamClassEdit" data-toggle="tooltip" data-rel="tooltip" data-original-title="Edit"><i class="icon-note"></i></a>
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-danger srvcParamClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>
			                    	</td>
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
              <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Tax Definitions</h3>
                    </div>
                    <div><font color="red"><label id="id_taxErr"></label></font></div>
                    <div class="panel-content">
                      <div class="row disableView">
                        <div class="col-sm-3">
                          <div class="form-group">
                            <input type="hidden" id="id_taxEditStatus"/>
                            <label class="control-label">Tax Code</label>
                            <div class="option-group">
                              <form:select  path="taxParams[0]" id="taxCodeID" cssClass="form-control form-white taxClass">
			    			  	<form:option value="">-Select-</form:option>
			    				<form:options items="${taxCodeLov}"></form:options>
			  				  </form:select>
                            </div>
                          </div>
                        </div>
                        <!-- <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">Percentage</label>
                            <input type="text" name="Percentage" class="form-control form-white" placeholder="Percentage">
                          </div>
                        </div> -->
						<div class="col-md-3">
                          <div class="form-group">
                            <label class="form-label">Tax Percentage</label>
                            <input type="text" id="taxEffDtID" class="form-control form-white" disabled="disabled">
                          </div>
                        </div>
                        <div class="col-sm-3">
                          <div class="form-group">
                            <label class="control-label">&nbsp;</label>
                            <div class="option-group">
                              <button id="addTaxID" class="btn btn-embossed btn-primary" type="button"><i class="icon-plus"></i>Add</button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- END ROW INNER-->
                      
                      <div class="row">
                        <div class="col-sm-12 disable-search">
                          <table id="taxTableID" class="table table-alt">
                            <thead>
                              <tr>
                                <th>Code</th>
                                <th>Tax Percentage</th>
								<th class="disableView">Action</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${taxParamsList}" var="taxParam" varStatus="status">
			                    <tr>                    
			                    	<td>${taxParam.taxCode}</td>
			                    	<td>${taxParam.taxPerc}</td>
			                    	<td class="disableView">
			                    		<a href="javascript:void(0);" class="btn btn-sm btn-danger taxCodeClass" data-toggle="tooltip" data-rel="tooltip" data-original-title="Delete"><i class="icon-trash"></i></a>
			                    	</td>
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
              <button id="submitID" class="btn btn-embossed btn-primary disableView" type="button">Submit</button>
              <button id="cancelID" class="btn btn-embossed btn-danger disableView" type="button">Cancel</button>
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
        <h2><strong>Core Services</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="home">Home</a> </li>
            <li class="active">Core Services</li>
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
	             	<div style="margin: auto;width: 20%"><label id="id_msg"></label></div>
	             </div>
              </div>
              <div class="row hideClass">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-content">
                      <div class="option-group">
                        <button onclick="location.href='./scsHome';" class="btn btn-embossed btn-primary" type="button"><i class="icon-plus"></i>Add Core Service</button>
                       </div>                        
                       <div class="row" style="padding-top: 10px">
                        <div class="col-sm-12">
                          <table id="parametersTable" class="table table-alt">
                            <thead>
                              <tr>
                              	<th style="width:5%;">SNo</th>
                                <th style="width:15%;">Service Code</th>
                                <th style="width:15%;">Service Prov Code</th>
                                <th style="width:20%;">Service Name</th>
                                <th style="width:2%;"></th>
                                <!-- <th style="width:2%;"></th> -->                                                           
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${coreServicesList}" var="coreServices" varStatus="status">
                   			 <tr>
                    			<td>${status.count}</td>
                    			<td>${coreServices.servCode}</td>
                    			<td>${coreServices.servProvCode}</td>
                    			<td>${coreServices.servName}</td>
                    			<td><a href="./editCoreService?srvcCode=${coreServices.servCode}&effFrom=${coreServices.effectivefrom}">View</a></td>
                    			<%-- <td><a href="./modifyCoreService?srvcCode=${coreServices.servCode}">Edit</a></td> --%>
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
</section>
<a href="#" class="scrollup"><i class="fa fa-angle-up"></i></a> 
</form:form>
</body>
</html>