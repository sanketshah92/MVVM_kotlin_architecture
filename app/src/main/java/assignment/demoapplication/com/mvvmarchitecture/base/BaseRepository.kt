package assignment.demoapplication.com.mvvmarchitecture.base

import assignment.demoapplication.com.mvvmarchitecture.network.APIinterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open abstract class BaseRepository {

    @Inject
    lateinit var apIinterface: APIinterface
    internal var disposable: Disposable? = null

    fun networkCall(
        apiMethod: Int,
        url: String,
        requestObj: Any
    ) {
        APIinterface.callBack(apIinterface, apiMethod, url, requestObj)?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::handleResponse, this::handleError)
    }

    abstract fun handleResponse(responseObj: Any)

    abstract fun handleError(error: Throwable)

}