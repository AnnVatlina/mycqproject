<%@include file="/apps/mycqproject/common/global.jsp"%>

<div class="weather_block">

    <h3>${properties.titleWeatherComponent}</h3>

    <div class="content_area">
        ${properties.textWeatherComponent}
    </div>

    ${properties.cityWeatherComponents}
    ${properties.unitWeatherComponents}

    <sling:adaptTo var="search" adaptable="${resource}" adaptTo="com.axamit.training.mycqproject.models.WeatherModel"/>
    ${search.currentCondition.city}, ${search.currentCondition.country}

</div>