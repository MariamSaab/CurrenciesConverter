package com.kozachenko.currencieskotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kozachenko.currencieskotlin.fragments.AllCurrencies
import com.kozachenko.currencieskotlin.fragments.Converter
import com.kozachenko.currencieskotlin.fragments.MyCurrencies

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allCurrencies = AllCurrencies()
        val myCurrencies = MyCurrencies()
        val converter = Converter()
        makeCurrentFragment(allCurrencies)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_fragment)
        bottomNavigationView.setupWithNavController(navController)

       bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_all_currences -> makeCurrentFragment(allCurrencies)
                R.id.ic_my_currences -> makeCurrentFragment(converter) //for now I don't have my own list of currencies so I decided to put converter screen here
            }
            true
        }
    }

        private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.nav_fragment, fragment)
                commit()
            }
    }
