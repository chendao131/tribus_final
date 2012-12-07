<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>upload failed!</title>

<script type="text/javascript">
		function callback()   
		{   		    
		    parent.document.getElementById("msg").innerHTML = "file size is too large or not Jpeg!";			
		}   
	</script>

</head>
<body>
uploadfail !!
	<script type="text/javascript">
		callback();   
	</script>
</body>
</html>