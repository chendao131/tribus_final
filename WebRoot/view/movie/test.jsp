<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.util.Enumeration" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<script type="text/javascript">
		var counter = 1;
		var limit = 3;
		function addInput(divName){
		     if (counter == limit)  {
		          alert("You have reached the limit of adding " + counter + " inputs");
		     }
		     else {
		          var newdiv = document.createElement('div');
		          newdiv.innerHTML = "Entry " + (counter + 1) + " <br><input type='text' name='myInputs[]'>";
		          
		          document.getElementById(divName).appendChild(newdiv);
		          counter++;
		     }
		}
	</script>
		<form action="movie/test1.action" method="POST">
		     <div id="dynamicInput">
		          Entry 1<br><input type="text" name="myInputs[]">
		     </div>
		     <input type="text" name="username">&nbsp;&nbsp;  
		     <input type="text" name="userpass">&nbsp;&nbsp;  
		     <input type="button" value="Add another text input" onClick="addInput('dynamicInput');">
		     <input type="submit" value="submit" >
		</form>
			
		<%
			String str="";  
			System.out.println(request.getParameter("username"));
			if(request.getParameter("username")!=null && request.getParameter("userpass")!=null){  
			Enumeration<?> enumt = request.getParameterNames();  
			System.out.println(enumt.hasMoreElements());
			while(enumt.hasMoreElements()){  
			str=enumt.nextElement().toString();  
			System.out.println("1");
			System.out.println(str +":" +request.getParameter(str)+ "< br>");  
// 			out.println(str ":" request.getParameter(str) "< br>");
			}  
			}  
		%> 
  </body>
</html>
