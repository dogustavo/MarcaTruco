package com.example.marcatruco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marcatruco.Adapter.AdapterUser;
import com.example.marcatruco.Modelo.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {

    private ListView listRankingV;

    private List<User> userList = new ArrayList<User>();
    private AdapterUser adapter = new AdapterUser(userList, this);
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        iniciarDatabase();
        Pesquisar();

        listRankingV = (ListView)findViewById(R.id.ltRankingV);

    }

    private void iniciarDatabase(){FirebaseApp.initializeApp(Ranking.this);}

    private void Pesquisar(){
        Query query;
        userList.clear();
        query = databaseReference.child("User").orderByChild("vitoria").limitToLast(20);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objdataSnapshot : dataSnapshot.getChildren()) {
                    User user = objdataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                listRankingV.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
