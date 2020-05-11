package com.example.marcatruco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcatruco.Adapter.AdapterUser;
import com.example.marcatruco.Modelo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btMenosT1, btMenosT2, btMaisT1, btMaisT2;
    private Button btTruco;
    public TextView txplacar1, txPlacar2;
    public Spinner sEquipe1, sEquipe2;

    Integer time1 = 0;
    Integer time2 = 0;
    Integer click = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregaWidgets();
        marcaTento();
        truco();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuaction, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.zerar:
                click = 0;
                time1 = 0;
                time2 = 0;
                txPlacar2.setText("0");
                txplacar1.setText("0");
                btMaisT1.setText("+1");
                btMaisT2.setText("+1");
                btTruco.setText("TRUCO");
                return true;
            case R.id.player:
                Intent cad = new Intent(this,CadastraUsuarios.class);
                startActivity(cad);
                return true;
            case R.id.historico:
                Intent rank = new Intent(this,Ranking.class);
                startActivity(rank);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void carregaWidgets(){
        btMenosT1 = (Button)findViewById(R.id.btnMenosT1);
        btMenosT2 = (Button)findViewById(R.id.btnMenosT2);
        btMaisT1 = (Button)findViewById(R.id.btnMaisT1);
        btMaisT2 = (Button)findViewById(R.id.btnMaisT2);
        btTruco = (Button)findViewById(R.id.btnTruco);
        txplacar1 = (TextView)findViewById(R.id.txtPlacar1);
        txPlacar2 = (TextView)findViewById(R.id.txtPlacar2);
        sEquipe1 = (Spinner)findViewById(R.id.spEquipe1);
        sEquipe2 = (Spinner)findViewById(R.id.spEquipe2);
    }


    public void marcaTento(){
        btMaisT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click == 1){
                    time1+=3;
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else if(click == 2){
                    time1+=6;
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else if(click == 3){
                    time1+=9;
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else if(click == 4){
                    time1+=12;
                    btMaisT2.setText("+1");
                    btMaisT1.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else{
                    click = 0;
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                    time1++;
                }
                if(time1>11){
                    mensagemVitoria("Fim de jogo","A equipe 1 é a vencedora");
                    click = 0;
                    time1 = 0;
                    time2 = 0;
                    txPlacar2.setText("0");
                    txplacar1.setText("0");
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                }
                txplacar1.setText(time1.toString());
            }
        });
        btMaisT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click == 1){
                    time2+=3;
                    btMaisT2.setText("+1");
                    btMaisT1.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else if(click == 2){
                    time2+=6;
                    btMaisT2.setText("+1");
                    btMaisT1.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else if(click == 3){
                    time2+=9;
                    btMaisT2.setText("+1");
                    btMaisT1.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else if(click == 4){
                    time2+=12;
                    btMaisT2.setText("+1");
                    btMaisT1.setText("+1");
                    btTruco.setText("TRUCO");
                    click = 0;
                }
                else{
                    click = 0;
                    btMaisT2.setText("+1");
                    btMaisT1.setText("+1");
                    btTruco.setText("TRUCO");
                    time2++;
                }
                if(time2>11){
                    mensagemVitoria("Fim de jogo","A equipe 2 é a vencedora");
                    click = 0;
                    time1 = 0;
                    time2 = 0;
                    txPlacar2.setText("0");
                    txplacar1.setText("0");
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                }
                txPlacar2.setText(time2.toString());
            }
        });
        btMenosT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time1 <= 0){
                    Toast.makeText(getApplicationContext(),
                            "Não há pontos para remover", Toast.LENGTH_SHORT).show();
                }else{
                    time1--;
                    txplacar1.setText(time1.toString());
                }

            }
        });
        btMenosT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time2 <= 0){
                    Toast.makeText(getApplicationContext(),
                            "Não há pontos para remover", Toast.LENGTH_SHORT).show();
                }else{
                    time2--;
                    txPlacar2.setText(time2.toString());
                }

            }
        });
    }


    public void truco(){
        btTruco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click++;
                if(click == 1){
                    btTruco.setText("SEIS");
                    btMaisT1.setText("+3");
                    btMaisT2.setText("+3");
                }
                else if(click == 2){
                    btTruco.setText("NOVE");
                    btMaisT1.setText("+6");
                    btMaisT2.setText("+6");
                }
                else if(click == 3){
                    btTruco.setText("DOZE");
                    btMaisT1.setText("+9");
                    btMaisT2.setText("+9");
                }
                else if(click == 4){
                    btTruco.setText("CANCELAR TRUCO");
                    btMaisT1.setText("+12");
                    btMaisT2.setText("+12");
                }
                else{
                    click = 0;
                    btMaisT1.setText("+1");
                    btMaisT2.setText("+1");
                    btTruco.setText("TRUCO");
                }

            }
        });
    }

    public void mensagemVitoria(String titulo, String texto){
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setMessage(texto);
        msg.setTitle(titulo);
        msg.setNeutralButton("OK", null);
        msg.show();
    }



}
