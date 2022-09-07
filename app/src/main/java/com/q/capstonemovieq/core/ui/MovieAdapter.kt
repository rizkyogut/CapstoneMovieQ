package com.q.capstonemovieq.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.q.capstonemovieq.R
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.utils.setImage
import com.q.capstonemovieq.databinding.ItemListMovieBinding
import java.util.ArrayList

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemListMovieBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                data.posterPath.let { ivPicMovie.setImage(it) }
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate
                tvPopularityMovie.text = data.popularity.toString()
                tvRating.text = data.voteAverage.toString()
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)

        val ivBookmark = holder.binding.imgBookmark
        if (data.isFavorite) {
            ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable.ic_baseline_bookmark))
        } else {
            ivBookmark.setImageDrawable(ContextCompat.getDrawable(ivBookmark.context, R.drawable.ic_baseline_bookmark_border))
        }
    }

    override fun getItemCount(): Int = listData.size

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldUser: Movie, newUser: Movie): Boolean {
                    return oldUser.title == newUser.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: Movie, newUser: Movie): Boolean {
                    return oldUser == newUser
                }
            }
    }
}