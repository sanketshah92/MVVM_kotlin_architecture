package assignment.demoapplication.com.mvvmarchitecture.sample.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import assignment.demoapplication.com.mvvmarchitecture.repository.SampleRepository
import assignment.demoapplication.com.mvvmarchitecture.sample.model.SampleData
import javax.inject.Inject

open class SampleViewModel @Inject constructor(private val repository: SampleRepository) : ViewModel() {
    private var fetchedData: MutableLiveData<List<SampleData>> = MutableLiveData()

    var processedData: MutableLiveData<List<SampleData>> = MutableLiveData()

    fun fetchSampleData() {
        repository.getCats(fetchedData)
        fetchedData.observeForever(Observer {
            Log.e("Sample Data Processing", "::->" + it.size)
            processedData.postValue(it)
        })
    }

     fun tearDown() {
        repository.flush()
    }

}