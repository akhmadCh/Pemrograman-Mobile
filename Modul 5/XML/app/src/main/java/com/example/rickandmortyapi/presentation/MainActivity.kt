package com.example.rickandmortyapi.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.rickandmortyapi.R
import com.example.rickandmortyapi.databinding.ActivityMainBinding
import com.example.rickandmortyapi.presentation.favorites.FavoriteFragment
import com.example.rickandmortyapi.presentation.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val fragHome = HomeFragment()
    val fragFavorites = FavoriteFragment()
    val mFragmentManager = supportFragmentManager
    // active fragment
    var active: Fragment = fragHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNaviBottom()
    }

    private fun setUpNaviBottom () {
        mFragmentManager.beginTransaction().apply {
            add(R.id.navigation_content, fragFavorites).hide(fragFavorites)
            add(R.id.navigation_content, fragHome)
        }.commit()

        active = fragHome

        menu = binding.btnNavigation.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        binding.btnNavigation.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    callFrag(0, fragHome)
                }
                R.id.navigation_fav -> {
                    callFrag(1, fragFavorites)
                }
            }
            true
        }
    }

    private fun callFrag (i: Int, fragment: Fragment) {
        if (fragment != active) {
            Log.d("MyFlexibleFragment", "Fragment Name : " + active::class.java.simpleName)
            menuItem = menu.getItem(i)
            menuItem.isChecked = true

            mFragmentManager.beginTransaction()
                .hide(active)
                .show(fragment)
                .commit()

            active = fragment
        }
    }
}