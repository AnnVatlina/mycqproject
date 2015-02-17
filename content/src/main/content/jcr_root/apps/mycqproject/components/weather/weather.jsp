<%@ page import="com.axamit.training.mycqproject.models.WeatherModel" %>
<%@include file="/libs/foundation/global.jsp"%>
<div class="weather_block">

    <h3>${properties.titleWeatherComponent}</h3>

    <div class="content_area">
        ${properties.textWeatherComponent}
    </div>

    ${properties.cityWeatherComponents}
    ${properties.unitWeatherComponents}

    <%--${sling:adaptTo(resource, "com.axamit.training.mycqproject.models.WeatherModel")}--%>
    <%--<sling:adaptTo adaptable='${resource}' adaptTo='com.axamit.training.mycqproject.models.WeatherModel' var="model"/>--%>
    <%
        WeatherModel weatherModel = resource.adaptTo(WeatherModel.class);
    %>

</div>