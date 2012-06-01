<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 't.jsp' starting page</title>
    
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
    This is my JSP page. <br>
    
    <%
    
    	String name = request.getParameter("name");
    	String pwd = request.getParameter("pwd");
		Cookie nameC = new Cookie("userName", name );
	    	Cookie pwdC = new Cookie("pwd", pwd );
	    	
	    	nameC.setMaxAge(60*60*24*7);	    	
	    	nameC.setValue(name);	    	
	    	
	    	pwdC.setMaxAge(60*60*24*7);
	    	pwdC.setValue(pwd);
	    	
	    	nameC.setPath("/tribus_spring/user/");
	    	pwdC.setPath("/tribus_spring/user/");
	    	
	    	response.addCookie(nameC);
	    	response.addCookie(pwdC);    
     %>
  </body>
</html>
