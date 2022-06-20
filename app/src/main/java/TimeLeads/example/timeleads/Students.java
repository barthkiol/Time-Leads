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

import TimeLeads.model.CursoModel;
import TimeLeads.model.EventModel;
import TimeLeads.repository.CursoRepository;
import TimeLeads.repository.EventRepository;

public class Students extends Fragment {

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
        rootview =  inflater.inflate(R.layout.students,container, false);

        int idCoordenadorLogado = (int) getActivity().getIntent().getIntExtra("COORDENADOR_ID", 0);

        fab = rootview.findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

        CursoRepository cursoRepository = new CursoRepository(((MainActivityCoordenador) getContext()));
        List<CursoModel> cursos = cursoRepository.ListarCursosCoordenador(idCoordenadorLogado);
        int l = cursos.size();
        String [] titulos = new String[l];
        ids =  new Integer[l];
        ArrayList<CursoModel> cursoModelArrayList = new ArrayList<>();
        for(int i=0;i<l;i++){
            ids [i] = cursos.get(i).getId();
            titulos[i] = cursos.get(i).getNome();
            CursoModel curso = new CursoModel();
            curso.setNome(cursos.get(i).getNome());
            curso.setPeriodos(cursos.get(i).getPeriodos());
            curso.setCoordenador_id(cursos.get(i).getCoordenador_id());
            curso.setHoras_neces(cursos.get(i).getHoras_neces());
            curso.setId(cursos.get(i).getId());
            cursoModelArrayList.add(curso);
        }

        lv =  rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.eventitem, R.id.text_view, titulos);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(rootview.getContext(), StudentsActivity.class);
                int id_curso = ids[i];
                intent.putExtra("CURSO_ID", id_curso);
                intent.putExtra("CURSO_NOME", titulos[i]);
                startActivity(intent);
                listener.itemClicked(i);
            }
        });

        /*setListAdapter(new ArrayAdapter<String>(inflater.getContext(),
                android.R.layout.simple_list_item_1, nomes));*/


        return rootview;

    }




}
