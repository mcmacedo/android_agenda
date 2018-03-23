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
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity) {
        this.campoNome = activity.findViewById(R.id.formulario_nome);
        this.campoEndereco = activity.findViewById(R.id.formulario_endereco);
        this.campoTelefone = activity.findViewById(R.id.formulario_telefone);
        this.campoSite = activity.findViewById(R.id.formulario_site);
        this.campoNota = activity.findViewById(R.id.formulario_nota);

        this.aluno = new Aluno();
    }

    public Aluno getInstanciaAluno() {
        this.aluno.setNome(this.campoNome.getText().toString());
        this.aluno.setEndereco(this.campoEndereco.getText().toString());
        this.aluno.setTelefone(this.campoTelefone.getText().toString());
        this.aluno.setSite(this.campoSite.getText().toString());
        this.aluno.setNota(Double.valueOf(this.campoNota.getProgress()));

        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        this.campoNome.setText(aluno.getNome());
        this.campoEndereco.setText(aluno.getEndereco());
        this.campoTelefone.setText(aluno.getTelefone());
        this.campoSite.setText(aluno.getSite());
        this.campoNota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}
