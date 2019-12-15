package ggflmob.project.goevents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ggflmob.project.goevents.Adapters.EventsListRecyclerAdapter
import ggflmob.project.goevents.Adapters.FeedListRecyclerAdapter
import ggflmob.project.goevents.Adapters.GroupListRecyclerAdapter
import ggflmob.project.goevents.Exceptions.Resource
import ggflmob.project.goevents.Models.Group
import ggflmob.project.goevents.Models.GroupListItem
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.Viewmodels.GroupViewModel
import ggflmob.project.goevents.Viewmodels.LoginViewModel

class GroupActivity : AppCompatActivity() {

    private lateinit var eventList: RecyclerView
    private lateinit var rcAdapter: GroupListRecyclerAdapter
    private lateinit var rcManager:  RecyclerView.LayoutManager
    private lateinit var groupViewModel : GroupViewModel
    private lateinit var joinBtn : Button
    private lateinit var createBtn : Button
    private lateinit var loading : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        rcManager = LinearLayoutManager(this)

        eventList = findViewById<RecyclerView>(R.id.rv_events).apply {
            layoutManager = rcManager
            adapter = rcAdapter

        }

        groupViewModel = ViewModelProviders.of(this).get(groupViewModel::class.java)
        groupViewModel.content.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    loading.visibility = View.INVISIBLE
                    var groupListItem = ArrayList<GroupListItem>()
                    it.data.forEach{
                        val groupItem = GroupListItem()
                    }
                    rcAdapter.updateDataSet()
                }
                is Resource.Error -> {
                    loading.visibility = View.INVISIBLE
                    Toast.makeText(this@GroupActivity, "Ops, something went wrong", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
                }
            }
        })

    }


}
