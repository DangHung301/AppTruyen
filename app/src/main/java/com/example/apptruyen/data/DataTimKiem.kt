package com.example.apptruyen.data

import com.example.apptruyen.model.Status
import com.example.apptruyen.model.data.Truyen
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException

class DataTimKiem {

    var listTruyen: MutableList<Truyen> = mutableListOf()

    fun searchTruyen(keyWord : String?) {
        listTruyen.clear()
        var url = "https://truyenfull.vn/tim-kiem/?tukhoa=$keyWord"
        println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> $keyWord ???????????????????????????????????")

        try {
            var document: Document = Jsoup.connect(url).get()
            var elements: Elements = document.select("div.row")

            for (i: Element in elements) {
                var image: String = i.select("div.col-xs-3 div").attr("data-image");
                var name: String = i.select("div.col-xs-7 h3.truyen-title").text()
                var author: String = i.select("div.col-xs-7 span.author").text();
                var chapter: String = i.select("div.col-xs-2.text-info div a").text();
                var isFull: Boolean = Status.FULL.getStatus(
                    i.select("div.col-xs-7 span.label-title.label-full").toString()
                )
                var isHot: Boolean = Status.HOT.getStatus(
                    i.select("div.col-xs-7 span.label-title.label-hot").toString()
                );
                var isNews: Boolean = Status.HOT.getStatus(
                    i.select("div.col-xs-7 span.label-title.label-new").toString()
                )

                val truyen = Truyen(image, name, author, chapter, isFull, isHot, isNews)
                if(truyen.name.isNotEmpty()) {
                    listTruyen.add(truyen)
                }
            }
        } catch (e : IOException) {
            println(e.toString())
        }
    }

    fun removeEmpty() {
        val removeList = ArrayList<Truyen>()
        this.listTruyen.forEach {
            if (it.image.isEmpty()) {
                removeList.add(it)
            }
        }

        this.listTruyen.removeAll(removeList)
        removeList.clear()
    }
}