package br.ufc.quixada.atividade02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.ufc.quixada.atividade02.transactions.Constants;

public class ActivityEdit extends AppCompatActivity {

    EditText edtModelo;
    EditText edtFabricante;
    EditText edtCor;

    boolean edit;
    int idCarroEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtModelo = findViewById(R.id.editTextModelo);
        edtFabricante = findViewById(R.id.editTextFabricante);
        edtCor = findViewById(R.id.editTextCor);
        edit =false;
        if(getIntent().getExtras() != null){
            String modelo = (String) getIntent().getExtras().get("modelo");
            String fabricante = (String) getIntent().getExtras().get("fabricante");
            String cor = (String) getIntent().getExtras().get("cor");
            idCarroEditar = (int)getIntent().getExtras().get("id");

            edtModelo.setText(modelo);
            edtFabricante.setText(fabricante);
            edtCor.setText(cor);

            edit = true;
        }
    }

    public void cancelar(View view){
        setResult(Constants.RESULT_CANCEL);
        finish();
    }

    public  void adicionar(View view){
        Intent intent = new Intent();

        String modelo = edtModelo.getText().toString();
        String fabricante = edtFabricante.getText().toString();
        String cor = edtCor.getText().toString();

        intent.putExtra("modelo", modelo);
        intent.putExtra("fabricante", fabricante);
        intent.putExtra("cor", cor);

        if(edit) intent.putExtra("id", idCarroEditar);

        setResult(Constants.RESULT_ADD, intent);
        finish();
    }
}