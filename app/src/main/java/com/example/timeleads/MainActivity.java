package com.example.timeleads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;


import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, new Fragment1()).commit();

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.events:
                        fragment = new Fragment1();
                        break;
                    case R.id.hours:
                        fragment = new Fragment2();
                        break;
                    case R.id.profile:
                        fragment = new Fragment3();
                        break;
                    case R.id.requests:
                        fragment = new Fragment4();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, fragment).commit();
                return true;
            }
        });


    }

}