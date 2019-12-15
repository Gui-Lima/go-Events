package ggflmob.project.goevents.Models

import java.util.*

class GroupListItem(name : String, ownerName: String, id: UUID, ownerId : UUID) {
    private var ownerName = ownerName
    private var name = name
    private var id  = id
    private var ownerId =  ownerId

    fun getOwnerName() : String{
        return ownerName
    }

    fun getName(): String {
        return name
    }

    fun getId(): UUID = id

    fun getOwnerId() = ownerId
 }