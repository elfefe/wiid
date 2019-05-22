package com.elfefe.wiid.controllers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.elfefe.wiid.models.Pages

class FragmentPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        return Pages.values()[i].fragment
    }

    override fun getCount(): Int {
        return Pages.values().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return Pages.values()[position].title
    }
}