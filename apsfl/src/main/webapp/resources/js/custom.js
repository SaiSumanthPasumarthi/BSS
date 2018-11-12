 //****************** CUSTOMIZED JAVASCRIPT **********************//
 $('.main-content').show();
    NProgress.start();
    setTimeout(function() { NProgress.done(); $('.fade').removeClass('out'); }, 1000);
	
//menu
	jQuery(document).ready(function () {
    jQuery('header nav').meanmenu();
});
//theme-colos-script
$(document).ready(function(){
	var color = $.cookie("color-theme");
	
	if(color === undefined){
		$("body").addClass("theme-blue");
		$('.theme-blue').addClass("active");
	}else{
		$("body").addClass(color);
		$('.'+color).addClass("active");
	}
	
    $(".btn-color-theme").click(function(){
		$("body").removeClass('theme-light-green theme-blue theme-yellow theme-tealgreen');
		$("body").addClass($(this).attr('id'));
		$(".btn-color-theme").removeClass('active');
		$(this).addClass("active");	
		$.cookie("color-theme", $(this).attr('id'),{path: "/"});
    });	
});

// Menu SlideDown Effect
$(document).ready(
		function() {

			$('.dataTabMoreCols').dataTable({
				"sPaginationType" : "full_numbers",
				"scrollX" : true,
				"scrollY" : false,
				"responsive" : true,
				"bAutoWidth" : true,
				"drawCallback" : function(settings, json) {
					$('.dataTables_scrollBody thead tr').css({
						visibility : 'collapse'
					});
				}
			});

			$('.dataTalProvising').dataTable({
				"scrollY" : "300px",
				"scrollCollapse" : true,
				"paging" : false,
				"responsive" : true,
				"bAutoWidth" : true,
				"drawCallback" : function(settings, json) {
					$('.dataTables_scrollBody thead tr').css({
						visibility : 'collapse'
					});
				}
			});

			$('.dataTab').dataTable({
				"sPaginationType" : "full_numbers",
			});

			$('.menu li').hover(
					function() {
						$('.submenu', this).slideUp(0).stop(true, true)
								.slideDown(500);
					},
					function() {
						$('.submenu', this).css("display", "block").stop(true,
								true).delay(1).slideUp(0);
					});

			$(".menu li a").click(function() {
				// $(this).next('.subdiv').slideToggle();
			});

		});