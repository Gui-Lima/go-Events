package ggflmob.project.goevents.Models

import java.util.ArrayList
import java.util.UUID

class Group {
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

    constructor() {}
}
