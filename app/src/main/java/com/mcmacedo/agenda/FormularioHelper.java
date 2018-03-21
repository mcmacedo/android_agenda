package com.mcmacedo.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.mcmacedo.agenda.modelo.Aluno;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar campoNota;

    public FormularioHelper(FormularioActivity activity) {
        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
        this.campoSite = activity.findViewById(R.id.formulario_site);
        this.campoNota = activity.findViewById(R.id.formulario_nota);
    }

    public Aluno getInstanciaAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(this.campoNome.getText().toString());
        aluno.setEndereco(this.campoEndereco.getText().toString());
        aluno.setTelefone(this.campoTelefone.getText().toString());
        aluno.setSite(this.campoSite.getText().toString());
        aluno.setNota(Double.valueOf(this.campoNota.getProgress()));

        return aluno;
    }
}
