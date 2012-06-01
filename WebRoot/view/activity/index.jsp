<%@ page language="java"
	import="java.util.*,model.User,model.Activity,model.ActivityClassified,vo.ActivityGoingMax,vo.UserComment"
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
	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

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
			href="../tribus/view/activity/font/font.css" />
		<link rel="stylesheet" type="text/css" media="screen,projection"
			href="../tribus/view/activity/css/style.css" /> 
		<script type="text/javascript">
function search(){
var a =document.getElementById("txt1");
window.location.href="activity/search.action?searchCondition="+a.value;
}
</script>
	</head>

	<body>
		<div id="wrapper">
			<!--start wrapper-->
			<div id="header">
				<!--start header-->
				<div class="logo">
					<a href="index.html"><img src="../tribus/view/activity/img/logo.png" alt="" />
					</a>
				</div>
				<div id="header_rgt">
					<!--start header_rgt-->
					<div id="menu_bg">
						<div id="menu_lft">
							<div id="menu_rgt">
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
										<a href="#" title="user/my.action">MY TRIBUS</a>
									</li>
								</ul>
								<div class="header_search">
									<form action="#">
										<p class="txt_header">
											<input type="text" />
										</p>
										<p class="submit_header">
											<input type="submit" value=" " />
										</p>
									</form>
								</div>
								<div class="header_icon_area">
									<span class="space_btm"><a href="#"><img
												src="../tribus/view/activity/img/icon_header1.png" alt="" />
									</a>
									</span>
									<span><a href="#"><img src="../tribus/view/activity/img/icon_header2.png"
												alt="" />
									</a>
									</span>
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
						<form action="activity/search.action">
							<p class="search_text">
								<input id="txt1" name="searchCondition"  type="text"
									value="Music, Musicial, Album, tribus music list"
									onclick="if (this.value == 'Music, Musicial, Album, tribus music list') (this.value = '')";
