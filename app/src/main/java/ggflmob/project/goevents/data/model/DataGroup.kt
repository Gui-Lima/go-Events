package ggflmob.project.goevents.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class DataGroup (
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("uuid")
    val uuid: UUID,

    @Expose
    @SerializedName("ownerid")
    val ownerdId: UUID

    )