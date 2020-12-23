package com.macrohard.bargraphlibrary.adapters

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.macrohard.bargraphlibrary.R
import com.macrohard.bargraphlibrary.animation.Easing
import com.macrohard.bargraphlibrary.models.BarDataModel
import kotlinx.android.synthetic.main.bar_row.view.*

/**
 * RecyclerView Adapter
 */
class GraphBarAdapter constructor(private val context: Context, private val items: ArrayList<BarDataModel>, var cellWidth: Int = 0, var cellHeight: Int = 0, var maxBar: Int = 0) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // Customizations
    var barBgColor = R.color.blue_dark
    var barBgDrawable: Drawable

    init {
        barBgDrawable = context.resources.getDrawable(R.drawable.white_rounded_bg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.bar_row, parent, false)
        val lp: GridLayoutManager.LayoutParams = v.getLayoutParams() as GridLayoutManager.LayoutParams
        lp.width = parent.measuredWidth / items.size
        v.layoutParams = lp
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try{
            val item = items[position]
            val holder : MyViewHolder = holder as MyViewHolder

            val perPixel = cellHeight.toDouble()/maxBar
            holder.viewBar.layoutParams.height = (perPixel*item.barValue).toInt()
            holder.viewBar.setBackgroundDrawable(barBgDrawable)

            holder.dataLabel.text = item.dataLabel

            val animatorY = ObjectAnimator.ofFloat(holder.viewBar, View.SCALE_Y, 0f, 1f)
            animatorY.interpolator = Easing.Linear
            animatorY.duration = 4000
            animatorY.start()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dataLabel = view.data_label
        val viewBar = view.view_bar

    }
}