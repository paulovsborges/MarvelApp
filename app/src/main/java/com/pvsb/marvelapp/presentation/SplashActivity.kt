package com.pvsb.marvelapp.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.pvsb.marvelapp.R
import com.pvsb.marvelapp.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSplashScreen()

    }

    private fun initSplashScreen() {

        binding.mainContent.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_fade))

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            SPLASH_DURATION
        )
    }

    companion object {
        const val SPLASH_DURATION = 3000L
    }
}