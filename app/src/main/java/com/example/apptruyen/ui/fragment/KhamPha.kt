package com.example.apptruyen.ui.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTheLoai
import com.example.apptruyen.adapter.AdapterTruyenHoanThanh
import com.example.apptruyen.data.TruyenHoanThanh
import com.example.apptruyen.data.TruyenHot
import com.example.apptruyen.model.data.Truyen
import com.example.apptruyen.model.data.TruyenHome
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
import java.lang.Math.log
import kotlin.concurrent.thread

class KhamPha : Fragment() {
    lateinit var viewPlay: View
    var listTruyenHot: MutableList<TruyenHome> = mutableListOf()
    var listTruyenHoanThanh : MutableList<TruyenHome> = mutableListOf()

    lateinit var adaptertruyenHotHome : AdapterTruyenHoanThanh
    lateinit var adapterTruyenHoanThanh: AdapterTruyenHoanThanh

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewPlay = inflater.inflate(R.layout.fragment_kham_pha, container, false)
        // Inflate the layout for this fragment
        var isShow = false
        var btn_xemthemth: Button = viewPlay.findViewById(R.id.btn_xemtiepth)

        var rvTruyenHot: RecyclerView = viewPlay.findViewById(R.id.rv_truyen_hot)
        rvTruyenHot.setHasFixedSize(true)
        rvTruyenHot.layoutManager = GridLayoutManager(this.context, 3)


        var rvTruyenHoanThanh: RecyclerView = viewPlay.findViewById(R.id.rv_truyen_ht)
        rvTruyenHot.setHasFixedSize(true)
        rvTruyenHoanThanh.layoutManager = GridLayoutManager(this.context, 3)

        GlobalScope.launch() {
            var truyenHot = TruyenHot()
            truyenHot.uploadTruyenHotKhamPha()

            listTruyenHot.addAll(truyenHot.listTruyenHotHome)

            adaptertruyenHotHome = AdapterTruyenHoanThanh(listTruyenHot, false)

            withContext(Dispatchers.Main) {
                rvTruyenHot.adapter = adaptertruyenHotHome

                if (listTruyenHot.size > 9) {
                    btn_xemthemth.visibility = View.VISIBLE
                } else {
                    btn_xemthemth.visibility = View.GONE
                }

                btn_xemthemth.setOnClickListener {
                    isShow = !isShow
                    adaptertruyenHotHome = AdapterTruyenHoanThanh(listTruyenHot, isShow)
                    if(isShow) {
                        btn_xemthemth.text = "Rút gọn"
                    } else {
                        btn_xemthemth.text = "Xem tiếp"
                    }
                    rvTruyenHot.adapter = adaptertruyenHotHome
                    adaptertruyenHotHome.notifyDataSetChanged()
                }
            }
        }

        var btn_xemthemht : Button = viewPlay.findViewById(R.id.btn_xemtieptht)

        GlobalScope.launch {
            var truyenHoanThanh = TruyenHoanThanh()
            truyenHoanThanh.uploadTruyenHoanThanh()

            listTruyenHoanThanh.addAll(truyenHoanThanh.listTruyenHoanThanh)

            adapterTruyenHoanThanh = AdapterTruyenHoanThanh(truyenHoanThanh.listTruyenHoanThanh, false)

            withContext(Dispatchers.Main) {
                if(listTruyenHoanThanh.size > 9) {
                    btn_xemthemht.visibility = View.VISIBLE
                } else {
                    btn_xemthemht.visibility = View.GONE
                }
                rvTruyenHoanThanh.adapter = adapterTruyenHoanThanh

                var isShow = false

                btn_xemthemht.setOnClickListener {
                    isShow = !isShow
                    adapterTruyenHoanThanh = AdapterTruyenHoanThanh(listTruyenHoanThanh, isShow)

                    if(isShow) {
                        btn_xemthemht.text = "Rút gọn"
                    } else {
                        btn_xemthemht.text = "Xem thêm"
                    }

                    rvTruyenHoanThanh.adapter = adapterTruyenHoanThanh
                    adapterTruyenHoanThanh.notifyDataSetChanged()
                }
            }
        }

        return viewPlay
    }
}