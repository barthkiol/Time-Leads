package TimeLeads.example.timeleads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.timeleads.R;

import TimeLeads.model.ChamadoModel;
import TimeLeads.model.EventModel;
import TimeLeads.repository.ChamadoRepository;
import TimeLeads.repository.EventRepository;

public class RequestActivity extends AppCompatActivity {

    TextView titulo, status, data, descricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        int id = (int) getIntent().getIntExtra("CHAMADO_ID", 0);
        getSupportActionBar().setTitle("Chamado " + id);
        EventRepository repo = new EventRepository(this);
        Bundle extra = this.getIntent().getExtras();
        int id_chamado = extra.getInt("CHAMADO_ID");
        ChamadoModel chamadoModel = new ChamadoRepository(this).GetChamado(id_chamado);
        titulo = findViewById(R.id.txtTituloR);
        status = findViewById(R.id.txtStatusR);
        data = findViewById(R.id.txtDataR);
        descricao = findViewById(R.id.txtDescR);

        titulo.setText(chamadoModel.getTitulo());
        status.setText("Status: " + chamadoModel.getStatus());
        data.setText("Data: " + chamadoModel.getData_envio());
        descricao.setText(chamadoModel.getDescricao());

    }
}