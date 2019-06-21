package com.elfefe.wiid.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.elfefe.wiid.controllers.adapters.PagerAdapter;
import com.elfefe.wiid.controllers.fragments.CommunityFragment;
import com.elfefe.wiid.controllers.fragments.MapsFragment;
import com.elfefe.wiid.controllers.fragments.PrivateFragment;
import com.elfefe.wiid.utility.ConnectionManager;
import com.elfefe.wiid.utility.PreferenceUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.elfefe.wiid.R;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager pager;
    private TabLayout tabLayout;
    NavigationView navigationView;
    Button connect;

    List<Fragment> fragments = new ArrayList<>();

    boolean connected = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar  = findViewById(R.id.activity_main_toolbar);
        DrawerLayout drawerLayout  = findViewById(R.id.activity_main_drawer_layout);
        navigationView = findViewById(R.id.activity_main_nav_view);


        pager = findViewById(R.id.activity_main_viewPager);
        tabLayout = findViewById(R.id.activity_main_tabLayout);

        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        SendBird.init(PreferenceUtils.APP_ID, getApplicationContext());

        final SharedPreferences preferences = getSharedPreferences(PreferenceUtils.KEY, Context.MODE_PRIVATE);

        fragments.add(MapsFragment.newInstance());
        fragments.add(CommunityFragment.newInstance());
        fragments.add(PrivateFragment.newInstance());

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),fragments);

        pager.setAdapter(adapter);

        Log.d("onCreate: ", navigationView.getHeaderCount() + "");

        navigationView.setNavigationItemSelectedListener(this);

        connect = navigationView.getHeaderView(0).findViewById(R.id.navDrawer_header_connect);
    }


    @Override
    protected void onResume() {
        super.onResume();

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CONNECT ", String.valueOf(connected));
                if (connected){
                    navigationView.removeHeaderView(navigationView.getHeaderView(0));
                    navigationView.inflateHeaderView(R.layout.activity_main_nav_header_connect);
                    connected = false;
                } else {
                    navigationView.removeHeaderView(navigationView.getHeaderView(0));
                    navigationView.inflateHeaderView(R.layout.activity_main_nav_header_login);
                    connected = true;
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}