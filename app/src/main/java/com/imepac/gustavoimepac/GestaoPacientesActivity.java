package com.imepac.gustavoimepac;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class GestaoPacientesActivity extends AppCompatActivity {

    private ListView listaPacientes;
    private Button btnNovoPaciente, btnVoltar;
    private DatabaseHelper dbHelper;
    private PacienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestao_pacientes);

        dbHelper = new DatabaseHelper(this);
        listaPacientes = findViewById(R.id.listaPacientes);
        btnNovoPaciente = findViewById(R.id.btnNovoPaciente);
        btnVoltar = findViewById(R.id.btnVoltar);

        atualizarLista();

        btnNovoPaciente.setOnClickListener(v -> abrirModalCadastro());
        btnVoltar.setOnClickListener(v -> finish());
    }

    private void atualizarLista() {
        List<Paciente> pacientes = dbHelper.getAllPacientes();
        adapter = new PacienteAdapter(this, pacientes);
        listaPacientes.setAdapter(adapter);
    }

    private void abrirModalCadastro() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_cadastro_paciente, null);

        EditText editNome = view.findViewById(R.id.editNome);
        EditText editCPF = view.findViewById(R.id.editCPF);
        EditText editTelefone = view.findViewById(R.id.editTelefone);
        EditText editEmail = view.findViewById(R.id.editEmail);
        EditText editDataNasc = view.findViewById(R.id.editDataNasc);
        EditText editEndereco = view.findViewById(R.id.editEndereco);

        builder.setView(view)
                .setPositiveButton("Salvar", (dialog, id) -> {
                    String nome = editNome.getText().toString();
                    String cpf = editCPF.getText().toString();

                    if(!nome.isEmpty() && !cpf.isEmpty()) {
                        Paciente novo = new Paciente(0, nome, cpf,
                                editTelefone.getText().toString(),
                                editEmail.getText().toString(),
                                editDataNasc.getText().toString(),
                                editEndereco.getText().toString());

                        dbHelper.addPaciente(novo);
                        atualizarLista();
                        Toast.makeText(this, "Paciente salvo!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Nome e CPF são obrigatórios", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", (dialog, id) -> dialog.cancel());

        builder.create().show();
    }
}