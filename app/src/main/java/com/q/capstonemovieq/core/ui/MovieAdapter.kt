package com.q.capstonemovieq.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.q.capstonemovieq.R
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.utils.setPosterImage
import com.q.capstonemovieq.databinding.ItemListMovieBinding
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                data.posterPath.let { ivPicMovie.setPosterImage(it) }
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvPopularityMovie.text = data.popularity.toString()
                tvRating.text = data.voteAverage.toString()
            }
        }

        init {
            binding.root.setOnClickListener {
//                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

}