<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>
<body class="metro">

<div class="container">

    <cq:include script="../headerBody.jsp"/>
    <div class="grid">
        <div class="row">
            <div class="span8"><cq:include path="mainBox" resourceType="foundation/components/parsys"/></div>
            <div class="span4"><cq:include script="rightColumn.jsp"/></div>
        </div>
    </div>
    <cq:include script="../footer.jsp"/>

</div>
</body>