package ggflmob.project.goevents.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ggflmob.project.goevents.Models.EventListItem
import ggflmob.project.goevents.R

class EventsListRecyclerAdapter(private val myDataset: ArrayList<EventListItem>) : RecyclerView.Adapter<EventsListRecyclerAdapter.RecyclerListViewHolder>() {


    class RecyclerListViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var title : TextView? = null
        var body : TextView? = null
        var image : ImageView? = null
    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerListViewHolder {
            val eventItem = LayoutInflater.from(parent.context)
                .inflate(R.layout.event_recycler_item, parent, false) as View

            var recHolder = RecyclerListViewHolder(eventItem)
            recHolder.body = eventItem.findViewById(R.id.tv_eventbody)
            recHolder.title = eventItem.findViewById(R.id.tv_eventtitle)

            return recHolder
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            holder.title?.text = this.myDataset[position].getTitle()
            holder.body?.text = this.myDataset[position].getBody()
        }

        override fun getItemCount() = myDataset.size

}