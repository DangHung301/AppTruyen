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

class TruyenHot {
    var url: String = URL.url_truyen_hot
    val urlHome: String = URL.url_home
    var listTruyenHot: MutableList<Truyen> = mutableListOf()
    var listTruyenHotHome : MutableList<TruyenHome> = mutableListOf()

    fun uploadTruyenHotKhamPha() {
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

    fun uploadTruyenHot() {

        try {
            var document: Document = Jsoup.connect(url).get()
            var element: Elements = document.select("div.row")

            for (i: Element in element) {
                var image: String = i.select("div.col-xs-3 div").attr("data-image");
                var name: String = i.select("div.col-xs-7 h3.truyen-title").text()
                var author: String = i.select("div.col-xs-7 span.author").text();
                var chapter: String = i.select("div.col-xs-2.text-info div a").text();
                var isFull: Boolean = Status.FULL.getStatus(
                    i.select("div.col-xs-7 span.label-title.label-full").toString()
                );
                var isHot: Boolean = Status.HOT.getStatus(
                    i.select("div.col-xs-7 span.label-title.label-hot").toString()
                );
                var isNews: Boolean = Status.HOT.getStatus(
                    i.select("div.col-xs-7 span.label-title.label-new").toString()
                )

                val truyen = Truyen(image, name, author, chapter, isFull, isHot, isNews)
                listTruyenHot.add(truyen)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun removeEmpty() {
        val removeList = ArrayList<Truyen>()
        this.listTruyenHot.forEach {
            if (it.image.isEmpty()) {
                removeList.add(it)
            }
        }

        this.listTruyenHot.removeAll(removeList)
        removeList.clear()
    }
}