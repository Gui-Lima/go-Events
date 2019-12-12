package ggflmob.project.goevents.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import ggflmob.project.goevents.Adapters.EventsListRecyclerAdapter
import ggflmob.project.goevents.MapActivity
import ggflmob.project.goevents.Models.EventListItem
import ggflmob.project.goevents.R

class EventFragment : Fragment() {

    private lateinit var eventList: RecyclerView
    private lateinit var rcAdapter:  EventsListRecyclerAdapter
    private lateinit var rcManager:  RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.events_fragment, container, false)

        rcManager = LinearLayoutManager(context)
        rcAdapter = EventsListRecyclerAdapter(loadListItens())

        eventList = view.findViewById<RecyclerView>(R.id.rv_events).apply {
            layoutManager = rcManager
            adapter = rcAdapter

        }

        val bt_map = view.findViewById<MaterialButton>(R.id.bt_map)
        bt_map.setOnClickListener{
            activity?.startActivity(Intent(context, MapActivity::class.java))
        }

        return view
    }


    private fun loadListItens() : ArrayList<EventListItem>{
        var itens = ArrayList<EventListItem>()
        itens.add(EventListItem("oi", "oi"))
        itens.add(EventListItem("oi", "oi"))
        return itens
    }

    companion object {
        fun newInstance(): EventFragment = EventFragment()
    }


}