onblur="if(this.value=='')(this.value='Music, Musicial, Album, tribus music list')" />
							</p>
							<p class="search_submit">
								<input type="submit" value=" " />
							</p>
						</form>
					</div>
					<!--//end #search_bg-->
					<div id="social_media">
						<!--start social_media-->
						<div id="social_lftcol">
							<a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" />
							</a>
							<a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" />
							</a>
						</div>
						<div id="social_box">
							<!--start social_box-->
							<div id="message">
								<a href="#"><img src="../tribus/view/activity/img/icon_message1.jpg" alt="" /><span>5</span>
								</a>
								<a href="#"><img src="../tribus/view/activity/img/icon_message2.jpg" alt="" />
								</a>
								<a href="#"><img src="../tribus/view/activity/img/icon_message3.jpg" alt="" />
								</a>
								<a href="#"><img src="../tribus/view/activity/img/icon_message4.jpg" alt="" />
								</a>
							</div>
							<div class="address">
								<h3>
									<%if(u==null){%><a href="user/login.action">login</a>
									<%}else{ %>Welcome back,
									<a href="user/my/<%=u.getUserId()%>"><%=u.getUserAlias()%>
										<%} %>
									</a>
								</h3>
								<span>New York City</span>
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
								href="activity/info.action?activityId=<%=hottestActivityList.get(0).getActivityId()%>"><img
									src="<%=hottestActivityList.get(0).getActivityPic() %>" width="729" height="479"/>
							</a>
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
											<img src="../tribus/view/activity/img/icon_star.jpg" alt="" />
										</div>
										<div class="event_rgtcol">
											<h3>
												Your Next City Events
											</h3>
											<p>
											<%if(latestActivity==null){ %>本月剩余时间无活动<%}else{ %><a href="activity/info.action?activityId=<%=latestActivity.getActivityId() %>"><%=latestActivity.getActivityStartTime() %>&nbsp<%=latestActivity.getActivityName() %> <%} %></a>
												
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--//end .event_box-->
						<div class="calender">
							<!--start calender-->
							<div class="indicator"></div>
							<div class="prev_home">
								<a href="#"></a>
							</div>
							<ul>
								<li>
									<a href="#">02</a>
								</li>
								<li>
									<a href="#">03</a>
								</li>
								<li>
									<a href="#">04 </a>
								</li>
								<li>
									<a href="#">05</a>
								</li>
								<li>
									<a href="#">06 </a>
								</li>
								<li>
									<a href="#">07 </a>
								</li>
								<li>
									<a href="#">08</a>
								</li>
								<li>
									<a href="#">09</a>
								</li>
								<li>
									<a href="#">10</a>
								</li>
								<li>
									<a href="#">11</a>
								</li>
								<li>
									<a href="#">12</a>
								</li>
								<li>
									<a href="#">13</a>
								</li>
								<li>
									<a href="#">14 </a>
								</li>
								<li>
									<a href="#">15</a>
								</li>
								<li>
									<a href="#">16 </a>
								</li>
								<li>
									<a href="#">17</a>
								</li>
								<li>
									<a href="#">18</a>
								</li>
								<li class="current_page_item">
									<a href="#">19 </a>
								</li>
								<li>
									<a href="#">20 </a>
								</li>
								<li>
									<a href="#">21</a>
								</li>
								<li>
									<a href="#">22 </a>
								</li>
								<li>
									<a href="#">23 </a>
								</li>
								<li>
									<a href="#">24 </a>
								</li>
								<li>
									<a href="#">25 </a>
								</li>
								<li>
									<a href="#">26 </a>
								</li>
								<li>
									<a href="#">27 </a>
								</li>
								<li>
									<a href="#">28 </a>
								</li>
								<li>
									<a href="#">29 </a>
								</li>
								<li>
									<a href="#">30</a>
								</li>
								<li>
									<a href="#">31 </a>
								</li>
							</ul>
							<div class="month">
								<span>MARCH 01</span>
							</div>
							<div class="next_home">
								<a href="#"></a>
							</div>
						</div>
						<!--//end .calender-->
						<div class="ranger">
							<img src="../tribus/view/activity/img/bgr_ranger.png" alt="" />
						</div>
						<div class="ranger_icon">
							<a href="activity/activityCreatInit.action"><img src="../tribus/view/activity/img/icon_ranger.png" alt="" />
							</a>
						</div>
						<div class="event_btn">
							<a href="activity/activityCreatInit.action"><span><span>Create your Event</span>
							</span>
							</a>
						</div>
						<div id="common_content_area">
							<!--start common_content_area-->
							<%if(session.getAttribute("user")==null) {%>

							<c:forEach items="${commentListRandom}" var="item"
								varStatus="i">

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
															href="activity/info.action?activityId=${item.activity.activityId }"><img src="${item.activity.activityPic}" alt="" width=137 height=187/>
														</a>
														<div class="feature_info">
															<div class="feature_txt">
																<p>
																	<a
																		href="activity/info.action?activityId=${item.activity.activityId}">${item.activity.activityName}</a>
																	<br />
																	${item.activity.activityCity}&nbsp ${item.activity.activityStreet}
																	<br />
																	${item.activity.activityState }&nbsp
																	<br />
																	Fee: $ ${item.activity.activityFees } each
																</p>
															</div>
															<div class="feature_social">
																<div class="feature_icon">
																	<a href="#"><img src="../tribus/view/activity/img/icon_feature1.png" alt="" />
																	</a>
																	<a href="#"><img src="../tribus/view/activity/img/icon_feature2.png" alt="" />
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
																	<span><a href="user/my/${item.userId}"><img src="${item.userPic}" alt="" width=41 height=39/>
																	</span><small>: ${item.userComment }</small>
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
															href="activity/info.action?activityId=${item.activity.activityId }"><img src="${item.activity.activityPic}" alt="" width="137" height="187"/>
														</a>
														<div class="feature_info">
															<div class="feature_txt">
																<p>
																	<a
																		href="activity/info.action?activityId=${item.activity.activityId}">${item.activity.activityName}</a>
																	<br />
																	${item.activity.activityCity}&nbsp ${item.activityStreet}
																	<br />
																	${item.activity.activityState }&nbsp
																	<br />
																	Fee: $ ${item.activity.activityFees } each
																</p>
															</div>
															<div class="feature_social">
																<div class="feature_icon">
																	<a href="#"><img src="../tribus/view/activity/img/icon_feature1.png" alt="" />
																	</a>
																	<a href="#"><img src="../tribus/view/activity/img/icon_feature2.png" alt="" />
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
																	<span><a href="user/my/${item.userId}"><img src="${item.userPic}" alt="" />
																	</span><small>: ${item.userComment }</small>
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
                        	<a href="activity/info.action?activityId=${item.activityId }"><img src="${item.picPath}" width="65" height="96" /></a>
                            <div class="bar_info">
                            	<h3><a href="activity/info.action?activityId=${item.activityId }">${item.activityName }</h3>
                                <span>Host: ${item.userName }</span>
                                <p>Open:${item.activityStartTime }<br /></p>
                                <div class="join"><small>${item.numbers } (people)</small>      <a>Join in</a> </div>
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
										href="activity/searchByTag.action?tagName=<%=activityTagsList.get(0).getClassifiedName() %>"><%=activityTagsList.get(0).getClassifiedName() %></a>/
									<a
										href="activity/searchByTag.action?tagName=<%=activityTagsList.get(1).getClassifiedName()%>"><%=activityTagsList.get(1).getClassifiedName()%></a>
									<br />
									<a
										href="activity/searchByTag.action?tagName=<%=activityTagsList.get(2).getClassifiedName()%>"><%=activityTagsList.get(2).getClassifiedName()%></a>/
									<a
										href="activity/searchByTag.action?tagName=<%=activityTagsList.get(3).getClassifiedName() %>"><%=activityTagsList.get(3).getClassifiedName() %></a>
									<br />
									<a
										href="activity/searchByTag.action?tagName=<%=activityTagsList.get(4).getClassifiedName() %>"><%=activityTagsList.get(4).getClassifiedName() %></a>/
									<a
										href="activity/searchByTag.action?tagName=<%=activityTagsList.get(5).getClassifiedName() %>"><%=activityTagsList.get(5).getClassifiedName() %></a>
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
											<a href="activity/byCityTag.action?city=${item}">${item}</a>
										</li>
									</c:forEach>
								</ul>
								<small><a href="#">Add</a> / <a href="#">Change</a>
									Locations</small>
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
			</div>
			<!--//end #main_area-->
		</div>
		<!--//end #wrapper-->
	</body>
</html>