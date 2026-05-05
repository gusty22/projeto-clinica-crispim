package com.imepac.gustavoimepac;

public class Consulta {
    private int id;
    private int pacienteId;
    private int profissionalId;
    private String data; // Formato YYYY-MM-DD
    private String horario; // Formato HH:MM
    private String status; // Agendada, Confirmada, Cancelada, Realizada

    public Consulta(int id, int pacienteId, int profissionalId, String data, String horario, String status) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.profissionalId = profissionalId;
        this.data = data;
        this.horario = horario;
        this.status = status;
    }

    // Gere os Getters e Setters para todos os atributos
}