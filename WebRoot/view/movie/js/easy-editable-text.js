$(document).ready(function(){
	
	$('.edit').click(function(){
		$(this).hide();
		prevContent = $(this).prev().html();
		$(this).prev().hide();
		$(this).next().show();
		//$(this).next().select();
		$(this).next().val(prevContent);
	});
	
	
	$('.edit_tools input[type="text"]').blur(function() {  //alert(prevContent)
         if ($.trim(this.value) == ''){  
			 this.value = (this.defaultValue ? this.defaultValue : '');  
		 }
		 else{
			 $(this).prev().prev().html(this.value);
		 }
		 
		 $(this).hide();
		 $(this).prev().show();
		 $(this).prev().prev().show();
     });
	  
	  $('.edit_tools input[type="text"]').keypress(function(event) {
		  if (event.keyCode == '13') {
			  if ($.trim(this.value) == ''){  
				 this.value = (this.defaultValue ? this.defaultValue : '');  
			 }
			 else
			 {
				 $(this).prev().prev().html(this.value);
			 }
			 
			 $(this).hide();
			 $(this).prev().show();
			 $(this).prev().prev().show();
		  }
	  });
		  
});