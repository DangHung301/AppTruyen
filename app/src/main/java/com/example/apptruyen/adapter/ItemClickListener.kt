package com.example.apptruyen.adapter

import android.view.View

interface ItemClickListener {
    fun setOnClick(view : View?, position : Int, isLongClick : Boolean)
}