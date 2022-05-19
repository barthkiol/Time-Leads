package TimeLeads.example.timeleads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.widget.TextView;

import com.example.timeleads.R;

import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class EventActivity extends AppCompatActivity {

    TextView titulo, horas, data, descricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        int id = (int) getIntent().getIntExtra("EVENTO_ID", 0);
        getSupportActionBar().setTitle("Evento " + id);
        EventRepository repo = new EventRepository(this);
        Bundle extra = this.getIntent().getExtras();
        int id_evento = extra.getInt("EVENTO_ID");
        EventModel event = new EventRepository(this).GetEvento(id_evento);
        titulo = findViewById(R.id.txtTitulo);
        horas = findViewById(R.id.txtHoras);
        data = findViewById(R.id.txtData);
        descricao = findViewById(R.id.textDesc);

        titulo.setText(event.getTitulo());
        horas.setText("Horas complementares: " + event.getHoras_validas());
        data.setText("Data: " + event.getData());
        descricao.setText(event.getDescricao());

    }
}