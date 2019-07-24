package assignment.demoapplication.com.mvvmarchitecture.sample.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import assignment.demoapplication.com.mvvmarchitecture.R
import assignment.demoapplication.com.mvvmarchitecture.base.BaseFragment
import assignment.demoapplication.com.mvvmarchitecture.databinding.FragmentSampleBinding
import assignment.demoapplication.com.mvvmarchitecture.sample.viewmodel.SampleViewModel
import assignment.demoapplication.com.mvvmarchitecture.util.ViewModelFactory
import javax.inject.Inject
import androidx.databinding.BindingAdapter
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class SampleFragment : BaseFragment() {
    override fun layoutRes(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var binding: FragmentSampleBinding? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val sampleViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SampleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_sample,
            container,
            false
        )
        return binding?.let { fragmentSampleBinding -> binding!!.root }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sampleViewModel.fetchSampleData()
        sampleViewModel.processedData.observe(
            this,
            Observer { Log.e("Observed Result", ":" + it?.let { it -> it[0].getId() }) })

    }

    override fun onDetach() {
        sampleViewModel.tearDown()
        super.onDetach()
    }

    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        val requestOptions = RequestOptions()
//        requestOptions.placeholder(assignment.demoapplication.com.mvvmarchitecture.R.mipmap.ic_launcher)
//        requestOptions.error(assignment.demoapplication.com.mvvmarchitecture.R.mipmap.ic_launcher)
//        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE)
        if (url != null && (url.contains("https://") || url.contains("http://"))) {
            Glide.with(imageView.getContext()).setDefaultRequestOptions(requestOptions).load(url).into(imageView)
        }
    }
}