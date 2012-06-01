<%@ page language="java" import="java.util.*,model.User,model.Activity,model.ActivityClassified,vo.ActivityGoingMax,vo.UserComment" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

  Calendar ca = Calendar.getInstance();
      int year = ca.get(Calendar.YEAR);//获取年份
      int month=ca.get(Calendar.MONTH)+1;//获取月份
      int day=ca.get(Calendar.DATE);//获取日
	List<Activity> hottestActivityList =(List<Activity>)request.getAttribute("hottestActivityList");
	Activity latestActivity=(Activity)request.getAttribute("latestActivity");
	List<ActivityGoingMax> activityGoingMaxList=(List<ActivityGoingMax>)request.getAttribute("activityGoingMaxList");
	List<UserComment> commentListRandom=(List<UserComment>)request.getAttribute("commentListRandom");
	List<ActivityClassified> activityTagsList= (List<ActivityClassified>)request.getAttribute("activityTagsList");
	User u=(User)session.getAttribute("user");
	int day1=0;
	if(latestActivity!=null){
	day1=latestActivity.getActivityStartTime().getDate();}
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'newActivityIndex.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" type="text/css" href="css/nav.css">
<link rel="stylesheet" type="text/css" href="css/myTribus.css">
<link rel="stylesheet" type="text/css" href="css/cityHomePage.css">
  <script type="text/javascript">
function search(){
var a =document.getElementById("txt1");
window.location.href="activity/search.action?searchCondition="+a.value;
}
</script>
  </head>
  
  <body>
   
<div class="main">

  <div class="main_nav">
  <table class="nav" cellspacing="0">
