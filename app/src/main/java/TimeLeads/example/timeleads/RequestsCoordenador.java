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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.ChamadoModel;
import TimeLeads.repository.ChamadoRepository;

public class RequestsCoordenador extends Fragment {

    ListView lv;
    View rootview;
    ArrayAdapter<String> adapter;
    private Listener listener;

    static interface Listener{
        void itemClicked(int id);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }


    Integer [] ids;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.events,container, false);
        ChamadoRepository chamadoRepository = new ChamadoRepository(((MainActivityCoordenador) getContext()));
        int idCoordenadorLogado = (int) getActivity().getIntent().getIntExtra("COORDENADOR_ID", 0);



        List<ChamadoModel> chamados = chamadoRepository.ListarChamadosAbertos();
        int l = chamados.size();
        String [] titulos = new String[l];
        ids =  new Integer[l];
        ArrayList<ChamadoModel> chamadoModelArrayList = new ArrayList<>();
        for(int i=0;i<l;i++){
            ids [i] = chamados.get(i).getId();
            titulos[i] = chamados.get(i).getTitulo();
        }

        lv =  rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.requestitem, R.id.text_view, titulos);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(rootview.getContext(), RequestActivity.class);
                int id_chamado = ids[i];
                intent.putExtra("COORDENADOR_ID", idCoordenadorLogado);
                intent.putExtra("CHAMADO_ID", id_chamado);
                startActivity(intent);
                listener.itemClicked(i);
            }
        });



        return rootview;

    }



}
