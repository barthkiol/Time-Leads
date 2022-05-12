package TimeLeads.uteis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.fragment.app.Fragment;

import com.example.timeleads.R;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.example.timeleads.Events;
import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class LinhaConsultarAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;
    private Events lista;
    List<EventModel> eventModels = new ArrayList<>();

    EventRepository eventRepository;

    public LinhaConsultarAdapter(Events lista, List<EventModel> eventModels){
        this.eventModels = eventModels;
        this.lista = lista;
        layoutInflater = (LayoutInflater) this.lista.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.eventRepository = new EventRepository(lista.getContext());
    }

    public void AtualizarLista(){
        this.eventModels.clear();
        this.eventModels = eventRepository.ListarEventos();
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View viewLinhaLista =layoutInflater.inflate(R.layout.activity_linha_consultar_eventos, null);




        return viewLinhaLista;
    }
}
