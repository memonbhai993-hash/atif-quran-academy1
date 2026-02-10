package com.quranacademy.atifquranapp.ui.admin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.quranacademy.atifquranapp.databinding.ActivityAdminBinding
import com.quranacademy.atifquranapp.util.Constants

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        checkAdminLogin()
    }

    private fun setupViews() {
        binding.btnLogin.setOnClickListener {
            performLogin()
        }
    }

    private fun checkAdminLogin() {
        val prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE)
        val isAdmin = prefs.getBoolean(Constants.PREF_IS_ADMIN, false)

        if (isAdmin) {
            showDashboard()
        }
    }

    private fun performLogin() {
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()

        if (username == Constants.ADMIN_USERNAME && password == Constants.ADMIN_PASSWORD) {
            // Save admin state
            val prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE)
            prefs.edit().putBoolean(Constants.PREF_IS_ADMIN, true).apply()

            showDashboard()
        }
    }

    private fun showDashboard() {
        binding.loginLayout.visibility = View.GONE
        binding.dashboardLayout.visibility = View.VISIBLE
    }

    fun logout() {
        val prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE)
        prefs.edit().putBoolean(Constants.PREF_IS_ADMIN, false).apply()

        binding.loginLayout.visibility = View.VISIBLE
        binding.dashboardLayout.visibility = View.GONE
        binding.etUsername.text?.clear()
        binding.etPassword.text?.clear()
    }
}
