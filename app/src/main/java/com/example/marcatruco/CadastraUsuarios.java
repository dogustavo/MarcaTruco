package com.example.marcatruco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.marcatruco.Modelo.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastraUsuarios extends AppCompatActivity {

    private Button btCadastrar;
    private TextView txNome;

    private ArrayAdapter<User> UserArrayAdapeter;

    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_usuarios);
        carregaWidgets();
        iniciarDatabase();
        carregarBotoes();
    }

    public void carregaWidgets(){
        btCadastrar = (Button)findViewById(R.id.btnCadastrar);
        txNome = (TextView)findViewById(R.id.txtNome);
    }

    private void iniciarDatabase(){
        FirebaseApp.initializeApp(CadastraUsuarios.this);
    }

    private void carregarBotoes(){
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User us = new User();
                us.setNome(txNome.getText().toString());
                DatabaseReference ref = databaseReference.child("User").child(us.getNome().toString());
                Log.d("teste","btcad");
                ref.setValue(us);
            }
        });
    }
}
