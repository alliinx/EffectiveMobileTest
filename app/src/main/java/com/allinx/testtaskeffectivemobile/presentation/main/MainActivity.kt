package com.allinx.testtaskeffectivemobile.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.allinx.testtaskeffectivemobile.R
import com.allinx.testtaskeffectivemobile.databinding.ActivityMainBinding
import com.allinx.testtaskeffectivemobile.presentation.account.AccountFragment
import com.allinx.testtaskeffectivemobile.presentation.hotel.HotelFragment
import com.allinx.testtaskeffectivemobile.presentation.map.MapFragment
import com.allinx.testtaskeffectivemobile.presentation.subscribe.SubscribeFragment
import com.allinx.testtaskeffectivemobile.presentation.tickets.offers.OffersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.dark_green)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, 0)
            insets
        }

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        with(binding) {
            bottomNavigationView.itemIconTintList = null
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.tickets -> replaceFragment(OffersFragment())
                    R.id.hotel -> replaceFragment(HotelFragment())
                    R.id.map -> replaceFragment(MapFragment())
                    R.id.subscribe -> replaceFragment(SubscribeFragment())
                    R.id.account -> replaceFragment(AccountFragment())
                }
                true
            }
            bottomNavigationView.selectedItemId = R.id.tickets
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}