<%@ page language="java" import="java.util.*,model.User,config.GlobleConfig" pageEncoding="utf8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)request.getAttribute("user");

request.setAttribute("path1",GlobleConfig.pathPath);
request.setAttribute("path2",GlobleConfig.pathPath1);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'myAlbum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/css/style.css" />
   
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${path1}activity/css/css3.css" />
    <script type="text/javascript" src="${path1}js/jquery-1.6.2.min.js"></script>
  </head>
  
  <body>
 <div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="index.html"><img src="${path1}activity/img/logo.png" alt="" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li class="current_page_item">
										<a href="${path2}activity/index.action">CITY</a>
									</li>
									<li>
										<a href="${path2}movie/movieHomePage.action" title="MOVIE">MOVIE</a>
									</li>
									<li>
										<a href="${path2}book/bookHomePage.action" title="BOOK">BOOK</a>
									</li>
									<li>
										<a href="${path2}music/musicHomePage.action" title="MUSIC">MUSIC</a>
									</li>
									<li>
										<a href="${path2}user/my.action" title="${path2}user/my.action">MY TRIBUS</a>
									</li>
                    </ul>
                    <div class="header_search">
                    	<form action="${path2}user/searchAll.action">
                        	<p class="txt_header"><input id="search" name="search" type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
                    </div>
                    <div class="header_icon_area">
                    	<span class="space_btm"><a href="#"><img src="${path1}activity/img/icon_header1.png" alt="" /></a></span>
                        <span><a href="#"><img src="${path1}activity/img/icon_header2.png" alt="" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${path2}activity/search.action">
                    	<p class="search_text"><input id="searchCondition" name="searchCondition" type="text" value="Seach activity, activity time, activity location" onclick="if(this.value=='Seach activity, activity time, activity location')(this.value='')"  onblur="if(this.value=='')(this.value='Seach activity, activity time, activity location')" /></p>
                        <p class="search_submit"><input type="submit" value=" " /></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${path1}activity/img/icon_facebook.jpg" alt="" /></a>
                        <a href="#"><img src="${path1}activity/img/icon_tweet.jpg" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="${path1}activity/img/icon_message1.jpg" alt="" /><span>5</span></a>
                            <a href="#"><img src="${path1}activity/img/icon_message2.jpg" alt="" /></a>
                            <a href="#"><img src="${path1}activity/img/icon_message3.jpg" alt="" /></a>
                            <a href="#"><img src="${path1}activity/img/icon_message4.jpg" alt="" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="user/my.action"><%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></h3>
                            <span>${userProf.profCity}</span>
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
                        	<a href="${path2}activity/showPicList.action?albumId=${item.albumId}"><img src="${item.albumCover}" alt="" width=192 height=192/></a>
                        	<div class="icon_edit"><img src="${path1}activity/img/icon_edit.jpg" alt="" width=19 height=19/></div>
							
                        </div>
						 <p class="edit_message">
                        	<textarea  <c:choose><c:when test="${flag==0}"> readonly=true </c:when></c:choose> id="picDescription${i.index }" name="picDescription${i.index }" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')"><c:choose><c:when test="${item.albumDescription!=null}">${item.albumDescription}</c:when><c:otherwise>Introduction</c:otherwise></c:choose></textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                   <c:choose>
					<c:when test="${i.index==2}">
                    <div class="clear"></div>
                    </c:when>
                    </c:choose>
                     <input type="hidden" name="albumId${i.index}" id="albumId${i.index}" value="${item.albumId }" />
                     </c:forEach>

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
									
									<form method="post" action="${path2}activity/editAlbum.action" id="hidden_form">
									
										
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
           
        </div><!--//end #main_area-->
          <div id="follower_apgi">
                            <a href="#" class="prev"> </a>
                              <c:forEach items="${pageNumbers}" var="item">  
             <a href="${path2}activity/album.action?page=${item}&activityId=${activityId}">
             ${item}
             </a>&nbsp
            
            </c:forEach>
                            <a href="#" class="next"></a>
                    	</div>
              <c:choose><c:when test="${flag==1}">
              <div class="save_edit">
<input type="button" id="save_button" name="Save" value="Save"  onclick="javascript:getValues();" >

</div>
</c:when></c:choose>
 <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
    </div><!--//end #wrapper-->
  </body>
</html>
