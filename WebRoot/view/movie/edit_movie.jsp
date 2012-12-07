<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt-rt.tld" %><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
	<title>Edit Movie</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/font/font.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/style.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local }/movie/css/css3.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/movie/css/jquery.ui.all.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/movie/css/demos.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/movie/css/jquery.base.css" />
    <link rel="stylesheet" type="text/css" media="screen,projection" href="${my_local}/movie/css/jquery.theme.css" />
    <!--[if lt IE 10]>
   		<script src="js/PIE.js" type="text/javascript"></script>
    <![endif]-->

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
                        <li><a id="bb" onMouseOver="get()" style="display:block" href="${my_domain}/user/my.action" title="MY TRIBUS">MY TRIBUS</a>
						<a id="aa" onMouseOut="bu()" style="font-size:24px; display:none"  href="${my_domain}/user/my.action" title="MY TRIBUS">MyTRIBUS</a></li>
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
            	<div id="search_bg" class="space_lft"><!--start search_bg-->
                	<form action="${my_domain }/movie/search/1.action" id="single_search_bar" name="single_search_bar">
                    	<p class="search_text"><input name="single_search_name" type="text" value="Seach for movie, celebrity, tribus list" onclick="if(this.value=='Seach for movie, celebrity, tribus list')(this.value='')"  onblur="if(this.value=='')(this.value='Seach for movie, celebrity, tribus list')" /></p>
                        <p class="search_submit"><input type="submit" value=" "/></p>
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
            	<div id="album_content" class="create_list"><!--start album_content-->
                    <div id="gallery"><!--start gallery-->
                        <div id="persoanal_form"><!--start persoanal_form-->
                        		<div id="div_pic_1"></div>
                        		<div class="persoanal_inp_field">
                                	<label>Poster:</label>
                                    <form method="post" name="form" id="form_upload_1" action="${my_domain}/uploadForm/uploadAction.action" enctype="multipart/form-data" target="hidden_frame">				
										<span id="msg"></span>
										
										<iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>			
                        				<p class="edit_txt">
                        					<input type="file" name="myfile" onchange="$('#form_upload_1').submit();"   />
                        				</p>
                        			    <input type="hidden" value="1" class="div_container_number" name="div_container_number" />
                        				<input type="hidden" value="none" id="hidden_para1" name="hidden_para1" />
                        				<input type="hidden" value="file_url" id="file_url" name="file_url" />
                        				<input type="hidden" value="file_url" id="file_url_big" name="file_url_big" />
                        				<input type="hidden" value="file_url" id="file_url_middle" name="file_url_middle" />
                        				<input type="hidden" value="file_url" id="file_url_small" name="file_url_small" />
                        			</form>
                                </div>
                        	<form action="" id="movie_edit_form">
                        	<input type="hidden" value="${movie.moviePic }" id="successful_img_path" name="successful_img_path" />
                        	<input type="hidden" value="" id="successful_img_path_big" name="successful_img_path_big" />
                        	<input type="hidden" value="" id="successful_img_path_middle" name="successful_img_path_middle" />
                        	<input type="hidden" value="" id="successful_img_path_small" name="successful_img_path_small" />
                            	<div class="persoanal_inp_field">
                                	<label>Movie Name:</label>
                                    <p><input type="text" value="${movie.movieNameOriginal }" name="movieNameOriginal"/></p>
                                </div>
                                <div class="persoanal_inp_field">
                                	<label>Director:</label>
                                    <p><input type="text" value="${movieDirectors }" name="movieDirector"/></p>
                                </div>
                                <div class="persoanal_inp_field">
                                	<label>Actor:</label>
                                    <p><input type="text" value="${movieActors }" name="movieActor"/></p>
                                </div>
                                <div class="persoanal_inp_field">
                                	<label>Region:</label>
                                    <p><input type="text" value="${movie.movieRegion}" name="movieRegion"/></p>
                                </div>
                                <div class="persoanal_inp_field">
                                	<label>Release Date:</label>
                                    <!--<p><input type="text" value="<fmt:formatDate value="${movie.movieDate}" type="date" pattern="dd/MM/yyyy" />" id="movieDate" name="movieDate"/></p>
                                	--><p><input type="text" value="${movie.movieDate}" id="movieDate" name="movieDate"/></p>
                                </div>
                                
                                <div class="persoanal_inp_field">
                                	<label>Rating:</label>
                                    <p><input type="text" value="${movie.movieRating}" name="movieRating"/></p>
                                </div>
								<div class="persoanal_inp_field">
                                	<label>Category:</label>
                                	<select name="movieTag" > 
								      <option value="action">action</option> 
								      <option value="adventure">adventure</option> 
								      <option value="animation">animation</option> 
								      <option value="war">war</option> 
								      <option value="comedy">comedy</option> 
								      <option value="horror">horror</option> 
								      <option value="mystery">mystery</option> 
								      <option value="romance">romance</option> 
								      <option value="thriller">thriller</option> 
								      <option value="thriller">others</option>
								      </select> 
                                </div>  
                                	<label>Brief Introduction:</label>
                                    <p><textarea rows="15" cols="80" name="movieBrief"><c:out value = "${movie.movieBrief }" /></textarea></p>
                                
                                <div class="btn_save"><a href="javascript: saveMovieEdition('${my_domain }/movie/saveMovieEdition/${movie.movieId }.action');">Save</a></div>
                            </form>
                        </div><!--//end #persoanal_form-->
                    </div><!--//end #gallery-->
                </div><!--//end #album_content-->
            </div><!--//end #common_maincontent-->
            <div id="footer"><p> &copy;2012 goTribus |<span>All rights reserved</span> </p></div>
            <div id="back_to_top"><a href="#top"></a></div>
            <script src="${my_local }/movie/js/smoothscroll.js" type="text/javascript"></script>
            <script src="${my_local }/movie/js/jquery.min.js" type="text/javascript"></script>
            <script src="${my_local }/movie/js/jquery.ui.core.js" type="text/javascript"></script>
			<script src="${my_local }/movie/js/jquery.ui.widget.js" type="text/javascript"></script>
			<script src="${my_local }/movie/js/jquery.ui.datepicker.js" type="text/javascript"></script>
			
            <script type="text/javascript">
	            function saveMovieEdition(path){
	            	        $('#successful_img_path_big').val($('#file_url_big').val());
	            			$('#successful_img_path_middle').val($('#file_url_middle').val());
	            			$('#successful_img_path_small').val($('#file_url_small').val());
	            			//$('#successful_img_path').val($('#file_url').val());
	                    	$('#movie_edit_form').attr("action", path);
	                    	//alert($('#comment_action_form').a);
	                    	$('#movie_edit_form').submit();
	            }
            </script>
           	<script type="text/javascript">
				$(function() {
					$( '#movieDate' ).datepicker();
					$( '#movieDate').datepicker( "option", "dateFormat", "yy-mm-dd");
				});
			  function get(){
				document.getElementById("aa").style.display="block";
				document.getElementById("bb").style.display="none";
				}
				function bu(){
				document.getElementById("bb").style.display="block";
				document.getElementById("aa").style.display="none";
				}					
            </script>
            
        </div><!--//end #main_area-->
    </div><!--//end #wrapper-->
</body>
</html>                                                                                                   