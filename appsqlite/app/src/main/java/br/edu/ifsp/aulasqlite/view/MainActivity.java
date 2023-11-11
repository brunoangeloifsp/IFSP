package br.edu.ifsp.aulasqlite.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifsp.aulasqlite.R;
import br.edu.ifsp.aulasqlite.controller.MainActivityController;
import br.edu.ifsp.aulasqlite.databinding.ActivityMainBinding;
import br.edu.ifsp.aulasqlite.model.entity.Pessoa;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MainActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        controller = new MainActivityController(this);

        /*activityMainBinding.recyclerview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Pessoa p = (Pessoa) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                intent.putExtra(CadastroActivity.ID_PESSOA, p.getId());
                startActivity(intent);
            }
        });

        activityMainBinding.recyclerview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Pessoa p = (Pessoa) adapterView.getItemAtPosition(position);
                removerPessoa(p);
                return true;
            }
        });
        */

        activityMainBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));

        carregarPessoas();
    }

    private void carregarPessoas() {
        ArrayList<Pessoa> arrPessoas = controller.getListPessoas();
        ListPessoasRecyclerViewAdapter listPessoasRecyclerViewAdapter =
                new ListPessoasRecyclerViewAdapter(arrPessoas);

        activityMainBinding.recyclerview.setAdapter(listPessoasRecyclerViewAdapter);

        ListPessoasRecyclerViewAdapter.PessoasListener listener = new ListPessoasRecyclerViewAdapter.PessoasListener() {
            @Override
            public void onItemClick(Integer pos) {
                Pessoa p = arrPessoas.get(pos);
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                intent.putExtra(CadastroActivity.ID_PESSOA, p.getId());
                startActivity(intent);
            }
        };

        ListPessoasRecyclerViewAdapter.PessoasLongListener longListener =
                new ListPessoasRecyclerViewAdapter.PessoasLongListener() {
                    @Override
                    public void onItemLongClick(Integer pos) {
                        Pessoa p = arrPessoas.get(pos);
                        removerPessoa(p);
                    }
                };

        listPessoasRecyclerViewAdapter.setClickListener(listener);
        listPessoasRecyclerViewAdapter.setLongClickListener(longListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.ac_adicionar:
                Intent intent = new Intent(this, CadastroActivity.class);
                intent.putExtra(CadastroActivity.ID_PESSOA, 0);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarPessoas();
    }

    private void removerPessoa(Pessoa p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!");
        builder.setMessage("Deseja excluir a Pessoa: " + p.getNome() + " ?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.removerPessoa(p);
                Toast.makeText(getApplicationContext(),
                        "Pessoa removida com sucesso!",
                        Toast.LENGTH_LONG).show();
                        carregarPessoas();
            }
        });
        builder.setNegativeButton(android.R.string.no, null );
        builder.create().show();
    }

}