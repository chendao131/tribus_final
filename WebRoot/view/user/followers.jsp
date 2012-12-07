<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c" %><%
%><%@ page import="config.GlobleConfig" %>
<%
	request.setAttribute("domain",GlobleConfig.localhost);
	request.setAttribute("no_view_url",GlobleConfig.show_local);
 %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Tribus_Followers</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain }user/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain }user/css/style.css" />
    
</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="index.html"><img src="${domain }user/img/logo.png" alt="" width="59" height="65" /></a></div>
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
                    	<span class="space_btm"><a href="#"><img src="img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="${domain }user/img/icon_facebook.jpg" alt=""  width="24" height="24" ></a>
                        <a href="#"><img src="${domain }user/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${domain }user/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${domain }user/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain }user/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain }user/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3>${user.userAlias }</h3>
                            <span>${userProf.profCity}</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="follower_title"><!--start follower_title-->
                	<h2>Followers </h2>
                    <div id="title_follower_row">
                    	<div id="pic_follower"><img src="img/pic_flower1.jpg" alt="" width="78" height="78" /></div>
                        <div id="people_follow">
                        	<p>( <span></span> ) People followed ${user.userAlias }</p>
                            <a href="#"><img src="img/icon_album.jpg" alt="" width="20" height="19" /></a>
                        </div>
                    </div>
                </div><!--//end #follower_title-->
                <div id="follower_gallery_cont"><!--start follower_gallery_cont-->
                	<div id="follower_gallery"><!--start follower_gallery-->
                    	
                    	
                    	<c:forEach items="${friends}" var="f">
                    		<div class="follower_widget"><!--start follower_widget-->
                        		<a href="#"><img src="${f.userPic}" alt="" width="48" height="48" /></a>
                            	<h3><a href="#">
                            		${f.userName }
                            	</a></h3>
                        	</div><!--//end .follower_widget-->
                    	</c:forEach>
                    	
                        
                                               
                    </div><!--//end #follower_gallery-->
                    <div id="follower_apgi">
                    	<a href="#" class="prev"> </a>
                    	
                    	<c:forEach items="${paging}" var="i" varStatus="in">
                    		<a href="getAllFriends/${inOrOut}/${in.index}.action">${in.index}</a>  
                    	</c:forEach>
                    	                    
                        <a href="#" class="next"></a>
                    </div>
                </div><!--//end #follower_gallery_cont-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   