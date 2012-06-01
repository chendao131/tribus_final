<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %><%

%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>        
    <title>MovieHomePage</title>   	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
  </head>
  
  <body>
    <table width="973" align="center" id="movie_home_page">
      <tr>
      <td width="682"><table id="left">
      	<tr id="hot_movie"><td><div style="position:relative; z-index:50; top:42px; left:15px"><img src="images/flag.png" /></div><table>
          <tr id="hot_movie_mainContent">
          
          
          <c:forEach items="${atts}" var="item" varStatus="s"> 
         	                               
            <td><table width="145">
              <tr>
              <td width="137" height="137" align="right">
              
               <c:if    test="${s.index == 0 }">        		        		              
              <img src="<c:out value="${item.moviePic}"/>" width="111" height="110" />
            </c:if>  
              
              </td>
              </tr>
              <tr>
                <td align="right">
                
                <img src="images/movie-pic.png" width="110.045px" height="225px" />
                
              	<c:if    test="${s.index == 1 }">        		        		              
              <img src="<c:out value="${item.moviePic}"/>" width="111" height="110" />
            </c:if>  
                
                </td></tr>
            </table>
            </td>
            <td><table height="376">
           	  
           	  
           	  
           	  <c:if    test="${s.index > 1 && s.index < 6}">
           	  <tr>        		        		              
              <td height="139"><img src="<c:out value="${item.moviePic}"/>" width="111" height="110" /></td>
              </tr>
            </c:if>
            
                       	             	                               
              <c:if    test="${s.index > 5 && s.index < 10}">
           	  <tr>        		        		              
              <td height="139"><img src="<c:out value="${item.moviePic}"/>" width="111" height="110" /></td>
              </tr>
            </c:if>
              

			<c:if    test="${s.index > 10 && s.index < 6}">
           	  <tr>        		        		              
              <td height="139"><img src="<c:out value="${item.moviePic}"/>" width="111" height="110" /></td>
              </tr>
            </c:if>
            
            
            </table></td>
            
            
            </c:forEach>
            
            
          </tr>
        </table></td></tr>
        <tr><td><table width="58" align="right">
          <tr>
            <td width="22">
              <img src="images/more-3.png" />
            </td>
            <td width="33">
              <img src="images/more-2.png" />
            </td>
          </tr>
        </table></td></tr>
        
        <tr id="recommend"><td><table>
          <tr><td height="238"><div style="position:relative; z-index:50; top:27px; left:13px"><img src="images/flag.png" /></div><table id="friends_recommend">
          <tr>
          
          <c:forEach items="${Movielist}" var="small" varStatus="s">
            <td width="128" height="163"><div align="right">                        
            <img src="images/movie-pic (2).png" width="100" height="148" /></div>            
            </td>
            <td width="441"><table width="186" height="161">
              <tr><td width="178" height="24">Turn Me On</td></tr>
              <tr><td>
              		${small.movieName }
              </td></tr>
              <tr><td>${small.movieGrade}</td></tr>
              <tr><td>${small.movieTime}</td></tr>
              <tr>
                <td>
                  <div><img src="images/expand-button.png" width="19" height="19" /> <img src="images/Search-button.png" width="19" height="19" /></div>
                </td>  
              </tr>
            </table></td>
            </c:forEach>
            
            
          </tr>        	
        </table></td></tr>
        </table></td></tr>
        <tr id="comment">
        <td>
        <div style="position:relative; top:23px; left:18px;">
        <img src="images/flag.png" />
        </div>
        <table width="620" id="comment">
		  
		  
		  <c:forEach items="${MovieComments}" var="v" varStatus="index">
          <tr><td><div align="right">
            <table class="comment_box" width="581" height="131" bgcolor="#FFFFFF">
              <tr>
                <td width="82" align="center">
                <div>                                                                            
                <img src="${v.url}" alt="" width="66" height="98" /></div>                                
                </td>
                <td width="115" align="center">                                
                <div>
                <img src="${v.followUrl }" alt="" width="65" height="64" />
                </div>                
                </td>
                <td width="368"><table>
                  <tr><td width="139">${v.title }</td></tr>
                  <tr><td>${v.comments }</td></tr>                  
                  </table></td>
                </tr>
            </table>
          </div></td></tr>          
          </c:forEach>
          
          
         
        </table>
            <div align="right"></div></td></tr>
      </table></td>
      
      <td width="279" style="vertical-align:top;"><table width="228" height="1481" border="0" style="margin-top:0;">
        <tr>
            <td width="227" height="924"><table width="216" height="840" border="0">
              <tr>
                <td width="206" height="23"><img src="images/follow-Title-bar-1.png" width="210" height="25"></td>
              </tr>
              <tr>
                <td height="103"><div align="center"><img class="guess_you_like_pic" src="images/Guess-u-like-pic-note-that-the-title-will-overlap-with-the-pic.png" width="137" height="139"></div></td>
              </tr>
              <tr>
                <td><div align="center">Introduction1</div></td>
              </tr>
              <tr>
                <td height="98"><div align="center"><img class="guess_you_like_pic" src="images/Guess-u-like-pic-note-that-the-title-will-overlap-with-the-pic.png"></div></td>
              </tr>
              <tr>
                <td><div align="center">Introduction1</div></td>
              </tr>
              <tr>
                <td><div align="center"><img class="guess_you_like_pic"  src="images/Guess-u-like-pic-note-that-the-title-will-overlap-with-the-pic.png" alt=""></div></td>
              </tr>
              <tr>
                <td><div align="center">Introduction1</div></td>
              </tr>
              <tr>
                <td><div align="center"><img class="guess_you_like_pic"  src="images/Guess-u-like-pic-note-that-the-title-will-overlap-with-the-pic.png" alt="" width="137" height="139"></div></td>
              </tr>
              <tr>
                <td><div align="center">Introduction1</div></td>
              </tr>
              <tr>
                <td><div align="center"><img class="guess_you_like_pic"  src="images/Guess-u-like-pic-note-that-the-title-will-overlap-with-the-pic.png" alt="" width="137" height="139"></div></td>
              </tr>
              <tr>
                <td><div align="center">Introduction1</div></td>
              </tr>
              <tr>
                <td height="104"><div align="center"><img class="guess_you_like_pic" src="images/Guess-u-like-pic-note-that-the-title-will-overlap-with-the-pic.png" alt="" width="137" height="139"></div></td>
              </tr>
              <tr>
                <td height="36"><div align="center">Introduction1</div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="141" style="vertical-align:top;"><table width="200" border="0">
              <tr>
                <td><img src="images/follow-Title-bar-1.png" alt="" width="210" height="25"></td>
              </tr>
              <tr>
                <td><div align="center">tag1 / tag2</div></td>
              </tr>
              <tr>
                <td><div align="center">tag3 / tag4</div></td>
              </tr>
              <tr>
                <td height="23"><div align="center">tag5 / tag6</div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="75"><table width="200" border="0">
              <tr>
                <td><img src="images/follow-Title-bar-1.png" alt="" width="210" height="25"></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td><div align="center">List1</div></td>
              </tr>
              <tr>
                <td><div align="center">List1</div></td>
              </tr>
              <tr>
                <td><div align="center">List1</div></td>
              </tr>
              <tr>
                <td><div align="center">List1</div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="329">&nbsp;</td>
          </tr>
        </table>
      </td> <!-- right -->
      </tr>
    </table>  <!-- movie home page -->
  </body>
</html>
