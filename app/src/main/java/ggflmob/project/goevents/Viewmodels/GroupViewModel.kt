package ggflmob.project.goevents.Viewmodels

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ggflmob.project.goevents.Models.Group
import ggflmob.project.goevents.Repository.ContentRepository
import ggflmob.project.goevents.Repository.ServiceRepository

class GroupViewModel : ViewModel() {
    private val contentRepository : ContentRepository = ContentRepository()
    private val groupStatus = contentRepository.groupListStatus
    val content = Transformations.map(groupStatus){it}


    fun getGroupsList(username : String) = this.contentRepository.getGroupList(username)

}