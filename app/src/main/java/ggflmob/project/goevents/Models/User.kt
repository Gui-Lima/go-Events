package ggflmob.project.goevents.Models
import java.io.Serializable
import java.util.ArrayList
import java.util.UUID

class User : Serializable{
    var name: String? = null
    var id: UUID? = null
    var username: String? = null
    var password: String? = null
    var groups: ArrayList<Group>? = null
    var events: ArrayList<Event>? = null

    constructor(
        name: String, username: String, password: String
    ) {
        this.name = name
        this.id = UUID.randomUUID()
        this.username = username
        this.password = password
        this.groups = ArrayList<Group>()
        this.events = ArrayList<Event>()
    }

    constructor(
         uuid: UUID, username: String, password: String, name: String
    ){
        this.name = name
        this.username = username
        this.id = uuid
        this.password = password
        this.groups = ArrayList<Group>()
        this.events = ArrayList<Event>()
    }

    constructor() {}
}
