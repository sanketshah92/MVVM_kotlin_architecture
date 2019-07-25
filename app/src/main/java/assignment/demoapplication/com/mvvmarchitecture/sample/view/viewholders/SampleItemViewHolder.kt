package assignment.demoapplication.com.mvvmarchitecture.sample.view.viewholders

import androidx.recyclerview.widget.RecyclerView
import assignment.demoapplication.com.mvvmarchitecture.databinding.ItemSampleBinding
import assignment.demoapplication.com.mvvmarchitecture.sample.model.SampleData

class SampleItemViewHolder(private var binding: ItemSampleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(sampleData: SampleData) {
        binding.data = sampleData
    }
}
