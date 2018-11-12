<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<html>
<style>
.SumoSelect > .CaptionCont{
    width: 182px !important;
}
</style>
<body>
<form:form method="POST" modelAttribute="revenueSharingForm" id="register-form">
  <!-- BEGIN MAIN CONTENT -->
  <div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2>Create Revenue Sharing<strong> Template</strong></h2>
        <div class="breadcrumb-wrapper">
          <ol class="breadcrumb">
            <li><a href="dashboard.html">Home</a> </li>
            <li class="active">Revenue Sharing Template</li>
          </ol>
        </div>
      </div>
      <div class="row main-row">
      <div style="margin: auto;width: 20%"><label id="id_msg"></label></div>
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<form role="form" class="form-validation">
			 <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Revenue Sharing</h3>
                    </div>
					<fieldset>
					 <!-- <legend>Revenue Sharing</legend> -->
                    <div class="panel-content">
                    <div class="row">
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Template Name</label>
								 <form:select  path="rstmplCode" id="id_tempNameMast" cssClass="form-control form-white tClass">
    								<form:option value="">-Select-</form:option>
    							 	<form:options items="${templatesLov}"></form:options>
  							  	 </form:select>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">&nbsp;</label>
								<div class="option-group">
									<button id="updatebtn_id"class="btn btn-embossed btn-primary" type="button">Update</button>
									<button id="newbtn_id" class="btn btn-embossed btn-primary" type="button">New</button>
								</div>
							</div> 
						</div>                                 				  
					  </div>	
					   <div class="row hide1">
						<div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Template Code</label>
								<input type="text" id="id_tempCode" name="tempCode" class="form-control form-white templateClass splclass" placeholder="Enter Template Code" maxlength="20" required>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Template Name</label>
								<input type="text" id="id_tempName" name="tempName" class="form-control form-white templateClass splclass" placeholder="Enter Template Name" maxlength="200" required>
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">No. of Partners</label>
								<input type="number" id="id_noOfPartners" name="noOfPartners" class="form-control form-white templateClass numeric" maxlength="2" min="1" placeholder="Enter No. Of Partners" required>
							</div>  
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label class="control-label">Template Type</label>
								 <form:select  path="rstmplCode" id="id_templType"  cssClass="form-control form-white tClass templateClass">
    								<form:option value="">-Select-</form:option>
    							 	<form:options items="${templateTypesLov}"></form:options>
  							  	 </form:select>
							</div> 
						</div>
						
						<div class="col-sm-2 cpeHide">
							<div class="form-group">
								<label class="control-label">CPE Charges</label>
								 <form:select  path="rstmplCode" id="id_cpeCharges" cssClass="form-control form-white tClass chargeTypeClass " multiple="multiple">
    							 	<form:options items="${chargeCodesList}"></form:options>
  							  	 </form:select>
							</div> 
						</div>
						
						<div class="col-sm-1">
							<div class="form-group">
								<label class="control-label">&nbsp;</label>
								<div class="option-group">
									<button id="savebtn_id" class="btn btn-embossed btn-primary" type="button">Save</button>
								</div>
							</div> 
						</div>                                 				  
					  </div>
					  <div class="row hide2">
					  <div class="col-sm-3">
							<div class="form-group">
								<label class="control-label">Region</label>
								<select id="id_region" class="form-control form-white tempClass tenantClass">
								</select>
							</div>
						</div>
					  </div>				  
					  <div class="row hide2">
                        <div class="col-sm-6 disable-search">
                          <table id="id_table" class="table table-alt">
                            <thead>
                              <tr>
                               <th>SNo.</th>
                                <th>Tenants</th>
                                <th>Percentage</th>                              
                              </tr>
                            </thead>
                            <tbody>
                            </tbody>
                          </table>
                        </div>
                        <button id="id_revsubmit" class="btn btn-embossed btn-primary hide2" type="button">Submit</button>
              			<!-- <button class="btn btn-embossed btn-danger hide2" type="button">Cancel</button> -->
                      </div>
                    </div>
					</fieldset>
                  </div>
                </div>
              </div>
              <!-- END ROW -->
              <!-- END ROW -->
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
</body>
</html>