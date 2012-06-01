<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ page import="config.GlobleConfig" %><%
	request.setAttribute("domain",GlobleConfig.localhost);
 %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>welcomepage-layout-signup-termsandcondition</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/style.css" />
    
</head>

<body class="welcome_bg">
	<div class="welcome_white_box"><!--start welcome_white_box-->
    	<div class="terms_box">
        	<div class="condition_box">
            	<div class="condition_flow">                	                    
                    <p>
                    	Successful registered !!
                    	<a href="javascript:window.history.go(-1);">take a tour</a>
                    	or go to check your email ! 
                    </p>                                        
                </div>
            </div>
        </div>
        <div class="agree_term">        	
        </div>
    </div><!--//end #welcome_white_box-->
</body>
</html>                                                                                                   