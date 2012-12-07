<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%


%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns:ice="http://ns.adobe.com/incontextediting">
<head>
        
    <title>
    	Star 
    </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/styles.css">


  <script src="includes/ice/ice.js" type="text/javascript"></script>
</head>
  
  <body>
  	<table width="462" height="474" border="1">
  <tr>
    <td><table width="395" height="1594" border="1">
      <tr>
        <td><table width="363" height="48" border="1">
          <tr>
            <td width="156"><table width="141" height="268" border="1">
              <tr>
              
              
                <td height="38"><h2>
                
                
                ${star.starName}
                
                
                </h2></td>
                
                
              </tr>
              <tr>
                <td><img src="${star.starUrl }" width="146" height="226"></td>
              </tr>
            </table></td>
            <td width="181"><table width="181" border="1">
              <tr>
                <td height="47">&nbsp;</td>
              </tr>
              <tr>
                <td><h4>Sex: 
                	
                	${star.starGender }
                
                </h4></td>
              </tr>
              <tr>
                <td><h4>Birth place: 
                
                ${star.starBirthplace }
                
                </h4></td>
              </tr>
              <tr>
                <td><h4>IMDB number: 
                
                ${star.starIMDB }
                
                </h4></td>
              </tr>
              <tr>
                <td height="58">&nbsp;</td>
              </tr>
              <tr>
                <td height="23"><h4>Edit Profile</h4></td>
              </tr>
              <tr>
                <td height="23"><h4>CHange pic</h4></td>
              </tr>
              <tr>
                <td height="38"><img src="images/ft.png" width="54" height="28"></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="297">
          <div style="position:relative; top:23px; right:12px;"><img src="images/flag.png" width="176" height="54"></div>
          <table width="534">
            <tr>
              <td width="473"><table width="426" height="161" border="1">
          <tr>
            <td width="416">&nbsp;</td>
          </tr>
          
          <tr>
            <td><div align="center">
            
            ${star.starBrief }
            
            </div></td>
          </tr>
          
          <tr>
            <td>&nbsp;</td>
          </tr>
        </table></td>
              <td width="49"><table><tr><td></td></tr></table>
                <table width="27" height="154" border="1">
                  <tr>
                    <td width="105" height="114">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><img src="images/expand-button.png" width="19" height="19"></td>
                  </tr>
                </table></td>
            </tr>
          </table>  
        </td>
      </tr>
      <tr>
        <td><div style="position:relative; top:23px; right:12px;"><img src="images/flag.png" width="176" height="54"></div>
          <table width="534">
            <tr>
              <td width="473"><table width="426" height="161" border="1">
                <tr>
                  <td width="416">&nbsp;</td>
                </tr>
                
                <tr>
                  <td><div align="center">
                  
                  ${star.starReward }
                  
                  </div></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                </tr>
              </table></td>
              <td width="49"><table>
                <tr>
                  <td>s</td>
                </tr>
              </table>
                <table width="27" height="154" border="1">
                  <tr>
                    <td width="105" height="114">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><img src="images/expand-button.png" alt="" width="19" height="19"></td>
                  </tr>
                </table></td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td height="223"><div style="position:relative; top:55px; right:12px;"><img src="images/flag.png" width="176" height="54"></div><table width="385" border="1">
          <tr>
            <td><table width="616" border="1">
              <tr>
                <td width="501">&nbsp;</td>
                <td width="39"><div align="right"><img src="images/more-3.png" width="18" height="18"></div></td>
                <td width="52"><div align="right"><img src="images/more-2.png" width="18" height="18"></div></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="200" border="1">
              <tr>
              
              
              <c:forEach items="${starAlbumlist}" var="l" varStatus="s">              
              <c:if test="${s <= 4}">
                <td><img src="${l.url }" width="126" height="89"></td>                               
                </c:if>                
                </c:forEach>
                
                
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="229"><div style="position:relative; top:23px; right:12px;"><img src="images/flag.png" width="176" height="54"></div><table width="580" border="1">
          <tr>
          
          
          <c:forEach items="${starAlbumlist2}" var="l" varStatus="s">              
              <c:if test="${s <= 4}">
                <td><img src="${l.url }" width="80" height="119"></td>                               
                </c:if>                
                </c:forEach>
          
            
            
                       
          </tr>
        </table></td>
      </tr>      
      <tr>
        <td height="96"><table width="260" border="1">
          <tr>
            <td width="207"><img src="images/Secondary-column-title-box.png" width="181" height="25"></td>
            <td width="37">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
    <td>&nbsp;</td>
  </tr>
</table>

</body>
</html>