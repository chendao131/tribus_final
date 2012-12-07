<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<img src="${fileUrl}" /> 
	<script type="text/javascript">
		function callback(id)   
		{   		    
		    parent.document.getElementById("msg").innerHTML = "";
		    parent.document.getElementById(id).value = "${fileUrl}"; 
		    var ele = parent.document.createElement("img");
		    ele.src = "${fileUrl}";
		    ele.width=200;
		    ele.height = 220;
		    if(parent.document.getElementById("div_pic")){
		    	parent.document.getElementById("div_pic").appendChild(ele);
		    	parent.document.getElementById("file_url").value="${fileUrl}";
		    }		    
		    //parent.document.getElementById("div_pic")		    		    
		    if(parent.document.getElementById("hidden_para${id}")){
		    	parent.document.getElementById("hidden_para${id}").value = "${fileUrl}";
		    }		     
		    var ele = parent.document.createElement("img");
		    ele.src = "${fileUrl}";
		    ele.width=200;
		    ele.height = 220;
		    if(parent.document.getElementById("container_${id}") && parent.document.getElementById("save_button")){
		    	parent.document.getElementById("container_${id}").appendChild(ele);
		    	parent.document.getElementById("save_button").disabled=false;    
		    }		    
		    
		    if(parent.document.getElementById("personal_img")){
		    	parent.document.getElementById("personal_img").src = "${fileUrl}";
		    	parent.document.getElementById("userPic").value = "${fileUrl}";
		    	//parent.document.getElementById("save_button").disabled=false;    
		    }	
		    
		}   
		callback("file_url");
	</script>

</body>
</html>