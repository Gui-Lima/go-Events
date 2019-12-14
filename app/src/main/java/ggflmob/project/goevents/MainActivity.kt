package ggflmob.project.goevents

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import ggflmob.project.goevents.Adapters.ScreenSlidePagerAdapter
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import org.osmdroid.config.Configuration
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.view.iterator
import ggflmob.project.goevents.Models.User


class MainActivity : FragmentActivity() {

    private lateinit var mPager: ViewPager
    private lateinit var pagerAdapter: ScreenSlidePagerAdapter
    private lateinit var userLoggedIn : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ctx = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        userLoggedIn = intent.getSerializableExtra("UserLoggedIn") as User

        setContentView(R.layout.view_pager)

        mPager = findViewById(R.id.pager)
        pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        pagerAdapter.setContent()
        mPager.adapter = pagerAdapter
        val navigation = findViewById<BottomNavigationView>(R.id.bnv_mainbottomnav)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                var i = 0
                navigation.menu.iterator().forEach {
                    if (i == position) {
                        it.setChecked(true)
                    }
                    i++
                }
            }
        })

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.i_feed -> {
                mPager.setCurrentItem(0 ,true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.i_event -> {
                mPager.setCurrentItem(1 ,true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.i_trade -> {
                mPager.setCurrentItem(2 ,true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.i_profile -> {
                mPager.setCurrentItem(3, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

}