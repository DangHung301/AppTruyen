package com.example.apptruyen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R

class AdapterTheLoai(val listTheLoai : MutableList<String>) : RecyclerView.Adapter<ViewHolderTheLoai>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTheLoai {
        return ViewHolderTheLoai(LayoutInflater.from(parent.context).inflate(R.layout.item_the_loai, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderTheLoai, position: Int) {
        var item = listTheLoai[position]
        holder.txtTheLoai.text = item
    }

    override fun getItemCount(): Int {
        return this.listTheLoai.size
    }
}

class ViewHolderTheLoai(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txtTheLoai : TextView = itemView.findViewById(R.id.txt_theloai)
}
