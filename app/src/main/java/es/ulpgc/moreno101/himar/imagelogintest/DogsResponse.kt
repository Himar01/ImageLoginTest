package es.ulpgc.moreno101.himar.imagelogintest

import android.telecom.Call
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

data class DogsResponse (@SerializedName("status") var status:String,
@SerializedName("message")var images: List<String>){

    interface APIService {
        @GET
        fun getCharacterByName(@Url url:String): Res<DogsResponse>

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchByName(query: String) {
        doAsync{
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
            val puppies = call.body() as DogsResponse
            uiThread{

            }
        }
    }
}