package com.ahmed.orangechallenge.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.orangechallenge.databinding.ItemTextBinding

class TextAdapter() : RecyclerView.Adapter<TextAdapter.MyViewHolder>() {

    private var dataList = ArrayList<String>()

    fun submitList(list: List<String>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(private val binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(txt: String) {
            binding.apply {
                name.text = txt
            }
        }
    }


}