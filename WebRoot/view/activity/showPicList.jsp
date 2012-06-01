<%@ page language="java" import="java.util.*,model.ActivityPic,model.User"
	pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)request.getAttribute("user");
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'showPic.jsp' starting page</title>

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
            	<div id="album_content"><!--start album_content-->
                	<div id="album_type">
                    	<ul>
                        	<li class="album"></li>  
                            <li><a href="#">Upload New Picture</a></li>
                        </ul>
                    </div>
                    <div id="gallery"><!--start gallery-->
                    	<h2>${activityAlbum.albumName }</h2>
                        <div id="album_img">
                         
                          <c:forEach items="${activityPics}" var="item">
                            <a href="activity/showPic.action?activityPicId=${item.picId}&albumName=${activityAlbum.albumName}"><img src="${item.picPath}" alt="" width="192" height="192"/></a>
</c:forEach>
                        </div>
                        <div id="slbum_descript">
                        	<p>${activityAlbum.albumDescription }</p>
                        </div>
                        <div id="album_pagination_area">
                        	<div id="album_pagi">
                            	<a href="#" class="prev"></a>
                                 <c:forEach items="${pageNumbers}" var="item">  
             <a href="activity/showPicList.action?page=${item}&albumId=${activityAlbum.albumId}">
             ${item}
             </a>&nbsp
            </c:forEach>
                                <a href="#" class="next"></a>
                            </div>
                            <div id="album_social">
                            	<a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" /></a>
                                <a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" /></a>
                                <span></span>
                                <span></span>
                                <small><a href="#"><img src="../tribus/view/activity/img/icon_album.jpg" alt="" /></a></small>
                            </div>
                        </div>
                    </div><!--//end #gallery-->
                </div><!--//end #album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
	</body>
</html>
