package ggflmob.project.goevents.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ggflmob.project.goevents.Models.GroupListItem
import ggflmob.project.goevents.R

class GroupListRecyclerAdapter(private var myDataSet: ArrayList<GroupListItem>, val clickListener: (GroupListItem) -> Unit) :  RecyclerView.Adapter<GroupListRecyclerAdapter.RecyclerListViewHolder>() {



    class RecyclerListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name : TextView? = null
        var ownerName : TextView? = null

        fun bind(part: GroupListItem, clickListener: (GroupListItem) -> Unit) {
            itemView.setOnClickListener { clickListener(part)}
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GroupListRecyclerAdapter.RecyclerListViewHolder {
        val groupItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.group_recycler_item, parent, false) as View

        var recHolder = GroupListRecyclerAdapter.RecyclerListViewHolder(groupItem)
        recHolder.ownerName = groupItem.findViewById(R.id.tv_ownername)
        recHolder.name = groupItem.findViewById(R.id.tv_name)

        return recHolder
    }

    override fun getItemCount(): Int {
        return myDataSet.size
    }

    override fun onBindViewHolder(
        holder: GroupListRecyclerAdapter.RecyclerListViewHolder,
        position: Int
    ) {
        holder.name?.text = this.myDataSet[position].getName()
        holder.ownerName?.text = this.myDataSet[position].getOwnerName()
        (holder as RecyclerListViewHolder).bind(myDataSet[position], clickListener)
    }

    fun updateDataSet(dataset : ArrayList<GroupListItem>){
        myDataSet = dataset
        notifyDataSetChanged()
    }
}