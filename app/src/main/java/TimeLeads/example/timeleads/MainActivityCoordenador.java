package TimeLeads.example.timeleads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import com.example.timeleads.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivityCoordenador extends AppCompatActivity implements EventsCoordenador.Listener, Students.Listener, RequestsCoordenador.Listener {

    BottomNavigationView bottomNav;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = (int) getIntent().getIntExtra("COORDENADOR_ID", 0);

        setContentView(R.layout.activity_main_coordenador);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        bottomNav = findViewById(R.id.bottom_navigation_cord);
        viewPager = findViewById(R.id.view_pager);
        setUpViewPager();
        bottomNav.setOnNavigationItemSelectedListener((item) ->{
            switch (item.getItemId()){
                case R.id.events:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.profile:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.requests:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });


    }


    private void setUpViewPager() {
        ViewPagerAdpaterCoordenador viewPagerAdpater = new ViewPagerAdpaterCoordenador(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
                        bottomNav.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.requests).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void itemClicked(int id) {
        Intent i = new Intent(this, EventActivity.class);
        i.putExtra("EVENTO_ID", id);
        //startActivity(i);
    }


}