package com.clarkelamothe.intermedia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAndBottomBar.barTop.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView = binding.topAndBottomBar.barBottom.bottomNavView
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.charactersFragment, R.id.eventsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.apply {
            itemIconTintList = null
            setupWithNavController(navController)
        }
        setActionBarTitle("Marvel Challenge")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setActionBarTitle(title: String?) {
        binding.topAndBottomBar.barTop.topTitle.text = title
    }

    fun changeBackButton(resId: Int) {
        binding.topAndBottomBar.barTop.toolbar.setNavigationIcon(resId)
    }
}