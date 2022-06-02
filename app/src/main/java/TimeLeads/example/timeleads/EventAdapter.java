package TimeLeads.example.timeleads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timeleads.R;

import java.util.ArrayList;

import TimeLeads.model.EventModel;

public class EventAdapter extends ArrayAdapter<EventModel> {

    public EventAdapter(Context context, ArrayList<EventModel> eventModelArrayList){

        super(context, R.layout.eventitem, eventModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        EventModel event = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.eventitem,parent,false);
        }
        /*ImageView imgView = convertView.findViewById(R.id.eventImg);
        TextView titleView = convertView.findViewById(R.id.eventTlt);
        TextView dateView = convertView.findViewById(R.id.eventDate);

        imgView.setImageResource(Integer.parseInt(event.getImagem()));
        titleView.setText(event.getTitulo());
        dateView.setText(event.getData());*/

        return super.getView(position, convertView, parent);
    }
}
