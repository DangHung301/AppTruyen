package com.example.apptruyen.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTheLoai
import com.example.apptruyen.adapter.AdapterTruyenHoanThanh
import com.example.apptruyen.data.TruyenHoanThanh
import com.example.apptruyen.data.TruyenHot
import com.example.apptruyen.model.data.TruyenHome
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*

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

        var rvTruyenHoanThanh : RecyclerView = viewPlay.findViewById(R.id.rv_truyen_ht)
        rvTruyenHoanThanh.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        GlobalScope.launch() {
            var truyenHot = TruyenHot()
            truyenHot.uploadTruyenHotKhamPha()
            truyenHot.removeEmpty()
            val adapter = AdapterTruyenHoanThanh(truyenHot.listTruyenHotHome)

            withContext(Dispatchers.Main) {
                truyenHot.listTruyenHotHome.forEach { println(it) }
                rvTruyenHot.adapter = adapter
            }
        }

        GlobalScope.launch {
            var truyenHoanThanh = TruyenHoanThanh()
            truyenHoanThanh.uploadTruyenHoanThanh()

            val adapter = AdapterTruyenHoanThanh(truyenHoanThanh.listTruyenHoanThanh)

            withContext(Dispatchers.Main) {
                rvTruyenHoanThanh.adapter = adapter
            }
        }

        return viewPlay
    }

    companion object {

    }
}