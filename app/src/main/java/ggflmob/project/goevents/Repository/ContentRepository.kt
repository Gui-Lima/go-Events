package ggflmob.project.goevents.Repository

import androidx.lifecycle.MutableLiveData
import ggflmob.project.goevents.Api.ApiClient
import ggflmob.project.goevents.Exceptions.Errors
import ggflmob.project.goevents.Exceptions.Resource
import ggflmob.project.goevents.Models.*
import ggflmob.project.goevents.data.model.DataGroup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContentRepository {
    val groupListStatus  = MutableLiveData<Resource<ArrayList<Group>>>()
    val feedListStatus = MutableLiveData<Resource<ArrayList<FeedListItem>>>()
    val evenvetListStatus = MutableLiveData<Resource<ArrayList<Event>>>()

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
                var groups = ArrayList<Group>()
                if(response.body() != null && response.isSuccessful){
                    response.body()!!.forEach {
                        var group = Group()
                        group.id = it.uuid
                        group.name = it.name
                        group.ownerid = it.ownerdId
                        groups.add(group)
                    }
                    groupListStatus.value = Resource.Complete(groups)
                }
                groupListStatus.value = Resource.Error(Errors.EMPTY_RESPONSE_FROM_API)
            }
        })
    }


}