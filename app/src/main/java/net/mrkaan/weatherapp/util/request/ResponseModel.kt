package net.mrkaan.weatherapp.util.request

data class ResponseModel(
    val city_name: String,
    val country_code: String,
    val data: List<Data>,
    val lat: Double,
    val lon: Double,
    val state_code: String,
    val timezone: String
)

data class Data(
    val datetime: String,
    val temp: Double,
    val weather: Weather,
)

data class Weather(
    val code: Int,
    val description: String,
    val icon: String
)


/*
data class ResponseModel(
    @SerializedName("city_name")
    val city_name: String,
    @SerializedName("country_code")
    val country_code: String,
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("state_code")
    val state_code: String,
    @SerializedName("timezone")
    val timezone: String
)

data class Data(
    @SerializedName("app_max_temp")
    val app_max_temp: Double,
    @SerializedName("app_min_temp")
    val app_min_temp: Double,
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("clouds_hi")
    val clouds_hi: Int,
    @SerializedName("clouds_low")
    val clouds_low: Int,
    @SerializedName("clouds_mid")
    val clouds_mid: Int,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("dewpt")
    val dewpt: Double,
    @SerializedName("high_temp")
    val high_temp: Double,
    @SerializedName("low_temp")
    val low_temp: Double,
    @SerializedName("max_dhi")
    val max_dhi: Any,
    @SerializedName("max_temp")
    val max_temp: Double,
    @SerializedName("min_temp")
    val min_temp: Double,
    @SerializedName("moon_phase")
    val moon_phase: Double,
    @SerializedName("moon_phase_lunation")
    val moon_phase_lunation: Double,
    @SerializedName("moonrise_ts")
    val moonrise_ts: Int,
    @SerializedName("moonset_ts")
    val moonset_ts: Int,
    @SerializedName("ozone")
    val ozone: Double,
    @SerializedName("pop")
    val pop: Int,
    @SerializedName("precip")
    val precip: Double,
    @SerializedName("pres")
    val pres: Double,
    @SerializedName("rh")
    val rh: Int,
    @SerializedName("slp")
    val slp: Double,
    @SerializedName("snow")
    val snow: Int,
    @SerializedName("snow_depth")
    val snow_depth: Int,
    @SerializedName("sunrise_ts")
    val sunrise_ts: Int,
    @SerializedName("sunset_ts")
    val sunset_ts: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("ts")
    val ts: Int,
    @SerializedName("uv")
    val uv: Double,
    @SerializedName("valid_date")
    val valid_date: String,
    @SerializedName("vis")
    val vis: Double,
    @SerializedName("weather")
    val weather: Weather,
    @SerializedName("wind_cdir")
    val wind_cdir: String,
    @SerializedName("wind_cdir_full")
    val wind_cdir_full: String,
    @SerializedName("wind_dir")
    val wind_dir: Int,
    @SerializedName("wind_gust_spd")
    val wind_gust_spd: Double,
    @SerializedName("wind_spd")
    val wind_spd: Double
)

data class Weather(
    @SerializedName("code")
    val code: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)*/