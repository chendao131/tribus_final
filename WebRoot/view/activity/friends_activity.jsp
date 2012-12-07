<%@ page language="java" import="java.util.*,model.User,vo.MyActivity,model.Activity,config.GlobleConfig" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<User> friends=(List<User>) request.getAttribute("friends");
User user=(User)session.getAttribute("user");
List<MyActivity> activity=(List<MyActivity>) request.getAttribute("activityList");
String detail=null;
String condition=(String)request.getAttribute("condition");

request.setAttribute("path1",GlobleConfig.pathPath);
request.setAttribute("path2",GlobleConfig.pathPath1);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'friends_activity.jsp' starting page</title>
    
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
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->
 
 
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
        	<div class="logo"><a href="${path2}activity/index.action"><img src="${path1}activity/img/logo.png" alt="" width="59" height="65" /></a></div>
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
                    	<span class="space_btm"><a href="#"><img src="${path1}activity/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${path1}activity/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="#">
                    	<p class="search_text"><input type="text" value="Search by tag" onclick="if(this.value=='Search by tag')(this.value='')"  onblur="if(this.value=='')(this.value='Search by tag')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${path1}activity/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${path1}activity/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${path1}activity/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${path1}activity/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${path1}activity/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${path1}activity/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="user/my.action"><%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></h3>
                            <span>${userProf.profCity}</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="main_content"><!--start main_content-->
            	<div id="content"><!--start content-->
                	<div id="title_my_activity"><!--start title_my_activity-->
                    	<h2>More Activity By Friends </h2>
                    	<div id="fiona_inner">
                    	  <div class="activity_rgt">
                                <a href="#" class="activity_btn1"></a>
                                <a href="#" class="activity_btn2"></a>
                                <a href="#" class="activity_btn2"></a>
                                <a href="#" class="activity_btn3"></a>
                            </div>
                        </div>
                    </div><!--//end #title_friends_activity-->
                    <div class="activity_common_box"><!--start activity_common_box-->
                    	<div class="activity_bg"><div class="activity_top"><div class="activity_btm">
                        	
                        	<c:forEach items="${activityList}" var="item">
                        	<div class="activity_first_content"><!--start activity_first_content-->
                            	<div class="activity_info">
                                	<p>${item.activity.activityDetail }</p>
                                    <h5>${item.activity.activityApt }</h5>
                                    <span>Fee: ${item.activity.activityFees } $</span>
                                    <div class="feature_social"><!--start feature_social-->
                                        <div class="activity_icon">
                                            <a href="#"><img src="${path1}activity/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                                            <a href="#"><img src="${path1}activity/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                                        </div>
                                        <div class="feature_list">
                                            <ul>
                                               
                                               
                                                 <li><!-- follow按钮一 -->
                                <c:choose>
                                	<c:when test="${item.owner == null}" >
                                	<input id="bt${index.index }" type="button" name="Submit" value=   <c:choose><c:when test="${item.followed eq true}">unFollow </c:when> <c:otherwise>Follow</c:otherwise></c:choose>
			onclick="followActivity('${path2}activity/followActivity.action?activityId=${item.activity.activityId }','bt${index.index }')"/>
                                	</c:when >
                                	    <c:otherwise>
                                            you are the host
                                          </c:otherwise>
				                   
				                    </c:choose>
				                     </li>
				                    
				                    <li><!-- join按钮一 -->
				                    <c:choose>
                                    <c:when test="${item.owner eq null}" >
                                	<input id="at${index.index }" type="button" name="Submit" value=<c:choose><c:when test="${item.joined eq true}">unJoin </c:when> <c:otherwise>Join</c:otherwise></c:choose>
			onclick="joinActivity('${path2}activity/joinActivity.action?activityId=${item.activity.activityId }','at${index.index }')"/>
                                	</c:when >
                                	    <c:otherwise>
                                            you are the host
                                          </c:otherwise>
                                          </c:choose>
                                          
                                         </li>
                                            </ul>
                                        
                                        
                                        
                                        </div>
                                    </div><!--//end .feature_social-->
                                </div>
                                <div class="activity_feature_img">
                                	<img src="${item.activity.activityPic }" alt="" width="189" height="116" />
                                </div>
                            </div><!--//end .activity_first_content-->
                      </c:forEach>
                      
                        </div></div></div>
                    </div><!--//end .activity_common_box-->
                    
                    <div class="activity_common_box"><!--start activity_common_box-->
                    	<div class="activity_bg"><div class="activity_top"><div class="activity_btm">
                    	  <!--//end .activity_first_content-->
</div>
                    	</div></div>
                    </div><!--//end .activity_common_box-->
                    
                    <div class="activity_common_box"><!--start activity_common_box-->
                    	<div class="activity_bg"><div class="activity_top"><div class="activity_btm">
                    	  <!--//end .scnd_activity_cont-->
</div>
                    	</div></div>
                    </div><!--//end .activity_common_box-->
                    
                    <div class="activity_common_box"><!--start activity_common_box-->
                    	<div class="activity_bg"><div class="activity_top"><div class="activity_btm">
                    	  <!--//end .scnd_activity_cont-->
</div>
                    	</div></div>
                    </div><!--//end .activity_common_box-->
                    <!--//end .mid_white_box-->
<div class="activity_common_box"><!--start activity_common_box-->
                    	<div class="activity_bg"><div class="activity_top"><div class="activity_btm">
                    	  <!--//end .activity_first_content-->
</div>
                    	</div></div>
                  </div><!--//end .activity_common_box-->
                    
                </div><!--//end #content-->
                <div id="side_bar"><!--start side_bar-->
                  <!--//end .widget-->
<div class="widget"><!--start widget-->
                   	<h2>Guess You Like</h2>
                    	<c:forEach items="${recommentActivity}" var="item">
                        <div class="widget_gallery">
                        	<a href="${path2}activity/info.action?activityId=${item.activityId }"><img src="${item.activityPic }" alt="" width=222 height=133/></a>
                            <h3><a href="${path2}activity/info.action?activityId=${item.activityId}">${item.activityName }</a></h3>
                        </div>
                       </c:forEach>
                       
                        <div class="next_big_img"><a href="#"><img src="${path1}activity/img/tripple_arrow3.jpg" alt="" /></a></div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Citys</h2>
                        <div class="widget_info">
                        	<ul>
                        	<c:forEach items="${topTribusCity}" var="item">
                            	<li><a href="${path2}activity/byCityTag.action?city=${item}">${item}</a></li>
                               </c:forEach>
                            </ul>
                         
                        </div>
                    </div><!--//end .widget-->
              </div><!--//end #side_bar-->
            </div><!--//end #main_content-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
  </body>
</html>
