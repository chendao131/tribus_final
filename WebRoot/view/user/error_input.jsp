<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Invitation</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="style.css" />
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript">

	var i = 0;
    	function check(){		   
		   $("input").each(
	   		function(){
				i++;			
				if((this.required == true || this.required == "true") && $.trim(this.value) == ""){
                                  // i=0;
					//alert("you must fill "+ this.name +"!");
					//return false;
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

			});
				
					if(i>=3){$("form").submit();}
//					return true;					
    	}
    	
    	
    	
    	
    	function IsEmail(email) {
		  var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
		}
		
		
		 $(document).ready(function(){	

$("#pwd").keyup( function() {
			var t = change_word($("#pwd").val(),$("#passoword"));
			$("#pwd").val(t);
		});   									

		$("#remb").click(function(){		
			alert(123);							
			if( $(this).css("background") != "url(img/blue_round.jpg)" ){
				$(this).css("background","url(img/blue_round.jpg)");				
			}else{
				$(this).css("background","url(img/un_select.jpg)");				
			}
		});					
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
	<div class="welcome_white_box"><!--start welcome_white_box-->
    	<form action="registerAction.php"  autocomplete="off">
        	<div class="welcome_logo"><a href="#"><img src="img/logo_welcome.jpg" alt="" /></a></div>
            <h2 class="title_welcome">Let's go Tribus</h2>          
            <div id="request_invitation">
            	<div id="send_request">
                	<label>Input your Email</label>
                    <p>
				<input type="text" name="email" isemail="true" id="myEmail" value="email" onclick="if(this.value=='email')(this.value='')"  onblur="if(this.value=='')(this.value='email')">
			</p>					 
                    <span><a href="javascript:check();">send</a></span>
                </div>
			<div align="center"><span>
			<!--img src="c_n.jpg" width="95px" height="18px"/-->
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
			&copy; 2012 goTribus
			</span></div>
            </div>
        </form>
</body>
</html>
                                                                                             