package TimeLeads.example.timeleads;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.os.IResultReceiver;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timeleads.R;

import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class EventActivity extends AppCompatActivity {

    TextView titulo, horas, data, descricao;
    ImageView imgfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        int id = (int) getIntent().getIntExtra("EVENTO_ID", 0);
        //int id = 28;
        getSupportActionBar().setTitle("Evento ");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        EventRepository repo = new EventRepository(this);
        Bundle extra = this.getIntent().getExtras();
        int id_evento = extra.getInt("EVENTO_ID");
        //int id_evento = 28;
        EventModel event = new EventRepository(this).GetEvento(id_evento);
        titulo = findViewById(R.id.txtTitulo);
        horas = findViewById(R.id.txtHoras);
        data = findViewById(R.id.txtData);
        descricao = findViewById(R.id.textDesc);
        imgfoto = findViewById(R.id.imageView2);

        titulo.setText(event.getTitulo());
        horas.setText("Horas complementares: " + event.getHoras_validas());
        data.setText("Data: " + event.getData());
        descricao.setText(event.getDescricao());
        imgfoto.setImageBitmap(StringToBitMap(event.getImagem()));
    }

    public Bitmap StringToBitMap(String encodedString){ //
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}