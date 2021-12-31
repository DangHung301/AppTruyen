package com.example.apptruyen.data

import com.example.apptruyen.helper.const.URL
import com.example.apptruyen.model.data.TruyenHome
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class DataTruyenHoanThanh {
    val url : String = URL.url_home
    var listTruyenHoanThanh : MutableList<TruyenHome> = mutableListOf()

    fun uploadTruyenHoanThanh() {
        try {
            var document : Document = Jsoup.connect(url).get()
            val elements: Elements = document.select("div.col-xs-4.col-sm-3.col-md-2")

            for(i in elements) {
                val name : String =
                    i.select("a").attr("title")
                val image : String =
                    i.select("a div.lazyimg").attr("data-desk-image")

                val truyen = TruyenHome(image, name)
                listTruyenHoanThanh.add(truyen)
            }

        }catch (e : IOException) {
            e.printStackTrace()
        }
    }
}