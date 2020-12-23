package com.example.bargraphexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.macrohard.bargraphlibrary.BarChartFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = BarChartFragment()
        fragment.setBarBackgroundDrawable(resources.getDrawable(com.macrohard.bargraphlibrary.R.drawable.white_rounded_bg))
        addFirstFragment(fragment,findViewById<FrameLayout>(R.id.container_frame).id)
    }

    /**
     * This method will add Fragment to fragment manager
     *
     * @param fragment Fragment to add (First fragment of Activity)
     * @param contentId Container ID
     */
    private fun addFirstFragment(fragment: Fragment, contentId: Int) {
        fragment.retainInstance = true
        supportFragmentManager.beginTransaction()
            .add(contentId, fragment, fragment.javaClass.name)
            .commit()
    }
}