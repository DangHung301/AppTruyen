package com.example.apptruyen.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTheLoai
import com.example.apptruyen.data.TruyenHot
import com.example.apptruyen.model.Truyen
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class KhamPha : Fragment() {
    lateinit var viewPlay: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPlay = inflater.inflate(R.layout.fragment_kham_pha, container, false)
        // Inflate the layout for this fragment
        var rvTruyenHot: RecyclerView = viewPlay.findViewById(R.id.rv_truyen_hot)
        rvTruyenHot.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        GlobalScope.launch() {
            var a = TruyenHot()
            a.uploadTruyen()
            a.removeEmpty()
            val adapter = AdapterTheLoai(a.listTruyen)

            withContext(Dispatchers.Main) {
                a.listTruyen.forEach { println(it) }
                rvTruyenHot.adapter = adapter
            }
        }

        return viewPlay
    }


    companion object {

    }
}