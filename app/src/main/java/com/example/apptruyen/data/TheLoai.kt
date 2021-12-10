package com.example.apptruyen.data

import com.example.apptruyen.helper.const.URL
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception

class TheLoai {
    val url : String = URL.url_home
    var listTheLoai : MutableList<String> = mutableListOf()

    fun uploadTheLoai() {
        try {
            var document : Document = Jsoup.connect(url).get()
            var element : Elements =  document.select("div.col-xs-6");

            for(i in element) {
                val theLoai = i.select("a").attr("title")
                listTheLoai.add(theLoai)
            }
        }catch (e : Exception) {
            e.printStackTrace()
        }
    }
}