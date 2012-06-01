<%@ page language="java" import="java.util.*,model.ActivityPic,model.User"
	pageEncoding="utf-8"%><%@page import="model.ActivityPic"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		ActivityPic activityPic=(ActivityPic)request.getAttribute("activityPics");
		Integer p=0;
		User user=(User)request.getAttribute("user");

%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>ShowPic</title>

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
                        	<li class="album">By Sheng Yu (<a href="#">Albums</a>)</li>  
                            <li>${activityPics.recordDate}</li>
                        </ul>
                    </div>cc
                    <div id="gallery"><!--start gallery-->
                    	<h2>${albumName }</h2>
                        <div id="banner_pic_page">
                        	<img src="${activityPics.picPath }" alt="" width=600 height=365/>
                        </div>
                        <div id="slbum_descript">
                        	<p>${activityPics.picDescription }</p>
                        </div>
                        <div id="pic_wish_list"><!--start pic_wish_list-->
                        	<div id="pic_wish_inner">
                            	<div class="pic_social">
                                	<a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" /></a>
                                	<a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" /></a>
                                </div>
                                <ul>
                                	<li><a href="#">+ Wish List</a></li>
                                	<li><a href="#">+Tribus List</a></li>
                                </ul>
                                <span><a href="#"><img src="../tribus/view/activity/img/icon_album.jpg" alt="" /></a></span>
                            </div>
                        </div><!--//end #pic_wish_list-->
                        <div id="comment_box_area"><!--start comment_box_area-->
                        
                        	<div class="comment_box"><!--start comment_box-->
                            	<div class="arrow_author2"></div>
                            	<div class="comment_first_content"><!--start comment_first_content-->
                                	<div class="pic_author">
                                    	<div class="arrow_author1"></div>
                                        <img src="../tribus/view/activity/img/pic_author1.png" alt="" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<small>Name Says : ...</small>
                                        </div>
                                        <div class="author_icon">
                                        	<span><img src="../tribus/view/activity/img/icon_album.jpg" alt="" /></span>
                                            <div class="btn_done"><a href="#">Done</a></div>
                                        </div>
                                    </div>
                                </div><!--//end .comment_first_content-->
                            </div><!--//end .comment_box-->
                            
                            <div class="comment_box bg_differ"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="../tribus/view/activity/img/pic_author2.png" alt="" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3>Pierce Tetro Cooper (New York)</h3>
                                            <p>Am I actually goin to see you there or are you too big<br />time to mingle wit us little people?</p>
                                            <span>Feb.18, 2012  at 1:43pm</span>
                                        </div>
                                    </div>
                                </div><!--//end .comment_scnd_content-->
                            </div><!--//end .comment_box-->
                         
                         
                            
                            <div class="comment_box"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="../tribus/view/activity/img/pic_author3.png" alt="" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3>Pierce Tetro Cooper (New York)</h3>
                                            <p>Am I actually goin to see you there or are you too big<br />time to mingle wit us little people?</p>
                                            <span>Feb.18, 2012  at 1:43pm</span>
                                        </div>
                                    </div>
                                </div><!--//end .comment_scnd_content-->
                            </div><!--//end .comment_box-->
                            
                       
                            
                        </div><!--//end #comment_box_area-->
                        <div id="comment_pagination">
                        	<a href="#" class="prev"></a>
                            <a href="#">1</a>
                            <a href="#">2</a>
                            <a href="#" class="next"></a>
                        </div>
                    </div><!--//end #gallery-->
                </div><!--//end #album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
	</body>
</html>
