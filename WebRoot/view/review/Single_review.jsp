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
	<title>Tribus_Single_review</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/review/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/review/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/review/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->


</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local}/review/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${my_domain}/activity/index.action">EVENT</a></li>
                    	<li class="current_page_item"><a href="${my_domain }/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain }/book/bookHomePage.action" title="BOOK">BOOK</a></li>
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
                            <span><c:out value="${userCity}" /></span>
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
                            	<div class="games_bg"><h3><c:out value="${singleReviewMain.itemName}" /> </h3></div>
                                <span>(<a href="#"><c:out value="${singleReviewMain.commentNumber}" /></a>)</span>
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
                            	<p><c:out value="${singleReviewMain.itemBrief}" /></p>
                            </div><!--//end .rating_descript-->
                        </div><!--//end .review_post-->
                        <div id="dreadcrumbs">
                        	<a href="#">Review &gt;</a> <a href="#"><c:out value="${singleReviewMain.itemName}" /> &gt;</a> <c:out value="${singleReviewMain.commentTitle}" />
                        </div>
                        
                        <div class="review_post"><!--start review_post-->
                            <div class="review_ratting space_rating"><!--start review_ratting-->
                            	<img src="${my_local}/review/img/pic_review.jpg" alt="" class="pic_review" width="44" height="43" />
                            	<h2><c:out value="${singleReviewMain.commentTitle}" /></h2>
                                <div class="rating_icon">
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12"  /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12"  /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" width="12" height="12"  /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" width="12" height="12"  /></a>
                                </div>
                            </div><!--//end .review_ratting-->
                        </div><!--//end .review_post-->
                        <div class="games_info"><!--start games_info-->
                        	<div class="games_btm_icon"><img src="${my_local}/review/img/icon_album.jpg" alt="" width="20" height="19"/></div>
                        	<div class="games_social">
                            	<span>By <c:out value="${singleReviewMain.userName}" /></span>
                                <div class="games_media">
                                	<a href="#"><img src="${my_local}/review/img/icon_facebook2.jpg" alt="" width="15" height="15" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_tweet2.jpg" alt="" width="16" height="15" /></a>
                                    <a href="#">+ Wish List</a>
                                    <a href="#">+Tribus List</a>
                                </div>
                            </div>
                            <div class="games_descript"><!--start games_descript-->
                            	<p><c:out value="${singleReviewMain.commentContent}" /></p>
                            </div><!--//end .games_descript-->
                        </div><!--//end .games_info-->
                        
                        <div class="realted_article"><!--start realted_article-->
                        	<h2>RELATED ARTICLES</h2>
                            <ul>
                            	<c:forEach items="${relatedArticles}" var="thisArticle">
                            	<li><c:out value="${thisArticle.itemName}" />, <c:out value="${thisArticle.commentTitle}" /> <c:out value="${thisArticle.commentDate}" /></li>
                            	</c:forEach>
                            </ul>
                        </div><!--//end .realted_article-->
                        
                         <div id="comment_box_area"><!--start comment_box_area-->
                        
                        	<div class="comment_box"><!--start comment_box-->
                            	<div class="arrow_author2"></div>
                            	<div class="comment_first_content"><!--start comment_first_content-->
                                	<div class="pic_author">
                                    	<div class="arrow_author1"></div>
                                        <img src="img/pic_author1.png" alt="" width="73" height="73" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                            <textarea rows="10" cols="10" onclick="if(this.value=='Name Says : ...')(this.value='')"  onblur="if(this.value=='')(this.value='Name Says : ...')">Name Says : ...</textarea>
                                        </div>
                                        <div class="author_icon">
                                        	<span><img src="img/icon_album.jpg" alt="" width="20" height="19" /></span>
                                            <div class="btn_done"><a href="#">Done</a></div>
                                        </div>
                                    </div>
                                </div><!--//end .comment_first_content-->
                            </div><!--//end .comment_box-->
                            
                            <div class="comment_box bg_differ"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="img/pic_author2.png" alt="" width="72" height="72" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3>Pierce Tetro Cooper (New York)</h3>
                                            <div class="edit_tools">
                                        	<label  class="text_label">Am I actually goin to see you there or are you too big<br />time to mingle wit us little people?</label>
                                            <div style="display: block;" class="edit"></div>
                                            <input type="text" />
                                         </div>
                                            <span>Feb.18, 2012  at 1:43pm</span>
                                        </div>
                                    </div>
                                </div><!--//end .comment_scnd_content-->
                            </div><!--//end .comment_box-->
                            
                            <div class="comment_box"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="img/pic_author3.png" alt="" width="72" height="72" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3>Pierce Tetro Cooper (New York)</h3>
                                            <div class="edit_tools">
                                        	<label  class="text_label">Am I actually goin to see you there or are you too big<br />time to mingle wit us little people?</label>
                                            <div style="display: block;" class="edit"></div>
                                            <input type="text" />
                                         </div>
                                            <span>Feb.18, 2012  at 1:43pm</span>
                                        </div>
                                    </div>
                                </div><!--//end .comment_scnd_content-->
                            </div><!--//end .comment_box-->
                            
                            <div class="comment_box bg_differ"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="img/pic_author4.png" alt="" width="72" height="72" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3>Pierce Tetro Cooper (New York)</h3>
                                              <div class="edit_tools">
                                                <label  class="text_label">Am I actually goin to see you there or are you too big<br />time to mingle wit us little people?</label>
                                                <div style="display: block;" class="edit"></div>
                                                <input type="text" />
                                             </div>
                                            <span class="color_differ">Feb.18, 2012  at 1:43pm</span>
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
                    </div><!--//end #review_lftcol-->
                    <div id="review_side_bar"><!--start review_side_bar-->
                    	<div class="review_btn"><a href="#">Create a Review</a></div>
                    	<div class="review_feature">
                        	<img src="${singleReviewMain.itemPic}" alt="" width="142" height="217"/>
                            <h3><c:out value="${singleReviewMain.itemName}" /> Page <a href="#"><img src="${my_local}/review/img/tripple_arrow3.jpg" alt="" /></a></h3>
                        </div>
                        <div class="rating_widget">
                        	<h2>Seach by Rating <span>(<a href="#"><c:out value="${singleReviewMain.commentNumber}" /></a>)</span></h2>
                            <ul>
                            	<li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[4]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[3]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[2]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[1]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/review/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/review/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[0]}" /></span>
                                </li>
                            </ul>
                        </div>
                    </div><!--//end #review_side_bar-->
                    <div class="clear"></div>
                </div><!--//end #review_main-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    <script src="${my_local}/review/js/smoothscroll.js" type="text/javascript"></script>
    <script src="${my_local}/review/js/jquery-1.js" type="text/javascript"></script>
    <script src="${my_local}/review/js/easy-editable-text.js" type="text/javascript"></script>

</body>
</html>                                                                                                   