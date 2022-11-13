package com.rana.myweather_app

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {
@GET("weather")
fun getWeather(@Query("q") cityName: String,
               @Query("appid") apiKey: String):Call<ResponseModel>

}

//q is the city name