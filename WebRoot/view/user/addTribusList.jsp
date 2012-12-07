<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c" %><%
%><%@ page import="config.GlobleConfig" %>
<%
	request.setAttribute("domain",GlobleConfig.localhost);
	request.setAttribute("no_view_url",GlobleConfig.show_local);
 %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Tribus_creat_my_own_tribus_list</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain }user/css/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain }user/css/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${domain }user/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${no_view_url}activity/index.action">EVENT</a></li>
                    	<li><a href="${no_view_url}movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${no_view_url}movie/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${no_view_url}movie/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li class="current_page_item"><a href="${no_view_url}user/my.action" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${domain }user/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${domain }user/img/icon_header2.png" alt="" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="#">
                    	<p class="search_text"><input type="text" value="Seach movie, actors, comment, tribus music list" onclick="if(this.value=='Seach movie, actors, comment, tribus music list')(this.value='')"  onblur="if(this.value=='')(this.value='Seach movie, actors, comment, tribus music list')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${domain }user/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${domain }user/img/icon_tweet.jpg" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${domain }user/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${domain }user/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain }user/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain }user/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3>Jeremy Guan</h3>
                            <span>New York City</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="album_content" class="create_list"><!--start album_content-->
                    <div id="gallery"><!--start gallery-->
                    	<h2 class="create_title">Create your own Tribus list</h2>
                        <div class="comment_box"><!--start comment_box-->
                        	<div class="arrow_create"></div>
                            <div class="create_box"></div>
                        </div><!--//end .comment_box-->
                        <div id="create_input_field_area"><!--start create_input_field_area-->
                        	<form action="#">
                                <div class="create_txt">
                                	<label>List name:</label>
                                    <input type="text" />
                                </div>
                                <div class="list_intro">
                                	<label>List Introduction</label>
                                    <textarea rows="10" cols="10"></textarea>
                                </div>
                                <div class="creat_search">
                                	<p class="create_txt_field"><input type="text" value="Search the name of the movie" onclick="if(this.value=='Search the name of the movie')(this.value='')" onblur="if(this.value=='')(this.value='Search the name of the movie')"  /></p>
                                </div>
                                <div class="list_intro">
                                    <textarea rows="10" cols="10" onclick="(this.value=='Add description')(this.value='')" onblur="(this.value=='')(this.value='Add description')">Add description</textarea>
                                </div>
                                <div class="create_btn_area">
                                	<a href="#">Add to list</a>
                                    <a href="#">Done</a>
                                </div>
                            </form>
                            <div id="tribus_list">
                            	<h3>Sheng¡¯s Tirbus List</h3>
                                <ul>
                                <c:forEach items="${list}" var="l">
                                
                                </c:forEach>
                                	<li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                    <li><a href="#">Let's Make Love (1960)</a></li>
                                </ul>
                            </div>
                        </div><!--//end #create_input_field_area-->
                    </div><!--//end #gallery-->
                </div><!--//end #album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="js/smoothscroll.js" type="text/javascript"></script>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   