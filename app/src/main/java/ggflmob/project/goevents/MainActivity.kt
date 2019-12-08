package ggflmob.project.goevents

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import ggflmob.project.goevents.Adapters.ScreenSlidePagerAdapter

class MainActivity : FragmentActivity() {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_pager)

        mPager = findViewById(R.id.pager)

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        pagerAdapter.setContent()
        mPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

}