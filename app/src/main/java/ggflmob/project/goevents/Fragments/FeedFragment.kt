package ggflmob.project.goevents.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ggflmob.project.goevents.Adapters.EventsListRecyclerAdapter
import ggflmob.project.goevents.Adapters.FeedListRecyclerAdapter
import ggflmob.project.goevents.Models.EventListItem
import ggflmob.project.goevents.Models.FeedListItem
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.R

class FeedFragment : Fragment(){

    private lateinit var eventList: RecyclerView
    private lateinit var rcAdapter:  FeedListRecyclerAdapter
    private lateinit var rcManager:  RecyclerView.LayoutManager
    lateinit var userLoggedIn : User


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.feed_fragment, container, false)

        rcManager = LinearLayoutManager(context)
        rcAdapter = FeedListRecyclerAdapter(loadListItens())

        var preferences = this.activity!!.getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
        userLoggedIn = User.fromJson(preferences.getString("session","")!!)

        eventList = view.findViewById<RecyclerView>(R.id.rv_feed).apply {
            layoutManager = rcManager
            adapter = rcAdapter

        }

        return view
    }



    private fun loadListItens() : ArrayList<FeedListItem>{
        var itens = ArrayList<FeedListItem>()
        itens.add(FeedListItem("Noticia1", "Algo aconteceu em algum grupo1"))
        itens.add(FeedListItem("Noticia2", "Algo aconteceu em algum grupo1"))
        itens.add(FeedListItem("Noticia3", "Algo aconteceu em algum grupo1"))
        return itens
    }

    companion object {
        fun newInstance(): FeedFragment = FeedFragment  ()
    }
}