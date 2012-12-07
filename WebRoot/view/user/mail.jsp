<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c" %><%
%><%@ page import="config.GlobleConfig" %>
<%
	request.setAttribute("domain",GlobleConfig.localhost);
 %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Tribus_Mail</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${domain}user/css/css3.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->
    <script type="text/Javascript" src="${domain}js/jquery-1.6.2.min.js"></script>
	<script type="text/Javascript">
	 $(document).ready(function(){	   									
	 							
	 });
	 function getAllSelect(){
	 //	$(".sel").each(function(){
	 	//	if($(this).attr("src").test()){
	 		
	 		//}
	 	//});
	 }
	</script>
</head>

<body>
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="index.html"><img src="${domain}user/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li class="current_page_item"><a href="#">CITY</a></li>
                    	<li><a href="#" title="MOVIE">MOVIE</a></li>
                        <li><a href="#" title="BOOK">BOOK</a></li>
                        <li><a href="#" title="MUSIC">MUSIC</a></li>
                        <li><a href="#" title="MY TRIBUS">MY TRIBUS</a></li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${domain}user/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="${domain}user/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="${domain}user/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${domain}user/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${domain}user/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="${domain}user/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain}user/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="${domain}user/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3>${user.userAlias}</h3>
                            <span>${userProf.profCity}</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="main_content"><!--start main_content-->
            	<div id="mail_main_content"><!--start mail_main_content-->
                	<div id="mail_lftcol"><!--start mail_lftcol-->
                    	<div class="mail_title"><!--start mail_title-->
                        	<h2>Shengâs Mail</h2>
                        	<div class="comment_box">
                            	<div class="drop_arrow"></div>
                                <div class="bottom_droparrow"></div>
                            	<div class="number_of_mail">
                                	<ul>
                                    	<li class="reminder"><span>${reminder}</span>Reminder </li>
                                        <li>
                                        <a href="${domain}userMail/inbox/1/1/4.action">
                                        Out Box
                                        </a>
                                        </li>
                                        <li class="inbox">
                                        <a href="${domain}userMail/inbox/1/2/4.action">
                                        In Box
                                        </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div><!--//end .mail_title-->
                        <div id="fourcol_content"><!--start fourcol_content-->
                        	<div id="four_coltitle">
                            	<h3 class="title_1">Form:</h3>
                                <h3 class="title_2">Title:</h3>
                                <h3 class="title_3">Time:</h3>
                                <div class="title_4">
                                	<h3>Select:</h3>
                                    <a href="#">Delete</a>
                                </div>
                            </div>
                            
                            
                            <c:forEach items="${inbox}" var="mails">
                            	<div class="select_row">
                            	<span class="select_col1">kaikaikaity </span>
                                <span class="select_col2">
                                
                                <a href="${domain}user/messageInfo.action?id="></a>
                                ${ mails.messageTitle } 
                                
                                </span>
                                <span class="select_col3">${ mails.messageDate }</span>
                                <span><a href="#">                                
                                <c:if test="${mails.isRead == 0}">
                                <img class="sel" src="${domain}user/img/blue_round1.jpg" alt="" width="11" height="11" />
                                </c:if>
                                <c:if test="${mails.isRead == 1}">
                                <img class="sel" src="${domain}user/img/blue_round2.jpg" alt="" width="11" height="11" />
                                </c:if>                                                                
                                </a>
                                </span>
                            </div>
                            </c:forEach>
                                                   
                            <div class="select_row space_select_row">
                            	<span class="select_col1">JeSuisTz   </span>
                                <span class="select_col2">Anyone excited ... Gravity MV?</span>
                                <span class="select_col3">15 hours ago </span>
                                <span><a href="#"><img src="${domain}user/img/blue_round2.jpg" width="13" height="13" alt="" /></a></span>
                            </div>
                            
                        </div><!--//end #fourcol_content-->
                        <div class="delete_option"><a href="javascript:getAllSelect();">Delete</a></div>
                        <div id="follower_apgi" class="mail_pagi">
                            <a href="#" class="prev">   &nbsp; </a>
                             
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
                             
                            <a href="#" class="next"> &nbsp;</a>
                        </div>
                    </div><!--//end #mail_lftcol-->
                    <div id="mail_rgtcol"><!--start mail_rgtcol-->
                    	<ul>
                        	<li><span><img src="${domain}user/img/icon_mail_1.jpg" alt="" width="20" height="17" /></span><a href="#">Mail To People I Follow</a></li>
                            <li><span><img src="${domain}user/img/icon_mail_1.jpg"  alt="" width="20" height="17" /></span><a href="#">Mail To People Follow Me</a></li>
                            <li><span><img src="${domain}user/img/icon_mail_2.jpg"  alt="" width="16" height="16" /></span><a href="#">Block List</a></li>
                        </ul>
                    </div><!--//end #mail_rgtcol-->
                	<div class="clear"></div>
                </div><!--//end #mail_main_content-->
            </div><!--//end #main_content-->
            <div id="footer"><div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   