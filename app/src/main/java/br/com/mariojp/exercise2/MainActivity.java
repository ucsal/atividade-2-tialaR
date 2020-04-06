package br.com.mariojp.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView helloUserTxt;
    private Button btnChangeUser;
    private String mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloUserTxt = findViewById(R.id.textView);
        btnChangeUser = findViewById(R.id.btnTrocar);

        Intent intent = getIntent();
        mensagem = intent.getStringExtra("mensagem");
        if(mensagem != null && !mensagem.equals("") ) {
            helloUserTxt.setText("Oi, " + mensagem + "!");
        }else {
            helloUserTxt.setText("Oi!");
        }

        btnChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), OutraActivity.class);
                it.putExtra("usuarioLogado", mensagem);
                startActivity(it);
            }
        });
    }
}
