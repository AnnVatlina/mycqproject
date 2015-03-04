<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>

<sling:listChildren resource="${sling:getResource(resourceResolver, currentPage.path)}" var="pageChildrens"/>
<sling:listChildren resource="${sling:getResource(resourceResolver, currentPage.path)}" var="forLenght"/>

<nav class="vertical-menu">
    <ul>
        <c:choose>
            <c:when test="${fn:length(forLenght) != 1}">
                <c:forEach var="pageChildren" items="${pageChildrens}">
                    <c:if test="${pageChildren.name != 'jcr:content'}">
                        <li><a href="${pageChildren.path}.html">${pageChildren.name}</a></li>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <li>No sub page</li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>