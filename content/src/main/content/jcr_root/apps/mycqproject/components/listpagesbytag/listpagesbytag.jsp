<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>

<sling:adaptTo var="searchResult" adaptable="${resource}"
               adaptTo="com.axamit.training.mycqproject.components.searchbytags.model.SearchModel"/>

<ul>
<c:forEach var="element" items="${searchResult.searchResultModels}" varStatus="status">
    <li>#${status.index}. <a href="${element.path}.html">${element.title}</a></li>
</c:forEach>
</ul>