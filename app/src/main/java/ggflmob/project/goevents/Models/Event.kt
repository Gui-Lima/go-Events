package ggflmob.project.goevents.Models

import java.util.UUID

class Event {
    var name: String? = null
    var uuid: UUID? = null
    var lat: Int = 0
    var log: Int = 0
    var group_id: UUID? = null
    var group_name: String? = null

    constructor(
        name: String, lat: Int, log: Int, group_id: UUID, group_name : String
    ) {
        this.name = name
        this.uuid = UUID.randomUUID()
        this.lat = lat
        this.log = log
        this.group_id = group_id
        this.group_name = group_name
    }

    constructor() {}
}
