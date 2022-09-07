package com.q.capstonemovieq.core.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.q.capstonemovieq.core.constant.Tabs
import com.q.capstonemovieq.home.category.ShowItemFragment

class MovieCategoryAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        val tab = Tabs.getTabById(position)
        return if (tab != null) {
            ShowItemFragment.newInstance(tab.tabId)
        } else {
            ShowItemFragment.newInstance(Tabs.TAB_POPULAR.tabId)
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}