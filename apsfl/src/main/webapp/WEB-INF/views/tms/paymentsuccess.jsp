<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<section>
		<!-- BEGIN MAIN CONTENT -->
		<div class="main-content">
			<!-- BEGIN PAGE CONTENT -->
			<div class="page-content page-width">
				<div class="page-title">
					<h2>Orders</h2>
					<div class="breadcrumb-wrapper">
						<ol class="breadcrumb">
							<li><a href="./">Home</a></li>
						</ol>
					</div>
				</div>
				
				<div class="row main-row">
					<div class="col-lg-12">
						<div class="panel main-panel">
							<div class="panel-content main-panel-content">
								<div class="row"><h1> Payment Success.........</h1></div>
								<div class="row">
									<label>Transaction Id :-  ${txnReferenceNO} </label> <br>
									<label>Customer Transaction Id :-  ${customerID}</label> <br>
									<label>Currency Type :-  ${currencyName} </label> <br>
									<label>Amount :-  ${txnAmount} </label> <br>
								</div>
								<div>
								    <c:if test="${not empty message}">
										<center>
											<font color='green' size="3">${message}</font>
										</center>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
				</div>
				
	</section>
	</body>