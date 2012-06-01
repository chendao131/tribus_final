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
		    parent.document.getElementById("msg").innerHTML = "upload success! and you can keep uploading !";
		    parent.document.getElementById(id).value = "${fileUrl}"; 
		    var ele = parent.document.createElement("img");
		    ele.src = "${fileUrl}";
		    parent.document.getElementById("div_pic").appendChild(ele);

		    //parent.document.getElementById("div_pic")
		        
		}   
		callback("file_url");
	</script>

</body>
</html>