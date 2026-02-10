package com.quranacademy.atifquranapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.quranacademy.atifquranapp.R
import com.quranacademy.atifquranapp.databinding.ActivityMainBinding
import com.quranacademy.atifquranapp.ui.admin.AdminActivity
import com.quranacademy.atifquranapp.util.Constants

/**
 * Main Activity with Bottom Navigation
 * Hosts all the main fragments of the application
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupNavigation() {
        // Get NavController from NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        // Setup AppBarConfiguration with top-level destinations
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_courses,
                R.id.navigation_teachers,
                R.id.navigation_classes,
                R.id.navigation_contact
            )
        )

        // Setup ActionBar with NavController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Setup Bottom Navigation with NavController
        binding.bottomNavigation.setupWithNavController(navController)

        // Update toolbar title based on destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title = destination.label

            // Hide bottom navigation for detail screens
            when (destination.id) {
                R.id.registrationFragment,
                R.id.courseDetailFragment,
                R.id.teacherDetailFragment -> {
                    binding.bottomNavigation.visibility = android.view.View.GONE
                }
                else -> {
                    binding.bottomNavigation.visibility = android.view.View.VISIBLE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_admin -> {
                navigateToAdmin()
                true
            }
            R.id.action_share -> {
                shareApp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToAdmin() {
        // Check if admin is logged in
        val prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE)
        val isAdmin = prefs.getBoolean(Constants.PREF_IS_ADMIN, false)

        if (isAdmin) {
            startActivity(Intent(this, AdminActivity::class.java))
        } else {
            // Navigate to admin login
            startActivity(Intent(this, AdminActivity::class.java))
        }
    }

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
            putExtra(
                Intent.EXTRA_TEXT,
                "Download ${getString(R.string.app_name)} and learn Quran online with verified teachers!\n\n${getString(R.string.contact_play_store_link)}"
            )
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.contact_share)))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
