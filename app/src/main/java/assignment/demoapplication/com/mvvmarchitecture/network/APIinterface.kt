package assignment.demoapplication.com.mvvmarchitecture.network

import androidx.lifecycle.LiveData
import assignment.demoapplication.com.mvvmarchitecture.sample.model.SampleData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterface {
    @GET("facts/random?animal_type=cat")
    fun getCats(@Query("amount") amount: String): Observable<List<SampleData>>
}