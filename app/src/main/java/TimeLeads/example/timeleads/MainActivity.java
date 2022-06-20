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

import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class MainActivity extends AppCompatActivity implements Events.Listener {

    BottomNavigationView bottomNav;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = (int) getIntent().getIntExtra("ALUNO_ID", 0);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));

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

    @Override
    public void itemClicked(int id) {
        Intent i = new Intent(this, EventActivity.class);
        i.putExtra("EVENTO_ID", id);
        //startActivity(i);
    }

    protected void criarEvento(){
        EventModel eventModel = new EventModel();
        eventModel.setData("10-02-2022");
        eventModel.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras semper eu sapien eu semper. Ut ultrices, nisi quis rutrum euismod, sapien urna commodo mauris, at lacinia quam ligula sit amet ex.");
        eventModel.setTitulo("Lorem ipsum");
        eventModel.setHoras_validas("2");
        EventRepository repo = new EventRepository(this);
        repo.salvar(eventModel);

    }
}