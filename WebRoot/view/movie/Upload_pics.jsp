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
	<title>Tribus_Upload_pics</title>
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
                    	<span class="space_btm"><a href="#"><img src="${my_local }/movie/img/icon_header1.png" alt="" width="10" height="11" /></a></span>
                        <span><a href="${my_domain }/user/editForm.action"><img src="${my_local }/movie/img/icon_header2.png" alt="" width="12" height="13" /></a></span>
                    </div>
                </div></div></div>
            </div><!--//end #header_rgt-->
        </div><!--//end #header-->
        <div id="main_area"><!--start main_area-->
        	<div id="saerch_area"><!--start saerch_area-->
                     	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${my_domain }/movie/search/1.action" id="single_search_bar" name="single_search_bar">
                    	<p class="search_text"><input name="single_search_name" type="text" value="Seach movie, actors, comment, tribus music list" onclick="if(this.value=='Seach movie, actors, comment, tribus music list')(this.value='')"  onblur="if(this.value=='')(this.value='Seach movie, actors, comment, tribus music list')" /></p>
                        <p class="search_submit"><input type="submit" value=" "/></p>
                    </form>
                </div><!--//end #search_bg-->
                <div id="social_media"><!--start social_media-->
                	<div id="social_lftcol">
                    	<a href="#"><img src="${my_local }/movie/img/icon_facebook.jpg" alt="" width="24" height="24" /></a>
                        <a href="#"><img src="${my_local }/movie/img/icon_tweet.jpg" width="24" height="24" alt="" /></a>
                    </div>
                    <div id="social_box"><!--start social_box-->
                    	<div id="message">
                        	<a href="${my_domain }/userMail/box/0.action"><img src="${my_local }/movie/img/icon_message1.jpg" alt="" width="22" height="22" /><c:if test="${unreadMail > 0}"><span><c:out value="${unreadMail}"/></span></c:if></a>
                            <a href="${my_local }/user/police.action"><img src="${my_local }/movie/img/icon_message2.jpg" alt="" width="22" height="22" /></a>
                            <a href="${my_local }/user/about.action"><img src="${my_local }/movie/img/icon_message3.jpg" alt="" width="22" height="22" /></a>
                            <a href="${my_local }/user/logout.action"><img src="${my_local }/movie/img/icon_message4.jpg" alt="" width="22" height="22" /></a>
                        </div>
                        <div class="address">
                        	<h3><c:out value="${userName}" /></h3>
                            <span><c:out value="${userCity }" /></span>
                        </div>
                    </div><!--//end #social_box-->
                </div><!--//end #social_media-->
            </div><!--//end #saerch_area-->
            <div id="common_maincontent"><!--start common_maincontent-->
            	<div id="edit_album_content"><!--start edit_album_content-->
                	<div class="edit_album_widget"><!--start edit_album_widget-->
                    	<h2>Upload Pictures</h2>
                    	<div class="frame">
                    		<div id="div_pic_1"></div>
                        	<div class="icon_edit"><img src="${my_local}/movie/img/icon_edit.jpg" alt="" width="19" height="19" /></div>
                        </div>
                                    <form method="post" name="form" id="form_upload_1" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_1').submit();"   />
                        				</p>
                        				<input type="hidden" value="1" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para1" name="hidden_para1" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        			</form>

                        <p class="edit_message">
                        	<textarea id="edit_message_1" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')">Introduction</textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                    <div class="edit_album_widget"><!--start edit_album_widget-->
                    	<div class="frame">
                    		<div id="div_pic_2"></div>
                        	<div class="icon_edit"><img src="${my_local}/movie/img/icon_edit.jpg" alt="" width="19" height="19" /></div>
                        </div>
                                    <form method="post" name="form" id="form_upload_2" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_2').submit();"   />
                        				</p>
                        				<input type="hidden" value="2" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para2" name="hidden_para2" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        			</form>
                        <p class="edit_message">
                        	<textarea id="edit_message_2" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')">Introduction</textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                    <div class="edit_album_widget padding_rgt_null"><!--start edit_album_widget-->
                    	<div class="frame">
                    		<div id="div_pic_3"></div>
                        	<div class="icon_edit"><img src="${my_local}/movie/img/icon_edit.jpg" alt="" width="19" height="19" /></div>
                        </div>
                                    <form method="post" name="form" id="form_upload_3" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_3').submit();"   />
                        				</p>
                        				<input type="hidden" value="3" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para3" name="hidden_para3" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        			</form>
                        <p class="edit_message">
                        	<textarea id="edit_message_3" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')">Introduction</textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                    <div class="clear"></div>
                    <div class="edit_album_widget"><!--start edit_album_widget-->
                    	<div class="frame">
                    		<div id="div_pic_4"></div>
                        	<div class="icon_edit"><img src="${my_local}/movie/img/icon_edit.jpg" alt="" width="19" height="19" /></div>
                        </div>
                                    <form method="post" name="form" id="form_upload_4" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_4').submit();"   />
                        				</p>
                        				<input type="hidden" value="4" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para4" name="hidden_para4" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        			</form>
                        <p class="edit_message">
                        	<textarea id="edit_message_4" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')">Introduction</textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                    <div class="edit_album_widget"><!--start edit_album_widget-->
                    	<div class="frame">
                    		<div id="div_pic_5"></div>
                        	<div class="icon_edit"><img src="${my_local}/movie/img/icon_edit.jpg" alt="" width="19" height="19" /></div>
                        </div>
                                    <form method="post" name="form" id="form_upload_5" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_5').submit();"   />
                        				</p>
                        				<input type="hidden" value="5" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para5" name="hidden_para5" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        			</form>
                        <p class="edit_message">
                        	<textarea id="edit_message_5" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')">Introduction</textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                    <div class="edit_album_widget padding_rgt_null"><!--start edit_album_widget-->
                    	<div class="frame">
                    		<div id="div_pic_6"></div>
                        	<div class="icon_edit"><img src="${my_local}/movie/img/icon_edit.jpg" alt="" width="19" height="19" /></div>
                        </div>
                                    <form method="post" name="form" id="form_upload_6" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_6').submit();"   />
                        				</p>
                        				<input type="hidden" value="6" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para6" name="hidden_para6" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        			</form>
                        <p class="edit_message">
                        	<textarea id="edit_message_6" rows="10" cols="10" onclick="if(this.value=='Introduction')(this.value='')" onblur="if(this.value=='')(this.value='Introduction')">Introduction</textarea>
                        </p>
                    </div><!--//end .edit_album_widget-->
                    <div class="clear"></div>
                    
                    <form action="" id="pics_upload_form">
                    	<input type="hidden" value="" id="successful_img_path_1" name="successful_img_path_1" />
                    	<input type="hidden" value="" id="successful_img_path_2" name="successful_img_path_2" />
                    	<input type="hidden" value="" id="successful_img_path_3" name="successful_img_path_3" />
                    	<input type="hidden" value="" id="successful_img_path_4" name="successful_img_path_4" />
                    	<input type="hidden" value="" id="successful_img_path_5" name="successful_img_path_5" />
                    	<input type="hidden" value="" id="successful_img_path_6" name="successful_img_path_6" />
                    	
                    	<input type="hidden" value="" id="successful_img_decs_1" name="successful_img_decs_1" />
                    	<input type="hidden" value="" id="successful_img_decs_2" name="successful_img_decs_2" />
                    	<input type="hidden" value="" id="successful_img_decs_3" name="successful_img_decs_3" />
                    	<input type="hidden" value="" id="successful_img_decs_4" name="successful_img_decs_4" />
                    	<input type="hidden" value="" id="successful_img_decs_5" name="successful_img_decs_5" />
                    	<input type="hidden" value="" id="successful_img_decs_6" name="successful_img_decs_6" />
                    	
                    </form>
                    
                    <div class="save_edit"><a href="javascript: uploadPics('${my_domain }/movie/picsSubmit/${itemId}.action');">Upload</a></div>
                </div><!--//end #edit_album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="${my_local }/movie/js/smoothscroll.js" type="text/javascript"></script>
            <script src="${my_local }/movie/js/jquery.min.js" type="text/javascript"></script>
            <script type="text/javascript">
	            function uploadPics(path){
	            		
	            			$('#successful_img_path_1').val($('#hidden_para1').val());
	            			$('#successful_img_path_2').val($('#hidden_para2').val());
	            			$('#successful_img_path_3').val($('#hidden_para3').val());
	            			$('#successful_img_path_4').val($('#hidden_para4').val());
	            			$('#successful_img_path_5').val($('#hidden_para5').val());
	            			$('#successful_img_path_6').val($('#hidden_para6').val());
	            			$('#successful_img_decs_1').val($('#edit_message_1').val());
	            			$('#successful_img_decs_2').val($('#edit_message_2').val());
	            			$('#successful_img_decs_3').val($('#edit_message_3').val());
	            			$('#successful_img_decs_4').val($('#edit_message_4').val());
	            			$('#successful_img_decs_5').val($('#edit_message_5').val());
	            			$('#successful_img_decs_6').val($('#edit_message_6').val());
	                    	$('#pics_upload_form').attr("action", path);
	                    	//alert($('#comment_action_form').a);
	                    	$('#pics_upload_form').submit();
	            }
            </script>
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   