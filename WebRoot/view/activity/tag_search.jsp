<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
    <%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-rt.tld" %>
    <%@ page import="config.GlobleConfig" %><%
        								
	request.setAttribute("path1",GlobleConfig.pathPath);
	request.setAttribute("path2",GlobleConfig.pathPath1);
    
     %>
     <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Tribus_tag_Activity_Final</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/css/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo">
        		<a name="top" href="${path2}activity/index.action">
        			<img src="${path1}activity/img/logo.png" alt="" width="59" height="65" />
        		</a>
        	</div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    		<li class="current_page_item">
										<a href="${path2}activity/index.action">CITY</a>
									</li>
                    	<li><a href="${path2}movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${path2}book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${path2}music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li><a href="${path2}user/my.action" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="${path2}user/searchAll.action">
                        	<p class="txt_header"><input id="search" name="search" type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#">
                    	<img src="${path1}activity/img/icon_header1.png" alt="" width="10" height="11" /></a>
                    	</span>
                        <span><a href="${path2}user/editForm.action">
                        <img src="${path1}activity/img/icon_header2.png" alt="" width="12" height="13" /></a>
                        </span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${path2}activity/search.action">
                    	<p class="search_text"><input id="searchCondition" name="searchCondition"   type="text" value="Seach activity, activity time, activity location" onclick="if(this.value=='Seach activity, activity time, activity location')(this.value='')"  onblur="if(this.value=='')(this.value='Seach activity, activity time, activity location')" /></p>
                <p class="search_submit"><input type="submit" value=" " /></p>
                    
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${path1}activity/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${path1}activity/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="${path2}userMail/box/0.action"><img src="${path1}activity/img/icon_message1.jpg" alt="" width="22" height="22" /><span></span></a>
                            <a href="${path2}user/about.action"><img src="${path1}activity/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/police.action"><img src="${path1}activity/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/logout.action"><img src="${path1}activity/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3>${user.userAlias}</h3>
                            <span>${userProf.profCity}</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="main_content"><!--start main_content-->
            	<div id="content"><!--start content-->
                	<div id="container_tag_activity"><!--start container_tag_activity-->
                    	<h2 class="tag_title">Activities by Tags</h2>
                    	<div class="tag_btn_area"><!--start tag_btn_area-->
                        	<ul>
                            	<li><a href="#"><span><span>Entertainment</span></span></a></li>
                                <li><a href="#"><span><span>Education</span></span></a></li>
                                <li><a href="#"><span><span>Other</span></span></a></li>
                            </ul>
                        </div><!--//end .tag_btn_area-->
                        <div id="tag_gallery"><!--start tag_gallery-->
                        	
                        	
                        	
                        	<c:forEach items="${activityList}" var="city" varStatus="in">
                        	<c:choose>
                        	<c:when test="${in.index%2==0 }">
                        	<div class="tag_feature differ_tag_feature"><!--start tag_feature-->
                        	</c:when>
                        	<c:otherwise>
                        	<div class="tag_feature"><!--start tag_feature-->
                        	</c:otherwise>
                        	</c:choose>
                            	<div class="tag_icon">
                                	<a href="#"><img src="${path1}activity/img/icon_tag1.png" alt="" width="16" height="17" /></a>
                                    <a href="#"><img src="${path1}activity/img/icon_tag2.png" alt="" width="16" height="16" /></a>
                                </div>
                                <div class="tag_frame1">
                                  <a href="${path2}activity/info.action?activityId=${city.activity.activityId} ">  <img src="${city.activity.activityPic}" alt="" width="111" height="111" /></a>
                                    <h3><a href="${path2}activity/info.action?activityId=${city.activity.activityId} ">${city.activity.activityName }</a></h3>
                                </div>
                            </div><!--//end .tag_feature-->
							</c:forEach>                                                      
                                                        
                        </div><!--//end #tag_gallery-->
                    </div><!--//end #container_tag_activity-->
                </div><!--//end #content-->
                <div id="side_bar"><!--start side_bar-->
                	<div class="widget"><!--start widget-->
                    	<h2>Tag</h2>
                        <div class="widget_info">
                        	<p class="upper">
                        	                        	
                        	<c:forEach items="${activityTagsList}" var="tags">
                        	
                        	<a href="${path2}activity/searchByTag.action?tagName=${tags.classifiedName}">
                        		${tags.classifiedName}
                        	</a>/
                        	
                        	</c:forEach>
                        	                        	                        		                        	                         	                        	                        	                        	
                        	</p>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Top Citys</h2>
                        <div class="widget_info space_widget_info">
                        	<ul>
									<c:forEach items="${topTribusCity}" var="item">
										<li>
											<a href="${path2}activity/byCityTag.action?city=${item}">${item}</a>
										</li>
									</c:forEach>
								</ul>

                            <small></small>
                        </div>
                    </div><!--//end .widget-->
                    <div class="widget"><!--start widget-->
                    	<h2>Guess You Like</h2>
                    	<c:forEach items="${recommendActivity}" var="item">
                        <div class="widget_gallery">
                        	<img src="${item.activityPic}" alt="" width="222" height="133" />
                            <h3><a href="${path2}activity/info.action?activityId=${city.activityId}">${item.activityName}</a></h3>
                        </div>
                       </c:forEach>
                       <c:if test="${arrowFlag==1 }">   
                         <div class="next_big_img"><a href="${path2}activity/getRecommends.action"><img src="${path1}activity/img/tripple_arrow3.jpg" alt="" width="25" height="8" /></a>
                         </div>
                         </c:if>
                   
                    </div><!--//end .widget-->
                </div><!--//end #side_bar-->
            </div><!--//end #main_content-->
            <div id="footer"><div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="js/smoothscroll.js" type="text/javascript"></script>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   
