package org.kvpbldsck.nastolochki.vedro.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kvpbldsck.nastolochki.vedro.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.navigation_menu)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navigation_host) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNav.setupWithNavController(navController)
    }
}