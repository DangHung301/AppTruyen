package com.example.apptruyen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.model.data.TruyenHome
import com.squareup.picasso.Picasso

class AdapterTruyenHoanThanh(var listTruyen: MutableList<TruyenHome>, var isShow : Boolean) :
    RecyclerView.Adapter<ViewHolderHoanThanh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHoanThanh {

        return ViewHolderHoanThanh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_column_truyen, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if(isShow) listTruyen.size
            else 6
    }

    override fun onBindViewHolder(holder: ViewHolderHoanThanh, position: Int) {
            val item = this.listTruyen[position]
            Picasso.get().load(item.image).into(holder.imageView)
            holder.txtView.text = item.name
    }
}

class ViewHolderHoanThanh(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.column_img_truyen)
    val txtView: TextView = itemView.findViewById(R.id.column_txt)
}