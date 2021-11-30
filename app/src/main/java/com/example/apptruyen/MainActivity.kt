package com.example.apptruyen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var navigation : BottomNavigationView = findViewById(R.id.bottom_navigation)

//        navigation.setOnItemSelectedListener {
//
//
//            return@setOnItemSelectedListener true
//        }
//        navigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
//            @Override
//            fun onNavigationOtemSelected() : Boolean {
//
//                return true
//            }
//        })
    }
}