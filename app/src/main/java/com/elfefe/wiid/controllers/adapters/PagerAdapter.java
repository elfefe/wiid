package com.elfefe.wiid.controllers.adapters;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.elfefe.wiid.models.MainPages;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return MainPages.values()[position].title;
    }
}