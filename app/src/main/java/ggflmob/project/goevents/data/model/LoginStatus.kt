package ggflmob.project.goevents.data.model

import com.google.gson.annotations.SerializedName


enum class LoginStatus {
    @SerializedName("logged")
    LOGGED,
    @SerializedName("unknown")
    UNKNOWN,
    @SerializedName("pending")
    PENDING
}