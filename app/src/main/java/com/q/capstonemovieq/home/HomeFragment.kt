package com.q.capstonemovieq.home

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.q.capstonemovieq.R
import com.q.capstonemovieq.core.constant.Tabs
import com.q.capstonemovieq.core.data.Resource
import com.q.capstonemovieq.core.ui.MovieAdapter
import com.q.capstonemovieq.core.ui.MovieCategoryAdapter
import com.q.capstonemovieq.databinding.FragmentHomeBinding
import com.q.capstonemovieq.detail.DetailMovieActivity
import com.q.capstonemovieq.home.category.ShowItemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieCategoryAdapter: MovieCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        movieCategoryAdapter = MovieCategoryAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (activity != null){
//
//            val movieAdapter = MovieAdapter()
//
//            homeViewModel.movie.observe(viewLifecycleOwner) { movie ->
//                if (movie != null) {
//                    when (movie) {
//                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
//                        is Resource.Success -> {
//                            binding.progressBar.visibility = View.GONE
//                            movieAdapter.setData(movie.data)
//                        }
//                        is Resource.Error -> {
//                            binding.progressBar.visibility = View.GONE
//                            binding.viewError.root.visibility = View.VISIBLE
//                            binding.viewError.tvError.text =
//                                movie.message ?: getString(R.string.oops_error)
//                        }
//                    }
//                }
//            }
//
//        }

        setupView()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        binding.appBar.tabLayout.getTabAt(Tabs.TAB_POPULAR.tabId)
            ?.select() // Select first tab of viewpager

        TabLayoutMediator(binding.appBar.tabLayout, binding.viewPager) { tab, position ->
            tab.text = Tabs.getTabById(position)!!.tabName
        }.attach()
    }

    private fun setupView() {
        binding.viewPager.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 3
            adapter = movieCategoryAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}