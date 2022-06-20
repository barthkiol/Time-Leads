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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;

import java.util.List;

import TimeLeads.model.AlunoModel;
import TimeLeads.repository.AlunoRepository;


public class AlunosCoordenador extends Fragment {

    ListView lv;
    View rootview;
    ArrayAdapter<String> adapter;

    static interface Listener{
        void itemClicked(int id);
    }

    Integer [] ids;
    private EventsCoordenador.Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (EventsCoordenador.Listener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview =  inflater.inflate(R.layout.events,container, false);

        int idCoordenadorLogado = (int) getActivity().getIntent().getIntExtra("COORDENADOR_ID", 0);



        AlunoRepository alunoRepository = new AlunoRepository(((MainActivity) getContext()));
        List<AlunoModel> alunos = alunoRepository.ListarAlunosCoordenador(idCoordenadorLogado);
        int l = alunos.size();
        String [] nomes = new String[l];
        ids =  new Integer[l];
        for(int i=0;i<l;i++){
            ids [i] = alunos.get(i).getId();
            nomes[i] = alunos.get(i).getNome();

        }

        lv =  rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.eventitem, R.id.text_view, nomes);
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



        return rootview;

    }
}
