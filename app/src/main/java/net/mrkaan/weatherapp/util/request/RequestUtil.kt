package net.mrkaan.weatherapp.util.request

class RequestUtil {
    companion object {
        var exampleUrl =
            "https://api.weatherbit.io/v2.0/forecast/daily?lat=39.2201094&lon=-80.1588387&key=a9ff03b3cd404cf98d3c94dfc571e6c0"

        var baseUrl = "https://api.weatherbit.io/v2.0/forecast/"

        fun getRequestUrl(lat: String, lon: String): String =
            baseUrl + "/daily?lat=${lat}&lon=${lon}&key=a9ff03b3cd404cf98d3c94dfc571e6c0"
    }
}