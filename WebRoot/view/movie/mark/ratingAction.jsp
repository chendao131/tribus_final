<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'ratingAction.jsp' starting page</title>
    
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
 	 		 	$("#submit_btn").click(function() {  
 	 		 		var ratingInput = $("input#rating_input").val();
			 		var dataString = 'rating='+ ratingInput;
			      	alert(dataString);
					$.ajax({  
					  type: "POST",  
					  url: "/tribus_spring/movie/ratingSubmit/1/1/1.action",  
					  data: dataString,  
					  success: function() {  
					  }  
					});  	      	
			  	});  
	    });

	</script>
	
  </head>
  
  <body>
	<form name="rating" id="rating" action=""> 
	<fieldset> 
	    <label for="rating_input" id="rating_input">rating</label>  
	    <input type="text" name="rating_input" id="rating_input" size="30" value="" class="text-input" />  
	    <input type="submit" name="submit" class="button" id="submit_btn" value="submit" />  
	 </fieldset>
	</form> 
  </body>
</html>
