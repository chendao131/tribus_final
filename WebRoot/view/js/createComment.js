$(function comment_submit(){  
	$(".create_comment").click(function() {  
	var comment_title = $("input.creat_comment_txt").val();  
	var comment_content = $("input.message_comment").val();
	var dataString = 'comment_title='+ comment_title+'&comment_content='+comment_content;
		alert(dataString);
		$.ajax({  
		type: "POST",  
		url: "commentSubmit.action",  
		data: dataString,  
		success: function() {  
		}  
		});  	// .ajax  	
	});   //.button
})