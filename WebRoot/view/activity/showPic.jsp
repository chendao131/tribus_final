<%@ page language="java" import="java.util.*,model.ActivityPic,model.User,config.GlobleConfig"
	pageEncoding="gbk"%>
<%@page import="model.ActivityPic"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-rt.tld" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		ActivityPic activityPic=(ActivityPic)request.getAttribute("activityPics");
Integer p=0;
User user=(User)request.getAttribute("user");
request.setAttribute("path1",GlobleConfig.pathPath);
request.setAttribute("path2",GlobleConfig.pathPath1);
	String wish=(String)request.getAttribute("wish");
			String tribus=(String)request.getAttribute("tribus");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>My JSP 'showPic.jsp' starting page</title>

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
	<script type="text/javascript">
	
    function check(){
    	if($.trim($("#commentContent").val()) == ""){
    		alert("empty comment");
    	}else{
    		$("#formComment").submit();
    	}
    	
    }
     function WishList(url){
      http_request = false;
    if (window.XMLHttpRequest) {    								// 非IE浏览器
        http_request = new XMLHttpRequest();							//创建XMLHttpRequest对象
    } else if (window.ActiveXObject) {     							// IE浏览器
        try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");		//创建XMLHttpRequest对象
        } catch (e) {
            try {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");	//创建XMLHttpRequest对象
           } catch (e) {}
        }
    }
    if (!http_request) {
        alert("不能创建XMLHttpRequest对象实例！");
        return false;
    }
    http_request.onreadystatechange = getResult;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);	
    }
    
    getResultWishList(){
        if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById("wishList").innerHTML=http_request.responseText;;	//设置提示内容
 
        } else {     													// 请求页面有错误
            alert("您所请求的页面有错误！");
        }
    }
    }
    
    
 function TribusList(url){
      http_request = false;
    if (window.XMLHttpRequest) {    								// 非IE浏览器
        http_request = new XMLHttpRequest();							//创建XMLHttpRequest对象
    } else if (window.ActiveXObject) {     							// IE浏览器
        try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");		//创建XMLHttpRequest对象
        } catch (e) {
            try {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");	//创建XMLHttpRequest对象
           } catch (e) {}
        }
    }
    if (!http_request) {
        alert("不能创建XMLHttpRequest对象实例！");
        return false;
    }
    http_request.onreadystatechange = getResult;							//调用返回结果处理函数
    http_request.open('GET', url, true);								//创建与服务器的连接
    http_request.send(null);	
    }
    
    getResultWishList(){
        if (http_request.readyState == 4) {     							// 判断请求状态
        if (http_request.status == 200) {     							// 请求成功，开始处理返回结果
            document.getElementById("tribusList").innerHTML=http_request.responseText;;	//设置提示内容
 
        } else {     													// 请求页面有错误
            alert("您所请求的页面有错误！");
        }
    }
    }
	</script>
	</head>

	<body>
	
	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="${path2}activity/index.action"><img src="${path1}activity/img/logo.png" alt="" /></a></div>
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
										<a href="${path2}user/my.action" title="user/my.action">MY TRIBUS</a>
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
                        	<a href="${path2}userMail/box/0.action"><img src="${path1}activity/img/icon_message1.jpg" alt="" width="22" height="22" /><span></span></a>
                            <a href="${path2}user/about.action"><img src="${path1}activity/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/police.action"><img src="${path1}activity/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/logout.action"><img src="${path1}activity/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="${path2}user/my.action"><%=user.getUserAlias()%></a><%}else{ %><a href="${path2}user/login.action">login</a><%} %></h3>
                            <span>${userProf.profCity}</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="album_content"><!--start album_content-->
                	<div id="album_type">
                    	<ul>
                        	<li class="album"><a href="${path2}user/friendHome/${user.userId}.action">By ${user.userAlias } </a>(<a href="${path2}activity/album.action?activityId=${activityId }">Albums</a>)</li>  
                            <li><fmt:formatDate value="${activityPics.recordDate}" type="date" dateStyle="medium"/> </li>
                        </ul>
                    </div>
                    <div id="gallery"><!--start gallery-->
                    	<h2>${albumName}</h2>
                        <div id="banner_pic_page">
                        	<img src="${activityPics.picPath }" alt="" width=600 height=365/>
                        </div>
                        <div id="slbum_descript">
                        	<p>${activityPics.picDescription }</p>
                        </div>
                        <div id="pic_wish_list"><!--start pic_wish_list-->
                        	<div id="pic_wish_inner">
                            	<div class="pic_social">
                                	<a href="#"><img src="${path1}activity/img/icon_facebook.jpg" alt="" /></a>
                                	<a href="#"><img src="${path1}activity/img/icon_tweet.jpg" alt="" /></a>
                                </div>
                                <ul>
      
                                    
                                	<li><a id="wishList"   href="javascript:WishList('${path2}user/addWishList/city/${activityId}.action')"><%if(wish!=null&&wish.equals("ok")){%>Already Added<%}else{%>+ Wish List<%}%></a></li>
                                	<li><a id="tribusList"  href="javascript:TribusList('${path2}user/addTribusList/city/${activityId}.action')"><%if(tribus!=null&&tribus.equals("ok")){%>Already Added<%}else{%>+Tribus List<%}%></a></li>
                                </ul>
                                <span><a href="#"><img src="${path1}activity/img/icon_album.jpg" alt="" /></a></span>
                            </div>
                        </div><!--//end #pic_wish_list-->
                       
                        
                        
                           <div id="comment_box_brief"><!--start comment_box_brief-->
                        <%if(session.getAttribute("user")!=null){ %>
                      
                        <div class="comment_box"><!--start comment_box-->
                            <div class="arrow_author2"></div>
                            <div class="comment_first_content"><!--start comment_first_content-->
                                <div class="pic_author">
                                    <div class="arrow_author1"></div>
                                    <img src="" alt="" width="73" height="73" />
                                </div>
                                <form id="formComment" name="formComment" method="post" action="${path2}activity/addPicComment.action?activityPicId=${activityPicId}&albumName=${albumName}&activityId=${activityId}">
                                <div class="author_total_rgt">
                                    <div class="author_speech">
                                        <textarea id="commentContent" name="commentContent" rows="10" cols="10" onclick="if(this.value=='Name Says : ...')(this.value='')"  onblur="if(this.value=='Name Says : ...')(this.value='')">Name Says : ...</textarea>
                                    </div>
                                    <div class="author_icon">
                                        <span><img src="${path1}activity/img/icon_album.jpg" alt="" width="20" height="19" /></span>
                                        <div class="btn_done"><a href="javascript: check();">Done</a></div>
                                    </div>
                                </div>
                                </form>
                            </div><!--//end .comment_first_content-->
                        </div><!--//end .comment_box-->
                        <%} %>
                     
                        
                      
                          
                       
                        <c:forEach items="${userCommentList}" var="item" varStatus="in">
                        
                            <c:choose>
                       <c:when test="${in.index %2!=0}">
                         <div class="comment_box bg_differ"><!--start comment_box-->
                       </c:when>
                       <c:otherwise>
                       <div class="comment_box"><!--start comment_box-->
                       </c:otherwise>
                       </c:choose>
                            <div class="comment_scnd_content"><!--start comment_scnd_content-->
                                <div class="pic_author">
                                  <a href="${path2}user/friendHome/${item.userId}.action"><img src="${item.userPic}" width="72" height="72" /></a>
                                </div>
                                <div class="author_total_rgt">
                                    <div class="author_speech">
                                        <h3><a href="${path2}user/friendHome/${item.userId}.action">${item.userName}</a></h3>
                                        <p>${item.commentContent}</p>
                                        <span><fmt:formatDate value="${item.commentDate}" type="date" dateStyle="medium"/> </span>
                                    </div>
                                </div>
                            </div><!--//end .comment_scnd_content-->
                        </div><!--//end .comment_box-->
                       </c:forEach>  
                    </div><!--//end #comment_box_brief-->
                       
                       
                       
                       
                       
                        <div id="comment_pagination">
                        	<a href="#" class="prev"></a>
                            <c:forEach items="${pageNumbers}" var="item">  
             <a href="${path2}activity/showPic.action?activityPicId=${activityPicId}&page=${item}&albumName=${albumName}">
             ${item}
             </a>
            </c:forEach>
                            <a href="#" class="next"></a>
                        </div>
                    </div><!--//end #gallery-->
                </div><!--//end #album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
	</body>
</html>
