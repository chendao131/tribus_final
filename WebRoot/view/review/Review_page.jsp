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
	<title>Tribus_Review_page</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/review/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/review/style.css" />

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local}/review/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${my_domain}/activity/index.action">CITY</a></li>
                    	<li class="current_page_item"><a href="${my_domain}/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain}/book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${my_domain}/music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li><a href="${my_domain}/my.action" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${my_local}/review/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${my_local}/review/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="${my_local}/review/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local}/review/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${my_local}/review/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${my_local}/review/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local}/review/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${my_local}/review/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><c:out value="${userName}" /></h3>
                            <span><c:out value="${userCity }" /></span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="review_main"><!--start review_main-->
                	<div id="review_lftcol"><!--start review_lftcol-->
                    	<div class="review_post"><!--start review_post-->
                        	<h2 class="review_title">Review </h2>
                            <div class="review_ratting"><!--start review_ratting-->
                            	<div class="games_bg"><h3>The Hunger Games </h3></div>
                                <span>(<a href="#">1768</a>)</span>
                                <h4><a href="#">Create a Review</a></h4>
                                <div class="rating_icon">
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                </div>
                            </div><!--//end .review_ratting-->
                            <div class="rating_descript"><!--start rating_descript-->
                            	<p>The Hunger Games, a vision of human life in all its nasty, brutish brevity demands to be devoured.</p>
                            </div><!--//end .rating_descript-->
                        </div><!--//end .review_post-->
                        
                        <c:forEach items="${reviewPages}" var="thisReview" >
                        <div class="review_post"><!--start review_post-->
                            <div class="review_ratting"><!--start review_ratting-->
                            	<h2><c:out value="${thisReview.commentTitle}" /></h2>
                                <div class="rating_icon">
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                </div>
                            </div><!--//end .review_ratting-->
                            <div class="rating_descript"><!--start rating_descript-->
                            	<p><c:out value="${thisReview.commentContent}" /></p>
                                <div class="social_rating"><!--start social_rating-->
                                	<div class="social_rating_lft">
                                    	<a href="#"><img src="${my_local}/review/img/rating_tweet.jpg" alt="" width="14" height="13" /></a>
                                        <a href="#"><img src="${my_local}/review/img/rating_facebook.jpg" alt="" width="13" height="13" /></a>
                                    </div>
                                    <div class="rating_wish">
                                    	<a href="#">+ Track List</a>
                                        <a href="#">+Tribus List</a>
                                    </div>
                                    <span><c:out value="${thisReview.commentDate}" /></span>
                                </div><!--//end .social_rating-->
                            </div><!--//end .rating_descript-->
                        </div><!--//end .review_post-->
                        </c:forEach>
                        
                       
                        <div id="rating_pagi">
                            <div id="follower_apgi">
                                <a href="#" class="prev"> </a>
                                <a href="#">1</a>   
                                <a href="#">2 </a>   
                                <a href="#">3 </a>   
                                <a href="#">4 </a>   
                                <a href="#">5</a>    
                                <a href="#">6 </a>   
                                <a href="#">7 </a>   
                                <a href="#">8</a>    
                                <a href="#">9 </a>   
                                <a href="#">10 </a>   
                                <a href="#">11</a>    
                                <a href="#">12</a>    
                                <a href="#">13</a>    
                                <a href="#">14</a>  
                                <a href="#" class="next"></a>
                       	 	</div>
                     	</div>
                    </div><!--//end #review_lftcol-->
                    <div id="review_side_bar"><!--start review_side_bar-->
                    	<div class="review_btn"><a href="#">Create a Review</a></div>
                    	<div class="review_feature">
                        	<img src="${my_local}/review/img/pic_games.jpg" alt="" />
                            <h3>The Hunger Games Page <a href="#"><img src="${my_local}/review/img/tripple_arrow3.jpg" alt="" width="25" height="8" /></a></h3>
                        </div>
                        <div class="rating_widget">
                        	<h2>Seach by Rating <span>(<a href="#">1768</a>)</span></h2>
                            <ul>
                            	<li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <span>452</span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <span>543</span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <span>339</span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <span>221</span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12" /></a>
                                    <span>231</span>
                                </li>
                            </ul>
                        </div>
                    </div><!--//end #review_side_bar-->
                    <div class="clear"></div>
                </div><!--//end #review_main-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="${my_local}/review/js/smoothscroll.js" type="text/javascript"></script>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   