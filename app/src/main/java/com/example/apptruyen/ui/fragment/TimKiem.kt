package com.example.apptruyen.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.adapter.AdapterTimKiem
import com.example.apptruyen.data.TimKiem
import com.example.apptruyen.model.data.Truyen
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.*
import java.util.logging.Handler

class TimKiem : Fragment() {
    lateinit var viewPlay: View
    lateinit var adapterTimKiem: AdapterTimKiem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewPlay = inflater.inflate(R.layout.fragment_tim_kiem, container, false)
        var rvTimKiem: RecyclerView = viewPlay.findViewById(R.id.rv_timKiem)
        var listTruyen: MutableList<Truyen> = mutableListOf()
        var txtEmpty: TextView = viewPlay.findViewById(R.id.txt_empty)
        var searchView: SearchView = viewPlay.findViewById(R.id.search_bar)
        var keyWord: String? = ""

        adapterTimKiem = AdapterTimKiem()

        rvTimKiem.setHasFixedSize(true)
        rvTimKiem.layoutManager = LinearLayoutManager(context)
        rvTimKiem.adapter = adapterTimKiem

        val handler = android.os.Handler()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                println("????????????????????????????????")
                println(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    handler.postDelayed(Runnable {
                        kotlin.run {
                            GlobalScope.launch {
                                var timKiem = TimKiem()
                                timKiem.searchTruyen(newText)

                                listTruyen.clear()
                                if (timKiem.listTruyen.isNotEmpty())
                                    listTruyen = timKiem.listTruyen

                                for(i in 0 until listTruyen.size) {
                                    println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> $i ??????? ${listTruyen[i].toString()}")
                                }

                                withContext(Dispatchers.Main) {
                                    if (listTruyen.isEmpty()) {
                                        txtEmpty.visibility = View.VISIBLE
                                        rvTimKiem.visibility = View.GONE
                                    } else {
                                        txtEmpty.visibility = View.GONE
                                        rvTimKiem.visibility = View.VISIBLE
                                        adapterTimKiem.setData(listTruyen)
                                    }
                                }
                            }
                        }
                    }, 2000)
                }
                return false
            }

        })

//        GlobalScope.launch {
//            var timKiem = TimKiem()
//            timKiem.searchTruyen("dau pha thuong khung")
//            timKiem.removeEmpty()
//
////            timKiem.listTruyen.forEach {
////                println(it.toString())
////            }
//            delay(1000)
//            listTruyen.clear()
//            listTruyen = timKiem.listTruyen
//            adapterTimKiem = AdapterTimKiem(listTruyen)
//
//            withContext(Dispatchers.Main) {
//                if (listTruyen.isEmpty()) {
//                    txtEmpty.visibility = View.VISIBLE
//                    rvTimKiem.visibility = View.GONE
//
//                    println("1111111111111111111111111111111111111111111111111")
//                } else {
//                    txtEmpty.visibility = View.GONE
//                    rvTimKiem.visibility = View.VISIBLE
//                    rvTimKiem.adapter = adapterTimKiem
//
//                    println("2222222222222222222222222222222222222222222222222222222222222222")
//                }
//            }
//        }
        return viewPlay
    }
}