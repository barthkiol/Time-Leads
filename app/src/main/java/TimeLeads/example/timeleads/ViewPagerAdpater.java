package TimeLeads.example.timeleads;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdpater extends FragmentStatePagerAdapter {
    public ViewPagerAdpater(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Events();
            case 1:
                return new Hours();
            case 2:
                return new Profile();
            case 3:
                return new Requests();
            default:
                return new Events();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
