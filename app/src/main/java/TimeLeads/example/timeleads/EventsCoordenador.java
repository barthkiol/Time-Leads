package TimeLeads.example.timeleads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class EventsCoordenador extends Fragment {

    ListView lv;
    View rootview;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

    static interface Listener{
        void itemClicked(int id);
    }

    Integer [] ids;
    private Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.events_fragment,container, false);

        int idCoordenadorLogado = (int) getActivity().getIntent().getIntExtra("COORDENADOR_ID", 0);
        fab = rootview.findViewById(R.id.fab);


        EventRepository eventRepository = new EventRepository(((MainActivityCoordenador) getContext()));
        List<EventModel> eventos = eventRepository.ListarEventos();
        int l = eventos.size();
        String [] titulos = new String[l];
        ids =  new Integer[l];
        for(int i=0;i<l;i++){
            ids [i] = eventos.get(i).getId();
            titulos[i] = eventos.get(i).getTitulo();

        }

        lv =  rootview.findViewById(R.id.listViewEventCord);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.eventitem, R.id.text_view, titulos);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootview.getContext(), SalvarEvento.class);
                startActivity(intent);
            }
        });


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
