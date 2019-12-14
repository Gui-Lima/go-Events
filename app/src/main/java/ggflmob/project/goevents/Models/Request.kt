package ggflmob.project.goevents.Models

class Request {
    var path: String? = null
        private set
    var requestMethod: String? = null
        private set

    fun withPath(path: String) {
        this.path = path
    }

    fun withMethod(method: String) {
        this.requestMethod = method
    }
}