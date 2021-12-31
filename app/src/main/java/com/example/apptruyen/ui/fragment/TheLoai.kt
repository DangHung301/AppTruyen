package com.example.apptruyen.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTheLoai
import com.example.apptruyen.data.DataTheLoai
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import androidx.recyclerview.widget.GridLayoutManager


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
            var theLoai = DataTheLoai()
            theLoai.uploadTheLoai()

            withContext(Dispatchers.Main) {
                val adapter = AdapterTheLoai(theLoai.listTheLoai, viewPlay.context)
                adapter.notifyDataSetChanged()
                rvTheLoai.adapter = adapter
            }
        }

        return viewPlay
    }

    companion object {}
}