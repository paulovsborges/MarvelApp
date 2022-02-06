package com.pvsb.marvelapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.pvsb.marvelapp.R
import com.pvsb.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appbarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment

        navController = navHost.navController

        appbarConfiguration = AppBarConfiguration(
            setOf(
                R.id.characters,
                R.id.favorites,
                R.id.about,
            )
        )

        binding.bottomNav.setupWithNavController(navController)

    }
}