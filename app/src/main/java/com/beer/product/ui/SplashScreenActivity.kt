package com.beer.product.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.product.R

class SplashScreenActivity : AppCompatActivity() {

    private companion object {
        const val SPLASH_SCREEN_DISPLAY_TIME_MILLISECONDS = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(
            { startMainActivity() },
            SPLASH_SCREEN_DISPLAY_TIME_MILLISECONDS
        )
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

