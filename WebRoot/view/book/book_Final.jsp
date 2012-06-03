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
	<title>Tribus_book_Final</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/book/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/book/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/book/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local}/book/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${my_domain}/activity/index.action">CITY</a></li>
                    	<li><a href="${my_domain }/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li class="current_page_item"><a href="${my_domain }/book/bookHomePage.action" title="BOOK">BOOK</a></li>
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
                    	<span class="space_btm"><a href="#"><img src="${my_local}/book/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${my_local}/book/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="#">
                    	<p class="search_text"><input type="text" value="Book, Auther, Book list" onclick="if(this.value=='Book, Auther, Book list')(this.value='')"  onblur="if(this.value=='')(this.value='Book, Auther, Book list')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${my_local}/book/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local}/book/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${my_local}/book/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${my_local}/book/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local}/book/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local}/book/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
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
                        	<div class="book_gallery_lftcol"><!--start book_gallery_lftcol-->
                            	<h2>New Fiction</h2>
                            	<div class="book_gallery">
                                	<c:forEach items="${booksByTag1}" var="thisBook"> 
                                	<a href="${my_domain}/book/${thisBook.bookId }.action"><img src="${thisBook.bookPic }" alt="" width="78" height="120" /></a>
                                	</c:forEach>
                                </div>
                            </div><!--//end #book_gallery_lftcol-->
                            <div class="book_gallery_lftcol"><!--start book_gallery_lftcol-->
                            	<h2>New Non-Fiction</h2>
                            	<div class="book_gallery">
                                	<c:forEach items="${booksByTag2}" var="thisBook"> 
                                	<a href="${my_domain}/book/${thisBook.bookId }.action"><img src="${thisBook.bookPic }" alt="" width="78" height="120" /></a>
                                	</c:forEach>
                                </div>
                            </div><!--//end #book_gallery_lftcol-->
                            <div class="clear"></div>
                        </div><!--//end #book_gallery_area-->
                        <div id="book_rating"><!--start book_rating-->
                        	<h2>Friends Recommend</h2>
                        	<ul id="slider1"><li>
                        	<c:forEach items="${homePageFriendRecommends}" var="thisRecommend" varStatus="fr">
                        	<div class="book_rating_lftcol"><!--start book_rating_lftcol-->
                            	<div class="rating_lftcol_cont">
                                	<div class="rating_book"><a href="${my_domain }/book/${thisRecommend.itemId }.action"><img src="${thisRecommend.itemPic }" alt="" width="120" height="185" /></a></div>
                                    <div class="book_rating_info">
                                    	<div class="book_icon">
                                        	<span>Rating:</span>
                                            <a href="#"><img src="${my_local }/book/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/book/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/book/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/book/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                            <a href="#"><img src="${my_local }/book/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
                                        </div>
                                        <h5><a href="${my_domain }/user/friendHome/${thisRecommend.friendId }.action"><c:out value="${thisRecommend.friendName}" /></a> Saw it </h5>
                                        <small>2 Minutes ago</small>
                                        <div class="zoom"><a href="#"><img src="${my_local }/book/img/icon_zoom.jpg" alt="" width="19" height="19" /></a></div>
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
                                <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                <a href="#"><img src="${my_local}/book/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                            </div>
                            <div class="review_title_rgt">
                                <h3><a href="${my_domain }/review/bookReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
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
                                    <a href="#"><img src="${my_local}/book/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star2.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star2.png" alt="" width="12" height="12" /></a>
                                </div>
                                <div class="rating_star_rt"><a href="${my_domain }/review/bookReview/${thisReview.commentId }.action"><img src="${my_local}/book/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                            </div>
                        </div><!--//end .rating_feature-->
                    </div><!--//end .review_box-->
                    </c:if> 
                    
                    <c:if test="${r.index > 0 }">
                    <div class="review_box"><!--start review_box-->
                        <div class="review_title_area"><!--start review_title_area-->
                            <div class="review_small_img">
                                <a href="${my_domain }/user/friendHome/${thisReview.userId }.action"><img src="${thisReview.userPic}" alt="" width="31" height="31" /></a>
                                <a href="#"><img src="${my_local}/book/img/black_arrow.jpg" alt="" width="9" height="16" /></a>
                            </div>
                            <div class="review_title_rgt">
                                <h3><a href="${my_domain }/review/bookReview/${thisReview.commentId }.action"><c:out value="${thisReview.commentTitle}" /></a></h3>
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
                                    <a href="#"><img src="${my_local}/book/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star1.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star2.png" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/book/img/icon_star2.png" alt="" width="12" height="12" /></a>
                                </div>
                                <div class="rating_star_rt"><a href="${my_domain }/review/bookReview/${thisReview.commentId }.action"><img src="${my_local}/book/img/tripple_arrow4.png" alt="" width="27" height="9" /></a></div>
                            </div>
                        </div><!--//end .rating_feature-->
                    </div><!--//end .review_box-->
                    </c:if>
                    </c:forEach>
                    <div class="more_review">
                        <a href="${my_domain }/review/book.action">More reviews <img src="${my_local}/book/img/tripple_arrow3.jpg" alt="" /></a>
                    </div>
                </div><!--//end #content_book_final-->
                </div><!--//end #content-->
                <div id="side_bar"><!--start side_bar-->
                    <div class="widget"><!--start widget-->
                    	<h2>Guess You Like</h2>
                        <div class="widget_gallery">
                        	<img src="${my_local}/book/img/pic_widget1.jpg" alt="" width="222" height="133" />
                            <h3><a href="#">Mandee Monet Presents the Triumph of A Broken. ..</a></h3>
                        </div>
                        <div class="widget_gallery">
                        	<img src="${my_local}/book/img/pic_widget2.jpg" alt="" />
                            <h3><a href="#">Amazing Typos for you </a></h3>
                        </div>
                        <div class="widget_gallery">
                        	<img src="${my_local}/book/img/pic_widget3.jpg" alt="" />
                            <h3><a href="#">TSA 2012 Motorsports</a></h3>
                        </div>
                        <div class="next_big_img"><a href="#"><img src="${my_local}/book/img/tripple_arrow3.jpg" alt="" width="25" height="8" /></a></div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Tag</h2>
                        <div class="widget_info">
                        	<p><a href="#">Fiction / History</a><br /><a href="#">Finance/ Law &amp; Order</a> <br /><a href="#">Music/ General Interest</a></p>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Book List</h2>
                        <div class="widget_info">
                        	<ul>
                            	<li><a href="#">Steve Jobs</a></li>
                                <li><a href="#">Bossypanys</a></li>
                                <li><a href="#"><small>The Mill River </small></a></li>
                                <li><a href="#"><small>Recluse</small></a></li>
                                <li><a href="#"><span>Daughter of Smoke</span></a></li> 
                                <li><a href="#"><span>and Bone</span></a></li>
                            </ul>
                        </div>
                    </div><!--//end .widget-->
                </div><!--//end #side_bar-->
            </div><!--//end #main_content-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    <script src="${my_local }/book/js/smoothscroll.js" type="text/javascript"></script>
    <script src="${my_local }/book/js/latest.js" type="text/javascript"></script>
    <script src="${my_local }/book/js/box.slider.js" type="text/javascript"></script>
	<script type="text/javascript">
	  $(document).ready(function(){
		$('#slider1').bxSlider();
	  });
	</script>

</body>
</html>                                                                                                   