<%@include file="/apps/mycqproject/common/global.jsp" %>
<sling:adaptTo var="search" adaptable="${slingRequest}"
               adaptTo="com.axamit.training.mycqproject.components.weather.models.WeatherModel"/>
<div class="panel-header bg-pink fg-white">${properties.titleWeatherComponent}</div>
<div class="panel-content fg-dark nlp nrp">
    <img src="data:image/png;base64,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"
         alt=""/>

    <p>${properties.textWeatherComponent}</p>

    <p>Location: ${search.currentCondition.weatherArray[0].main} - <img
            src="http://openweathermap.org/img/w/${search.currentCondition.weatherArray[0].iconWeather}.png"
            alt="${search.currentCondition.weatherArray[0].main}" width="40"
            height="40"/></p>

    <p>${search.currentCondition.name}, ${search.currentCondition.country}</p>
    <table border="0" cellpadding="10" cellspacing="0" width="100%">
        <tr>
            <td>Min:</td>
            <td>Max:</td>
            <td>Humidity:</td>
            <td>Wind speed:</td>
        </tr>
        <tr>
            <td>${search.currentCondition.mainAttributes.temp_min}</td>
            <td>${search.currentCondition.mainAttributes.temp_max}</td>
            <td>${search.currentCondition.mainAttributes.humidity}</td>
            <td>${search.currentCondition.speed}</td>
        </tr>
    </table>
</div>