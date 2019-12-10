package ggflmob.project.goevents

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import ggflmob.project.goevents.Adapters.ScreenSlidePagerAdapter
import android.preference.PreferenceManager
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import org.osmdroid.config.Configuration

class MainActivity : FragmentActivity() {

    private lateinit var mPager: ViewPager
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ctx = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        setContentView(R.layout.view_pager)

        mPager = findViewById(R.id.pager)
        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
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