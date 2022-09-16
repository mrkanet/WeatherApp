package net.mrkaan.weatherapp.network

import net.mrkaan.weatherapp.util.request.RequestUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(RequestUtil.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit as Retrofit
    }
}