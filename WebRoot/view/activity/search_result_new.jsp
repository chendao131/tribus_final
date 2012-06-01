<%@ page language="java" import="config.GlobleConfig,java.util.*,model.User,model.Activity,vo.UserCommentSupper,model.ActivityClassified" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		List<vo.UserCommentSupper> activityList= (List<vo.UserCommentSupper>)request.getAttribute("activityList");
		List<ActivityClassified> activityTagsList= (List<ActivityClassified>)request.getAttribute("activityTagsList");
		List<String> topTribusCity= (List<String>)request.getAttribute("topTribusCity");
		User user=(User)session.getAttribute("user");
		String tagName=(String) request.getAttribute("tagName");				
		request.setAttribute("domain",GlobleConfig.localhost);
		
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Tribus_tag_Activity_Final</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${domain}user/img/logo.png" alt="" width="59" height="65" /></a></div>
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
                    	<span class="space_btm"><a href="#"><img src="${domain}user/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${domain}user/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="${domain}user/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${domain}user/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${domain}user/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${domain}user/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain}user/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain}user/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3>
                        		${user.userAlias}
                        	</h3>
                            <span>
                            	${userProf.profCity }
                            </span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="main_content"><!--start main_content-->
            	<div id="content"><!--start content-->
                	<div id="container_tag_activity"><!--start container_tag_activity-->
                    	<h2 class="tag_title">Activities by Tags</h2>
                    	<div class="tag_btn_area"><!--start tag_btn_area-->
                        	<ul>
                            	<li><a href="#"><span><span>Entertainment</span></span></a></li>
                                <li><a href="#"><span><span>Education</span></span></a></li>
                                <li><a href="#"><span><span>Other</span></span></a></li>
                            </ul>
                        </div><!--//end .tag_btn_area-->
                        <div id="tag_gallery"><!--start tag_gallery-->
                        	
                        	
                        	<div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1">
                                    <img src="img/pic_tag1.jpg" alt="" width="111" height="111" />
                                    <h3>Chicago McCormick Place 2301...</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            
                            
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame2">
                                    <img src="img/pic_tag2.jpg" alt="" width="116" height="116" />
                                    <h3>Five Star Dessert Kitchen</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame3">
                                    <img src="img/pic_tag3.jpg" alt="" width="75" height="151" />
                                    <div class="tag_info"><p>iTupac Live: The Weird Logic of a Touring Hologram</p></div>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame4">
                                    <img src="img/pic_tag4.jpg" alt="" width="105" height="119" />
                                    <h3>THIS WEEKEND </h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame5">
                                    <img src="img/pic_tag5.jpg" alt="" width="136" height="95" />
                                    <h3>Countdown to a New Times Square</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame6">
                                    <img src="img/pic_tag6.jpg" alt="" width="157" height="80" />
                                    <h4>From Masa to Neta</h4>
                                    <h3>By Adam Platt Published Apr 15.</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature bg_differ_big"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame7">
                                    <img src="img/pic_tag7.jpg" alt="" width="156" height="158" />
                                    <h3>The John Cusack Affair</h3>
                                    <p>&nbsp; &nbsp; By Jada  <br />Robbing the Getty Museum with Malibu¡¯s most whimsical maniac.</p>
                                    <p>Minutes after meeting me at the Getty Villa in Malibu, John Cusack has concocted ... </p>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame8">
                                    <img src="img/pic_tag8.jpg" alt="" width="78" height="108" />
                                    <h4>Art Within Reach</h4>
                                </div>
                            </div><!--//end .tag_feature-->
                            
                            
                            
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame10">
                                    <img src="img/pic_tag11.jpg" alt="" width="119" height="84" />
                                    <h3>From Masa to Neta</h3>
                                    <span>Die! FML!</span>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="clear"></div>
                            <div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1">
                                    <img src="img/pic_tag1.jpg" alt="" width="111" height="111" />
                                    <h3>Chicago McCormick Place 2301...</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame2">
                                    <img src="img/pic_tag2.jpg" alt="" width="116" height="116" />
                                    <h3>Five Star Dessert Kitchen</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame3">
                                    <img src="img/pic_tag3.jpg" alt="" width="75" height="151" />
                                    <div class="tag_info"><p>iTupac Live: The Weird Logic of a Touring Hologram</p></div>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame4">
                                    <img src="img/pic_tag4.jpg" alt="" width="105" height="119" />
                                    <h3>THIS WEEKEND </h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame5">
                                    <img src="img/pic_tag5.jpg" alt="" width="136" height="95" />
                                    <h3>Countdown to a New Times Square</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                            <div class="tag_feature"><!--start tag_feature-->
                            	<div class="tag_icon">
                                	<a href="#"><img src="img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1 tag_frame6">
                                    <img src="img/pic_tag6.jpg" alt="" width="157" height="80" />
                                    <h4>From Masa to Neta</h4>
                                    <h3>By Adam Platt Published Apr 15.</h3>
                                </div>
                            </div><!--//end .tag_feature-->
                        </div><!--//end #tag_gallery-->
                    </div><!--//end #container_tag_activity-->
                </div><!--//end #content-->
                <div id="side_bar"><!--start side_bar-->
                	<div class="widget"><!--start widget-->
                    	<h2>Tag</h2>
                        <div class="widget_info">
                        	<p class="upper"><a href="#">Music / Sports / Starwar</a> <a href="#">Education / Boardway</a> <a href="#">Shows / </a></p>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Citys</h2>
                        <div class="widget_info space_widget_info">
                        	<ul>
                            	<li><a href="#">CHICAGO</a></li>
                                <li class="color1"><a href="#">TORONTO</a></li>
                                <li><a href="#"><small>NEWYORK </small></a></li>
                                <li><a href="#"><span>LASVEGAS</span></a></li> 
                            </ul>
                            <small><a href="#">Add</a> / <a href="#">Change</a> Locations</small>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Guess You Like</h2>
                        <div class="widget_gallery">
                        	<img src="img/pic_widget1.jpg" alt="" width="222" height="133" />
                            <h3><a href="#">Mandee Monet Presents the Triumph of A Broken. ..</a></h3>
                        </div>
                        <div class="widget_gallery">
                        	<img src="img/pic_widget2.jpg" alt="" />
                            <h3><a href="#">Amazing Typos for you </a></h3>
                        </div>
                        <div class="widget_gallery">
                        	<img src="img/pic_widget3.jpg" alt="" />
                            <h3><a href="#">TSA 2012 Motorsports</a></h3>
                        </div>
                        <div class="next_big_img"><a href="#"><img src="img/tripple_arrow3.jpg" alt="" width="25" height="8" /></a></div>
                    </div><!--//end .widget-->
                </div><!--//end #side_bar-->
            </div><!--//end #main_content-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="js/smoothscroll.js" type="text/javascript"></script>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   