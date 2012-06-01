<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c" %><%
%><%@ page import="config.GlobleConfig" %>
<%
	request.setAttribute("domain",GlobleConfig.localhost);
 %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Login</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain }user/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain }user/css/style.css" />
    <script type="text/javascript" src="${domain }js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="${domain }js/md5.js"></script>
    <script type="text/javascript">
		var i = 0;
    	function check(){		   
		   $("input").each(
	   		function(){
				i++;			
				if((this.required == true || this.required == "true") && $.trim(this.value) == ""){
				}
				if(this.id == "myEmail" && !IsEmail(this.value)){
                                   i=0;
					alert("please fill in a proper email ^_^");
					return false;
				}
				if( (this.isNumber == true || this.isNumber == "true") && !$.isNumeric(this.value)){
                                   i=0;
					alert("please fill in a proper email ^_^");		
					return false;
				}							
				if(this.name == "userPw"){ this.value = MD5(this.value);}								
			});				
					if(i>=3){$("form").submit();}
    	}
    	
    	    	    	
	    function IsEmail(email) {
		  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
		
		var flag = true;
		$(document).ready(function(){	
				$("#pwd").focus( function() {
//					var t = change_word($("#pwd").val(),$("#passoword"));
//					$("#pwd").val(t);
					this.value = "";
					this.type = "password";
				});   									
							
		        $("#remb").css("background-image","url(${domain}img/un_select.jpg)");
				$("#remb").click(function(){
					if(flag){
						flag = false;
						$(this).css("background-image","url(${domain}img/blue_round.jpg)");
					}else{
						flag = true;
						$(this).css("background-image","url(${domain}img/un_select.jpg)");
					}
				});					
				//$("#remb1").click(function(){
					//$("#remb").show();
					//$(this).hide();
				//});
		});


		function change_word(obj,hidden){
			var a = "";	
			var b = hidden.val();
			hidden.val(obj);
			for(var i=0;i<obj.length;i++){
				a += "*" ;
			}
			return a;
		}
    </script>
</head>

<body class="welcome_bg">
	<div class="welcome_white_box"><br /><br /><!--start welcome_white_box-->
    	<form action="login.action" method="post">
        	<div class="welcome_logo">
        	<a href="#"><img src="${domain }user/img/logo_welcome.jpg" alt="" /></a></div>
            <h2 class="title_welcome">Let's go Tribus</h2>
            <div class="user_account"><!--start user_account-->
            	<p class="welcome_txt">
<input type="text" name="email" required="false" value="username" onclick="if(this.value=='username')(this.value='')"  onblur="if(this.value=='')(this.value='username')" /></p>			
<p class="welcome_txt">
	<input type="hidden" name="userPw1" id="passoword" value="" />
<input type="text" required="false" name="userPw" id="pwd" value="password" /></p>
			<span class="remind_me">
			
				<c:if test="${message != null}">	            			            		
	            		<div class="error space_error">                
                			<span>!!! ${message} </span>
            			</div>	        					            		
            	</c:if>
            	
			</span>
             <span class="remind_me"><a href="#" id="remb">Remember Me</a></span>        

                <div class="enter"><a href="javascript: check();"><b>ENTER</b></a></div>
                <div class="or_sign_by">
                	<p>Or sign in with: <a href="error.action"><img src="${domain }user/img/icon_facebook.jpg" alt="" /></a> </p>
                    <p><a href="error_input.action">Forgot your password?</a></p>
                </div>
            </div><!--//end .user_account-->
            <div id="request_invitation">            			
			<!--img src="c_n.jpg" width="95px" height="18px"/-->
			&copy; 2012 goTribus			
            </div>
        </form>
    </div><!--//end #welcome_white_box-->    
</body>
</html>                                                                                                  