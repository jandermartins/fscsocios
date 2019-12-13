package br.fsc.crateus.fscsocios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btNovoSocio, btBuscaSocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNovoSocio = (Button) findViewById(R.id.btNovoSocio);
        btBuscaSocio = (Button) findViewById(R.id.btBuscarSocio);

        btNovoSocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastraSocio.class));
            }
        });
    }
}
