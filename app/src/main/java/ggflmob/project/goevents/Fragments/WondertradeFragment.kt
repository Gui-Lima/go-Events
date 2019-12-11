package ggflmob.project.goevents.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ggflmob.project.goevents.R

class WondertradeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.wondertrade_fragment, container, false)


    companion object {
        fun newInstance(): WondertradeFragment = WondertradeFragment()
    }
}