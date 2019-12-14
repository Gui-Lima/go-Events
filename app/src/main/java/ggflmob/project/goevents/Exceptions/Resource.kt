package ggflmob.project.goevents.Exceptions

sealed class Resource<T> {
    data class Complete<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    data class Error<T>(val error: Errors) : Resource<T>()
}