package assignment.demoapplication.com.mvvmarchitecture.model

data class ResponseWrapper(
    var error: Error,
    var age :Int,
    var name: String,
    var salary: String,
    var id: String
)
