<%@ page language="java" import="java.util.*,model.User" pageEncoding="utf8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)request.getAttribute("user");
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myAlbum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" media="screen,projection" href="../tribus/view/activity/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="../tribus/view/activity/css/style.css" />
   
    <link rel="stylesheet" type="text/css" media="screen,projection" href="../tribus/view/activity/css/css3.css" />
    <script type="text/javascript" src="../tribus/view/js/jquery-1.6.2.min.js"></script>
  </head>
  
  <body>
 <div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="index.html"><img src="../tribus/view/activity/img/logo.png" alt="" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li class="current_page_item">
										<a href="activity/index.action">CITY</a>
									</li>
									<li>
										<a href="movie/movieHomePage.action" title="MOVIE">MOVIE</a>
									</li>
									<li>
										<a href="book/bookHomePage.action" title="BOOK">BOOK</a>
									</li>
									<li>
										<a href="music/musicHomePage.action" title="MUSIC">MUSIC</a>
									</li>
									<li>
										<a href="user/my.action" title="My TRIBUS">MY TRIBUS</a>
									</li>
                    </ul>
                    <div class="header_search">
                    	<form action="#">
                        	<p class="txt_header"><input type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="../tribus/view/activity/img/icon_header1.png" alt="" /></a></span>
                        <span><a href="#"><img src="../tribus/view/activity/img/icon_header2.png" alt="" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="activity/search.action">
                    	<p class="search_text"><input id="searchCondition" name="searchCondition" type="text" value="Seach activity, activity time, activity location" onclick="if(this.value=='Seach activity, activity time, activity location')(this.value='')"  onblur="if(this.value=='')(this.value='Seach activity, activity time, activity location')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" /></a>
                        <a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="../tribus/view/activity/img/icon_message1.jpg" alt="" /><span>5</span></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message2.jpg" alt="" /></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message3.jpg" alt="" /></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message4.jpg" alt="" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="user/my/<%=user.getUserId()%>">welcome back,<%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></h3>
                            <span>New York City</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="edit_album_content"><!--start edit_album_content-->
                	<c:forEach items="${activityAlbumList}" var="item" varStatus="i">
					<c:choose>
					<c:when test="${i.index%3==2}">
					 <div class="edit_album_widget padding_rgt_null"><!--start edit_album_widget-->
					</c:when>
					<c:otherwise>
					<div class="edit_album_widget"><!--start edit_album_widget-->
					</c:otherwise>
					</c:choose>
					
                    	<h2>${item.albumName }</h2>
                    	<div class="frame">
                        	<a href="activity/showPicList.action?albumId=${item.albumId}"><img src="${item.albumCover}" alt="" width=192 height=192/></a>
                        	<div class="icon_edit"><img src="../tribus/view/activity/img/icon_edit.jpg" alt="" width=19 height=19/></div>
							
                        </div>
						 <p class="edit_message">
                        	<textarea id="picDescription${i.index }" name="picDescription${i.index }" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')"><c:choose><c:when test="${item.albumDescription!=null}">${item.albumDescription}</c:when><c:otherwise>Introduction</c:otherwise></c:choose></textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                   <c:choose>
					<c:when test="${i.index==2}">
                    <div class="clear"></div>
                    </c:when>
                    </c:choose>
                     <input type="hidden" name="albumId${i.index}" id="albumId${i.index}" value="${item.albumId }" />
                     </c:forEach>
                    <div id="follower_apgi">
                            <a href="#" class="prev"> </a>
                              <c:forEach items="${pageNumbers}" var="item">  
             <a href="activity/album.action?page=${item}&activityId=${activityId}">
             ${item}
             </a>&nbsp
            
            </c:forEach>
                            <a href="#" class="next"></a>
                    	</div>
                    	                    <div class="save_edit">
<input type="button" id="save_button" name="Save" value="Save"  onclick="javascript:getValues();" >

</div>
                </div><!--//end #edit_album_content-->
                
                
                
                
            </div>
            
            <script type="text/javascript">
									function getValues(){
									
										$("#desc1").attr('value',$("#picDescription0").val());
										$("#desc2").attr('value',$("#picDescription1").val());
										$("#desc3").attr('value',$("#picDescription2").val());
										$("#desc4").attr('value',$("#picDescription3").val());
										$("#desc5").attr('value',$("#picDescription4").val());
										$("#desc6").attr('value',$("#picDescription5").val());
							
								        $("#albumIdd1").attr('value',$("#albumId0").val());
								        $("#albumIdd2").attr('value',$("#albumId1").val());
								        $("#albumIdd3").attr('value',$("#albumId2").val());
								        $("#albumIdd4").attr('value',$("#albumId3").val());
								        $("#albumIdd5").attr('value',$("#albumId4").val());
								        $("#albumIdd6").attr('value',$("#albumId5").val());
									    
												
									
										$("#hidden_form").submit();
									}
									</script>
									
									<form method="post" action="activity/editAlbum.action" id="hidden_form">
									
										
										<input type="hidden" name="desc1" id="desc1" value="" />
										<input type="hidden" name="albumIdd1" id="albumIdd1" value="" />
										
										
										<input type="hidden" name="desc2" id="desc2" value="" />
										<input type="hidden" name="albumIdd2" id="albumIdd2" value="" />
										
										<input type="hidden" name="desc3" id="desc3" value="" />
										<input type="hidden" name="albumIdd3" id="albumIdd3" value="" />
										
										<input type="hidden" name="desc4" id="desc4" value="" />
										<input type="hidden" name="albumIdd4" id="albumIdd4" value="" />
										
										<input type="hidden" name="desc5" id="desc5" value="" />
										<input type="hidden" name="albumIdd5" id="albumIdd5" value="" />
										
										<input type="hidden" name="desc6" id="desc6" value="" />
										<input type="hidden" name="albumIdd6" id="albumIdd6" value="" />
										<input type="hidden" name="activityId" id="activityId" value="${activityId}" />
									    
									</form>
            
            
            <!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
  </body>
</html>
