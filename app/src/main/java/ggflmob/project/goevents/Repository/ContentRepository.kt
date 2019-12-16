package ggflmob.project.goevents.Repository

import androidx.lifecycle.MutableLiveData
import ggflmob.project.goevents.Api.ApiClient
import ggflmob.project.goevents.Resources.Errors
import ggflmob.project.goevents.Resources.Resource
import ggflmob.project.goevents.Models.*
import ggflmob.project.goevents.data.model.DataEvent
import ggflmob.project.goevents.data.model.DataGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentRepository {
    val groupListStatus  = MutableLiveData<Resource<ArrayList<Group>>>()
    val feedListStatus = MutableLiveData<Resource<ArrayList<FeedListItem>>>()
    val eventListStatus = MutableLiveData<Resource<ArrayList<Event>>>()

    fun getGroupList(username: String){
        groupListStatus.value = Resource.Loading()
        val call: Call<ArrayList<DataGroup>> = ApiClient.getClient.getGroupsByUsername(username)
        call.enqueue(object: Callback<ArrayList<DataGroup>> {
            override fun onFailure(call: Call<ArrayList<DataGroup>>, t: Throwable) {
                groupListStatus.value = Resource.Error(Errors.RESPONSE_NOT_SUCCESSFUL)
            }

            override fun onResponse(
                call: Call<ArrayList<DataGroup>>,
                response: Response<ArrayList<DataGroup>>
            ) {
                val groups = ArrayList<Group>()
                if(response.body() != null && response.isSuccessful){
                    response.body()!!.forEach {
                        val group = Group()
                        group.id = it.uuid
                        group.name = it.name
                        group.ownerid = it.ownerdId
                        group.ownername = it.ownerName
                        groups.add(group)
                    }
                    groupListStatus.value = Resource.Complete(groups)
                }
                groupListStatus.value = Resource.Error(Errors.EMPTY_RESPONSE_FROM_API)
            }
        })
    }

    fun getEventList(username : String){
        eventListStatus.value = Resource.Loading()
        val call : Call<ArrayList<DataEvent>> = ApiClient.getClient.getEventsByUsername(username)
        call.enqueue(object: Callback<ArrayList<DataEvent>>{
            override fun onFailure(call: Call<ArrayList<DataEvent>>, t: Throwable) {
                eventListStatus.value = Resource.Error(Errors.ERROR_COMMUNICATING_WITH_API)
            }

            override fun onResponse(
                call: Call<ArrayList<DataEvent>>,
                response: Response<ArrayList<DataEvent>>
            ) {
                val events = ArrayList<Event>()
                if(response.body() != null && response.isSuccessful){
                    response.body()!!.forEach{
                        val event = Event()
                        event.group_id = it.group_id
                        event.lat = it.lat
                        event.log = it.log
                        event.name = it.name
                        event.uuid = it.uuid
                        event.group_name = it.group_name
                        events.add(event)
                    }
                    eventListStatus.value = Resource.Complete(events)
                }
                eventListStatus.value = Resource.Error(Errors.EMPTY_RESPONSE_FROM_API)
            }

        })
    }


}