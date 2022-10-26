package es.ulpgc.moreno101.himar.imagelogintest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.squareup.picasso.Picasso
import es.ulpgc.moreno101.himar.imagelogintest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener{
    private lateinit var binding: ActivityMainBinding
    private val dogImages = mutableListOf<String>()
    private var initDogValue:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var currentTime: Date = Calendar.getInstance().time;
        var prefs = Prefs(applicationContext)
        binding.lastLogin.text = prefs.name
        binding.searchView.setOnQueryTextListener(this)
        prefs.name= currentTime.toString()
        binding.button.setOnClickListener { v->
            changeImage()
        }

    }

    private fun changeImage() {
        initDogValue = nextInt(0,dogImages.size)
        Picasso.get().load(dogImages[initDogValue]).into(binding.dogImage)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            searchByName(query.lowercase())
        }
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getDogsByBreeds("$query/images")
            val puppies = call.body()
            runOnUiThread(){
                if(call.isSuccessful){
                    val images = puppies?.images ?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    changeImage()

                }else{
                    showError()
                }
            }
        }


}
    private fun showError() {
        Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
class Prefs (context: Context){
    val PREFS_NAME = "es.ulpgc.moreno101.himar.imagelogintest"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME,0)

    var name: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}



}




