package ggflmob.project.goevents.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ggflmob.project.goevents.Fragments.FeedFragment
import ggflmob.project.goevents.Fragments.MapFragment
import ggflmob.project.goevents.Fragments.WondertradeFragment

class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    private val NUM_PAGES = 3
    private val pages = ArrayList<Fragment>()


    fun setContent() {
        this.pages.add(FeedFragment())
        this.pages.add(MapFragment())
        this.pages.add(WondertradeFragment())
    }

    override fun getCount(): Int {
        return this.NUM_PAGES
    }

    override fun getItem(position: Int): Fragment? {
        return this.pages[position]
    }

}