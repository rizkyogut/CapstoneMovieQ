package com.q.capstonemovieq.core.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.q.capstonemovieq.core.constant.Tabs

class MovieCategoryAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        val tab = Tabs.getTabById(position)
        return if (tab != null) {
            MovieViewPagerFragment.newInstance(tab.tabId)
        } else {
            MovieViewPagerFragment.newInstance(Tabs.TAB_POPULAR.tabId)
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}