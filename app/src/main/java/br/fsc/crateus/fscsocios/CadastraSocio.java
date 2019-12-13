package br.fsc.crateus.fscsocios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

public class CadastraSocio extends AppCompatActivity {

    EditText etNomeCadastra, etRgCadastro, etCpfCadastro, etEnderecoCadastrar, etNascimentoCadastrar;
    ImageButton ib3x4Cadastra;
    Button btCadastrar;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private int result;
    private Uri filePath;
    private String idImagem;
    private String urlImagem;
    private String idSocio;
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_socio);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        urlImagem = "";

        etNomeCadastra = (EditText) findViewById(R.id.etNomeCadastra);
        etRgCadastro = (EditText) findViewById(R.id.etRgCadastro);
        etCpfCadastro = (EditText) findViewById(R.id.etCpfCadastro);
        etEnderecoCadastrar = (EditText) findViewById(R.id.etEnderecoCadastrar);
        etNascimentoCadastrar = (EditText) findViewById(R.id.etNascimentoCadastrar);

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        ib3x4Cadastra = (ImageButton) findViewById(R.id.ib3x4Cadastra);

        ib3x4Cadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });


        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Socio socio = new Socio();

                socio.setNome(etNomeCadastra.getText().toString());
                socio.setRG(etRgCadastro.getText().toString());
                socio.setCPF(etCpfCadastro.getText().toString());
                socio.setEndereco(etEnderecoCadastrar.getText().toString());
                socio.setNascimento(etNascimentoCadastrar.getText().toString());
                socio.setUrlFoto3x4(urlImagem);
                socio.setIdNucleo(firebaseUser.getUid());

                FirebaseDatabase database = FirebaseDatabase.getInstance("https://fscsocios.firebaseio.com/");
                final DatabaseReference myRefSocios = database.getReference("socios");

                myRefSocios.push().setValue(socio);


                FirebaseDatabase.getInstance().getReference().child("socios").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                            if (firebaseUser.getUid().equals(snapshot.getValue(Socio.class).getIdNucleo())) {

                                idSocio = snapshot.getKey(); // Encontrado o anuncio que possui o id do usuario logado...
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                final DatabaseReference refNucleo = database.getReference("nucleos");

                FirebaseDatabase.getInstance().getReference().child("nucleos").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                            List<String> socios = snapshot.getValue(Nucleo.class).getIdSocio();

                            for (String socio : socios){
                                if(firebaseUser.getUid().equals(socio)){
                                    refNucleo.child(snapshot.getKey()).child("idSocio").setValue(idSocio);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                startActivity(new Intent(CadastraSocio.this, CadastraSocio.class));
            }
        });
    }


    public void tirarFoto(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == result  && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ib3x4Cadastra.setImageBitmap(bitmap);
                uploadFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void uploadFile() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Carregando imagem...");
            progressDialog.show();

            idImagem = UUID.randomUUID().toString();

            final StorageReference ref = storageReference.child("images/"+ idImagem);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    urlImagem = String.valueOf(uri);
                                }
                            });
                            progressDialog.dismiss();


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();


                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Carregando "+(int)progress+"%");
                        }
                    });
        }
    }


}
