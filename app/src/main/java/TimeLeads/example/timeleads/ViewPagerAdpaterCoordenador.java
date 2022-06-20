package TimeLeads.example.timeleads;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdpaterCoordenador extends FragmentStatePagerAdapter {
    public ViewPagerAdpaterCoordenador(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new EventsCoordenador();
            case 1:
                return new Students();
            case 2:
                return new RequestsCoordenador();
            default:
                return new EventsCoordenador();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
