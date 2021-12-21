package com.example.apptruyen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.apptruyen.data.TruyenHot
import com.example.apptruyen.model.data.Truyen
import com.example.apptruyen.model.data.TruyenHome
import com.example.apptruyen.ui.fragment.KhamPha
import com.example.apptruyen.ui.fragment.TheLoai
import com.example.apptruyen.ui.fragment.ThuVien
import com.example.apptruyen.ui.fragment.TimKiem
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.core.Observer as Observer


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtAppBar: TextView = findViewById(R.id.title_appbar)
        var navigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        openFragment(TheLoai())

        runOnUiThread {  }

        navigation.setOnItemSelectedListener { item ->
            val fragment: Fragment

            when (item.itemId) {
                R.id.the_loai -> {
                    fragment = TheLoai()
                    txtAppBar.text = "Thể loại"
                    openFragment(fragment)
                    true
                }

                R.id.kham_pha -> {
                    fragment = KhamPha()
                    txtAppBar.text = "Khám phá"
                    openFragment(fragment)
                    true
                }

                R.id.tim_kiem -> {
                    println(item.itemId)
                    fragment = TimKiem()
                    txtAppBar.text = "Tìm kiếm"
                    openFragment(fragment)
                    true
                }

                R.id.thu_vien -> {
                    println(item.itemId)
                    fragment = ThuVien()
                    txtAppBar.text = "Thư viện"
                    openFragment(fragment)
                    true
                }

                else -> {
                    fragment = TheLoai()
                    txtAppBar.text = "Thể loại"
                    openFragment(fragment)
                    true
                }
            }
        }
    }

    private fun getThotobserver(): Observer<Truyen> = object : Observer<Truyen> {
        override fun onSubscribe(d: Disposable?) {
            println("Subscribed to $d")
        }

        override fun onError(e: Throwable?) {
            println("Error Occured $e")
        }

        override fun onComplete() {
            println("All Completed")
        }

        override fun onNext(t: Truyen?) {
            TODO("Not yet implemented")
            println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<")
            println(t)
        }
    }

    private fun getTHotObservable(): Observable<TruyenHome> {
        val listTruyen = getListTruyen()

        return Observable.create(ObservableOnSubscribe {

            for (truyen in listTruyen) {
//                if (!it.isDisposed)
                it.onNext(truyen)
            }

            it.onComplete()
        })
    }

    private fun getListTruyen(): List<TruyenHome> {
        var truyen = TruyenHot()

        truyen.uploadTruyenHotKhamPha()

        return truyen.listTruyenHotHome
    }

    private fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}