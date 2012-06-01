<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'commentAction.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="../view/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){  
			$(".comment_input_error").hide(); 
		 	$(".button").click(function() {  
		  		$(".comment_input_comment_input").hide(); 
				var comment_input = $("input#comment_input").val();  
		    	if (comment_input == "") {  
		      		$("label#comment_input_error").show();  
		      		$("input#comment_input").focus();  
		      	return false; 
		      	} 
		      	// input valid
		      	var dataString = 'newComment='+ comment_input+'&movieId=1'+'&userId=1';
		      	alert(dataString);
				$.ajax({  
				  type: "POST",  
				  url: "commentSubmit.action",  
				  data: dataString,  
				  success: function() {  
				  }  
				});  	      	
		  	});  
		});
	</script>
  </head>
 
  <body>
	<form name="comment" action=""> 
	<fieldset> 
	    <label for="comment_input" id="comment_input">Comment</label>  
	    <input type="text" name="comment_input" id="comment_input" size="30" value="" class="text-input" />  
	    <label class="comment_input_error" for="comment_input" id="comment_input_error">This field is required.</label>  
	    <input type="submit" name="submit" class="button" id="submit_btn" value="submit" />  
	 </fieldset>
	</form> 
  </body>
</html>
