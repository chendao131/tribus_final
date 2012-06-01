<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addAction.jsp' starting page</title>
    
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
		var addMovieModel = {
			"movieNameOriginal":"",
			"movieNameEn":"",
			"movieNameChn":"",
			"movieNameAlias":"",
			"movieIMDB":"",
			"directorStarName":"",
			"leadStarName":"",
			"tagName":"",
			"movieWeb":"",
			"movieRegion":"",
			"movieLanguage":"",
			"movieDate":"",
			"movieTime":"",
			"movieView":"",
			"movieDemo":"",
			"movieBrief":"",
			"userName":"",
			"recordDate":""
		};
		
		function getAddMovieModel(){
			var addMovieModel = {};
				addMovieModel.movieNameOriginal = $("input#movieNameOriginal").val();  
				addMovieModel.movieNameEn = $("input#movieNameEn").val();
				addMovieModel.movieNameChn = $("input#movieNameChn").val();
				addMovieModel.movieNameAlias = $("input#movieNameAlias").val();  
				addMovieModel.movieIMDB = $("input#movieIMDB").val();
				addMovieModel.directorStarName = $("input#directorStarName").val();  
				addMovieModel.leadStarName = $("input#leadStarName").val();
				addMovieModel.tagName = $("input#tagName").val();
				addMovieModel.movieRegion = $("input#movieRegion").val();  
				addMovieModel.movieDate = $("input#movieDate").val();
				addMovieModel.movieTime = $("input#movieTime").val();
				addMovieModel.movieView = $("input#movieView").val();
				addMovieModel.movieDemo = $("input#movieDemo").val();  
				addMovieModel.movieBrief = $("input#movieBrief").val();
				addMovieModel.userName = $("input#userName").val();
				addMovieModel.recordDate = $("input#recordDate").val(); 
				var addMovieModelStr=JSON.stringify(addMovieModel);  
				alert(addMovieModel);
				return addMovieModel;
		}
		$(document).ready(function(){  
			$(".edit_input_error").hide(); 
		 	$(".button").click(function() {  
		  		$(".edit_input_edit_input").hide(); 
/* 				var movieNameOriginal = $("input#movieNameOriginal").val();  
				var movieNameEn = $("input#movieNameEn").val();
				var movieNameChn = $("input#movieNameChn").val();
				var movieNameAlias = $("input#movieNameAlias").val();  
				var movieIMDB = $("input#movieIMDB").val();
				var directorStarName = $("input#directorStarName").val();  
				var leadStarName = $("input#leadStarName").val();
				var tagName = $("input#tagName").val();
				var movieRegion = $("input#movieRegion").val();  
				var movieDate = $("input#movieDate").val();
				var movieTime = $("input#movieTime").val();
				var movieView = $("input#movieView").val();
				var movieDemo = $("input#movieDemo").val();  
				var movieBrief = $("input#movieBrief").val();
				var userName = $("input#userName").val();
				var recordDate = $("input#recordDate").val();   */
		    	if (edit_input == "") {  
		      		$("label#edit_input_error").show();  
		      		$("input#edit_input").focus();  
		      	return false; 
		      	} 
		      	// input valid
				$.ajax({  
				  type: "POST",  
				  url: "editSubmit.action",  
				  data: {addMovieModel:getAddMovieModel()},  
				  success: function() {  
				  }  
				});  	      	
		  	});  
		});
	</script>

  </head>
  
  <body>
	<form name="add" action="movie/addSubmit.action"> 
	<fieldset> 
		<legend > movieAddForm </legend> 
<!-- 		<label >movieIid: </label> 
		<input type="text" id="movieId" name="movieId" value="" >
		<br> -->
		<label >movieNameOriginal: </label> 
		<input type="text" id="movieNameOriginal" name="movieNameOriginal" value="" > 
		<br>
		<label >movieNameEn: </label> 
		<input type="text" id="movieNameEn" name="movieNameEn" value="" > 
		<br>
		<label >movieNameChn: </label> 
		<input type="text" id="movieNameChn" name="movieNameChn" value="" > 
		<br>
		<label >movieNameAlias: </label> 
		<input type="text" id="movieNameAlias" name="movieNameAlias" value="" > 
		<br>
		<label >movieIMDB: </label> 
		<input type="text" id="movieIMDB" name="movieIMDB" value="" > 
		<br>
		<label >directorStarName: </label> 
		<input type="text" id="directorStarName" name="directorStarName" value="" >
		<br>
		<label >leadStarName: </label> 
		<input type="text" id="leadStarName" name="leadStarName" value="" >
		<br>
		<label >tagName: </label> 
		<input type="text" id="tagName" name="tagName" value="" >
		<br>
		<label >movieWeb: </label> 
		<input type="text" id="movieWeb" name="movieWeb" value="" > 
		<br>
		<label >movieRegion: </label> 
		<input type="text" id="movieRegion" name="movieRegion" value="" > 
		<br>
		<label >movieLanguage: </label> 
		<input type="text" id="movieLanguage" name="movieLanguage" value="" >
		<br>
		<label >movieDate: </label> 
		<input type="text" id="movieDate" name="movieDate" value="" >
		<br>
		<label >movieTime: </label> 
		<input type="text" id="movieTime" name="movieTime" value="" >
		<br>
		<label >movieView: </label> 
		<input type="text" id="movieView" name="movieView" value="" >
		<br>
		<label >movieDemo: </label> 
		<input type="text" id="movieDemo" name="movieDemo" value="" > 
		<br>
		<label >movieBrief: </label> 
		<input type="text" id="movieBrief" name="movieBrief" value="" > 
		<br>
		<label >recordDate: </label> 
		<input type="text" id="recordDate" name="recordDate" value="" >
		<br>
		<input type="submit" class="button" value="submit"/> 
	 </fieldset>
	</form> 
  </body>
</html>
