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
	<title>Review</title>
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
                        <a href="#"><img src="${my_local}/music/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="${my_domain }/userMail/box/0.action"><img src="${my_local }/music/img/icon_message1.jpg" alt="" width="22" height="22" /><c:if test="${unreadMail > 0}"><span><c:out value="${unreadMail}"/></span></c:if></a>
                            <a href="${my_domain }/user/police.action"><img src="${my_local }/music/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${my_domain }/user/about.action"><img src="${my_local }/music/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${my_domain }/user/logout.action"><img src="${my_local }/music/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
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
                            	<div class="games_bg"><h3><c:out value="${singleReviewMain.itemName}" /> </h3></div>
                                <span>(<a href="#"><c:out value="${singleReviewMain.commentNumber}" /></a>)</span>
                                <h4><a href="${my_domain }/review/musicReviewAction/${singleReviewMain.itemId }.action">Create a Review</a></h4>
                                <div class="rating_icon">
									<c:choose>
                                		<c:when test="${singleReviewMain.itemRating>=0.5 && singleReviewMain.itemRating<1.5}">                                		
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>		                                    		                                   
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singleReviewMain.itemRating>=1.5 && singleReviewMain.itemRating<2.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singleReviewMain.itemRating>=2.5 && singleReviewMain.itemRating<3.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singleReviewMain.itemRating>=3.5 && singleReviewMain.itemRating<4.5}">
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting.jpg" alt="" width="20" height="19" /></a>
		                                    <a href="#"><img src="${my_local}/movie/img/icon_ratting2.jpg" alt="" width="20" height="19" /></a>
		                               </c:when>
		                               <c:when test="${singleReviewMain.itemRating>=4.5}">
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
                            	<a href="${my_domain }/user/friendHome/${singleReviewMain.userId }.action"><img src="${singleReviewMain.userPic}" alt="" class="pic_review" width="44" height="43" /></a>
                            	<h2><c:out value="${singleReviewMain.commentTitle}" /></h2>
                                <div class="rating_icon">
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
                                </div>
                            </div><!--//end .review_ratting-->
                        </div><!--//end .review_post-->
                        <div class="games_info"><!--start games_info-->
                        	<div class="games_btm_icon"><img src="${my_local}/music/img/icon_album.jpg" alt="" width="20" height="19"/></div>
                        	<div class="games_social">
                            	<span>By <a href="${my_domain }/user/friendHome/${singleReviewMain.userId }.action"><c:out value="${singleReviewMain.userName}" /></a></span>
                                <div class="games_media">
                                	<div id='fb-root'></div>
                                	<a onclick='postToFeed(); return false;'><img src="${my_local}/music/img/icon_facebook2.jpg" alt="" width="15" height="15" /></a>
                                    <a id='msg'></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_tweet2.jpg" alt="" width="16" height="15" /></a><!--
                                    <a href="#">+ Wish List</a>
                                    <a href="#">+Tribus List</a>
                                --></div>
                            </div>
                            <div class="games_descript"><!--start games_descript-->
                            	<p><c:out value="${singleReviewMain.commentContent}" /></p>
                            </div><!--//end .games_descript-->
                        </div><!--//end .games_info-->
                        
                        <div class="realted_article"><!--start realted_article-->
                        	<h2>RELATED ARTICLES</h2>
                            <ul>
                            	<c:forEach items="${relatedArticles}" var="thisArticle">
                            	<li><a href="${my_domain }/review/musicReview/${thisArticle.itemId}.action"><c:out value="${thisArticle.itemName}" />, <c:out value="${thisArticle.commentTitle}" /> <c:out value="${thisArticle.commentDate}" /></a></li>
                            	</c:forEach>
                            </ul>
                        </div><!--//end .realted_article-->
                        
                        <div id="comment_box_area"><!--start comment_box_area-->
                        
                        	<div class="comment_box" id="comment_box_write"><!--start comment_box-->
                            	<div class="arrow_author2"></div>
                            	<div class="comment_first_content"><!--start comment_first_content-->
                                	<div class="pic_author">
                                    	<div class="arrow_author1"></div>
                                        <img src="${myPic}" alt="" width="73" height="73" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                            <textarea id="quick_comment_content" rows="10" cols="10" onclick="if(this.value=='Name Says : ...')(this.value='')"  onblur="if(this.value=='')(this.value='Name Says : ...')">Name Says : ...</textarea>
                                        </div>
                                        <div class="author_icon">
                                        	<span><img src="${my_local}/music/img/icon_album.jpg" alt="" width="20" height="19" /></span>
                                            <div class="btn_done"><a href="javascript:add_comment()">Done</a></div>
                                        </div>
                                    </div>
                                </div><!--//end .comment_first_content-->
                            </div><!--//end .comment_box-->
                            
                            <c:forEach items="${singleReviewQuickComments}" var="thisQuickComment" varStatus="r">
                            <c:if test="${r.index%2== 0 }">
                            <div class="comment_box bg_differ"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="${thisQuickComment.userPic}" alt="" width="72" height="72" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3><c:out value="${thisQuickComment.userName}" /> (New York)</h3>
                                            <div class="edit_tools">
                                        	<label  class="text_label"><c:out value="${thisQuickComment.commentContent}" /></label>
                                            <c:if test="${thisQuickComment.userId == singleReviewMain.userId}" >
                                            <div style="display: block;" class="edit"></div>
                                            </c:if>
                                            <input type="text" />
                                         </div>
                                            <span><c:out value="${thisQuickComment.commentDate}" /></span>
                                        </div>
                                    </div>
                                </div><!--//end .comment_scnd_content-->
                            </div><!--//end .comment_box-->
                            </c:if>
                            <c:if test="${r.index%2!= 0 }">
                            <div class="comment_box"><!--start comment_box-->
                            	<div class="comment_scnd_content"><!--start comment_scnd_content-->
                                	<div class="pic_author">
                                        <img src="${thisQuickComment.userPic}" alt="" width="72" height="72" />
                                    </div>
                                    <div class="author_total_rgt">
                                    	<div class="author_speech">
                                        	<h3><c:out value="${thisQuickComment.userName}" /></h3>
                                            <div class="edit_tools">
                                        	<label  class="text_label"><c:out value="${thisQuickComment.commentContent}" /></label>
                                            <c:if test="${thisQuickComment.userId == singleReviewMain.userId}" >
                                            <div style="display: block;" class="edit"></div>
                                            </c:if>
                                            <input type="text" />
                                         </div>
                                            <span><c:out value="${thisQuickComment.commentDate}" /></span>
                                        </div>
                                    </div>
                                </div><!--//end .comment_scnd_content-->
                            </div><!--//end .comment_box-->
                            </c:if>
                            </c:forEach>
                            
                        </div><!--//end #comment_box_area-->
                        <div id="comment_pagination">
                            ${pageStr}
                        </div>
                    </div><!--//end #review_lftcol-->
                    <div id="review_side_bar"><!--start review_side_bar-->
                    	<div class="review_btn"><a href="${my_domain }/review/musicReviewAction/${singleReviewMain.itemId }.action">Create a Review</a></div>
                    	<div class="review_feature">
                        	<img src="${singleReviewMain.itemPic}" alt="" width="142" height="142"/>
                            <h3><c:out value="${singleReviewMain.itemName}" /> Page <a href="${my_domain }/music/${singleReviewMain.itemId }.action"><img src="${my_local}/music/img/tripple_arrow3.jpg" alt="" /></a></h3>
                        </div>
                        <div class="rating_widget">
                        	<h2>Seach by Rating <span>(<a href="#"><c:out value="${singleReviewMain.commentNumber}" /></a>)</span></h2>
                            <ul>
                            	<li>
                                	<a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[4]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[3]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[2]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[1]}" /></span>
                                </li>
                                <li>
                                	<a href="#"><img src="${my_local}/music/img/icon_rating1.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <a href="#"><img src="${my_local}/music/img/icon_rating2.jpg" alt="" /></a>
                                    <span><c:out value="${rateNumber[0]}" /></span>
                                </li>
                            </ul>
                        </div>
                    </div><!--//end #review_side_bar-->
                    <div class="clear"></div>
                </div><!--//end #review_main-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
    <script src="${my_local}/music/js/smoothscroll.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/jquery-1.js" type="text/javascript"></script>
    <script src="${my_local}/music/js/easy-editable-text.js" type="text/javascript"></script>
	<script type="text/javascript">
			var i = 33;
			var j = 36;
		function add_comment(){
			var d = new Date();
			var userName= "${userName}";
			var content = $("#quick_comment_content").val();
			var text = "<div class=\"comment_scnd_content\"><!--start comment_scnd_content-->"+
                                	"<div class=\"pic_author\">"+			
                 "<img src=\"${myPic}\" alt=\"\" width=\"72\" height=\"72\" />"+                           "</div>"+ 	
				"<div class=\"author_total_rgt\">"+
                      "<div class=\"author_speech\">"+
                      		"<h3>"+userName+"</h3>"+
                      			"<div class=\"edit_tools\">"+
                                     "<label  class=\"text_label\">"+content+"</label>"+
                                            	"<div style=\"display: block;\" class=\"edit\"></div>" +
                                            	"<input type=\"text\" />"+
                                "</div>"+    
                                "<span>"+d.toLocaleTimeString()+"</span>"+                               
                      "</div>"+
                "</div>"+
                       "</div>";                             
			var content_div = document.createElement("div");
			//content_div.id = j+"";			
			if(j%2 == 0){
     			content_div.className = "comment_box";
     			j=j+1;
			}else{			
				content_div.className="comment_box bg_differ";
				j=j+1;
			}
			content_div.innerHTML = text;
			//document.getElementById("comment_box_area").appendChild(content_div);
			$("#comment_box_write").after(content_div);
			$("#"+i).slideDown();
			i++;		
			
			var dataString = "comment="+ content+"&date="+d;
			$.ajax({  
				type: "POST",  
				url: "${my_domain}/review/addMusicComment/"+${commentId}+".action",  
				data: dataString,  
				success: function() {  
				}  
			});
		}
	</script>
	
		    <script> 
      FB.init({appId: "167819743335514", status: true, cookie: true});

      function postToFeed() {

        // calling the API ...
        var obj = {
          method: 'feed',
          link: '${my_domain}/review/musicReview/${singleReviewMain.itemId}.action',
          picture: '${singleReviewMain.itemPic}',
          name: '${singleReviewMain.itemName}',
          caption: '${singleReviewMain.commentTitle}',
          description: ''
        };

        function callback(response) {
          document.getElementById('msg').innerHTML = "Post ID: " + response['post_id'];
        }

        FB.ui(obj, callback);
      }
    </script>
    
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