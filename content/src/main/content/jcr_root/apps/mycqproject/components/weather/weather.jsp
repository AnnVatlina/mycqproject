<%@include file="/libs/foundation/global.jsp"%>

<div class="weather_block">

    <h3>${properties.titleWeatherComponent}</h3>

    <div class="content_area">
        ${properties.textWeatherComponent}
    </div>

    ${properties.cityWeatherComponents}
    ${properties.unitWeatherComponents}

    ${sling:adaptTo(resource, "com.axamit.training.mycqproject.components.weather.WeatherModel")}

</div>