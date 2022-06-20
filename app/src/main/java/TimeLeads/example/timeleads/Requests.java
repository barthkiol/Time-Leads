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
import TimeLeads.repository.Curso_AlunoRepository;
import TimeLeads.repository.HorasComplementaresRepository;

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
        rootview =  inflater.inflate(R.layout.requests,container, false);
        ChamadoRepository chamadoRepository = new ChamadoRepository(((MainActivity) getContext()));
        int alunoId = (int) getActivity().getIntent().getIntExtra("ALUNO_ID", 0);
        fab = rootview.findViewById(R.id.fab);

        Curso_AlunoRepository curso_alunoRepository = new Curso_AlunoRepository((MainActivity) getContext());
        HorasComplementaresRepository horasComplementaresRepository = new HorasComplementaresRepository(((MainActivity) getContext()));
        Integer totalHoras = horasComplementaresRepository.GetTotalHorasComplementaresAluno(alunoId);
        String horasCurso = curso_alunoRepository.GetHorasCursoAluno(alunoId);
        TextView complete = (TextView) rootview.findViewById(R.id.txtFinal);

        if(totalHoras == null ||  totalHoras == 0){

        }else{
            if(totalHoras >= Integer.parseInt(horasCurso)){
                fab.setVisibility(View.GONE);
                complete.setVisibility(View.VISIBLE);
                complete.setText("Você já completou todas as suas horas comeplementares!");
            }
        }


        List<ChamadoModel> chamados = chamadoRepository.ListarChamadosAluno(alunoId);
        int l = chamados.size();
        String [] titulos = new String[l];
        String [] data = new String[l];
        ids =  new Integer[l];
        ArrayList<ChamadoModel> chamadoModelArrayList = new ArrayList<>();
        for(int i=0;i<l;i++){
            ids [i] = chamados.get(i).getId();
            titulos[i] = chamados.get(i).getTitulo() + " - " + chamados.get(i).getStatus();
            data[i] = chamados.get(i).getData_envio();
            ChamadoModel request = new ChamadoModel();
            request.setTitulo(chamados.get(i).getTitulo());
            request.setData_envio(chamados.get(i).getData_envio());
            chamadoModelArrayList.add(request);
        }

        lv =  rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.requestitem, R.id.text_view, titulos);
        lv.setAdapter(adapter);


//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(rootview.getContext(), EventActivity.class);
//                int id_chamado = ids[i];
//                intent.putExtra("CHAMADO_ID", id_chamado);
//                startActivity(intent);
//                listener.itemClicked(i);
//            }
//        });

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
