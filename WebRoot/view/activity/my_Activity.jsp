<%@ page language="java" import="java.util.*,model.User,vo.MyActivity,model.Activity" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<User> friends=(List<User>) request.getAttribute("friends");
User user=(User)session.getAttribute("user");
List<MyActivity> activity=(List<MyActivity>) request.getAttribute("activityList");
String detail=null;
String condition=(String)request.getAttribute("condition");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'my_Activity.jsp' starting page</title>
    
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
    <link rel="stylesheet" type="text/css" media="screen,projection" href="../tribus/view/activity/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->
 
 
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
 <div id="wrapper">=$<!--start wrapper-->
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
                	<form action="#">
                    	<p class="search_text"><input type="text" value="Search by tag" onclick="if(this.value=='Search by tag')(this.value='')"  onblur="if(this.value=='')(this.value='Search by tag')" /></p>
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
            <div id="main_content"><!--start main_content-->
            	<div id="content"><!--start content-->
                	<div id="title_my_activity"><!--start title_my_activity-->
                    	<h2>More Activity By <%if(user!=null){ %><a href="user/my/<%=user.getUserId()%>">welcome back,<%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></h2>
                    	<div id="fiona_inner">
                            <img src="../tribus/view/activity/img/pic_activity.jpg" alt="" />
                            <div class="activity_rgt">
                                <a href="#" class="activity_btn1"></a>
                                <a href="#" class="activity_btn2">This Week</a>
                                <a href="#" class="activity_btn2">Next Week</a>
                                <a href="#" class="activity_btn3"></a>
                            </div>
                        </div>
                    </div><!--//end #title_my_activity-->
               
            <%for(int i=0;i<activity.size();i++) {%>
                                   <div class="activity_common_box"><!--start activity_common_box-->
                    	<div class="activity_bg"><div class="activity_top"><div class="activity_btm">
                        	<div class="scnd_activity_cont"><!--start scnd_activity_cont-->
                            	<div class="red_arrow"></div>
                            	<div class="activity_feature_lft"><!--start activity_feature_lft-->
                                	<a href="activity/info.action?activityId=<%=activity.get(i).getActivity().getActivityId() %>"><img src="<%=activity.get(i).getActivity().getActivityPic() %>" alt="" width=118 height=161/></a>
                                    <div class="activity_descript"><!--start activity_descript-->
                                    	<h2><a href="activity/info.action?activityId=<%=activity.get(i).getActivity().getActivityId() %>"><%=activity.get(i).getActivity().getActivityName() %></a></h2>
                                        <p><%if(activity.get(i).getActivity().getActivityDetail().length()>50){detail=activity.get(i).getActivity().getActivityDetail().substring(0,51)+" ..." ;}else{%><%detail=activity.get(i).getActivity().getActivityDetail();} %>
                                        <%=detail %></p>
                                        <p>By <a href="user/my/<%=activity.get(i).getUser().getUserId() %>"><%=activity.get(i).getUser().getUserAlias() %></a> Published <%=activity.get(i).getActivity().getRecordDate() %> </p>
                                        <div class="feature_social"><!--start feature_social-->
                                            <div class="activity_icon">
                                                <a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" /></a>
                                                <a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" /></a>
                                            </div>
                                            <div class="feature_list">
                                                <ul>
                          <li>
                              
                               
				                   <%if(activity.get(i)!=null &&activity.get(i).getOwner()==null) {%>
				<input id="bt<%=i%>" type="button" name="Submit" value="<%if(activity.get(i).getFollowed()!=null &&activity.get(i).getFollowed().equals("true")){%>unfollow<%}else{%>Follow<%}%>" 
			onclick="followActivity('activity/followActivity.action?activityId=<%=activity.get(i).getActivity().getActivityId() %>','bt<%=i %>')"/><%}else{ %>you are the host<%} %>
				                
				                     </li>
				                    
				                    <li>
				                  <%if(activity.get(i)!=null && activity.get(i).getOwner()==null) { %>
				                  <input id="at<%=i%>" type="button" name="Submit" value="<%if(activity.get(i).getJoined()!=null &&activity.get(i).getJoined().equals("true")){%>unjoin<%}else{%>join<%}%>" 
			onclick="joinActivity('activity/joinActivity.action?activityId=<%=activity.get(i).getActivity().getActivityId() %>','at<%=i %>')"/> <%}else{ %>you are the host<%} %>  
				                    
				                    </li>
                                                </ul>
                                            </div>
                                        </div><!--//end .feature_social-->
                                    </div><!--//end .activity_descript-->
                                </div><!--//end .activity_feature_lft-->
                                <div class="activity_feature_rgt">
                                	<ul>
                                	<%for(int p=0;p<activity.get(i).getUserComments().size();p++){ %>
                                        <li><span><a href ="user/my/<%=activity.get(i).getUser().getUserId() %>"><img src="<%=activity.get(i).getUserComments().get(p).getUserPic() %>" alt="" width=34 height=34/></a></span><small>: <%=activity.get(i).getUserComments().get(p).getUserComment() %></small></li>
                                       
                              <% if(p==3){break;}}%>
                                    </ul>
                                </div>
                            </div><!--//end .scnd_activity_cont-->
                        </div></div></div>
                    </div><!--//end .activity_common_box-->
               
               
          <%} %>
               
                                      <div id="follower_apgi">
                            <a href="#" class="prev"> </a>
              <c:forEach items="${pageNumbers}" var="item">  
             <a href="activity/myActivity.action?page=${item}">
             ${item}
             </a>&nbsp
            </c:forEach>
            
                            <a href="#" class="next"></a>
                    	</div>
                </div><!--//end #content-->
               
                <div id="side_bar"><!--start side_bar-->
                    <div class="widget"><!--start widget-->
                    	<h2>Followers</h2>
                        <div class="small_img_widget"><!--start small_img_widget-->
                        	<c:forEach items="${friends}" var="item">
                        	<a href="user/my/${item.userId }"><img src="${item.userPic}" alt="" width=34 height=34/></a>
                          </c:forEach>
                            <div class="clear"></div>
                        </div><!--//end .small_img_widget-->
                        <div class="next_small_img"><a href="#"><img src="../tribus/view/activity/img/tripple_arrow3.jpg" alt="" /></a></div>
                    </div><!--//end .widget-->
                   
                   
                    <div class="widget"><!--start widget-->
                    	<h2>Guess You Like</h2>
                    	<c:forEach items="${recommentActivity}" var="item">
                        <div class="widget_gallery">
                        	<a href="activity/info.action?activityId=${item.activityId }"><img src="${item.activityPic }" alt="" width=222 height=133/></a>
                            <h3><a href="activity/info.action?activityId=${item.activityId}">${item.activityName }</a></h3>
                        </div>
                       </c:forEach>
                       
                        <div class="next_big_img"><a href="#"><img src="../tribus/view/activity/img/tripple_arrow3.jpg" alt="" /></a></div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Citys</h2>
                        <div class="widget_info">
                        	<ul>
                        	<c:forEach items="${topTribusCity}" var="item">
                            	<li><a href="activity/byCityTag.action?city=${item}">${item}</a></li>
                               </c:forEach>
                            </ul>
                         
                        </div>
                    </div><!--//end .widget-->
                </div><!--//end #side_bar-->
                
            </div><!--//end #main_content-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
  </body>
</html>
