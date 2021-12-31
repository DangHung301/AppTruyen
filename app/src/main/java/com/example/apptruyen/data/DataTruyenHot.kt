package com.example.apptruyen.data

import com.example.apptruyen.helper.const.URL
import com.example.apptruyen.model.Status
import com.example.apptruyen.model.data.Truyen
import com.example.apptruyen.model.data.TruyenHome
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class DataTruyenHot {
    var url: String = URL.url_truyen_hot
    val urlHome: String = URL.url_home
    var listTruyenHotHome : MutableList<TruyenHome> = mutableListOf()

    fun uploadTruyenHotKhamPha() {
        listTruyenHotHome.clear()
        try {
            var document: Document = Jsoup.connect(urlHome).get()
            var elements: Elements = document.select("div.index-intro")

            for (i in 1..16) {
                val image : String
                val name : String = elements.select("div.item.top-$i").text()
                if (!elements.select("div.item.top-$i img.img-responsive.item-img").attr("lazysrc").isEmpty()) {
                   image = elements.select("div.item.top-$i img.img-responsive.item-img").attr("lazysrc")
                } else {
                   image = elements.select("div.item.top-$i img.img-responsive.item-img").attr("src")
                }

                val truyen = TruyenHome(image, name)

                this.listTruyenHotHome.add(truyen)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


}