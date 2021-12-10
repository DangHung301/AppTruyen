package com.example.apptruyen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.model.data.TruyenHome
import com.squareup.picasso.Picasso

class AdapterTruyenHoanThanh (var listTruyen : MutableList<TruyenHome>) : RecyclerView.Adapter<ViewHolderHoanThanh>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHoanThanh {

        return  ViewHolderHoanThanh(LayoutInflater.from(parent.context).inflate(R.layout.item_column_truyen, parent, false))
    }

    override fun getItemCount(): Int {
        return this.listTruyen.size
    }

    override fun onBindViewHolder(holder: ViewHolderHoanThanh, position: Int) {
        val item = this.listTruyen.get(position)
        Picasso.get().load(item.image).into(holder.imageView)
        holder.txtView.setText(item.name)
    }
}

class ViewHolderHoanThanh(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val imageView : ImageView = itemView.findViewById(R.id.column_img_truyen)
    val txtView : TextView = itemView.findViewById(R.id.column_txt)
}