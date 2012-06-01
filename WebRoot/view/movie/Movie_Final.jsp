<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" import="config.*"%>
<%
	request.setAttribute("domain",GlobleConfig.localhost);
	//request.setAttribute("my_local",GlobleConfig.show_local);
	request.setAttribute("my_local",GlobleConfig.my_local);
	request.setAttribute("my_domain",GlobleConfig.my_domain);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Tribus_Movie_Final</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/css/css3.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/css/slider.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local }/movie/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="#">CITY</a></li>
                    	<li class="current_page_item"><a href="#" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain }/book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${my_domain }/music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li><a href="${my_domain }/my.action" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${my_local }/movie/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${my_local }/movie/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="${my_local }/movie/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local }/movie/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${my_local }/movie/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${my_local }/movie/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local }/movie/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local }/movie/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3>Jeremy Guan</h3>
                            <span>New York City</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="main_content"><!--start main_content-->
            	<div id="content"><!--start content-->
                	<div id="content_book_final"><!--start content_book_final-->
                    	<div id="book_gallery_area"><!--start book_gallery_area-->
                            <h2 class="title_movie">Recent Hot Movies</h2>
                            <div class="slider">
                    			<div class="sliderContent">
                                	<div class="item">
                                        <div class="movie_gallery">
                                  			<c:forEach items="${recentHotMovies_1}" var="thisHotMovie">
                                        	<a href="${my_domain}/movie/${thisHotMovie.movieId }.action"><img src="${thisHotMovie.moviePic }" alt="" width="110" height="110" /></a>
                                        	</c:forEach>
                                        </div>
                             		</div>
                                    <div class="item">
                                        <div class="movie_gallery">
                                            <c:forEach items="${recentHotMovies_2}" var="thisHotMovie">
                                        	<a href="${my_domain}/movie/${thisHotMovie.movieId }.action"><img src="${thisHotMovie.moviePic }" alt="" width="110" height="110" /></a>
                                        	</c:forEach>
                                        </div>
                             		</div>
                                    
                                    <div class="item">
                                        <div class="movie_gallery">
                                            <c:forEach items="${recentHotMovies_3}" var="thisHotMovie">
                                        	<a href="${my_domain}/movie/${thisHotMovie.movieId }.action"><img src="${thisHotMovie.moviePic }" alt="" width="110" height="110" /></a>
                                        	</c:forEach>
                                        </div>
                             		</div>
                                    
                             </div></div>           
                        </div><!--//end #book_gallery_area-->
                        <div id="book_rating"><!--start book_rating-->
                        <h2>Friends Recommend</h2>
                        <ul id="slider1"><li>
                        	<c:forEach items="${homePageFriendRecommends}" var="thisRecommend" varStatus="fr">
                        	<div class="book_rating_lftcol"><!--start book_rating_lftcol-->
                            	<div class="rating_lftcol_cont">
                                	<div class="rating_book"><a href="${my_domain }/movie/${thisRecommend.itemId }.action"><img src="${thisRecommend.itemPic }" alt="" width="120" height="185" /></a></div>
                                    <div class="book_rating_info">
                                    	<div class="book_icon">
                                        	<span>Rating:</span>
                                            <a href="#"><img src="${my_local }/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                        </div>
                                        <h5><a href="${my_domain }/friendHome/${thisRecommend.friendId }.action"><c:out value="${thisRecommend.friendName}" /></a> Saw it </h5>
                                        <small>2 Minutes ago</small>
                                        <div class="zoom"><a href="#"><img src="${my_local }/movie/img/icon_zoom.jpg" alt="" width="19" height="19" /></a></div>
                                        <p><c:out value="${thisRecommend.itemName}" /></p>
                                    </div>
                               </div>
                            </div><!--//end .book_rating_lftcol-->
                            </c:forEach>
                            <div class="clear"></div>
                            </li></ul>
                        </div><!--//end #book_rating-->
                        
                        <c:forEach items="${homePageReviews }" var="thisReview" varStatus="r">
                    <c:if test="${r.index == 0 }">
                    <div class="review_box"><!--start review_box-->
                        <h2>Review</h2>
                        <div class="review_title_area"><!--start review_title_area-->
                            <div class="review_small_img">
                                <a href="${my_domain }/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                <a href="#"><img src="${my_local}/movie/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                            </div>
                            <div class="review_title_rgt">
                                <h3><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                <span><c:out value="${thisReview.userName}" /></span>
                            </div>
                        </div><!--//end .review_title_area-->
                        <div class="rating_feature"><!--strat rating_feature-->
                            <div class="review_featureimg"><img src="${thisReview.itemPic}" alt="" width="38" height="53" /></div>
                            <div class="rating_feature_descript">
                                <p><c:out value="${thisReview.itemName}" /><br/>
									<c:out value="${thisReview.commentContent}" /></p>
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
                    
                    <c:if test="${r.index > 0 }">
                    <div class="review_box"><!--start review_box-->
                        <div class="review_title_area"><!--start review_title_area-->
                            <div class="review_small_img">
                                <a href="${my_domain }/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                <a href="#"><img src="${my_local}/movie/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                            </div>
                            <div class="review_title_rgt">
                                <h3><a href="${my_domain }/review/movieReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
                                <span><c:out value="${thisReview.userName}" /></span>
                            </div>
                        </div><!--//end .review_title_area-->
                        <div class="rating_feature"><!--strat rating_feature-->
                            <div class="review_featureimg"><img src="${thisReview.itemPic}" alt="" width="38" height="53" /></div>
                            <div class="rating_feature_descript">
                                <p><c:out value="${thisReview.itemName}" /><br/>
									<c:out value="${thisReview.commentContent}" /></p>
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
                        <div class="more_review">
                        	<a href="${my_domain }/review/movie.action">More reviews <img src="${my_local }/movie/img/tripple_arrow3.jpg" alt="" /></a>
                        </div>
                    </div><!--//end #content_book_final-->
                </div><!--//end #content-->
                <div id="side_bar"><!--start side_bar-->
                    <div class="widget"><!--start widget-->
                    	<h2>Guess You Like</h2>
                        <div class="widget_gallery">
                        	<img src="${my_local }/movie/img/pic_widget1.jpg" alt="" width="222" height="133" />
                            <h3><a href="#">Mandee Monet Presents the Triumph of A Broken. ..</a></h3>
                        </div>
                        <div class="widget_gallery">
                        	<img src="${my_local }/movie/img/pic_widget2.jpg" alt="" />
                            <h3><a href="#">Amazing Typos for you </a></h3>
                        </div>
                        <div class="widget_gallery">
                        	<img src="${my_local }/movie/img/pic_widget3.jpg" alt="" />
                            <h3><a href="#">TSA 2012 Motorsports</a></h3>
                        </div>
                        <div class="next_big_img"><a href="#"><img src="${my_local }/movie/img/tripple_arrow3.jpg" alt="" width="25" height="8" /></a></div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Tag</h2>
                        <div class="widget_info">
                        	<p><a><span class="search_by_tag" value="">LOVE</span></a>/<a>CLASSIC</a><br /> <a href="#">COMEDY/WAR</a><br /> <a href="#">SPORTS/MUSIC</a></p>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Movie List</h2>
                        <div class="widget_info">
                        	<ul>
                            	<li><a href="#">INCREDIBLES</a></li>
                                <li><a href="#">INCREDIBLES</a></li>
                                <li class="color1"><a href="#">HUNGER GAMES</a></li>
                                <li class="color1"><a href="#">HUNGER GAMES</a></li>
                                <li><a href="#"><small>KILL BILL</small></a></li>
                                <li><a href="#"><small>KILL BILL</small></a></li>
                                <li><a href="#"><span>TITANIC</span></a></li>
                                <li><a href="#"><span>TITANIC</span></a></li>
                            </ul>
                            <small><a href="#">Add</a> / <a href="#">Change</a> Locations</small>
                        </div>
                    </div><!--//end .widget-->
                </div><!--//end #side_bar-->
            </div><!--//end #main_content-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    <script src="${my_local }/movie/js/jquery.min.js" type="text/javascript"></script>
    <script src="${my_local }/movie/js/latest.js" type="text/javascript"></script>
    <script src="${my_local }/movie/js/box.slider.js" type="text/javascript"></script>
	<script src="${my_local }/movie/js/mobilyslider.js" type="text/javascript"></script>
    <script src="${my_local }/movie/js/movie_fad.js" type="text/javascript"></script>
    <script src="${my_local }/movie/js/smoothscroll.js" type="text/javascript"></script>
	<script type="text/javascript">
	  $(document).ready(function(){
		$('#slider1').bxSlider();
	  });
	</script>
</body>
</html>                                                                                                   