package ggflmob.project.goevents

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ggflmob.project.goevents.Exceptions.Resource
import ggflmob.project.goevents.Models.Event
import ggflmob.project.goevents.Models.Group
import ggflmob.project.goevents.Models.GroupListItem
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.Viewmodels.GroupViewModel

class GroupProfileActivity : AppCompatActivity() {

    lateinit var groupName : TextView
    lateinit var ownerName : TextView
    lateinit var btnCreateEvent : Button
    lateinit var group : Group
    lateinit var userLoggedIn : User
    lateinit var groupProfileViewModel : GroupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_profile)

        group = Group.fromJson(intent.getStringExtra("Group"))

        groupName = findViewById(R.id.tv_group_name)
        ownerName = findViewById(R.id.tv_group_ownername)
        btnCreateEvent = findViewById(R.id.btn_create_event)

        val preferences = this.getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
        userLoggedIn = User.fromJson(preferences.getString("session","")!!)

        if(userLoggedIn.id!! == group.ownerid){
            btnCreateEvent.visibility = View.VISIBLE
        }

        groupName.text = group.name
        ownerName.text = group.ownername

        groupProfileViewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        groupProfileViewModel.eventContent.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    Toast.makeText(this@GroupProfileActivity, "Event Created With Success", Toast.LENGTH_LONG).show()
                }
                is Resource.Error -> {
                    Toast.makeText(this@GroupProfileActivity, "Error", Toast.LENGTH_LONG).show()
                }
            }
        })

        btnCreateEvent.setOnClickListener {
            val event = Event("new Event", 10, 10, group.id!!, group.name!!)
            groupProfileViewModel.createEvent(event)
        }
    }
}
