package ggflmob.project.goevents.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class DataUser(

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("uuid")
    var uuid: UUID,

    @Expose
    @SerializedName("username")
    var username: String,

    @Expose
    @SerializedName("password")
    var password: String
)