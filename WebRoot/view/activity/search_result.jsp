<%@ page language="java" import="java.util.*,model.User,vo.SuperActivity,config.GlobleConfig" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-rt.tld" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<SuperActivity> activityList=(List<SuperActivity>)request.getAttribute("activityList");
		User user=(User)session.getAttribute("user");	
		String condition=(String)request.getAttribute("condition");
request.setAttribute("path1",GlobleConfig.pathPath);
request.setAttribute("path2",GlobleConfig.pathPath1);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'search_result.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/css/style.css" />
<script type="text/javascript">
function search(){
var a =document.getElementById("txt1");
window.location.href="${path2}activity/search.action?searchCondition="+a.value;
}


var event1=null;
 function followActivity(url,id){
  event1=id;

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
        alert("cannot create XMLHttpRequest instance！");
        return false;
    }
    http_request.onreadystatechange = getResult;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);		

    }
function getResult() {
    if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById(event1+"").value=http_request.responseText;	//设置提示内容

        } else {     													// 请求页面有错误
            alert("您所请求的页面有错误！");
        }
    }
}

    var event2=null;
 function TribusList(url,id){
  event2=id;
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
        alert("不能创建XMLHttpRequest对象实例！");
        return false;
    }
    http_request.onreadystatechange = getResultTribusList;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);	
    }
    
  function   getResultTribusList(){
        if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById(event2+"").innerHTML=http_request.responseText;;	//设置提示内容
 
        } else {     													// 请求页面有错误
            alert("您所请求的页面有错误！");
        }
    }
    }
    
    
    
    
 function WishList(url,id){
 event2=id;
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
        alert("不能创建XMLHttpRequest对象实例！");
        return false;
    }
    http_request.onreadystatechange = getResultWishList;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);	
    }
    
  function   getResultWishList(){
        if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById(event2+"").innerHTML=http_request.responseText;;	//设置提示内容
 
        } else {     													// 请求页面有错误
            alert("您所请求的页面有错误！");
        }
    }
    }
  function joinActivity(url,id){
    event1=id;

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
        alert("不能创建XMLHttpRequest对象实例！");
        return false;
    }
    http_request.onreadystatechange = getResult1;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);		
    
    }
    function getResult1() {
    if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById(event1+"").innerHTML="+  "+http_request.responseText;	//设置提示内容

        } else {     													// 请求页面有错误
            alert("您所请求的页面有错误！");
        }
    }
}

</script>
  </head>
  
  <body>
  	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="${path2}activity/index.action"><img src="${path1}activity/img/logo.png" alt="" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li class="current_page_item">
										<a href="${path2}activity/index.action">CITY</a>
									</li>
									<li>
										<a href="${path2}movie/movieHomePage.action" title="MOVIE">MOVIE</a>
									</li>
									<li>
										<a href="${path2}book/bookHomePage.action" title="BOOK">BOOK</a>
									</li>
									<li>
										<a href="${path2}music/musicHomePage.action" title="MUSIC">MUSIC</a>
									</li>
									<li>
										<a href="${path2}user/my.action" title="">MY TRIBUS</a>
									</li>
                    </ul>
                    <div class="header_search">
                    	<form action="${path2}user/searchAll.action">
                        	<p class="txt_header"><input id="search" name="search" type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${path1}activity/img/icon_header1.png" alt="" /></a></span>
                        <span><a href="${path2}user/editForm.action"><img src="${path1}activity/img/icon_header2.png" alt="" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${path2}activity/search.action">
                    	<p class="search_text"><input id="searchCondition" name="searchCondition" type="text" value="Seach activity, activity time, activity location" onclick="if(this.value=='Seach activity, activity time, activity location')(this.value='')"  onblur="if(this.value=='')(this.value='Seach activity, activity time, activity location')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${path1}activity/img/icon_facebook.jpg" alt="" /></a>
                        <a href="#"><img src="${path1}activity/img/icon_tweet.jpg" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="${path2}userMail/box/0.action"><img src="${path1}activity/img/icon_message1.jpg" alt="" width="22" height="22" /><span></span></a>
                            <a href="${path2}user/about.action"><img src="${path1}activity/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/police.action"><img src="${path1}activity/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/logout.action"><img src="${path1}activity/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="${path2}user/my.action"><%=user.getUserAlias()%></a><%}else{ %><a href="${path2}user/login.action">login</a><%} %></h3>
                            <span>${userProf.profCity}</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="main_search_content"><!--start main_search_content-->
                	<div id="search_lftcol"><!--start search_lftcol-->
                    	<div class="search_title"><!--start search_title-->
                        	<h2>SEARCH: ACTIVITY</h2>
                            
                        </div><!--//end .search_title-->

                    
    <c:forEach items="${activityList}" var="item" varStatus="index">    
                      <div class="search_post"><!--start search_post-->
                        	<div class="search_post_lft"><a href="${path2}activity/info.action?activityId=${item.activity.activityId} "><img src=${item.activity.activityPic} alt="" width=134 height=179/></a></div>
                            <div class="search_info"><!--start search_info-->
                            	<h2><a href="${path2}activity/info.action?activityId=${item.activity.activityId} ">${item.activity.activityName }</a></h2>
                                <p>${item.activity.activityCity } ,${item.activity.activityStreet }<br />Start Date: <fmt:formatDate value="${item.activity.activityStartTime}" type="date" dateStyle="medium"/> <br />End Date: <fmt:formatDate value="${item.activity.activityFinishTime}" type="date" dateStyle="medium"/></p>
                                <span><a href="${path2}activity/info.action?activityId=${item.activity.activityId} "><img src="${path1}activity/img/tripple_arrow3.jpg" alt="" /></a></span>
                                <ul>
				                    <li>
				                    <c:choose>
                                    <c:when test="${item.owner eq null}" >
                                	
                                	 <a id="at${index.index }"  href="javascript:joinActivity('${path2}activity/joinActivity.action?activityId=${item.activity.activityId }','at${index.index }')"><c:choose><c:when test="${item.joined eq true}">+ Unjoin</c:when> <c:otherwise>+ Join</c:otherwise></c:choose>
                                	</c:when >
                                	    <c:otherwise>
                                            you are the host
                                          </c:otherwise>
                                          </c:choose></li>
                                              <li> <a id="wishList${index.index }"  href="javascript:WishList('${path2}user/addWishList/city/${item.activity.activityId}.action','wishList${index.index}')"><c:choose><c:when test="${item.wish eq 'ok'}">+ Already Added</c:when> <c:otherwise>+ Wish List</c:otherwise></c:choose></li>
                                              <li> <a id="tribusList${index.index }"  href="javascript:TribusList('${path2}user/addTribusList/city/${item.activity.activityId}.action','tribusList${index.index}')"><c:choose><c:when test="${item.tribus eq 'ok'}">+ Already Added</c:when> <c:otherwise>+ Tribus List</c:otherwise></c:choose></li>
                                          </ul>
                            </div><!--//end .search_info-->
                        </div><!--//end .search_post-->
                      </c:forEach>
                      
                      
                        <div id="follower_apgi">
                            <a href="#" class="prev"> </a>
              <c:forEach items="${pageNumbers}" var="item">
             <%if(condition==null){ %>
             <a href="${path2}activity/byCityTag.action?page=${item}&city=${city}">
             <%}else{ %>
             <a href="${path2}activity/search.action?page=${item}&searchCondition=<%=condition %>"><%} %>
             ${item}
             </a>&nbsp
            </c:forEach>
            
                            <a href="#" class="next"></a>
                    	</div>
                    </div><!--//end #search_lftcol-->
                   
                </div><!--//end #main_search_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>
