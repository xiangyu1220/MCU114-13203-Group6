package com.example.a5work

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val startFragment = StartFragment()
    val progressFragment = ProgressFragment()
    val resultFragment = ResultFragment()

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> startFragment
            1 -> progressFragment
            else -> resultFragment
        }
    }
}