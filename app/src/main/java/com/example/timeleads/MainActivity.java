package com.example.timeleads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        //getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, new Events()).commit();

        bottomNav = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager();
        bottomNav.setOnNavigationItemSelectedListener((item) ->{
                switch (item.getItemId()){
                    case R.id.events:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.hours:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.profile:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.requests:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
        });


    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void setUpViewPager() {
        ViewPagerAdpater viewPagerAdpater = new ViewPagerAdpater(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdpater);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    switch (position){
                        case 0:
                            bottomNav.getMenu().findItem(R.id.events).setChecked(true);
                            break;
                        case 1:
                            bottomNav.getMenu().findItem(R.id.hours).setChecked(true);
                            break;
                        case 2:
                            bottomNav.getMenu().findItem(R.id.profile).setChecked(true);
                            break;
                        case 3:
                            bottomNav.getMenu().findItem(R.id.requests).setChecked(true);
                            break;
                    }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}