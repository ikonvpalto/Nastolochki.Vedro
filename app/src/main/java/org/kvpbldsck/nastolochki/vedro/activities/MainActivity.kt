package org.kvpbldsck.nastolochki.vedro.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.kvpbldsck.nastolochki.vedro.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        val toolbar = getToolbar()

        setSupportActionBar(toolbar)
    }

    private fun setupNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.navigation_menu)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navigation_host) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNav.setupWithNavController(navController)
    }

    private fun getToolbar(): Toolbar = findViewById(R.id.toolbar)
}