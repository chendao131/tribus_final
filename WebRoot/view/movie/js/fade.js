$(function(){
	
	$('.slider').mobilyslider({
		content: '.sliderContent',
		children: 'div',
		transition: 'fade',
		animationSpeed: 500,
		autoplay: true,
		autoplaySpeed: 3000,
		pauseOnHover: false,
		bullets: false,
		arrows: false,
		arrowsHide: false,
		prev: 'prev',
		next: 'next',
		animationStart: function(){},
		animationComplete: function(){}
	});
	
});
