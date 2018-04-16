package ar.edu.uns.cs.trabajosocialform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class NuevoFamiliarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_familiar);

        rellenarListasDesplegables();
    }

    public void guardar(View view){
        finish();
    }


    private void rellenarListasDesplegables(){
        Spinner spinner = (Spinner)findViewById(R.id.spinner_sexo_familiar);
        String[] values = getResources().getStringArray(R.string.sexo_familiar);
        spinner.setAdapter();
    }
}
