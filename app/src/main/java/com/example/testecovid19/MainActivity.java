package com.example.testecovid19;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txt_name;
    EditText txt_idade;
    CheckBox secrecao_garganta, febre_alta, tosse, dores_musculares, falta_de_ar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txt_name = findViewById(R.id.txt_name);
        this.txt_idade = findViewById(R.id.txt_idade);
        this.febre_alta = (CheckBox) findViewById(R.id.febre_alta);
        this.tosse = (CheckBox) findViewById(R.id.tosse);
        this.dores_musculares = (CheckBox) findViewById(R.id.dores_musculares);
        this.falta_de_ar = (CheckBox) findViewById(R.id.falta_de_ar);
        this.secrecao_garganta = (CheckBox) findViewById(R.id.secrecao_garganta);
    }

    public void button_confirm(View view) {
        if (txt_name.getText().toString().equals("")) {
            exibirMensagemSnack("Digite seu Nome");
        } else if (txt_idade.getText().toString().equals("")) {
            exibirMensagemSnack("Digite sua Idade");
        } else {
            onClick(view);
        }
    }

    public void exibirMensagemToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    public void exibirMensagemSnack(String mensagem) {
        Snackbar.make(this.txt_idade, mensagem, Snackbar.LENGTH_LONG).show();
    }

    public void exibirMensagemAlert(String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensagem);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exibirMensagemToast("Boa sorte");
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exibirMensagemToast("Entao morra");
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Voce concorda com as Recomendações?");
        alert.show();
    }

    @Override
    public void onClick(View v) {
        int idade = Integer.parseInt(this.txt_idade.getText().toString());
        if (idade < 60 && secrecao_garganta.isChecked() || febre_alta.isChecked() || tosse.isChecked() || dores_musculares.isChecked() || falta_de_ar.isChecked()) {
            exibirMensagemAlert("Voce nao esta no grupo de risco porem apresenta os sintomas, trate-os e se persistirem procure um medico!");
        } else if (idade < 60) {
            exibirMensagemAlert("Voce não esta no grupo de risco, porem deve seguir as recomendacoes da quarentena!");
        } else if (idade >= 60 && secrecao_garganta.isChecked() || febre_alta.isChecked() || tosse.isChecked() || dores_musculares.isChecked() || falta_de_ar.isChecked()) {
            exibirMensagemAlert("Voce esta no grupo de risco e apresenta os sintomas, PROCURE UM MÉDICO!");
        } else if (idade >= 60) {
            exibirMensagemAlert("Voce esta no grupo de risco porem nao apresenta sintomas, siga as recomendações da AN da Saude!");
        }
    }
}
