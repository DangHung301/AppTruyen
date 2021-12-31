package com.example.apptruyen.data

import com.example.apptruyen.helper.const.URL
import com.example.apptruyen.model.data.TheLoai
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception

class DataTheLoai {
    val url : String = URL.url_home
    var listTheLoai : MutableList<TheLoai> = mutableListOf()

    fun uploadTheLoai() {
        try {
            var document : Document = Jsoup.connect(url).get()
            var element : Elements =  document.select("div.col-xs-6");

            for(i in element) {
                val nameTheLoai = i.select("a").attr("title")
                val urlTheLoai = i.select("a").attr("href")
                val theLoai = TheLoai(urlTheLoai, nameTheLoai)
                listTheLoai.add(theLoai)
            }
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }
}