<tr>
<td cellspacing="0"><a class="city-active" href="#"></a></td>
<td cellspacing="0"><a class="movie" href="#"></a></td>
<td cellspacing="0"><a class="book" href="#"></a></td>
<td cellspacing="0"><a class="music" href="#"></a></td>
<td cellspacing="0"><a class="mytribus" href="#"></a></td>
</tr>
  </table>
   <div class="setting" style="float:right;position:relative;right:13px;bottom:12px;">
   	<p><img src="setting.png" /></p>
    <p style="position:relative;bottom:5px;"><img src="setting.png" /></p>
   </div>
  <div class="search" style="float:right;">
      	<p style="float:right; padding-right:60px;"><input type="text" id="sea" /></p>
   </div>
   <div id="resource_search" style="position:relative;top:72px;left:46px">
    	<div><img src="search bar.png" /> <img src="Search-button.png" style="position:relative;bottom:8px;right:50px;"/></div>
    </div>
   <table style="float:right;" id="nav_sub">
    	<tr>
        	<td width="235" height="63" align="right">
            <img src="../tribus/view/image/face.png" /> 	
            </td>
            <td width="273"><div style="border: 2px double rgb(222, 222, 222); -moz-border-radius: 15px 15px 15px 15px; border-radius: 15px; -webkit-border-radius: 15px;"><div id="nav_nameAndCity" style="float:right;"><%if(u==null){%><a href="user/login.action">login</a><%}else{ %>Welcome back,<a href="user/my/<%=u.getUserId()%>"><%=u.getUserAlias()%><%} %></a></div> <img class="nav_social" src="../tribus/view/image/logout.png" /><img class="nav_social" src="../tribus/view/image/notification.png" /><img class="nav_social" src="../tribus/view/image/about.png" /><img class="nav_social" src="../tribus/view/image/logout.png" />  <br></div></td>
        </tr>     
    </table>  
   
   </div>  <!-- yuan jiao kuang -->
	</div>  <!--main_nav -->
    
    <div id="city_header">
    	<div id="city_header_left">
            <div id="city_main_image"><%if(hottestActivityList.get(0)!=null) {%><a href="activity/info.action?activityId=<%=hottestActivityList.get(0).getActivityId()%>"><img src="<%=hottestActivityList.get(0).getActivityPic() %>" width="677" height="366"/><%} %></div>
            <div id="city_main_intro"><p><%if(hottestActivityList.get(0)!=null) {%><a href="activity/info.action?activityId=<%=hottestActivityList.get(0).getActivityId()%>"><%=hottestActivityList.get(0).getActivityName()%> <%} %></p></div>
      	</div> <!-- city_header_left -->
        <div id="top_tribus_new">
        	<div id="top_tribus_new_flag">
            	<img src="../tribus/view/image/flag.png" />
            </div>
        	<div>
                <ul>
                <c:forEach items ="${hottestActivityList}" var="forum" varStatus="s">

          	 <c:if test="${forum.userId != null}">
                    <li>
                       <a href="activity/info.action?activityId=${forum.activityId}">
          	      <img src="${forum.activityPic}" width="38" height="38" />
          	           <p>${forum.activityName}</p>
          	           </a> 
                    </li>
          	 
	 </c:if>    	 
          
          </c:forEach>

             
                </ul>
            </div>
            <div id="top_tribus_new_more"><%if(hottestActivityList.size()>4){ %><a href="activity/byHotSearch.action"><img src="../tribus/view/image/more.png" /><%} %></a></div>
        </div>
        <br><br><br><br>
    </div><!-- city_header -->
        
    </div> <!-- top_tribus_new -->
    
    <div id="city_calendar">
    	<div id="activity_board"><img src="../tribus/view/image/next-city-event-box.png" width="900"; height="72px"; />
        <br><br>
        </div>
        <div id="activity_date"><img src="../tribus/view/image/calendar-background.png" width="900"; height="26px"; />
        </div>
        <br><br><br>
    </div>
    
    <div id="activity_recommend">
    	<div id="activity_recommend_left">
        	<div id="activity_friends_recommend">
            	<div id="activity_flag"><img src="../tribus/view/image/flag.png" style="position:relative;top:38px; left:22px;" />
                </div>
                <div>
                	<ul>
                    	<%for (int i=0;i<commentListRandom.size();i++){ %>
                      <li class="activity_box_<%if(i%2==0){ %>1<%}else{ %>2<%} %>"><div class="activity_content">
                            	<div class="activity_content_up">
                                	<span><a href="activity/info.action?activityId=<%=(commentListRandom.get(i).getActivity()).getActivityId()%>"><%=commentListRandom.get(i).getActivityName() %>(<%=commentListRandom.get(i).getNumbers()%> &nbsp people) </a></span><br>
                                    <span><a href="activity/info.action?activityId=<%=(commentListRandom.get(i).getActivity()).getActivityId()%>"><%=commentListRandom.get(i).getStartTime() %> <%=commentListRandom.get(i).getStreet() %>, <%=commentListRandom.get(i).getCity() %></a></span>
                                </div>
                                <div class="activity_content_down">
                                	<div style="float:left;"><a href="user/my/<%=commentListRandom.get(i).getUserId() %>"><img src="<%=commentListRandom.get(i).getUserPic() %>" width="72" height="72" /></a></div>
                                    <div style="float:left;">
                                    	<ul>
                                        	<li><a href="user/my/<%=commentListRandom.get(i).getUserId() %>"><%=commentListRandom.get(i).getUserName() %></a></li>
                                            <li><%=commentListRandom.get(i).getCommentContent() %></li>
                                            <li><%=commentListRandom.get(i).getStartTime()%></li>
                                        </ul>
                                    </div>
                                    <br><br><br><br>
                                </div> <!-- activity_content_down -->
                                <br><br><br><br><br><br><br><br>
                            </div></li>
                    <%} %>
                    </ul>
                </div>
            </div>
            <div id="activity_hear_from_friends">
            	<div><img class="flag" src="../tribus/view/image/flag.png" /></div>
                    <div class="content" style="border: 2px double rgb(222, 222, 222); -moz-border-radius: 15px 15px 15px 15px; border-radius: 15px; -webkit-border-radius: 15px;"> 
                    <ul>
                    	<li>hear from friends</li>
                        <li>hear from friends</li>
                        <li><img src="../tribus/view/image/hear-from-friend-pic.png" /></li>
                    </ul>
                    <ul>
                    	<li>hear from friends</li>
                        <li>hear from friends</li>
                        <li>hear from friends</li>
                        <li>hear from friends</li>
                    </ul>
                    </div>
           	</div> <!-- activity_hear_from_friends -->
        </div> <!-- activity_recommend_left -->
        <div id="activity_recommend_right">
        	<div id="top_events">
            	<div class="flag"><img src="../tribus/view/image/flag.png" />
                </div>
                <div>
                    <ul style="float:left;">
                        <%for(int i=0;i<activityGoingMaxList.size();i++){ %>
                        <li><a href="activity/info.action?activityId=<%=activityGoingMaxList.get(i).getActivityId()%>"><img src="<%=activityGoingMaxList.get(i).getPicPath() %>" width="113" height="149"  style="float:left;" /></a>
                            <div class="intro">
                                <ul>
                                    <li><a href="activity/info.action?activityId=<%=activityGoingMaxList.get(i).getActivityId()%>"><%=activityGoingMaxList.get(i).getActivityName() %></a></li>
                                    <li>Host:<%=activityGoingMaxList.get(i).getUserName() %></li>
                                    <li><%=activityGoingMaxList.get(i).getActivityStartTime() %></li>
                                    <li><a href="activity/info.action?activityId=<%=activityGoingMaxList.get(i).getActivityId()%>"><%=activityGoingMaxList.get(i).getNumbers()%>&nbsp Join </a>></li>
                                </ul>
                            </div>
                        </li>
                        <%if(i==2)break;} %>
                    </ul>
				</div>
                <br>
                <div style="position:relative;top:550px;"><img src="../tribus/view/image/more.png" /></div>
            </div> <!-- top_events -->
            
            <div class="top_events_tag">
            	<div><img src="../tribus/view/image/Secondary-column-title-box.png" /></div>
                <div>
                	<ul>
                	<li>
                	<c:forEach items ="${activityTagsList}" var="forum" varStatus="s">
				
                  <c:if test="${s.index%3==0 && s.index!=0}">
     			
         			</li> 
         			<li>        
                  </c:if> 

          	     <c:if test="${forum!= null}">
            	<a href="activity/searchByTag.action?tagName=${ forum.classifiedName}">${ forum.classifiedName}</a>/
          	 </c:if>    	 
          	
          </c:forEach>
           </li>
                    </ul>
                </div>
            </div>
            
            <div class="top_events_tag">
            	<div><img src="../tribus/view/image/Secondary-column-title-box.png" /></div>
                <div>
                	<ul>
                	 <c:forEach items="${topTribusCity}" var="item">
                    	<li><a href="activity/byCityTag.action?city=${item}">${item}</a></li>
                    	 </c:forEach>
                    </ul>
                </div>
            </div>
            
        </div> <!-- activity_recommend_right -->
    </div>
</div> <!-- main -->
  </body>
</html>
