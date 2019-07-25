package assignment.demoapplication.com.mvvmarchitecture.sample.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import assignment.demoapplication.com.mvvmarchitecture.databinding.ItemSampleBinding
import assignment.demoapplication.com.mvvmarchitecture.sample.view.viewholders.SampleItemViewHolder
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import assignment.demoapplication.com.mvvmarchitecture.R
import assignment.demoapplication.com.mvvmarchitecture.sample.model.SampleData


class SampleAdapter constructor(private val data: List<SampleData>) : RecyclerView.Adapter<SampleItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleItemViewHolder {
        var itemFeedBinding: ItemSampleBinding? = null
        itemFeedBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sample,
            parent,
            false
        )
        return SampleItemViewHolder(itemFeedBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SampleItemViewHolder, position: Int) {
        holder.bindData(data[position])
    }

}