package ggflmob.project.goevents.Repository

import androidx.lifecycle.MutableLiveData
import ggflmob.project.goevents.Api.ApiClient
import ggflmob.project.goevents.Exceptions.Errors
import ggflmob.project.goevents.Exceptions.Resource
import ggflmob.project.goevents.Models.User
import ggflmob.project.goevents.data.model.DataUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ServiceRepository {

    val loginStatus  = MutableLiveData<Resource<User>>()

    fun login(username: String, password: String){
        loginStatus.value = Resource.Loading()
        val call: Call<DataUser> = ApiClient.getClient.login(username, password)
        call.enqueue(object: Callback<DataUser>{

            override fun onFailure(call: Call<DataUser>, t: Throwable) {
                loginStatus.value = Resource.Error(Errors.ERROR_COMMUNICATING_WITH_API)
            }

            override fun onResponse(call: Call<DataUser>, response: Response<DataUser>) {
                if(response.isSuccessful && response.body() != null){
                    val user = User(response.body()!!.uuid, response.body()!!.username, response.body()!!.password, response.body()!!.name)
                    loginStatus.value = Resource.Complete(user)
                }
                else{
                    loginStatus.value = Resource.Error(Errors.WRONG_CREDENTIALS)
                }
            }

        })
    }

    fun register(username: String, password: String){
        loginStatus.value = Resource.Loading()
        val user = DataUser(username, UUID.randomUUID(),username, password)
        val call : Call<ResponseBody> = ApiClient.getClient.register(user)
        call.enqueue(object: Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginStatus.value = Resource.Error(Errors.RESPONSE_NOT_SUCCESSFUL)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    login(username, password)
                }
                else{
                    loginStatus.value = Resource.Error(Errors.EMPTY_RESPONSE_FROM_API)
                }
            }

        })

    }

}