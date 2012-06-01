<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %><%@page import="config.GlobleConfig" %><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
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
	 background:url(../images/wel_register.jpg);
	 width:619px;
	 background-repeat:no-repeat;
	 margin-top:0px;
	}

</style>
<link rel="stylesheet" type="text/css" href="../css/styles.css">
<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">

function change_word(obj,hidden){
	var a = "";	
	var b = hidden.val();
	hidden.val(obj);
	for(var i=0;i<obj.length;i++){
		a += "*" ;
	}
	return a;
}

 $(document).ready(function(){	   	
 
 		$("#remb").show();
			$("#unf").hide();
 						
		 $("#pwd").blur( function() {
			var t = change_word($("#pwd").val(),$("#passoword"));
			$("#pwd").val(t);
		});			
		
		$("#remb").click(function(){
			if( $(this).attr("src") != "unf.jpg" ){
				$(this).attr("src","unf.jpg");				
			}else{
				$(this).attr("src","remember.jpg");						
			}
		});			
			
			$("#unf").click(function(){
				$("#remb").show();
				$("#unf").hide();
			});
 });
 
 

</script>
</head>
<body style="background:url(../images/back_new.jpg);">
<center>
<form action="#">

<div class="container" align="center">
	<div class="inner" style="position:relative; top:283px;">
    
    
    <table width="201" height="68" border="0px;"  cellpadding="0px" cellspacing="0px">
    	<tr style="border:0px;"> 				
 
            <td width="213" height="32"><div>
            <div style="float:left">
            <img src="../images/login and easy register dialog (2).png" width="230" height="32"></div>
            <div class="userAndPassword" style="float:left">
            <input type="text" name="username" id="textfield" 
            style="border-color: transparent;" value="username"></div>
            </div>
            
            <input type="hidden" name="pwd" id="passoword" value="" />
            
            </td>          	
        </tr>
        
        <tr style="border:0px;"> 				
            <td width="213" height="27px"><div>
            <div style="float:left">
            <img src="../images/login and easy register dialog (2).png" width="230" height="32"></div>
            <div class="userAndPassword" style="float:left">
            <input type="text" name="pwd" id="pwd" style="border-color: transparent; " value="password" />
            </div>
            </div></td>       	
        </tr>

		

		<tr style="border:0px;"> 				
            <td width="213" height="27px"><div>
            <div style="float:left">
            <img src="../images/login and easy register dialog (2).png" width="230" height="32"></div>
            <div class="userAndPassword" style="float:left"><input type="text" name="password2" id="pwd2" style="border-color: transparent; " value="password"></div>
            </div></td>       	
        </tr>


		<tr style="border:0px;"> 				
            <td width="213" height="27px"><div>
            <div style="float:left">
            <img src="../images/login and easy register dialog (2).png" width="230" height="32"></div>
            <div class="userAndPassword" style="float:left">
            <input type="text" name="email" id="textfield" style="border-color: transparent; " value="email"></div>
            </div></td>       	
        </tr>
		

    	</table>

    <br />
    <a href="#"><img src="../images/remember.jpg" id="remb"/></a>
    <br />
        <a href="#"><img src="../images/unf.jpg" id="unf"/></a>
    <br />
    <a href="mytribus.jsp"><img src="../images/enter.jpg" /></a>    
    <br />
    <a href="#"><img src="../images/forgot_pwd.jpg" /></a>    
	<br />
    <a href="#"><img src="../images/join.jpg" /></a> 
	<br />
    <a href="#"><img src="../images/take_tour.jpg" /></a> 
    </div>
</div>

</form>
</center>
</body></html>