package assignment.demoapplication.com.mvvmarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

abstract class BaseFragment : dagger.android.support.DaggerFragment() {
    private var activity: AppCompatActivity? = null

    @LayoutRes
    protected abstract fun layoutRes(): Int

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity = getActivity() as AppCompatActivity?
    }

    fun getBaseActivity(): AppCompatActivity? {
        return activity
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }
}