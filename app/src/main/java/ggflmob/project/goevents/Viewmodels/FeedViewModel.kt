package ggflmob.project.goevents.Viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ggflmob.project.goevents.Models.FeedListItem
import ggflmob.project.goevents.Repository.ContentRepository

class FeedViewModel : ViewModel() {

    var feedItens : MutableLiveData<ArrayList<FeedListItem>> = MutableLiveData()
    var contentRepository : ContentRepository = ContentRepository()

    public fun getItens() : LiveData<ArrayList<FeedListItem>> {
        return feedItens
    }

    public fun loadItens() {
    }

}