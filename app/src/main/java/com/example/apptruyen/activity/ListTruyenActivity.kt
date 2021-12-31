package com.example.apptruyen.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.MainActivity
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTimKiem
import com.example.apptruyen.data.DataListTruyen
import com.example.apptruyen.model.data.Truyen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListTruyenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_truyen)

        var btnBack : ImageButton = findViewById(R.id.back)
        var rvListTruyen : RecyclerView = findViewById(R.id.rv_list_the_loai)
        var txtAppbar : TextView = findViewById(R.id.title_appbar)

        var listTruyen : MutableList<Truyen> = mutableListOf()
        val extras = intent.extras
        var adapter = AdapterTimKiem()
        rvListTruyen.setHasFixedSize(false)
        rvListTruyen.layoutManager = LinearLayoutManager(this)
        txtAppbar.text = extras?.getString("nameTheLoai")

        btnBack.visibility = View.VISIBLE
        btnBack.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        GlobalScope.launch {
            val urlTheLoai = extras?.getString("urlTheLoai")
            var dataListTruyen = DataListTruyen(urlTheLoai)
            dataListTruyen.getTruyen()

            listTruyen = dataListTruyen.listTruyen

            adapter.setData(listTruyen)

            withContext(Dispatchers.Main) {
                rvListTruyen.adapter = adapter
            }
        }

    }
}
