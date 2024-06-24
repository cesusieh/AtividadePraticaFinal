package models;

import controllers.Helper;

public class Tarefa {
    private int ID;
    private String nome;
    private boolean concluida;

    public Tarefa(String nome) {
        this.ID = Helper.buscarUltimoID();
        this.nome = nome;
        this.concluida = false;
    }

    public Tarefa(int ID, String nome) {
        this.ID = ID;
        this.nome = nome;
        this.concluida = true;
    }

    @Override
    public String toString() {
        return "{"
                + "\"id\": \"" + ID + "\", "
                + "\"nome\": \"" + nome + "\", "
                + "\"concluida\": \"" + concluida + "\""
                + "}";
    }
}
