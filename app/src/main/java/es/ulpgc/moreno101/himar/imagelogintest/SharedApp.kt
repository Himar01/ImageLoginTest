package es.ulpgc.moreno101.himar.imagelogintest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SharedApp : AppCompatActivity() {
    companion object {
        lateinit var prefs: Prefs
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = Prefs(applicationContext)
        finish()
    }
}