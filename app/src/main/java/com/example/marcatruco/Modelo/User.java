package com.example.marcatruco.Modelo;

public class User {
    private String nome;
    private int vitoria = 0;

    public User() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVitoria() {
        return vitoria;
    }

    public void setVitoria(int vitoria) { this.vitoria = vitoria; }




/*
    @Override
    public String toString() {
        return "Usuário{" +
                "nome='" + nome + '\'' +
                ", vitoria=" + vitoria +
                '}';
    }*/
}
