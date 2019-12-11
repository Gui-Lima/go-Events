package ggflmob.project.goevents.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ggflmob.project.goevents.Adapters.RecyclerListViewAdapter
import ggflmob.project.goevents.Models.EventListItem
import ggflmob.project.goevents.R

class EventFragment : Fragment() {

    private lateinit var eventList: RecyclerView
    private lateinit var rcAdapter:  RecyclerListViewAdapter
    private lateinit var rcManager:  RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.events_fragment, container, false)

        rcManager = LinearLayoutManager(context)
        rcAdapter = RecyclerListViewAdapter(loadListItens())

        eventList = view.findViewById<RecyclerView>(R.id.rv_events).apply {
            layoutManager = rcManager
            adapter = rcAdapter

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