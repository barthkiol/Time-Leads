package TimeLeads.example.timeleads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;

public class Events extends Fragment {

    private CardView card;
    private ImageView img1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.events,container, false);
        ((MainActivity) getActivity()).setActionBarTitle("Eventos");

        card = (CardView) rootview.findViewById(R.id.card);
        img1 = (ImageView) rootview.findViewById(R.id.img1);
        TextView desclong1 = (TextView) rootview.findViewById(R.id.descLong1);
        card.findViewById(R.id.card);
        img1.setVisibility(View.GONE);
        desclong1.setVisibility(View.GONE);

        card .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(img1.getVisibility() == View.VISIBLE){
                    img1.setVisibility(View.GONE);
                    desclong1.setVisibility(View.GONE);
                }
                else{
                    img1.setVisibility(View.VISIBLE);
                    desclong1.setVisibility(View.VISIBLE);
                }
            }
        });


        return rootview;

    }

}
