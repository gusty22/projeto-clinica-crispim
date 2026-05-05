package com.imepac.gustavoimepac;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelaPerfil extends AppCompatActivity {

    private TextView txtNomePerfil, txtEmailPerfil;
    private Button btnGestaoPacientes, btnGestaoProfissionais, btnAgendaConsultas, btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        // Mapeamento de UI
        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil);
        btnGestaoPacientes = findViewById(R.id.btnGestaoPacientes);
        btnGestaoProfissionais = findViewById(R.id.btnGestaoProfissionais);
        btnAgendaConsultas = findViewById(R.id.btnAgendaConsultas);
        btnSair = findViewById(R.id.btnSair);

        // Recupera os dados passados pelo Login (SharedPreferences)
        String nomeUsuario = getIntent().getStringExtra("nomeUsuario");
        String emailUsuario = getIntent().getStringExtra("emailUsuario");

        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
            txtNomePerfil.setText(nomeUsuario);
        }
        if (emailUsuario != null && !emailUsuario.isEmpty()) {
            txtEmailPerfil.setText(emailUsuario);
        }

        // Navegação do Sistema
        btnGestaoPacientes.setOnClickListener(v -> {
            // Cria a "intenção" de sair da TelaPerfil e ir para a GestaoPacientesActivity
            Intent intent = new Intent(TelaPerfil.this, GestaoPacientesActivity.class);
            startActivity(intent);
        });
        btnGestaoProfissionais.setOnClickListener(v -> {
            // Intent para Activity de Profissionais (A ser criada)
            Toast.makeText(this, "Abrindo Gestão de Profissionais...", Toast.LENGTH_SHORT).show();
        });

        btnAgendaConsultas.setOnClickListener(v -> {
            // Intent para Activity de Agenda (A ser criada)
            Toast.makeText(this, "Abrindo Agenda...", Toast.LENGTH_SHORT).show();
        });

        btnSair.setOnClickListener(v -> {
            Intent intent = new Intent(TelaPerfil.this, FormLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}