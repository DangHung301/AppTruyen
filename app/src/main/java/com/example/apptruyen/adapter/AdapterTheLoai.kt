package com.example.apptruyen.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptruyen.R
import com.example.apptruyen.activity.ListTruyenActivity
import com.example.apptruyen.model.data.TheLoai

class AdapterTheLoai(val listTheLoai: MutableList<TheLoai>, val context: Context) :
    RecyclerView.Adapter<ViewHolderTheLoai>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTheLoai {
        return ViewHolderTheLoai(
            LayoutInflater.from(parent.context).inflate(R.layout.item_the_loai, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolderTheLoai, position: Int) {
        var item = listTheLoai[position]
        holder.txtTheLoai.text = item.nameTheLoai

        holder.setItemClickListener(object : ItemClickListener {
            override fun setOnClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick) {

                } else {
                    println("44444444444444444444444444444444444444444444444")
                    var intent = Intent(context, ListTruyenActivity::class.java)
                    intent.putExtra("urlTheLoai", item.urlTheLoai)
                    intent.putExtra("nameTheLoai", item.nameTheLoai)
                    context.startActivity(intent)
                    println("5555555555555555555555555555555555555555555555555")
                }
            }

        })
    }

    override fun getItemCount(): Int {
        return this.listTheLoai.size
    }
}

class ViewHolderTheLoai(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
    View.OnLongClickListener {
    var txtTheLoai: TextView = itemView.findViewById(R.id.txt_theloai)

    lateinit var itemClick: ItemClickListener

    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClick = itemClickListener
    }

    override fun onClick(v: View?) {
        println("111111111111111111111111111111111")
        this.itemClick.setOnClick(v, adapterPosition, false)
        println("2222222222222222222222222222222222222222")
    }

    override fun onLongClick(v: View?): Boolean {
        this.itemClick.setOnClick(v, adapterPosition, true)
        return true
    }
}