<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c" %><%
%><%@ page import="config.GlobleConfig" %>
<%
	request.setAttribute("domain",GlobleConfig.localhost);
 %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>welcomepage-layout-loginfailed</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/style.css" />
    <script type="text/javascript" src="${domain}js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="${domain}js/md5.js"></script>
    <script type="text/javascript">    
    
    	var i = 0;
    	function check(){		   
		   $("input").each(
	   		function(){
				i++;			
				if((this.required == true || this.required == "true") && $.trim(this.value) == ""){
				}
				if(this.name == "email" && !IsEmail(this.value)){
                                   i=0;
					alert("please fill in a proper email ^_^");
					return false;
				}
				if( (this.isNumber == true || this.isNumber == "true") && !$.isNumeric(this.value)){
                                   i=0;
					alert("please fill in a proper email ^_^");		
					return false;
				}
				//alert(MD5(this.value));
				if(this.name == "userPw"){ this.value = MD5(this.value);}
				if(this.name == "userPw2" && MD5(this.value) != $("#password").val() ){
					i = 0;
					alert("two passwords not the same !");
					return false;
				}
			});				
					if(i>=4){$("form").submit();}
    	}  	    	
    	
    	function IsEmail(email) {
		  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
		
		var flag = true;
		$(document).ready(function(){	
				$("#pwd").keyup( function() {
					var t = change_word($("#pwd").val(),$("#passoword"));
					$("#pwd").val(t);
				});   									
				
				//$("#remb1").hide();
		        $("#remb").css("background-image","url(${domain}/img/un_select.jpg)");
				$("#remb").click(function(){
					if(flag){
						flag = false;
						$(this).css("background-image","url(${domain}/img/blue_round.jpg)");
						$("#remember").attr("value",1);
					}else{
						flag = true;
						$(this).css("background-image","url(${domain}/img/un_select.jpg)");
						$("#remember").attr("value",0);
					}
				});					
				//$("#remb1").click(function(){
					//$("#remb").show();
					//$(this).hide();
				//});
		});
    </script>
</head>

<body class="welcome_bg">
	<div class="welcome_white_box spacing"><!--start welcome_white_box-->
    	<form action="registerAction.action" method="post">
        	<div class="welcome_logo">
	        	<a href="index.html">
	        		<img src="${domain}user/img/logo_welcome.jpg" alt="" />
	        	</a>
        	</div>
            <h2 class="title_welcome">Let's to goTribus</h2>
            <div class="user_account"><!--start user_account-->
            	            	
            	<p class="welcome_txt"><input type="text" required="true" name="userAlias" value="username" onclick="if(this.value=='username')(this.value='')"  onblur="if(this.value=='')(this.value='username')" /></p>			
                
                
                <p class="welcome_txt"><input type="text" required="true" id="password" name="userPw" value="password" onclick="if(this.value=='password')(this.value='')"  onblur="if(this.value=='')(this.value='password')" /></p>
                
                <p class="welcome_txt"><input type="text" required="true" name="userPw2" value="password again" onclick="if(this.value=='password again')(this.value='')"  onblur="if(this.value=='')(this.value='password again')" /></p>
                
                <p class="welcome_txt"><input type="text" required="true" name="userEmail" value="email" onclick="if(this.value=='email')(this.value='')"  onblur="if(this.value=='')(this.value='email')" /></p>
                               
                
                
                <div class="enter"><a href="javascript:check();">Register &gt;</a></div>
                <div class="or_sign_by">
                    <!-- p><a href="#">Forgot your password?</a></p -->
                </div>
            </div><!--//end .user_account-->
            
            <c:if test="${!empty message}">
            <div class="error"><span>!!! ${message}</span></div>
            </c:if>
            
        </form>
    </div><!--//end #welcome_white_box-->
</body>
</html>                                                                                                   