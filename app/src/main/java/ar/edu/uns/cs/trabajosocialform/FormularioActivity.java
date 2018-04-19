package ar.edu.uns.cs.trabajosocialform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ar.edu.uns.cs.trabajosocialform.java_classes.ViewAdapter.ViewAdapter;
import ar.edu.uns.cs.trabajosocialform.java_classes.configuracion.Configuracion;

public class FormularioActivity extends AppCompatActivity {

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Configuracion config = getConfigurationFile();

      //  ViewAdapter va = new ViewAdapter(config,this);
       // va.adaptarSolicitante();

        bundle = new Bundle();
        bundle.putSerializable("CONFIG",config);
    }

    public void continuar(View view){
        Intent intent = new Intent(this,FormSolicitanteActivity.class);
        intent.putExtra("CONFIG",bundle);
        startActivity(intent);
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
            Configuracion config = (new Gson()).fromJson(json, Configuracion.class);
            return config;
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
            ex.printStackTrace();
        }
        return null;
    }
}
