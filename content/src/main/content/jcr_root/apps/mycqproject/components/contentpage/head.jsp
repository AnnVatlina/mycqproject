<%@include file="/apps/mycqproject/common/global.jsp" %>
<%@page session="false" contentType="text/html; charset=utf-8" %>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width" />

    <title>${not empty currentPage.title ? currentPage.title : currentPage.pageTitle}</title>
    <style>
        .container {
            width: 1040px;
        }
    </style>

    <cq:include script="headlibs.jsp"/>
    <cq:include script="/libs/wcm/core/components/init/init.jsp"/>

</head>
