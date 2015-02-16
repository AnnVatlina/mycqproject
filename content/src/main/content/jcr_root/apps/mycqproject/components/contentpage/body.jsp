<%@include file="/libs/foundation/global.jsp"%>
<body>
<div id="CQ">
    <cq:include path="topnav" resourceType="mycqproject/components/topnav" />
    <div class="content">
        <cq:include script="left.jsp" />
        <cq:include script="center.jsp" />
        <cq:include script="right.jsp" />
    </div>
    <div class="footer">
        <cq:include path="toolbar" resourceType="foundation/components/toolbar"/>
    </div>
</div>
</body>