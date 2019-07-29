package assignment.demoapplication.com.mvvmarchitecture.network

import assignment.demoapplication.com.mvvmarchitecture.model.ResponseWrapper
import assignment.demoapplication.com.mvvmarchitecture.util.Constants.Companion.GET_METHOD
import assignment.demoapplication.com.mvvmarchitecture.util.Constants.Companion.POST_METHOD
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIinterface {

    @POST("{url}")
    fun postMethod(
        @Path(
            value = "url",
            encoded = true
        ) methodUrl: String, @Body loginRequestObj: Any
    ): Observable<ResponseWrapper>

    @GET("{url}")
    fun getMethod(@Path(value = "url", encoded = true) methodUrl: String): Observable<ResponseWrapper>


    companion object {
        fun callBack(
            apiCallInterface: APIinterface?,
            apiMethod: Int,
            url: String,
            requestObj: Any
        ): Observable<ResponseWrapper>? {
            var call: Observable<ResponseWrapper>? = null
            when (apiMethod) {
                GET_METHOD -> call = apiCallInterface?.getMethod(url)
                POST_METHOD -> call = apiCallInterface?.postMethod(url, requestObj)
            }
            return call
        }
    }

}