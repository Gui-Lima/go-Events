package ggflmob.project.goevents.Models

import android.graphics.Bitmap

class FeedListItem(title: String, body: String) {

    private var title : String = title
    private var body : String = body
    private lateinit var image : Bitmap

    fun getTitle(): String { return this.title}
    fun getBody(): String {return this.body}

}