<%@include file="/apps/mycqproject/common/global.jsp" %>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <cq:include script="headlibs.jsp"/>
    <cq:include script="/libs/wcm/core/components/init/init.jsp"/>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">

    <title>${not empty currentPage.title ? currentPage.title : currentPage.pageTitle}</title>
    <cq:includeClientLib categories="mycqproject.base"/>
    <style>
        .container {
            width: 1040px;
        }
    </style>


</head>
