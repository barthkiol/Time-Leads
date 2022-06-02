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

public class Events extends Fragment {

    ListView lv;
    SearchView searchView;
    View rootview;
    ArrayAdapter<String> adapter;
    FloatingActionButton fab;

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
        rootview =  inflater.inflate(R.layout.events,container, false);

        int idAlunoLogado = (int) getActivity().getIntent().getIntExtra("ALUNO_ID", 0);
        fab = rootview.findViewById(R.id.fab);
        if(idAlunoLogado != 0){
            fab.setVisibility(View.GONE);
        }

        EventRepository eventRepository = new EventRepository(((MainActivity) getContext()));
        List<EventModel> eventos = eventRepository.ListarEventos();
        int l = eventos.size();
        String [] titulos = new String[l];
        String [] data = new String[l];
        int imageId = R.drawable.calendar;
        ids =  new Integer[l];
        ArrayList<EventModel> eventModelArrayList = new ArrayList<>();
        for(int i=0;i<l;i++){
            ids [i] = eventos.get(i).getId();
            titulos[i] = eventos.get(i).getTitulo();
            data[i] = eventos.get(i).getData();
            EventModel event = new EventModel();
            event.setImagem(String.valueOf(imageId));
            event.setTitulo(eventos.get(i).getTitulo());
            event.setData(eventos.get(i).getData());
            event.setHoras_validas(eventos.get(i).getHoras_validas());
            event.setDescricao(eventos.get(i).getDescricao());
            event.setId(eventos.get(i).getId());
            eventModelArrayList.add(event);
        }
        String[] nomes2 = {"A", "B", "C"};

        //EventAdapter listAdapter = new EventAdapter((MainActivity) getContext(), eventModelArrayList);
        lv =  rootview.findViewById(R.id.listView);
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
