<%@ page language="java" import="java.util.*, model.ActivityAlbum" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int i =0;
int size=((List)request.getAttribute("userList")).size();
int p=1;
List<ActivityAlbum> activityAlbum=new LinkedList();
activityAlbum=(List<ActivityAlbum>)request.getAttribute("activityAlbum");
			String followed=(String)request.getAttribute("followed");
			String joined=(String)request.getAttribute("joined");
			String owner=(String)request.getAttribute("owner");
		
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'activityInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
<!--
.STYLE4 {font-size: 12px}
.STYLE5 {
	font-size: 12px;
	color: #FFFFFF;
	font-weight: bold;
}
.STYLE6 {font-size: 12px; color: #000000; font-weight: bold; }
.STYLE8 {font-size: 10px; font-style: italic; }
-->
</style>
		<script
			src="http://ditu.google.cn/maps?file=api&amp;v=2&amp;key=ABQIAAAAVjCvQMEky2Xe635UMlEabxTaxHhufQQ_wqXxGIk3tfPhbmHbTBTcMgGyho7ATM90-GzVNkgnC6ko4w&sensor=true"
			type="text/javascript"></script>
		<script type="text/javascript">
   var map = null;
    var geocoder = null;
        function initialize() {
      if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(${activityLat}, ${activityLong}), 13);
        geocoder = new GClientGeocoder();
        
map.openInfoWindow(map.getCenter(),
                   document.createTextNode("${activityInfo.activityStreet}"));
		 mapControl= new GMapTypeControl();<!--地图类型控件-->
        map.addControl(mapControl);
         map.addControl(new GLargeMapControl());<!--大平移控件-->
		  map.addControl(new GOverviewMapControl ());
              var marker = new GMarker(${activityLat}, ${activityLong});
              map.addOverlay(marker);
              
      }
    }
      function showAddress(address) {
      if (geocoder) {
        geocoder.getLatLng(
          address,
          function(point) {
            if (!point) {
              alert(address + " not found");
            } else {
              map.setCenter(point, 13);
              var marker = new GMarker(point);
              map.addOverlay(marker);
              marker.openInfoWindow(document.createTextNode(address));
            }
          }
        );
      }
    }
  function followActivity(url){
    
     http_request = false;
    if (window.XMLHttpRequest) {    								// 非IE浏览器
        http_request = new XMLHttpRequest();							//创建XMLHttpRequest对象
    } else if (window.ActiveXObject) {     							// IE浏览器
        try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");		//创建XMLHttpRequest对象
        } catch (e) {
            try {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");	//创建XMLHttpRequest对象
           } catch (e) {
           }
        }
    }
    if (!http_request) {
       
        return false;
    }
    http_request.onreadystatechange = getResult;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);		
    
    }
function getResult() {
    if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById("bt1").value=http_request.responseText;;	//设置提示内容
 
        } else {     													// 请求页面有错误
            alert("failure");
        }
    }
}
  function joinActivity(url){
    
     http_request = false;
    if (window.XMLHttpRequest) {    								// 非IE浏览器
        http_request = new XMLHttpRequest();							//创建XMLHttpRequest对象
    } else if (window.ActiveXObject) {     							// IE浏览器
        try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");		//创建XMLHttpRequest对象
        } catch (e) {
            try {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");	//创建XMLHttpRequest对象
           } catch (e) {}
        }
    }
    if (!http_request) {
        alert("cannot create XMLHttpRequest instance");
        return false;
    }
    http_request.onreadystatechange = getResult1;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);		
    
    }
    
    
    
    
    
    function getResult1() {
    if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {// 请求成功，开始处理返回结果
            document.getElementById("bt2").value=http_request.responseText;;	//设置提示内容
 
        } else {     													// 请求页面有错误
            alert("failure");
        }
    }
}

        </script>
  </head>
  
  <body onload="initialize()">
  <div align="center">
  <table width="1006" height="63" border="0">
    <tr>
      <td><img src="../tribus/view/image/a.JPG" width="1026" height="63" border="0" usemap="#Map" /></td>
    </tr>
  </table>
  <map name="Map" id="Map">
    <area shape="rect" coords="84,19,141,39" href="activity/index.action" />
    <area shape="rect" coords="187,20,250,38" href="movie/homepage.action" />
    <area shape="rect" coords="298,19,358,36" href="book/homepage.action" />
    <area shape="rect" coords="389,19,454,41" href="music/homepage.action" />
    <area shape="rect" coords="490,23,595,41" href="user/home.action" />
  </map>
