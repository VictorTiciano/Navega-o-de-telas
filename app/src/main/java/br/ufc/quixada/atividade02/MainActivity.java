package br.ufc.quixada.atividade02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.quixada.atividade02.model.Aluno;
import br.ufc.quixada.atividade02.model.Carro;
import br.ufc.quixada.atividade02.transactions.Constants;

public class MainActivity extends AppCompatActivity {

    int selected;
    ArrayList<Carro> listaCarros;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;

        listaCarros = new ArrayList<Carro>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaCarros);
        ListView listViewCarros = (ListView) findViewById(R.id.listViewCarros);
        listViewCarros.setAdapter(adapter);
        listViewCarros.setSelector(android.R.color.holo_blue_light);

        listViewCarros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(MainActivity.this,
                        "" + listaCarros.get(position).toString(),
                        Toast.LENGTH_SHORT).show();
                selected = position;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                clicarAdicionar();
                break;
            case R.id.edit:
                clicarEditar();
                break;
            case R.id.delete:
                apagarItemLista();
                break;
        }
        return true;
    }

    public void apagarItemLista() {
        if (selected >= 0) {
            listaCarros.remove(selected);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(MainActivity.this, "Selecione um Item", Toast.LENGTH_SHORT).show();
        }
    }

    public void clicarAdicionar() {
        Intent intent = new Intent(this, ActivityEdit.class);
        startActivityForResult(intent, Constants.REQUEST_ADD);
    }

    public void clicarEditar() {

        if (selected >= 0) {
            Intent intent = new Intent(this, ActivityEdit.class);

            Carro carro = listaCarros.get(selected);

            intent.putExtra("id", carro.getId());
            intent.putExtra("modelo", carro.getModelo());
            intent.putExtra("fabricante", carro.getFabricante());
            intent.putExtra("cor", carro.getCor());

            startActivityForResult(intent, Constants.REQUEST_EDIT);
        } else {
        Toast.makeText(MainActivity.this, "Selecione um Item", Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD) {
            String modelo = (String) data.getExtras().get("modelo");
            String fabricante = (String) data.getExtras().get("fabricante");
            String cor = (String) data.getExtras().get("cor");
            Carro carro = new Carro(modelo, fabricante, cor);
            listaCarros.add(carro);
            adapter.notifyDataSetChanged();
        } else if (requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD) {
            String modelo = (String) data.getExtras().get("modelo");
            String fabricante = (String) data.getExtras().get("fabricante");
            String cor = (String) data.getExtras().get("cor");
            int idEditar = (int)data.getExtras().get("id");

            for (Carro carro: listaCarros) {
                if(carro.getId() == idEditar){
                    carro.setModelo(modelo);
                    carro.setFabricante(fabricante);
                    carro.setCor(cor);
                }
            }
            adapter.notifyDataSetChanged();
        } else if (resultCode == Constants.RESULT_CANCEL) {
            Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
        }
    }
}