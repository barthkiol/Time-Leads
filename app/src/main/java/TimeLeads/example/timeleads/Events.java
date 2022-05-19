package TimeLeads.example.timeleads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.example.timeleads.R;

import org.w3c.dom.Text;

import java.util.List;

import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;
import TimeLeads.uteis.LinhaConsultarAdapter;

public class Events extends Fragment {

    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;

    static interface Listener{
        void itemClicked(int id);
    }

    Integer [] ids;
    private Listener listener;
    TextView txt;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.events,container, false);
        EventRepository eventRepository = new EventRepository(((MainActivity) getContext()));
        List<EventModel> eventos = eventRepository.ListarEventos();
        int l = eventos.size();
        String [] nomes = new String[l];
        ids =  new Integer[l];
        for(int i=0;i<l;i++){
            ids [i] = eventos.get(i).getId();
            nomes[i] = eventos.get(i).getTitulo();
        }
        String[] nomes2 = {"A", "B", "C"};
        lv = (ListView) rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, nomes);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(rootview.getContext(), EventActivity.class);
                int id_evento = ids[i];
                intent.putExtra("EVENTO_ID", id_evento);
                startActivity(intent);
                listener.itemClicked(i);
            }
        });

        /*setListAdapter(new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, nomes));*/


        return rootview;

    }

    /*@Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(v.getContext(), EventActivity.class);
        int id_evento = ids[position];
        intent.putExtra("EVENTO_ID", id_evento);
        startActivity(intent);
        listener.itemClicked(position);
    }*/



}
