package es.ulpgc.moreno101.himar.imagelogintest

import android.telecom.Call
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

data class DogsResponse (
    @SerializedName("status") var status:String,
    @SerializedName("message") var images: List<String>){

}