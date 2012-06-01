<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'markAction.jsp' starting page</title>
    
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
		 	$("#submit_watched").click(function() {  
		 		alert("test");
		 		var dataString = 'watched=0';
		      	alert(dataString);
				$.ajax({  
				  type: "POST",  
				  url: "/tribus_spring/movie/markSubmit/1/1.action",  
				  data: dataString,  
				  success: function() {  
				  }  
				});  	      	
		  	});  
		});
	</script>

  </head>
  
  <body>
	<form name="mark" action=""> 
	<fieldset> 
	    <label for="mark_input" id="mark_input">mark</label>  
	    <input type="submit" name="watched" class="button" id="submit_watched" value="watched"/> 
	    <input type="submit" name="liked" class="button" id="submit_liked" value="liked" /> 
	    <input type="submit" name="graded" class="button" id="submit_graded" value="graded" /> 
	 </fieldset>
	</form> 
  </body>
</html>
