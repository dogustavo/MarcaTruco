package com.example.marcatruco.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.marcatruco.Modelo.User;
import com.example.marcatruco.R;

import java.util.AbstractCollection;
import java.util.List;


public class AdapterUser extends BaseAdapter {

    private final List<User> users;
    private final Activity activity;

    public AdapterUser(List<User> users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.listviewusuario, viewGroup, false);

        User user = users.get(i);
        TextView nome = (TextView)v.findViewById(R.id.txtNomeRankin);
        TextView vit = (TextView)v.findViewById(R.id.txtVitoriaRanking);

        nome.setText(user.getNome());
        vit.setText(String.valueOf(user.getVitoria()));
        return v;
    }
}
