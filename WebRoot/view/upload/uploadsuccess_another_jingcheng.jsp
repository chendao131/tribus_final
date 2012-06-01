<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 
	img src="${fileUrl}"
 -->

	<script type="text/javascript">
	alert(parent.document.getElementById("container_"+"${id}"));
	alert("${fileUrl}");
		function callback(id)   
		{   		    
		    //parent.document.getElementById("msg").innerHTML = "upload success! and you can keep uploading !";
		    parent.document.getElementById("hidden_para"+"${id}").value = "${fileUrl}"; 
		    var ele = parent.document.createElement("img");
		    ele.src = "${fileUrl}";
		    ele.width=200;
		    ele.height = 220;
		    parent.document.getElementById("container_"+"${id}").appendChild(ele);
		    parent.document.getElementById("save_button").disabled=false;
		}   
		callback();
	</script>

</body>
</html>