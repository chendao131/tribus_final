<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %><%@page import="config.GlobleConfig" %><% 
	String domain = GlobleConfig.domainName;
%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="shortcut icon"
 href="${pageScope.domain}/myicon.ico" />
<title>Welcome to Tribus</title>
<style>
body{
	margin-top:0px;
	margin-left:0px;	
	}
	input:focus {
    outline: none;
}
.container{	     
	 position:relative;	
	 height:625px;
	 background:url(wel_back.jpg);
	 width:619px;
	 background-repeat:no-repeat;
	 margin-top:0px;
	}

</style>
</head>
<body style="background:url(back_new.jpg);">
<center>
<form action="#">

<div class="container" align="center">
	<div class="inner" style="position:relative; top:283px;">
    
    
    <table width="173" height="27px" border="0px;"  cellpadding="0px" cellspacing="0px">
    	<tr style="border:0px;"> 				
            <td width="52" height="27px" style="border:0px; background:url(log_1.PNG); ">
				&nbsp;	&nbsp;	
            </td>
            <td width="32" style="border:hidden;background:url(login%20and%20easy%20register%20dialog.png); border:0px"> 
            <input type="text" name="textfield" id="textfield" style="border-color: transparent; " value="username"></td>          	
        </tr>
        
        <tr style="border:0px;"> 				
            <td width="52" height="27px" style="border:0px; background:url(log_1.PNG)">

            </td>
            <td width="32" style="border:hidden;background:url(login%20and%20easy%20register%20dialog.png); border:0px"> 
            <input type="text" name="textfield" id="textfield" style="border-color: transparent; " value="password"></td>          	
        </tr>
    	</table>
    <br/>
    <br/>
    <br />
    <br />
    <a href="#"><img src="remember.jpg" /></a>
    <br />
    <a href="#"><img src="enter.jpg" /></a>    
    <br />
    <br />
    <a href="#"><img src="forgot_pwd.jpg" /></a>    
    </div>
</div>

</form>
</center>
</body></html>