</div>
   <table width="1003" height="967" border="0" align="center">
  <tr>
    <td width="289"><div align="center"><img src="${activityInfo.activityPic}" width="255" height="336" /></div></td>
    <td width="361"><table width="349" height="319" border="0">
      <tr>
        <td height="15"><span class="STYLE4">${activityInfo.activityName}</span></td>
      </tr>
      <tr>
        <td height="10">&nbsp;</td>
      </tr>
      <tr>
        <td height="15"><span class="STYLE4">Start Date:${activityInfo.activityStartTime} </span></td>
      </tr>
      <tr>
        <td height="15"><span class="STYLE4">End Date:${activityInfo.activityFinishTime}</span></td>
      </tr>
      <tr>
        <td height="23"><span class="STYLE4">Place:${activityInfo.activityCity}&nbsp ${activityInfo.activityStreet}&nbsp${activityInfo.activityApt} <img src="../tribus/view/image/play.png" width="19" height="19" /></span></td>
      </tr>
      <tr>
        <td height="15"><span class="STYLE4">Starter:</span></td>
      </tr>
      <tr>
        <td height="15"><span class="STYLE4">Type:${activityInfo.activityClassified}</span></td>
      </tr>
      <tr>
        <td height="15"><span class="STYLE4">Fees:${activityInfo.activityFees}D</span></td>
      </tr>
      <tr>
        <td height="23"><span class="STYLE4"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /><%if(owner==null) {%>
				<input id="bt1" type="button" name="Submit" value="<%if(followed.equals("true")){%>unFollow<%}else{%>Follow<%}%>" 
			onclick="followActivity('activity/followActivity.action?activityId=${activityId}')"/><%}else{%>you are the host <%} %> </span></td>
      </tr>
	        <tr>
        <td height="23"><span class="STYLE4"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /><%if(owner==null){ %>
        <input id="bt2" type="button" name="Submit" value="<%if(joined.equals("true")){%>unjoin<%}else{%>join<%}%>" 
			onclick="joinActivity('activity/joinActivity.action?activityId=${activityId}')"/><%}else{%>you are the host <%} %> </span></td>
      </tr>
	        <tr>
        <td height="26"><img src="../tribus/view/image/2.JPG" width="52" height="24" /></td>
      </tr>
      <tr>
        <td height="93"><span class="STYLE4">${activityInfo.activityDetail}</span></td>
      </tr>
    </table></td>
    <td width="339"><table width="327" height="281" border="0" align="center">
      <tr>
        <td><div id="map_canvas" style="width: 257; height: 247"></div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="244" colspan="2">
    <table width="659" height="148" border="0">
      
     <tr><%if(activityAlbum.get(0)!=null) %>
        <td width="100" height="114"><div align="center"><%if(activityAlbum.get(0)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(0).getAlbumId() %>"><img src="<%=activityAlbum.get(0).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td width="100"><div align="center"><%if(activityAlbum.get(1)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(1).getAlbumId() %>"><img src="<%=activityAlbum.get(1).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td width="100"><div align="center"><%if(activityAlbum.get(2)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(2).getAlbumId() %>"><img src="<%=activityAlbum.get(2).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td width="100"><div align="center"><%if(activityAlbum.get(3)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(3).getAlbumId() %>"><img src="<%=activityAlbum.get(3).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td width="100"><div align="center"><%if(activityAlbum.get(4)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(4).getAlbumId() %>"><img src="<%=activityAlbum.get(4).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
      </tr>
      <tr>
        <td><div align="center"><%if(activityAlbum.get(5)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(5).getAlbumId() %>"><img src="<%=activityAlbum.get(5).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td><div align="center"><%if(activityAlbum.get(6)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(6).getAlbumId() %>"><img src="<%=activityAlbum.get(6).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td><div align="center"><%if(activityAlbum.get(7)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(7).getAlbumId() %>"><img src="<%=activityAlbum.get(7).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td><div align="center"><%if(activityAlbum.get(8)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(8).getAlbumId() %>"><img src="<%=activityAlbum.get(8).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
        <td><div align="center"><%if(activityAlbum.get(9)!=null) {%><a href="activity/showPicList.action?albumId=<%=activityAlbum.get(9).getAlbumId() %>"><img src="<%=activityAlbum.get(9).getAlbumCover()%>"width="94" height="97" /> </br> </a><%}else{ %><img src="" width="94" height="97" /><%} %></div></td>
      </tr>
      <tr>
       
        
    </table>
    </td>
    <td><table width="238" height="206" border="0" align="center">
      <tr>
        <td height="27"><div align="center"><img src="../tribus/view/image/Secondary-column-title-box.png" width="203" height="25" /></div></td>
      </tr>
      <tr>
        <td height="173">
        <table width="211" height="142" border="0" align="center">
          <tr>
          <c:forEach items="${userList}" var="item">
          
         
            <td width="38" height="44"><div align="center"><a href="user/my/${item.userId}"><img src="${item.userPic}" width="33" height="29" /></a></div></td>
          <% i++;if(i%4==0){%>
          </tr>
          <tr>
		  <%} %>
		  <%if(i==size){%>
		  </tr>
		  <%} %>
 </c:forEach>
        </table>
        </td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
   
   <%if(session.getAttribute("user")!=null){ %> <td><A href="activity/addPicIndex.action?activityId=${activityId}">上传新照片</A></td><%} %>
  </tr>
  <tr>
    <td height="360" colspan="2">
   <table width="662" height="358" border="0">
      <c:forEach items="${userCommentList}" var="item">
     <tr>
        <td height="114">
          <table width="653" height="112" border=<%if(p%2==0){ %>"3"<%}else{ %>"0"<%} %> bordercolor="#E0E0E0">
          <tr bordercolor=<%if(p%2!=0){ %>"#E0E0E0"<%}else{ %>"#FFFFFF"<% }%> bgcolor="#E0E0E0">
            <td width="76" <%if(p%2==0){ %>bgcolor="#FFFFFF"<%} %>height="108"><a href="user/my/${item.userId}"><img src="${item.userPic}" width="66" height="66" /></a></td>
            <td width="561"<%if(p%2==0){ %>bgcolor="#FFFFFF"<%} %>><p class="STYLE4"><a href="user/my/${item.userId}">${item.userName}</a></p>
              <p class="STYLE4">${item.commentContent}</p>
              <p class=<%if(p%2==0){ %>"STYLE6"<%}else{%>"STYLE5"<%} %>>${item.commentDate}</p>
              <p>&nbsp;  </p></td>
          </tr>
         </table>
        </td>
      </tr>
      <%p++; %>
      </c:forEach>
    </table>
    </td>
    <td><table width="238" height="318" border="0" align="center">
      <tr>
        <td height="27"><div align="center"><img src="../tribus/view/image/Secondary-column-title-box.png" width="203" height="25" /></div></td>
      </tr>
      <tr>
        <td height="173"><table width="231" height="265" border="0">
          
          <c:forEach items="${recommentActivity}" var="item">
          <tr>
            <td height="78"><div align="center"><a href="activity/info.action?activityId=${item.activityId}"><img src="${item.activityPic}" width="107" height="64" /></a></div></td>
          </tr>
          <tr>
            <td height="12"><div align="center" class="STYLE8"><a href="activity/info.action?activityId=${item.activityId}">${item.activityName}</a></div></td>
          </tr>
          </c:forEach>
          

          
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
<%if(session.getAttribute("user")!=null){ %>
<form id="formComment" name="formComment" method="post" action="activity/addComment.action?activityId=${activityId}">
  <p>
    <textarea name="commentContent"></textarea>
</p>
  <p>
    <input type="submit" name="Submit" value="commit" />
</p>
</form>
<%} %>
  </body>
</html>
