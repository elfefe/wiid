package com.elfefe.wiid.controllers

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import com.elfefe.wiid.R
import com.elfefe.wiid.controllers.adapters.FragmentPagerAdapter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.activity_main_toolbar)
        val drawerLayout : DrawerLayout = findViewById(R.id.activity_main_drawer_layout)
        val navigationView : NavigationView = findViewById(R.id.activity_main_nav_view)
        val pager : ViewPager = findViewById(R.id.activity_main_viewPager)
        val tabLayout : TabLayout = findViewById(R.id.activity_main_tabLayout)


        pager.setAdapter(FragmentPagerAdapter(supportFragmentManager))

        tabLayout.setupWithViewPager(pager)
        tabLayout.setTabMode(TabLayout.MODE_FIXED)

        setSupportActionBar(toolbar)

        if (supportActionBar != null)
            supportActionBar!!.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}