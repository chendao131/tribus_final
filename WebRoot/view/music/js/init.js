$(function(){
	
	$('.slider').mobilyslider({
		content: '.sliderContent',
		children: 'div',
		transition: 'horizontal',
		animationSpeed: 800,
		autoplay: true,
		autoplaySpeed: 6000,
		pauseOnHover: false,
		bullets: false,
		arrows: true,
		arrowsHide: false,
		prev: 'prev',
		next: 'next',
		animationStart: function(){},
		animationComplete: function(){}
	});
	
});
