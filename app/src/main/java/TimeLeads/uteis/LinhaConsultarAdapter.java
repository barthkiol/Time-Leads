package TimeLeads.uteis;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timeleads.R;

import java.util.List;

import TimeLeads.example.timeleads.Events;
import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class LinhaConsultarAdapter extends ArrayAdapter<EventModel> {

    private Context context;
    private static LayoutInflater layoutInflater = null;
    private Events lista;
    private List<EventModel> eventModels = null;

    EventRepository eventRepository;

    public LinhaConsultarAdapter(Context context, List<EventModel> eventModels){
        super(context, 0, eventModels);
        this.eventModels = eventModels;
        this.context = context;
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
    public EventModel getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        EventModel evento = eventModels.get(position);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.activity_linha_consultar_eventos, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.img1);
        TextView textViewTitulo = (TextView) view.findViewById(R.id.title);
        TextView textViewData = (TextView) view.findViewById(R.id.data);
        TextView textViewDescricao = (TextView) view.findViewById(R.id.descLong1);

        imageView.setImageDrawable(Drawable.createFromPath(evento.getImagem()));
        textViewTitulo.setText(evento.getTitulo());
        textViewData.setText(evento.getData());
        textViewDescricao.setText(evento.getDescricao());


        return view;
    }
}
