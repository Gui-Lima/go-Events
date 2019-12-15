package ggflmob.project.goevents.Models

import com.google.gson.Gson
import java.io.Serializable
import java.util.ArrayList
import java.util.UUID

class Group : Serializable{
    var name: String? = null
    var id: UUID? = null
    var ownerid: UUID? = null
    var ownername : String? = null
    var events: ArrayList<Event>? = null

    constructor(name: String, ownerId: UUID, ownerName : String) {
        this.name = name
        this.ownerid = ownerId
        this.id = UUID.randomUUID()
        this.ownername = ownerName
        this.events = ArrayList<Event>()
    }

    constructor(id:UUID, name: String, ownerId: UUID, ownerName: String){
        this.name = name
        this.id = id
        this.ownerid = ownerId
        this.ownername = ownerName
    }

    constructor() {}

    companion object{
        fun toJson(group: Group): String {
            var gson : Gson = Gson()
            var stringedGroup = gson.toJson(group)
            return stringedGroup
        }
        fun fromJson(user : String) : Group{
            var gson : Gson = Gson()
            var group = gson.fromJson(user, Group::class.java)
            return group
        }
    }
}
