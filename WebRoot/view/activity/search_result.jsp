<%@ page language="java" import="java.util.*,model.User,vo.SuperActivity" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<SuperActivity> activityList=(List<SuperActivity>)request.getAttribute("activityList");
		User user=(User)session.getAttribute("user");	
		String condition=(String)request.getAttribute("condition");
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'search_result.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	    <link rel="stylesheet" type="text/css" media="screen,projection" href="../tribus/view/activity/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="../tribus/view/activity/css/style.css" />
<script type="text/javascript">
function search(){
var a =document.getElementById("txt1");
window.location.href="activity/search.action?searchCondition="+a.value;
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
            document.getElementById(event1+"").value=http_request.responseText;	//设置提示内容

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
        	<div class="logo"><a href="index.html"><img src="../tribus/view/activity/img/logo.png" alt="" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li class="current_page_item"><a href="#">CITY</a></li>
                    	<li><a href="#" title="MOVIE">MOVIE</a></li>
                        <li><a href="#" title="BOOK">BOOK</a></li>
                        <li><a href="#" title="MUSIC">MUSIC</a></li>
                        <li><a href="#" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="../tribus/view/activity/img/icon_header1.png" alt="" /></a></span>
                        <span><a href="#"><img src="../tribus/view/activity/img/icon_header2.png" alt="" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="activity/search.action">
                    	<p class="search_text"><input id="searchCondition" name="searchCondition" type="text" value="Seach activity, activity time, activity location" onclick="if(this.value=='Seach activity, activity time, activity location')(this.value='')"  onblur="if(this.value=='')(this.value='Seach activity, activity time, activity location')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" /></a>
                        <a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="../tribus/view/activity/img/icon_message1.jpg" alt="" /><span>5</span></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message2.jpg" alt="" /></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message3.jpg" alt="" /></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message4.jpg" alt="" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="user/my/<%=user.getUserId()%>">welcome back,<%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></h3>
                            <span>New York City</span>
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
                        	<div class="search_post_lft"><a href="activity/info.action?activityId=${item.activity.activityId} "><img src=${item.activity.activityPic} alt="" width=134 height=179/></a></div>
                            <div class="search_info"><!--start search_info-->
                            	<h2><a href="activity/info.action?activityId=${item.activity.activityId} ">${item.activity.activityName }</a></h2>
                                <p>${item.activity.activityCity } ,${item.activity.activityStreet }<br />Start Date: ${item.activity.activityStartTime}<br/>End Date: ${item.activity.activityFinishTime }</p>
                                <span><a href="activity/info.action?activityId=${item.activity.activityId} "><img src="../tribus/view/activity/img/tripple_arrow3.jpg" alt="" /></a></span>
                                <ul>
                                <li>
                                <c:choose>
                                	<c:when test="${item.owner == null}" >
                                	<input id="bt${index.index }" type="button" name="Submit" value=   <c:choose><c:when test="${item.followed eq true}">unFollow </c:when> <c:otherwise>Follow</c:otherwise></c:choose>
			onclick="followActivity('activity/followActivity.action?activityId=${item.activity.activityId }','bt${index.index }')"/>
                                	</c:when >
                                	    <c:otherwise>
                                            you are the host
                                          </c:otherwise>
				                   
				                    </c:choose>
				                     </li>
				                    
				                    <li>
				                    <c:choose>
                                    <c:when test="${item.owner eq null}" >
                                	<input id="at${index.index }" type="button" name="Submit" value=<c:choose><c:when test="${item.joined eq true}">unJoin </c:when> <c:otherwise>Join</c:otherwise></c:choose>
			onclick="joinActivity('activity/joinActivity.action?activityId=${item.activity.activityId }','at${index.index }')"/>
                                	</c:when >
                                	    <c:otherwise>
                                            you are the host
                                          </c:otherwise>
                                          </c:choose></li></ul>
                            </div><!--//end .search_info-->
                        </div><!--//end .search_post-->
                      </c:forEach>
                      
                      
                        <div id="follower_apgi">
                            <a href="#" class="prev"> </a>
              <c:forEach items="${pageNumbers}" var="item">
             <%if(condition==null){ %>
             <a href="activity/byCityTag.action?page=${item}&city=${city}">
             <%}else{ %>
             <a href="activity/search.action?page=${item}&searchCondition=<%=condition %>"><%} %>
             ${item}
             </a>&nbsp
            </c:forEach>
            
                            <a href="#" class="next"></a>
                    	</div>
                    </div><!--//end #search_lftcol-->
                    <div id="search_rgt"><!--start search_rgt-->
                    	<div class="search_widget"><!--start search_widget-->
                        	<img src="../tribus/view/activity/img/pic_search_widget1.jpg" alt="" />
                        </div><!--//end .search_widget-->
                        <div class="search_widget"><!--start search_widget-->
                        	<img src="../tribus/view/activity/img/pic_search_widget2.jpg" alt="" />
                        </div><!--//end .search_widget-->
                        <div class="search_widget"><!--start search_widget-->
                        	<img src="../tribus/view/activity/img/pic_search_widget3.jpg" alt="" />
                            <h2>Insider: Kerry Tribe at The Power Plant</h2>
                            <strong>April 3rd, 2012 7:00pm-9:00pm</strong>
                            <p>Join the Ministry at The Power Plant Contemporary Art Gallery for a special viewing of the film works 
                            of Kerry Tribe and a discussion with the museum...</p>
                            <a href="#">More info...</a>
                        </div><!--//end .search_widget-->
                        <div class="search_widget"><!--start search_widget-->
                        	<img src="../tribus/view/activity/img/pic_search_widget4.jpg" alt="" />
                            <h2>Dialogue: Brendan Monroe at Cooper Cole</h2>
                            <strong>March 3rd, 2012 3:00pm-4:00pm</strong>
                            <p>Join the Ministry of artist talk with internationally exhibited artist Brendan Monroe...</p>
                            <a href="#">More info...</a>
                        </div><!--//end .search_widget-->
                        <div class="search_widget"><!--start search_widget-->
                        	<img src="../tribus/view/activity/img/pic_search_widget5.jpg" alt="" />
                            <h2>Studio: Niall McClelland</h2>
                            <strong>February 21st,6:00pm-8:00pm</strong>
                            <p>For the past five years, Niall McClelland has been exhibiting his art in numerous galleries across North america. 
                            A true multidisciplinary artist, McC...</p>
                            <a href="#">More info...</a>
                        </div><!--//end .search_widget-->
                    </div><!--//end #search_rgt-->
                </div><!--//end #main_search_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>
