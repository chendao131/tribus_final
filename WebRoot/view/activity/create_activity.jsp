<%@ page language="java" import="java.util.*,model.User" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user=(User)session.getAttribute("user");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'create_activity.jsp' starting page</title>
    
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
    <script type="text/javascript">
    	function check_sub(){
			document.getElementById("form1").submit();
    	}
    </script>
    <script src="view/activity/calendar.js" type="text/javascript"></script>
  </head>
  
  <body>
  	<div id="wrapper"><!--start wrapper-->
    	<div id="header"><!--start header-->
        	<div class="logo"><a href="index.html"><img src="../tribus/view/activity/img/logo.png" alt="" width="59" height="65" /></a></div>
            <div id="header_rgt"><!--start header_rgt-->
            	<div id="menu_bg"><div id="menu_lft"><div id="menu_rgt">
                    <ul>
                    	<li><a href="#">CITY</a></li>
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
                    	<span class="space_btm"><a href="#"><img src="../tribus/view/activity/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="#"><img src="../tribus/view/activity/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
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
                    	<a href="#"><img src="../tribus/view/activity/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="../tribus/view/activity/img/icon_tweet.jpg" alt="" width="24" height="24" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="#"><img src="../tribus/view/activity/img/icon_message1.jpg" alt="" width="22" height="22" /><span>5</span></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="#"><img src="../tribus/view/activity/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><%if(user!=null){ %><a href="user/my/<%=user.getUserId()%>">welcome back,<%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></h3>
                            <span>New York City</span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="album_content" class="create_list"><!--start album_content-->
                    
                    <div id="gallery"><!--start gallery-->
                    	<h2 class="create_title title_activity">Create An Activity</h2>
                        <div id="contact_form_create"><!--start contact_form_create-->
                        	<form id="form1" name="form1" method="post"
			action="activity/activityCreat.action">
                            	<div class="account_message">
                                	<div class="message_drop"></div>
                                	<div class="message_activity">
                                    	<span>Activity introduction:</span>
                                        <textarea name="activityDetail" id="activityDetail"></textarea>
                                    </div>
                                </div>
                                <div class="contact_field_widget">
                                	<p>&nbsp;</p>
                                	<label>Activity name :</label>
                                    <div class="text_field"><input type="text" name="activityName" id="activityName"/></div>
                              </div>
                                <div class="contact_field_widget">
                                	<label>Activity street: :</label>
                                    <div class="text_field"><input class="edit_info" type="text"  name="activityStreet" id="activityStreet"/></div>
                                </div>
                                <div class="contact_field_widget">
                                  <label>Activity city: :</label>
                                  <div class="text_field"><input class="edit_info" type="text" name="activityCity" id="activityCity"/></div>
                              </div>
                                <div class="contact_field_widget">
                                  <label>Activity state: :</label>
                                    <div class="text_field"><input type="text" name="activityState" id="activityState"/></div>
                              </div>
                                <div class="contact_field_widget">
                                	<label>Fees:</label>
                                    <div class="text_field"><input class="small_txt" type="text" name="activityFees" id="activityFees"/></div>
                                </div>
                                <div class="contact_field_widget">
                                	<label>Start time - End time：</label>
                                    <div class="text_field"><input class="small_txt" type="text" id="activitystartDate" name="activitystartDate"  onclick="SelectDate(this)" readonly=true/>
                                    <small>-</small>
                                    <input class="small_txt" type="text" id="activityfinishDate" name="activityfinishDate" onclick="SelectDate(this)" readonly=true/></div>
                                </div>
                              <div class="contact_field_widget"></div>
                                <div class="contact_field_widget">
                                	<label>Activity picture</label>
                                </div>
                                <div class="contact_field_widget">
                                    <div class="text_field">
                                      <div class="frame" id="container_2"> 
                                          <div class="icon_edit"></div>
                                      </div>
                                      <br/>
                                      <br/>
                                      <input type="hidden" name="hidden_para2" id="hidden_para2" value="" />
                                      </form>
                                   <form method="post" name="form12" id="form_upload2"
				action="http://localhost:8080/tribus/uploadForm/uploadAction.action"
				enctype="multipart/form-data" target="hidden_frame_2">

				
				<!-- input type="submit" value="submit" -->
				<span id="msg"></span>
				<div id="div_pic"></div>
				<iframe name='hidden_frame_2' id="hidden_frame_2" style='display: none'></iframe>
			
                        <p class="edit_txt">
                        <input type="file" name="myfile"   onchange="$('#form_upload2').submit();"   /></p>
                        <input type="hidden" value="2" name="div_container_number" />
                        
                        		</form>
                                      
                                      
                                      
                                      
                                      
                                    </div>
                                </div>
                                <div class="btn_contact_field">
                               	<a href="javascript: check_sub();">Save</a></div>                               		
                            
                        </div><!--//end #contact_form_create-->
                    </div><!--//end #gallery-->
                </div><!--//end #album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p>Copyright @ Tribus.us 2012.      <span>All rights reserved</span> </p></div>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
  </body>
</html>
