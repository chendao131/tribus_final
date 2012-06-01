<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <base href="<%=basePath%>">
    <title>My JSP 'editAction.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript" src="../../view/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){  
		 	$(".button1").click(function() {  
/* 		  		$(".edit_input_edit_input").hide();  */
				var movieNameOriginal = $("input#movieNameOriginal").val();  
				var movieNameEn = $("input#movieNameEn").val();
				var movieNameChn = $("input#movieNameChn").val();
				var movieNameAlias = $("input#movieNameAlias").val();  
				var movieIMDB = $("input#movieIMDB").val();
				var movieWeb = $("input#movieWeb").val();
				var movieRegion = $("input#movieRegion").val();  
				var movieLanguage = $("input#movieLanguage").val();
/* 				var directorStarId = $("input#directorStarId").val();
				var leadStarId = $("input#leadStarId").val(); 
				var tagId = $("input#tagId").val();  
				var userId = $("input#userId").val();  */
/* 		    	if (edit_input == "") {  
		      		$("label#edit_input_error").show();  
		      		$("input#edit_input").focus();  
		      	return false; 
		      	}  */
		      	// input valid
/*  		      	var dataString = 'movieNameOriginal='+ movieNameOriginal+'&movieNameEn='+movieNameEn+
		      				'&movieNameChn='+movieNameChn;  */
		      	var dataString = "movieNameOriginal="+ movieNameOriginal;
		      	alert(dataString);
				$.ajax({  
				  type: "POST",  
				  url: "movie/editSubmit.action",  
				  data: dataString,  
				  success: function() { 
				  alert(data); 
				  }  
				});  	      	
		  	});  
		});
	</script>
  </head>
  
  <body>
<%--  	<form name="edit" action=""> 
	<fieldset> 
	    <label for="edit_input" id="edit_input">edit</label>  
	    <input type="text" name="edit_input" id="edit_input" size="30" value="" class="text-input" />  
	    <label class="edit_input_error" for="edit_input" id="edit_input_error">This field is required.</label>  
	    <input type="submit" name="submit" class="button" id="submit_btn" value="submit" />  
	    
	    <br>
	   	<label for="edit_input" id="edit_input">edit1</label>  
	    <input type="text" name="edit_input" id="edit_input" size="30" value="" class="text-input" />  
	    <label class="edit_input_error" for="edit_input" id="edit_input_error">This field is required.</label>  
	    <input type="submit" name="submit" class="button1" id="submit_btn" value="submit1" onclick="test()" />
	    
	 </fieldset>
	</form>   --%>
  
  
  
	<form name="edit" action="movie/editSubmit.action" method="POST"> 
	<fieldset> 
		<legend > movieEditForm </legend> 
		<label >movieIid: </label> 
		<input type="text" id="movieId" name="movieId" value="${movie.movieId}" >
		<br>
		<label >movieNameOriginal: </label> 
		<input type="text" id="movieNameOriginal" name="movieNameOriginal" value="${movie.movieNameOriginal}" > 
		<br>
		<label >movieNameEn: </label> 
		<input type="text" id="movieNameEn" name="movieNameEn" value="${movie.movieNameEn}" > 
		<br>
		<label >movieNameChn: </label> 
		<input type="text" id="movieNameChn" name="movieNameChn" value="${movie.movieNameChn}" > 
		<br>
		<label >movieNameAlias: </label> 
		<input type="text" id="movieNameAlias" name="movieNameAlias" value="${movie.movieNameAlias}" > 
		<br>
		<label >movieIMDB: </label> 
		<input type="text" id="movieIMDB" name="movieIMDB" value="${movie.movieIMDB}" > 
		<br>
		********
		<c:forEach  items="${movie.stars}"  var="stars"  >
    		<c:out  value="${stars.starName}"  />
		</c:forEach> 
		********
<%-- 		<label >directorStarId: </label> 
		<input type="text" id="directorStarId" name="directorStarId" value="${movie.stars.get(0).starName}" >
		<br>
		<label >tagId: </label> 
		<input type="text" id="tagId" name="tagId" value="${movie.tags.get(0).tagName}" >
		<br> --%>
		<label >movieWeb: </label> 
		<input type="text" id="movieWeb" name="movieWeb" value="${movie.movieWeb}" > 
		<br>
		<label >movieRegion: </label> 
		<input type="text" id="movieRegion" name="movieRegion" value="${movie.movieRegion}" > 
		<br>
		<label >movieLanguage: </label> 
		<input type="text" id="movieLanguage" name="movieLanguage" value="${movie.movieLanguage}" >
		<br>
		<label >movieDate: </label> 
		<input type="text" id="movieDate" name="movieDate" value="${movie.movieDate}" >
		<br>
		<label >movieTime: </label> 
		<input type="text" id="movieTime" name="movieTime" value="${movie.movieTime}" >
		<br>
		<label >movieView: </label> 
		<input type="text" id="movieView" name="movieView" value="${movie.movieView}" >
		<br>
		<label >movieDemo: </label> 
		<input type="text" id="movieDemo" name="movieDemo" value="${movie.movieDemo}" > 
		<br>
		<label >movieBrief: </label> 
		<input type="text" id="movieBrief" name="movieBrief" value="${movie.movieBrief}" > 
		<br>
		<label >recordDate: </label> 
		<input type="text" id="recordDate" name="recordDate" value="${movie.recordDate}" >
		<br>
		<input type="submit" name="submit" class="button" id="submit_btn" value="submit" />
	 </fieldset>
	</form> 
  </body>
</html>
