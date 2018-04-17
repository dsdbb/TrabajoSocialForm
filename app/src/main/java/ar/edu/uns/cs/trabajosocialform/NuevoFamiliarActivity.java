package ar.edu.uns.cs.trabajosocialform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
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
        String[] values = getResources().getStringArray(R.array.sexo_familiar_opciones);
        for (String valor: values) {
            Log.i("Valor: ",valor);

        }
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.spinner_row,values));
    }

    public void nuevoIngresoNoLaboral(View view){
        LinearLayout contenedor = (LinearLayout) findViewById(R.id.panel_nuevo_ingreso_no_laboral);
        LayoutInflater inflater = LayoutInflater.from(this);

        View inflatedView = inflater.inflate(R.layout.panel_ingreso_no_laboral, contenedor, false);
        contenedor.addView(inflatedView);
    }
}
