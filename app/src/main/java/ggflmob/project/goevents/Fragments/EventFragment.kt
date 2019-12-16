package ggflmob.project.goevents.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ggflmob.project.goevents.Adapters.EventsListRecyclerAdapter
import ggflmob.project.goevents.Resources.Resource
import ggflmob.project.goevents.MapActivity
import ggflmob.project.goevents.Models.EventListItem
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.R
import ggflmob.project.goevents.Viewmodels.EventViewModel

class EventFragment : Fragment() {

    private lateinit var eventList: RecyclerView
    private lateinit var rcAdapter:  EventsListRecyclerAdapter
    private lateinit var rcManager:  RecyclerView.LayoutManager
    private lateinit var eventViewModel : EventViewModel
    private lateinit var loading : ProgressBar

    private var eventsList = ArrayList<EventListItem>()
    lateinit var userLoggedIn : User


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.events_fragment, container, false)

        rcManager = LinearLayoutManager(context)
        rcAdapter = EventsListRecyclerAdapter(eventsList)

        loading = view.findViewById(R.id.loading)

        eventList = view.findViewById<RecyclerView>(R.id.rv_events).apply {
            layoutManager = rcManager
            adapter = rcAdapter

        }

        var preferences = this.activity!!.getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
        userLoggedIn = User.fromJson(preferences.getString("session","")!!)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        eventViewModel.content.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    val eventListItem = ArrayList<EventListItem>()
                    it.data.forEach{eventMember ->
                        val eventItem = EventListItem(eventMember.name!!,eventMember.group_name!!)
                        eventListItem.add(eventItem)
                    }
                    rcAdapter.updateItemList(eventListItem)
                    loading.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    loading.visibility = View.INVISIBLE
                }
            }
        })

        val bt_map = view.findViewById<MaterialButton>(R.id.bt_map)
        bt_map.setOnClickListener{
            activity?.startActivity(Intent(context, MapActivity::class.java))
        }

        eventViewModel.loadItens(userLoggedIn.username!!)

        return view
    }


    companion object {
        fun newInstance(): EventFragment = EventFragment()
    }


}