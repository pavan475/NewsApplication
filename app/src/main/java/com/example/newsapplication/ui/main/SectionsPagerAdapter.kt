package com.example.newsapplication.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.newsapplication.R
import com.example.newsapplication.model.Source


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager,categoryies:List<Source>) :
    FragmentPagerAdapter(fm) {
    var cat:List<Source>
init {
    cat=categoryies
}
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1,cat[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return cat[position].name
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return cat.size
    }
}