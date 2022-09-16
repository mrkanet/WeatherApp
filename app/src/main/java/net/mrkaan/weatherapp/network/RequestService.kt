package net.mrkaan.weatherapp.network

import net.mrkaan.weatherapp.util.request.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestService {

    @GET("daily")
    fun getDailyForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("key") key: String = "a9ff03b3cd404cf98d3c94dfc571e6c0"
    ): Call<ResponseModel>
}