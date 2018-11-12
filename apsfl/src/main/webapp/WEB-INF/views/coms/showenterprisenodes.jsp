<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="main-content">
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content page-width">
		<div class="page-title">
			<h2>
				Enterprise<strong> Node Customer</strong>
			</h2>
			<c:if test="${not empty message}"> <center id="comsErrorMsg" ><font color='green' size="3">${message}</font></center></c:if>
			<c:if test="${not empty msg}"> <center id="comsErrorMsg" ><font color='red' size="3">${msg}</font></center></c:if>
			<div class="breadcrumb-wrapper">
				<ol class="breadcrumb">
					<li><a href="./">Home</a></li>
					<li class="active">Enterprise NodeCustomer</li>
				</ol>
			</div>
		</div>
		<div class="row main-row">
			<div class="col-lg-12">
				<div class="panel main-panel">
					<div class="panel-content main-panel-content">
						<div class="row m-b-5">
							<div class="col-sm-12">
								<table class="table table-alt" id="example-basic">
									<caption>
									Basic jQuery treetable Example
									</caption>
									<thead>
									<tr>
									<th>Tree column</th>
									<th>Additional data</th>
									</tr>
									</thead>
									<tbody>
									<tr data-tt-id="1">
									<td>Node 1: Click on the icon in front of me to expand this branch.</td>
									<td>I live in the second column.</td>
									</tr>
									<tr data-tt-id="1.1" data-tt-parent-id="1">
									<td>Node 1.1: Look, I am a table row <em>and</em> I am part of a tree!</td>
									<td>Interesting.</td>
									</tr>
									<tr data-tt-id="1.1.1" data-tt-parent-id="1.1">
									<td>Node 1.1.1: I am part of the tree too!</td>
									<td>That's it!</td>
									</tr>
									<tr data-tt-id="2">
									<td>Node 2: I am another root node, but without children</td>
									<td>Hurray!</td>
									</tr>
									</tbody>
									</table>
							</div>
						</div>
						<!-- END ROW INNER-->


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
<div class="clear"></div>
