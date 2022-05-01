package com.example.timeleads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, new Events()).commit();

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.events:
                        fragment = new Events();
                        break;
                    case R.id.hours:
                        fragment = new Hours();
                        break;
                    case R.id.profile:
                        fragment = new Profile();
                        break;
                    case R.id.requests:
                        fragment = new Requests();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, fragment).commit();
                return true;
            }
        });


    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}