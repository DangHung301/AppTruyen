package com.example.apptruyen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.model.data.Truyen
import com.example.apptruyen.model.data.TruyenHome
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class AdapterTimKiem() : RecyclerView.Adapter<ViewHolderTimKiem>() {
        var listTruyen : MutableList<Truyen> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTimKiem {
            return ViewHolderTimKiem(
                LayoutInflater.from(parent.context).inflate(R.layout.item_row_truyen, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTimKiem, position: Int) {
        if(listTruyen.isNotEmpty()){
            var item = this.listTruyen[position]

            holder.txtName.text = item.name
            holder.txtAuthor.text = item.author
            holder.txtChapter.text = item.chapter

            if(item.isHot) {
                holder.statusHot.visibility = View.VISIBLE
            } else {
                holder.statusHot.visibility = View.GONE
            }

            if(item.isFull) {
                holder.statusFull.visibility = View.VISIBLE
            } else {
                holder.statusFull.visibility = View.GONE
            }

            Picasso.get().load(item.image).into(holder.imgView)
        }
    }

    override fun getItemCount(): Int {
        return if(listTruyen == null) {
            0
        } else {
            listTruyen.size
        }
    }

    public fun setData(listData : MutableList<Truyen>) {
        listTruyen.clear()
        listTruyen = listData
        notifyDataSetChanged()
    }
}

class ViewHolderTimKiem(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var imgView : ShapeableImageView = itemView.findViewById(R.id.row_img_truyen)
    var txtName : TextView = itemView.findViewById(R.id.row_ten_truyen)
    var txtAuthor : TextView = itemView.findViewById(R.id.row_tac_gia)
    var txtChapter : TextView = itemView.findViewById(R.id.row_chuong)
    var statusHot : TextView = itemView.findViewById(R.id.status_hot)
    var statusFull : TextView = itemView.findViewById(R.id.status_full)
}
