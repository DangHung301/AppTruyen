package com.example.apptruyen.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTheLoai
import com.example.apptruyen.data.TheLoai
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.security.auth.Destroyable
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.core.app.ApplicationProvider


class TheLoai : Fragment() {
    lateinit var viewPlay : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPlay = inflater.inflate(R.layout.fragment_the_loai, container, false)

        var rvTheLoai : RecyclerView = viewPlay.findViewById(R.id.rv_the_loai)

        rvTheLoai.layoutManager = GridLayoutManager(context, 2)

        GlobalScope.launch {
            var theLoai = TheLoai()
            theLoai.uploadTheLoai()

            val adapter = AdapterTheLoai(theLoai.listTheLoai)

            withContext(Dispatchers.Main) {
                rvTheLoai.adapter = adapter
            }
        }

        return viewPlay
    }

    companion object {}
}