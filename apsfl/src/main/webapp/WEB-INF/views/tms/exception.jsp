<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
  <div class="main-content"> 
    <!-- BEGIN PAGE CONTENT -->
    <div class="page-content page-width">
      <div class="page-title">
        <h2><strong>Error Exception Page</strong></h2>
      </div>
      <div class="row main-row">
        <div class="col-lg-12">
          <div class="panel main-panel">
            <div class="panel-content main-panel-content">
			<form role="form" class="form-validation">
			<div class="row">
            <div class="col-lg-12">
            <div class="panel-content main-panel-content">
			    <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Error Message</h3>
                    </div>
                    <div class="panel-content">
                    	<div>
							<center><font face="Times New Roman" size="4px" color="Red">${message}</font></center>
						</div>
                    </div>
                  </div>
                </div>
              </div>
			  	<div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Error Message Localized</h3>
                    </div>
                    <div class="panel-content">
                     	<div>
							<center><font face="Times New Roman" size="4px" color="Red">${errorMessageLocalized}</font></center>
						</div>
                    </div>
                  </div>
                </div>
              </div>
			<div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Cause Error</h3>
                    </div>
                    <div class="panel-content">
                   	<div>
							<center><font face="Times New Roman" size="4px" color="Red">${errorCause}</font></center>
						</div>
                    </div>
                  </div>
                </div>
              </div>
			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Stack Trace Error</h3>
                    </div>
                    <div class="panel-content">
                      <div>
							<center><font face="Times New Roman" size="4px" color="Red">${errorStackTrace}</font></center>
						</div>
					  </div>
                    </div>
                  </div>
                </div>
			  <div class="row">
                <div class="col-sm-12">
                  <div class="panel">
                    <div class="panel-header bg-light">
                      <h3>Suppressed Error</h3>
                    </div>
                    <div class="panel-content">
                     		<div>
							<center><font face="Times New Roman" size="4px" color="Red">${errorSuppressed}</font></center>
						</div>		  
					  </div>
                    </div>
                  </div>
                </div>
              </div>
			  </div>
			  </div> 
			  </form>
			  </div><!-- /.tab-content -->
              </div>
            </div>
            </div>
          </div>
        </div>