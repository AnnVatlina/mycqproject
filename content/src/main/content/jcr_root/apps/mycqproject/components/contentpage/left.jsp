<%@include file="/apps/mycqproject/common/global.jsp"%><%
%><%@ page import="com.day.text.Text"%><%
%><% String docroot = currentDesign.getPath(); 
String home = Text.getAbsoluteParent(currentPage.getPath(), 2);%><%
%><div class="left">
<%--<cq:include path="logo" resourceType="mycqproject/components/logo" />--%>
<cq:include path="newslist" resourceType="mycqproject/components/listchildren" />
<div class="form_1">
     <form class="geo" action="<%= home %>/toolbar/search.html" id="form" >
          <p>
               <input class="geo" type="text" name="q"><br> 
               <a href="<%= home %>/toolbar/search.html" class="link_1">advanced search</a> 
          </p>
     </form>
</div>
</div>