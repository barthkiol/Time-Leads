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

public class Requests extends Fragment {

    ListView lv;
    View rootview;
    ArrayAdapter<String> adapter;
    static interface Listener{
        void itemClicked(int id);
    }

    FloatingActionButton fab;

    Integer [] ids;
    private Listener listener;
    TextView txt;

   /* @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.events,container, false);
        ChamadoRepository chamadoRepository = new ChamadoRepository(((MainActivity) getContext()));
        int alunoId = (int) getActivity().getIntent().getIntExtra("ALUNO_ID", 0);
        fab = rootview.findViewById(R.id.fab);



        List<ChamadoModel> chamados = chamadoRepository.ListarChamadosAluno(alunoId);
        int l = chamados.size();
        String [] titulos = new String[l];
        String [] data = new String[l];
        ids =  new Integer[l];
        ArrayList<ChamadoModel> chamadoModelArrayList = new ArrayList<>();
        for(int i=0;i<l;i++){
            ids [i] = chamados.get(i).getId();
            titulos[i] = chamados.get(i).getTitulo();
            data[i] = chamados.get(i).getData_envio();
            ChamadoModel request = new ChamadoModel();
            request.setTitulo(chamados.get(i).getTitulo());
            request.setData_envio(chamados.get(i).getData_envio());
            chamadoModelArrayList.add(request);
        }
        String[] nomes2 = {"A", "B", "C"};

        //EventAdapter listAdapter = new EventAdapter((MainActivity) getContext(), eventModelArrayList);
        lv =  rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.requestitem, R.id.text_view, titulos);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(rootview.getContext(), EventActivity.class);
                int id_chamado = ids[i];
                intent.putExtra("CHAMADO_ID", id_chamado);
                startActivity(intent);
                listener.itemClicked(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootview.getContext(), CriarChamado.class);
                intent.putExtra("ALUNO_ID", alunoId);
                startActivity(intent);
            }
        });



        return rootview;

    }



}
