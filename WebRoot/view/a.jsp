<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'a.jsp' starting page</title>
    
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
    
    
	    	Cookie[] cookies = request.getCookies();
	    	for (int i = 0; i < cookies.length; i++) {
	    		
									
					Cookie c = cookies[i];
					System.out.println(c.getName());
					System.out.println(c.getValue());				       				      
				       //res.getWriter().write(name);					
											
						
				       
				
			}
			
			out.print("ok");
    
     %>
  </body>
</html>
