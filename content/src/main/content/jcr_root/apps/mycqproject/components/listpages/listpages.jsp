<%@include file="/apps/mycqproject/common/global.jsp" %>

<sling:listChildren
        resource="${sling:getResource(resourceResolver, not empty properties.pageroot ? properties.pageroot : '/content/mycqproject/en')}"
        var="children"/>
<ul class="horizontal-menu nlm">
    <c:forEach var="child" items="${children}" varStatus="status">
        <c:if test="${status.index != 0}">
            <li><a href="${child.path}.html">${child.name}</a></li>
        </c:if>
    </c:forEach>
</ul>