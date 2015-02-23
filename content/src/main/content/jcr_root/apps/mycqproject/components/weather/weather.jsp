<%@include file="/apps/mycqproject/common/global.jsp" %>
<sling:adaptTo var="search" adaptable="${resource}"
               adaptTo="com.axamit.training.mycqproject.components.weather.models.WeatherModel"/>
<div class="panel-header bg-pink fg-white">${properties.titleWeatherComponent}</div>
<div class="panel-content fg-dark nlp nrp">

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