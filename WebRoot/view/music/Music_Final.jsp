<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" import="config.*"%>
<%
	request.setAttribute("my_local",GlobleConfig.my_local);
	request.setAttribute("my_domain",GlobleConfig.my_domain);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Music Home</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local}/music/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${my_domain}/activity/index.action">EVENT</a></li>
                    	<li><a href="${my_domain}/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain}/book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li class="current_page_item"><a href="${my_domain}/music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li><a id="bb" onMouseOver="get()" style="display:block" href="${my_domain}/user/my.action" title="MY TRIBUS">MY TRIBUS</a>
						<a id="aa" onMouseOut="bu()" style="font-size:24px; display:none"  href="${my_domain}/user/my.action" title="MY TRIBUS">MyTRIBUS</a></li>
                    </ul>
                    <div class="header_search">
						<form action="${my_domain}/user/ searchAll.action">
                         	<p class="txt_header"><input type="text" name="search" value="" /></p>
                            <p class="submit_header"><input type="submit"  value="" /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${my_local}/music/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="${my_domain }/user/editForm.action"><img src="${my_local}/music/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${my_domain }/music/search/1.action" id="single_search_bar" name="single_search_bar">
                    	<p class="search_text"><input name="single_search_name" type="text" value="Seach for music, celebrity, tribus list" onclick="if(this.value=='Seach for music, celebrity, tribus list')(this.value='')"  onblur="if(this.value=='')(this.value='Seach for music, celebrity, tribus list')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="https://www.facebook.com/TheTribus"><img src="${my_local}/music/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local}/music/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="${my_domain }/userMail/box/0.action"><img src="${my_local }/movie/img/icon_message1.jpg" alt="" width="22" height="22" /><c:if test="${unreadMail > 0}"><span><c:out value="${unreadMail}"/></span></c:if></a>
                            <a href="${my_domain }/user/police.action"><img src="${my_local }/movie/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${my_domain }/user/about.action"><img src="${my_local }/movie/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${my_domain }/user/logout.action"><img src="${my_local }/movie/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><c:out value="${userName}" /></h3>
                            <span><c:out value="${userCity }" /></span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="main_content"><!--start main_content-->
            	<div id="content"><!--start content-->
                	<div id="content_book_final"><!--start content_book_final-->
                    	<div id="book_gallery_area"><!--start book_gallery_area-->
                            <h2 class="title_movie">Recent Hot Muisc</h2>
                            <div id="music_widget_area"><!--start music_widget_area-->
                            <ul id="slider1"><li>
                            	<c:forEach items="${recentHotMusics_1}" var="thisMusic">
                                <div class="music_widget"><!--start music_widget-->
                                	<div class="music_title">
                                    	<h2><c:out value="${thisMusic.musicName}" /> </h2>
                                        <div class="play_music"><a href="#"></a></div>
                                    </div>
                                    <div class="music_twocol"><!--start music_twocol-->
                                    	<div class="music_lftcol"><a href="${my_domain }/music/${thisMusic.musicId }.action"><img src="${thisMusic.musicPic}" alt="" width="88" height="89" /></a></div>
                                        <div class="music_rgtcol">
                                        	<h3>Singer:</h3>
                                            <h4><c:out value="${thisMusic.singerName}" /></h4>
                                            <small><c:out value="${thisMusic.musicPublishDate}" /></small>
                                            <div class="music_rating">
                                            	<span>Rating:</span>
                                                <c:choose>
		                                		<c:when test="${thisMusic.musicRating>=0.5 && thisMusic.musicRating<1.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=1.5 && thisMusic.musicRating<2.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=2.5 && thisMusic.musicRating<3.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=3.5 && thisMusic.musicRating<4.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=4.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:otherwise>
				                               		<a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:otherwise>
				                           	</c:choose>
		                                    <c:out value="${singlePageMain.itemAveGrade}" />
                                            </div>
                                        </div>
                                    </div><!--//end .music_twocol-->
                                </div><!--//end .music_widget-->
                                </c:forEach> 
                                
                                <div class="clear"></div>
                                </li>
                                
                                <li>
                            	<c:forEach items="${recentHotMusics_2}" var="thisMusic">
                                <div class="music_widget"><!--start music_widget-->
                                	<div class="music_title">
                                    	<h2><c:out value="${thisMusic.musicName}" /> </h2>
                                        <div class="play_music"><a href="#"></a></div>
                                    </div>
                                    <div class="music_twocol"><!--start music_twocol-->
                                    	<div class="music_lftcol"><a href="${my_domain }/music/${thisMusic.musicId }.action"><img src="${thisMusic.musicPic}" alt="" width="88" height="89" /></a></div>
                                        <div class="music_rgtcol">
                                        	<h3>Singer:</h3>
                                            <h4><c:out value="${thisMusic.singerName}" /></h4>
                                            <small><c:out value="${thisMusic.musicPublishDate}" /></small>
                                            <div class="music_rating">
                                            	<span>Rating:</span>
                                                <c:choose>
		                                		<c:when test="${thisMusic.musicRating>=0.5 && thisMusic.musicRating<1.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=1.5 && thisMusic.musicRating<2.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=2.5 && thisMusic.musicRating<3.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=3.5 && thisMusic.musicRating<4.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisMusic.musicRating>=4.5}">
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:otherwise>
				                               		<a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
				                               </c:otherwise>
				                           	</c:choose>
                                            </div>
                                        </div>
                                    </div><!--//end .music_twocol-->
                                </div><!--//end .music_widget-->
                                </c:forEach> 
                                
                                <div class="clear"></div>
                                </li>
                                
                                </ul>
                            </div><!--//end #music_widget_area-->
                        </div><!--//end #book_gallery_area-->
                        <div id="book_rating"><!--start book_rating-->
                        	<h2>Friend's Footprint</h2>
                            <ul id="slider2"><li>
                        	<c:forEach items="${homePageFriendRecommends}" var="thisRecommend" varStatus="fr">
                        	<c:if test="${fr.index<2}">
                        	<div class="book_rating_lftcol"><!--start book_rating_lftcol-->
                            	<div class="rating_lftcol_cont">
                                	<div class="rating_book"><a href="${my_domain }/music/${thisRecommend.itemId }.action"><img src="${thisRecommend.itemPic }" alt="" width="120" height="185" /></a></div>
                                    <div class="book_rating_info">
                                    	<div class="book_icon">
                                        	<span>Rating:</span>
												<c:choose>
		                                		<c:when test="${thisRecommend.rating>=0.5 && thisRecommend.rating<1.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=1.5 && thisRecommend.rating<2.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=2.5 && thisRecommend.rating<3.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=3.5 && thisRecommend.rating<4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:otherwise>
				                               		<a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:otherwise>
				                           	</c:choose>
		                                    <c:out value="${singlePageMain.itemAveGrade}" />
                                        </div>
                                        <h5><a href="${my_domain }/user/friendHome/${thisRecommend.friendId }.action"><c:out value="${thisRecommend.friendName}" /></a> Saw it </h5>
                                        <small><c:out value="${thisRecommend.timeSpan}" /> ago</small>
                                        <div class="zoom"></div>
                                        <p><c:out value="${thisRecommend.itemName}" /></p>
                                    </div>
                               </div>
                            </div><!--//end .book_rating_lftcol-->
                            </c:if>
                            </c:forEach>
                            <div class="clear"></div>
                            </li>
                            
                            <li>
                        	<c:forEach items="${homePageFriendRecommends}" var="thisRecommend" varStatus="fr">
                        	<c:if test="${fr.index>=2 && fr.index<4}">
                        	<div class="book_rating_lftcol"><!--start book_rating_lftcol-->
                            	<div class="rating_lftcol_cont">
                                	<div class="rating_book"><a href="${my_domain }/music/${thisRecommend.itemId }.action"><img src="${thisRecommend.itemPic }" alt="" width="120" height="185" /></a></div>
                                    <div class="book_rating_info">
                                    	<div class="book_icon">
                                        	<span>Rating:</span>
 												<c:choose>
		                                		<c:when test="${thisRecommend.rating>=0.5 && thisRecommend.rating<1.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=1.5 && thisRecommend.rating<2.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=2.5 && thisRecommend.rating<3.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=3.5 && thisRecommend.rating<4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:when test="${thisRecommend.rating>=4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
				                               </c:when>
				                               <c:otherwise>
				                               		<a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
				                               </c:otherwise>
				                           	</c:choose>
		                                    <c:out value="${singlePageMain.itemAveGrade}" />
                                        </div>
                                        <h5><a href="${my_domain }/user/friendHome/${thisRecommend.friendId }.action"><c:out value="${thisRecommend.friendName}" /></a> Saw it </h5>
                                        <small><c:out value="${thisRecommend.timeSpan}" /> ago</small>
                                        <div class="zoom"></div>
                                        <p><c:out value="${thisRecommend.itemName}" /></p>
                                    </div>
                               </div>
                            </div><!--//end .book_rating_lftcol-->
                            </c:if>
                            </c:forEach>
                            <div class="clear"></div>
                            </li>
                            
                            </ul>
                        </div><!--//end #book_rating-->
                        
                        <c:forEach items="${homePageReviews }" var="thisReview" varStatus="r">
                    <c:if test="${r.index == 0 }">
                    <div class="review_box"><!--start review_box-->
                        <h2>Review</h2>
                        <div class="review_title_area"><!--start review_title_area-->
                            <div class="review_small_img">
                                <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                <a href="#"><img src="${my_local}/music/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                            </div>
                            <div class="review_title_rgt">
                                <h3><a href="${my_domain }/review/musicReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><span><c:out value="${thisReview.userName}" /></span></a>
                            </div>
                        </div><!--//end .review_title_area-->
                        <div class="rating_feature"><!--strat rating_feature-->
                            <div class="review_featureimg"><img src="${thisReview.itemPic}" alt="" width="38" height="38" /></div>
                            <div class="rating_feature_descript">
                                <p><c:out value="${thisReview.itemName}" /><br/>
									<c:out value="${thisReview.commentContent}" /></p>
                            </div>
                            <div class="rating_star">
                                <div class="rating_star_lft">
												<c:choose>
		                                		<c:when test="${thisReview.userRate>=0.5 && thisReview.userRate<1.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=1.5 && thisReview.userRate<2.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=2.5 && thisReview.userRate<3.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=3.5 && thisReview.userRate<4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:otherwise>
				                               		<a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:otherwise>
				                           	</c:choose>
                                </div>
                                <div class="rating_star_rt"><a href="${my_domain }/review/musicReview/${thisReview.commentId }.action"><img src="${my_local}/music/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                            </div>
                        </div><!--//end .rating_feature-->
                    </div><!--//end .review_box-->
                    </c:if> 
                    
                    <c:if test="${r.index > 0 }">
                    <div class="review_box"><!--start review_box-->
                        <div class="review_title_area"><!--start review_title_area-->
                            <div class="review_small_img">
                                <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                <a href="#"><img src="${my_local}/music/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                            </div>
                            <div class="review_title_rgt">
                                <h3><a href="${my_domain }/review/musicReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><span><c:out value="${thisReview.userName}" /></span></a>
                            </div>
                        </div><!--//end .review_title_area-->
                        <div class="rating_feature"><!--strat rating_feature-->
                            <div class="review_featureimg"><img src="${thisReview.itemPic}" alt="" width="38" height="38" /></div>
                            <div class="rating_feature_descript">
                                <p><c:out value="${thisReview.itemName}" /><br/>
									<c:out value="${thisReview.commentContent}" /></p>
                            </div>
                            <div class="rating_star">
                                <div class="rating_star_lft">
												<c:choose>
		                                		<c:when test="${thisReview.userRate>=0.5 && thisReview.userRate<1.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=1.5 && thisReview.userRate<2.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=2.5 && thisReview.userRate<3.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=3.5 && thisReview.userRate<4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:when test="${thisReview.userRate>=4.5}">
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
				                               </c:when>
				                               <c:otherwise>
				                               		<a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                                    <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
				                               </c:otherwise>
				                           	</c:choose>
                                </div>
                                <div class="rating_star_rt"><a href="${my_domain }/review/musicReview/${thisReview.commentId }.action"><img src="${my_local}/music/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                            </div>
                        </div><!--//end .rating_feature-->
                    </div><!--//end .review_box-->
                    </c:if>
                    </c:forEach>
                        <div class="more_review">
                        	<a href="${my_domain }/review/music.action">More reviews <img src="${my_local}/music/img/tripple_arrow3.jpg" alt="" /></a>
                        </div>
                    </div><!--//end #content_book_final-->
                </div><!--//end #content-->
                <div id="side_bar"><!--start side_bar-->
                    <div class="widget"><!--start widget-->
                    	<h2>You May Like</h2>
                    	<c:forEach items="${guessYouLike}" var="thisLike">
                        <div class="widget_gallery">
                        	<img src="${thisLike.activityPic}" alt="" width="222" height="133" />
                            <h3><a href="${my_domain}/activity/info.action?activityId=${thisLike.activityId }"><c:out value="${thisLike.activityName}" />. ..</a></h3>
                        </div>
                        </c:forEach>
                        <div class="next_big_img"><a href="#"><img src="${my_local}/music/img/tripple_arrow3.jpg" alt="" width="25" height="8" /></a></div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Tag</h2>
                        <div class="widget_info">
                        	<p class="upper"><a href="${my_domain }/music/searchByTag/blue.action">Blue</a> / <a  href="${my_domain }/music/searchByTag/classical.action">Classical</a> / <a href="${my_domain }/music/searchByTag/country.action">Country</a><br />
                            <a href="${my_domain }/music/searchByTag/hip.action">Hip hop</a> / <a href="${my_domain }/music/searchByTag/jazz.action">Jazz</a> / <a href="${my_domain }/music/searchByTag/pop.action">Pop</a> / <a href="${my_domain }/music/searchByTag/r&b.action">R&B</a><br/>
                        	<a href="${my_domain }/music/searchByTag/rock.action">Rock</a> / <a href="${my_domain }/music/searchByTag/other.action">Others</a></p>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Music List</h2>
                        <div class="widget_info">
                        	<ul>
                            	<li><a href="${my_domain }/tribuslist/list/${tribusList[0].resourceId }.action"><c:out value="${tribusList[0].resourceName}" /></a></li>
                                <li><a href="${my_domain }/tribuslist/list/${tribusList[1].resourceId }.action"><c:out value="${tribusList[1].resourceName}" /></a></li>
                                <li class="color1"><a href="${my_domain }/tribuslist/list/${tribusList[2].resourceId }.action"><c:out value="${tribusList[2].resourceName}" /></a></li>
                                <li class="color1"><a href="${my_domain }/tribuslist/list/${tribusList[3].resourceId }.action"><c:out value="${tribusList[3].resourceName}" /></a></li>
                                <li><a href="${my_domain }/tribuslist/list/${tribusList[4].resourceId }.action"><small><c:out value="${tribusList[4].resourceName}" /></small></a></li>
                                <li><a href="${my_domain }/tribuslist/list/${tribusList[5].resourceId }.action"><small><c:out value="${tribusList[5].resourceName}" /></small></a></li>
                                <li><a href="${my_domain }/tribuslist/list/${tribusList[6].resourceId }.action"><span><c:out value="${tribusList[6].resourceName}" /></span></a></li>
                                <li><a href="${my_domain }/tribuslist/list/${tribusList[7].resourceId }.action"><span><c:out value="${tribusList[7].resourceName}" /></span></a></li>
                            </ul>

                        </div>
                    </div><!--//end .widget-->
                </div><!--//end #side_bar-->
            </div><!--//end #main_content-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    <script src="${my_local}/music/js/smoothscroll.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/latest.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/box.slider.js" type="text/javascript"></script>
	<script type="text/javascript">
	  $(document).ready(function(){
		$('#slider1').bxSlider();
	  });
	  $(document).ready(function(){
		$('#slider2').bxSlider();
	  });
	  function get(){
					document.getElementById("aa").style.display="block";
					document.getElementById("bb").style.display="none";
					}
					function bu(){
					document.getElementById("bb").style.display="block";
					document.getElementById("aa").style.display="none";
				}
	</script>

</body>
</html>                                                                                                   