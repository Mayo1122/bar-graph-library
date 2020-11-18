package com.macrohard.bargraphlibrary.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.macrohard.bargraphlibrary.R
import kotlinx.android.synthetic.main.x_axis_label_row.view.*

/**
 * RecyclerView Adapter; AxisLabelAdapter
 */
class AxisLabelAdapter constructor(private val context: Context, private val items: List<String>, val orientation: Int, var cellWidth: Int = 0,var cellHeight: Int = 0) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var v = LayoutInflater.from(context).inflate(R.layout.y_axis_label_row, parent, false)
        val lp: GridLayoutManager.LayoutParams = v.getLayoutParams() as GridLayoutManager.LayoutParams
        if (orientation == RecyclerView.HORIZONTAL){
            v = LayoutInflater.from(context).inflate(R.layout.x_axis_label_row, parent, false)

            lp.width = cellWidth / items.size
            lp.height = cellHeight
        }else{
            lp.height = cellHeight / items.size
            lp.width = cellWidth
        }
        v.layoutParams = lp

        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try{
            val holder : MyViewHolder = holder as MyViewHolder
            holder.title.text = items[position]
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title
    }
}