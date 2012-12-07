<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" import="config.*"%>
<%
	request.setAttribute("my_local",GlobleConfig.my_local);
	request.setAttribute("my_domain",GlobleConfig.my_domain);
	if(request.getServerName().contains("localhost")){
		request.setAttribute("Server",1);
	}
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><c:out value="${singlePageMain.itemName}" /></title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/music/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/music/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/music/css/css3.css" />
    <link rel="stylesheet" type="text/css" href="${my_local }/music/css/jquery.lightbox.css" media="screen" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local}/movie/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${my_domain}/activity/index.action">EVENT</a></li>
                    	<li class="current_page_item"><a href="${my_domain}/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain}/book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${my_domain}/music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
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
                    	<span class="space_btm"><a href="#"><img src="${my_local}/movie/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="${my_domain }/user/editForm.action"><img src="${my_local}/movie/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${my_domain }/movie/search/1.action" id="single_search_bar" name="single_search_bar">
                    	<p class="search_text"><input name="single_search_name" type="text" value="Seach for movie, celebrity, tribus list" onclick="if(this.value=='Seach for movie, celebrity, tribus list')(this.value='')"  onblur="if(this.value=='')(this.value='Seach for movie, celebrity, tribus list')" /></p>
                        <p class="search_submit"><input type="submit" value=" "/></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                		<div id='fb-root'></div>
                    	<a href="https://www.facebook.com/TheTribus"><img src="${my_local}/movie/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local}/movie/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
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
            <div id="common_maincontent"><!--start common_maincontent-->
                <div id="review_lftcol"><!--start review_lftcol-->
                    <div id="product_area"><!--start product_area-->
                    	<h2><c:out value="${singlePageMain.itemName }" /></h2>
                        <div id="product_twocol"><!--start product_twocol-->
                        	<div id="product_lftcol"><img src="${singlePageMain.itemPic }" alt="" width="270" height="400" /><strong><a href="${my_domain }/movie/editMovie/${singlePageMain.itemId }.action">Edit this movie</a></strong></div>
                            <div id="product_rgtcol"><!--start product_rgtcol-->
                            	<div class="kings_rating">
                                	<span>Rating:</span>
                                	<c:choose>
                                		<c:when test="${singlePageMain.itemAveGrade>=0.5 && singlePageMain.itemAveGrade<1.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singlePageMain.itemAveGrade>=1.5 && singlePageMain.itemAveGrade<2.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singlePageMain.itemAveGrade>=2.5 && singlePageMain.itemAveGrade<3.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singlePageMain.itemAveGrade>=3.5 && singlePageMain.itemAveGrade<4.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singlePageMain.itemAveGrade>=4.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:otherwise>
		                               		<a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:otherwise>
		                           	</c:choose>
                                    <c:out value="${singlePageMain.itemAveGrade}" />
                                </div>
                                <ul class="kings_list">
                                    <li>Director: <c:out value="${singlePageMain.itemDirectors }" /></li>
                                    <li>Actor: <c:out value="${singlePageMain.itemActors }" /></li>
                                    <li>Region: <c:out value="${singlePageMain.itemRegion }" /></li>
                                	<li>Release Date: <c:out value="${singlePageMain.itemDate }" /></li>
                                    <li>Rating: <c:out value="${singlePageMain.itemRate }" /></li>
                                </ul>
                                <div class="king_wish">
                                	<a href="#" class="trackList">+ Track List</a>
                                	<a href="javascript:void(0)" onClick="showPop()">+ Tribus List</a>
                                </div><!--
                                <div class="king_social">
			                		<div id='fb-root'></div>
			                    	<a onclick='postToFeed(); return false;'><img src="${my_local}/movie/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
			                        <a id='msg'></a>
                                    <a href="#"><img src="${my_local}/movie/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                                </div>
                                
                                --><c:if test="${myRate>0}">
                                <strong id="delete_rate"><a href="#">Rerate</a></strong>
								<div class="king_blue_rated">
									
                                	<span>Your rating is: <c:out value="${myRate}" /></span>
                                	
                                	<c:choose>
                                		<c:when test="${myRate>=0.5 && myRate<1.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${myRate>=1.5 && myRate<2.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${myRate>=2.5 && myRate<3.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${myRate>=3.5 && myRate<4.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star1.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/blue_star2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${myRate>=4.5}">
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
                                </div>   
                                
                                </c:if>
                                <div class="king_blue_rating" <c:if test="${myRate>0}">style="visibility:hidden"</c:if><c:if test="${myRate==0}">style="visibility:visibility"</c:if>>
                                	<span>Rate this movie:</span>
                                	<div class="rating_align">
                                		
                                		<div id="score" data-rating="0"></div>
                                	
                                	</div>
                                </div> 
   								<div class="king_done">
                                	<a href="javascript:void(0)" class="selectOne">Wanted</a>
                                    <a href="javascript:void(0)" class="selectTwo">Done</a>
                                    <input type="hidden" id="item_rate_id" value="${singlePageMain.itemId}" />
                                </div>

                            </div><!--//end #product_rgtcol-->
                        </div><!--//end #product_twocol-->
                    </div><!--//end #product_area-->
                    <div id="brief_main"><!--start brief_main-->
                    	<div class="brief_activity space_brief_activity"><!--start brief_activity-->
                        	<h2>Brief Introduction</h2>
                            <div class="comment_box"><!--start comment_box-->
                                <div class="brief_content">
                                	<p><c:out value="${singlePageMain.itemBrief}" /></p>
                                    <div class="icon_brief"><img src="${my_local}/movie/img/icon_plus.jpg" alt="" width="20" height="20" /></div>
                                </div>
                            </div><!--//end .comment_box-->
                        </div><!--//end .brief_activity-->
                        <div class="just_watch"><!--start just_watch-->
                        	<h5>Just watched :</h5>
                            <ul>
                            	<c:forEach items="${SinglePageFriendsRecord}" var="thisRecord" varStatus="re">
                            	<li>
                            	<img src="${thisRecord.friendPic}" alt="" width="31" height="30" /><span> <c:out value="${thisRecord.friendName}"/><small>........</small><c:out value="${thisRecord.timeSpan}"/> ago</span>
                            	</li>
                           		</c:forEach>
                            </ul>
                        </div><!--//end .just_watch-->
                        <div class="movie_img"><!--start movie_img-->
                        
                        	<h2><a href="${my_domain }/movie/uploadPicsAction/${singlePageMain.itemId }.action">Movie Image</a></h2>
							                        	
                            <div class="kings_gallery">
                            
                         <ul id="slider1"><li>
                            	<c:forEach items="${singlePageImages}" var="thisImage" varStatus="ti">
                            	<c:if test="${ti.index<4}">
                            	<a class="movie_thumb" href="${thisImage }"><img src="${thisImage }" alt="" width="124" height="81" /></a>
                            	</c:if>
                            	</c:forEach>
                                <div class="clear"></div>
                                </li>
                                
                                <li>
                            	<c:forEach items="${singlePageImages}" var="thisImage" varStatus="ti">
                            	<c:if test="${ti.index>=4 && ti.index<8}">
                            	<a class="movie_thumb" href="${thisImage }"><img src="${thisImage }" alt="" width="124" height="81" /></a>
                            	</c:if>
                            	</c:forEach>
                                </li>
                                </ul>
                            </div>
                        </div>
                        <div id="kings_reivew">
                            <c:forEach items="${singlePageReviews}" var="thisReview" varStatus="r">
                        	<c:if test="${r.index==0}">
                            <div class="review_box"><!--start review_box-->
                                <h2>Review</h2>
                                <h4 class="create_kings"><a href="${my_domain }/review/movieReviewAction/${singlePageMain.itemId }.action">Create a Review</a></h4>
                                <div class="review_title_area"><!--start review_title_area-->
                                    <div class="review_small_img">
                                        <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="30" /></a>
                                        <a href="#"><img src="${my_local}/movie/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                                    </div>
                                    <div class="review_title_rgt">
                                        <h3><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                         <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><span><c:out value="${thisReview.userName}" /></span></a>
                                    </div>
                                </div><!--//end .review_title_area-->
                                <div class="rating_feature"><!--strat rating_feature-->
                                    <div class="rating_feature_descript">
                                        <p><c:out value="${thisReview.commentContent}" /></p>
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
                                        <div class="rating_star_rt"><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><img src="${my_local}/movie/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                                    </div>
                                </div><!--//end .rating_feature-->
                            </div><!--//end .review_box-->
                            </c:if>
                            <c:if test="${r.index>0}">
                            <div class="review_box"><!--start review_box-->
                                <div class="review_title_area"><!--start review_title_area-->
                                    <div class="review_small_img">
                                        <a href="${my_domain}/user/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                        <a href="#"><img src="${my_local}/movie/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                                    </div>
                                    <div class="review_title_rgt">
                                        <h3><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                         <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><span><c:out value="${thisReview.userName}" /></span></a>
                                    </div>
                                </div><!--//end .review_title_area-->
                                <div class="rating_feature"><!--strat rating_feature-->
                                    <div class="rating_feature_descript">
                                        <p><c:out value="${thisReview.commentContent}" /></p>
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
                                        <div class="rating_star_rt"><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><img src="${my_local}/movie/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                                    </div>
                                </div><!--//end .rating_feature-->
                            </div><!--//end .review_box-->
                            </c:if>
                            </c:forEach>
                            <div class="more_review">
                        	<a href="${my_domain }/review/movie${singlePageMain.itemId }.action">More reviews <img src="${my_local }/movie/img/tripple_arrow3.jpg" alt="" /></a>
                        	</div>
                        </div>
                    </div><!--//end #brief_main-->
                    
                     
                </div><!--//end #review_lftcol-->
                <div id="review_side_bar"><!--start review_side_bar-->
                	<div class="review_btn"><a href="${my_domain }/review/movieReviewAction/${singlePageMain.itemId }.action">Create a Review</a></div>
                </div><!--//end review_side_bar-->
                <div class="clear"></div>
                
               
            </div><!--//end #common_maincontent-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    

    <div id="popup_layer" onClick="closePop()"></div>   
    <div id="light_box_wrapper"><!--start light_box_wrapper-->
        <div id="light_box_bg"><!--start light_box_bg-->
            <div class="pop_up_cross"> <a href="javascript:void(0)" onClick="closePop()"></a></div>
            <h2>Add to list</h2>
            <form action="#">
                <p>
                    <select>	
						<c:forEach items="${list}" var="l">
							<option value="${l.id}">${l.name}</option>
						</c:forEach>
                    </select>
                </p>
                <p><textarea rows="10" cols="10" onclick="if(this.value=='Description...')(this.value='')" onblur="if(this.value=='')(this.value='Description...')" >Description...</textarea></p>
                <div class="pop_btn"><a href="gotribus.com/tribuslist/add.action">Add</a><a href="gotribus.com/user/addTribusList/movie/{tribusListId}/${singlePageMain.itemId}">Create</a></div>
            </form>
        </div><!--//end #light_box_bg-->
    </div><!--//end #light_box_wrapper-->
    
	<script src='http://connect.facebook.net/en_US/all.js'></script>    
    <script src="${my_local }/music/js/jquery.min.js" type="text/javascript"></script>
    <script src="${my_local }/music/js/latest.js" type="text/javascript"></script>
    <script src="${my_local }/music/js/box.slider.js" type="text/javascript"></script>
    <script type="text/javascript" src="${my_local }/music/js/code.js"></script>
    <script type="text/javascript" src="${my_local }/music/js/jquery.lightbox.js"></script>
	<c:if test="${Server == 1}">
	    <script src="${my_local }/movie/js/jquery.raty.js" type="text/javascript"></script>
	</c:if>
    <c:if test="${Server != 1}">
    	<script src="${my_local }/movie/js/jquery.raty.server.js" type="text/javascript"></script>
    </c:if> 
    <script src="${my_local }/music/js/smoothscroll.js" type="text/javascript"></script>
    <script type="text/javascript">
    	
	  $(document).ready(function(){
		$('#slider1').bxSlider();
	  });
	</script>
    <script type="text/javascript">
        $(document).ready(function() {
				$('.movie_thumb').lightBox();
        	});
    </script>
    
    <script type="text/javascript">
    	$(function() {
    		$('#delete_rate').click( function(){
    		$('.king_blue_rating').css("visibility", "visible");
			$('.king_blue_rated').css("visibility", "hidden");
			$('#delete_rate').css("visibility", "hidden");
	
				$.ajax({  
				  type: "POST",  
				  url: "${my_domain}/movie/deleteRate/${singlePageMain.itemId}.action",  
				  success: function() {  
				  }  
				});			
			});
    		
        	$(".trackList").click(function() {
			$.ajax({  
				type: "POST",  
				url: "${my_domain }/user/addWishList/movie/${singlePageMain.itemId }.action",  
				success: function(data, textStatus, jqXHR) {  
					if(data == "ok!"){
						$(".trackList").html("Already added");
					}else {
						$(".trackList").html("+Track List");
					}
				}  
			});   		
    		});
    		
    		$(".tribusList").click(function() {
     		var dataString;
			$.ajax({  
				type: "POST",  
				url: "${my_domain }/user/addTribusList/movie/${singlePageMain.itemId }.action",  
				data: dataString,  
				success: function(data, textStatus, jqXHR) {  
					if(data == "ok!"){
						$(".tribusList").html("Already added");
					}else {
						$(".tribusList").html("+Tribus List");
					}
				}  
			});   		
    		});
    	});
    </script>
	<script type="text/javascript">
		$(function() {
			$('#score').raty({
				  starOn  : 'blue_star1.jpg',
				  starOff : 'blue_star2.jpg',						  
				  score: function() {

					var dataString = $(this).attr('data-rating');
					if($(this).attr('data-rating') != 0){
					}
					return $(this).attr('data-rating');
				 },
				 parss:"${singlePageMain.itemId}"
			});
			
	var markWatch = "${markWatch}";
	if(markWatch == 1){
		$(".selectOne").addClass("click_done");
	}
	if(markWatch == 2){
		$(".selectTwo").addClass("click_done");
	}			   

    $(".selectOne").click(function() {
     $(".selectOne").addClass("click_done");
     $(".selectTwo").removeClass("click_done");
     var dataString = "mark=wanted";
				$.ajax({  
				  type: "POST",  
				  url: "${my_domain}/movie/markMovie/${singlePageMain.itemId}.action",  
				  data: dataString,  
				  success: function() {  
				  }
				});
    });
    $(".selectTwo").click(function() {
    
     $(".selectTwo").addClass("click_done");
     $(".selectOne").removeClass("click_done");
          var dataString = "mark=done";
				$.ajax({  
				  type: "POST",  
				  url: "${my_domain}/movie/markMovie/${singlePageMain.itemId}.action",  
				  data: dataString,  
				  success: function(data) {
				  postToFeed();
				  }  
				});
    });   
            });
        </script>
        
    <script><!-- 
      FB.init({appId: "167819743335514", status: true, cookie: true});

      function postToFeed() {

        // calling the API ...
        var obj = {
          method: 'feed',
          link: '${my_domain}/movie/${singlePageMain.itemId}.action',
          picture: '${singlePageMain.itemPic}',
          name: 'About this movie',
          caption: '${singlePageMain.itemName}',
          description: '${singlePageMain.itemBrief_short}'
        };
		if(${markWatch}==1){
			obj.name = 'I want to watch this movie';
		}
		if(${markWatch}==2){
			obj.name = 'I\'v watched this movie';
		}
        function callback(response) {
          //document.getElementById('msg').innerHTML = "Post ID: " + response['post_id'];
        }

        FB.ui(obj, callback);
      }
    --></script>
    <script type="text/javascript">
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