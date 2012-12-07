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
	<title>Celebrities Search Result</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/style.css" />
    
</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a name="top" href="index.html"><img src="${my_local}/movie/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="${my_domain}/activity/index.action">EVENT</a></li>
                    	<li class="current_page_item"><a href="${my_domain }/movie/movieHomePage.action" title="MOVIE">MOVIE</a></li>
                        <li><a href="${my_domain }/book/bookHomePage.action" title="BOOK">BOOK</a></li>
                        <li><a href="${my_domain }/music/musicHomePage.action" title="MUSIC">MUSIC</a></li>
                        <li><a href="${my_domain }/user/my.action" title="MY TRIBUS">MY TRIBUS</a></li>
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
            	<div id="search_bg"><!--start search_bg-->
                	<form action="${my_domain }/movie/search/2.action" id="single_search_bar" name="single_search_bar">
                    	<p class="search_text"><input name="single_search_name" type="text" value="${searchName }" onclick="(this.value='')"  onblur="if(this.value=='')(this.value='${searchName }')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
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
            	<div id="main_search_content"><!--start main_search_content-->
                	<div id="search_lftcol"><!--start search_lftcol-->
                    	<div class="search_title"><!--start search_title-->
                        	<h2>SEARCH: <c:out value="${searchName}" /></h2>
                            <div class="book_btn">
                            	<a href="${my_domain }/movie/search/1.action?single_search_name=${searchName }">Movies</a>
                                <a class="select_book" href="#">Celebrities</a>
                                <a href="${my_domain }/movie/search/3.action?single_search_name=${searchName }">Tribus Lists</a>
                            </div>
                        </div><!--//end .search_title-->
                        <div id="gallery_book"><!--start gallery_book-->
                        	<c:forEach items="${searchResults}" var="thisResult">
                        	<div class="book_feature">
                            	<a href=""><img src="${thisResult.starPic }" alt="" width="123" height="184" /></a>
                                <h2><a href="#"><c:out value="${thisResult.starName }" /></a></h2>
                            </div>
                            </c:forEach>
                            <div class="clear"></div>
                        </div><!--//end #gallery_book-->
                        <div id="follower_apgi" class="space_pagi">
							${pageStr}
                        </div>
                    </div><!--//end #search_lftcol-->


                </div><!--//end #main_search_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="${my_local }/movie/js/smoothscroll.js" type="text/javascript"></script>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   