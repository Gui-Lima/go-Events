package ggflmob.project.goevents.Viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ggflmob.project.goevents.Models.EventListItem
import ggflmob.project.goevents.Repository.ContentRepository

class EventViewModel : ViewModel() {

    var contentRepository : ContentRepository = ContentRepository()
    private val eventStatus = contentRepository.eventListStatus
    val content = Transformations.map(eventStatus){it}


    fun loadItens(username : String) {
        this.contentRepository.getEventList(username)
    }

}