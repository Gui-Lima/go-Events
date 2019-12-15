package ggflmob.project.goevents.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ggflmob.project.goevents.Fragments.FeedFragment
import ggflmob.project.goevents.Fragments.EventFragment
import ggflmob.project.goevents.Fragments.ProfileFragment
import ggflmob.project.goevents.Fragments.WondertradeFragment
import ggflmob.project.goevents.Models.User

class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    private val NUM_PAGES = 4
    private val pages = ArrayList<Fragment>()
    private lateinit var userLoggedIn : User

    fun setContent() {
        this.pages.add(FeedFragment())
        this.pages.add(EventFragment())
        this.pages.add(WondertradeFragment())
        this.pages.add(ProfileFragment())
    }

    override fun getCount(): Int {
        return this.NUM_PAGES
    }

    override fun getItem(position: Int): Fragment? {
        return this.pages[position]
    }

}