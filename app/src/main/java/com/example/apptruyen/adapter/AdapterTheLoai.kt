package com.example.apptruyen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.model.Truyen

class AdapterTheLoai(var listTruyen: MutableList<Truyen>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")

//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.id))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return this.listTruyen.size
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

}