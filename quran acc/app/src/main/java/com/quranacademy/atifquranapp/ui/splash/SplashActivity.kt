package com.quranacademy.atifquranapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.quranacademy.atifquranapp.R
import com.quranacademy.atifquranapp.databinding.ActivitySplashBinding
import com.quranacademy.atifquranapp.ui.main.MainActivity
import com.quranacademy.atifquranapp.util.Constants

/**
 * Splash Screen Activity
 * Displays the app logo and name while initializing the app
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashHandler = Handler(Looper.getMainLooper())
    private val splashRunnable = Runnable {
        navigateToMain()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        startSplashTimer()
    }

    private fun setupViews() {
        // You can add animations or custom views here if needed
    }

    private fun startSplashTimer() {
        // Post a delayed runnable to navigate after splash delay
        splashHandler.postDelayed(splashRunnable, Constants.SPLASH_DELAY)
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove any pending callbacks to prevent memory leaks
        splashHandler.removeCallbacks(splashRunnable)
    }
}
