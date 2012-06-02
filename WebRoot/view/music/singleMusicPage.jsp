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
	<title><c:out value="${singlePageMain.itemName}" /></title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/css/css3.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/music/css/slider.css" />
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
                    	<li><a href="${my_domain}/activity/index.action">CITY</a></li>
                    	<li class="current_page_item"><a href="${my_domain }/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain}/book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${my_domain }/music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li><a href="${my_domain}/my.action" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${my_local}/music/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${my_local}/music/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="${my_local}/music/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local}/music/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${my_local}/music/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${my_local}/music/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local}/music/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local}/music/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
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
                    	<h2><c:out value="${singlePageMain.itemName}" /></h2>
                        <div id="product_twocol"><!--start product_twocol-->
                        	<div id="product_lftcol"><img src="${singlePageMain.itemPic}" alt="" width="313" height="417" /></div>
                            <div id="product_rgtcol"><!--start product_rgtcol-->
                            	<div class="kings_rating">
                                	<span>Rating:</span>
                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
                                </div>
                                <ul class="kings_list">
                                	<li>ReleaseDate: <c:out value="${singlePageMain.itemDate}" /></li>
                                    <li>Singer: <c:out value="${singlePageMain.singerName}" /></li>
                                </ul>
                                <div class="king_wish">
                                	<a href="#">+ Track List</a><a href="#">+ Tribus List</a>
                                </div>
                                <div class="king_social">
                                	<a href="#"><img src="${my_local}/music/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                                </div>
                                <div class="king_blue_rating">
                                	<span>Rate this movie:</span>
                                	<div class="rating_align"><div id="score" data-rating="0"></div></div>
                                </div>
                                <div class="king_done">
                                	<a href="javascript:void(0)" class="selectOne">Wanted</a>
                                    <a href="javascript:void(0)" class="selectTwo click_done">Done</a>
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
                                    <div class="icon_brief"><img src="${my_local}/music/img/icon_plus.jpg" alt="" width="20" height="20" /></div>
                                </div>
                            </div><!--//end .comment_box-->
                        </div><!--//end .brief_activity-->
                        <div class="just_watch"><!--start just_watch-->
                        	<h5>Just watched :</h5>
                            <ul>
                            	<li><img src="${my_local}/music/img/pic_review_small_1.jpg" alt="" width="31" height="30" /><span> Name<small>........</small>4mins ago</span></li>
                                <li><img src="${my_local}/music/img/pic_review_small_1.jpg" alt="" width="31" height="30" /><span> Name<small>........</small>4mins ago</span></li>
                                <li><img src="${my_local}/music/img/pic_review_small_1.jpg" alt="" width="31" height="30" /><span> Name<small>........</small>4mins ago</span></li>
                                <li><img src="${my_local}/music/img/pic_review_small_1.jpg" alt="" width="31" height="30" /><span> Name<small>........</small>4mins ago</span></li>
                                <li><img src="${my_local}/music/img/pic_review_small_1.jpg" alt="" width="31" height="30" /><span> Name<small>........</small>4mins ago</span></li>
                                <li><img src="${my_local}/music/img/pic_review_small_1.jpg" alt="" width="31" height="30" /><span> Name<small>........</small>4mins ago</span></li>
                            </ul>
                        </div><!--//end .just_watch-->
                        <div class="movie_img"><!--start movie_img-->
                        	<h2>Movie Image</h2>
                        	
                            <div class="kings_gallery">
                            <ul id="slider1"><li>
                            	<img src="${my_local }/music/img/pic_kings1.jpg" alt="" width="124" height="81" />
                                <img src="${my_local }/music/img/pic_kings2.jpg" alt="" width="124" height="81" />
                                <img src="${my_local }/music/img/pic_kings3.jpg" alt="" width="124" height="82" />
                                <img src="${my_local }/music/img/pic_kings4.jpg" alt="" width="124" height="82" />
                                <img src="${my_local }/music/img/pic_kings5.jpg" alt="" width="79" height="83" />
                                <div class="clear"></div>
                                </li></ul>
                            </div>
                        </div><!--//end .movie_img-->
                        <div class="video_box"><!--start video_box-->
                        	<h2 class="tailors">Trailors</h2>
                        	<div class="video_box_cont"><!--start video_box_cont-->
                            	<div class="btn_play"><a href="#"><img src="${my_local}/music/img/btn_play.jpg" alt="" width="18" height="18" /></a></div>
                            	<img src="${my_local}/music/img/pic_kings6.jpg" alt="" width="185" height="121"/>
                                <div class="play_side">
                                	<p>Uploaded by theblackkeys on 7 Feb 2012<br />&copy; 2012 WMG</p>
                                    <p>This is the official video for "Gold On The Ceiling" from El Camino.  To download El Camino now clickhere - 
                                    <a href="#">http://smarturl.it/ElCamino</a></p>
                                    <p>Directed by Reid Long</p>
                                </div>
                            </div><!--//end .video_box_cont-->
                        </div><!--//end .video_box-->
                        <div class="video_box"><!--start video_box-->
                        	<div class="video_box_cont"><!--start video_box_cont-->
                            	<div class="btn_play"><a href="#"><img src="${my_local}/music/img/btn_play.jpg" alt="" width="18" height="18" /></a></div>
                            	<img src="${my_local}/music/img/pic_kings7.jpg" alt="" width="186" height="102"  />
                                <div class="play_side">
                                	<p class="space_top">Uploaded by theblackkeys on 2 Dec 2011 <br />The Black Keys essential Videos Playlist</p>
                                </div>
                            </div><!--//end .video_box_cont-->
                        </div><!--//end .video_box-->
                        <div id="kings_reivew">
                            <c:forEach items="${singlePageReviews}" var="thisReview" varStatus="r">
                        	<c:if test="${r.index==0}">
                            <div class="review_box"><!--start review_box-->
                                <h2>Review</h2>
                                <h4 class="create_kings"><a href="${my_domain }/review/musicReviewAction/${singlePageMain.itemId }.action">Create a Review</a></h4>
                                <div class="review_title_area"><!--start review_title_area-->
                                    <div class="review_small_img">
                                        <a href="${my_domain }/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="30" /></a>
                                        <a href="#"><img src="${my_local}/movie/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                                    </div>
                                    <div class="review_title_rgt">
                                        <h3><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                        <span><c:out value="${thisReview.userName}" /></span>
                                    </div>
                                </div><!--//end .review_title_area-->
                                <div class="rating_feature"><!--strat rating_feature-->
                                    <div class="rating_feature_descript">
                                        <p><c:out value="${thisReview.commentContent}" /></p>
                                    </div>
                                    <div class="rating_star">
                                        <div class="rating_star_lft">
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
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
                                        <a href="${my_domain}/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                        <a href="#"><img src="${my_local}/movie/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                                    </div>
                                    <div class="review_title_rgt">
                                        <h3><a href="{my_local }/review/movieReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                        <span><c:out value="${thisReview.userName}" /></span>
                                    </div>
                                </div><!--//end .review_title_area-->
                                <div class="rating_feature"><!--strat rating_feature-->
                                    <div class="rating_feature_descript">
                                        <p><c:out value="${thisReview.commentContent}" /></p>
                                    </div>
                                    <div class="rating_star">
                                        <div class="rating_star_lft">
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
                                            <a href="#"><img src="${my_local}/movie/img/icon_star2.png" alt="" width="12" height="12" /></a>
                                        </div>
                                        <div class="rating_star_rt"><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><img src="${my_local}/movie/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                                    </div>
                                </div><!--//end .rating_feature-->
                            </div><!--//end .review_box-->
                            </c:if>
                            </c:forEach>
                        </div>
                    </div><!--//end #brief_main-->
                </div><!--//end #review_lftcol-->
                <div id="review_side_bar"><!--start review_side_bar-->
                	<div class="review_btn"><a href="${my_domain }/review/musicReviewAction/${singlePageMain.itemId }.action">Create a Review</a></div>
                </div><!--//end review_side_bar-->
                <div class="clear"></div>
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    <script src="${my_local}/music/js/jquery.min.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/jquery.raty.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/smoothscroll.js" type="text/javascript"></script>
	<script src="${my_local}/music/js/mobilyslider.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/init.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(function() {
			$('#score').raty({
				  starOn  : 'blue_star1.jpg',
				  starOff : 'blue_star2.jpg'	,							  
				score: function() {
					return $(this).attr('data-rating');
				}
			});
					   
					   
    $(".selectOne").click(function() {
     $(".selectOne").addClass("click_done");
     $(".selectTwo").removeClass("click_done");
    });
    $(".selectTwo").click(function() {
     $(".selectTwo").addClass("click_done");
     $(".selectOne").removeClass("click_done");
    });   
            });
        </script>
</body>
</html>                                                                                                   