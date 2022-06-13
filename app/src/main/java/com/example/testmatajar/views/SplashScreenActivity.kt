package com.example.testmatajar.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.testmatajar.R
import com.example.testmatajar.utils.Constants
import com.example.testmatajar.utils.SessionManager
import com.example.testmatajar.views.home.HomeActivity

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        setupDelay()
    }

    private fun setupDelay() {

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()

        }, Constants.SPLASH_TIME_OUT)

    }

}