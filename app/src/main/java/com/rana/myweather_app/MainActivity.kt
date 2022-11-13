package com.rana.myweather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val apiKey: String = "4ecbc5f10b8546146d057b25c4f2d0cb"
    var city: String = "Cairo"
    lateinit var textView_dg: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView_dg = findViewById(R.id.degree_TV)
        getWeather()
    }

    private fun getWeather() {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var responseApi: IWeatherApi = retrofit.create(IWeatherApi::class.java)
        var response: Call<ResponseModel> = responseApi.getWeather("Cairo", apiKey)
        response.enqueue(/* callback = */ object: Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>
                                    ,response: Response<ResponseModel>) {
                if (response.isSuccessful){
                    textView_dg.text = "${response.code()}"
                }
                else if ( !response.isSuccessful){
                 textView_dg.text = "An error has occurred"
                }


               var data: ResponseModel? = response.body()
                textView_dg.text = data?.main?.temp.toString()


            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                textView_dg.text = t.message
            }
        })


    }


}


