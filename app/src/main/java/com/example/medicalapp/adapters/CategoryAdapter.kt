package com.example.medicalapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.R


class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    var list : List<String> ?= null
    var onTapClick : OnTapClick ?=null
    var selectedItem = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tab,parent,false)
        )
    }
    override fun getItemCount(): Int {
        return list?.size!!
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val type = list?.get(position)

        holder.apply {
            textType.text = type
            if(selectedItem == position) {
                textType.setBackgroundResource(R.drawable.background_selected)
                textType.setTextColor(R.color.white)

            }else{
                textType.setBackgroundResource(R.drawable.item_tab_background)
                textType.setTextColor(R.color.white)

            }

        }
    }





    inner class Holder ( view: View):RecyclerView.ViewHolder(view) {

        val textType: TextView = view.findViewById(R.id.text_type)

        init {
            itemView.setOnClickListener {
                val previousSelected = selectedItem
                selectedItem = adapterPosition
                notifyItemChanged(previousSelected)
                notifyItemChanged(selectedItem)
            }


        }
    }

    interface OnTapClick {
        fun onClick (type  :String)
    }
}