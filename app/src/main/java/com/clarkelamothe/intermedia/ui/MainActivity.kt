package com.clarkelamothe.intermedia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.barTop.toolbar)
        bottomNavigationView = binding.barBottom.bottomNavView
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.charactersFragment, R.id.eventsFragment))
        navController = findNavController(R.id.homeFragmentContainer)
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.apply {
            itemIconTintList = null
            setupWithNavController(navController)
        }
    }
}