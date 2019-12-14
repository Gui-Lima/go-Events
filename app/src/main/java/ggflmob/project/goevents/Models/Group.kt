package ggflmob.project.goevents.Models

import java.util.ArrayList
import java.util.UUID

class Group {
    var name: String? = null
    var id: UUID? = null
    var ownerId: UUID? = null
    var events: ArrayList<Event>? = null

    constructor(name: String, ownerId: UUID) {
        this.name = name
        this.ownerId = ownerId
        this.id = UUID.randomUUID()
        this.events = ArrayList<Event>()
    }

    constructor() {}
}
