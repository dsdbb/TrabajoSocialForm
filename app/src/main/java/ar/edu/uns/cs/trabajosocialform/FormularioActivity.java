package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.edu.uns.cs.trabajosocialform.DataModel.Formulario;
import ar.edu.uns.cs.trabajosocialform.configuracion.Configuracion;

public class FormularioActivity extends AppCompatActivity {

    private Bundle bundle;
    private boolean update;
    private Formulario updateForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Configuracion config = getConfigurationFile();

        bundle = new Bundle();
        bundle.putSerializable("CONFIG",config);

        update = getIntent().getBooleanExtra("UPDATE",false);
        if(update){
            updateForm =(Formulario)getIntent().getSerializableExtra("UPDATE_FORM");
            rellenarCampos(updateForm);
        }
    }

    public void continuar(View view){
        Formulario form = tomarDatos();
        Intent intent = new Intent(this,FormSolicitanteActivity.class);
        intent.putExtra("CONFIG",bundle);
        intent.putExtra("FORM",form);
        intent.putExtra("UPDATE",update);
        if(update){
            intent.putExtra("UPDATE_FORM", updateForm);
        }

        startActivity(intent);

    }

    public Formulario tomarDatos(){
        String nombre = ((EditText)findViewById(R.id.nombre_entrevistador_et)).getText().toString();
        String apellido = ((EditText)findViewById(R.id.apellido_entrevistador_et)).getText().toString();

        Formulario form = new Formulario();
        form.setNombreEntrevistador(nombre);
        form.setApellidoEntrevistador(apellido);

        return form;
    }

    private Configuracion getConfigurationFile(){
        try
        {
            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    openFileInput("config.txt")));

            /*Leo el String json del archivo de configuracion y hago la deserializacion en la clase configuracion con la
             librería GSON*/
            String json = fin.readLine();
            Log.i("EL JSON QUE VA CONFIG",json);
            Configuracion config = (new Gson()).fromJson(json, Configuracion.class);
            Log.i("archivo configuracion",json);
            return config;
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
        }
        return null;
    }

    private void rellenarCampos(Formulario updateForm){

    }
}
