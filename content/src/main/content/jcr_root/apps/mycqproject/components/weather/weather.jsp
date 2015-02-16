<%@ page import="com.axamit.training.mycqproject.components.weather.WeatherModel" %>
<%@include file="/libs/foundation/global.jsp" %>

<div class="weather_block">

    <h3>${properties.titleWeatherComponent}</h3>

    <div class="content_area">
        ${properties.textWeatherComponent}
    </div>

    ${properties.cityWeatherComponents}
    ${properties.unitWeatherComponents}

    <%--${sling:adaptTo(resource, "com.axamit.training.mycqproject.components.weather.WeatherModel")}--%>
    <%--<c:set var="myProperties" value="${sling:adaptTo(resource,'com.axamit.training.mycqproject.components.weather.WeatherModel')}" />--%>
    <%
        WeatherModel weatherModel = resource.adaptTo(WeatherModel.class);
    %>
    <%= weatherModel.getCityWeatherComponents() %>
</div>