package TimeLeads.uteis;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timeleads.R;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.example.timeleads.MainActivity;
import TimeLeads.example.timeleads.StudentsActivity;
import TimeLeads.model.AlunoModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.repository.HorasComplementaresRepository;

public class LinhaConsultarAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;
    private StudentsActivity lista;
    private List<AlunoModel> alunoModels = new ArrayList();


    AlunoRepository alunoRepository;

    public LinhaConsultarAdapter(StudentsActivity lista, List<AlunoModel> alunoModels){
        this.lista = lista;
        this.alunoModels = alunoModels;
        layoutInflater = (LayoutInflater) this.lista.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.alunoRepository = new AlunoRepository(lista);
    }


    @Override
    public int getCount(){
        return alunoModels.size();
    }

    @Override
    public Object getItem (int position){
        return position;
    }

    @Override
    public long getItemId (int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha_consultar_eventos, null);
        TextView textViewTitulo = (TextView) viewLinhaLista.findViewById(R.id.nomeAlunoList);
        TextView textViewHoras = (TextView) viewLinhaLista.findViewById(R.id.horasAlunoList);

        AlunoModel aluno = alunoModels.get(position);
        int idAluno = aluno.getId();

        HorasComplementaresRepository horasComplementaresRepository = new HorasComplementaresRepository(lista);
        Integer alunoHoras = horasComplementaresRepository.GetTotalHorasComplementaresAluno(idAluno);



        textViewTitulo.setText(aluno.getNome());
        if(alunoHoras == null){
            alunoHoras = 0;
        }else if(alunoHoras > 150){
            alunoHoras = 150;
        }
        textViewHoras.setText(alunoHoras + " Horas");


        return viewLinhaLista;
    }
}
