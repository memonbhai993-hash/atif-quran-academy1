package com.quranacademy.atifquranapp

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * Quran Academy Application Class
 *
 * This class initializes the application and sets up Firebase.
 */
class QuranAcademyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize Firebase
        try {
            FirebaseApp.initializeApp(this)
        } catch (e: IllegalStateException) {
            // Firebase already initialized
        }

        // Initialize app-wide configurations
        initAppConfigurations()
    }

    private fun initAppConfigurations() {
        // App configuration initialization
        // This can include analytics, crash reporting, etc.
    }

    companion object {
        @Volatile
        private lateinit var instance: QuranAcademyApp

        fun getInstance(): QuranAcademyApp = instance
    }
}
