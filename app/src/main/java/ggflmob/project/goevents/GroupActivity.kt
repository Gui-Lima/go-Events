package ggflmob.project.goevents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ggflmob.project.goevents.Adapters.GroupListRecyclerAdapter
import ggflmob.project.goevents.Dialog.CreateGroupDialog
import ggflmob.project.goevents.Dialog.JoinGroupDialog
import ggflmob.project.goevents.Resources.Resource
import ggflmob.project.goevents.Models.Group
import ggflmob.project.goevents.Models.GroupListItem
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.Viewmodels.GroupViewModel

class GroupActivity : AppCompatActivity(), CreateGroupDialog.NoticeDialogListener, JoinGroupDialog.NoticeDialogListener {

    private lateinit var groupList: RecyclerView
    private lateinit var rcAdapter: GroupListRecyclerAdapter
    private lateinit var rcManager:  RecyclerView.LayoutManager
    private lateinit var groupViewModel : GroupViewModel
    private lateinit var joinBtn : Button
    private lateinit var createBtn : Button
    private lateinit var loading : ProgressBar
    private lateinit var userLoggedIn : User
    private var groupLists = ArrayList<GroupListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_group)

        groupList = findViewById(R.id.rv_group)
        joinBtn = findViewById(R.id.btn_joingroup)
        createBtn = findViewById(R.id.btn_creategroup)
        loading = findViewById(R.id.loading)

        val preferences = this.getSharedPreferences("ggflmob.project.goevents", Context.MODE_PRIVATE)
        userLoggedIn = User.fromJson(preferences.getString("session","")!!)

        rcManager = LinearLayoutManager(this)
        rcAdapter = GroupListRecyclerAdapter(groupLists, { groupListItem : GroupListItem -> groupItemClicked(groupListItem) })

        groupList.apply {
            layoutManager = rcManager
            adapter = rcAdapter

        }

        groupViewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        groupViewModel.content.observe(this, Observer {
            when(it){
                is Resource.Complete -> {
                    loading.visibility = View.INVISIBLE
                    val groupListItem = ArrayList<GroupListItem>()
                    it.data.forEach{groupMember ->
                        val groupItem = GroupListItem(groupMember.name!!, groupMember.ownername!!, groupMember.id!!, groupMember.ownerid!!)
                        groupListItem.add(groupItem)
                    }
                    rcAdapter.updateDataSet(groupListItem)
                }
                is Resource.Loading -> {
                    loading.visibility = View.VISIBLE
                }
            }
        })

        groupViewModel.getGroupsList(userLoggedIn.username!!)


        joinBtn.setOnClickListener {
            showJoinGroupDialog()
        }

        createBtn.setOnClickListener {
            showCreateGroupDialog()
        }


    }


    fun showCreateGroupDialog() {
        val dialog = CreateGroupDialog()
        dialog.show(supportFragmentManager, "GroupDialogFragment")
    }

    fun showJoinGroupDialog(){
        val dialog = JoinGroupDialog()
        dialog.show(supportFragmentManager, "GroupDialogFragment")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        if (dialog is CreateGroupDialog ){
            val group = Group(dialog.groupNameString, userLoggedIn.id!!, userLoggedIn.name!!)
            groupViewModel.createGroup(group)
        }

        if(dialog is JoinGroupDialog){
            groupViewModel.joinGroup(userLoggedIn.username!!, dialog.groupNameString)
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {}

    private fun groupItemClicked(groupItem : GroupListItem){
        var group = Group(groupItem.getId(), groupItem.getName(), groupItem.getOwnerId() , groupItem.getOwnerName())
        val intent = Intent(this@GroupActivity,GroupProfileActivity::class.java)
        intent.putExtra("Group", Group.toJson(group))
        startActivity(intent)
    }

}
