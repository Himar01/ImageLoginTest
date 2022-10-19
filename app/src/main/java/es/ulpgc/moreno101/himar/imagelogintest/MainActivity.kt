package es.ulpgc.moreno101.himar.imagelogintest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.ulpgc.moreno101.himar.imagelogintest.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(applicationContext, SharedApp::class.java)
        startActivity(intent)
        var currentTime: Date = Calendar.getInstance().time;
        var prefs = Prefs(applicationContext)
        binding.lastLogin.text = prefs.name
        SharedApp.prefs.name=currentTime.toString()

        binding.lastLogin.text = SharedApp.prefs.name
        SharedApp.prefs.name = currentTime.toString()

    }
}

class Prefs (context: Context){
    val PREFS_NAME = "es.ulpgc.moreno101.himar.imagelogintest"
    val SHARED_NAME = "shared_name"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME,0)

    var name: String?
        get() = prefs.getString(SHARED_NAME, "")
        set(value) = prefs.edit().putString(SHARED_NAME, value).apply()
}
