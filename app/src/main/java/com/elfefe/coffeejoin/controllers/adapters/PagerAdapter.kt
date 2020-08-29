package com.elfefe.coffeejoin.controllers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.elfefe.coffeejoin.models.MainPages

class PagerAdapter(
    fm: FragmentManager?,
    private val fragments: List<Fragment>
) : FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return MainPages.values()[position].title
    }

}