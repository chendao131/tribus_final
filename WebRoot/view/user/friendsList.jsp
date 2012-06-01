<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="/WEB-INF/tld/c-rt.tld" %><%
%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" type="text/css" href="css/nav.css">
<link rel="stylesheet" type="text/css" href="css/myTribus.css">
<link rel="stylesheet" type="text/css" href="css/mail.css">
<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
 		$(document).ready(function(){	   									
	 			$('#btn-logo').click(function(event) {
  					$('#logo').click();
				});			
		 });
</script>
</head>
<body>
<div class="main">
  <div class="main_nav">
  <table class="nav" cellspacing="0">
<tr>
<td cellspacing="0"><a class="city-active" href="#"></a></td>
<td cellspacing="0"><a class="movie" href="#"></a></td>
<td cellspacing="0"><a class="book" href="#"></a></td>
<td cellspacing="0"><a class="music" href="#"></a></td>
<td cellspacing="0"><a class="mytribus" href="#"></a></td>
</tr>
  </table>
   <div class="setting" style="float:right;position:relative;right:13px;bottom:12px;">
   	<p><img src="../images/setting.png" /></p>
    <p style="position:relative;bottom:5px;"><img src="../images/setting.png" /></p>
   </div>  
  <div class="search" style="float:right;">
      	<p style="float:right; padding-right:60px;">
        <input type="text" id="sea"  value="search123"/>
        </p>
   </div>
   <table style="float:right;" id="nav_sub">
    	<tr>
        	<td width="235" height="63" align="right">
            <img src="../images/face.png" /> 	
            </td>
            <td width="273"><div style="border: 2px double rgb(222, 222, 222); -moz-border-radius: 15px 15px 15px 15px; border-radius: 15px; -webkit-border-radius: 15px;"><div id="nav_nameAndCity" style="float:right;">
            <a>Jeremy Lin</a><a>New York</a></div> 
            <img class="nav_social" src="../images/logout.png" />
            <img class="nav_social" src="../images/notification.png" />
            <img class="nav_social" src="../images/about.png" />
            <img class="nav_social" src="../images/logout.png" />  <br></div></td>
        </tr>     
    </table>  
   
   </div> <!-- main_nav -->
 
 <div id="mail">
 	<div id="mail_search">
    	<div><img src="../images/search bar.png" /> <img src="../images/Search-button.png" style="position:relative;bottom:8px;right:50px;"/></div>
    </div>
    <div id="mail_box">
    	<div id="mail_box_title">
        
        
        <c:forEach items="${v}" var="i" varStatus="ind">
        	
        	<c:choose>
        		<c:when test="${i != null}">
        		
        		</c:when>
        		<c:otherwise>
        		
        		</c:otherwise>
        	</c:choose>
        	
        </c:forEach>
        
        <!-- -->
       <div id="one" style="position:relative; float:left;">
   <div id="top"  style="position: absolute; float:left; z-index:+1">
   <img src="../images/flag.png" width="275" height="79">
   </div>
&nbsp;
   <div style=" float:left; margin-top:20px; border: 2px double rgb(222, 222, 222); -moz-border-radius: 15px 15px 15px 15px; border-radius: 15px; -webkit-border-radius: 15px;">			
            <table>
            	<tr>
               	  <td>guan@163.com
                    <br/>
                    guanzhichao
                  </td>                                                         
                </tr>
            </table>
		</div>
 </div>
        <!-- -->
            <div id="mail_box_content">            	                 
                <div id="mail_box_mails">                  
                   <c:forEach items="${mails}" var="m">
                   
                    <div class="mail_lines">
                        <ul>
                            <li>
                            	<img src="${user.picUrl}" />
                                <br />
                                ${m.userName}
                            </li>                           
                        </ul>
                        <br />
                    </div>
				    </c:forEach>
                    
            	</div>
            </div> <!-- mail_box_content -->
        </div>  <!-- mail_box_title -->    
    </div> <!-- mail_box -->       
   
 </div><!-- mail--> 

</div> <!-- main -->


</body>
</html>