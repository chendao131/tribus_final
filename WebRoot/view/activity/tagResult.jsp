<%@ page language="java" import="java.util.*,model.User,model.Activity,vo.UserCommentSupper,model.ActivityClassified" pageEncoding="utf-8"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		List<vo.UserCommentSupper> activityList= (List<vo.UserCommentSupper>)request.getAttribute("activityList");
		List<ActivityClassified> activityTagsList= (List<ActivityClassified>)request.getAttribute("activityTagsList");
		List<String> topTribusCity= (List<String>)request.getAttribute("topTribusCity");
		User user=(User)session.getAttribute("user");
		String tagName=(String) request.getAttribute("tagName");
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tagResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
<!--
.STYLE1 {
	color: #FFFFFF;
	font-weight: bold;
}
.STYLE2 {color: #FFFFFF}
-->
</style>
<script type="text/javascript">
	function search(){
		var a =document.getElementById("txt1");
		window.location.href="activity/search.action?searchCondition="+a.value;
	}
</script>
  </head>
  
  <body>
  <div align="center">
  <table width="1006" height="63" border="0">
    <tr>
      <td><img src="../tribus/view/image/a.JPG" width="1026" height="63" border="0" usemap="#Map" /></td>
    </tr>
  </table>
  <map name="Map" id="Map">
    <area shape="rect" coords="84,19,141,39" href="activity/index.action" />
    <area shape="rect" coords="187,20,250,38" href="movie/homepage.action" />
    <area shape="rect" coords="298,19,358,36" href="book/homepage.action" />
    <area shape="rect" coords="389,19,454,41" href="music/homepage.action" />
    <area shape="rect" coords="490,23,595,41" href="user/home.action" />
  </map>
</div>
 <table width="866" height="218" border="0" align="center">
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="620" height="39"><table width="585" height="35" border="0" bgcolor="#DBDBDB">
      <tr>
        <td width="523" height="33"><form id="form1" name="form1" method="post" action="">
            <div align="center">
              <input type="text" id="txt1" name="textfield" />
            </div>
        </form></td>
        <td width="52"><div align="center"><img src="../tribus/view/image/Search-button.png" width="24" height="24" onclick="search()" /></div></td>
      </tr>
    </table></td>
    <td width="236"><table width="231" height="35" border="0" align="center" bgcolor="#EAEAEA">
      <tr>
        <td width="27"><div align="center"><img src="../tribus/view/image/mail.png" width="21" height="17" /></div></td>
        <td width="27"><div align="center"><img src="../tribus/view/image/notification.png" width="22" height="22" /></div></td>
        <td width="27"><div align="center"><img src="../tribus/view/image/about.png" width="22" height="22" /></div></td>
        <td width="27"><div align="center"><img src="../tribus/view/image/setting.png" width="19" height="19" /></div></td>
        <td width="97" colspan="2"><div align="center"><%if(user!=null){ %><a href="user/my/<%=user.getUserId()%>">welcome back,<%=user.getUserAlias()%></a><%}else{ %><a href="user/login.action">login</a><%} %></div></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="635" height="794" border="0">
       <%if(activityList.get(0)!=null){%>
       <tr>
      
        <td width="157" height="433"><table width="157" height="430" border="0" bgcolor="#CCCCCC">
          <tr>
            <td width="181" height="265"><div align="center"> <a href="activity/info.action?activityId=<%=activityList.get(0).getActivity().getActivityId()%>"><img src="<%=activityList.get(0).getActivity().getActivityPic() %>" width="136" height="278" /></a></div></td>
          </tr>
          <tr>
            <td><div align="center"> <a href="activity/info.action?activityId=<%=activityList.get(0).getActivity().getActivityId()%>"><%=(activityList.get(0).getActivity()).getActivityName() %></a></div></td>
          </tr>
          <tr>
            <td height="101"><div align="center">
              <p><%=activityList.get(0).getActivity().getActivityDetail() %> </p>
              <p align="right"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /> <img src="../tribus/view/image/expand-button.png" width="19" height="19" /></p>
            </div></td>
    
          </tr><%} %>
        </table></td>
        <td><table width="233" height="431" border="0">
           <%if(activityList.get(1)!=null){%>
          <tr>
          
            <td width="227" height="138"><p align="center"><a href="activity/info.action?activityId=<%=activityList.get(1).getActivity().getActivityId()%>"><%=activityList.get(1).getActivity().getActivityName() %></a></p>
              <p align="center"><%=activityList.get(1).getActivity().getActivityApt() %>,<%=activityList.get(1).getActivity().getActivityStreet()%></p>
              <p align="center"><%=activityList.get(1).getActivity().getActivityCity() %>,<%=activityList.get(1).getActivity().getActivityState() %>  </p></td>
          </tr>
          <tr>
            <td height="225"><div align="center"><a href="activity/info.action?activityId=<%=activityList.get(1).getActivity().getActivityId()%>"><img src="<%=activityList.get(1).getActivity().getActivityPic() %>" width="145" height="147" /></a></div></td>
          </tr>
          <tr>
            <td height="26"><div align="center">Fees:<%=activityList.get(1).getActivity().getActivityFees() %></div></td>
          </tr>
          <tr>
            <td height="32"><div align="right"><img src="../tribus/view/image/expand-button.png" width="19" height="19" />  <img src="../tribus/view/image/expand-button.png" width="19" height="19" /></div></td>
          </a>
          </tr>
        <%} %></table></td>
        <td>
        <table width="198" height="425" border="0" bgcolor="#CCCCCC">
          <%if(activityList.get(2)!=null){%>
          <tr>
          
            <td width="192" height="302"><div align="center">
              <p><a href="activity/info.action?activityId=<%=activityList.get(2).getActivity().getActivityId()%>"><img src="<%=activityList.get(2).getActivity().getActivityPic() %>" width="147" height="153" /></a></p>
              <p><%=activityList.get(2).getActivity().getActivityDetail() %></p>
            </div></td>
          </tr><%if(activityList.get(2).getUserComment().get(0)!=null){ %>
          <tr>
            <td height="34"><a href="user/my/<%=activityList.get(2).getUserComment().get(0).getUserId()%>"><img src="<%=activityList.get(2).getUserComment().get(0).getUserPic() %>" width="38" height="38" /></a>: <%=activityList.get(2).getUserComment().get(0).getCommentContent() %> </td>
          </tr><%}if(activityList.get(2).getUserComment().get(1)!=null){ %>
          <tr>
            <td height="29"><a href="user/my/<%=activityList.get(2).getUserComment().get(1).getUserId()%>"><img src="<%=activityList.get(2).getUserComment().get(1).getUserPic() %>" width="36" height="41" /></a>:<%=activityList.get(2).getUserComment().get(0).getCommentContent() %> </td>
         </a>
          </tr>
          <%} %>
          <tr>
            <td><div align="right"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /> <img src="../tribus/view/image/expand-button.png" width="19" height="19" /></div></td>
          </tr>
       <%} %> </table>
        </td>
      </tr>
      <tr>
        <td><table width="156" height="420" border="0">
          <%if(activityList.get(3)!=null){%>
          <tr>
          
            <td width="150" height="138"><p align="center"><a href="activity/info.action?activityId=<%=activityList.get(3).getActivity().getActivityId()%>"><%=activityList.get(3).getActivity().getActivityName()%></a></p>
                <p align="center"><%=activityList.get(3).getActivity().getActivityStreet() %></p>
              <p align="center"><%=activityList.get(3).getActivity().getActivityCity() %>,<%=activityList.get(0).getActivity().getActivityState() %></p></td>
          </tr>
          <tr>
            <td height="225"><div align="center"><a href="activity/info.action?activityId=<%=activityList.get(3).getActivity().getActivityId()%>"><img src="<%=activityList.get(3).getActivity().getActivityPic() %>" width="145" height="147" /></a></div></td>
          </tr>
          <tr>
            <td height="26"><div align="center">fees:<%=activityList.get(3).getActivity().getActivityFees() %></div></td>
          </tr>
          <tr>
            <td height="21"><div align="right"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /> <img src="../tribus/view/image/expand-button.png" width="19" height="19" /></div></td>
          </a>
          </tr>
          <%} %>
        </table></td>
        <td width="233"><table width="233" height="425" border="0" bgcolor="#CCCCCC">
           <%if(activityList.get(4)!=null){%>
          <tr>
         
            <td width="227" height="302"><div align="center">
                <p> <a href="activity/info.action?activityId=<%=activityList.get(4).getActivity().getActivityId()%>"><img src="<%=activityList.get(4).getActivity().getActivityPic() %>" width="147" height="153" /></a></p>
              <p><%=activityList.get(4).getActivity().getActivityDetail() %></p>
            </div></td>
          </tr><%if(activityList.get(4).getUserComment().get(0)!=null){ %>
          <tr>
            <td height="34"> <a href="user/my/<%=activityList.get(4).getUserComment().get(0).getUserId()%>"><img src="<%=activityList.get(4).getUserComment().get(0).getUserPic() %>" width="38" height="38" /></a>: <%=activityList.get(4).getUserComment().get(0).getCommentContent() %> </td>
          </tr><%}if(activityList.get(4).getUserComment().get(1)!=null) {%>
          <tr>
            <td height="29"><a href="user/my/<%=activityList.get(4).getUserComment().get(1).getUserId()%>"><img src="<%=activityList.get(4).getUserComment().get(1).getUserPic() %>" width="36" height="41" /></a>:<%=activityList.get(4).getUserComment().get(1).getCommentContent() %></td>
         </a>
          </tr>
          <% }%>
          <tr>
            <td><div align="right"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /> <img src="../tribus/view/image/expand-button.png" width="19" height="19" /></div></td>
          </tr>
          <%} %>
        </table></td>
        <td width="231"><table width="197" height="431" border="0">
         <%if(activityList.get(5)!=null){%>
          <tr>
            <td width="191" height="138"><p align="center"><a href="activity/info.action?activityId=<%=activityList.get(5).getActivity().getActivityId()%>"><%=activityList.get(5).getActivity().getActivityName() %></a></p>
                <p align="center"><%=activityList.get(5).getActivity().getActivityStreet() %></p>
              <p align="center"><%=activityList.get(5).getActivity().getActivityCity() %>,<%=activityList.get(5).getActivity().getActivityState() %> </p></td>
          </tr>
          <tr>
            <td height="225"><div align="center"><a href="activity/info.action?activityId=<%=activityList.get(5).getActivity().getActivityId()%>"><img src="<%=activityList.get(5).getActivity().getActivityPic() %>" width="145" height="147" /></a></div></td>
          </tr>
          <tr>
            <td height="26"><div align="center">fees:<%=activityList.get(5).getActivity().getActivityFees() %></div></td>
          </tr>
          <tr>
            <td height="32"><div align="right"><img src="../tribus/view/image/expand-button.png" width="19" height="19" /> <img src="../tribus/view/image/expand-button.png" width="19" height="19" /></div></td>
          </tr>
          <%} %>

 </table></td>

      </tr>
              <c:forEach items="${pageNumbers}" var="item">
              <a href="activity/searchByTag.action?page=${item}&tagName=${tagName}">${item}</a>&nbsp
              </c:forEach>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
    <td><table width="236" height="790" border="0">
      <tr>
        <td width="331" height="125"><table width="221" height="123" border="0">
          <tr>
            <td height="29" colspan="4" background="../tribus/view/image/Secondary-column-title-box.png"><span class="STYLE1"> Followers</span></td>
            </tr>
          <tr>
          <c:forEach items ="${followList}" var="forum" varStatus="s">
				
             <c:if test="${s.index%4==0 && s.index!=0}">     		
         			</tr> 
         			<tr>        
             </c:if>
                                
          	 <td width="38">          	
          	
          	 <c:if test="${forum.userId != null}">
          	 	<a href="user/my/${forum.userId}">
          	 		<img src="${forum.userPic}" width="38" height="38" />
          	 	</a>
          	 </c:if>  
          	   	 
          	 </td>
          </c:forEach>
        </tr>
        </table></td>
      </tr>
      <tr>
        <td height="86"><table width="223" border="0">
          <tr>
            <td width="224" height="30" background="../tribus/view/image/Secondary-column-title-box.png"><span class="STYLE1">Tags</span></td>
          </tr>
          <tr>
            <td height="47"><p><a href="activity/searchByTag.action?tagName=<%=activityTagsList.get(0).getClassifiedName() %>"><%=activityTagsList.get(0).getClassifiedName() %></a>/<a href="activity/searchByTag.action?tagName=<%=activityTagsList.get(1).getClassifiedName()%>"><%=activityTagsList.get(1).getClassifiedName()%></a>/<a href="activity/searchByTag.action?tagName=<%=activityTagsList.get(2).getClassifiedName()%>"><%=activityTagsList.get(2).getClassifiedName()%></a>/<a href="activity/searchByTag.action?tagName=<%=activityTagsList.get(3).getClassifiedName() %>"><%=activityTagsList.get(3).getClassifiedName() %></a>/</p>
              <p><a href="activity/searchByTag.action?tagName=<%=activityTagsList.get(4).getClassifiedName() %>"><%=activityTagsList.get(4).getClassifiedName() %></a>/<a href="activity/searchByTag.action?tagName=<%=activityTagsList.get(5).getClassifiedName() %>"><%=activityTagsList.get(5).getClassifiedName() %></a></p></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="136"><table width="223" height="134" border="0">
          <tr>
            <td width="224" height="28" background="../tribus/view/image/Secondary-column-title-box.png"><span class="STYLE1">Top Citys</span> </td>
          </tr>
          <tr>
            <td height="26"><strong><a href="activity/byCityTag.action?city=<%=topTribusCity.get(0) %>"><%=topTribusCity.get(0) %></a></strong></td>
          </tr>
          <tr>
            <td height="21"><strong><a href="activity/byCityTag.action?city=<%=topTribusCity.get(1) %>"><%=topTribusCity.get(1)%></a></strong></td>
          </tr>
          <tr>
            <td height="24"><strong><a href="activity/byCityTag.action?city=<%=topTribusCity.get(2) %>"><%=topTribusCity.get(2) %></a></strong></td>
          </tr>
          <tr>
            <td><strong><a href="activity/byCityTag.action?city=<%=topTribusCity.get(3) %>"><%=topTribusCity.get(3) %></a></strong></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="433"><table width="224" height="210" border="0">
          <tr>
            <td width="218" height="30" background="../tribus/view/image/Secondary-column-title-box.png"><span class="STYLE2">Guess You Like</span> </td>
          </tr>
         <c:forEach items="${recommendActivity}" var="item">
          <tr>
            <td><div align="center"><a href="${item.activityId}"><img src="${item.activityPic} " width="107" height="64" /></a></div></td>
          </tr>
</c:forEach>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

  </tr>
</table>
  </body>
</html>
