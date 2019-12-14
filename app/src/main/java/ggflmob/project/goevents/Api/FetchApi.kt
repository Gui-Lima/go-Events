package ggflmob.project.goevents.Api

import ggflmob.project.goevents.data.model.DataUser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FetchApi {

    @GET("api/login/{username}/{password}")
    fun login(@Path("username") username : String, @Path("password") password:String): Call<DataUser>

    @POST("api/login/register")
    fun register(@Body user : DataUser) : Call<ResponseBody>

}