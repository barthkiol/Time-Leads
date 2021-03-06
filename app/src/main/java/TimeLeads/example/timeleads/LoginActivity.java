package TimeLeads.example.timeleads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timeleads.R;

import TimeLeads.model.AlunoModel;
import TimeLeads.model.CoordenadorModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.repository.CoordenadorRepository;

public class LoginActivity extends AppCompatActivity {

    TextView email, password;
    Button login;
    String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));


        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailString = email.getText().toString();
                passwordString = password.getText().toString();
                AlunoRepository alunoRepository = new AlunoRepository(getApplicationContext());
                AlunoModel alunoLogin = alunoRepository.GetAlunoLogin(emailString, passwordString);
                CoordenadorRepository coordenadorRepository = new CoordenadorRepository(getApplicationContext());
                CoordenadorModel coordenadorLogin = coordenadorRepository.GetCoordenadorLogin(emailString, passwordString);
                if(alunoLogin != null){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("ALUNO_ID", alunoLogin.getId());
                    startActivity(intent);
                    finish();
                }
                else if (coordenadorLogin != null){
                    Intent intent = new Intent(getBaseContext(), MainActivityCoordenador.class);
                    intent.putExtra("COORDENADOR_ID", coordenadorLogin.getId());
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(LoginActivity.this, "Email e/ou senha errados!", Toast.LENGTH_SHORT).show();
                    password.clearComposingText();
                }
            }
        });
    }
}