package ggflmob.project.goevents.Viewmodels

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ggflmob.project.goevents.Models.Event
import ggflmob.project.goevents.Models.Group
import ggflmob.project.goevents.Repository.ContentRepository
import ggflmob.project.goevents.Repository.ServiceRepository

class GroupViewModel : ViewModel() {
    private val contentRepository : ContentRepository = ContentRepository()
    private val serviceRepository : ServiceRepository = ServiceRepository()

    private val groupStatus = contentRepository.groupListStatus
    val content = Transformations.map(groupStatus){it}

    private val eventCreationStatus = contentRepository.eventListStatus
    val eventContent = Transformations.map(eventCreationStatus){it}

    fun getGroupsList(username : String) = this.contentRepository.getGroupList(username)

    fun createGroup(group: Group) = this.serviceRepository.createGroup(group)

    fun joinGroup(username: String, groupName : String) = this.serviceRepository.joinGroup(username, groupName)

    fun createEvent(event : Event) = this.serviceRepository.createEvent(event)

}