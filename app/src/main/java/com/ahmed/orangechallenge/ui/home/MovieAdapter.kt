package com.ahmed.orangechallenge.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.domain.model.movie.Movie
import com.ahmed.orangechallenge.databinding.ItemMovieBinding
import java.util.Locale

class MovieAdapter(
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    private var dataList = ArrayList<Movie>()
    private var dataListTotal = ArrayList<Movie>()

    fun submitList(list: List<Movie>) {
        dataList.clear()
        dataList.addAll(list)
        dataListTotal.clear()
        dataListTotal.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

    inner class MyViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                title.text = movie.title
                years.text = movie.year.toString()
                rate.text = "Rating : ${movie.rating.toString()}"
                root.setOnClickListener {
                    onItemClick(movie)
                }
            }
        }
    }

    fun getFilter(): Filter {
        return movieFilter
    }

    private val movieFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Movie> = ArrayList()
            if (constraint.isNullOrEmpty()) {
                dataList.let { filteredList.addAll(dataListTotal) }
            } else {
                val query = constraint.toString().trim().toLowerCase()
                dataListTotal.forEach {
                    if (it.title?.toLowerCase(Locale.ROOT)?.contains(query) == true) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                dataList.clear()
                dataList.addAll(results.values as ArrayList<Movie>)
                notifyDataSetChanged()
            }
        }
    }

}