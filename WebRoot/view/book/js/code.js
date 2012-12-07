$(function() {
	$('#light_box_wrapper').fadeOut();
});

function showPop(){
	var winH = $(document).height();
	var binH = $(window).height();
	var windowWidth = $(window).width();
	var contentWidth = $("#light_box_wrapper").width();
	
	var contentLeft = (windowWidth-contentWidth)/2
	
	var scrollTop = $(window).scrollTop()+175;
	
	$('#popup_layer').css('height',winH+'px');
	$('#popup_layer').css('opacity',0.8);
	
	$('#popup_layer').fadeIn(500);
	
	$('#light_box_wrapper').css({'top' : scrollTop+'px'});
	$('#light_box_wrapper').css({'left' : contentLeft+'px'});
	$('#light_box_wrapper').fadeIn(500);
}
function closePop(){
	$('#popup_layer').fadeOut(500);
	$('#light_box_wrapper').fadeOut(500);
}