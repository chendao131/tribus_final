<%@ page language="java"
	import="java.util.*,model.User,model.Activity,model.ActivityClassified,vo.ActivityGoingMax,vo.UserComment,config.GlobleConfig"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%>
<%
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
request.setAttribute("path2",GlobleConfig.pathPath1);
	request.setAttribute("path1",GlobleConfig.pathPath);
//asdfsdfdsf	
	List days=(List<Integer>)request.getAttribute("days");
	%>




<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'index.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" media="screen,projection"
			href="${path1}activity/font/font.css" />
		<link rel="stylesheet" type="text/css" media="screen,projection"
			href="${path1}activity/css/style.css" />
		<script type="text/javascript">
function search(){
var a =document.getElementById("txt1");
window.location.href="${path2}activity/search.action?searchCondition="+a.value;
}
</script>
<style type="text/css">
.STYLE1 {color: #FF0000}
</style>
	</head>

	<body>
		<div id="wrapper">
			<!--start wrapper-->
			<div id="header">
				<!--start header-->
				<div class="logo">
					<a name="top" href="${path2}activity/index.action"><img
							src="${path1}activity/img/logo.png" alt="" /> </a>
				</div>
				<div id="header_rgt">
					<!--start header_rgt-->
					<div id="menu_bg">
						<div id="menu_lft">
							<div id="menu_rgt">
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
										<a href="${path2}user/my.action" title="MY TRIBUS">MY
											TRIBUS</a>
									</li>
								</ul>
								<div class="header_search">
									<form action="${path2}user/searchAll.action">
                        	<p class="txt_header"><input id="search" name="search" type="text" /></p>
                            <p class="submit_header"><input type="submit" value=" " /></p>
                        </form>
								</div>
								<div class="header_icon_area">
									<span class="space_btm"><a href="#"><img
												src="${path1}activity/img/icon_header1.png" alt="" /> </a> </span>
									<span><a href="${path2}user/editForm.action"><img
												src="${path1}activity/img/icon_header2.png" alt="" /> </a> </span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--//end #header_rgt-->
			</div>
			<!--//end #header-->
			<div id="main_area">
				<!--start main_area-->
				<div id="saerch_area">
					<!--start saerch_area-->
					<div id="search_bg">
						<!--start search_bg-->
						<form action="${path2}activity/search.action">
							<p class="search_text"><input id="txt1" name="searchCondition" type="text" value="Seach activity, activity time, activity location" onclick="if(this.value=='Seach activity, activity time, activity location')(this.value='')"  onblur="if(this.value=='')(this.value='Seach activity, activity time, activity location')" /></p>
							<p class="search_submit">
								<input type="submit" value=" " />
							</p>
						</form>
					</div>
					<!--//end #search_bg-->
					<div id="social_media">
						<!--start social_media-->
						<div id="social_lftcol">
							<a href="#"><img src="${path1}activity/img/icon_facebook.jpg"
									alt="" /> </a>
							<a href="#"><img src="${path1}activity/img/icon_tweet.jpg"
									alt="" /> </a>
						</div>
						<div id="social_box">
							<!--start social_box-->
							<div id="message">
								<a href="${path2}userMail/box/0/0.action"><img src="${path1}activity/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="${path2}user/about.action"><img src="${path1}activity/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/police.action"><img src="${path1}activity/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${path2}user/logout.action"><img src="${path1}activity/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
							</div>
							<div class="address">
									<h3>
									<%if(u==null){%><a href="${path2}user/loginForm.action">login</a>
									<%}else{ %>
									<a href="${path2}user/my.action"><%=u.getUserAlias()%>
										<%} %> </a>
								</h3>
								<span>${userProf.profCity}</span>
							</div>
						</div>
						<!--//end #social_box-->
					</div>
					<!--//end #social_media-->
				</div>
				<!--//end #saerch_area-->
				<div id="main_content">
					<!--start main_content-->
					<div id="content">
						<!--start content-->
						<%if(hottestActivityList.get(0)!=null) {%>
						<div id="banner">
							<a
								href="${path2}activity/info.action?activityId=<%=hottestActivityList.get(0).getActivityId()%>"><img
									src="<%=hottestActivityList.get(0).getActivityPic() %>"
									width="729" height="479" /> </a>
						</div>
						<div class="banner_title">
							<h2><%=hottestActivityList.get(0).getActivityName() %></h2>
							<h2><%=hottestActivityList.get(0).getActivityCity() %>,<%=hottestActivityList.get(0).getActivityState() %></h2>
						</div>
						<%} %>
						<div class="event_box">
							<!--start event_box-->
							<div class="event_bg">
								<div class="event_top">
									<div class="event_btm">
										<div class="event_lftcol">
											<img src="${path1}activity/img/icon_star.jpg" alt="" />
										</div>
										<div class="event_rgtcol">
											<h3>
												Your Next City Events
											</h3>
											<p>
												<%if(latestActivity==null){ %>No to-do events among this month<%}else{ %><a
													href="${path2}activity/info.action?activityId=<%=latestActivity.getActivityId() %>"><%=latestActivity.getActivityStartTime() %>&nbsp<%=latestActivity.getActivityName() %>
													<%} %> </a>

											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--//end .event_box-->
						<div class="calender">
							<!--start calender-->
						
							<div class="prev_home">
								<a href="#"></a>
							</div>
							<ul>
								<%for(int i=1;i<29;i++){ %>
								<%if(days!=null&&days.contains(i)){%>
								<li >
									<a style="color:red"  href="${path2}activity/searchByDay.action?day=<%=i%>&month=<%=month%>"><%if(i<=9){%>0<%}%><%=i%></a>
								</li>
								<%}else{ %>
								<li><%if(day==i){ %> <u><%}%><a href="javascript:void(0);"><%if(i<=9){%>0<%}%><%=i %></a><%if(day==i){ %> </u><%}%></li>
								<%} %>
								<%} %>
								<% if((year%400==0)||((year%100!=0)&&(year%4==0))){//如果是闰年
   if(month==2){//如果是闰年的二月必有29%>
								<%if(days!=null&&days.contains(29)){%>
								<li >
									<a style="color:red" href="${path2}activity/searchBy.action?day=<%=29%>&month=<%=month%>"><%=29%></a>
								</li>
								<%}else{ %>
								<li><%if(day==29){ %> <u><%}%><a href="javascript:void(0);"><%=29 %></a><%if(day==29){ %> </u><%}%></li>
								<%} }}%>
								<%if(month!=2){//如果不是二月 必有29、30%>
								<%if(days!=null&&days.contains(29)){%>
								<li >
									<a style="color:red" href="${path2}activity/searchByDay.action?day=<%=29%>&month=<%=month%>"><%=29%></a>
								</li>
								<%}else{ %>
								<li><%if(day==29){ %> <u><%}%><a href="javascript:void(0);"><%=29 %></a><%if(day==29){ %> </u><%}%></li>
								<%}
								if(days!=null&&days.contains(30)){%>
								<li>
									<a style="color:red"  href="${path2}activity/searchByDay.action?day=<%=30%>&month=<%=month%>"><%=30%></a>
								</li>
								<%}else{ %>
								<li><%if(day==30){ %> <u><%}%><a href="javascript:void(0);"><%=30 %></a><%if(day==30){ %> </u><%}%></li>
								<%} 
								 }%>

								<%if((month==1)||(month==3)||(month==5)||(month==7)||(month==8)||(month==10)||(month==12) ){System.out.println(month);%>
								<%if(days!=null&&days.contains(31)){%>
								<li>
									<a style="color:red"  href="${path2}activity/searchByDay.action?day=<%=31%>&month=<%=month%>"><%=31%></a>
								</li>
								<%}else{ %>
								<li><%if(day==31){ %> <u><%}%><a href="javascript:void(0);"><%=31 %></a><%if(day==31){ %> </u><%}%></li>
								<%} }%>
							</ul>
							<div class="month">
								<span><%=new Date().toString().substring(4, 7)%></span>
							</div>
							<div class="next_home">
								<a href="#"></a>
							</div>
						</div>
						<!--//end .calender-->
						<div class="ranger">
							<img src="${path1}activity/img/bgr_ranger.png" alt="" />
						</div>
						<c:choose>
							<c:when test="${flag==1}">
								<div class="ranger_icon">
									<a href="${path2}activity/activityCreatInit.action"><img
											src="${path1}activity/img/icon_ranger.png" alt="" /> </a>
								</div>

								<div class="event_btn">
									<a href="${path2}activity/activityCreatInit.action"><span><span>Create
												your Event</span> </span> </a>
								</div>
							</c:when>
						</c:choose>
						<div id="common_content_area">
							<!--start common_content_area-->
							<%if(session.getAttribute("user")==null) {%>

							<c:forEach items="${commentListRandom}" var="item" varStatus="i">

								<div class="common_box">
									<!--start common_box-->
									<c:choose>
										<c:when test="${i.index == 0}">
											<div class="title">
												<h2>
													Random Recommend
												</h2>
											</div>
										</c:when>
									</c:choose>
									<div class="common_box_bg">
										<div class="common_box_top">
											<div class="common_box_btm">
												<div class="scnd_feature">
													<!--start scnd_feature-->
													<div class="red_arrow_home"></div>
													<div class="scnd_feature_lft">
														<!--start scnd_feature_lft-->
														<a
															href="${path2}activity/info.action?activityId=${item.activity.activityId }"><img
																src="${item.activity.activityPic}" alt="" width=137
																height=187 /> </a>
														<div class="feature_info">
															<div class="feature_txt">
																<p>
																	<a
																		href="${path2}activity/info.action?activityId=${item.activity.activityId}">${item.activity.activityName}</a>
																	<br />
																	${item.activity.activityCity}&nbsp
																	${item.activity.activityStreet}
																	<br />
																	${item.activity.activityState }&nbsp
																	<br />
																	Fee: $ ${item.activity.activityFees } each
																</p>
															</div>
															<div class="feature_social">
																<div class="feature_icon">
																	<a href="#"><img
																			src="${path1}activity/img/icon_feature1.png" alt="" />
																	</a>
																	<a href="#"><img
																			src="${path1}activity/img/icon_feature2.png" alt="" />
																	</a>
																</div>
																<div class="feature_list">
																	<ul>

																	</ul>
																</div>
															</div>
														</div>
													</div>
													<!--//end .scnd_feature_lft-->
													<div class="scnd_feature_rgt">
														<ul>
															<c:forEach items="${item.userComments}" var="item">
																<li>
																	<span><a href="${path2}user/friendHome/${item.userId}.action"><img
																				src="${item.userPic}" alt="" width=41 height=39 />
																	</a> </span><small>: ${item.userComment}</small>
																</li>
															</c:forEach>
														</ul>
													</div>
												</div>
												<!--//end .scnd_feature-->

											</div>
										</div>
									</div>
								</div>
								<!--//end .common_box-->

							</c:forEach>
							<%}else{ %>


							<c:forEach items="${commentListRandom1}" var="item"
								varStatus="index">

								<div class="common_box">
									<!--start common_box-->
									<c:when test="${index == 0}">
										<div class="title">
											<h2>
												Friends Recommend
											</h2>
										</div>
									</c:when>
									<div class="common_box_bg">
										<div class="common_box_top">
											<div class="common_box_btm">
												<div class="scnd_feature">
													<!--start scnd_feature-->
													<div class="red_arrow_home"></div>
													<div class="scnd_feature_lft">
														<!--start scnd_feature_lft-->
														<a
															href="${path2}activity/info.action?activityId=${item.activity.activityId }"><img
																src="${item.activity.activityPic}" alt="" width="137"
																height="187" /> </a>
														<div class="feature_info">
															<div class="feature_txt">
																<p>
																	<a
																		href="${path2}activity/info.action?activityId=${item.activity.activityId}">${item.activity.activityName}</a>
																	<br />
																	${item.activity.activityCity}&nbsp
																	${item.activityStreet}
																	<br />
																	${item.activity.activityState }&nbsp
																	<br />
																	Fee: $ ${item.activity.activityFees } each
																</p>
															</div>
															<div class="feature_social">
																<div class="feature_icon">
																	<a href="#"><img
																			src="${path1}activity/img/icon_feature1.png" alt="" />
																	</a>
																	<a href="#"><img
																			src="${path1}activity/img/icon_feature2.png" alt="" />
																	</a>
																</div>
																<div class="feature_list">
																	<ul>

																	</ul>
																</div>
															</div>
														</div>
													</div>
													<!--//end .scnd_feature_lft-->
													<div class="scnd_feature_rgt">
														<ul>
															<c:forEach items="${item.userComments}" var="item">
																<li>
																	<span><a href="${path2}user/friendHome/${item.userId}.action"><img
																				src="${item.userPic}" alt="" />
																	</span><small>: ${item.userComment}</small>
																</li>
															</c:forEach>
														</ul>
													</div>
												</div>
												<!--//end .scnd_feature-->

											</div>
										</div>
									</div>
								</div>
								<!--//end .common_box-->

							</c:forEach>


							<%} %>

						</div>
						<!--//end #common_content_area-->
					</div>
					<!--//end #content-->
					<div id="side_bar">
						<!--start side_bar-->

						<!--//end .blue_widget-->
						<div class="widget">
							<!--start widget-->
							<h2>
								Tribus Top News
							</h2>

							<c:forEach items="${activityGoingMaxList}" var="item">
								<div class="bar_widget">
									<a href="${path2}activity/info.action?activityId=${item.activityId }"><img
											src="${item.picPath}" width="65" height="96" /> </a>
									<div class="bar_info">
										<h3>
											<a href="${path2}activity/info.action?activityId=${item.activityId }">${item.activityName
												} 
										</h3>
										<span>Host: ${item.userName }</span>
										<p>
											Open:${item.activityStartTime }
											<br />
										</p>
										<div class="join">
											<small>${item.numbers} (people)</small>
											<a>Join in</a>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
						<!--//end .widget-->
						<div class="widget">
							<!--start widget-->
							<h2>
								Tag
							</h2>
							<div class="widget_info">
								<p>
									<a
										href="${path2}activity/searchByTag.action?tagName=<%=activityTagsList.get(0).getClassifiedName() %>"><%=activityTagsList.get(0).getClassifiedName() %></a>/
									<a
										href="${path2}activity/searchByTag.action?tagName=<%=activityTagsList.get(1).getClassifiedName()%>"><%=activityTagsList.get(1).getClassifiedName()%></a>
									<br />
									<a
										href="${path2}activity/searchByTag.action?tagName=<%=activityTagsList.get(2).getClassifiedName()%>"><%=activityTagsList.get(2).getClassifiedName()%></a>/
									<a
										href="${path2}activity/searchByTag.action?tagName=<%=activityTagsList.get(3).getClassifiedName() %>"><%=activityTagsList.get(3).getClassifiedName() %></a>
									<br />
									<a
										href="${path2}activity/searchByTag.action?tagName=<%=activityTagsList.get(4).getClassifiedName() %>"><%=activityTagsList.get(4).getClassifiedName() %></a>/
									<a
										href="${path2}activity/searchByTag.action?tagName=<%=activityTagsList.get(5).getClassifiedName() %>"><%=activityTagsList.get(5).getClassifiedName() %></a>
								</p>
							</div>
						</div>
						<!--//end .widget-->
						<div class="widget">
							<!--start widget-->
							<h2>
								Top Citys
							</h2>
							<div class="widget_info">
								<ul>
									<c:forEach items="${topTribusCity}" var="item">
										<li>
											<a href="${path2}activity/byCityTag.action?city=${item}">${item}</a>
										</li>
									</c:forEach>
								</ul>

							</div>
						</div>
						<!--//end .widget-->
					</div>
					<!--//end #side_bar-->
				</div>
				<!--//end #main_content-->
				<div id="footer">
					<p>
						Copyright @ Tribus.us 2012.
						<span>All rights reserved</span>
					</p>
				</div>
				<div id="back_to_top">
					<a href="#top"></a>
				</div>
			</div>
			<!--//end #main_area-->
		</div>
		<!--//end #wrapper-->
	</body>
</html>
