package br.crateus.fscsocios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText etEmailLogin, etSenhaLogin;
    Button btEntrarLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmailLogin = (EditText) findViewById(R.id.etEmailLogin);
        etSenhaLogin = (EditText) findViewById(R.id.etSenhaLogin);
        btEntrarLogin = (Button) findViewById(R.id.btEntrarLogin);


        btEntrarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();

                firebaseAuth.signInWithEmailAndPassword(etEmailLogin.getText().toString(),
                        etSenhaLogin.getText().toString()).addOnCompleteListener(LoginActivity.this,
                        new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("Entrou", "login feito com sucesso");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        }else {
                            Log.w("ok", "trap", task.getException());
                            Toast.makeText(LoginActivity.this, "Falhou", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else{
            startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        }
    }
}
