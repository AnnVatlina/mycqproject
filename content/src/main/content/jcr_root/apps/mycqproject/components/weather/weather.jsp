<%@ page import="com.axamit.training.mycqproject.models.WeatherModel" %>
<%@include file="/libs/foundation/global.jsp"%>

<div class="weather_block">

    <h3>${properties.titleWeatherComponent}</h3>

    <div class="content_area">
        ${properties.textWeatherComponent}
    </div>

    ${properties.cityWeatherComponents}
    ${properties.unitWeatherComponents}

    <%
        com.axamit.training.mycqproject.models.WeatherModel weatherModel = resource.adaptTo(com.axamit.training.mycqproject.models.WeatherModel.class);
        System.out.println("JspClass.jsp_service_method");
        weatherModel.getCityWeatherComponents();
    %>

</div>