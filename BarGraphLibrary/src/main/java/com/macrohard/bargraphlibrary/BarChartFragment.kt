package com.macrohard.bargraphlibrary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.macrohard.bargraphlibrary.adapters.AxisLabelAdapter
import com.macrohard.bargraphlibrary.adapters.GraphBarAdapter
import com.macrohard.bargraphlibrary.databinding.FragmentBarChartBinding
import com.macrohard.bargraphlibrary.models.BarDataModel

/**
 * Bar chart graph with full customizations
 */
open class BarChartFragment : BaseFragment<FragmentBarChartBinding>() {
    private val TAG = "BarChartFragment"

    private lateinit var mContext: Context

    // Bars
    private var bars = ArrayList<BarDataModel>()
    private lateinit var barsAdapter: GraphBarAdapter

    // X labels
    private var xLabels = ArrayList<String>()
    private lateinit var xAdapter: AxisLabelAdapter

    // X labels
    private var yLabels = ArrayList<String>()
    private lateinit var yAdapter: AxisLabelAdapter

    override val layoutId: Int
        get() = R.layout.fragment_bar_chart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = activity!!.baseContext

        //dummyData()
        initViews()
        listeners()
    }

    private fun dummyDataBars() {
        // Data for bars
        bars.clear()
        bars.add(BarDataModel(300,1,1))
        bars.add(BarDataModel(200,1,1))
        bars.add(BarDataModel(400,1,1))
        bars.add(BarDataModel(500,1,1))
        bars.add(BarDataModel(350,1,1))
        bars.add(BarDataModel(100,1,1))
        bars.add(BarDataModel(270,1,1))
    }

    private fun dummyDataAxisX() {
        xLabels.add("Mon")
        xLabels.add("Tue")
        xLabels.add("Wed")
        xLabels.add("Thu")
        xLabels.add("Fri")
        xLabels.add("Sat")
        xLabels.add("Sun")
    }
    private fun dummyDataAxisY() {
        yLabels.add("0")
        yLabels.add("100")
        yLabels.add("200")
        yLabels.add("300")
        yLabels.add("400")
        yLabels.add("500")
        yLabels.add("600")

        yLabels.reverse()
    }

    private fun listeners() {
    }

    private fun initViews() {
        setAdapters()
    }

    private fun setAdapters() {

        // bar updates
        binding.barRecyclerView.viewTreeObserver.addOnGlobalLayoutListener{
            if (barsAdapter.cellWidth == 0){
                barsAdapter.cellWidth = binding.barRecyclerView.width
                barsAdapter.cellHeight = binding.barRecyclerView.height
                barsAdapter.maxBar = 700
                dummyDataBars()
                barsAdapter.notifyDataSetChanged()
            }
        }

        // x axis updates
        binding.xRecyclerView.viewTreeObserver.addOnGlobalLayoutListener{
            if (xAdapter.cellWidth == 0){
                xAdapter.cellWidth = binding.xRecyclerView.width
                xAdapter.cellHeight = binding.xRecyclerView.height
                dummyDataAxisX()
                xAdapter.notifyDataSetChanged()
            }
        }

        // y axis updates
        binding.yRecyclerView.viewTreeObserver.addOnGlobalLayoutListener{
            if (yAdapter.cellHeight == 0){

                yAdapter.cellHeight = binding.yRecyclerView.height
                yAdapter.cellWidth = binding.yRecyclerView.width
                dummyDataAxisY()
                yAdapter.notifyDataSetChanged()
            }
        }

        barsAdapter = GraphBarAdapter(mContext, bars,binding.barRecyclerView.layoutParams.width)
        binding.barRecyclerView.setHasFixedSize(true)
        binding.barRecyclerView.layoutManager = GridLayoutManager(context,1,
            RecyclerView.HORIZONTAL,false)
        binding.barRecyclerView.adapter = barsAdapter

        xAdapter = AxisLabelAdapter(mContext, xLabels, RecyclerView.HORIZONTAL,binding.xRecyclerView.layoutParams.width)
        binding.xRecyclerView.setHasFixedSize(true)
        binding.xRecyclerView.layoutManager = GridLayoutManager(context,1,
            RecyclerView.HORIZONTAL,false)
        binding.xRecyclerView.adapter = xAdapter

        yAdapter = AxisLabelAdapter(mContext, yLabels, RecyclerView.VERTICAL,binding.yRecyclerView.layoutParams.height)
        binding.yRecyclerView.setHasFixedSize(true)
        binding.yRecyclerView.layoutManager = GridLayoutManager(context,1,
            RecyclerView.VERTICAL,false)
        binding.yRecyclerView.adapter = yAdapter
    }

}