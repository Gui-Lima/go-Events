package ggflmob.project.goevents.Models

class GroupListItem(name : String, ownerName: String) {
    private var ownerName = ownerName
    private var name = name

    fun getOwnerName() : String{
        return ownerName
    }

    fun getName(): String {
        return name
    }
}