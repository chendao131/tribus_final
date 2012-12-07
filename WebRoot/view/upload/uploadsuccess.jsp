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
		function callback(id,big_id,small_id)   
		{   		    
		    //parent.document.getElementById("msg").innerHTML = "";

			if(parent.document.getElementById("file_url")){
				parent.document.getElementById("file_url").value = "${fileUrl_big}"; 			
			}
			if(parent.document.getElementById(id)){
			    parent.document.getElementById(id).value = "${fileUrl_middle}"; 			
			    parent.document.getElementById(big_id).value = "${fileUrl_big}"; 			
			    parent.document.getElementById(small_id).value = "${fileUrl_small}"; 			
			}

		    var ele = parent.document.createElement("img");
		    ele.src = "${fileUrl_big}";
		    ele.width=200;
		    ele.height = 220;
		    if(parent.document.getElementById("div_pic")){
		    	parent.document.getElementById("div_pic").innerHTML = null;
		    	parent.document.getElementById("div_pic").appendChild(ele);
		    }		    
			
			if(parent.document.getElementById("successful_img_path_middle")){
				parent.document.getElementById("successful_img_path").value = "${fileUrl_big}";
				parent.document.getElementById("successful_img_path_big").value = "${fileUrl_big}";
				parent.document.getElementById("successful_img_path_middle").value = "${fileUrl_middle}";
				parent.document.getElementById("successful_img_path_small").value = "${fileUrl_small}";				
			}

			if(parent.document.getElementById("div_pic_${id}")){
		    	parent.document.getElementById("div_pic_${id}").innerHTML = null;
		    	parent.document.getElementById("div_pic_${id}").appendChild(ele);
		    }	

			if(parent.document.getElementById("container_single")){
		    	parent.document.getElementById("container_single").innerHTML = null;
		    	parent.document.getElementById("container_single").appendChild(ele);
		    }
		    //parent.document.getElementById("div_pic")		    		    
		    if(parent.document.getElementById("hidden_para${id}_small")){
		    	
				parent.document.getElementById("hidden_para${id}_small").value = "${fileUrl_small}";
		    	parent.document.getElementById("hidden_para${id}_middle").value = "${fileUrl_middle}";
		    	parent.document.getElementById("hidden_para${id}_big").value = "${fileUrl_big}";

		    }
		    if(parent.document.getElementById("hidden_para${id}")){		    
				parent.document.getElementById("hidden_para${id}").value = "${fileUrl_big}";
		    }

		    var ele = parent.document.createElement("img");
		    ele.src = "${fileUrl_big}";
		    ele.width=200;
		    ele.height = 220;
		    if(parent.document.getElementById("container_${id}")){
				parent.document.getElementById("container_${id}").innerHTML = null;
		    	parent.document.getElementById("container_${id}").appendChild(ele);
		    	//parent.document.getElementById("save_button").disabled=false;    
		    }		    
		    
		    if(parent.document.getElementById("personal_img")){
				parent.document.getElementById("personal_img").innerHTML = null;
		    	parent.document.getElementById("personal_img").src = "${fileUrl_big}";
		    	parent.document.getElementById("userPic").value = "${fileUrl_big}";
		    	//parent.document.getElementById("save_button").disabled=false;    
		    }	
		    
		}   
		callback("file_url_middle","file_url_big","file_url_small");
	</script>

</body>
</html>
