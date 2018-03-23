package com.mcmacedo.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mcmacedo.agenda.dao.AlunoDAO;
import com.mcmacedo.agenda.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaDeAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        this.listaDeAlunos = findViewById(R.id.lista_alunos);

        // Listener dos itens da lista de Alunos, clique simples.
        this.listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) ListaAlunosActivity.this.listaDeAlunos.getItemAtPosition(position);
                Intent intentNavegaFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentNavegaFormulario.putExtra("aluno", aluno);
                startActivity(intentNavegaFormulario);
            }
        });

        // Botão principal do app, no canto inferior direito.
        Button novoAluno = findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNavegaFormulario = new Intent(
                        ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentNavegaFormulario);
            }
        });

        // Listener dos itens da lista de Alunos, clique prolongado.
        registerForContextMenu(this.listaDeAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) ListaAlunosActivity.this.listaDeAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.delete(aluno);
                dao.close();

                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, alunos);

        listaDeAlunos.setAdapter(adapter);
    }
}
