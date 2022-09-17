package net.mrkaan.weatherapp.views.main

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.mrkaan.weatherapp.network.ApiClient
import net.mrkaan.weatherapp.network.RequestService
import net.mrkaan.weatherapp.util.request.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val weatherData = MutableLiveData<ResponseModel>()
    val showToast = MutableLiveData<Boolean>()
    val location = MutableLiveData<Location>()

    lateinit var service: RequestService

    fun request(lat: String, lon: String) {
        service = ApiClient.getClient().create(RequestService::class.java)
        val forecast = service.getDailyForecast(lat,lon)

        forecast.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    response.body()?.let { weatherData.postValue(it) }
                } else {
                    showToast.postValue(!(showToast.value ?: true))
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                showToast.postValue(!(showToast.value ?: true))
            }
        })
    }
}