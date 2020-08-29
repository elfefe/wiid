package com.elfefe.wiid.controllers

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.elfefe.wiid.R
import com.elfefe.wiid.controllers.adapters.PagerAdapter
import com.elfefe.wiid.controllers.fragments.CommunityFragment.Companion.newInstance
import com.elfefe.wiid.controllers.fragments.MapsFragment
import com.elfefe.wiid.controllers.fragments.PrivateFragment
import com.elfefe.wiid.utility.PreferenceUtils
import com.elfefe.wiid.utility.checkPermissions
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_nav_header_connect.*
import kotlinx.android.synthetic.main.activity_main_nav_header_connect.view.*
import kotlinx.android.synthetic.main.navigationview_main.*
import java.util.*

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    var fragments: MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val drawerLayout =
            findViewById<DrawerLayout>(R.id.activity_main_drawer_layout)


        activity_main_tabLayout.setupWithViewPager(activity_main_viewPager)
        activity_main_tabLayout.tabMode = TabLayout.MODE_FIXED

        val toggle =
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                activity_main_toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        fragments.add(MapsFragment.newInstance())
        fragments.add(newInstance())
        fragments.add(PrivateFragment.newInstance())
        val adapter =
            PagerAdapter(
                supportFragmentManager,
                fragments
            )
        activity_main_viewPager.adapter = adapter

        activity_main_nav_view.setNavigationItemSelectedListener(this)

        main_nav_parameter_textview.setOnClickListener {
            if (motionlayout_main_layout.currentState == motionlayout_main_layout.startState)
                motionlayout_main_layout.transitionToEnd()
            else motionlayout_main_layout.transitionToStart()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu_drawer, menu)
        return true
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}