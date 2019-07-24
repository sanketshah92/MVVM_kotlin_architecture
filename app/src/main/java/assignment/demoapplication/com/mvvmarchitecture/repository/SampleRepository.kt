package assignment.demoapplication.com.mvvmarchitecture.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import assignment.demoapplication.com.mvvmarchitecture.base.BaseRepository
import assignment.demoapplication.com.mvvmarchitecture.network.APIinterface
import assignment.demoapplication.com.mvvmarchitecture.sample.model.SampleData
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.internal.util.NotificationLite.disposable
import io.reactivex.disposables.Disposable


class SampleRepository @Inject constructor(val service: APIinterface) : BaseRepository {
    internal var disposable: Disposable? = null
    fun getCats(requestedData: MutableLiveData<List<SampleData>>) {
        service.getCats("20")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<SampleData>> {
                override fun onNext(t: List<SampleData>) {
                    requestedData.postValue(t)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }


                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {

                }
            })
    }

    fun flush() {
        if (!disposable!!.isDisposed) {
            disposable!!.dispose()
            disposable = null
        }

    }
}