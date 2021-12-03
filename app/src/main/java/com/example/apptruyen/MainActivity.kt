package com.example.apptruyen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.apptruyen.data.TruyenHot
import com.example.apptruyen.ui.fragment.KhamPha
import com.example.apptruyen.ui.fragment.TheLoai
import com.example.apptruyen.ui.fragment.ThuVien
import com.example.apptruyen.ui.fragment.TimKiem
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtAppBar : TextView = findViewById(R.id.title_appbar)
        var navigation : BottomNavigationView = findViewById(R.id.bottom_navigation)

        var test : TruyenHot = TruyenHot()

        test.uploadTruyen()

        println(test.listTruyen)

        navigation.setOnItemSelectedListener{ item ->
            val fragment : Fragment

            when (item.itemId) {
                R.id.the_loai -> {
                    fragment = TheLoai()
                    txtAppBar.setText("Thể loại")
                    openFragment(fragment)
                    true
                }

                R.id.kham_pha -> {
                    fragment = KhamPha()
                    txtAppBar.setText("Khám phá")
                    openFragment(fragment)
                    true

                }

                R.id.tim_kiem -> {
                    println(item.itemId)
                    fragment = TimKiem()
                    txtAppBar.setText("Tìm kiếm")
                    openFragment(fragment)
                    true

                }

                R.id.thu_vien -> {
                    println(item.itemId)
                    fragment = ThuVien()
                    txtAppBar.setText("Thư viện")
                    openFragment(fragment)
                  true

                }

                else -> {
                    fragment = TheLoai()
                    txtAppBar.setText("Thể loại")
                    openFragment(fragment)
                    true
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}