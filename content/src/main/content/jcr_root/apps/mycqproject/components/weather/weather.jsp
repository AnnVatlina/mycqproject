<%@include file="/apps/mycqproject/common/global.jsp" %>
<sling:adaptTo var="search" adaptable="${resource}"
               adaptTo="com.axamit.training.mycqproject.components.weather.models.WeatherModel"/>
<div class="panel-header bg-pink fg-white">${properties.titleWeatherComponent}</div>
<div class="panel-content fg-dark nlp nrp">

    <p>${properties.textWeatherComponent}</p>

    <p>Location: ${search.currentCondition.condition} - <img src="${search.currentCondition.icon}.png"
                                                             alt="${search.currentCondition.condition}" width="40"
                                                             height="40"/></p>

    <p>${search.currentCondition.city}, ${search.currentCondition.country}</p>
    <table border="0" cellpadding="10" cellspacing="0" width="100%">
        <tr>
            <td>Min:</td>
            <td>Max:</td>
            <td>Humidity:</td>
            <td>Wind speed:</td>
        </tr>
        <tr>
            <td>${search.currentCondition.tempMin}</td>
            <td>${search.currentCondition.tempMax}</td>
            <td>${search.currentCondition.humidity}</td>
            <td>${search.currentCondition.windSpeed}</td>
        </tr>
    </table>
</div>