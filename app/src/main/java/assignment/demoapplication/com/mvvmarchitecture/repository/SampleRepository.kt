package assignment.demoapplication.com.mvvmarchitecture.repository


import androidx.lifecycle.MutableLiveData
import assignment.demoapplication.com.mvvmarchitecture.CreateUser
import assignment.demoapplication.com.mvvmarchitecture.base.BaseRepository
import assignment.demoapplication.com.mvvmarchitecture.model.ResponseWrapper
import assignment.demoapplication.com.mvvmarchitecture.network.APIinterface
import assignment.demoapplication.com.mvvmarchitecture.util.Constants.Companion.POST_METHOD
import javax.inject.Inject


class SampleRepository @Inject constructor(val service: APIinterface) : BaseRepository() {

    private lateinit var requestedData: MutableLiveData<ResponseWrapper>
    fun getCats(requestedData: MutableLiveData<ResponseWrapper>) {
        this.requestedData = requestedData
        val createUser = CreateUser("qwerty12653", salary = "2525", age = "25")
        networkCall(POST_METHOD, "create", createUser)
    }

    fun flush() {
        if (!disposable!!.isDisposed) {
            disposable!!.dispose()
            disposable = null
        }
    }

    override fun handleResponse(responseObj: Any) {
        if (responseObj is ResponseWrapper)
            requestedData.value = responseObj
            print("$responseObj  ")
    }

    override fun handleError(error: Throwable) {
        print("$error  ")
    }
}