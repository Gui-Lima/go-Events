package ggflmob.project.goevents.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class DataEvent(

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("uuid")
    val uuid: UUID,

    @Expose
    @SerializedName("lat")
    val lat: Int,

    @Expose
    @SerializedName("long")
    val log: Int,

    @Expose
    @SerializedName("group_id")
    val group_id: UUID,

    @Expose
    @SerializedName("group_name")
    val group_name: String
)
