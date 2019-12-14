package ggflmob.project.goevents.Viewmodels

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ggflmob.project.goevents.Repository.ServiceRepository

class LoginViewModel : ViewModel() {
    private val serviceRepository : ServiceRepository = ServiceRepository()
    private val loginStatus = serviceRepository.loginStatus
    val content = Transformations.map(loginStatus){it}

    fun login(username: String, password: String) = this.serviceRepository.login(username, password)

    fun register(username: String, password: String) = this.serviceRepository.register(username, password)
}