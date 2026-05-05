package com.imepac.gustavoimepac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ClinicaMedica.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabela Pacientes
        String CREATE_TABLE_PACIENTE = "CREATE TABLE Pacientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "cpf TEXT UNIQUE NOT NULL, " +
                "telefone TEXT, " +
                "email TEXT, " +
                "dataNascimento TEXT, " +
                "endereco TEXT)";
        db.execSQL(CREATE_TABLE_PACIENTE);

        // Tabela Profissionais
        String CREATE_TABLE_PROFISSIONAL = "CREATE TABLE Profissionais (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "especialidade TEXT, " +
                "crm TEXT UNIQUE NOT NULL, " +
                "horarioAtendimento TEXT)";
        db.execSQL(CREATE_TABLE_PROFISSIONAL);

        // Tabela Consultas
        String CREATE_TABLE_CONSULTA = "CREATE TABLE Consultas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pacienteId INTEGER, " +
                "profissionalId INTEGER, " +
                "data TEXT NOT NULL, " +
                "horario TEXT NOT NULL, " +
                "status TEXT NOT NULL, " +
                "FOREIGN KEY(pacienteId) REFERENCES Pacientes(id), " +
                "FOREIGN KEY(profissionalId) REFERENCES Profissionais(id))";
        db.execSQL(CREATE_TABLE_CONSULTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Consultas");
        db.execSQL("DROP TABLE IF EXISTS Profissionais");
        db.execSQL("DROP TABLE IF EXISTS Pacientes");
        onCreate(db);
    }

    // ==========================================================
    // MÉTODOS DE MANIPULAÇÃO DE DADOS (CRUD) DA TABELA PACIENTES
    // ==========================================================

    // Método para ADICIONAR um novo paciente ao banco de dados
    public void addPaciente(Paciente paciente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", paciente.getNome());
        values.put("cpf", paciente.getCpf());
        values.put("telefone", paciente.getTelefone());
        values.put("email", paciente.getEmail());
        values.put("dataNascimento", paciente.getDataNascimento());
        values.put("endereco", paciente.getEndereco());

        // Insere a linha na tabela
        db.insert("Pacientes", null, values);
        db.close(); // Fecha a conexão com o banco
    }

    // Método para LISTAR todos os pacientes cadastrados
    public List<Paciente> getAllPacientes() {
        List<Paciente> lista = new ArrayList<>();

        // Usamos getReadableDatabase porque vamos apenas LER os dados
        SQLiteDatabase db = this.getReadableDatabase();

        // O Cursor percorre os resultados da nossa busca SQL
        Cursor cursor = db.rawQuery("SELECT * FROM Pacientes", null);

        if (cursor.moveToFirst()) {
            do {
                // Monta o objeto Paciente com os dados retornados do banco
                Paciente p = new Paciente(
                        cursor.getInt(0),    // id
                        cursor.getString(1), // nome
                        cursor.getString(2), // cpf
                        cursor.getString(3), // telefone
                        cursor.getString(4), // email
                        cursor.getString(5), // dataNascimento
                        cursor.getString(6)  // endereco
                );
                // Adiciona na lista
                lista.add(p);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }
}