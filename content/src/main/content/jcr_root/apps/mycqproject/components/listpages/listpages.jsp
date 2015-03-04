<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>

<sling:listChildren
        resource="${sling:getResource(resourceResolver, not empty properties.pageroot ? properties.pageroot : '/content/mycqproject/en')}"
        var="children"/>
<ul class="horizontal-menu nlm">
    <c:forEach var="child" items="${children}" varStatus="status">
        <c:if test="${child.name != 'jcr:content'}">
            <li><a href="${child.path}.html">${child.name}</a></li>
        </c:if>
    </c:forEach>
</ul>