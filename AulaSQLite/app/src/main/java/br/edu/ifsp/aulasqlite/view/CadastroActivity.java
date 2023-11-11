package br.edu.ifsp.aulasqlite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.edu.ifsp.aulasqlite.R;
import br.edu.ifsp.aulasqlite.controller.CadastroActivityController;
import br.edu.ifsp.aulasqlite.databinding.ActivityCadastroBinding;
import br.edu.ifsp.aulasqlite.model.entity.Pessoa;

public class CadastroActivity extends AppCompatActivity {

    public final static String ID_PESSOA = "br.edu.ifsp.aulasqlite.view.ID_PESSOA";

    private ActivityCadastroBinding activityCadastroBinding;
    private CadastroActivityController controller;
    private int idPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCadastroBinding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(activityCadastroBinding.getRoot());

        controller = new CadastroActivityController(this);

        Intent intent = getIntent();
        if(intent != null) {
            idPessoa = intent.getIntExtra(ID_PESSOA, 0);

            if(idPessoa > 0) {
                //Atualizar Pessoa
                activityCadastroBinding.trSalvar.setVisibility(View.GONE);
                //activityCadastroBinding.btnSalvar.setVisibility(View.GONE);
                carregarPessoa();
            }
            else {
                //Cadastrar Pessoa
                activityCadastroBinding.trEditar.setVisibility(View.GONE);
                //activityCadastroBinding.btnEditar.setVisibility(View.GONE);
            }
        }

        /*activityCadastroBinding.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        //usando lamba
        activityCadastroBinding.btnSalvar.setOnClickListener(view -> {
            adicionarPessoa();
        });

        activityCadastroBinding.btnEditar.setOnClickListener(view -> {
            editarPessoa();
        });
    }

    private void editarPessoa(){
        //Recuperar dados da activity
        String nome = activityCadastroBinding.etNome.getText().toString();
        String cpf = activityCadastroBinding.etCPF.getText().toString();
        String email = activityCadastroBinding.etEmail.getText().toString();

        //validar se a pessoa tem nome
        if(!nome.isEmpty()) {
            //chamar o controller para editar a pessoa
            Pessoa p = new Pessoa(idPessoa, nome, cpf, email);
            controller.updatePessoa(p);
            Toast.makeText(this,
                    "Pessoa editada com sucesso!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            //campo nome vazio
            Toast.makeText(this,
                    "Campo nome é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void adicionarPessoa(){
        //Recuperar dados da activity
        String nome = activityCadastroBinding.etNome.getText().toString();
        String cpf = activityCadastroBinding.etCPF.getText().toString();
        String email = activityCadastroBinding.etEmail.getText().toString();

        //validar se a pessoa tem nome
        if(!nome.isEmpty()) {
            //chamar o controller para salva a pessoa
            Pessoa p = new Pessoa(0, nome, cpf, email);
            controller.addPessoa(p);
            Toast.makeText(this,
                    "Pessoa adicionada com sucesso!",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            //campo nome vazio
            Toast.makeText(this,
                    "Campo nome é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void carregarPessoa() {
        Pessoa p = controller.findPessoa(idPessoa);
        activityCadastroBinding.etNome.setText(p.getNome());
        activityCadastroBinding.etCPF.setText(p.getCpf());
        activityCadastroBinding.etEmail.setText(p.getEmail());
    }

}