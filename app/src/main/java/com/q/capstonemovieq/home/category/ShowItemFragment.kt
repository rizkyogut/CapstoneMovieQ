package com.q.capstonemovieq.home.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.q.capstonemovieq.R
import com.q.capstonemovieq.core.constant.Tabs
import com.q.capstonemovieq.core.data.Resource
import com.q.capstonemovieq.core.domain.model.Movie
import com.q.capstonemovieq.core.ui.MovieAdapter
import com.q.capstonemovieq.databinding.FragmentShowItemBinding
import com.q.capstonemovieq.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowItemFragment : Fragment() {

    private lateinit var binding: FragmentShowItemBinding
    private val showItemViewModel: ShowItemViewModel by viewModels()

    private var fragmentType: Int? = null

    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val args = arguments
        if (args != null) {
            fragmentType = args.getInt("fragmentType", 0)
//            Timber.d(fragmentType.toString())
        }
        binding = FragmentShowItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Timber.d("Config Changed")
        setupObservers(viewLifecycleOwner, fragmentType ?: 0)


        if (activity != null) {
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }
        }
    }

    private fun setupObservers(viewLifecycleOwner: LifecycleOwner, position: Int) {
        when (position) {
            Tabs.TAB_POPULAR.tabId -> showItemViewModel.popularMovieList.observe(viewLifecycleOwner) { movie ->
                showItems(movie)
            }
            Tabs.TAB_PLAYING.tabId -> showItemViewModel.playingMovieList.observe(viewLifecycleOwner) { movie ->
                showItems(movie)
            }
            Tabs.TAB_UPCOMING.tabId -> showItemViewModel.upcomingMovieList.observe(
                viewLifecycleOwner) { movie ->
                showItems(movie)
            }
            Tabs.TAB_TOP_RATED.tabId -> showItemViewModel.topRatedMovieList.observe(
                viewLifecycleOwner) { movie ->
                showItems(movie)
            }
        }
    }

    private fun showItems(movie: Resource<List<Movie>>) {
        when (movie) {
            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
            is Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                movieAdapter.setData(movie.data)
            }
            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.viewError.root.visibility = View.VISIBLE
                binding.viewError.tvError.text =
                    movie.message ?: getString(R.string.oops_error)
            }
        }

        with(binding.rvHome) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }


    companion object {
        fun newInstance(fragmentType: Int): ShowItemFragment {
            val bundle = Bundle()
            val commonViewPagerFragment = ShowItemFragment()
            bundle.putInt("fragmentType", fragmentType)
            commonViewPagerFragment.arguments = bundle
            return commonViewPagerFragment
        }
    }
}