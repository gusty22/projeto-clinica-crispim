package com.imepac.gustavoimepac;

public class Profissional {
    private int id;
    private String nome;
    private String especialidade;
    private String crm;
    private String horarioAtendimento; // Ex: "08:00 - 18:00"

    public Profissional(int id, String nome, String especialidade, String crm, String horarioAtendimento) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.crm = crm;
        this.horarioAtendimento = horarioAtendimento;
    }

    // Gere os Getters e Setters para todos os atributos
}