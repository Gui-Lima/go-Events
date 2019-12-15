package ggflmob.project.goevents.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ggflmob.project.goevents.GroupActivity
import ggflmob.project.goevents.MapActivity
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.R

class ProfileFragment : Fragment() {

    lateinit var userLoggedIn : User
    lateinit var username : TextView
    lateinit var name : TextView
    lateinit var manageGroup : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View?{
        var View = inflater.inflate(R.layout.profile_fragment, container, false)
        var preferences = this.activity!!.getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
        userLoggedIn = User.fromJson(preferences.getString("session","")!!)

        username = View.findViewById(R.id.tv_username)
        name = View.findViewById(R.id.tv_name)

        username.text = userLoggedIn.username
        name.text = userLoggedIn.name


        manageGroup = View.findViewById(R.id.btn_managegroups)
        manageGroup.setOnClickListener {
            activity?.startActivity(Intent(context, GroupActivity::class.java))
        }

        return View
    }


    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}